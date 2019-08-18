package com.petshop.dao;

import java.util.List;

import com.petshop.entity.PetOwner;


public interface PetOwnerDao {

	int save(PetOwner po) throws Exception;
	
	int del(int id) throws Exception;
	
	int udp(PetOwner po) throws Exception;
	
	PetOwner qryById(int id) throws Exception;
	PetOwner qryByName(String name) throws Exception;
	
	List<PetOwner> qryAll()  throws Exception;
	//add
	PetOwner qryByNameAndPwd(String name,String pwd) throws Exception;
}
