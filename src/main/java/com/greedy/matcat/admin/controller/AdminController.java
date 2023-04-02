package com.greedy.matcat.admin.controller;

import com.greedy.matcat.admin.dto.TotalDTO;
import com.greedy.matcat.board.dto.BoardDTO;
import com.greedy.matcat.board.service.BoardService;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberService;
import com.greedy.matcat.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder PE;

    public AdminController(MemberService memberService, BoardService boardService, MessageSourceAccessor messageSourceAccessor, PasswordEncoder pe) {
        this.memberService = memberService;
        this.boardService = boardService;
        this.messageSourceAccessor = messageSourceAccessor;
        PE = pe;
    }

    @RequestMapping("/mainpage")
    public String mainpage(Model model) {

        List<ProductDTO> productList = memberService.newProdct();
        List<BoardDTO> boardList = memberService.newPost();
        List<OrderDTO> orderList = memberService.newOrder();
        TotalDTO total = memberService.newTotal();

        model.addAttribute("boardList", boardList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);
        model.addAttribute("total", total);

        return "/admin/mainpage";
    }

    @GetMapping("/regist")
    public String adminRegist() {
        return "/admin/regist";
    }

    @PostMapping("/regist")
    public String registAdmin(@ModelAttribute MemberDTO member,
                              RedirectAttributes rttr) {

        member.setMemberPwd(PE.encode(member.getPassword()));
        int result = memberService.adminRegist(member);

        if (result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("regist.fail"));
        }
        return "redirect:/main";
    }

    @GetMapping("/viewMember")
    public String viewMember(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false) String searchCondition,
                             @RequestParam(required = false) String searchValue,
                             Model model) {
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        Map<String, Object> memberList = memberService.viewMemberList(searchMap, page);

        model.addAttribute("paging", memberList.get("paging"));
        model.addAttribute("memberList", memberList.get("memberList"));

        return "/admin/viewMember";
    }

    @GetMapping("/memberDetail")
    public String memberDetail(@RequestParam int no, @RequestParam(defaultValue = "1") int page, Model model) {

        Map<String, Object> pagingAndOrderList = memberService.memberDetail(no, page);

        model.addAttribute("paging", pagingAndOrderList.get("paging"));
        model.addAttribute("orderList", pagingAndOrderList.get("orderList"));
        log.info("ordList : {} ", pagingAndOrderList.get("orderList"));

        return "admin/memberDetail";
    }

    @GetMapping("adminHelp001")
    public void adminHelp001() {
    }


}