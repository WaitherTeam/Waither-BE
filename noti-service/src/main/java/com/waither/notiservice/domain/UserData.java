package com.waither.notiservice.domain;

import com.waither.notiservice.domain.type.Season;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_data")
@DynamicInsert
@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 각 답변의 평균 값 (level 1 쪽이 추움 ~ level 5 쪽이 더움)
    @ColumnDefault(value = "34")
    private Double level1;

    @ColumnDefault(value = "28")
    private Double level2;

    @ColumnDefault(value = "24")
    private Double level3;

    @ColumnDefault(value = "10")
    private Double level4;

    @ColumnDefault(value = "7")
    private Double level5;

    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private Season season;


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
