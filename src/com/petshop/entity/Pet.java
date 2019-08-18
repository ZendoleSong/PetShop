package com.petshop.entity;

import java.util.Date;

public class Pet {

	private int pId;//宠物id
	private String pName;//宠物名
	private String typeName;//宠物类型
	private int health;//健康值
	private int love;//亲密度
	private Date birthDay;//出生日期
	private int ownerId;//宠物主人id
	private int storeId;//宠物商店id
	//add:主人的中文名和商店的中文名
	private String owName;//主人姓名
	private String stName;//商店名
	//add:宠物的交易价格
	private int price;
	//add:是否在售，0.培育中,1.新培育完成，2.卖出，3.库存-回收
	private int canSell;
	
	public Pet() {
			// TODO 自动生成的构造函数存根
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
