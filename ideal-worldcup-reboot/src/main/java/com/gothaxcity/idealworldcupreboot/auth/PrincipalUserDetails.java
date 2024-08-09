package com.gothaxcity.idealworldcupreboot.auth;

import com.gothaxcity.idealworldcupreboot.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record PrincipalUserDetails(Member member, Map<String, Object> attributes, String attributeKey) implements OAuth2User, UserDetails {

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getMemberKey();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(member.getRole().getKey()));
    }

    @Override
    public String getName() {
        return attributes.get(attributeKey).toString();
    }


    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }


    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return UserDetails.super.isEnabled();
    }
}
