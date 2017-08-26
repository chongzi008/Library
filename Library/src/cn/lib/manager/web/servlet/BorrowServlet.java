package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lib.exception.FineException;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.BorrowService;

public class BorrowServlet extends BaseServlet {
	private BorrowService borrowService = new BorrowService();

	/**
	 * 借阅相关分页显示到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showBorrows(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.borrowService.findBorrows(currentPage,
				pageSize);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/book_reader_borrow.jsp")
				.forward(request, response);

	}

	/**
	 * 根据查询结果显示到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findBorrows(HttpServletRequest request,
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
		request.setAttribute("condition", item);
		PageBean pagebean = this.borrowService.findItem(currentPage, pageSize,
				item);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "page");
		request.getRequestDispatcher("/backgroundPage/book_reader_borrow.jsp")
				.forward(request, response);
	}

	/**
	 * 显示所有未还书籍数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findOverdateBorrows(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.borrowService.findOverdateBorrows(currentPage,
				pageSize);

		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "overdate");
		request.getRequestDispatcher("/backgroundPage/book_reader_borrow.jsp")
				.forward(request, response);
	}

	/**
	 * 借阅相关的修改
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editBorrow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rid = request.getParameter("rid");
		String bid = request.getParameter("bid");
		String isback = request.getParameter("isback");
		Borrow borrow = new Borrow();
		String lenddate = request.getParameter("lendtime");
		Date lend_date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			lend_date = sdf.parse(lenddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String bor_id = request.getParameter("bor_id");
		borrow.setBor_id(Integer.valueOf(bor_id));
		borrow.setIsback(isback);
		borrow.setLend_date(lend_date);
		if (isback.equals("是")) {

			borrow.setBack_date(new Date());
			boolean is = this.borrowService.update(borrow,Integer.valueOf(rid),Integer.valueOf(bid));
			if(!is){
				request.getRequestDispatcher("/backgroundPage/borrow_error.jsp").forward(request, response);
				return ;
			}

		} else if (isback.equals("否")) {

			borrow.setBack_date(null);
			this.borrowService.updateNo(borrow,Integer.valueOf(rid),Integer.valueOf(bid));

		}

		showBorrows(request, response);
	}

	public void borrowRecord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Reader reader = (Reader) request.getSession().getAttribute("reader");

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.borrowService.findById(currentPage, pageSize,
				reader.getR_id());
		request.setAttribute("pagebean", pagebean);

		request.getRequestDispatcher("/readhistory.jsp").forward(request,
				response);
	}

	public void addBorrow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getParameter("r_id");
		String b = request.getParameter("b_id");
		boolean is = false;
		try {
			is = this.borrowService.addBorrow(Integer.valueOf(r),
					Integer.valueOf(b));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FineException e) {
			request.setAttribute("msg", e.getMessage());
			 request.getRequestDispatcher("/backgroundPage/B_error.jsp").forward(request, response);
		}
		if (is) {
			showBorrows(request, response);
		} else {
             request.getRequestDispatcher("/backgroundPage/addBorrowMiss.jsp").forward(request, response);
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
