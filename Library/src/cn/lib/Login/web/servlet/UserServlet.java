package cn.lib.Login.web.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import cn.lib.Login.service.loginService;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.ReaderService;
import cn.lib.manager.web.servlet.BaseServlet;

public class UserServlet extends BaseServlet {

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().setAttribute("code1", null);
		request.getSession().setAttribute("pass",null);
		String fcode = request.getParameter("code");
		String r_id = request.getParameter("r_id");
		String r_password = request.getParameter("r_password");
		String remember = request.getParameter("remember");

		Reader reader = new Reader();

		try {
			BeanUtils.populate(reader, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if ("".equals(fcode)) {

			request.getSession().setAttribute("code1", "验证码不能为空");
			response.sendRedirect(request.getContextPath()
					+ "/WebPage/PageLogin.jsp");
		} else {
			HttpSession session = request.getSession();
			String scode = (String) session.getAttribute("scode");

			if (!fcode.equalsIgnoreCase(scode)) {

				request.getSession().setAttribute("code1", "验证码错误");
				// request.getRequestDispatcher("/WebPage/PageLogin.jsp");
				response.sendRedirect(request.getContextPath()
						+ "/WebPage/PageLogin.jsp");

			} else {

				Cookie c = new Cookie("r_id", r_id);
				Cookie c1 = new Cookie("pass", r_password);
				if ("on".equals(remember)) {

					// 设定存储到客户端的硬盘上
					c.setMaxAge(Integer.MAX_VALUE);
					c1.setMaxAge(Integer.MAX_VALUE);

				} else {
					// 设定客户端存储的用户账号和密码立刻失效
					c.setMaxAge(0);
					c1.setMaxAge(0);
				}
				// 设定访问路径
				c.setPath(request.getContextPath());
				c1.setPath(request.getContextPath());

				// 向客户端发送Cookie
				response.addCookie(c);
				response.addCookie(c1);

			  try{
					Reader Rreader = new ReaderService().find(reader);
					String id = Rreader.getR_id() + "";
					
					if (id.equals(r_id)
							&& Rreader.getR_password().equals(r_password)) {
						// 账号密码正确 跳转主页面
						loginService service = new loginService();
						int currentPage = getCurrentPage(request);
						int pageSize = 6;
						PageBean pagebean = service.getBook(currentPage,
								pageSize);
						request.setAttribute("pagebean", pagebean);
						request.getSession().setAttribute("reader", Rreader);
						request.getRequestDispatcher("/homepage.jsp").forward(
								request, response);
                        
						return;
					}
					
					if(!Rreader.getR_password().equals(r_password)){
						request.getSession().setAttribute("pass", "用户账号或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/WebPage/PageLogin.jsp");
						return;
					}
					if ("".equals(r_password)||r_password.trim()==null) {

						request.getSession().setAttribute("pass", "用户账号或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/WebPage/PageLogin.jsp");
						return;
					}
					if (!id.equals(r_id)&&"".equals(r_password)&&r_password.trim()==null) {

						request.getSession().setAttribute("pass", "用户账号或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/WebPage/PageLogin.jsp");
						return;
					}
					if ("".equals(r_id)) {

						request.getSession().setAttribute("pass", "用户账号不能为空");
						response.sendRedirect(request.getContextPath()+"/WebPage/PageLogin.jsp");
						return;
					} else {

						request.getSession().setAttribute("pass", "用户账号或密码错误");
						response.sendRedirect(request.getContextPath()+"/WebPage/PageLogin.jsp");
						return;
					}
			  }catch(NullPointerException e){
				  
					request.getSession().setAttribute("pass", "用户账号或密码错误");
					response.sendRedirect(request.getContextPath()+"/WebPage/PageLogin.jsp");
				  
			  }catch(IllegalStateException e){
				  request.getSession().setAttribute("pass", "用户账号或密码错误");
					response.sendRedirect(request.getContextPath()+"/WebPage/PageLogin.jsp");
				  
			  }
					
				
				

			}

			
			
			
		}

		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * 获取当前页码
	 * 
	 * @param request
	 * @return
	 */
	private int getCurrentPage(HttpServletRequest request) {
		String value = request.getParameter("pagecode");
		if ((value == null) || (value.trim().isEmpty())) {
			return 1;
		}
		return Integer.parseInt(value);
	}

}
