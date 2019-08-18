package com.petshop.dao;

import java.util.List;

import com.petshop.entity.PetOwner;
import com.petshop.entity.PetStore;



public interface PetStoreDao {

	int save(PetStore ps) throws Exception;
	
	int del(int id) throws Exception;
	
	int udp(PetStore ps) throws Exception;
	
	PetStore qryById(int id) throws Exception;
	PetStore qryByName(String name) throws Exception;
	List<PetStore> qryAll()  throws Exception;
	PetStore qryByNameAndPwd(String name,String pwd) throws Exception;
}
