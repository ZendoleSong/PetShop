package com.petshop.filter;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


/* 实现Filter接口的EncodingFilter
 * HttpFilter经过对比仅在tomcat9以上的servlet-api中支持
 * 若tomcat版本小于9，则用此过滤器
 * */

public class SuperEncodingFilter implements Filter {  
	private String charset=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		charset= filterConfig.getInitParameter("charset");
		if(charset == null || charset.isEmpty()) 
	       charset = "UTF-8";   
	}

	@Override
    public void doFilter(ServletRequest req,  
                         ServletResponse resp, 
                         FilterChain chain)  
            throws IOException, ServletException {  
		HttpServletRequest request= (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
    
        response.setCharacterEncoding(charset);  
        //response.setContentType("text/html;charset=" + charset);
        FSEncodingRequest res = new FSEncodingRequest(request, charset);  
        //继续请求，调用过滤器链
        chain.doFilter(res, response);  
    }
	
	@Override
	public void destroy() {
		Filter.super.destroy();
		System.out.println("SuperEncodingFilter has been destroyed!!!");
	}

}

 class FSEncodingRequest extends HttpServletRequestWrapper {  
	private HttpServletRequest request;
	private boolean hasEncode;
	private String charset;  
    public FSEncodingRequest(HttpServletRequest request, String charset) {  
        super(request);  
        this.charset = charset;  
        this.request=(HttpServletRequest) super.getRequest();
    }
    
    @Override
	public Map<String, String[]> getParameterMap() {
		//先获得请求方式
        String method = request.getMethod();
        if(method.equalsIgnoreCase("post")){   //post方式请求
            try {
                request.setCharacterEncoding(charset);
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else if(method.equalsIgnoreCase("get")){  //get方式请求
            Map<String,String[]> parameterMap = request.getParameterMap();
            if(!hasEncode){
                for(String parameterName:parameterMap.keySet()){
                    String[] values = parameterMap.get(parameterName);
                    if(values!=null){
                        for (int i = 0; i < values.length; i++) {
                            try {
                            	//tomcat8.0默认编码为utf-8
                            	if(java.nio.charset.Charset.forName("ISO-8859-1")
                                		.newEncoder().canEncode(values[i]))
                                values[i] = new String(values[i].getBytes("ISO-8859-1"),charset);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode =  true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
	}
    
	@Override
	public String getParameter(String name) {
		 Map<String,String[]> parameterMap = getParameterMap();
	        String[] values = parameterMap.get(name);
	        if(values==null){
	            return null;
	        }
	        return values[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		   Map<String,String[]> parameterMap = getParameterMap();
	        String[] values=parameterMap.get(name);
	        return values;
	}  

    
}


