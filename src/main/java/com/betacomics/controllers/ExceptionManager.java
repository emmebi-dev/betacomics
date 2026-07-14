package com.betacomics.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.betacomics.dto.output.ApiErrorResponse;
import com.betacomics.exceptions.BetacomicsException;
import com.betacomics.services.interfaces.MessageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionManager {

    private final MessageService messageService;

    /**
     * 1. CATTURA TUTTE LE ECCEZIONI CUSTOM (ResourceNotFoundException, InsufficientStockException, ecc.)
     * Sfrutta il polimorfismo per estrarre automaticamente HTTP Status e Error Code.
     */
    @ExceptionHandler(BetacomicsException.class)
    public ResponseEntity<ApiErrorResponse> handleBetacomicsException(
            BetacomicsException e, HttpServletRequest request) {
        
        // Loggiamo l'evento come avviso (è un errore di business causato dall'utente, non un crash del server)
        log.warn("Business exception caught at [{}]: {} (Code: {})", 
                request.getRequestURI(), e.getMessage(), e.getErrorCode());

        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(e.getHttpStatus().value())
                .error(e.getHttpStatus().getReasonPhrase())
                .errorCode(e.getErrorCode())
                // Passiamo il messaggio (che è la chiave, es: "product.not.found") al tuo MessageService
                .message(messageService.get(e.getMessage()))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

    /**
     * 2. CATTURA GLI ERRORI DI VALIDAZIONE DEI FORM (@Validated / @Valid)
     * Cicla tutti i campi falliti, traduce i messaggi e li impacchetta in una lista comoda per il frontend.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        
        List<ApiErrorResponse.ValidationError> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ApiErrorResponse.ValidationError.builder()
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        // Traduce la stringa di errore del campo (Es: @NotBlank(message = "comic.name.empty"))
                        .message(messageService.get(fieldError.getDefaultMessage()))
                        .build())
                .toList();

        log.warn("Validation failure at [{}] - {} fields rejected", request.getRequestURI(), errors.size());

        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorCode("VALIDATION_FAILED")
                .message(messageService.get("error.validation.global")) // Messaggio generico di testata form
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .validationErrors(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * 3. RETE DI SICUREZZA GLOBALE (NullPointerException, crash del DB, errori di sintassi SQL)
     * Maschera i dettagli tecnici al client per motivi di sicurezza (OWASP standard) e logga l'errore critico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception e, HttpServletRequest request) {
        
        // Logghiamo lo stacktrace intero in modalità ERROR sul server per permetterti il debug
        log.error("CRITICAL: Unhandled exception caught at path: " + request.getRequestURI(), e);

        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorCode("INTERNAL_SERVER_ERROR")
                // Mai esporre e.getMessage() qui, conterrebbe dettagli del DB. Usiamo una chiave sicura.
                .message(messageService.get("error.internal.generic"))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}