package com.greedy.matcat.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.matcat.common.paging.Pagenation;
import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.main.dao.ProductMapper;

import com.greedy.matcat.main.dto.FilesDTO;
import com.greedy.matcat.main.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;

	public ProductServiceImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	@Override
	public Map<String, Object> defaultSearchProduct(Map<String, String> searchMap, int page, int arrayCode, int categoryCode) {
		int totalCount = 0;
		int pageLimit = 8;
		int buttonAmount = 10;
		Map<String,Object> search = new HashMap<>();
		String searchValue = searchMap.get("searchValue");
		search.put("searchValue", searchValue);
		search.put("categoryCode", categoryCode);

		if (searchValue != null || categoryCode >= 10) {
			totalCount = productMapper.selectProductTotalCount2(search);
		} else {
			totalCount = productMapper.selectProductTotalCount();
		}

		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, pageLimit, buttonAmount, searchMap);
		List<ProductDTO> productList = productMapper.mainProduct(selectCriteria,arrayCode, categoryCode);

		Map<String, Object> productListAndPaging = new HashMap<>();
		productListAndPaging.put("paging", selectCriteria);
		productListAndPaging.put("productList", productList);

		return productListAndPaging;
	}

	@Override
	public List<ProductDTO> findMainNewProduct() {
		return productMapper.findMainNewProduct();
	}
	@Override
	public List<ProductDTO> semiProduct(int no) {
		return productMapper.semiProduct(no);
	}



	//관리자
	/* 등록된 상품 리스트 조회*/
	@Override
	public Map<String, Object> productManageList(int page) {

		int totalCount = productMapper.selectProductTotalCount();
		log.info("[ProductService] totalCount : {}", totalCount);
		
		int limit = 10;

		int buttonAmount = 5;
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);
		log.info("[ProductService] selectCriteria : {}", selectCriteria);

		List<ProductDTO> productList = productMapper.productManageList(selectCriteria);
		log.info("[ProductService] productList : {}", productList);
		
		log.info("[ProductService] thumbnailList : {}", productList);

		Map<String, Object> productListAndPaging = new HashMap<>();
		productListAndPaging.put("paging", selectCriteria);
		productListAndPaging.put("productList", productList);

		return productListAndPaging;
	}
	/* 상품 등록 */
	@Override
	public void productRegistImg(ProductDTO product) {
		log.info(" abc : {} " , product);
		int result = productMapper.insertProduct(product);// TBL_Product 테이블에 데이터 저장
		log.info("result : {} ", result);

		for(FilesDTO files : product.getFilesList()) {	//TBL_FILES 테이블에 첨부 이미지 저장
			productMapper.insertPrdImgs(files);
		}
	}
	@Override
	public List<ProductDTO> findGoodProduct() {
		return productMapper.findGoodProduct();
	}


}