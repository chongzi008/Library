<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查询结果</title>
		<%@include file="/common/header.jsp" %>
		<link rel="stylesheet" href="${basePath }css/readhistory.css" />
				
	</head>
	<style type="text/css">
.pagination ul{
	margin-left:0px;
	list-style:none;
	}
.pagination ul li{
			float:left;}
.pagination ul li a{
	text-align:center;
	width:60px;		
	height:23px;
	margin-bottom:1px;
	display:block;
	text-decoration:none;
		}
.pagination ul li a:hover{
			background-color:#CCC;}
			
	</style>
	
	<body>
		<%@include file="/common/top.jsp" %>
		
		<c:choose>
			  <c:when test="${ pagebean.pageBeanList[0]eq NULL }">
			    <h1 align="center" style="color: red">不好意思，你查询的结果，不存在</h1>
			  </c:when>
			
		<c:otherwise>
		
		<div class="container">
		   <div class="_cart">
		     <div class="_cart_title">
		        <h2>查询结果</h2>
		     </div>
		     <div class="_cart_table">
		      
			       <table class="table table-responsive">
			      
				        <tr>
				          <td width="10%"><h3>书本图片</h3></td>
				          <td width="10%"><h3>书名</h3></td>
				          <td width="10%"><h3>作者</h3></td>
				          <td width="10%"><h3>出版社</h3></td>
				          <td width="10%"><h3>价格</h3></td>
				          <td width="10%"><h3>剩余数量</h3></td>
				          <td width="10%"><h3>图书位置</h3></td>
				          
				       </tr>  
				   
				       <c:forEach var="book" items="${pagebean.pageBeanList}">
				        <tr>
				          <td width="10%"><img alt="" src="${pageContext.request.contextPath}/${book.b_image}" width=100px height=100px></td>
				          <td width="10%">${book.b_name}</td>
				          <td width="10%">${book.b_author}</td>
				          <td width="10%">${book.b_pubname}</td>
				          <td width="10%">${book.b_price}</td>
				          <td width="10%">${book.b_surplus}</td>
				          <td width="10%">${book.b_site}</td>
				         </tr>
				       
				       </c:forEach>
				          
				
				         
			     </table><br>
		     </div>
		   </div>
		   	   
   	   	  
<div  id ="pagebottom"  class="pagination">

 <c:choose>
 <c:when test="${pagebean.totalPage<=1}">
 </c:when>
 <c:otherwise>
 
 <ul>
				    <li>
						<label>第${pagebean.currentPage}页/共${pagebean.totalPage}页</label>
					</li>
				
					<li>
						 <a href="${pageContext.request.contextPath}/BookServlet?method=findBook&bookName=${condition}&pagecode=1">首页</a>
					</li>
					
						 <c:if test="${pagebean.currentPage>1}"> 
					<li>	                                             
                         <a href="${pageContext.request.contextPath}/BookServlet?method=findBook&bookName=${condition}&pagecode=${pagebean.currentPage-1}">上一页</a>
                    </li>
                        </c:if> 
                        		
					<!-- 通过计算得到当前的页码表开始和结束数字 -->
 <c:choose>
   <c:when test="${pagebean.totalPage<=10}">
     <c:set var="begin" value="1"></c:set>
     <c:set var="end" value="${pagebean.totalPage}"></c:set>
   </c:when>
   <c:otherwise>
    <c:set var="begin" value="${pagebean.currentPage-5}"></c:set>
    <c:set var="end" value="${pagebean.currentPage+4}"></c:set>
   <!--注意头溢出  -->
   <c:if test="${begin <1}">
   <c:set var="begin" value="${1}"></c:set>
    <c:set var="end" value="${10}"></c:set>
   </c:if>
   <!--尾溢出也要处理  -->
   <c:if test="${end>pagebean.totalPage}">
    <c:set var="end" value="${pagebean.totalPage}"></c:set>
    <c:set var="begin" value="${pagebean.totalPage-9}"></c:set>
   </c:if>
   </c:otherwise>
 </c:choose>
<!--这里根据计算的页码数开始插入-->
<c:forEach var="i" begin="${begin}" end="${end}">

  <li>
  <a href="${pageContext.request.contextPath}/BookServlet?method=findBook&bookName=${condition}&pagecode=${i}">[${i}]</a>
  </li>   
 

</c:forEach>					
					
						<c:if test="${pagebean.currentPage<pagebean.totalPage}">
						   <li>
                                <a href="${pageContext.request.contextPath}/BookServlet?method=findBook&bookName=${condition}&pagecode=${pagebean.currentPage+1}">下一页</a>
                           </li>
                        </c:if>
                           <li>
                                <a href="${pageContext.request.contextPath}/BookServlet?method=findBook&bookName=${condition}&pagecode=${pagebean.totalPage}">尾页</a>
                           </li>   
				
			
				</ul>
			
 
 </c:otherwise>
 </c:choose>
			
				
</div>   	   	  
	   	</div>
	   		<%@include file="/common/footer.jsp" %>	
	   		  </c:otherwise>
			</c:choose>
		
	
	</body>
</html>
