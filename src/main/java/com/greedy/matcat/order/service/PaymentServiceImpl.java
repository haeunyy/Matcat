package com.greedy.matcat.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greedy.matcat.order.dao.PaymentMapper;
import com.greedy.matcat.order.dto.PaymentDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService{
	
	private PaymentMapper payMapper;
	public PaymentServiceImpl(PaymentMapper payMapper) {
		this.payMapper = payMapper;
	}
	
	public int insertPay(PaymentDTO pay) {
		

		
		return 0;
	}

	
	
}
