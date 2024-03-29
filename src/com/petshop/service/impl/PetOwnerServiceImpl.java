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
			System.out.println("查询所有的宠物主人信息成功！！");
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.getAllPetOwner查询异常>>"+e.getMessage());
		}
		return ownerLit;
	}

	@Override
	public PetOwner login(String name, String pwd) {
		PetOwner owner=null;
		try {
			owner = petOwnerDao.qryByNameAndPwd(name, pwd);
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.login查询异常>>>>"+e.getMessage());
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
			System.out.println("PetOwnerServiceImpl.register查询异常>>>>"+e.getMessage());
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
			// TODO 自动生成的 catch 块
			System.out.println("PetOwnerServiceImpl.find查询异常>>>>"+e.getMessage());
		}
		return po;
	}

	@Override
	public PetOwner find(String name) {
		PetOwner po=null;
		try {
			po=petOwnerDao.qryByName(name);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println("PetOwnerServiceImpl.find查询异常>>>>"+e.getMessage());
		}
		return po;
	}

	@Override
	public boolean buy(int petId, int price, PetOwner owner) {
		boolean flag = true;
		try {
			//02.更新宠物主人的余额
			//防止余额不足（本项目前端已处理过数据，这一步应该无所谓有无）
			//其他具体见sell()的说明
			owner=petOwnerDao.qryById(owner.getOwId());
			if((owner.getMoney()-price)>=0)
			{
				owner.setMoney(owner.getMoney()-price);
				int u2=petOwnerDao.udp(owner);
				System.out.println("owner:"+owner.getOwName()+u2);
				if(!(u2>0))return false;
			}else{
				System.out.println("主人buy余额不足，交易失败》》》");
				return false;
			}
			//根据主键查询出宠物信息
			Pet pet = petDao.qryById(petId);
			//根据pet中的store_id查询出商店对象
			PetStore store = petStoreDao.qryById(pet.getStoreId());
			//01.更新宠物信息
			pet.setStoreId(0);//去除商店
			pet.setOwnerId(owner.getOwId());//加入主人信息
			//add.把状态由3（库存中）修改为2（已卖出）
			pet.setCanSell(2);
			int u1=petDao.udp(pet);
			System.out.println("pet:"+pet.getpName()+u1);
			if(!(u1>0))return false;
			
			//03.更新商店余额
			store.setBalance(store.getBalance()+price);
			int u3=petStoreDao.udp(store);
			System.out.println("store:"+store.getStName()+u3);
			if(!(u3>0))return false;
			//04.插入账户表
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
			System.out.println("PetOwnerServiceImpl.buy异常>>>>"+e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean sell(int petId, int price, int storeId, PetOwner owner) {
		boolean flag = true;
		try {
			Pet pet = petDao.qryById(petId);
			//03.更新宠物商店的余额：-钱    //移到第一步，余额不足不能交易
			PetStore store = petStoreDao.qryById(storeId);
			/*应该在DAO层用sql语句直接操作数据库保证实时并发，
			 * 此处只是为了简单学习项目，立个flag在这儿以后再改
			 *还有回滚的事务等等在这里都先不处理了*/
			if((store.getBalance() - price)>=0)
			{
				store.setBalance(store.getBalance() - price);
				if(!(petStoreDao.udp(store)>0))return false;
			}
			else
			{
				System.out.println("主人sell商店余额不足，交易失败》》》");
				return false;
			}
			//01.更新宠物表：1）主人字段清空=0；2）更新store_id；3）can_sell = 3
			pet.setOwnerId(0);
			pet.setStoreId(storeId);
			pet.setCanSell(3);
			if(!(petDao.udp(pet)>0))return false;
			//02.更新宠物主人表的余额：+钱
			owner.setMoney(owner.getMoney() + price);
			if(!(petOwnerDao.udp(owner)>0))return false;
			
			//04.插入台账
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

	


