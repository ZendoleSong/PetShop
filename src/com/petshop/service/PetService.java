package com.petshop.service;

import java.util.List;

import com.petshop.entity.Pet;

public interface PetService {
	    //��ʼ����ѯ���еĳ�����Ϣ
		List<Pet> getAllPets();
		//��ѯ�������۵ĳ�����Ϣ
		List<Pet> getAllPetsOnSell();
		//��ѯ�������۳��ĳ�����Ϣ
		List<Pet> getAllPetsHaveSell()throws Exception;
		//��ѯĳ�̵����������ĳ�����Ϣ
		List<Pet> getAllStoreOnGrowPets(int storeId)throws Exception;
		//��ѯĳ�̵����۵ĳ�����Ϣ��can_sell=1/3��
		List<Pet> getAllStoreOnSellPets(int storeId)throws Exception;
		//��ѯ�����������ĳ�����Ϣ
		List<Pet> getAllPetsNew();
		//��ѯ�������˵ĳ�����Ϣ
		List<Pet> getAllOwnerPets(int ownerId);
		
}
