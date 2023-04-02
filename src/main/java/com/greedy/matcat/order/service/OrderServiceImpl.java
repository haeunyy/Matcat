package com.greedy.matcat.order.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.matcat.common.paging.Pagenation;
import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.main.dao.ProductMapper;
import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.order.dao.OrderMapper;
import com.greedy.matcat.order.dto.OrderDTO;
import com.greedy.matcat.order.dto.PaymentDTO;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	private final OrderMapper orderMapper;

	public OrderServiceImpl(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	/* 관리자 주문내역 검색 및 리스트 조회 */
	@Override
	public Map<String, Object> selectOrderList(Map<String, String> searchMap, int page) {
		/* 전체 게시글 수 확인 */
		int totalCount = orderMapper.selectTotalCount(searchMap);
		log.info("[OrderService] totalCount : {}", totalCount);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[OrderService] selectCriteria : {}", selectCriteria);
		
		/* 검색조건에 맞는 데이터 담기 */
		List<OrderDTO> orderList = orderMapper.selectOrderList(selectCriteria);
		log.info("[OrderService] OrderList : {}", orderList);
		
		Map<String, Object> orderListAndPaging = new HashMap<>();
		orderListAndPaging.put("paging", selectCriteria);
		orderListAndPaging.put("orderList", orderList);
		
		log.info("[OrderService] selectCriteria : {}", selectCriteria);

		return orderListAndPaging;
	}

	/* 관리자 주문내역 상세 */
	@Override
	public OrderDTO selectAdminOrderDetail(int ordCode) {
		return orderMapper.selectAdminOrderDetail(ordCode);
	}

	@Override
	public List<OrderDTO> adminOrdCustomerSelect(int ordCode) {
		return orderMapper.adminOrdCustomerSelect(ordCode);
	}


	@Override
	public List<ProductDTO> ordInfo(int prdCode){
		return orderMapper.ordInfo(prdCode);
	}


	@Override
	public List<OrderDTO> myOrderDetail(int ordCode) {
		return orderMapper.myOrderDetail(ordCode);
	}


	@Override
	public List<CartDTO> getMyCart(int memberNo) {
		return orderMapper.getMyCart(memberNo);
	}

	@Override
	public int insertPay(PaymentDTO pay) {
		return orderMapper.insertPay(pay);
	}

	@Override
	public OrderDTO ordComplete(int memNo) {
		return orderMapper.ordComplete(memNo);
	}

	@Override
	public int insertOrder(int memberNo, int price) {
		return orderMapper.insertOrder(price, memberNo);
	}

	@Override
	public int insertOrderDetail(int productCode) {
		return orderMapper.insertOrderDetail(productCode);
	}


}

