package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.service.BookTypeService;
import cn.lib.manager.service.FineService;

/**
 * 管理分类
 * 
 * @author ChongZi007
 * 
 */
public class BookTypeServlet extends BaseServlet {
	private BookTypeService bookTypeService = new BookTypeService();

	/**
	 * 显示所有分类的信息到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showTypes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.bookTypeService.findAllByPages(currentPage,
				pageSize);
		request.setAttribute("pagebean", pagebean);
		request.getRequestDispatcher("/backgroundPage/booktype.jsp").forward(
				request, response);

	}

	/**
	 * 分类删除
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void typeDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String t_id = request.getParameter("t_id");
		bookTypeService.delete(t_id);
		showTypes(request, response);
	}

	/**
	 * 分类编辑
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void typeEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BookType bookType=new BookType();

		try {
			BeanUtils.populate(bookType, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		this.bookTypeService.update(bookType);
		showTypes(request, response);
	}

	/**
	 * 添加分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookType bookType = new BookType();
		try {
			BeanUtils.populate(bookType, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		this.bookTypeService.add(bookType);
		showTypes(request, response);

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
