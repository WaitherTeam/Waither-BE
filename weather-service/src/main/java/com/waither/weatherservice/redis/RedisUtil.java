package com.waither.weatherservice.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.waither.weatherservice.entity.ExpectedWeather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedisUtil {

	private static final String EXPECTED_TEMP_SUFFIX = ":expectedTemp";
	private static final String EXPECTED_RAIN_SUFFIX = ":expectedRain";
	private static final String EXPECTED_PTY_SUFFIX = ":expectedPty";
	private static final String EXPECTED_SKY_SUFFIX = ":expectedSky";
	private final RedisTemplate<String, String> redisTemplate;

	private String convertListToString(List<String> list) {
		return String.join(",", list);
	}

	public void saveAsExpectedWeather(String key, ExpectedWeather val, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue()
			.set(key + EXPECTED_TEMP_SUFFIX, convertListToString(val.getExpectedTemp()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + EXPECTED_RAIN_SUFFIX, convertListToString(val.getExpectedRain()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + EXPECTED_PTY_SUFFIX, convertListToString(val.getExpectedPty()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + EXPECTED_SKY_SUFFIX, convertListToString(val.getExpectedSky()), timeout, timeUnit);
	}

	public boolean hasKey(String key) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	public ExpectedWeather getExpectedWeather(String key) {
		String expectedTemp = Optional.ofNullable(redisTemplate.opsForValue().get(key + EXPECTED_TEMP_SUFFIX)).orElse("");
		String expectedRain = Optional.ofNullable(redisTemplate.opsForValue().get(key + EXPECTED_RAIN_SUFFIX)).orElse("");
		String expectedPty = Optional.ofNullable(redisTemplate.opsForValue().get(key + EXPECTED_PTY_SUFFIX)).orElse("");
		String expectedSky = Optional.ofNullable(redisTemplate.opsForValue().get(key + EXPECTED_SKY_SUFFIX)).orElse("");

		return ExpectedWeather.builder()
			.expectedTemp(Arrays.asList(expectedTemp.split(",")))
			.expectedRain(Arrays.asList(expectedRain.split(",")))
			.expectedPty(Arrays.asList(expectedPty.split(",")))
			.expectedSky(Arrays.asList(expectedSky.split(",")))
			.build();
	}

	public boolean delete(String key) {
		return redisTemplate.delete(key + EXPECTED_TEMP_SUFFIX) &&
			redisTemplate.delete(key + EXPECTED_RAIN_SUFFIX) &&
			redisTemplate.delete(key + EXPECTED_PTY_SUFFIX) &&
			redisTemplate.delete(key + EXPECTED_SKY_SUFFIX);
	}
}

