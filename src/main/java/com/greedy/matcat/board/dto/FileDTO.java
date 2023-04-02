package com.greedy.matcat.board.dto;


import java.sql.Date;

import lombok.Data;

@Data
public class FileDTO {

	private int imgCode;
	private String savePath;
	private int size;
	private Date uploadDate;
	private String originalName;
	private String savedName;
	private String fileType;
	private String titleImgYn;
}
