<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>借阅记录</title>
		<%@include file="/common/header.jsp" %>
		<link rel="stylesheet" href="${basePath }css/readhistory.css" />
				
	</head>
	<style type="text/css">
.pagination ul{
	margin-left:100px;
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
		
		<div class="container">
		<form action="#" method="post">
		   <div class="_cart">
		     <div class="_cart_title">
		        <h2>借阅记录</h2>
		     </div>
		     <div class="_cart_table">
		      
			       <table class="table table-responsive">
			          <tr class="warning" >
			            <td width="15%">书籍图片</td>
			            <td width="15">书名</td>
			            <td width="15%">借书日期</td>
			            <td width="25%">最后还书日期</td>
			            <td width="25%">是否还书</td>

			          </tr>
				          <tr>
				          	<td colspan="4">  </td>
				          </tr>
				          
				  <c:forEach items="${pagebean.pageBeanList}" var="borrow">
				  
				    <tr>
				          <td width="15%"><img alt="" src="${pageContext.request.contextPath}/${borrow.book.b_image}" width=100px height=100px></td>
				          <td width="15">${borrow.book.b_name}</td>
			              <td width="15%">${borrow.lend_date}</td>
			              <td width="25%">${borrow.back_date}</td>
			              <td width="25%">${borrow.isback}</td>
				       </tr>
				  </c:forEach>        
				     
				         
			     </table><br>
		     </div>
		   </div>
		   	   	  </form>
		   	   	  
		   	   	  
		   	   	  
<div  id ="pagebottom"  class="pagination">
			
				<ul>
				    <li>
						第${pagebean.currentPage}页/共${pagebean.totalPage}页
					</li>
				
					<li>
						 <a href="${pageContext.request.contextPath}/BorrowServlet?method=borrowRecord&pagecode=1">首页</a>
					</li>
					
						 <c:if test="${pagebean.currentPage>1}"> 
					<li>	                                             
 <a href="${pageContext.request.contextPath}/BorrowServlet?method=borrowRecord&pagecode=${pagebean.currentPage-1}">上一页</a>
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
  <a href="${pageContext.request.contextPath}/BorrowServlet?method=borrowRecord&pagecode=${i}">[${i}]</a>
  </li>   
 

 </c:forEach>					
					
						<c:if test="${pagebean.currentPage<pagebean.totalPage}">
						   <li>
 <a href="${pageContext.request.contextPath}/BorrowServlet?method=borrowRecord&pagecode=${pagebean.currentPage+1}">下一页</a>
                           </li>
                        </c:if>
                     <li>
                      <a href="${pageContext.request.contextPath}/BorrowServlet?method=borrowRecord&pagecode=${pagebean.totalPage}">尾页</a>
                     </li>   
				
			
				</ul>
			
</div>   	   	  
	   	</div>
	   	
		<%@include file="/common/footer.jsp" %>	
	</body>
</html>
