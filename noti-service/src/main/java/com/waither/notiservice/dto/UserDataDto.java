package com.waither.notiservice.dto;

import com.waither.notiservice.domain.UserData;
import lombok.Getter;

@Getter
public class UserDataDto {

    public Long userId;

    public Integer level;

    public Double temperature;

    public UserData toEntity() {
        UserData userData = UserData.builder()
                .userId(userId)
                .build();
        userData.setLevel(level, temperature);
        return userData;
    }
}
