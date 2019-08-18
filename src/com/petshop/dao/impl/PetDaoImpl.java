package com.petshop.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.PetDao;
import com.petshop.entity.Pet;
import com.petshop.utils.JdbcUtils;


public class PetDaoImpl implements PetDao {

	@Override
	public int save(Pet pet) throws Exception {
		int i=0;
		String sql = "insert into pet(p_name,type_name,health,love,birthDay,owner_id,store_id,can_sell) values(?,?,?,?,?,?,?,?)";
		Object[] params = {pet.getpName(),pet.getTypeName(),pet.getHealth(),pet.getLove(),pet.getBirthDay(),pet.getOwnerId(),pet.getStoreId(),pet.getCanSell()};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int del(int id) throws Exception {
		int i=0;
		String sql = "delete from pet where p_id = ?";
		Object[] params = {id};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public int udp(Pet pet) throws Exception {
		int i=0;
		String sql = "update pet set p_name=?,type_name=?,health=?,love=?,birthDay=?,owner_id=?,store_id=?,can_sell=? where p_id = ?";
		Object[] params = {pet.getpName(),pet.getTypeName(),pet.getHealth(),pet.getLove(),pet.getBirthDay(),pet.getOwnerId(),pet.getStoreId(),pet.getCanSell(),pet.getpId()};
		i=JdbcUtils.excuteUpdate(sql, params);
		JdbcUtils.freeAll();
		return i;
	}

	@Override
	public Pet qryById(int id) throws Exception {
		Pet pet = null;
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,can_sell,ow.`ow_name`,s.`st_name` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` where p_id = ?";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{id});
		if(rs.next()){
			pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			pet.setPrice(0);
			pet.setCanSell(rs.getInt("can_sell"));
		}
		JdbcUtils.freeAll();
		return pet;
	}

	@Override
	public List<Pet> qryAll() throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,can_sell,ow.`ow_name`,s.`st_name` FROM pet p LEFT JOIN  owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id`";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			pet.setPrice(0);
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllOnSell()throws Exception  {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN  owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`owner_id` = 0 AND p.`can_sell` = 3";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,3代表库存中
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllNew() throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`owner_id` = 0 AND p.`can_sell` = 1";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,1代表新培育完成
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllOwnerPets(int ownerId) throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`owner_id` = ?";
		Object[] params = {ownerId};
		ResultSet rs=JdbcUtils.excuteQuery(sql,params);
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,1代表新培育完成
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllHaveSell() throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`owner_id` != 0 AND p.`can_sell` = 2";
		ResultSet rs=JdbcUtils.excuteQuery(sql, new Object[]{});
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,1代表新培育完成
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllStoreOnGrow(int storeId) throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`store_id` = ? and p.can_sell=0";
		Object[] params = {storeId};
		ResultSet rs=JdbcUtils.excuteQuery(sql,params);
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,1代表新培育完成
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}

	@Override
	public List<Pet> qryAllStoreOnSell(int storeId) throws Exception {
		List<Pet> petList = new ArrayList<Pet>();
		String sql = "SELECT p_id,p_name,type_name,health,love,birthDay,owner_id,store_id,ow.`ow_name`,s.`st_name`,p.`can_sell` FROM pet p LEFT JOIN owner ow ON p.`owner_id` = ow.`ow_id` LEFT JOIN store s ON p.`store_id` = s.`st_id` WHERE p.`store_id` = ? and (p.can_sell=1 or p.can_sell=3)";
		Object[] params = {storeId};
		ResultSet rs=JdbcUtils.excuteQuery(sql,params);
		while(rs.next()){
			Pet pet = new Pet();
			pet.setpId(rs.getInt("p_id"));
			pet.setpName(rs.getString("p_name"));
			pet.setTypeName(rs.getString("type_name"));
			pet.setHealth(rs.getInt("health"));
			pet.setLove(rs.getInt("love"));
			pet.setBirthDay(rs.getDate("birthDay"));
			pet.setOwnerId(rs.getInt("owner_id"));
			pet.setStoreId(rs.getInt("store_id"));
			//add
			pet.setOwName(rs.getString("ow_name"));
			pet.setStName(rs.getString("st_name"));
			//add:增加状态值：是否库存,1代表新培育完成
			pet.setCanSell(rs.getInt("can_sell"));
			petList.add(pet);
		}
		JdbcUtils.freeAll();
		return petList;
	}


}
