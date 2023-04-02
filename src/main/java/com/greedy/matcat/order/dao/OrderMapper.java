package com.greedy.matcat.order.dao;

import java.util.List;
import java.util.Map;


import com.greedy.matcat.admin.dto.TotalDTO;
import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.order.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.greedy.matcat.common.paging.SelectCriteria;
import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.order.dto.OrderDTO;
import com.greedy.matcat.order.dto.PaymentDTO;

@Mapper
public interface OrderMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<OrderDTO> selectOrderList(SelectCriteria selectCriteria);

	List<OrderDTO> memberOrderList(int ordCode);

	OrderDTO selectAdminOrderDetail(int ordCode);

	List<OrderDTO> adminOrdCustomerSelect(int ordCode);

	int totalCountOrder(int no);

	List<OrderDTO> memberOrderList(@Param("memberNo") int no,@Param("startRow")int startRow , @Param("endRow")int endRow);

	List<ProductDTO> ordInfo(int prdCode);

    List<OrderDTO> myOrderDetail(int ordCode);
    
    List<CartDTO> getMyCart(int memberNo);
    
    int insertPay(PaymentDTO pay);

	OrderDTO ordComplete(int ordCode);

	int insertOrder(@Param("price") int price,@Param("memberNo") int memberNo);

    List<OrderDTO> newOrder();

	TotalDTO total();

	int insertOrderDetail(int productCode);
}
