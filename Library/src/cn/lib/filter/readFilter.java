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
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.BorrowService;
import cn.lib.manager.web.servlet.BorrowServlet;

public class readFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
	     HttpServletResponse response = (HttpServletResponse) resp;
		Reader reader = (Reader) request.getSession().getAttribute("reader");
	    if(reader==null){
	    	request.setAttribute("msg", "你还没有登录不可以进入该页面!!!");
	    	request.getRequestDispatcher("/WebPage/error.jsp").forward(request, response);
	    	
	    }else{
	    	int r_id=reader.getR_id();
	    	BorrowService borrowService=new BorrowService();
	    	int currentPage;
	    	String value = request.getParameter("pagecode");
			if ((value == null) || (value.trim().isEmpty())) {
				currentPage= 1;
			}else{
				currentPage=Integer.parseInt(value);
			}
	    	PageBean pageBean = borrowService.findById(currentPage, 10, r_id);
	    	request.setAttribute("pagebean", pageBean);
	    	chain.doFilter(request, response);
	    } 
	
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
