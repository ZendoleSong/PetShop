package com.petshop.service;

import java.util.List;

import com.petshop.entity.PetOwner;

public interface PetOwnerService {

	List<PetOwner> getAllPetOwner();
	
	PetOwner login(String name,String pwd);
	PetOwner register(PetOwner po);
	PetOwner find(int id);
	PetOwner find(String name);
	//�������˹���
	boolean buy(int petId,int price,PetOwner owner);	
	//����������������
	boolean sell(int petId,int price,int storeId,PetOwner owner);
	
}
