package com.petshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.petshop.dao.AccountDao;
import com.petshop.dao.impl.AccountDaoImpl;
import com.petshop.entity.Account;
import com.petshop.service.AccountService;

public class AccountServiceImpl implements AccountService{
	
	private AccountDao accountDao = new AccountDaoImpl();

	@Override
	public List<Account> getAllAccount() {
		List<Account> accountList = new ArrayList<Account>();
		try {
			accountList = accountDao.qryAll();
			System.out.println("查询所有的交易信息成功！！");
		} catch (Exception e) {
			System.out.println("AccountServiceImpl.getAllAccount查询异常>>>"+e.getMessage());
		}
		return accountList;
	}

}
