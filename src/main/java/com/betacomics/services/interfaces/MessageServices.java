package com.betacomics.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface MessageServices {

	String get(String code);
}
