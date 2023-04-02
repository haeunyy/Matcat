package com.greedy.matcat.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.controller.MemberController;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
class AdminControllerTest {

    @Autowired
    private AdminController adminController;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder PE;
    @Autowired
    private AuthenticationService AS;
    private MockMvc mockMvc;
    @Mock
    private MemberDTO member;

    private HandlerMethodArgumentResolver putAuthenticationPrincipal = new HandlerMethodArgumentResolver() {
        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.getParameterType().isAssignableFrom(MemberDTO.class);
        }
        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            MemberDTO member = new MemberDTO();
            String id = "dlwnstjr0310";
            String pwd = "a1S@D#F$";
            String name = "이준석";
            String email = "dlwnstjr0310@naver.com";
            char gender = 'M';
            String phone = "010-7299-9740";
            String address = "16873$경기 용인시 수지구 달맞이로 17$123";
            char LeaveYN = 'N';
            String grade = "USER";
            member.setMemberId(id);
            member.setMemberPwd(pwd);
            member.setMemberName(name);
            member.setMemberEmail(email);
            member.setMemberGender(gender);
            member.setMemberPhone(phone);
            member.setMemberAddress(address);
            member.setMemberLeaveYN(LeaveYN);
            member.setMemberGrade(grade);
            return member;
        }
    };

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(putAuthenticationPrincipal)
                .build();
    }

    @Test
    void 관리자계정생성() throws Exception {
        //given
        MultiValueMap<String, String> hoho = new LinkedMultiValueMap<>();
        hoho.add("memberName", "이준석");
        hoho.add("memberId", "hoho123");
        hoho.add("memberPwd", "asdf!");

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/regist").params(hoho))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeCount(1))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/main"))
                .andDo(MockMvcResultHandlers.print());
    }

}