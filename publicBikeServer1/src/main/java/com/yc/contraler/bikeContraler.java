package com.yc.contraler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.bike;
import com.yc.biz.BikeBiz;

@Controller

public class bikeContraler {
	
	
	@Autowired
	private BikeBiz bikeBiz;
	
	
	
	@PostMapping("/open")
	public  @ResponseBody  JsonModel open( @RequestBody bike bk){
		JsonModel js =new JsonModel();
		System.out.println("bk"+bk);
		//Todo jsonModel不反回空值消耗流量
		try {
			bikeBiz.open(bk);
			js.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			js.setCode(0);
			js.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return js;
	}
	
	
	@PostMapping("/findNearAll")
	
	public @ResponseBody JsonModel findNearAll(JsonModel js,@RequestBody bike bk){
		
		try {
			List<bike> list =bikeBiz.findNearBike(bk);
			js.setCode(1);
			js.setObj(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			js.setCode(0);
			js.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return js;
		
	
	}
	
	
	// 报修
		@PostMapping("/repair")
		public @ResponseBody JsonModel repair(JsonModel jm, bike bike) {
			try {
				this.bikeBiz.reportMantinant(bike);
				jm.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
				jm.setCode(0);
				jm.setMsg(e.getMessage());
			}
			return jm;
		}
	
	

}
