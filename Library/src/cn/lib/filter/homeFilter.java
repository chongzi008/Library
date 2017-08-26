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
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.web.servlet.AnnouncementServlet;

public class homeFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
	     HttpServletResponse response = (HttpServletResponse) resp;
		
		loginService service = new loginService();
		int currentPage;
		String value = request.getParameter("pagecode");
		if ((value == null) || (value.trim().isEmpty())) {
			currentPage= 1;
		}else{
			currentPage= Integer.parseInt(value);
		}
		
		int pageSize = 6;
		PageBean pagebean=service.getBook(currentPage,pageSize);
		request.setAttribute("pagebean", pagebean);
		new AnnouncementServlet().showAnnouncement(request, response);
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
