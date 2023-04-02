package com.greedy.matcat.member.dao;

import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    String memberIdCheck(String memberId);
    String memberPwCheck(String memberId);
    String selectMemberByEmail(MemberDTO member);
    MemberDTO login(String memberId);
    int memberRegist(MemberDTO member);
    int memberAuth();
    int updateMember(MemberDTO updateMember);
    String findMyId(MemberDTO member);
    String findByMemberEmail(String email);
    int updatePassword(@Param("memberId") String memberId,@Param("memberPwd") String memberPassword);
    int deleteMember(MemberDTO member);
    int memberAuthForAdmin();
    int adminRegist(MemberDTO member);
    int totalCountMember(Map<String, String> searchMap);
    List<MemberDTO> totalMember(SelectCriteria selectCriteria);
    int memberRegistForApi(@Param("name") String name,@Param("email") String email);
    List<BoardDTO> newPost();
    int findMemberByEmail(String bizEmail);
}
