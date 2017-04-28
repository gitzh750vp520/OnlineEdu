package com.online.edu.web.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.online.edu.entity.Admin;

public class CommonInterceptorMgr extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("currAdmin");
		
		
		if (admin != null) {
			return true;
		}else{
			response.sendRedirect(request.getContextPath() + "/mgr/loginForm");
			return false;
		}
	}
}
