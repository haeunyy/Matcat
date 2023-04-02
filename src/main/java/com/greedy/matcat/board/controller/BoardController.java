package com.greedy.matcat.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.board.dto.ReplyDTO;
import com.greedy.matcat.board.service.BoardService;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.user.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final UserService userService;
	private final BoardService boardService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public BoardController(BoardService boardService, MessageSourceAccessor messageSourceAccessor, UserService userService) {
		this.boardService = boardService;
		this.messageSourceAccessor = messageSourceAccessor;
		this.userService = userService;
	}
	
	@GetMapping("/boardList")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String boardList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue,
			Model model) {
		log.info("[BoardController] page : {}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		Map<String, Object> boardListAndPaging2 = boardService.selectBoardList2(searchMap, page);
		
		
		log.info("[BoardController] boardListAndPaging : {}", boardListAndPaging2);
		
		model.addAttribute("paging", boardListAndPaging2.get("paging"));
		
		log.info("[BoardController] boardListAndPaging.get(paging) : {}", boardListAndPaging2.get("paging"));
		
		model.addAttribute("boardList", boardListAndPaging2.get("boardList"));
		
		log.info("[BoardController] boardListAndPaging.get(boardList) : {}", boardListAndPaging2.get("boardList"));
		
		return "board/boardList";
	}
	
	@GetMapping("/boardDetail")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String selectBoardDetail(@RequestParam Long postCode, Model model, Long selsctType)  {
		if (postCode == null) {}
		BoardDTO boardDetail = boardService.selectBoardDetail(postCode);
		log.info("[BoardController] boardDetail : {}", boardDetail);
		
		List<ReplyDTO> boardReply = boardService.loadReply(postCode);
		log.info("[BoardController] boardDetail : {}", boardReply);
		
		model.addAttribute("board", boardDetail);
		model.addAttribute("reply" , boardReply);
		log.info("[BoardDetail] boardDetail2 : {}", boardDetail);
		log.info("[BoardController] boardDetail : {}", boardReply);
		
		return "board/boardDetail";
	}
	
	@GetMapping("/loadReply")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public ResponseEntity<List<ReplyDTO>> loadReply(Long postCode){
		
		log.info("[BoardController] loadReply : {}", postCode);
		
		List<ReplyDTO> replyList = boardService.loadReply(postCode);
		
		log.info("[BoardController] replyList : {}", replyList);
		
		return ResponseEntity.ok(replyList);
	}
	
	@PostMapping("/registReply")
	public ResponseEntity<String> registReply(@RequestBody ReplyDTO registReply,
			@AuthenticationPrincipal MemberDTO member){
		log.info("[BoardController] registReply : {}", member);
		registReply.setWriter(member);
		log.info("[BoardController] registReply : {}", registReply);
		
		boardService.registReply(registReply);
		
		return ResponseEntity.ok("댓글 등록 완료");
	}
	
	@PostMapping("/removeReply")
	public ResponseEntity<String> removeRepely(@RequestBody ReplyDTO removeReply){
		
		log.info("[BoardController] removerReply : {}", removeReply);
		
		boardService.removeReply(removeReply);
		
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	
	@GetMapping("/loadReplyList")
	public ResponseEntity<List<ReplyDTO>> loadReplyList(ReplyDTO loadReplyList){
		
		log.info("[BoardController] loadReplyLst : {}", loadReplyList);
		
		List<ReplyDTO> replyList2 = boardService.loadReplyList(loadReplyList);
		
		log.info("[BoardController] replyList2 : {}", replyList2);
		
		return ResponseEntity.ok(replyList2);
	}
	
	@PostMapping("/removePost")
	public ResponseEntity<String> removePost(@RequestBody BoardDTO removePost){
		
		log.info("[BoardController] removerPost : {}", removePost);
		
		boardService.removePost(removePost);
		
		return ResponseEntity.ok("게시글 삭제 완료");
	}
	
	@GetMapping(value="/boardUpdate")
	public String boardUpdate(@RequestParam(required = false) Long selectType, Model model,
			@AuthenticationPrincipal MemberDTO member, @RequestParam(required = false) Long postCode) {
		
		model.addAttribute("BoardDTO", boardService.selectBoardDetail(postCode));
		
		log.info("UpselectType : {} " , selectType);
		log.info("Upmember : {}" , member);
		log.info("UppostCode : {}", postCode);
		
		return "/board/boardUpdate";
		
	}
	
	@PostMapping(value="/boardUpdate")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String updatePost(@ModelAttribute BoardDTO board, @AuthenticationPrincipal MemberDTO member,
			@RequestParam(required = false) Long selectType){
		board.setWriter(member); // 게시글 작성자 정보
		log.info("[BoardController] boardUpdate : {}", board);

		boardService.updatePost(board);
	
		return "redirect:/board/boardList";
				
	}
	
	@GetMapping(value="/adminWrite")
	public String adminWrite2(@RequestParam(required = false) Long selectType, Model model, BoardDTO board,
			@AuthenticationPrincipal MemberDTO member, @RequestParam(required = false) Long postCode) {
		
		model.addAttribute("BoardDTO", boardService.selectBoardDetail(postCode));
		
		log.info("UpselectType : {} " , selectType);
		log.info("Upmember : {}" , member);
		log.info("UppostCode : {}", postCode);
		
		return "/board/adminWrite";
		
	}
	
	@PostMapping(value="/adminWrite")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String adminWrite(@ModelAttribute BoardDTO board, @AuthenticationPrincipal MemberDTO member,
			@RequestParam(required = false) Long selectType){
		board.setWriter(member); // 게시글 작성자 정보
		log.info("[BoardController] boardUpdate : {}", board);

		userService.insertBoard(board);
	
		return "redirect:/board/boardList";
				
	}

	@GetMapping("alwaysHelp")
	public void alwaysHelp() {}
	
	@GetMapping("/userBoardList")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String userBoardList(@RequestParam(defaultValue="1") int page, 
	@RequestParam(required=false) String searchCondition, 
	@RequestParam(required=false) String searchValue,
	@AuthenticationPrincipal MemberDTO member,
	Model model) {

log.info("[BoardController] page : {}", page);

Map<String, String> searchMap = new HashMap<>();
searchMap.put("searchCondition", searchCondition);
searchMap.put("searchValue", searchValue);

log.info("[BoardController] searchMap : {}", searchMap);

Map<String, Object> boardListAndPaging = boardService.selectBoardList(searchMap, page, member.getMemberNo());

log.info("[UserBoardController] UserBoardListAndPaging : {}", boardListAndPaging);

model.addAttribute("paging", boardListAndPaging.get("paging"));

log.info("[UserBoardController] UserBoardListAndPaging.get(paging) : {}", boardListAndPaging.get("paging"));

model.addAttribute("boardList", boardListAndPaging.get("boardList"));

log.info("[UserBoardController] UserBoardListAndPaging.get(boardList) : {}", boardListAndPaging.get("boardList"));

return "board/userBoardList";
}
	
	@GetMapping("/adminBoardList")
	@DateTimeFormat(pattern="yyMMddHHmmss")
	public String adminBoardList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue,
			Model model) {
		log.info("[BoardController] page : {}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		Map<String, Object> boardListAndPaging3 = boardService.selectBoardList3(searchMap, page);
		
		
		log.info("[BoardController] boardListAndPaging3 : {}", boardListAndPaging3);
		
		model.addAttribute("paging", boardListAndPaging3.get("paging"));
		
		log.info("[BoardController] boardListAndPaging3.get(paging) : {}", boardListAndPaging3.get("paging"));
		
		model.addAttribute("boardList", boardListAndPaging3.get("boardList"));
		
		log.info("[BoardController] boardListAndPaging3.get(boardList) : {}", boardListAndPaging3.get("boardList"));
		
		return "board/adminBoardList";
	}
	
}
