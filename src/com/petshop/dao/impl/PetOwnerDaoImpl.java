package com.petshop.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.PetOwnerDao;
import com.petshop.entity.PetOwner;
import com.petshop.utils.JdbcUtils;


public class PetOwnerDaoImpl implements PetOwnerDao {

	@Override
	public int save(PetOwner po) throws Exception {
		int i=0;
		String sql = "insert into owner(ow_name,password,money)values(?,?,?)";
		Object[] params = {po.getOwName(),po.getPassword(),po.getMoney()};
		i= JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int del(int id) throws Exception {
		int i=0;
		String sql = "delete from owner where ow_id = ?";
		Object[] params = {id};
		i= JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int udp(PetOwner po) throws Exception {
		int i=0;
		String sql = "update owner set ow_name=?,password=?,money=? where ow_id = ?";
		Object[] params = {po.getOwName(),po.getPassword(),po.getMoney(),po.getOwId()};
		i= JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public PetOwner qryById(int id) throws Exception {
		PetOwner owner = null;
		String sql = "select ow_id,ow_name,password,money from owner where ow_id = ?";
		Object[] params = {id};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			owner = new PetOwner();
			owner.setOwId(rs.getInt("ow_id"));
			owner.setOwName(rs.getString("ow_name"));
			owner.setPassword(rs.getString("password"));
			owner.setMoney(rs.getInt("money"));
		}
		JdbcUtils.freeAll();
		return owner;
	}

	@Override
	public List<PetOwner> qryAll() throws Exception {
		List<PetOwner> poList = new ArrayList<PetOwner>();
		String sql = "select ow_id,ow_name,password,money from owner";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			PetOwner owner = new PetOwner();
			owner.setOwId(rs.getInt("ow_id"));
			owner.setOwName(rs.getString("ow_name"));
			owner.setPassword(rs.getString("password"));
			owner.setMoney(rs.getInt("money"));
			poList.add(owner);
		}
		JdbcUtils.freeAll();
		return poList;
	}

	@Override
	public PetOwner qryByNameAndPwd(String name, String pwd) throws Exception {
		PetOwner owner = null;
		String sql = "select ow_id,ow_name,password,money from owner where ow_name = ? and password = ?";
		Object[] params = {name,pwd};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			owner = new PetOwner();
			owner.setOwId(rs.getInt("ow_id"));
			owner.setOwName(rs.getString("ow_name"));
			owner.setPassword(rs.getString("password"));
			owner.setMoney(rs.getInt("money"));
			System.out.println(owner.getOwId()+" "+owner.getOwName()+" "+owner.getPassword());
		}
		else System.out.println(name+" "+pwd+" ≤È—Ø ß∞‹");
		JdbcUtils.freeAll();
		return owner;
	}

	@Override
	public PetOwner qryByName(String name) throws Exception {
		PetOwner owner = null;
		String sql = "select ow_id,ow_name,password,money from owner where ow_name = ?";
		Object[] params = {name};
		ResultSet rs=JdbcUtils.excuteQuery(sql, params);
		if(rs.next()){
			owner = new PetOwner();
			owner.setOwId(rs.getInt("ow_id"));
			owner.setOwName(rs.getString("ow_name"));
			owner.setPassword(rs.getString("password"));
			owner.setMoney(rs.getInt("money"));
		}
		JdbcUtils.freeAll();
		return owner;
	}

}
