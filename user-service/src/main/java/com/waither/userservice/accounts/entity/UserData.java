package com.waither.userservice.accounts.entity;

import com.waither.userservice.survey.entity.type.Season;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_data")
@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // '매우 추움' 답변의 평균값
    private Double veryCold;

    // '추움' 답변의 평균값
    private Double cold;

    // '좋음/보통' 답변의 평균값
    private Double normal;

    // '더움' 답변의 평균값
    private Double hot;

    // '매우 더움' 답변의 평균값
    private Double veryHot;

    // 계절
    @Enumerated(EnumType.STRING)
    private Season season;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}