package com.petshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshop.entity.PetOwner;
import com.petshop.entity.PetStore;
import com.petshop.service.PetOwnerService;
import com.petshop.service.PetStoreService;
import com.petshop.service.impl.PetOwnerServiceImpl;
import com.petshop.service.impl.PetStoreServiceImpl;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/store/identityservlet","/owner/identityservlet","/identityservlet"})
public class IdentityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");if(name!=null)name=name.trim();
		String pwd = req.getParameter("userpw");if(pwd!=null)pwd=pwd.trim();
		String role=req.getParameter("role");if(role!=null)role=role.trim();
		String action=req.getParameter("action");if(action!=null)action=action.trim();
		System.out.println("name:"+name);
		System.out.println("pwd:"+pwd);
		System.out.println("role:"+role);
		System.out.println("action:"+action);
		if(action==null)action="error";
		switch (action)
		{
		  case "login":
			  if(role.equals("owner"))
				{
					//调用service接口
					PetOwnerService petOwnerService = new PetOwnerServiceImpl();
					PetOwner owner=petOwnerService.login(name, pwd);
					if(owner!=null)
					{
						//登陆成功
						if(req.getSession().getAttribute("store")!=null)
							req.getSession().removeAttribute("store");
						req.getSession().setAttribute("owner", owner);
						resp.sendRedirect("owner/ownerAdmin.jsp");
					}
					else
					{
						req.setAttribute("errMsg", "用户名或密码错误!");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
						/*PrintWriter out=resp.getWriter();
						out.println("<script>alert(\"用户名或密码错误!\");</script>");
						resp.sendRedirect("login.jsp");*/
						
					}
				}
				else if(role.equals("store"))
				{
					PetStoreService petStoreService =new PetStoreServiceImpl();
					PetStore store=petStoreService.login(name, pwd);
					if(store!=null)
					{
						//登陆成功
						if(req.getSession().getAttribute("owner")!=null)
							req.getSession().removeAttribute("owner");
						req.getSession().setAttribute("store", store);
						resp.sendRedirect("store/storeAdmin.jsp");
					}
					else
					{
						req.setAttribute("errMsg", "用户名或密码错误!");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
						/*PrintWriter out=resp.getWriter();
						out.println("<script>alert(\"用户名或密码错误!\");</script>");
						resp.sendRedirect("login.jsp");*/
					}
				}
				else
					resp.sendRedirect("login.jsp");
			  break;
		  
		  case "register":
			  if(role.equals("owner"))
			  {
				  PetOwnerService pos=new PetOwnerServiceImpl();
				  if(pos.find(name)==null)
				  {
					  PetOwner po=pos.register(new PetOwner(-1, name, pwd, 0));
					  if(po==null)
					  {
						 req.setAttribute("errMsg", "用户注册失败!");
						 req.getRequestDispatcher("register.jsp").forward(req, resp);
					  }
					  else
					  {			
						  if(req.getSession().getAttribute("store")!=null)
								req.getSession().removeAttribute("store");
						  req.getSession().setAttribute("owner", po);
						  resp.sendRedirect("owner/ownerAdmin.jsp");
					  }
					  
						
				  }
				  else
				  {
					 req.setAttribute("errMsg", "用户名已被注册!");
					 req.getRequestDispatcher("register.jsp").forward(req, resp);
				  }				  
			   }
			  else if(role.equals("store"))
			  {
				  PetStoreService pos=new PetStoreServiceImpl();
				  if(pos.find(name)==null)
				  {
					  PetStore po=pos.register(new PetStore(-1, name, pwd, 0));
					  if(po==null)
					  {
						 req.setAttribute("errMsg", "用户注册失败!");
						 req.getRequestDispatcher("register.jsp").forward(req, resp);
					  }
					  else
					  {			
						  if(req.getSession().getAttribute("owner")!=null)
								req.getSession().removeAttribute("owner");
						  req.getSession().setAttribute("store", po);
						  resp.sendRedirect("store/storeAdmin.jsp");
					  }
				  }
				  else
				  {
					 req.setAttribute("errMsg", "用户名已被注册!");
					 req.getRequestDispatcher("register.jsp").forward(req, resp);
				  }			
			  
			  }
			  else
				  resp.sendRedirect("login.jsp");
			  break;
		  case "logout":
			  if(req.getSession().getAttribute("owner")!=null)
					req.getSession().removeAttribute("owner");
			  if(req.getSession().getAttribute("store")!=null)
					req.getSession().removeAttribute("store");
			  resp.sendRedirect("login.jsp");
			  break;
		  default:
			  resp.sendRedirect("login.jsp");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
