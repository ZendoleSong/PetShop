package com.petshop.entity;

public class PetStore {

	private int stId;//�̵�id
	private String stName;//�̵���
	private String password;//�̵�����
	private int balance;//�̵����
	
	
	public PetStore() {
		// TODO �Զ����ɵĹ��캯�����
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
