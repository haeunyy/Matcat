package com.greedy.matcat.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
@Slf4j
class MemberControllerTest {

    @Autowired
    private MemberController memberController;
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
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(putAuthenticationPrincipal)
                .build();
    }

    @Test
    void 아이디중복체크테스트() throws Exception {
        //given
        String id = "안녕 반가워요";
        member = new MemberDTO();
        member.setMemberId(id);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/idDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("사용 가능한 아이디입니다.");
    }

    @Test
    void 아이디중복체크실패테스트() throws Exception {
        //given
        String id = "dlwnstjr0310";
        member = new MemberDTO();
        member.setMemberId(id);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/idDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("중복 된 아이디가 존재합니다.");
    }


    @Test
    void 회원가입() throws Exception {
        //given
        MultiValueMap<String, String> hoho = new LinkedMultiValueMap<>();
        hoho.add("memberName", "이준석");
        hoho.add("memberId", "hoho123");
        hoho.add("memberPwd", "asdf!");
        hoho.add("memberEmail", "asdf@naver.com");
        hoho.add("memberGender", "F");
        hoho.add("memberPhone", "010-1234-1234");
        hoho.add("zipCode", "12345");
        hoho.add("address1", "히히");
        hoho.add("address2", "하하");

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/member/regist").params(hoho))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeCount(1))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/main"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 이메일확인성공() throws Exception {
        //given
        member = new MemberDTO();
        member.setMemberEmail("dlwnstjr0310@naver.com");
        member.setMemberId("dlwnstjr0310");
        member.setMemberPhone("010-7299-9740");
        member.setMemberName("이준석");
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/mailDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("입력하신 정보가 일치합니다. 임시 비밀번호를 전송하고, 로그인 화면으로 돌아갑니다.");
    }

    @Test
    void 이메일확인실패() throws Exception {
        //given
        member = new MemberDTO();
        member.setMemberEmail("dlwnstjr0310@naver.com");
        member.setMemberId("dlwnstjr0310");
        member.setMemberPhone("010-7299-9711");
        member.setMemberName("이준식");
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/mailDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("입력하신 정보가 일치하지 않습니다.");
    }

    @Test
    void 이메일보내기() throws Exception {
        //given
        String memberEmail = "dlwnstjr0310@naver.com";
        //then & when
        mockMvc.perform(MockMvcRequestBuilders.post("/member/send").param("memberEmail", memberEmail))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/main"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void BeforeMypage_비밀번호체크_성공() throws Exception {

        //given
        String id = "dlwnstjr0310";
        String pwd = "a1S@D#F$";
        member = new MemberDTO();
        member.setMemberPwd(pwd);
        member.setMemberId(id);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/pwDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("비밀번호가 일치합니다 !");
    }

    @Test
    void BeforeMypage_비밀번호체크_실패() throws Exception {
        //given
        String id = "asdf";
        String pwd = "a1S@D#F$";
        member = new MemberDTO();
        member.setMemberId(id);
        member.setMemberPwd(pwd);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/pwDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("비밀번호가 일치하지 않습니다.");
    }

    @Test
    void 로그인후정보수정페이지접속전인증() throws Exception {
        //given
        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/member/Mypage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 회원탈퇴() throws Exception {
        //given
        String reason = "그냥요";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/member/delete").param("memberLeaveReason", reason))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
        //then
    }

}