package com.waither.weatherservice.dto;

public record WeatherTestRequest(
	int nx,
	int ny,
	String baseDate,
	String baseTime
) {
}
