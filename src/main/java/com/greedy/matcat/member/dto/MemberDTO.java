package com.greedy.matcat.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.*;

@Data
public class MemberDTO implements UserDetails , Serializable, OAuth2User {

    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberEmail;
    private char memberGender;
    private String memberPhone;
    private String memberAddress;
    private char memberLeaveYN;
    private String memberGrade;
    private String memberLeaveReason;
    private List<AuthMemberDTO> memberRoleList;
    private Map<String,Object> attributes;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authority = new HashSet<>();
        for (AuthMemberDTO role : memberRoleList) {
            authority.add(new SimpleGrantedAuthority(role.getAuthority().getAuthName()));
        }
        return authority;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getName() {
        return null;
    }
}
