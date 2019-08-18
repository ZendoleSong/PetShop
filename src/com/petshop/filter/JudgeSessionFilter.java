package com.petshop.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class JudgeSessionFilter
 * 
 * urlPatterns	String[]	ָ��һ��������� URL ƥ��ģʽ���ȼ��� <url-pattern> ��ǩ��
 * servletNames	String[]	ָ����������Ӧ������Щ Servlet��ȡֵ�� @WebServlet �е� name ���Ե�ȡֵ�������� web.xml �� <servlet-name> ��ȡֵ��
 * initParams	WebInitParam[]	ָ��һ���������ʼ���������ȼ��� <init-param> ��ǩ��
 */
@WebFilter({"/owner/*","/store/*"})

public class JudgeSessionFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("JudgeSessionFilter has been destroyed!!!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("����OwnerSessionFilter����");
		//ִ��owner��sessionУ��
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(null == session.getAttribute("owner")&&null == session.getAttribute("store"))
		{
			System.out.println("δ��½�����ȵ�½!!");
			System.out.println(req.getContextPath()+"/login.jsp");
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			
		}else
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("JudgeSessionFilter has been inited!!!");
	}

}
