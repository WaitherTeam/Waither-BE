package com.waither.weatherservice.openapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpenApiUtil {

	@Value("${openapi.short.key}")
	private String shortForecastKey;

	// TODO 위도, 경도 -> x, y 좌표 변환 메서드 추가

	// 기상청 Api (초단기, 단기)
	public List<ApiResponse.Item> callForeCastApi(
		int nx,
		int ny,
		String baseDate,
		String baseTime,
		String apiUrl
	) throws URISyntaxException {

		int numOfRows = 60;
		int pageNo = 1;
		String dataType = "JSON";

		WebClient webClient = WebClient.create();
		String uriString = apiUrl + "?serviceKey=" + shortForecastKey +
			"&numOfRows=" + numOfRows +
			"&pageNo=" + pageNo +
			"&dataType=" + dataType +
			"&base_date=" + baseDate +
			"&base_time=" + baseTime +
			"&nx=" + nx +
			"&ny=" + ny;

		URI uri = new URI(uriString);

		log.info("[*] uri : {}", uri);

		ApiResponse.Response response = webClient.get()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve().bodyToMono(ApiResponse.class)
			.block().getResponse();

		if (response.getHeader().getResultCode().equals("00")) {
			return response.getBody().getItems().getItem();
		} else {
			throw new OpenApiException(response.getHeader().getResultMsg());
		}
	}

	public List<String> apiResponseFilter(List<ApiResponse.Item> items, String category) {
		return items.stream()
			.filter(item -> item.getCategory().equals(category))
			.sorted(Comparator.comparing(item -> item.getFcstDate() + item.getFcstTime()))
			.map(ApiResponse.Item::getFcstValue)
			.toList();
	}

}
