package com.greedy.matcat.board.dto;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.greedy.matcat.member.dto.MemberDTO;

import lombok.Data;

@Data
public class BoardDTO {

	private Long postCode;
	private String postTitle;
	private String postContent;
	@DateTimeFormat(pattern = "yy-MM-dd")
	private String reportDate;
	@DateTimeFormat(pattern = "yy-MM-dd")
	private String modyDate;
	@DateTimeFormat(pattern = "yy-MM-dd")
	private String deleteDate;
	private MemberDTO writer;
	private List<ReplyDTO> replyList;
	private String selectType;
	
	
}
