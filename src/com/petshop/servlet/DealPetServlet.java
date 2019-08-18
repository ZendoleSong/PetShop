package com.petshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.petshop.entity.PetOwner;
import com.petshop.entity.PetStore;
import com.petshop.service.PetOwnerService;
import com.petshop.service.PetService;
import com.petshop.service.PetStoreService;
import com.petshop.service.impl.PetOwnerServiceImpl;
import com.petshop.service.impl.PetServiceImpl;
import com.petshop.service.impl.PetStoreServiceImpl;
import com.petshop.utils.JsonUtils;

@WebServlet({"/owner/dealpetservlet","/store/dealpetservlet"})
public class DealPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  String role= req.getParameter("role");if(null==role)role="";
	  String action= req.getParameter("action");if(null==action)action="";
	  String kind= req.getParameter("kind");if(null==kind)kind="";
	  //ʵ��������ӿ�
	  PetOwnerService petOwnerService = new PetOwnerServiceImpl();
	  PetStoreService petStoreService = new PetStoreServiceImpl();
	  PetService petService = new PetServiceImpl();
	  switch(role)
	  {
	    case "owner":
	    	PetOwner owner=(PetOwner)(req.getSession().getAttribute("owner"));  
	    	if(action.equals("buy"))
	    	{
	    		//��ȡҳ��Ĳ���
    			int petId = Integer.valueOf(req.getParameter("petBuy"));
    			int price = Integer.valueOf(req.getParameter("price"));
	    		System.out.println("petid:"+petId+"  price:"+price);
    			//���ýӿ�
    			boolean result=petOwnerService.buy(petId,price, owner);
	    		PrintWriter out =resp.getWriter();
    			out.println(result);
    			out.flush();
    			out.close();
	    	}
	    	else if(action.equals("sell"))
	    	{
	    		//��ȡҳ���3������
				//01.�����id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.�̵��id
				int storeId = Integer.parseInt(req.getParameter("target"));
				//03.�����ļ۸�
				int price = Integer.parseInt(req.getParameter("price"));
				//�������˵ķ���ӿ�
				boolean result=petOwnerService.sell(petId, price, storeId, owner);
				PrintWriter out =resp.getWriter();
    			out.println(result);
    			out.flush();
    			out.close();
	    	}
	    	else
	    		resp.sendRedirect(req.getContextPath()+"/error/error.jsp");
	    	break;
	    case "store":
	    	//�̵�store
            PetStore store=(PetStore)(req.getSession().getAttribute("store"));
	    	if(action.equals("grow"))
	    	{
	    		//��ȡҳ���1������
				//01.�����id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//�����̵�ķ���ӿڲ����
	    		PrintWriter out =resp.getWriter();
    			out.println(petStoreService.grow(petId, store));
    			out.flush();
    			out.close();
	    	}
	    	else if(action.equals("buy"))
	    	{
	    		//��ȡҳ���2������
				//01.�����id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.�����ļ۸�
				int price = Integer.parseInt(req.getParameter("price"));
				//�����̵�ķ���ӿڲ����
				PrintWriter out =resp.getWriter();
    			out.println(petStoreService.buy(petId, price, store));
    			out.flush();
    			out.close();
				
	    	}
	    	else if(action.equals("sell"))
	    	{
	    		//��ȡҳ���3������
				//01.�����id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.���˵�id
				int ownerId = Integer.parseInt(req.getParameter("target"));
				//03.�����ļ۸�
				int price = Integer.parseInt(req.getParameter("price"));
				//�����̵�ķ���ӿڲ����
				PrintWriter out =resp.getWriter();
    			out.println(petStoreService.sell(petId, price, ownerId, store));
    			out.flush();
    			out.close();
	    	}
	    	else
	    		resp.sendRedirect(req.getContextPath()+"/error/error.jsp");
	    	break;
	    default:
	    	resp.sendRedirect(req.getContextPath()+"/error/error.jsp");
	  }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
