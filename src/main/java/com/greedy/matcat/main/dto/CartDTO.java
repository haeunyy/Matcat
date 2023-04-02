package com.greedy.matcat.main.dto;


import com.greedy.matcat.member.dto.MemberDTO;
import lombok.Data;

@Data
public class CartDTO {

	private String cartNo;
	private int memberNo;
	private MemberDTO member;
	private String productCode;
	private ProductDTO products;
	private FilesDTO files;			// 상품 썸네일 불러오기
	private int quantity;
	private int cartPrice;			// 상품금액 * 수량
	
	
//	private List<CartDTO> cartList;
	public void initSaleTotal() {
		this.cartPrice = products.getPrice()*this.quantity;
	}
	
	/*
	 * CART_NO 
	 * MEMBER_NO 
	 * PRD_CODE 
	 * QUANTITY 
	 * CART_PRICE
	 */
	
	
}
