package com.greedy.matcat.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.board.service.BoardService;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.user.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value="/user")
public class UserController {
	
	private final UserService userService;
	private final MessageSourceAccessor messageSourceAccessor;
	private final BoardService boardService;
	
	public UserController(UserService userService, MessageSourceAccessor messageSourceAccessor, BoardService boardService) {
		this.userService = userService;
		this.messageSourceAccessor = messageSourceAccessor;
		this.boardService = boardService;
	}
	
	@GetMapping(value="/userHelp001")
	public String userWrite2(@RequestParam(required = false) Long selectType, Model model, BoardDTO board,
			@AuthenticationPrincipal MemberDTO member, @RequestParam(required = false) Long postCode) {
		
		model.addAttribute("BoardDTO", boardService.selectBoardDetail(postCode));
		
		log.info("UserSelectType : {} " , selectType);
		log.info("UserMember : {}" , member);
		log.info("UserPostCode : {}", postCode);
		
		return "/user/userHelp001";
		
	}
	
	@PostMapping(value="/userHelp001")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String userWrite(@ModelAttribute BoardDTO board, @AuthenticationPrincipal MemberDTO member,
			@RequestParam(required = false) Long selectType){
		board.setWriter(member); // 게시글 작성자 정보
		log.info("[BoardController] boardUpdate : {}", board);

		userService.insertBoard(board);
	
		return "redirect:/board/userBoardList";
				
	}


}
