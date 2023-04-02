package com.greedy.matcat.user.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.matcat.board.dao.BoardMapper;
import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {

	private final BoardMapper boardMapper;
	
	public UserService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	public void insertBoard(BoardDTO boardDTO) {

		boardMapper.insertBoard(boardDTO);
		log.info("[UserService] board : {}", boardDTO);
		
	}

}
