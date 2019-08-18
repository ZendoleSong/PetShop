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
	//�����̵깺��
	boolean buy(int petId,int price,PetStore store);	
	//�����̵���������
	boolean sell(int petId,int price,int ownerId,PetStore store);
	//�����̵��������
	boolean grow(int petId,PetStore store);
}
