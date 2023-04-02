package com.greedy.matcat.order.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.matcat.order.dto.ImpDTO;
import com.greedy.matcat.order.dto.PaymentDTO;
import com.greedy.matcat.order.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/verifyIamport")
public class VerifyCotroller {


    private final IamportClient api;
    private final OrderService orderservice;
    private final MemberService memberService;

    @Autowired
    public VerifyCotroller(OrderService orderService, MemberService memberService) {
        // 토큰 발급을 위해 키 생성
        // REST API 키,  REST API secret
        this.api = new IamportClient("0004881740526076", "2EVuTBZoqh2GI5MUvfbePvdr0fOCeBSjjBRaQxjC0dUuzag4uLlzSapvStFBq5JE8SUXgnk8uQ0fikUr");
        this.orderservice = orderService;
        this.memberService = memberService;
    }


    //paymentByImpUid 함수를 사용하기 위해 토큰이 필요함
    @ResponseBody
    @PostMapping("/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session
            , @PathVariable(value = "imp_uid") String imp_uid , @RequestBody ImpDTO imp) throws IamportResponseException, IOException {
        log.info("[PaymentController] imp_uid 진입 : {}", imp_uid);
        log.info("[PaymentController] imp : {}", imp);

        String impUid = imp.getImp_uid();
        String cardType = imp.getCard_type();
        String bizEmail = imp.getBiz_email();
        int price = imp.getAmount();
        int productCode = imp.getProCode();

        PaymentDTO pay = new PaymentDTO();
        pay.setCardAccCode(impUid);
        pay.setPayAmount(price);
        pay.setPayType(cardType);
        int memberNo = memberService.findMemberByEmail(bizEmail);
        int success = orderservice.insertOrder(memberNo, price);
        int success2 = orderservice.insertOrderDetail(productCode);
        int success3 = orderservice.insertPay(pay);
        if (success > 0 && success2 > 0 && success3 > 0) {
            log.info("order, orderDetail, pay 등록 완료");
        }

        //paymentByImpUid 함수는 아임포트서버에서 imp_uid(거래 고유번호)를 검사하여, 데이터를 보내줍니다.
        return api.paymentByImpUid(imp_uid);
    }


}