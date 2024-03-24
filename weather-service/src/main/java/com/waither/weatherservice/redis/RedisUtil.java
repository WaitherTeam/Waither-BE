package com.waither.weatherservice.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

	public void saveAsValue(String key, String val, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue()
			.set(key, val, timeout, timeUnit);
	}

	public void saveAsList(String key, List<String> list, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue()
			.set(key, convertListToString(list), timeout, timeUnit);
	}

	public boolean hasKey(String key) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	public String getAsValue(String key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse("");
	}

	public List<String> getAsList(String key) {
		String temp = Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse("");
		return Arrays.asList(temp.split(","));
	}

	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}
}

