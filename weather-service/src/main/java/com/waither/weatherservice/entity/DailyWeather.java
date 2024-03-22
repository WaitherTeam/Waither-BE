package com.waither.weatherservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DailyWeather {

	// 강수확률 (%)
	private String pop;
	private String tempMin;
	private String tempMax;
	private String humidity;
	private String humidityPer;
	private String windVector;
	private String windDegree;
}
