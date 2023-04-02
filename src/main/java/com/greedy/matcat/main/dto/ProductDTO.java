package com.greedy.matcat.main.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;



@Data
public class ProductDTO {
	
	private String code;	// 변수타입 int >> string
	private String name;
	private int price;
	private int quan;
	private String categoryCode;
	private String status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date modiDate;
	private List<FilesDTO> filesList;	// 상품 이미지 업로드 때문에 추가했어요 -이현
	private FilesDTO file; 
	
}
