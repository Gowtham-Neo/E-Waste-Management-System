package com.ey.mapper;

import java.time.LocalDate;

import com.ey.dto.request.recycler.DisposeRequest;
import com.ey.dto.response.DisposeCollectorResponse;
import com.ey.dto.response.DisposeMsgResponse;
import com.ey.dto.response.DisposeResponse;
import com.ey.dto.response.RegisterCollectorResponse;
import com.ey.enums.RequestStatus;
import com.ey.model.Catagory;
import com.ey.model.Dispose;

public class DisposeMapper {

	
	
	public static Dispose toEntity(DisposeRequest req,Catagory cata) {
		
		Dispose dis=new Dispose();
		dis.setCatagory(cata);
		dis.setLocation(req.getLocation());
		dis.setQuantity(req.getQuantity());
		dis.setRequestDate(LocalDate.now());
		dis.setStatus(RequestStatus.CREATED);
		
		return dis;
	}
	
	public static DisposeMsgResponse toResponse(Dispose req,String msg) {
		DisposeMsgResponse res=new DisposeMsgResponse();
		res.setCatagory(req.getCatagory());
		res.setId(req.getId());
		res.setLocation(req.getLocation());
		res.setQuantity(req.getQuantity());
		res.setRequestDate(req.getRequestDate());
		res.setStatus(req.getStatus());
		res.setMessage(msg);
		
		
		return res;
	}
	public static DisposeResponse toResponse(Dispose req) {
		DisposeResponse res=new DisposeResponse();
		res.setCatagory(req.getCatagory());
		res.setId(req.getId());
		res.setLocation(req.getLocation());
		res.setQuantity(req.getQuantity());
		res.setRequestDate(req.getRequestDate());
		res.setStatus(req.getStatus());
		
		
		return res;
	}
	
	public static DisposeCollectorResponse toResponseWithCollector(Dispose req,String msg) {
		DisposeCollectorResponse res=new DisposeCollectorResponse();
		res.setId(req.getId());
		RegisterCollectorResponse coll=CollectorMapper.toResponse(req.getCollector(), msg);
		res.setCollector(coll);
		res.setMessage(msg);
		res.setStatus(req.getStatus());
		
		
		
		return res;
	}
	
}
