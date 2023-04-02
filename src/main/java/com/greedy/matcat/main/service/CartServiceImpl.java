package com.greedy.matcat.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.matcat.main.dao.CartMapper;
import com.greedy.matcat.main.dto.CartDTO;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
	
//	// 카트에서 주문으로 넘기기
//	@Override
//	public Map<String, Object> orderCartInsert(String cartNo, int memNo){
//		
//		return null;
//	}
	
	
//	@Value("${image.image-dir}")
//	private String IMAGE_DIR;
//	
//
	@Autowired
	private final CartMapper cartMapper;
	
	public CartServiceImpl(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	// 카트에 상품 추가하기
	@Override
	public String addCart(CartDTO cart) {
		// 장바구니 데이터 체크(장바구니에 동일한 상품이 존재하는 경우 메인 페이지로?
		CartDTO checkCart = cartMapper.checkCart(cart);
		if(checkCart != null) {
//			ProductDTO product;
//			int quant = product.getQuan();
//			if( quant <= checkCart.getQuantity()) {
//				return "/localhost:8001/main";
//			} else {
//				return cartMapper.plusCartQuan(cart);
//				
//			}
			return "/localhost:8001/main";
		}
		return cartMapper.addCart(cart);
	}
	
	/* 장바구니페이지로 이동 */
	@Override
	public List<CartDTO> goCartList(int memberNo){
		
		List<CartDTO> cartList = cartMapper.goCart(memberNo);
		
		
		return cartList;
	}
	
	
//	
//	// 장바구니에 상품 담기	
//	public void addCart(CartDTO cart) {
//		cartService.addCart(cart);
//	}
//	
//	
	
//	// 장바구니 목록 확인
//	@Override
//	public List<CartDTO> goCartList(int memberNo, String IMAGE_DIR){
//		return cartMapper.goCartList(memberNo, IMAGE_DIR);
//	}
//	
//	// 장바구니에 상품 담기
//	@Override
//	public Boolean addCart(String cartNo, int quantity, String productCode) {
//		CartDTO existProduct = cartMapper.addCart(cartNo, productCode);
//		if(existProduct==null) {	// 장바구니에 상품이 존재하지 않을 경우에는 상품 추가
//			ProductDTO product = productMapper.findByCode(productCode);
//			int cartPrice = product.getPrice()*quantity;	//상품가격*수량
//			CartDTO cart = new CartDTO();
//			cart.getCartNo();
//			cart.getMemberNo();
//			cart.getProductCode();
//			cart.setQuantity(quantity);
//			cart.setCartPrice(cartPrice);
//			cartMapper.saveOneProduct(cart);
//			return true;
//		} else {	// 장바구니에 상품이 이미 있으면 수량을 추가함
//			cartService.plusQuant(cartNo, productCode);
//			return true;
//		}
//	}
//	
//	// 상품 개수 증가
//	@Override
//	public int plusQuant(String cartNo, String productCode) {
//		ProductDTO product = productMapper.findByPrdCode(productCode);
//		CartDTO cart = cartMapper.findByMemberNoAndPrdCode(cartNo, productCode);
//		if(cart.getQuantity() +1 > product.getQuan()) {
//			return -1;
//		}
//		cartMapper.updateQuantPlus(cart);
//		return cart.getQuantity()+1;
//		
//	}
//	
//	// 상품 개수 감소
//	@Override
//	public int minusQuant(String cartNo, String productCode) {
//		CartDTO cart = cartMapper.findByMemberNoAndPrdCode(cartNo, productCode);
//		if(cart.getQuantity() <= 1) {
//			return -1;
//		}
//		cartMapper.updateQuantMinus(cart);
//		return cart.getQuantity()-1;
//		
//	}
//	
//	// 카트 상품 하나 삭제
//	@Override
//	public String deleteOne(String cartNo, String productCode) {
//		return cartMapper.deleteOne(cartNo, productCode);
//	}
//	
	
}
