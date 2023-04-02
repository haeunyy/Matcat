package com.greedy.matcat.member.service;

import com.greedy.matcat.admin.dto.TotalDTO;
import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.common.paging.Pagenation;
import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.main.dao.ProductMapper;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.member.dao.MemberMapper;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.order.dao.OrderMapper;
import com.greedy.matcat.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;
    private final PasswordEncoder PE;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;


    public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder pe, OrderMapper orderMapper, ProductMapper productMapper) {
        this.mapper = memberMapper;
        PE = pe;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }

    /* 아이디 중복확인 */
    @Override
    public boolean memberIdCheck(String memberId) {

        String result = mapper.memberIdCheck(memberId);

        return result != null ? true : false;
    }

    /* 회원 가입 */
    @Override
    public int memberRegist(MemberDTO member) {

        int result = mapper.memberRegist(member);
        int result2 = mapper.memberAuth();

        if(result > 0 && result2 >0){
            return 1;
        }
        return 0;
    }

    /* 비밀번호 찾기 시 이메일 인증 */
    @Override
    public boolean selectMemberByEmail(MemberDTO member) {

        String result = mapper.selectMemberByEmail(member);

        return result != null ? true: false;
    }

    /* 회원 정보 수정 */
    @Override
    public int modifyMember(MemberDTO updateMember) {

        log.info("updateMember : {} ", updateMember);
        int result = mapper.updateMember(updateMember);

        return result;
    }

    /* 아이디 찾기 */
    @Override
    public String findMyId(MemberDTO member) {
        log.info("[MemberService] findMyId : {} " , mapper.findMyId(member));
        return mapper.findMyId(member);
    }

    /* 임시비밀번호로 업데이트하기 위해 해당 아이디를 이메일로 찾기 */
    @Override
    public String findByMemberEmail(String email) {
        return mapper.findByMemberEmail(email);
    }

    /* 찾아온 아이디의 비밀번호를 변경 */
    @Override
    public int updatePassword(String memberId, String pwd) {
        String memberPassword = PE.encode(pwd);
        return mapper.updatePassword(memberId,memberPassword);
    }

    /* 회원 탈퇴 */
    @Override
    public int removeMember(MemberDTO member) {
        return mapper.deleteMember(member);
    }

    /* 개인정보 수정 전 비밀번호 체크 */
    @Override
    public String memberPwCheck(String id) {
        return mapper.memberPwCheck(id);
    }

    /* 관리자 생성 */
    @Override
    public int adminRegist(MemberDTO member) {

        int result = mapper.adminRegist(member);
        int result2 = mapper.memberAuthForAdmin();

        if(result > 0 && result2 >0){
            return 1;
        }
        return 0;
    }

    @Override
    public Map<String, Object> viewMemberList(Map<String, String> searchMap, int page) {

        int totalCount = mapper.totalCountMember(searchMap);
        int pageLimit = 7;
        int buttonAmount = 10;

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, pageLimit, buttonAmount, searchMap);
        List<MemberDTO> memberList = mapper.totalMember(selectCriteria);

        Map<String, Object> memberListAndPaging = new HashMap<>();
        memberListAndPaging.put("paging", selectCriteria);
        memberListAndPaging.put("memberList", memberList);

        return memberListAndPaging;
    }

    @Override
    public Map<String, Object> memberDetail(int memberNo, int page) {
        int totalCount = orderMapper.totalCountOrder(memberNo);
        int pageLimit = 10;
        int buttonAmount = 10;

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, pageLimit, buttonAmount);

        Map<String, Object> orderListAndPaging = new HashMap<>();
        List<OrderDTO> orderList = orderMapper.memberOrderList(memberNo, selectCriteria.getStartRow(), selectCriteria.getEndRow());
        orderListAndPaging.put("paging", selectCriteria);
        orderListAndPaging.put("orderList", orderList);

        return orderListAndPaging;
    }

    @Override
    public List<BoardDTO> newPost() {
        return mapper.newPost();
    }

    @Override
    public List<OrderDTO> newOrder() {
        return orderMapper.newOrder();
    }

    @Override
    public List<ProductDTO> newProdct() {
        return productMapper.newProduct();
    }

    @Override
    public TotalDTO newTotal() {
        return orderMapper.total();
    }

    @Override
    public int findMemberByEmail(String bizEmail) {
        return mapper.findMemberByEmail(bizEmail);
    }
}
