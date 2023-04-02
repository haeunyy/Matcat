package com.greedy.matcat.main.service;

import java.util.List;

import com.greedy.matcat.main.dto.CartDTO;

public interface CartService {
	
//	// 카트에서 주문으로 넘기기
//	Map<String, Object> orderCartInsert(String cartNo, int memNo);

	// 카트에 상품 추가하기 
	String addCart(CartDTO cart);

	/* 장바구니페이지로 이동 */
	List<CartDTO> goCartList(int memberNo);
	
	
	
	
	
	

	// 카트에 상품 추가
//	void addCart(CartDTO cart);
	
	
	
//	//카트 목록 확인
//	List<CartDTO> goCartList(int memberNo, String IMAGE_DIR);
//
//	// 카트에 상품 추가
//	Boolean addCart(String cartNo, int quantity, String productCode);
//	
//	// 카트에 담긴 상품 개수 증가
//	int plusQuant(String cartNo, String productCode);
//
//	// 카트에 담긴 상품 개수 감소
//	int minusQuant(String cartNo, String productCode);
//
//	// 카트에 담긴 상품 하나 삭제
//	String deleteOne(String cartNo, String productCode);

	
	

}
