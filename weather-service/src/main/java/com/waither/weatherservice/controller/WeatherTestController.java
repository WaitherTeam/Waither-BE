package com.waither.weatherservice.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waither.weatherservice.dto.WeatherTestRequest;
import com.waither.weatherservice.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherTestController {

	private final WeatherService weatherService;

	@PostMapping("/short")
	public void createExpectedWeatherTest(@RequestBody WeatherTestRequest request) throws URISyntaxException {
		weatherService.createExpectedWeather(request.nx(), request.ny(), request.baseDate(), request.baseTime());
	}

	@PostMapping("/daily")
	public void createDailyWeatherTest(@RequestBody WeatherTestRequest request) throws URISyntaxException {
		weatherService.createDailyWeather(request.nx(), request.ny(), request.baseDate(), request.baseTime());
	}
}
