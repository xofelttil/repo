package com.yc.bean;

import java.io.Serializable;
import java.util.Arrays;

public class bike implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private String bid;

	private int status;
	
	private String qrcode;

	private Double latitude;
	
	private Double longitude;


	private String id;
	private String location;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private Double[] loc = new Double[2];

	private String phoneNum;
	private String[] types;
	private String openid;
	private Double status1;
	
	public Double getStatus1() {
		return status1;
	}
	public void setStatus1(Double status1) {
		this.status1 = status1;
	}
	/**
	 * 0未启用
	 */
	public static final int UNACTIVE = 0;
	/**
	 * 1启用但未解锁
	 */
	public static final int LOCK = 1;
	/**
	 * 2. 开锁使用中
	 */
	public static final int USING = 2;
	/**
	 * 3.报修
	 */
	public static final int INTROUBLE = 3;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double[] getLoc() {
		return loc;
	}
	public void setLoc(Double[] loc) {
		this.loc = loc;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "bike [bid=" + bid + ", status=" + status + ", qrcode=" + qrcode + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", id=" + id + ", location=" + location + ", loc=" + Arrays.toString(loc)
				+ ", phoneNum=" + phoneNum + ", types=" + Arrays.toString(types) + ", openid=" + openid + ", status1="
				+ status1 + "]";
	}
	
	
	
	
	
	
	
	

}
