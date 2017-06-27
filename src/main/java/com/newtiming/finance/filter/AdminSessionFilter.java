package com.newtiming.finance.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

import com.common.util.StringUtil;
import com.newtiming.core.Constants;

/**
 * @description 权限控制过滤器
 * @ClassName AdminSessionFilter
 * @CreateDate 
 */
//@WebFilter(filterName="",urlPatterns={"/"})
@SuppressWarnings("unused")
public class AdminSessionFilter implements Filter {
    private static String CONTEXT_PATH = "/";
	private static List<String> WHITE_URIS = null;
	private static List<String> STATIC_URIS = null;
//	private static List<String> permissions = null;
	Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME); 
	
	/*@PostConstruct
    public void setIP(){
    	try {
    		MDC.put("ip", InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.err.println(StringUtil.getExceptionStackTrace(e));
		}
    	
    }*/
	
	/**
	 * Ajax请求header中的标识名和值
	 */
	private static String AJAX_REQUEST_HEADER_NAME = "x-requested-with";
	private static String AJAX_REQUEST_HEADER_VALUE = "XMLHttpRequest";

	public void init(FilterConfig filterConfig) throws ServletException {
		CONTEXT_PATH = filterConfig.getServletContext().getContextPath();
		String[] whiteUris = filterConfig.getInitParameter("whiteUrls").split(",");
		if(whiteUris!=null && whiteUris.length>0){
			WHITE_URIS = java.util.Arrays.asList(whiteUris);
		}
		String[] staticUtis = filterConfig.getInitParameter("staticUrls").split(",");
		if(whiteUris!=null && whiteUris.length>0){
			STATIC_URIS = java.util.Arrays.asList(staticUtis);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		//请求URI,如:/account/login
		String uri = request.getRequestURI();
		uri = uri.startsWith("//") ? uri.substring(1) : uri;
		boolean isWhiteUri = isWhiteUri(uri);//是否为白名单
		boolean isStatic = isStatic(uri);//是否为静态资源
		
		if(!isStatic){
			System.out.println("------>>>uri: "+uri);
		}
		
		if(isWhiteUri || isStatic) {
			chain.doFilter(request, response);
		}else {
			Object admin = session.getAttribute(Constants.LOGIN_USER_INFO);
			
			if(admin == null){
				if (isAjaxRequest(request)) {
					//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.setHeader("sessionstatus", "timeout");
				} else {
					response.sendRedirect("/admin/reqlogin.htm");
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}
	
	
	public void destroy() {}
	
	
	/**
	 * 是否为白名单
	 * @param uri 请求的URI
	 * @param whiteUtiList 白名单列表
	 * @return
	 */
	private boolean isWhiteUri(String uri) {
		if(WHITE_URIS != null && WHITE_URIS.size()>0){
			String reg = null;
			Pattern pattern = null;
			Matcher matcher = null;
			for(String u : WHITE_URIS){
				reg = u.replace(" ", "").replace("*", "[-_0-9A-Za-z]+");
				pattern = Pattern.compile("^"+reg+"$");
				matcher = pattern.matcher(uri);
				if(matcher.matches()){
					return true;
				}
			}
			
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 是否为静态资源
	 * @param uri 请求的URI
	 * @return
	 */
	private boolean isStatic(String uri) {
		if(STATIC_URIS != null && STATIC_URIS.size()>0){
			for(String u : STATIC_URIS){
				if(uri.toLowerCase().endsWith(u)){
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 是否ajax请求
	 * @param request
	 * @return
	 */
	private static boolean isAjaxRequest(HttpServletRequest request) {
		String val = request.getHeader(AJAX_REQUEST_HEADER_NAME);
		val = val== null?"":val.toLowerCase();
		return AJAX_REQUEST_HEADER_VALUE.toLowerCase().equals(val);
	}
	
	public static void main(String[] args) {
		String uri = "/admin/login.htm";
		System.out.println(uri.matches("^"+uri+"$"));
		
		
//		String reg = uri.replace(".", "\\.").replace("*", "[-_0-9A-Za-z]+");
		String reg = uri;
		
		Pattern pattern = Pattern.compile("^"+reg+"$");
		Matcher matcher = pattern.matcher(uri);
//		System.out.println(matcher.matches());
		
			
	}
	
	public static void test() {}
}
