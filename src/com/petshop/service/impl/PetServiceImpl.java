package com.petshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.PetDao;
import com.petshop.dao.impl.PetDaoImpl;
import com.petshop.entity.Pet;
import com.petshop.service.PetService;

public class PetServiceImpl implements PetService{
	
	//面向接口的编程：注入PetDao
	private PetDao petDao = new PetDaoImpl();

	@Override
	public List<Pet> getAllPets() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAll();
			System.out.println("查询所有的宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPets查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsOnSell() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllOnSell();
			System.out.println("查询所有在售的宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsOnSell查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsNew() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllNew();
			System.out.println("查询所有新培育的宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsNew查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllOwnerPets(int ownerId) {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllOwnerPets(ownerId);
			System.out.println("查询所有主人的宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllOwnerPets查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsHaveSell() throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllHaveSell();
			System.out.println("查询所有已售出宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsHaveSell查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllStoreOnGrowPets(int storeId) throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllStoreOnGrow(storeId);
			System.out.println("查询所有培育中宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllStoreOnGrowPets查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllStoreOnSellPets(int storeId) throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllStoreOnSell(storeId);
			System.out.println("查询某商店在售宠物信息成功！！");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllStoreOnSellPets查询异常>>>"+e.getMessage());
		}
		return petLists;
	}

}
