package com.greedy.matcat.main.controller;


import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.main.service.ProductService;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class Maincontroller {

    private final ProductService productService;
    private final OrderService orderService;


    @Autowired
    public Maincontroller(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @RequestMapping("/common/error")
    public String error(Model model) {
        model.addAttribute("message", "권한이 없습니다.\\n[확인] 을 누르면 메인페이지, [취소] 를 누르면 로그인 페이지로 이동합니다.");
        return "/common/error";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<ProductDTO> findNewProduct = productService.findMainNewProduct();
        log.info("findNewProduct : {} ", findNewProduct);
        model.addAttribute("newProduct", findNewProduct);
        List<ProductDTO> goodProduct = productService.findGoodProduct();
        model.addAttribute("goodProduct", goodProduct);

        return "/abc/main/main";
    }

    @GetMapping("/productList")
    public String main3(@RequestParam(defaultValue = "1") int categoryCode,
                        @RequestParam(defaultValue = "1") int arrayCode,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(required = false) String searchValue,
                        Model model) {
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchValue", searchValue);
        Map<String, Object> productList = productService.defaultSearchProduct(searchMap, page, arrayCode, categoryCode);
        model.addAttribute("paging", productList.get("paging"));
        model.addAttribute("productList", productList.get("productList"));
        model.addAttribute("arrayCode", arrayCode);
        model.addAttribute("categoryCode", categoryCode);
        return "abc/main/productList";
    }


    @GetMapping("/menusemi")
    public String menusemi(Model model, int no) {

        List<ProductDTO> semiProduct = productService.semiProduct(no);
        log.info("semiProduct : {}", semiProduct);
        model.addAttribute("semiProduct", semiProduct);

        return "/abc/main/menusemi";
    }

    @GetMapping("/review")
    public String review() {

        return "/abc/main/review";

    }

    @GetMapping("/main/cart2")
    public String cart(@AuthenticationPrincipal MemberDTO member, Model model) {
        int memberNo = member.getMemberNo();
        List<CartDTO> cartList = orderService.getMyCart(memberNo);
        int totalPrice = 0;
        for (int i = 0; i < cartList.size(); i++) {
            totalPrice += cartList.get(i).getProducts().getPrice();
        }
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);

        return "/main/ehyun/cart2";
    }


}