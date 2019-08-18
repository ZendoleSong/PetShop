package com.petshop.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.AccountDao;
import com.petshop.entity.Account;
import com.petshop.utils.JdbcUtils;


public class AccountDaoImpl implements AccountDao{

	@Override
	public int save(Account account) throws Exception {
		int i=0;
		String sql = "insert into account(deal_type,pet_id,seller_id,buyer_id,price,deal_time)values(?,?,?,?,?,NOW())";
		Object[] params = {account.getDealType(),account.getPetId(),account.getSellerId(),account.getBuyerId(),account.getPrice()};
		i= JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public Account qryById(int id) throws Exception {
		Account account =null;
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT ac_id,deal_type,pet_id,seller_id,buyer_id,price,deal_time,");
		sb.append(" (SELECT pet.p_name FROM pet where pet.p_id=ac.pet_id)as p_name,");
		sb.append(" CASE ac.deal_type");	
		sb.append(" WHEN 1 THEN");
		sb.append(" (SELECT `owner`.ow_name FROM `owner` WHERE `owner`.ow_id=ac.buyer_id)");
		sb.append(" ELSE");
		sb.append(" (SELECT store.st_name FROM store WHERE store.st_id=ac.buyer_id )");
		sb.append(" END AS buyer_name,");
		sb.append(" CASE ac.deal_type");
		sb.append(" WHEN 1 THEN");
		sb.append(" (SELECT store.st_name FROM store WHERE store.st_id=ac.seller_id )");
		sb.append(" ELSE");
		sb.append(" (SELECT `owner`.ow_name FROM `owner` WHERE `owner`.ow_id=ac.seller_id)");
		sb.append(" END AS seller_name");
		sb.append(" FROM account as ac where ac_id=?");
		
	    String sql = new String(sb);

		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{id});
		if(rs.next()){
			account = new Account();
			account.setAcId(rs.getInt("ac_id"));
			account.setDealType(rs.getInt("deal_type"));
			account.setPetId(rs.getInt("pet_id"));
			account.setSellerId(rs.getInt("seller_id"));
			account.setBuyerId(rs.getInt("buyer_id"));
			account.setPrice(rs.getInt("price"));
			account.setDealTime(rs.getDate("deal_time"));
			//add
			account.setpName(rs.getString("p_name"));
			account.setSellerName(rs.getString("seller_name"));
			account.setBuyerName(rs.getString("buyer_name"));
		}
		JdbcUtils.freeAll();
		return account;
	}

	@Override
	public List<Account> qryAll() throws Exception {
		List<Account> accountList = new  ArrayList<Account>();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT ac_id,deal_type,pet_id,seller_id,buyer_id,price,deal_time,");
		sb.append(" (SELECT pet.p_name FROM pet where pet.p_id=ac.pet_id)as p_name,");
		sb.append(" CASE ac.deal_type");	
		sb.append(" WHEN 1 THEN");
		sb.append(" (SELECT `owner`.ow_name FROM `owner` WHERE `owner`.ow_id=ac.buyer_id)");
		sb.append(" ELSE");
		sb.append(" (SELECT store.st_name FROM store WHERE store.st_id=ac.buyer_id )");
		sb.append(" END AS buyer_name,");
		sb.append(" CASE ac.deal_type");
		sb.append(" WHEN 1 THEN");
		sb.append(" (SELECT store.st_name FROM store WHERE store.st_id=ac.seller_id )");
		sb.append(" ELSE");
		sb.append(" (SELECT `owner`.ow_name FROM `owner` WHERE `owner`.ow_id=ac.seller_id)");
		sb.append(" END AS seller_name");
		sb.append(" FROM account as ac");
		
	    String sql = new String(sb);

		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			Account account = new Account();
			account.setAcId(rs.getInt("ac_id"));
			account.setDealType(rs.getInt("deal_type"));
			account.setPetId(rs.getInt("pet_id"));
			account.setSellerId(rs.getInt("seller_id"));
			account.setBuyerId(rs.getInt("buyer_id"));
			account.setPrice(rs.getInt("price"));
			account.setDealTime(rs.getDate("deal_time"));
			//add
			account.setpName(rs.getString("p_name"));
			account.setSellerName(rs.getString("seller_name"));
			account.setBuyerName(rs.getString("buyer_name"));
			accountList.add(account);
		}
		JdbcUtils.freeAll();
		return accountList;
	}

}
