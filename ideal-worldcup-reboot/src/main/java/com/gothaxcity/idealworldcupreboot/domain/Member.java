package com.gothaxcity.idealworldcupreboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String password;
    private String email;
    private String nickName;

    public Member(String password, String email, String nickName, PasswordEncoder encoder) {
        this.password = encoder.encode(password);
        this.email = email;
        this.nickName = nickName;
    }
}
