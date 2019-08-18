package com.petshop.entity;

import java.util.Date;

public class Pet {

	private int pId;//����id
	private String pName;//������
	private String typeName;//��������
	private int health;//����ֵ
	private int love;//���ܶ�
	private Date birthDay;//��������
	private int ownerId;//��������id
	private int storeId;//�����̵�id
	//add:���˵����������̵��������
	private String owName;//��������
	private String stName;//�̵���
	//add:����Ľ��׼۸�
	private int price;
	//add:�Ƿ����ۣ�0.������,1.��������ɣ�2.������3.���-����
	private int canSell;
	
	public Pet() {
			// TODO �Զ����ɵĹ��캯�����
		}
	
	public Pet(int pId, String pName, String typeName, int health, int love, Date birthDay, int ownerId, int storeId,
			String owName, String stName, int price) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.typeName = typeName;
		this.health = health;
		this.love = love;
		this.birthDay = birthDay;
		this.ownerId = ownerId;
		this.storeId = storeId;
		this.owName = owName;
		this.stName = stName;
		this.price = price;
	}

	public String getOwName() {
		return owName;
	}
	public void setOwName(String owName) {
		this.owName = owName;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCanSell() {
		return canSell;
	}

	public void setCanSell(int canSell) {
		this.canSell = canSell;
	}
}
