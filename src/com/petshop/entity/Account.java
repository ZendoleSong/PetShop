package com.petshop.entity;

import java.util.Date;

public class Account {

	private int acId;//��Ŀid
	private int dealType;//�������ͣ�1.�̵��������ˣ�2.���������̵�
	private int petId;//����id
	private int sellerId;//����id
	private int buyerId;//���id
	private int price;//�۸�
	private Date dealTime;//����ʱ��
	private String pName;//������
	private String sellerName;//������
	private String buyerName;//�����
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	public int getDealType() {
		return dealType;
	}
	public void setDealType(int dealType) {
		this.dealType = dealType;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
}
