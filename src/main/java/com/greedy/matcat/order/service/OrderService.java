package com.greedy.matcat.order.service;

import java.util.List;
import java.util.Map;

import com.greedy.matcat.main.dto.CartDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.order.dto.OrderDTO;
import com.greedy.matcat.order.dto.PaymentDTO;

public interface OrderService {


	public Map<String, Object> selectOrderList(Map<String, String> searchMap, int page);

    public OrderDTO selectAdminOrderDetail(int ordCode);

    public List<OrderDTO> adminOrdCustomerSelect(int ordCode);

	List<ProductDTO> ordInfo(int prdCode);
    
    List<OrderDTO> myOrderDetail(int ordCode);

    List<CartDTO> getMyCart(int memberNo);
    
    int insertPay(PaymentDTO pay);

	OrderDTO ordComplete(int memNo);
    int insertOrder(int memberNo, int price);
    int insertOrderDetail(int productCode);
}
