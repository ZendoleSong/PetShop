package com.petshop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshop.entity.PetOwner;
import com.petshop.service.AccountService;
import com.petshop.service.PetOwnerService;
import com.petshop.service.PetService;
import com.petshop.service.PetStoreService;
import com.petshop.service.impl.AccountServiceImpl;
import com.petshop.service.impl.PetOwnerServiceImpl;
import com.petshop.service.impl.PetServiceImpl;
import com.petshop.service.impl.PetStoreServiceImpl;

@WebServlet({"/initservlet"})
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PetService petService = new PetServiceImpl();
		AccountService accountService = new AccountServiceImpl();
		PetOwnerService petOwnerService = new PetOwnerServiceImpl();
		PetStoreService petStoreService = new PetStoreServiceImpl();
		req.setAttribute("petList", petService.getAllPets());
		req.setAttribute("accountList", accountService.getAllAccount());
		req.setAttribute("ownerList", petOwnerService.getAllPetOwner());
		req.setAttribute("storeList", petStoreService.getAllPetStore());
		req.getRequestDispatcher("information.jsp").forward(req, resp);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
