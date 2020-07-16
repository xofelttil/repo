package com.yc.contraler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.biz.logService;
@RestController
public class LogController {
	
	@Autowired
	private logService ls;
	
	@PostMapping("/savelog")
	@ResponseBody
	public JsonModel savelog(JsonModel jsonModel,@RequestBody String log){
		
		try {
			ls.save(log);
			jsonModel.setCode(1);
		} catch (Exception e) {
			jsonModel .setCode(0);
			jsonModel.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return jsonModel;
	}
	
	
	@PostMapping("addPayLog")
	@ResponseBody
	public JsonModel addPayLog(JsonModel js,@RequestBody String log){
		ls.savePayLog(log);
		js.setCode(1);
		return js;
	}
	
	/*
	 * 添加维修操作 采集数据到Mongodb
	 */
	@PostMapping("addrepairlog")
	@ResponseBody
	public JsonModel addrepairlog(JsonModel js,@RequestBody String log){
		ls.repairlog(log);
		js.setCode(1);
		return js;
	}
	
	/*
	 * bikingLog
	 */
		
	
	@PostMapping("bikingLog")
	@ResponseBody
	public JsonModel bikingLog(JsonModel js,@RequestBody String log){
		ls.bikingLog(log);
		js.setCode(1);
		return js;
	}
}
