package com.waither.userservice.setting.entity;

import com.waither.userservice.accounts.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "settings")
@Entity
// 알림 설정 관련 Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 외출 시간
    private LocalDateTime outTime;

    // 외출 알림
    private Boolean outAlert;

    // 월 ~ 금 알림
    private Boolean sun;
    private Boolean mon;
    private Boolean tue;
    private Boolean wed;
    private Boolean thu;
    private Boolean fri;
    private Boolean sat;

    // 기상 특보 알림
    private Boolean climateAlert;

    // 사용자 맞춤 알림
    private Boolean userAlert;

    // 강설 정보 알림
    private Boolean snowAlert;

    // 바람 세기 알림
    private Boolean windAlert;

    // 바람세기 정도
    private Integer windDegree;

    // 직장 지역 레포트 알림 받기
    private Boolean regionReport;

    // 강수량 보기
    private Boolean precipitation;

    // 풍량/풍속 보기
    private Boolean wind;

    // 미세먼지 보기
    private Boolean dust;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}