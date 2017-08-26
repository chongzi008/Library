package cn.lib.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lib.Login.service.loginService;
import cn.lib.manager.dao.impl.BorrowImpl;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.BorrowService;
import cn.lib.manager.web.servlet.BorrowServlet;

public class ManagerFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
	     HttpServletResponse response = (HttpServletResponse) resp;
		Manager manager =  (Manager) request.getSession().getAttribute("manager");
	    if(manager==null){
	    	request.setAttribute("msg", "你还没有登录不可以进入该页面!!!");
	    	request.getRequestDispatcher("/WebPage/error.jsp").forward(request, response);
	    	
	    }else{
	    	
	    	chain.doFilter(request, response);
	    } 
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
