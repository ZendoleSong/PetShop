package com.petshop.entity;

public class PetOwner {

	private int owId;//����id
	private String owName;//��������
	private String password;//��������
	private int money;//Ԫ����
	
	
	public PetOwner() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public PetOwner(int owId, String owName, String password, int money) {
		super();
		this.owId = owId;
		this.owName = owName;
		this.password = password;
		this.money = money;
	}

	public int getOwId() {
		return owId;
	}
	public void setOwId(int owId) {
		this.owId = owId;
	}
	public String getOwName() {
		return owName;
	}
	public void setOwName(String owName) {
		this.owName = owName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
