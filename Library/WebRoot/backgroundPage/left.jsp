<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/bootstrap-combined.min.css"></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/layoutit.css"></link>
<script type="text/javascript">
        function logout() {
            if(window.confirm('您确定要退出吗？')) {
                top.location = '#';
            }
        }      
	
    </script>
    <script type="text/javascript">
    $("document").ready(function(){ 
  	$(".container-fluid.row-fluid.span2.nav nav-list.nav-header.li").click(function(){
    $(".container-fluid.row-fluid.span2.nav nav-list.nav-header.li").removeClass("active");//首先移除全部的active
    $(this).addClass("active");//选中的添加acrive
 });
}); 
</script>
</head>

<body >
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2">
			<ul class="nav nav-list">
				<li >
					<a href="${pageContext.request.contextPath}/backgroundPage/main.jsp" target="mainframe">首页</a>
				</li>
				<li class="nav-header" >
					业务管理
				</li>
				
				<li>
					<a href="${pageContext.request.contextPath}/backgroundPage/announce_add.jsp" target="mainframe">发布公告</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce" target="mainframe">公告管理</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/backgroundPage/book_add.jsp" target="mainframe">添加图书</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/BookServlet?method=showBooks" target="mainframe">图书管理</a>
				</li>
					<li>
					<a href="${pageContext.request.contextPath}/backgroundPage/type_add.jsp" target="mainframe">添加分类</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes" target="mainframe">分类管理</a>
				</li>
				<li>
					<a href="fine_add.jsp" target="mainframe">添加罚款记录</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/FineServlet?method=showFines" target="mainframe">罚款管理</a>
				</li>
				  <li>
					<a href="${pageContext.request.contextPath}/backgroundPage/add_borrow.jsp" target="mainframe">添加借阅记录</a>
				</li>
                <li>
					<a href="${pageContext.request.contextPath}/BorrowServlet?method=showBorrows" target="mainframe">图书借阅管理</a>
				</li>
				<li class="nav-header" >
					人员管理
				</li>
				
				<li>
					<a href="mem_add.jsp" target="mainframe">添加读者</a>
				</li>
				<li>
					<a href="manger_add.jsp" target="mainframe">添加管理员</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/ManagerServlet?method=showManages" target="mainframe">管理员管理</a>
				</li>
                <li>
					<a href="<c:url value='/ReaderServlet?method=showReaders'/>" target="mainframe">读者管理</a>
				</li>
                <li class="divider">&nbsp;</li>
				<li><a href="javascript:void(0)" onclick="logout();">退出登录</a>
                </li>
				
			</ul>
		</div>
		
	</div>
</div>

</body>
</html>
