package com.waither.weatherservice.entity;

import java.util.List;
import java.util.StringJoiner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpectedWeather {

	// 예상 기온
	private List<String> expectedTemp;

	// 예상 강수량
	private List<String> expectedRain;

	// 예상 강수 형태
	private List<String> expectedPty;

	// 예상 하늘 상태
	private List<String> expectedSky;

	@Override
	public String toString() {
		return new StringJoiner(", ", ExpectedWeather.class.getSimpleName() + "[", "]")
			.add("expectedTemp=" + expectedTemp)
			.add("expectedRain=" + expectedRain)
			.add("expectedPty=" + expectedPty)
			.add("expectedSky=" + expectedSky)
			.toString();
	}
}
