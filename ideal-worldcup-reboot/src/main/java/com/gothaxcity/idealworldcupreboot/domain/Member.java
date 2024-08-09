package com.gothaxcity.idealworldcupreboot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static jakarta.persistence.EnumType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String nickName;
    private String profileImage;

    @Column(nullable = false, unique = true)
    private String memberKey;

    @Enumerated(STRING)
    private Role role;

    // OAuth
    public Member(String email, String nickName, String profileImage, String memberKey) {
        this.email = email;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.memberKey = memberKey;
    }

    // 자체 회원가입
    public Member(String email, String nickName, String password, String profileImage, String memberKey , Role role, PasswordEncoder encoder) {
        this.email = email;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.memberKey = memberKey;
        this.role = role;
        this.password = encoder.encode(password);
    }
}
