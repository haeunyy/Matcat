package com.greedy.matcat.member.service;

import com.greedy.matcat.member.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSenderImpl mailSender;
    private final MemberService memberService;

    public MailServiceImpl(JavaMailSenderImpl mailSender,
                           MemberService memberService, PasswordEncoder pe) {
        this.mailSender = mailSender;
        this.memberService = memberService;
    }


    @Override
    public MailDTO createMailAndChangePassword(String memberEmail) {
        String pass = getTempPassword();
        MailDTO mail = new MailDTO();
        mail.setAddress(memberEmail);
        mail.setTitle("임시비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + pass + "입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePassword(pass,memberEmail);
        return mail;
    }

    @Override
    public void mailSend(MailDTO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setFrom("dlwnstjr0310@naver.com");
        message.setReplyTo("dlwnstjr0310@naver.com");
        log.info("message : {} ", message);
        log.info("mailSender : {} ", mailSender);
        mailSender.send(message);
    }

    @Override
    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    @Override
    public void updatePassword(String pass, String email) {
        String memberId = memberService.findByMemberEmail(email);
        memberService.updatePassword(memberId,pass);
    }
}
