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
			System.out.println("查询所有的宠物商店信息成功！！");
		} catch (Exception e) {
			System.out.println("PetStoreServiceImpl.getAllPetStore查询异常>>"+e.getMessage());
		}
		return storeLit;
	}

	@Override
	public PetStore login(String name, String pwd) {
		PetStore store=null;
		try {
			store = petStoreDao.qryByNameAndPwd(name, pwd);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println("PetStoreServiceImpl.login查询异常>>"+e.getMessage());
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
			System.out.println("PetOwnerServiceImpl.register查询异常>>>>"+e.getMessage());
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
			System.out.println("PetOwnerServiceImpl.find查询异常>>>>"+e.getMessage());
		}
		return ps;
	}

	@Override
	public PetStore find(int id) {
		PetStore ps=null;
		try {
			ps=petStoreDao.qryById(id);
		} catch (Exception e) {
			System.out.println("PetOwnerServiceImpl.find查询异常>>>>"+e.getMessage());
		}
		return ps;
	}

	@Override
	public boolean buy(int petId, int price, PetStore store) {
		boolean flag = true;
		try {
			//02.更新宠物商店的余额
			//防止余额不足（本项目前端已处理过数据，这一步应该无所谓有无）
			//其他具体见sell()的说明
			store=petStoreDao.qryById(store.getStId());
			if((store.getBalance()-price)>=0)
			{
				store.setBalance(store.getBalance()-price);;
				int u2=petStoreDao.udp(store);
				System.out.println("store:"+store.getStName()+u2);
				if(!(u2>0))return false;
			}else{
				System.out.println("商店buy余额不足，交易失败》》》");
				return false;
			}
			//根据主键查询出宠物信息
			Pet pet = petDao.qryById(petId);
			//根据owner中的owner_id查询出主人对象
			PetOwner owner = petOwnerDao.qryById(pet.getOwnerId());
			//01.更新宠物信息
			pet.setOwnerId(0);//去除主人
			pet.setStoreId(store.getStId());//加入商店信息
			//add.把状态由2（卖出）修改为3（库存中）
			pet.setCanSell(3);
			int u1=petDao.udp(pet);
			System.out.println("pet:"+pet.getpName()+u1);
			if(!(u1>0))return false;
			
			//03.更新主人余额
			owner.setMoney(owner.getMoney()+price);
			int u3=petOwnerDao.udp(owner);
			System.out.println("owner:"+owner.getOwName()+u3);
			if(!(u3>0))return false;
			//04.插入账户表
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
			System.out.println("PetStoreServiceImpl.buy异常>>>>"+e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean sell(int petId, int price, int ownerId, PetStore store) {
		boolean flag = true;
		try {
			Pet pet = petDao.qryById(petId);
			//03.更新宠物主人的余额：-钱    //移到第一步，余额不足不能交易
			PetOwner owner = petOwnerDao.qryById(ownerId);
			/*应该在DAO层用sql语句直接操作数据库保证实时并发，
			 * 此处只是为了简单学习项目，立个flag在这儿以后再改
			 *还有回滚的事务等等在这里都先不处理了*/
			if((owner.getMoney() - price)>=0)
			{
				owner.setMoney(owner.getMoney()-price);
				if(!(petOwnerDao.udp(owner)>0))return false;
			}
			else
			{
				System.out.println("商店sell主人余额不足，交易失败》》》");
				return false;
			}
			//01.更新宠物表：1）商店字段清空=0；2）更新owner_id；3）can_sell = 2
			pet.setStoreId(0);
			pet.setOwnerId(ownerId);
			pet.setCanSell(2);
			if(!(petDao.udp(pet)>0))return false;
			//02.更新商店余额：+钱
			store.setBalance(store.getBalance()+price);
			if(!(petStoreDao.udp(store)>0))return false;
			
			//04.插入台账
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
