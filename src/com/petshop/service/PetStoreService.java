package com.petshop.service;

import java.util.List;

import com.petshop.dao.PetStoreDao;
import com.petshop.dao.impl.PetStoreDaoImpl;
import com.petshop.entity.PetOwner;
import com.petshop.entity.PetStore;


public interface PetStoreService {
	

	List<PetStore> getAllPetStore();
	PetStore login(String name,String pwd);
	PetStore register(PetStore ps);
	PetStore find(String name);
	PetStore find(int id);
	//宠物商店购买
	boolean buy(int petId,int price,PetStore store);	
	//宠物商店卖出宠物
	boolean sell(int petId,int price,int ownerId,PetStore store);
	//宠物商店培育宠物：
	boolean grow(int petId,PetStore store);
}
