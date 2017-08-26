<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="css/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery1.42.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
	});	
});
</script>

<script type="text/javascript">
$(function(){
	$(".screenbg ul li").each(function(){
		$(this).css("opacity","0");
	});
	$(".screenbg ul li:first").css("opacity","1");
	var index = 0;
	var t;
	var li = $(".screenbg ul li");	
	var number = li.size();
	function change(index){
		li.css("visibility","visible");
		li.eq(index).siblings().animate({opacity:0},3000);
		li.eq(index).animate({opacity:1},3000);
	}
	function show(){
		index = index + 1;
		if(index<=number-1){
			change(index);
		}else{
			index = 0;
			change(index);
		}
	}
	t = setInterval(show,8000);
	//根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>
<script type="text/javascript">
  		function change(){
  			var img = document.getElementById("img") ;
  			//加一个无意义的参数，目的就是让地址每次都发生变化
  			img.src = "/Library/AUcode?t=" + new Date().getTime() ;
  		}
  		
  	</script>
  	<script type="text/javascript">
  		function change1(){
  			var img = document.getElementById("img1") ;
  			//加一个无意义的参数，目的就是让地址每次都发生变化
  			img.src = "/Library/AUcode?t=" + new Date().getTime() ;
  		}
  		
  	</script>
  		<script type="text/javascript">
  		function change2(){
  			var img = document.getElementById("img2") ;
  			//加一个无意义的参数，目的就是让地址每次都发生变化
  			img.src = "/Library/AUcode?t=" + new Date().getTime() ;
  		}
  		
  	</script>
</head>

<body>
<div id="tab">
  <ul class="tab_menu">
    <li class="selected">用户登录</li>
    <li>管理员登录</li>
  </ul>
  <div class="tab_box"> 
     <!-- 用户登录 -->
    <div>
      <div class="stu_error_box"></div>
      <form action="${pageContext.request.contextPath}/UserServlet?method=login" method="post" class="stu_login_error">
        <div id="username">
          <label>帐&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" id="stu_username_hide" name="r_id" value="${cookie.r_id.value }"/>
          <br/>${pass}
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="r_password" value="${cookie.pass.value }" />
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="stu_code_hide" name="code"  value="" />         
          <a href = "javascript:change()"><img align="middle" src="${pageContext.request.contextPath}/AUcode" title="点击更换"  id = "img"/></a> </div>
      	 <br/>${code1} 
        <div id="remember">
          <input type="checkbox" name="remember" value="on"></input>
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit">登录</button>
        </div>
      </form>
    </div>
   
   
    
     <!-- 管理员登录 -->
    <div class="hide">
    <div class="sec_error_box"></div>
      <form action="${pageContext.request.contextPath}/ManagerServlet?method=login" method="post" class="sec_login_error">
        <div id="username">
          <label>用户：</label>
          <input type="text" id="sec_username_hide" name="m_id" value="${ cookie.m_id.value}" />
          <br/>${username}
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="sec_password_hide" name="m_password" value="${ cookie.password.value}" />
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="sec_code_hide" name="code"  value="" />
            <a href = "javascript:change2()"><img align="middle" src="${pageContext.request.contextPath}/AUcode" title="点击更换"  id = "img2"/></a>  
         
        </div>
       <br/>${code}
        <div id="remember">
          <input type="checkbox" name="remember" value="on"></input>
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit">登录</button>
        </div>
      </form>
    </div>
   
  </div>
</div>
<div class="bottom">©2014 Leting <a href="javascript:;" target="_blank">关于</a> <span>京ICP证030173号</span><img width="13" height="16" src="images/copy_rignt_24.png" /></div>
<div class="screenbg">
  <ul>
    <li><a href="javascript:;"><img src="images/jizhu03.png"></img></a></li>
    <li><a href="javascript:;"><img src="images/jizhu01.jpg"></img></a></li>
    <li><a href="javascript:;"><img src="images/jizhu03.png"></img></a></li>
     <li><a href="javascript:;"><img src="images/jizhu01.jpg"></img></a></li>
  </ul>
</div>
</body>
</html>
