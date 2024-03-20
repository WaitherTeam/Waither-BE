package com.waither.weatherservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DailyWeather {
	
	private String region;
	private double tempCur;
	private double tempMin;
	private double tempMax;
	private double humidity;
	private int humidityPer;
	private int windVector;
	private double windDegree;
}
