package com.yc.biz;

import java.util.List;

import com.yc.bean.User;

public interface genCode {
	
	public void genVerifyCode(String nationCode, String phoneNum) throws Exception;
	
	//验证验证码的实现类
	public boolean verify(User user);
	
	//实现充值
	public boolean deposit(User user);
	
	/**完成身份验证 */
	public boolean identity(User user);
	
	/**
	 * 充值
	 * 
	 * @param balance:
	 *            金额
	 * @param phoneNum:
	 *            电话
	 * @return
	 */
	public boolean recharge(double balance, String phoneNum);

	public List<User> selectMember(String openId);

	public void addMember(User u);

	public String redisSessionKey(String openId, String session_key);
	
	
	

	
	
}
