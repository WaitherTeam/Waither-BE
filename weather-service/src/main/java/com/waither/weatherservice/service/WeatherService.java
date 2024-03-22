package com.waither.weatherservice.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waither.weatherservice.entity.DailyWeather;
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

		List<ApiResponse.Item> items = openApiUtil.callForeCastApi(nx, ny, baseDate, baseTime, 60,
			"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst");

		List<String> expectedTempList = openApiUtil.apiResponseListFilter(items, "T1H");

		List<String> expectedRainList = openApiUtil.apiResponseListFilter(items, "RN1");

		List<String> expectedPtyList = openApiUtil.apiResponseListFilter(items, "PTY");

		List<String> expectedSkyList = openApiUtil.apiResponseListFilter(items, "SKY");

		ApiResponse.Item item = items.get(0);
		String key = item.getNx() + "_" + item.getNy() + "_" + item.getFcstDate() + "_" + item.getFcstTime();

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
		String baseTime) throws URISyntaxException {

		// Base_time : 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
		List<ApiResponse.Item> items = openApiUtil.callForeCastApi(nx, ny, baseDate, baseTime, 300,
			"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");

		String pop = openApiUtil.apiResponseStringFilter(items, "POP");
		String tmn = openApiUtil.apiResponseStringFilter(items, "TMN");
		String tmx = openApiUtil.apiResponseStringFilter(items, "TMX");
		String reh = openApiUtil.apiResponseStringFilter(items, "REH");
		String vec = openApiUtil.apiResponseStringFilter(items, "VEC");
		String wsd = openApiUtil.apiResponseStringFilter(items, "WSD");

		log.info(pop);
		log.info(tmn);
		log.info(tmx);
		log.info(reh);
		log.info(vec);
		log.info(wsd);

		DailyWeather dailyWeather = DailyWeather.builder()
			.pop(pop)
			.tempMin(tmn)
			.tempMax(tmx)
			.humidity(reh)
			.windVector(vec)
			.windDegree(wsd)
			.build();

		log.info("{} : ", dailyWeather);

	}
}
