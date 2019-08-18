package com.petshop.dao;

import java.util.List;

import com.petshop.entity.Account;


public interface AccountDao {

	int save(Account account) throws Exception;
	
//	int del(int id) throws Exception;

//	int udp(Pet pet) throws Exception;
	
	Account qryById(int id) throws Exception;
	List<Account> qryAll()  throws Exception;
}
