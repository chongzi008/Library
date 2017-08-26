package cn.lib.manager.service;

import cn.lib.manager.dao.impl.AnnouncementImpl;
import cn.lib.manager.domain.Announcement;
import cn.lib.manager.domain.PageBean;

public class AnnouncementService {
	private AnnouncementImpl announcementImpl = new AnnouncementImpl();

	public Announcement showAnnouncement() {

		return this.announcementImpl.showAnnouncement();
	}

	public void addAnnounce(String content) {
		this.announcementImpl.addAnnounce(content);

	}

	public PageBean findAllByPages(int currentPage, int pageSize) {

		return this.announcementImpl.findAllByPages(currentPage, pageSize);
	}

	public void update(String content, int aid) {
		this.announcementImpl.update(content, aid);

	}

	public void delete(int aid) {
		this.announcementImpl.delete(aid);

	}

	public Announcement findById(int a_id) {
		return this.announcementImpl.findById(a_id);

	}

}
