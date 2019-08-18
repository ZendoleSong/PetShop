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
 * urlPatterns	String[]	指定一组过滤器的 URL 匹配模式。等价于 <url-pattern> 标签。
 * servletNames	String[]	指定过滤器将应用于哪些 Servlet。取值是 @WebServlet 中的 name 属性的取值，或者是 web.xml 中 <servlet-name> 的取值。
 * initParams	WebInitParam[]	指定一组过滤器初始化参数，等价于 <init-param> 标签。
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
		System.out.println("进入OwnerSessionFilter》》");
		//执行owner的session校验
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(null == session.getAttribute("owner")&&null == session.getAttribute("store"))
		{
			System.out.println("未登陆，请先登陆!!");
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
