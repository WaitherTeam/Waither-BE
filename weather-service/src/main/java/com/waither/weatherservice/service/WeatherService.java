package com.waither.weatherservice.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waither.weatherservice.entity.ExpectedWeather;
import com.waither.weatherservice.openapi.ApiResponse;
import com.waither.weatherservice.openapi.OpenApiUtil;
import com.waither.weatherservice.redis.RedisUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WeatherService {

	private final OpenApiUtil openApiUtil;

	private final RedisUtil redisUtil;

	public void createExpectedWeather(
		int nx,
		int ny,
		String baseDate,
		String baseTime
	) throws URISyntaxException {

		List<ApiResponse.Item> items = openApiUtil.callForeCastApi(nx, ny, baseDate, baseTime,
			"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst");

		List<String> expectedTempList = openApiUtil.apiResponseFilter(items, "T1H");

		List<String> expectedRainList = openApiUtil.apiResponseFilter(items, "RN1");

		List<String> expectedPtyList = openApiUtil.apiResponseFilter(items, "PTY");

		List<String> expectedSkyList = openApiUtil.apiResponseFilter(items, "SKY");

		String key = nx + "_" + ny + "_" + baseDate + "_" + baseTime;
		ExpectedWeather expectedWeather = ExpectedWeather.builder()
			.expectedTemp(expectedTempList)
			.expectedRain(expectedRainList)
			.expectedPty(expectedPtyList)
			.expectedSky(expectedSkyList).build();

		redisUtil.saveAsExpectedWeather(key, expectedWeather, 6L, TimeUnit.HOURS);
		log.info("ExpectedWeather : {}", redisUtil.getExpectedWeather(key));
	}

	// TODO
	public void createDailyWeather(int nx,
		int ny,
		String baseDate,
		String baseTime) {

		return;
	}
}
