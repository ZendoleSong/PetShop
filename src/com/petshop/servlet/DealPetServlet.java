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
	  //实例化服务接口
	  PetOwnerService petOwnerService = new PetOwnerServiceImpl();
	  PetStoreService petStoreService = new PetStoreServiceImpl();
	  PetService petService = new PetServiceImpl();
	  switch(role)
	  {
	    case "owner":
	    	PetOwner owner=(PetOwner)(req.getSession().getAttribute("owner"));  
	    	if(action.equals("buy"))
	    	{
	    		//获取页面的参数
    			int petId = Integer.valueOf(req.getParameter("petBuy"));
    			int price = Integer.valueOf(req.getParameter("price"));
	    		System.out.println("petid:"+petId+"  price:"+price);
    			//调用接口
    			boolean result=petOwnerService.buy(petId,price, owner);
	    		PrintWriter out =resp.getWriter();
    			out.println(result);
    			out.flush();
    			out.close();
	    	}
	    	else if(action.equals("sell"))
	    	{
	    		//获取页面的3个数据
				//01.宠物的id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.商店的id
				int storeId = Integer.parseInt(req.getParameter("target"));
				//03.卖出的价格
				int price = Integer.parseInt(req.getParameter("price"));
				//调用主人的服务接口
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
	    	//商店store
            PetStore store=(PetStore)(req.getSession().getAttribute("store"));
	    	if(action.equals("grow"))
	    	{
	    		//获取页面的1个数据
				//01.宠物的id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//调用商店的服务接口并输出
	    		PrintWriter out =resp.getWriter();
    			out.println(petStoreService.grow(petId, store));
    			out.flush();
    			out.close();
	    	}
	    	else if(action.equals("buy"))
	    	{
	    		//获取页面的2个数据
				//01.宠物的id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.卖出的价格
				int price = Integer.parseInt(req.getParameter("price"));
				//调用商店的服务接口并输出
				PrintWriter out =resp.getWriter();
    			out.println(petStoreService.buy(petId, price, store));
    			out.flush();
    			out.close();
				
	    	}
	    	else if(action.equals("sell"))
	    	{
	    		//获取页面的3个数据
				//01.宠物的id
				int petId = Integer.parseInt(req.getParameter("petBuy"));
				//02.主人的id
				int ownerId = Integer.parseInt(req.getParameter("target"));
				//03.卖出的价格
				int price = Integer.parseInt(req.getParameter("price"));
				//调用商店的服务接口并输出
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
