package com.greedy.matcat.main.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FilesDTO {
	
/*
FILE_IMG_CODE		VARCHAR2(10 BYTE)
FILE_PATH			VARCHAR2(1000 BYTE)
FILE_SIZE			NUMBER(20,0)
FILE_UPLOAD_DATE	DATE
FILE_ORIGIN_NAME	VARCHAR2(100 BYTE)
FILE_SAVED_NAME		VARCHAR2(100 BYTE)
FILE_DIV			VARCHAR2(20 BYTE)
INQ_CODE			NUMBER(10,0)
POST_CODE			VARCHAR2(20 BYTE)
REV_CODE			NUMBER(10,0)
PRD_CODE			VARCHAR2(10 BYTE)
THUMB_YN			VARCHAR2(3 BYTE)
THUMB_PATH			VARCHAR2(1000 BYTE)
*/
	private String fileCode;
	private String filePath;
	private int fileSize;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date uploadDate;
	private String originName;
	private String savedName;
	private String fileDiv;
	private int inqCode;
	private String postCode;
	private int revCode;
	private String prdCode;
	private String thumbYN;
	private String thumbPath;

}
