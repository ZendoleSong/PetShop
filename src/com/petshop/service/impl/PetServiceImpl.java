package com.petshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.PetDao;
import com.petshop.dao.impl.PetDaoImpl;
import com.petshop.entity.Pet;
import com.petshop.service.PetService;

public class PetServiceImpl implements PetService{
	
	//����ӿڵı�̣�ע��PetDao
	private PetDao petDao = new PetDaoImpl();

	@Override
	public List<Pet> getAllPets() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAll();
			System.out.println("��ѯ���еĳ�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPets��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsOnSell() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllOnSell();
			System.out.println("��ѯ�������۵ĳ�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsOnSell��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsNew() {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllNew();
			System.out.println("��ѯ�����������ĳ�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsNew��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllOwnerPets(int ownerId) {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllOwnerPets(ownerId);
			System.out.println("��ѯ�������˵ĳ�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllOwnerPets��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllPetsHaveSell() throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllHaveSell();
			System.out.println("��ѯ�������۳�������Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllPetsHaveSell��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllStoreOnGrowPets(int storeId) throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllStoreOnGrow(storeId);
			System.out.println("��ѯ���������г�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllStoreOnGrowPets��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

	@Override
	public List<Pet> getAllStoreOnSellPets(int storeId) throws Exception {
		List<Pet> petLists = new ArrayList<Pet>();
		try {
			petLists = petDao.qryAllStoreOnSell(storeId);
			System.out.println("��ѯĳ�̵����۳�����Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetServiceImpl.getAllStoreOnSellPets��ѯ�쳣>>>"+e.getMessage());
		}
		return petLists;
	}

}
