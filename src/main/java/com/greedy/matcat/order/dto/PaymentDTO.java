package com.greedy.matcat.order.dto;

import lombok.Data;

import java.sql.Date;



@Data
public class PaymentDTO {
	
	private int payCode;
	private Date payDate;
	private int payAmount;
	private char payStatus;
	private int ordCode;
	private String payType;
	private String kakaoId;
	private String cardName;
	private String cardAccCode;

}
