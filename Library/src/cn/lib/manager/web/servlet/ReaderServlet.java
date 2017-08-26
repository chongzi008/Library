package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.ReaderService;



public class ReaderServlet extends BaseServlet {
	private ReaderService readerService = new ReaderService();
	
	/**
	 * 显示所有读者到页面上去
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showReaders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.readerService.findAllByPages(currentPage,
				pageSize);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/reader_manager.jsp")
				.forward(request, response);
		;
	}
	
	
	
	/**
	 * 按条件查询读者显示到页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findReaders(HttpServletRequest request,
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
		PageBean pagebean = this.readerService.findItem(currentPage, pageSize,
				item);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "page");
		request.setAttribute("condition", item);
		request.getRequestDispatcher("/backgroundPage/reader_manager.jsp")
				.forward(request, response);
	}

	
	
	/**
	 * 添加会员的方法
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void readerAdd(HttpServletRequest request,
			HttpServletResponse respose) throws ServletException, IOException {

		String sex = request.getParameter("sex");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Reader reader = new Reader();
		reader.setR_password(password);
		reader.setR_username(username);
		reader.setR_sex(sex);
		readerService.add(reader);

		request.getRequestDispatcher("/backgroundPage/mem_add_success.jsp")
				.forward(request, respose);
		;
	}
	
	/**
	 * 删除读者
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteReader(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r_id = request.getParameter("r_id");
		readerService.delete(Integer.valueOf(r_id));
		request.getRequestDispatcher(
				"/backgroundPage/reader_delete_success.jsp").forward(request,
				response);
	}

	/**
	 * 修改读者信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editReader(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Reader reader = new Reader();
		try {
			BeanUtils.populate(reader, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		this.readerService.update(reader);
		request.getRequestDispatcher("/backgroundPage/reader_edit_success.jsp")
				.forward(request, response);
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
