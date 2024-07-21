package com.waither.userservice.dto.request;

import java.time.LocalDateTime;

public class SurveyReqDto {

    public record SurveyRequestDto(
            Double latitude,
            Double longitude,
            // level
            Integer ans,
            LocalDateTime time
    ) {}

}
