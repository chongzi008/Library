package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import cn.lib.exception.FineException;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.service.FineService;

public class FineServlet extends BaseServlet {
	private FineService fineService = new FineService();

	/**
	 * 显示所有罚款的信息到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showFines(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.fineService.findAllByPages(currentPage,
				pageSize);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/Fine_manager.jsp")
				.forward(request, response);

	}

	/**
	 * 根据查询条件返回结果显示到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findFines(HttpServletRequest request,
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
		PageBean pagebean = this.fineService.findItem(currentPage, pageSize,
				item);

		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "page");
		request.getRequestDispatcher("/backgroundPage/Fine_manager.jsp")
				.forward(request, response);
	}

	/**
	 * 罚款记录删除
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fineDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String f_id = request.getParameter("f_id");
		fineService.delete(f_id);
		showFines(request, response);
	}

	/**
	 * 罚款记录编辑
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fineEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Fine fine = new Fine();

		try {
			BeanUtils.populate(fine, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		this.fineService.update(fine);
		showFines(request, response);
	}

	public void addFine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Fine fine = new Fine();
		try {
			BeanUtils.populate(fine, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		fine.setF_date(null);
		String fineType = request.getParameter("fine");

		boolean add = true;
		try {
			add = this.fineService.add(fine, fineType);
		} catch (FineException e) {
			request.setAttribute("msg", e.getMessage());
			 request.getRequestDispatcher("/backgroundPage/B_error.jsp").forward(request, response);
		}
		if (add) {
			showFines(request, response);
		} else {
			request.getRequestDispatcher("/backgroundPage/error.jsp").forward(
					request, response);
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
