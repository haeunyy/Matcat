package com.greedy.matcat.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.matcat.main.dto.CartDTO;

@Mapper
public interface CartMapper {

	/* 카트에 상품 추가 */
	String addCart(CartDTO cart);


	// 카트 안에 상품이 이미 존재하는지 확인함
	CartDTO checkCart(CartDTO cart);

	/* 장바구니페이지로 이동 */
	List<CartDTO> goCart(int memberNo);
	
//	// 카트에 상품 이미지 불러오기
//	FilesDTO getPrdImg(String prdCode);

	
//	// 카트 안에 상품이 이미 존재하면 수량을 
//	String plusCartQuan(CartDTO cart);
//	
//	// 장바구니에 있는 전체 상품 조회
//	List<CartDTO> goCartList(int memberNo, String iMAGE_DIR);
//
//	// 장바구니에 상품 추가
//	CartDTO addCart(String code, String cartNo);
//
//	// 장바구니에 상품 하나 담기
//	void saveOneProduct(CartDTO cart);
//
//	// 장바구니에 추가한 상품이 기존에 담겨있는 상품이라면 1개를 추가함
//	CartDTO findByMemberNoAndPrdCode(String cartNo, String productCode);
//
//	// 장바구니 상품 수량 증가
//	int updateQuantPlus(CartDTO cart);
//
//	int updateQuantMinus(CartDTO cart);
//
//	String deleteOne(String cartNo, String productCode);
//
//	String deleteAll(String cartNo);



}
