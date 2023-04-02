package com.greedy.matcat.board.dto;

import com.greedy.matcat.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ReplyDTO {

	private int replyNo;
	private int postCode;
	private String replyContent;
	private String reportDate;
	private MemberDTO writer;
	private String modyDate;
	private String deleteDate;
	
}
