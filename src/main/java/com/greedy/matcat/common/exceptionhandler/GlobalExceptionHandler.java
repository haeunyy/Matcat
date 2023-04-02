package com.greedy.matcat.common.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String errorView(Exception e, Model model) {
        model.addAttribute("message", "권한이 없습니다.\\n[확인] 을 누르면 메인페이지, [취소] 를 누르면 로그인 페이지로 이동합니다.");
        return "/common/error";
    }

}

