package com.greedy.matcat.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.main.service.ProductService;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberService;
import com.greedy.matcat.order.dto.OrderDTO;
import com.greedy.matcat.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, MemberService memberService, ProductService productService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.productService = productService;
    }

    
    /* 메인에서 결제 이동시 주문 정보 생성 */
    @ResponseBody
    @PostMapping("/insert")
    public ResponseEntity<String> orderInsert(@AuthenticationPrincipal MemberDTO member,@RequestBody CartDTO ct) {
	    
    	log.info("[OrderController] member : {}",member);
        log.info("[OrderController] cart : {} ", ct);
        
        // cartcode 들로 
        CartDTO cart = new CartDTO();
        cart.setMemberNo(member.getMemberNo());
        cart.setProductCode(ct.getProductCode());
        int result;
        for (int i = 0; i > 0; i++) {
        	
        }
        
    	return ResponseEntity.ok("order 등록완료");
    }
    
    
    /* 결제 전 주문서 */
    @GetMapping("/form")
    public String orderForm(@RequestParam("prdCode") int prdCode, 
    			@RequestParam("amount") Long count,
    			@RequestParam("sell_price") Long ordPrice,
    			@AuthenticationPrincipal MemberDTO loginMember, Model model) {
    	log.info("[OrderController] loginMember : {}", loginMember);

    	String[] memberAddress = loginMember.getMemberAddress().split("\\$");
        model.addAttribute("memberAddress", memberAddress);

        List<ProductDTO> list = productService.semiProduct(prdCode);
        log.info("list : {} ", list);
        model.addAttribute("list", list);
        
        model.addAttribute("count", count);
//        model.addAttribute("ordPrice",ordPrice);
        
        /* 배송비 설정 */
        int del = 0 ;
        if(ordPrice > 50000) del = 3000;
        
        model.addAttribute("del",del);
        
        return "order/orderForm";
    }
    
    
    /* 결제 후 주문 내역 */
    @GetMapping("/completion")
    public String orderCompletion(@AuthenticationPrincipal MemberDTO member,Model model) {
    	log.info("[OrderController] order: {}",member);
    	
    	OrderDTO order = orderService.ordComplete(member.getMemberNo());
        String[] memberAddress = member.getMemberAddress().split("\\$");

    	model.addAttribute("orderList",order);
        model.addAttribute("memberAddress", memberAddress);
        log.info("[OrderController] list : {} ", order);

        return "order/orderCompletion";
    }
    

    
    
    /* 회원 마이페이지 주문 조회리스트 */
    @GetMapping("/myOrdList")
    public String ordList(@AuthenticationPrincipal MemberDTO member, Model model,
                          @RequestParam(defaultValue = "1") int page){

        int memberNo = member.getMemberNo();

        Map<String,Object> pagingAndOrderList = memberService.memberDetail(memberNo,page);

        model.addAttribute("paging", pagingAndOrderList.get("paging"));
        model.addAttribute("orderList", pagingAndOrderList.get("orderList"));

        return "/order/myOrdList";
    }

    
    /* 회원 마이페이지 주문조회 상세 페이지 */
    @GetMapping("/ordDetail")
    public String ordDetail(@AuthenticationPrincipal MemberDTO member,
			@RequestParam(defaultValue="1") int page,
			@RequestParam int ordCode, Model model) {

        List<OrderDTO> orderList = orderService.myOrderDetail(ordCode);
        log.info("orderList : {} ", orderList);
        String[] memberAddress = member.getMemberAddress().split("\\$");
        model.addAttribute("memberAddress", memberAddress);
        model.addAttribute("orderList", orderList);
        model.addAttribute("orderCode", ordCode);

        return "/order/myOrdDetail";
    }
	
    
    /* 관리자 주문 조회 */
    @GetMapping("/admin/ordList")
	public String adminOrdList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[orderController] page : {}", page);

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
	
		log.info("[OrderController] searchMap : {}", searchMap);
		
		Map<String, Object> orderListAndPaging = orderService.selectOrderList(searchMap, page);
		model.addAttribute("paging", orderListAndPaging.get("paging"));
		model.addAttribute("orderList", orderListAndPaging.get("orderList"));
		
		log.info("[OrderController] : {}", model);
		
		return "order/admin/ordList";
    }

    /* 관리자 주문조회 상세페이지 */
    @GetMapping("/admin/ordDetail")
    public String adminOrdDetail(@RequestParam("no") int ordCode, Model model) {
    	log.info("[OrderController] ordCode : {}", ordCode);

    	OrderDTO detail = orderService.selectAdminOrderDetail(ordCode);
    	log.info("[OrderController] detail : {}", detail);
    	model.addAttribute("detail", detail);
    	
    	List<OrderDTO> customList = orderService.adminOrdCustomerSelect(ordCode);
    	log.info("[OrderController] customList : {}", customList);
    	model.addAttribute("customList", customList);
    	
        return "order/admin/ordDetail";
    }
    
    
    /* 주문서 개인정보 동의 상세 페이지 */
    @GetMapping("/policy2")
    public String policy2() {
    	return "order/policy2";
    }

    @GetMapping("/admin/refDetail")
    public String adminRefDetail() {
        return "order/admin/refDetail";
    }

    @GetMapping("/admin/refList")
    public String adminRefList() {
        return "order/admin/refList";
    }
    
    @GetMapping("/myRefCompletion")
    public String myRefCompletion() {
        return "order/myRefCompletion";
    }
    

}

