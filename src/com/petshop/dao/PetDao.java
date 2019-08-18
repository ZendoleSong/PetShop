package com.petshop.dao;

import java.util.List;

import com.petshop.entity.Pet;



public interface PetDao {

	int save(Pet pet) throws Exception;
	
	int del(int id) throws Exception;
	
	int udp(Pet pet) throws Exception;
	
	Pet qryById(int id) throws Exception;
	
	List<Pet> qryAll()  throws Exception;

	List<Pet> qryAllOnSell()throws Exception;

	List<Pet> qryAllNew()throws Exception;
	
	List<Pet> qryAllHaveSell()throws Exception;
	
	List<Pet> qryAllStoreOnGrow(int storeId)throws Exception;
	
	List<Pet> qryAllStoreOnSell(int storeId)throws Exception;

	List<Pet> qryAllOwnerPets(int ownerId)throws Exception;
}
