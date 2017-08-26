package cn.lib.manager.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lib.manager.domain.Announcement;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.service.AnnouncementService;

/**
 * 這是圖書館公告的管理類
 * 
 * @author Administrator
 * 
 */
public class AnnouncementServlet extends BaseServlet {

	private AnnouncementService announcementService = new AnnouncementService();

	/**
	 * 這是取得公告方法並且顯示返回到前台頁面上
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showAnnouncement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Announcement announcement = this.announcementService.showAnnouncement();
		request.setAttribute("announcement", announcement);
		request.getRequestDispatcher("/homepage.jsp").forward(request, response);
	}
	/**
	 * 添加公告
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addAnnounce(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String content = request.getParameter("Content");
		this.announcementService.addAnnounce(content);
		AllAnnounce(request, response);
	}
	/**
	 * 显示所有公告后台页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AllAnnounce(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = getCurrentPage(request);
		int pageSize = 10;
		PageBean pagebean = this.announcementService.findAllByPages(currentPage,
				pageSize);
		
		request.setAttribute("pagebean", pagebean);
		request.setAttribute("flag", "all");
		request.getRequestDispatcher("/backgroundPage/announce.jsp")
				.forward(request, response);
		
		
	}
	/**
	 * 删除公告
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String aid = request.getParameter("a_id");
		this.announcementService.delete(Integer.valueOf(aid));
		AllAnnounce(request, response);
	}
	/**
	 * 修改公告
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String content = request.getParameter("Content");
		this.announcementService.update(content,Integer.valueOf(aid));
		AllAnnounce(request, response);
	}
	
	
	public void updatehuixian(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("a_id");
		Announcement acc = this.announcementService.findById(Integer.valueOf(aid));
        request.setAttribute("acc", acc);
        request.getRequestDispatcher("/backgroundPage/arrounce_edit.jsp")
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
