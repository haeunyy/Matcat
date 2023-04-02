package com.greedy.matcat.member.service;


import com.greedy.matcat.member.dao.MemberMapper;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.dto.OAuthAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthenticationService extends DefaultOAuth2UserService implements UserDetailsService {

    private final MemberMapper mapper;
    public AuthenticationService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) {
        return mapper.login(memberId);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());

        MemberDTO member = mapper.login(attributes.getEmail());
        if (member == null) {
            mapper.memberRegistForApi(attributes.getName(), attributes.getEmail());
            mapper.memberAuth();
            member = mapper.login(attributes.getEmail());
        }
        return member;
    }
}
