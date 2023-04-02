package com.greedy.matcat.member.service;

import com.greedy.matcat.admin.dto.TotalDTO;
import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.order.dto.OrderDTO;

import java.util.List;
import java.util.Map;

public interface MemberService {

    boolean memberIdCheck(String memberId);

    int memberRegist(MemberDTO member);

    String memberPwCheck(String password);

    boolean selectMemberByEmail(MemberDTO member);

    int modifyMember(MemberDTO updateMember);

    String findMyId(MemberDTO member);

    String findByMemberEmail(String email);

    int updatePassword(String memberId, String memberPassword);

    int removeMember(MemberDTO member);

    int adminRegist(MemberDTO member);
    Map<String, Object> viewMemberList(Map<String, String> searchMap, int page);
    Map<String, Object> memberDetail(int no, int page);
    List<BoardDTO> newPost();
    List<OrderDTO> newOrder();
    List<ProductDTO> newProdct();
    TotalDTO newTotal();
    int findMemberByEmail(String bizEmail);
}
