package com.waither.weatherservice.openapi;

public class OpenApiException extends RuntimeException {
	public OpenApiException(String message) {
		super(message);
	}
}
