package com.greedy.matcat.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.service.CartService;
import com.greedy.matcat.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main/cart")
public class CartController {
	
	
	@Autowired
    private final CartService cartService;
    
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping("/list")
    public String goCart() {
    	return "/main/ehyun/cart";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public  String addcartPOST(CartDTO cart, @AuthenticationPrincipal MemberDTO member) {
    	// 로그인 체크
    	log.info("[CartController] member : {}", member);
    	
    	if( member == null) {
    		return "redirect:/member/login";	
    	} else {
    		// 회원번호를 카트 테이블로??
    		cart.setMemberNo(member.getMemberNo());
    		log.info("[CartController] member : {}", member);
    		
    		// 카트 등록
    		return cartService.addCart(cart);
    	}
    	
    	
    }
    
	/* 장바구니페이지로 이동 */
    @GetMapping("/{memberNo}")
    public String cartPageGET(@PathVariable("memberNo") int memberNo, Model model) {
    	
    	model.addAttribute("cartInfo", cartService.goCartList(memberNo));
    	
    	return "main/ehyun/cart";
    }
    
//	/* 장바구니 삭제? */
//    @PostMapping("/cart/delete")
//    public String deleteCartPOST(CartDTO cart) {
////    	cartService;
//    	return null;
//    }
    
    
    
/* 카트에서 주문하기 */
//    @PostMapping("http://localhost:8001/order/insert")
//    public int orderCartInsert( @RequestParam(value="cartNo", required=false) String cartNo, Model model,
//    							@AuthenticationPrincipal MemberDTO member) {
//    	
//    	log.info("[CartController] cartNo : {} ", cartNo );
//    	
//    	int memNo = member.getMemberNo();
//    	Map<String, Object> ordCartMap = cartService.orderCartInsert(cartNo, memNo);
//    	log.info("[CartController] ordCartMap : {} ", ordCartMap);
//    	
//    	
//    	model.addAttribute("cartNo", ordCartMap.get(cartNo));
// 
//    	
//    	return 1;
//    }
//    
//    
//    @PostMapping("/list")
//    public String cartOrder(@AuthenticationPrincipal MemberDTO member, OrderDTO order, OrderDetailDTO orderDetail) throws Exception {
//    	
//    	log.info("[CartController] order : {} ", order);
//    	
//    	member.getAttribute("memberNo");
//    	int memNo = member.getMemberNo();
//    	
//    	order.setOrdCode(memNo);
//    	return null;
//    }
/* 카트에서 주문하기 끝 */    
    
    
    
//    cart.setMemberNo(member.getMemberNo());
//    log.info("[CartController] product request: {} ", product);
//    log.info("[CartController] order request: {} ", order);
    
//	/* 장바구니페이지 보기 */
//    @RequestMapping("/cart")
//    public String goCartList(CartDTO cart, FilesDTO files, Model model) {
//    	List<CartDTO> cartList = cartService.goCartList(cart.getMemberNo());
//    	model.addAttribute("cartList", cartList);
//    	return "main/ehyun/cart";
//    }
//    
    
//    // 카트에 상품 담기
//    @ResponseBody
//    @RequestMapping(value = "/cart/addCart", method = RequestMethod.POST)
//    public void addCart(CartDTO cart, HttpSession session) throws Exception {
//       
//       MemberDTO member = (MemberDTO)session.getAttribute("member");
//       cart.setMemberNo(member.getMemberNo());
//
//       cartService.addCart(cart);
//       
//    }
//    
    // 카트에 담긴 상품 주문으로 넘기기
    
    
/////     
    /* 장바구니에 상품 담기 이미 담긴 상품일 경우 개수를 증가시킴 */
//    @PostMapping("/add")
//    public String addCart(ProductDTO product, CartDTO cart, RedirectAttributes rttr) {
//    	
//    	log.info("[CartController] cart request: {} ", cart);
//    	log.info("[CartController] product request: {} ", product);
//    	
//    	Boolean result = cartService.addCart(cart.getCartNo(), cart.getQuantity(), product.getCode());
//    	if(result == false) {
//    		return "redirect:/main/상품상세페이지";
//    	} else {
//    		return "redirect:/main/cart/list";
//    	}
//    }
    
//    // 장바구니 수량 증가
//    @PatchMapping("/plustProduct/{productCode]")
//    public ResponseEntity<Integer> plusCartProduct(@PathVariable String cartNo, String productCode, CartDTO cart){
//    	int productQuant = cartService.plusQuant(cart.getCartNo(), productCode);
//    	if( productQuant <= 0 ) {
//    		return ResponseEntity.status(HttpStatus.CONFLICT).body(productQuant);
//    	}
//    	return ResponseEntity.ok(productQuant);
//    }
//    
//    // 장바구니 수량 감소
//    @PatchMapping("/minustProduct/{productCode]")
//    public ResponseEntity<Integer> minusCartProduct(@PathVariable String cartNo, String productCode, CartDTO cart){
//    	int productQuant = cartService.minusQuant(cart.getCartNo(), productCode);
//    	if( productQuant <= 0 ) {
//    		return ResponseEntity.status(HttpStatus.CONFLICT).body(productQuant);
//    	}
//    	return ResponseEntity.ok(productQuant);
//    }
//    
//    // 장바구니 상품 하나 삭제
//    @PostMapping("/deleteOne")
//	public String deleteCartProduct(String cartNo, String productCode) {
//		cartService.deleteOne(cartNo, productCode);
//		return "redirect:/main/cart/list";
//	}
    

    	  
	
}
