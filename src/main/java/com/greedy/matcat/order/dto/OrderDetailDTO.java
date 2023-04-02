package com.greedy.matcat.order.dto;

import com.greedy.matcat.main.dto.ProductDTO;

import lombok.Data;

@Data
public class OrderDetailDTO {

	private ProductDTO products; 	// 상품코드
	private int prdCode;
	private String ordCode;		// 주문코드
	private int ordCount;			// 주문수량
}
