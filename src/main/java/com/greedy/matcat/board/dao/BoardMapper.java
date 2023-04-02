package com.greedy.matcat.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.board.dto.ReplyDTO;
import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.member.dto.MemberDTO;

@Mapper
public interface BoardMapper {

	int selectTotalCount(@Param("searchMap")Map<String, String> searchMap , @Param("memberNo")int memberNo);

	List<BoardDTO> selectBoardList(@Param("searchMap")SelectCriteria selectCriteria, @Param("memberNo")int memberNo);

	List<BoardDTO> selectThumbnailBoardList(SelectCriteria selectCriteria);

	/*조회수 안하는중 int incrementBoardCount(Long postCode); */

	List<ReplyDTO> selectReplyList(Long postCode);
	
	List<ReplyDTO> selectReplyList(ReplyDTO loadReplyList);

	void insertBoard(BoardDTO boardDTO);

	void deleteReply(ReplyDTO removeReply);

	void insertReply(ReplyDTO registReply);

	BoardDTO selectBoardDetail(Long postCode);

	void deletePost(BoardDTO removePost);

	void updatePost(BoardDTO updateBoard);

	int selectTotalCount2(@Param("searchMap")Map<String, String> searchMap);

	List<BoardDTO> selectBoardList2(@Param("searchMap")SelectCriteria selectCriteria);

	int selectTotalCount3(@Param("searchMap")Map<String, String> searchMap);

	List<BoardDTO> selectBoardList3(@Param("searchMap")SelectCriteria selectCriteria);


	
}
