package com.petshop.entity;

public class PetStore {

	private int stId;//商店id
	private String stName;//商店名
	private String password;//商店密码
	private int balance;//商店余额
	
	
	public PetStore() {
		// TODO 自动生成的构造函数存根
	}
	public PetStore(int stId, String stName, String password, int balance) {
		super();
		this.stId = stId;
		this.stName = stName;
		this.password = password;
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public int getStId() {
		return stId;
	}
	public void setStId(int stId) {
		this.stId = stId;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
