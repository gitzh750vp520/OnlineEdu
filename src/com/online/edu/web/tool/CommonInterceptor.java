package com.online.edu.web.tool;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.online.edu.entity.User;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		User user = (User) request.getSession().getAttribute("currUser");
		
		
		
		/*StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();
		System.out.println("是否获取到user----" + user);
		System.out.println("是否获取到URL----" + requestURL);
		System.out.println("是否获取到URI----" + requestURI);*/
		
		if (user != null) {
			return true;
		}else{
			request.setAttribute("loginTip", "请先登录！");
			request.getRequestDispatcher("/user/loginForm").forward(request, response);
			return false;
		}
	}
}
