package com.waither.userservice.util;

import com.waither.userservice.global.exception.CustomException;
import com.waither.userservice.global.response.ApiResponse;
import com.waither.userservice.global.response.ErrorCode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RestClient {
    public static final String WEATHER_SERVICE_URL = "http://localhost:8081";

    private final WebClient webClient;

    public RestClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(WEATHER_SERVICE_URL).build();
    }

    public Double getTemperature(double latitude, double longitude, LocalDateTime time) {
        ApiResponse<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather/temperature")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("baseTime", time.format(DateTimeFormatter.ISO_DATE_TIME))
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        Mono.error(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR_500)))
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {})
                .block();

        if (response != null && "COMMON200".equals(response.getCode())) {
            return Double.parseDouble(response.getResult());
        } else {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR_500);
        }
    }
}
