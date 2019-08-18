package com.petshop.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.PetStoreDao;
import com.petshop.entity.PetStore;
import com.petshop.utils.JdbcUtils;

public class PetStoreDaoImpl implements PetStoreDao {

	@Override
	public int save(PetStore ps) throws Exception {
		int i=0;
		String sql="insert into store(st_name,password,balance)values(?,?,?)";
		Object[] params ={ps.getStName(),ps.getPassword(),ps.getBalance()};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int del(int id) throws Exception {
		int i=0;
		String sql="delete from store where st_id = ?";
		Object[] params ={id};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int udp(PetStore ps) throws Exception {
		int i=0;
		String sql="update store set st_name=?,password=?,balance=? where st_id = ?";
		Object[] params ={ps.getStName(),ps.getPassword(),ps.getBalance(),ps.getStId()};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public PetStore qryById(int id) throws Exception {
		PetStore store = null;
		String sql = "select st_id,st_name,password,balance from store where st_id = ?";
		Object[] params = {id};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			store = new PetStore();
			store.setStId(rs.getInt("st_id"));
			store.setStName(rs.getString("st_name"));
			store.setPassword(rs.getString("password"));
			store.setBalance(rs.getInt("balance"));
		}
		JdbcUtils.freeAll();
		return store;
	}

	@Override
	public List<PetStore> qryAll() throws Exception {
		List<PetStore> storeList = new ArrayList<PetStore>();
		String sql = "select st_id,st_name,password,balance from store";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			PetStore store = new PetStore();
			store.setStId(rs.getInt("st_id"));
			store.setStName(rs.getString("st_name"));
			store.setPassword(rs.getString("password"));
			store.setBalance(rs.getInt("balance"));
			storeList.add(store);
		}
		JdbcUtils.freeAll();
		return storeList;
	}

	@Override
	public PetStore qryByNameAndPwd(String name, String pwd) throws Exception {
		PetStore store = null;
		String sql = "select st_id,st_name,password,balance from store where st_name = ? and password = ?";
		Object[] params = {name,pwd};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			store = new PetStore();
			store.setStId(rs.getInt("st_id"));
			store.setStName(rs.getString("st_name"));
			store.setPassword(rs.getString("password"));
			store.setBalance(rs.getInt("balance"));
		}
		JdbcUtils.freeAll();
		return store;
	}

	@Override
	public PetStore qryByName(String name) throws Exception {
		PetStore store = null;
		String sql = "select st_id,st_name,password,balance from store where st_name = ?";
		Object[] params = {name};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			store = new PetStore();
			store.setStId(rs.getInt("st_id"));
			store.setStName(rs.getString("st_name"));
			store.setPassword(rs.getString("password"));
			store.setBalance(rs.getInt("balance"));
		}
		JdbcUtils.freeAll();
		return store;
	}

}
