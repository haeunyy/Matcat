package com.greedy.matcat.member.service;

import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.dao.MemberMapper;
import com.greedy.matcat.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
@Transactional
@Slf4j
class MemberServiceImplTest {

    @Autowired
    private MemberService MemberService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder PE;


    @Test
    void 이아이디사용가능한가요() {
        //given
        String memberId = "dlwnstjr0310123";
        //then
        boolean hello = MemberService.memberIdCheck(memberId);
        //when
        assertFalse(hello);
    }

    /* 회원 가입 */
    @Test
    void 가입() {
        //given
        MemberDTO loginMember = new MemberDTO();
        String address = "03154$서울 종로구 종로 1$123";
        String[] split = address.split("\\$");
        loginMember.setMemberAddress(address);
        loginMember.setMemberEmail("dlwnstjr0310@naver.com");
        loginMember.setMemberId("dlwnstjr0310");
        loginMember.setMemberPhone("010-7299-9740");
        loginMember.setMemberName("이준석");
        loginMember.setMemberPwd("a1S@D#F$");
        loginMember.setMemberGender('M');
        loginMember.setMemberLeaveYN('N');
        //then
        int hoho = MemberService.memberRegist(loginMember);
        //when
        assertThat(hoho).isEqualTo(1);
    }

    @Test
    void 회원정보수정() {
        //given
        MemberDTO loginMember = new MemberDTO();
        String address = "03154$서울 종로구 종로 1$123";
        loginMember.setMemberAddress(address);
        loginMember.setMemberEmail("dlwnstjr0310@naver.com");
        loginMember.setMemberId("dlwnstjr0310");
        loginMember.setMemberPhone("010-7299-9740");
        loginMember.setMemberName("이준식");
        loginMember.setMemberLeaveYN('N');
        loginMember.setMemberNo(14);
        loginMember.setMemberPwd(PE.encode("a1S@D#F$"));
        //when
        int ho = MemberService.modifyMember(loginMember);
        //then
        assertThat(ho).isEqualTo(1);
    }

    /* 아이디 찾기 */
    @Test
    void 아이디찾기() {
        //given
        MemberDTO abc = new MemberDTO();
        abc.setMemberName("이준석");
        abc.setMemberPhone("010-7299-9740");
        abc.setMemberEmail("dlwnstjr0310@naver.com");
        //when
        String ho = MemberService.findMyId(abc);
        //then
        assertThat(ho).isEqualTo("dlwnstjr0310");
    }

    /* 비밀번호 찾기 시 이메일 인증 */
    @Test
    void 이거제이메일맞는데요(){
        //given
        MemberDTO member = new MemberDTO();
        String memberEmail = "dlwnstjr0310@naver.com";
        String memberId = "dlwnstjr0310";
        String memberName = "이준석";
        String memberPhone = "010-7299-9740";
        member.setMemberEmail(memberEmail);
        member.setMemberId(memberId);
        member.setMemberName(memberName);
        member.setMemberPhone(memberPhone);
        //when
        boolean hello = MemberService.selectMemberByEmail(member);
        //then
        assertTrue(hello);
    }

    /* 찾아온 아이디의 비밀번호 변경 */
    @Test
    void 비번변경() {
        //given
        String memberId = "dlwnstjr0310";
        String memberPwd = "a1S@D#F$";
        //when
        int update = MemberService.updatePassword(memberId, memberPwd);
        //then
        assertThat(update).isEqualTo(1);
    }

    /* 회원 탈퇴 */
    @Test
    void 탈퇴() {
        //given
        MemberDTO bye = new MemberDTO();
        bye.setMemberId("hoho123");
        bye.setMemberLeaveReason("실험용이라서");
        //when
        int asd = MemberService.removeMember(bye);
        //then
        assertThat(asd).isEqualTo(1);
    }


    /* 개인정보 수정 전 비밀번호 체크 */
    @Test
    void 마이페이지비밀번호체크() {
        //given
        String abc = "hoho123";
        //when
        String abc2 = MemberService.memberPwCheck(abc);
        //then
        assertNotNull(abc2);
    }
}