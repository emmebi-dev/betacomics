package com.betacomics.services.impl;

import org.springframework.stereotype.Service;

import com.betacomics.models.Message;
import com.betacomics.repositories.MessageRepository;
import com.betacomics.services.interfaces.MessageServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageImpl implements MessageServices {

	private final MessageRepository messageRepository;
	
	@Override
	public String get(String code) {
		log.debug("get {}", code);
		Message msg = messageRepository.findById(code)
		        .orElse(new Message(code, code));
		return msg.getMessage();
	}

}
