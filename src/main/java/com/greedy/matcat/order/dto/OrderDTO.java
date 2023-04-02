package com.greedy.matcat.order.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.member.dto.MemberDTO;

import lombok.Data;

@Data
public class OrderDTO {
	
	private int ordCode;			// 주문번호
	private Date ordDate;			// 주문일자
	private int ordPrice;			// 주문금액
	private int memberNo;
	private MemberDTO member;		// 회원DTO조인시
	private int ordTotalCount;		// 주문 총 수량
	private int deliveryPrice;		// 배송비
	private OrderStatusDTO status; 	// 주문상태
	private int ordCount;			// 상품 별 수량
	private String statusName; 		// 주문상태 설명
	private OrderDetailDTO ordDetail; 	// 주문상세 조인시
	private ProductDTO product;			// 상품정보 조인시
	private PaymentDTO payment;			// 결제정보 조인시
	private List<ProductDTO> products;		// 상품정보 조인시 
	private List<OrderDetailDTO> ordDetails; // 주문상세 조인시


}