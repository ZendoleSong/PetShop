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
import com.petshop.service.PetStoreService;

public class PetStoreServiceImpl implements PetStoreService{
	
	private PetOwnerDao petOwnerDao = new PetOwnerDaoImpl();
	private PetDao petDao = new PetDaoImpl();
	private PetStoreDao petStoreDao = new PetStoreDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();

	@Override
	public List<PetStore> getAllPetStore() {
		List<PetStore> storeLit = new ArrayList<>();
		try {
			storeLit = petStoreDao.qryAll();
			System.out.println("��ѯ���еĳ����̵���Ϣ�ɹ�����");
		} catch (Exception e) {
			System.out.println("PetStoreServiceImpl.getAllPetStore��ѯ�쳣>>"+e.getMessage());
		}
		return storeLit;
	}

	@Override
	public PetStore login(String name, String pwd) {
		PetStore store=null;
		try {
			store = petStoreDao.qryByNameAndPwd(name, pwd);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("PetStoreServiceImpl.login��ѯ�쳣>>"+e.getMessage());
		}
		return store;
	}

	@Override
	public PetStore register(PetStore ps) {
		int i=0;
		try {
			i=petStoreDao.save(ps);
			if(i>0)
				ps=petStoreDao.qryByNameAndPwd(ps.getStName(), ps.getPassword());
			else
				ps=null;
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.register��ѯ�쳣>>>>"+e.getMessage());
			ps=null;
		}
		return ps;
	}

	@Override
	public PetStore find(String name) {
		PetStore ps=null;
		try {
			ps=petStoreDao.qryByName(name);
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.find��ѯ�쳣>>>>"+e.getMessage());
		}
		return ps;
	}

	@Override
	public PetStore find(int id) {
		PetStore ps=null;
		try {
			ps=petStoreDao.qryById(id);
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.find��ѯ�쳣>>>>"+e.getMessage());
		}
		return ps;
	}

	@Override
	public boolean buy(int petId, int price, PetStore store) {
		boolean flag = true;
		try {
			//02.���³����̵�����
			//��ֹ���㣨����Ŀǰ���Ѵ�������ݣ���һ��Ӧ������ν���ޣ�
			//���������sell()��˵��
			store=petStoreDao.qryById(store.getStId());
			if((store.getBalance()-price)>=0)
			{
				store.setBalance(store.getBalance()-price);;
				int u2=petStoreDao.udp(store);
				System.out.println("store:"+store.getStName()+u2);
				if(!(u2>0))return false;
			}else{
				System.out.println("�̵�buy���㣬����ʧ�ܡ�����");
				return false;
			}
			//����������ѯ��������Ϣ
			Pet pet = petDao.qryById(petId);
			//����owner�е�owner_id��ѯ�����˶���
			PetOwner owner = petOwnerDao.qryById(pet.getOwnerId());
			//01.���³�����Ϣ
			pet.setOwnerId(0);//ȥ������
			pet.setStoreId(store.getStId());//�����̵���Ϣ
			//add.��״̬��2���������޸�Ϊ3������У�
			pet.setCanSell(3);
			int u1=petDao.udp(pet);
			System.out.println("pet:"+pet.getpName()+u1);
			if(!(u1>0))return false;
			
			//03.�����������
			owner.setMoney(owner.getMoney()+price);
			int u3=petOwnerDao.udp(owner);
			System.out.println("owner:"+owner.getOwName()+u3);
			if(!(u3>0))return false;
			//04.�����˻���
			Account account=new Account();
			account.setDealType(2);
			account.setSellerId(owner.getOwId());
			account.setBuyerId(store.getStId());
			account.setPetId(pet.getpId());
			account.setPrice(price);
			int u4=accountDao.save(account);
			System.out.println("account:"+u4);
			if(!(u4>0))return false;
		} catch (Exception e) {
			flag = false;
			System.out.println("PetStoreServiceImpl.buy�쳣>>>>"+e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean sell(int petId, int price, int ownerId, PetStore store) {
		boolean flag = true;
		try {
			Pet pet = petDao.qryById(petId);
			//03.���³������˵���-Ǯ    //�Ƶ���һ�������㲻�ܽ���
			PetOwner owner = petOwnerDao.qryById(ownerId);
			/*Ӧ����DAO����sql���ֱ�Ӳ������ݿⱣ֤ʵʱ������
			 * �˴�ֻ��Ϊ�˼�ѧϰ��Ŀ������flag������Ժ��ٸ�
			 *���лع�������ȵ������ﶼ�Ȳ�������*/
			if((owner.getMoney() - price)>=0)
			{
				owner.setMoney(owner.getMoney()-price);
				if(!(petOwnerDao.udp(owner)>0))return false;
			}
			else
			{
				System.out.println("�̵�sell�������㣬����ʧ�ܡ�����");
				return false;
			}
			//01.���³����1���̵��ֶ����=0��2������owner_id��3��can_sell = 2
			pet.setStoreId(0);
			pet.setOwnerId(ownerId);
			pet.setCanSell(2);
			if(!(petDao.udp(pet)>0))return false;
			//02.�����̵���+Ǯ
			store.setBalance(store.getBalance()+price);
			if(!(petStoreDao.udp(store)>0))return false;
			
			//04.����̨��
			Account account=new Account();
			account.setDealType(1);
			account.setSellerId(store.getStId());
			account.setBuyerId(owner.getOwId());
			account.setPetId(pet.getpId());
			account.setPrice(price);
			if(accountDao.save(account)>0)flag=true;
			else flag=false;
		} catch (Exception e) {
			flag = false;
			System.out.println("PetStoreServiceImpl.sell>>>"+e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean grow(int petId, PetStore store) {
		boolean flag=true;
		try {
			Pet pet=petDao.qryById(petId);
			pet.setCanSell(1);
			int i=petDao.udp(pet);
			if(!(i>0))return false;
		} catch (Exception e) {
			flag = false;
			System.out.println("PetStoreServiceImpl.grow>>>"+e.getMessage());
		}
		return flag;
	}

}
