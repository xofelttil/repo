package com.yc.contraler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.biz.BikeBiz;
import com.yc.biz.PayBiz;
import com.yc.biz.genCode;

@Controller
public class PayContraler {
	@Autowired
	private genCode gc;
	@Autowired
	private BikeBiz bz;
	@Autowired
	private PayBiz pz;
	
	@PostMapping("/payMoney")
	public @ResponseBody JsonModel payMoney(JsonModel jm,PayModel pm) {
		try {
			pz.pay(  pm );     
			jm.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(  e.getMessage());
		}
		return jm;
	}
	
	
}
