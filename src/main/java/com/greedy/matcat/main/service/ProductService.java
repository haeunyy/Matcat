package com.greedy.matcat.main.service;


import com.greedy.matcat.main.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {


    /* 전체메뉴 조회 */
    Map<String,Object> defaultSearchProduct(Map<String, String> searchMap, int page, int arrayCode, int categoryCode);

    /* 신메뉴 조회 */
	List<ProductDTO> findMainNewProduct();
	
	/* 상세 조회 */
	List<ProductDTO> semiProduct(int no);

    // 등록된 상품 목록 조회
	Map<String, Object> productManageList(int page);

    /* 상품 등록 서비스 */
    void productRegistImg(ProductDTO product);
    List<ProductDTO> findGoodProduct();
}