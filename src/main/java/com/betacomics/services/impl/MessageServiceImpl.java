package com.betacomics.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.models.Message;
import com.betacomics.repositories.MessageRepository;
import com.betacomics.services.interfaces.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "errorMessages", key = "#code")
    public String get(String code) {
        log.debug("Database hit for error message code: {}", code);
        
        if (code == null || code.isBlank()) {
            return "Unknown error";
        }

        return messageRepository.findById(code)
                .map(Message::getMessage)
                .orElse(code);
    }
}