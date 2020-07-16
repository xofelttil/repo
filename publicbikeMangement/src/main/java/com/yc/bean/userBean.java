package com.yc.bean;

import java.io.Serializable;

public class userBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _id ;
	
	private String status;
	
	private String openId;
	
	private String _class;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	@Override
	public String toString() {
		return "userBean [_id=" + _id + ", status=" + status + ", openId=" + openId + ", _class=" + _class + "]";
	}
	
	
	
	

}
