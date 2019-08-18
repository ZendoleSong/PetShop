package com.petshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.AccountDao;
import com.petshop.dao.PetDao;
import com.petshop.dao.PetOwnerDao;
import com.petshop.dao.PetStoreDao;
import com.petshop.dao.impl.AccountDaoImpl;
import com.petshop.dao.impl.PetDaoImpl;
import com.petshop.dao.impl.PetOwnerDaoImpl;
import com.petshop.dao.impl.PetStoreDaoImpl;
import com.petshop.entity.Account;
import com.petshop.entity.Pet;
import com.petshop.entity.PetOwner;
import com.petshop.entity.PetStore;
import com.petshop.service.PetOwnerService;

public class PetOwnerServiceImpl implements PetOwnerService{
	
	private PetOwnerDao petOwnerDao = new PetOwnerDaoImpl();
	private PetDao petDao = new PetDaoImpl();
	private PetStoreDao petStoreDao = new PetStoreDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();
	
	
	@Override
	public List<PetOwner> getAllPetOwner() {
		List<PetOwner> ownerLit = new ArrayList<>();
		try {
			ownerLit = petOwnerDao.qryAll();
			System.out.println("��ѯ���еĳ���������Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.getAllPetOwner��ѯ�쳣>>"+e.getMessage());
		}
		return ownerLit;
	}

	@Override
	public PetOwner login(String name, String pwd) {
		PetOwner owner=null;
		try {
			owner = petOwnerDao.qryByNameAndPwd(name, pwd);
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.login��ѯ�쳣>>>>"+e.getMessage());
		}
		return owner;
	}

	@Override
	public PetOwner register(PetOwner po) {
		int i=0;
		try {
			i=petOwnerDao.save(po);
			if(i>0)
				po=petOwnerDao.qryByNameAndPwd(po.getOwName(), po.getPassword());
			else
				po=null;
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.register��ѯ�쳣>>>>"+e.getMessage());
			po=null;
		}
		return po;
	}

	@Override
	public PetOwner find(int id) {
		PetOwner po=null;
		try {
			po=petOwnerDao.qryById(id);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("PetOwnerServiceImpl.find��ѯ�쳣>>>>"+e.getMessage());
		}
		return po;
	}

	@Override
	public PetOwner find(String name) {
		PetOwner po=null;
		try {
			po=petOwnerDao.qryByName(name);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("PetOwnerServiceImpl.find��ѯ�쳣>>>>"+e.getMessage());
		}
		return po;
	}

	@Override
	public boolean buy(int petId, int price, PetOwner owner) {
		boolean flag = true;
		try {
			//02.���³������˵����
			//��ֹ���㣨����Ŀǰ���Ѵ�������ݣ���һ��Ӧ������ν���ޣ�
			//���������sell()��˵��
			owner=petOwnerDao.qryById(owner.getOwId());
			if((owner.getMoney()-price)>=0)
			{
				owner.setMoney(owner.getMoney()-price);
				int u2=petOwnerDao.udp(owner);
				System.out.println("owner:"+owner.getOwName()+u2);
				if(!(u2>0))return false;
			}else{
				System.out.println("����buy���㣬����ʧ�ܡ�����");
				return false;
			}
			//����������ѯ��������Ϣ
			Pet pet = petDao.qryById(petId);
			//����pet�е�store_id��ѯ���̵����
			PetStore store = petStoreDao.qryById(pet.getStoreId());
			//01.���³�����Ϣ
			pet.setStoreId(0);//ȥ���̵�
			pet.setOwnerId(owner.getOwId());//����������Ϣ
			//add.��״̬��3������У��޸�Ϊ2����������
			pet.setCanSell(2);
			int u1=petDao.udp(pet);
			System.out.println("pet:"+pet.getpName()+u1);
			if(!(u1>0))return false;
			
			//03.�����̵����
			store.setBalance(store.getBalance()+price);
			int u3=petStoreDao.udp(store);
			System.out.println("store:"+store.getStName()+u3);
			if(!(u3>0))return false;
			//04.�����˻���
			Account account=new Account();
			account.setDealType(1);
			account.setSellerId(store.getStId());
			account.setBuyerId(owner.getOwId());
			account.setPetId(pet.getpId());
			account.setPrice(price);
			int u4=accountDao.save(account);
			System.out.println("account:"+u4);
			if(!(u4>0))return false;
		} catch (Exception e) {
			flag = false;
			System.out.println("PetOwnerServiceImpl.buy�쳣>>>>"+e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean sell(int petId, int price, int storeId, PetOwner owner) {
		boolean flag = true;
		try {
			Pet pet = petDao.qryById(petId);
			//03.���³����̵����-Ǯ    //�Ƶ���һ�������㲻�ܽ���
			PetStore store = petStoreDao.qryById(storeId);
			/*Ӧ����DAO����sql���ֱ�Ӳ������ݿⱣ֤ʵʱ������
			 * �˴�ֻ��Ϊ�˼�ѧϰ��Ŀ������flag������Ժ��ٸ�
			 *���лع�������ȵ������ﶼ�Ȳ�������*/
			if((store.getBalance() - price)>=0)
			{
				store.setBalance(store.getBalance() - price);
				if(!(petStoreDao.udp(store)>0))return false;
			}
			else
			{
				System.out.println("����sell�̵����㣬����ʧ�ܡ�����");
				return false;
			}
			//01.���³����1�������ֶ����=0��2������store_id��3��can_sell = 3
			pet.setOwnerId(0);
			pet.setStoreId(storeId);
			pet.setCanSell(3);
			if(!(petDao.udp(pet)>0))return false;
			//02.���³������˱����+Ǯ
			owner.setMoney(owner.getMoney() + price);
			if(!(petOwnerDao.udp(owner)>0))return false;
			
			//04.����̨��
			Account account=new Account();
			account.setDealType(2);
			account.setSellerId(owner.getOwId());
			account.setBuyerId(store.getStId());
			account.setPetId(pet.getpId());
			account.setPrice(price);
			if(!(accountDao.save(account)>0))return false;
		} catch (Exception e) {
			flag = false;
			System.out.println("PetOwnerServiceImpl.sell>>>"+e.getMessage());
		}
		return flag;
	}
}

	


