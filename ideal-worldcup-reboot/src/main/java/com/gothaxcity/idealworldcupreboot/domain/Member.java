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
    private String userName;
    private String profileImage;

    @Enumerated(STRING)
    private Role role;

}
