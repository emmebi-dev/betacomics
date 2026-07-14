package com.betacomics.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {
    private int status;                
    private String error;             
    private String errorCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<ValidationError> validationErrors;

    @Getter
    @Builder
    public static class ValidationError {
        private String field;
        private Object rejectedValue;
        private String message;
    }
}