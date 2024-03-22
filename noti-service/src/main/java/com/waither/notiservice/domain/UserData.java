package com.waither.notiservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_data")
@Entity
public class UserData {

    @Id
    private Long userId;

    // 각 답변의 평균 값 (level 1 쪽이 추움 ~ level 5 쪽이 더움)
    private Double level1;
    private Double level2;
    private Double level3;
    private Double level4;
    private Double level5;

    public void setLevel(int level, Double value) {
        switch (level) {
            case 1 -> level1 = value;
            case 2 -> level2 = value;
            case 3 -> level3 = value;
            case 4 -> level4 = value;
            case 5 -> level5 = value;
        }
    }
}
