package com.greedy.matcat.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.main.dto.FilesDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

	/* search 페이지 조회 */
	List<ProductDTO> mainProduct(@Param("criteria") SelectCriteria selectCriteria
			, @Param("arrayCode") int arrayCode,@Param("categoryCode") int categoryCode);
	/* 효자상품 */
	List<ProductDTO> findGoodProduct();
	/* 검색 카운트 */
	int selectProductTotalCount2(Map<String, Object> searchValue);
	
	/* 신메뉴 조회 */
	List<ProductDTO> findMainNewProduct();
	
	/* 상세 조회 */
	List<ProductDTO> semiProduct(int no);

	/* 신메뉴 조회 메인페이지용 */
	List<ProductDTO> newProduct();

	//관리자	
	// 상품 등록
	int insertProduct(ProductDTO product);

	// 상품 이미지 등록
	int insertPrdImgs(FilesDTO files);

	// 등록된 상품 총 수량
	int selectProductTotalCount();

	//
	List<ProductDTO> productManageList(SelectCriteria selectCriteria);
}