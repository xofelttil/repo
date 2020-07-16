package com.yc.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//对应Mongo中的一个文档
@Document(collection="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status; //0 1 2 
	@Indexed(unique=true)
	private String phoneNum;
	
	private String name;
	
	private String idNum;
	
	private Double deposit;
	
	private Double balance;
	
	//数据库中不出村
	
	@Transient
	private String verifyCode;
	
	private String openId;
	
	private String uuid;
	
	

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public String toString() {
		return "User [status=" + status + ", phoneNum=" + phoneNum + ", name=" + name + ", idNum=" + idNum
				+ ", deposit=" + deposit + ", balance=" + balance + ", verifyCode=" + verifyCode + ", openId=" + openId
				+ ", uuid=" + uuid + "]";
	}

	
	
	
	
	
	
	
	
	

}
