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

	private final RedisTemplate<String, String> redisTemplate;

	private String convertListToString(List<String> list) {
		return String.join(",", list);
	}

	public void saveAsExpectedWeather(String key, ExpectedWeather val, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue()
			.set(key + ":expectedTemp", convertListToString(val.getExpectedTemp()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + ":expectedRain", convertListToString(val.getExpectedRain()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + ":expectedPty", convertListToString(val.getExpectedPty()), timeout, timeUnit);
		redisTemplate.opsForValue()
			.set(key + ":expectedSky", convertListToString(val.getExpectedSky()), timeout, timeUnit);
	}

	public boolean hasKey(String key) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	public ExpectedWeather getExpectedWeather(String key) {
		String expectedTemp = Optional.ofNullable(redisTemplate.opsForValue().get(key + ":expectedTemp")).orElse("");
		String expectedRain = Optional.ofNullable(redisTemplate.opsForValue().get(key + ":expectedRain")).orElse("");
		String expectedPty = Optional.ofNullable(redisTemplate.opsForValue().get(key + ":expectedPty")).orElse("");
		String expectedSky = Optional.ofNullable(redisTemplate.opsForValue().get(key + ":expectedSky")).orElse("");

		return ExpectedWeather.builder()
			.expectedTemp(Arrays.asList(expectedTemp.split(",")))
			.expectedRain(Arrays.asList(expectedRain.split(",")))
			.expectedPty(Arrays.asList(expectedPty.split(",")))
			.expectedSky(Arrays.asList(expectedSky.split(",")))
			.build();
	}

	public boolean delete(String key) {
		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}
}

