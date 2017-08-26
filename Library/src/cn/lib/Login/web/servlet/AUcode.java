package cn.lib.Login.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AUcode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ment(request,response);
	
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request,response);
		
	}
public void  ment ( HttpServletRequest request,HttpServletResponse response) throws IOException{
	
	//创建变量
	int width = 120 ;
	int height = 40 ;
	//创建一个内存图像
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) ;
	//创建画笔
	Graphics g = image.getGraphics() ;
	//指定边框的颜色
	g.setColor(Color.RED) ;
	//画图像的边框
	g.drawRect(0, 0, width, height) ;
	
	//填充一下矩形的背景色
	//设定画笔的颜色
	g.setColor(Color.YELLOW) ;
	//填充矩形的背景
	g.fillRect(1, 1, width-2, height-2) ;
	
	//设置字体大小
	g.setFont(new Font("幼圆",Font.BOLD + Font.ITALIC,22)) ;
	
	
	
	//填充内容
	Random r = new Random() ;
	
	//设置画笔的颜色
	g.setColor(Color.GRAY) ;
	//画30条干扰线
	for (int i = 0; i < 10; i++) {
		g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height)) ;
	}
	
	//设定画笔的颜色
	g.setColor(Color.RED) ;
	//随机产生4个汉字
	String s = "zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP" ;
	//先将汉字转换为unicode编码
	
	StringBuffer sb = new StringBuffer() ;
	for (int i = 0; i < 4; i++) {
		char c = s.charAt(r.nextInt(s.length())) ;
		sb.append(c) ;
		int flag = r.nextBoolean()?1:-1 ;
			g.drawString(c+"", 20 + 20*i + flag*r.nextInt(5), 23 + flag * r.nextInt(6)) ; 
	}
	//将验证码存入session
	request.getSession().setAttribute("scode", sb.toString()) ;
//	//随机产生4个数字输出到页面
//	for (int i = 0; i < 4; i++) {
//		int n  = r.nextInt(10) ;
//		//画到到图片中
//		g.drawString(n+"", 20 + 20*i, 20) ;
//	}
	
	//告诉客户端不要缓存图像
	response.setHeader("Expires", -1 + "") ;
	response.setHeader("Cache-control", "no-cache") ;
	response.setHeader("Pragma", "no-cache") ;
	
	//将图片输出到客户端
	ImageIO.write(image, "jpg", response.getOutputStream()) ;
	
}
}
