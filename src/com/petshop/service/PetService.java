package com.petshop.service;

import java.util.List;

import com.petshop.entity.Pet;

public interface PetService {
	    //初始化查询所有的宠物信息
		List<Pet> getAllPets();
		//查询所有在售的宠物信息
		List<Pet> getAllPetsOnSell();
		//查询所有已售出的宠物信息
		List<Pet> getAllPetsHaveSell()throws Exception;
		//查询某商店正在培育的宠物信息
		List<Pet> getAllStoreOnGrowPets(int storeId)throws Exception;
		//查询某商店在售的宠物信息（can_sell=1/3）
		List<Pet> getAllStoreOnSellPets(int storeId)throws Exception;
		//查询所有新培育的宠物信息
		List<Pet> getAllPetsNew();
		//查询所有主人的宠物信息
		List<Pet> getAllOwnerPets(int ownerId);
		
}
