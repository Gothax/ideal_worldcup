package com.gothaxcity.idealworldcupreboot.accounts.domain;

import com.gothaxcity.idealworldcupreboot.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String nickName;
    private String profileImage;
    @Enumerated(STRING)
    private Role role;

}
