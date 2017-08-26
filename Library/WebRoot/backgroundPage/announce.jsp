<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/bootstrap-combined.min.css"></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/layoutit.css"></link>
</head>

<body>
<div class="container-fluid">
	
		<div>
			<div class="page-header">
				<h1>
					公告管理
				</h1>
			</div>
			
			<c:choose>
			  <c:when test="${ pagebean.pageBeanList[0]eq NULL }">
			    <h1 align="center" style="color: red">暂无公告</h1>
			  </c:when>
			
		<c:otherwise>
			  
			  <c:choose>
			<c:when test="${requestScope.flag == 'all'}">
			<c:set var="m" value="showFines" ></c:set>
			</c:when>
			<c:otherwise>
			<c:set var="m" value="findFines"></c:set>
			</c:otherwise> 
	    </c:choose>
			
				<table class="table">
				<thead>
					<tr>
						<th>
							公告标识id
						</th>
						<th>
							公告发布时间
						</th>
                        <th>
							公告内容
						</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="bean" items="${pagebean.pageBeanList}">
				
				<tr class="success">
						<td>${bean.a_id}</td>
						<td>${bean.a_time}</td>
						<td>${bean.a_content}</td>
						<td>
							<a href="${pageContext.request.contextPath}/AnnouncementServlet?method=updatehuixian&a_id=${bean.a_id}">编辑 </a><a href="<c:url value='/AnnouncementServlet'><c:param name='method' value='delete'/> <c:param name='a_id' value='${bean.a_id}'/></c:url>" onclick="return confirm('确定删除吗？')">删除</a>
						</td>
					</tr>
				</c:forEach>
					
				</tbody>
			</table>
			<div class="pagination">
				<ul>
				    <li>
						<label>第${pagebean.currentPage}页/共${pagebean.totalPage}页</label>
					</li>
				
					<li>
						 <a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce&pagecode=1">首页</a>
					</li>
					
						 <c:if test="${pagebean.currentPage>1}"> 
					<li>	                                             
                         <a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce&pagecode=${pagebean.currentPage-1}">上一页</a>
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
  <a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce&pagecode=${i}">[${i}]</a>
  </li>   
 

</c:forEach>					
					
						<c:if test="${pagebean.currentPage<pagebean.totalPage}">
						   <li>
                                <a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce&pagecode=${pagebean.currentPage+1}">下一页</a>
                           </li>
                        </c:if>
                           <li>
                                <a href="${pageContext.request.contextPath}/AnnouncementServlet?method=AllAnnounce&pagecode=${pagebean.totalPage}">尾页</a>
                           </li>   
				
			
				</ul>
			</div>
			 
			  </c:otherwise>
			</c:choose>
		
			</div>
		</div>
</body>
</html>
