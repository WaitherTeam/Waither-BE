package com.waither.userservice.accounts.entity;

import com.waither.userservice.accounts.entity.type.UserStatus;
import com.waither.userservice.survey.entity.Survey;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 이름
    private String username;

    // 유저 이메일
    private String email;

    // 유저 비밀번호
    private String password;

    // 유저 상태 (active / 휴면 / 탈퇴 등)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // 프로필 이미지
    private String image;

    // 사용자 맞춤 서비스 허용 여부
    private Boolean custom;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", custom=" + custom +
                '}';
    }
}
