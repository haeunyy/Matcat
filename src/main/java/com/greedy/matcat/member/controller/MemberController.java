package com.greedy.matcat.member.controller;

import com.greedy.matcat.member.dto.MailDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.AuthenticationService;
import com.greedy.matcat.member.service.MailService;
import com.greedy.matcat.member.service.MailServiceImpl;
import com.greedy.matcat.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Value("${spring.mail.username}")
    private String from;
    private final MailService mailService;
    private final MemberService memberService;
    private final PasswordEncoder PE;
    private final MessageSourceAccessor messageSourceAccessor;
    private final AuthenticationService AS;

    @Autowired
    public MemberController(MailService mailService,
                            MemberService memberService,
                            PasswordEncoder PE,
                            MessageSourceAccessor messageSourceAccessor,
                            AuthenticationService AS) {
        this.mailService = mailService;
        this.memberService = memberService;
        this.PE = PE;
        this.messageSourceAccessor = messageSourceAccessor;
        this.AS = AS;
    }

    /* 회원 가입 */
    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member,
                               @RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
                               RedirectAttributes rttr) {

        String Address = zipCode + "$" + address1 + "$" + address2;
        member.setMemberAddress(Address);
        member.setMemberPwd(PE.encode(member.getPassword()));
        log.info("[MemberController] registMember : {} ", member);
        int result = memberService.memberRegist(member);
        if (result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
            return "redirect:/main";
        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("regist.fail"));
            return "redirect:/main";
        }
    }

    /* 아이디 중복 확인 */
    @PostMapping(value = "/idDupCheck", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> idCheck(@RequestBody MemberDTO member) {

        String result = "사용 가능한 아이디입니다.";
        if (memberService.memberIdCheck(member.getMemberId())) {
            result = "중복 된 아이디가 존재합니다.";
        }

        return ResponseEntity.ok(result);
    }

    /* 이메일 중복 확인 */
    @PostMapping(value = "/emailDupCheck", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> emailDupCheck(@RequestBody MemberDTO member) {
        log.info("member : {} ", member);

        String result = "사용 가능한 이메일입니다.";
        if (!(memberService.findByMemberEmail(member.getMemberEmail()).isEmpty())) {
            result = "중복 된 이메일이 존재합니다.";
        }

        return ResponseEntity.ok(result);
    }

    /* 로그인 실행 시 에러 발생 */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/member/login";
    }

    /* 아이디 찾기 */
    @PostMapping("/findId")
    public String yourId(MemberDTO member, Model model) {
        model.addAttribute("yourId", memberService.findMyId(member));
        return "member/yourId";
    }

    /* 비밀번호 찾기 실행 전 이메일 체크 */
    @PostMapping(value = "/mailDupCheck", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> doubleCheckMail(@RequestBody MemberDTO member) {
        String result = "입력하신 정보가 일치하지 않습니다.";
        if (memberService.selectMemberByEmail(member)) {
            result = "입력하신 정보가 일치합니다. 임시 비밀번호를 전송하고, 로그인 화면으로 돌아갑니다.";
        }

        return ResponseEntity.ok(result);
    }

    /* 비밀번호 찾기 이메일 보내기 */
    @PostMapping("/send")
    public String sendMail(@RequestParam(required = false) String memberEmail) {
        MailDTO mail = mailService.createMailAndChangePassword(memberEmail);
        mailService.mailSend(mail);
        return "redirect:/main";
    }

    /* BeforeUpdate 내에서 비밀번호 확인 */
    @PostMapping(value = "/pwDupCheck", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> doubleCheckPWD(@RequestBody MemberDTO member) {

        String memberPwd = memberService.memberPwCheck(member.getMemberId());
        String result = "비밀번호가 일치하지 않습니다.";
        if (PE.matches(member.getMemberPwd(), memberPwd)) {
            result = "비밀번호가 일치합니다 !";
        }
        return ResponseEntity.ok(result);
    }

    /* 회원정보 수정 요청 발생 시 필요정보 전달 */
    @GetMapping("/mypage")
    public String Mypage(@AuthenticationPrincipal MemberDTO loginMember, Model model) {

        if (loginMember.getMemberPhone() != null) {
            String[] memberAddress = loginMember.getMemberAddress().split("\\$");
            model.addAttribute("memberAddress", memberAddress);
        }

        return "/member/mypage";
    }

    /* update 요청 발생 시 필요 정보 전달 */
    @RequestMapping("/update")
    public String update(@AuthenticationPrincipal MemberDTO loginMember, Model model) {

        String[] memberAddress = loginMember.getMemberAddress().split("\\$");

        model.addAttribute("memberAddress", memberAddress);

        return "member/update";
    }

    /* 정보 수정 시 필요 정보 전달 */
    @PostMapping("/updateComplete")
    public String updateComplete(@ModelAttribute MemberDTO updateMember,
                                 @RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
                                 @AuthenticationPrincipal MemberDTO loginMember,
                                 RedirectAttributes rttr) {
        String address = zipCode + "$" + address1 + "$" + address2;
        updateMember.setMemberAddress(address);
        updateMember.setMemberNo(loginMember.getMemberNo());
        updateMember.setMemberPwd(PE.encode(updateMember.getPassword()));

        int result = memberService.modifyMember(updateMember);
        if (result > 0) {
            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginMember.getMemberId()));
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));
        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("modify.fail"));
        }

        return "redirect:/main";
    }

    /* 정보 수정 완료 후 인증객체 정보 수정 */
    protected Authentication createNewAuthentication(String memberId) {

        UserDetails newPrincipal = AS.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }

    /* 회원 탈퇴 */
    @PostMapping("/delete")
    public String deleteMember(@AuthenticationPrincipal MemberDTO member,
                               @RequestParam String memberLeaveReason,
                               RedirectAttributes rttr) {

        member.setMemberLeaveReason(memberLeaveReason);
        int result = memberService.removeMember(member);
        SecurityContextHolder.clearContext();

        if (result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.delete"));
        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("delete.fail"));
        }

        return "redirect:/main";
    }

    @RequestMapping("/success")
    public String success(@AuthenticationPrincipal MemberDTO member, Model model,RedirectAttributes rttr) {
        if (member.getMemberGrade().equals("USER")) {
            if (member.getMemberPwd() != null) {
                return "redirect:/main";
            } else {
                model.addAttribute("message", messageSourceAccessor.getMessage("member.regist2"));
                return "/member/regist2";
            }
        } else {
            rttr.addFlashAttribute("message", member.getMemberName() + " 님 환영합니다.");
                return "redirect:/admin/mainpage";
        }
    }

    @GetMapping("/regist1")
    public void regist1() {
    }

    @GetMapping("/regist2")
    public void regist2() {
    }

    @GetMapping("/findId")
    public void findId() {
    }

    @GetMapping("/findPWD")
    public void findPWD() {
    }

    @RequestMapping("/BeforeUpdate")
    public void BeforeUpdate() {
    }

    @GetMapping("/DeleteMember")
    public void DeleteMember() {
    }
}
