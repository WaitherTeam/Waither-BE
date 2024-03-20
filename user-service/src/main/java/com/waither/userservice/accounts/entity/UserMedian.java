package com.waither.userservice.accounts.entity;

import com.waither.userservice.survey.entity.type.Season;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_median")
@Entity
public class UserMedian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // '매우 추움'~'추움' 의 평균값
    private Integer veryColdMedian;

    // '추움'~'보통' 의 평균값
    private Integer coldNormalMedian;

    // '보통'~'더움' 의 평균값
    private Integer normalHotMedian;

    // '더움'~'매우 더움' 의 평균값
    private Integer veryHotMedian;

    // 계절
    @Enumerated(EnumType.STRING)
    private Season season;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

