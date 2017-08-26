<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书管理</title>
    <%@include file="/common/header.jsp" %>
    <link rel="stylesheet" href="${basePath }css/homepage.css" />
  </head>
  
  <body>
    <%@include file="/common/top.jsp" %>
      <div class="main">
<div class="left">
 <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
    <li data-target="#carousel-example-generic" data-slide-to="5"></li>
  </ol>
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="image/library01.jpg" alt="...">
    </div>
    <div class="item">
      <img src="image/library02.jpg" alt="...">
    </div>
    <div class="item">
      <img src="image/library03.jpg" alt="...">
    </div>
    <div class="item">
      <img src="image/library04.jpg" alt="...">
    </div>
    <div class="item">
      <img src="image/library05.jpg" alt="...">
    </div>
    <div class="item">
      <img src="image/library06.jpg" alt="...">
    </div>
  </div>

  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>

 <div class="right">
   <p class="show">公告栏：</p>
   <p class="context">${announcement.a_content }</p>
 </div>
 
 
</div>




<div class="advicebook">
<!--  <div class="_title">推荐书籍：
</div>-->
<div class="_good" >
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[0].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[0].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[0].b_name}</div>
</div>
<div class="_good">
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[1].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[1].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[1].b_name}</div>
</div>
<div class="_good">
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[2].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[2].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[2].b_name}</div>
</div>
<div class="_good">
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[3].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[3].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[3].b_name}</div>
</div>
<div class="_good">
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[4].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[4].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[4].b_name}</div>
</div>
<div class="_good">
<a href="${pageContext.request.contextPath }/BookServlet?method=loadOneBook&bid=${requestScope.pagebean.pageBeanList[5].b_id}"><img  src="${pageContext.request.contextPath }/${requestScope.pagebean.pageBeanList[5].b_image}" alt="Ballade" width="160" height="160"></a>
  <div class="desc">${requestScope.pagebean.pageBeanList[5].b_name}</div>
</div>
</div> 


    <%@include file="/common/footer.jsp" %>
 
 
  </body>
</html>
