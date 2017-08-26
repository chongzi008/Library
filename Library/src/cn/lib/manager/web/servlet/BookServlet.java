package cn.lib.manager.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import cn.lib.exception.BookException;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.service.BookService;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();

	/**
	 * 查询到所有图书并显示到页面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.bookService.findAllByPages(currentPage,
				pageSize);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/book_manager.jsp")
				.forward(request, response);
		
	}

	/**
	 * 查找书本
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findBooks(HttpServletRequest request,
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
		PageBean pagebean = this.bookService.findItem(currentPage, pageSize,
				item);
		int size = pagebean.getPageBeanList().size();
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("size", Integer.valueOf(size));
		request.setAttribute("flag", "page");
		request.setAttribute("condition", item);
		request.getRequestDispatcher("/backgroundPage/book_manager.jsp")
				.forward(request, response);
	}

	/**
	 * 修改图书相关信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws BookException
	 */
	public void bookEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorsMap = new HashMap<String, String>();

		Book book = new Book();
		try {
			BeanUtils.populate(book, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String b_state = request.getParameter("b_state");

		try {
			bookService.update(book, b_state);
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (BookException e) {
			errorsMap.put("errors", e.getMessage());
			request.setAttribute("emap", errorsMap);
			request.getRequestDispatcher("/backgroundPage/book_edit.jsp")
					.forward(request, response);
		}
		showBooks(request, response);
	}

	/**
	 * 删除图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bookDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String b_id = request.getParameter("b_id");
		bookService.delete(Integer.valueOf(b_id));
		request.getRequestDispatcher("/backgroundPage/book_delete_success.jsp")
				.forward(request, response);
	}

	/**
	 * 添加图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Book book = new Book();
		try {
			BeanUtils.populate(book, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("b_type");
		BookType bookType = new BookType();
		try{
			bookType.setT_id(Integer.valueOf(type));	
		}catch(NumberFormatException e){
			request.setAttribute("msg","请输入正确的类型id");
			request.setAttribute("book", book);
			request.getRequestDispatcher("/backgroundPage/book_add.jsp")
			.forward(request, response);
		}
		
		book.setType(bookType);
		try {
			bookService.add(book);
		} catch (BookException e) {
			
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("book", book);
			request.getRequestDispatcher("/backgroundPage/book_add.jsp")
			.forward(request, response);
		}
		request.getRequestDispatcher("/backgroundPage/book_add_success.jsp")
				.forward(request, response);
	}

	/**
	 * 前台查看推荐书籍的一本书信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadOneBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String b = request.getParameter("bid");
		int bid = Integer.valueOf(b);
		Book book = this.bookService.findById(bid);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}

	/**
	 * 前台根据书名查询书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		String item = null;
		String type = request.getContentType();
		if (type != null) {

			item = request.getParameter("bookName");
		} else if (type == null) {

			String str = request.getParameter("bookName");

			byte[] bytes = str.getBytes("ISO-8859-1");

			item = new String(bytes, "utf-8");
		}
		if (item.trim() == null || item == null || item.equals("")) {
			PageBean pagebean = null;
			request.setAttribute("pagebean", pagebean);
			request.setAttribute("condition", item);
		} else {

			PageBean pagebean = this.bookService.findItem(currentPage, pageSize, item);
			request.setAttribute("pagebean", pagebean);
			request.setAttribute("condition", item);
		}

		

		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}
	/**
	 * 根据A类书籍返回书本
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sortByA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean<Book> pageBean = bookService.sortByA(currentPage, pageSize,1);
		request.setAttribute("pagebean", pageBean);
		request.getRequestDispatcher("/list.jsp")
				.forward(request, response);
		
		
	}
	
   /**
    * 通过类型查找书本
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
	public void findByType(HttpServletRequest request,
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
		PageBean pagebean=null;
		if(item==null||item.trim()==null){
			request.setAttribute("pagebean", pagebean);
			request.setAttribute("flag", "page");
			request.setAttribute("condition", item);
			request.getRequestDispatcher("/backgroundPage/book_manager.jsp")
					.forward(request, response);
			
		}else{
			 pagebean = this.bookService.findByType(currentPage, pageSize,
						item);
			 request.setAttribute("pagebean", pagebean);
				request.setAttribute("flag", "page");
				request.setAttribute("condition", item);
				request.getRequestDispatcher("/backgroundPage/book_manager.jsp")
						.forward(request, response);
		}
			
	
		
	}
	
	/**
	 *书籍回显
	 */
		public void bookhuixian(HttpServletRequest request,HttpServletResponse response) 
				throws ServletException, IOException {
			String b_id = request.getParameter("b_id");
			Book book = bookService.findById(Integer.valueOf(b_id));
			request.setAttribute("book", book);
			request.getRequestDispatcher("/backgroundPage/book_edit.jsp")
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
