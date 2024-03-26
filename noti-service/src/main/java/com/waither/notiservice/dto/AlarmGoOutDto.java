package com.waither.notiservice.dto;

import com.waither.notiservice.domain.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 본 DTO는 Kafka 메세지 큐 중 alarm-go-out 토픽에서 사용되는 DTO입니다.
 */
@AllArgsConstructor
@Builder
@Getter
public class AlarmGoOutDto {

    public String token; //사용자 푸시알림 토큰

    public String userName; //사용자 닉네임

    public boolean isUserAlert; //사용자가 "사용자 맞춤 알림"을 on으로 설정했는지?

    //아래 중간값들은 가중치(weight)가 적용된 값을 보내줘야 함
    public Double medianOf1And2;
    public Double medianOf2And3;
    public Double medianOf3And4;
    public Double medianOf4And5;

    public UserMedian getUserMedian() {
        return UserMedian.builder()
                .medianOf1And2(medianOf1And2)
                .medianOf2And3(medianOf2And3)
                .medianOf3And4(medianOf3And4)
                .medianOf4And5(medianOf4And5)
                .build();
    }

}
