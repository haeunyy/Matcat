package com.greedy.matcat.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.matcat.board.dao.BoardMapper;
import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.board.dto.ReplyDTO;
import com.greedy.matcat.common.paging.Pagenation;
import com.greedy.matcat.common.paging.SelectCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {

	private final BoardMapper boardMapper;
	
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	public Map<String, Object> selectBoardList(Map<String, String> searchMap, int page, int memberNo){
		
		int totalCount = boardMapper.selectTotalCount(searchMap , memberNo);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[BoardService] selectCriteria : {}", selectCriteria);
		
		List<BoardDTO> boardList = boardMapper.selectBoardList(selectCriteria , memberNo);
		log.info("[BoardService] selectCriteria : {}", selectCriteria);
		
		Map<String, Object> boardListAndPaging = new HashMap<>();
		boardListAndPaging.put("paging", selectCriteria);
		boardListAndPaging.put("boardList", boardList);
		
		return boardListAndPaging;
	}
	public BoardDTO selectBoardDetail(Long postCode) {
		
		BoardDTO board = new BoardDTO();
		
		if(postCode != null){board = boardMapper.selectBoardDetail(postCode);
		
		}
				
		return board;
	}

	public List<ReplyDTO> loadReply(Long postCode) {
		
		return boardMapper.selectReplyList(postCode);
	}

	public void registReply(ReplyDTO registReply) {
		boardMapper.insertReply(registReply);
		
	}

	public void removeReply(ReplyDTO removeReply) {
		
		boardMapper.deleteReply(removeReply);
		
	}
	
	public List<ReplyDTO> loadReplyList(ReplyDTO loadReplyList){
		
		return boardMapper.selectReplyList(loadReplyList);
	}

	public void removePost(BoardDTO removePost) {
		
		boardMapper.deletePost(removePost);
		
	}

	public void updatePost(BoardDTO board) {
		
		boardMapper.updatePost(board);
		
	}

	public Map<String, Object> selectBoardList2(Map<String, String> searchMap, int page) {
		int totalCount = boardMapper.selectTotalCount2(searchMap);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[BoardService] selectCriteria : {}", selectCriteria);
		
		List<BoardDTO> boardList = boardMapper.selectBoardList2(selectCriteria);
		log.info("[BoardService] selectCriteria : {}", selectCriteria);
		
		Map<String, Object> boardListAndPaging = new HashMap<>();
		boardListAndPaging.put("paging", selectCriteria);
		boardListAndPaging.put("boardList", boardList);
		
		return boardListAndPaging;
	}

	public Map<String, Object> selectBoardList3(Map<String, String> searchMap, int page) {
		int totalCount = boardMapper.selectTotalCount3(searchMap);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[BoardService] selectCriteria3 : {}", selectCriteria);
		
		List<BoardDTO> boardList = boardMapper.selectBoardList3(selectCriteria);
		log.info("[BoardService] selectCriteria3 : {}", selectCriteria);
		
		Map<String, Object> boardListAndPaging = new HashMap<>();
		boardListAndPaging.put("paging", selectCriteria);
		boardListAndPaging.put("boardList", boardList);
		
		return boardListAndPaging;
	}
	

	


}