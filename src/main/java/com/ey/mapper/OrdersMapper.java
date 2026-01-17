package com.ey.mapper;

import com.ey.dto.response.OrderResponse;
import com.ey.dto.response.OrderStatusResponse;
import com.ey.enums.OrderStatus;
import com.ey.model.Order;
import com.ey.model.RefurbishProducts;

public class OrdersMapper {

	
	
	public static Order toEntity(RefurbishProducts prod,Integer quantity) {
		
		Order o=new Order();
		o.setQuantity(quantity);
		o.setProducts(prod);
		o.setStatus(OrderStatus.PENDING);
		o.setTotalAmount(prod.getPrice()*quantity);
		
		return o;
	}
	
	public static OrderResponse toResponse(Order req) {
		OrderResponse res=new OrderResponse();
		res.setBrand(req.getProducts().getInspection().getBrand());
		res.setId(req.getId());
		res.setManufatureYear(req.getProducts().getInspection().getManufatureYear());
		res.setModelName(req.getProducts().getInspection().getModelName());
		res.setPrice(req.getProducts().getPrice());
		res.setQuantity(req.getQuantity());
		res.setStatus(req.getStatus());
		res.setTotalAmount(req.getTotalAmount());
		res.setType(req.getProducts().getInspection().getType());
		
		return res;
	}
	
	public static OrderStatusResponse toStatusResponse(Order req,String msg) {
		OrderStatusResponse res=new OrderStatusResponse();
		res.setId(req.getId());
		res.setMessage(msg);
		res.setStatus(req.getStatus());
		
		return res;
	}
	
	
	
}
