package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.lib.exception.BookException;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.BookService;
import cn.lib.manager.service.BorrowService;
import cn.lib.manager.service.FineService;
import cn.lib.manager.service.ManagerService;
import cn.lib.manager.service.ReaderService;

public class ManagerServlet extends BaseServlet {
	private ManagerService managerService = new ManagerService();

	/**
	 * 显示所有管理人员到页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showManages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.managerService.findAllByPages(currentPage,
				pageSize);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/mem_manager.jsp")
				.forward(request, response);
	}

	/**
	 * 按条件查询管理员显示到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findManages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;

		String item = null;
		String type = request.getContentType();
		if (type != null) {

			item = request.getParameter("findItem");
		} else if (type == null) {

			String str = request.getParameter("findItem");

			byte[] bytes = str.getBytes("ISO-8859-1");

			item = new String(bytes, "utf-8");
		}
		request.setAttribute("flag", "pages");
		request.setAttribute("condition", item);
		PageBean pagebean = this.managerService.findItem(currentPage, pageSize,
				item);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.getRequestDispatcher("/backgroundPage/mem_manager.jsp")
				.forward(request, response);
	}

	/**
	 * 添加管理员
	 * 
	 * @param request
	 * @param respose
	 * @throws ServletException
	 * @throws IOException
	 */
	public void mangerAdd(HttpServletRequest request,
			HttpServletResponse respose) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Manager manager = new Manager();
		manager.setM_password(password);
		manager.setM_username(username);

		this.managerService.add(manager);
		request.getRequestDispatcher("/backgroundPage/mem_add_success.jsp")
				.forward(request, respose);

	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete_manager(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m_id = request.getParameter("m_id");
		Manager manager = new Manager();
		manager.setM_id(Integer.valueOf(m_id));
		managerService.delete(manager);
		request.getRequestDispatcher(
				"/backgroundPage/manager_delete_success.jsp").forward(request,
				response);
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update_manager(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Manager mana = new Manager();
		try {
			BeanUtils.populate(mana, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		managerService.update(mana);
		request.getRequestDispatcher(
				"/backgroundPage/manager_update_success.jsp").forward(request,
				response);
	}

	/**
	 * 对管理员信息修改进行校验
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void checkMemEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		// 得到username可以做一些响应的逻辑了

		response.getWriter().write(username);
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

	/**
	 * 管理员登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("code", null);
		request.getSession().setAttribute("username",null);
		String fcode = request.getParameter("code");
		String usernamer = request.getParameter("m_id");
		String password = request.getParameter("m_password");
		String remember = request.getParameter("remember");

		Manager manager = new Manager();
		try {
			BeanUtils.populate(manager, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ("".equals(fcode)) {

			request.getSession().setAttribute("code", "验证码不能为空");
			response.sendRedirect(request.getContextPath()
					+ "/WebPage/PageLogin.jsp");
		} else {
			HttpSession session = request.getSession();
			String scode = (String) session.getAttribute("scode");

			if (!fcode.equalsIgnoreCase(scode)) {

				request.getSession().setAttribute("code", "验证码错误");
				request.getRequestDispatcher("/WebPage/PageLogin.jsp");
			}
			Cookie c = new Cookie("m_id", usernamer);
			Cookie c1 = new Cookie("password", password);
			if ("on".equals(remember)) {
				// 设定存储到客户端的硬盘上
				c.setMaxAge(Integer.MAX_VALUE);
				c1.setMaxAge(Integer.MAX_VALUE);

			} else {
				// 设定客户端存储的用户名和密码立刻失效
				c.setMaxAge(0);
				c1.setMaxAge(0);
			}
			// 设定访问路径
			c.setPath(request.getContextPath());
			c1.setPath(request.getContextPath());

			// 向客户端发送Cookie
			response.addCookie(c);
			response.addCookie(c1);

			try {
				Manager Mmanager = new ManagerService().find(manager);
				String id = Mmanager.getM_id() + "";
				if (id.equals(usernamer)
						&& Mmanager.getM_password().equals(password)) {
                     request.getSession().setAttribute("manager", Mmanager);
					request.getRequestDispatcher("/backgroundPage/index.jsp").forward(request, response);
				}
				if(!Mmanager.getM_password().equals(password)){
					request.getSession().setAttribute("username", "用户账号或密码错误");
					response.sendRedirect(request.getContextPath()
							+ "/WebPage/PageLogin.jsp");
				}
				if ("".equals(password)) {

					request.getSession().setAttribute("username", "用户名或密码错误");
					response.sendRedirect(request.getContextPath()
							+ "/WebPage/PageLogin.jsp");
				}
				
			} catch (Exception e) {
				if ("".equals(usernamer)) {

					request.getSession().setAttribute("username", "用户名不能为空");
					response.sendRedirect(request.getContextPath()
							+ "/WebPage/PageLogin.jsp");
				}

				else {

					request.getSession().setAttribute("username", "用户名或密码错误");
					response.sendRedirect(request.getContextPath()
							+ "/WebPage/PageLogin.jsp");
				}
				// TODO: handle exception
			}

		}

	}

}
