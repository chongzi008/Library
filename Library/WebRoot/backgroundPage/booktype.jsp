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
					书籍类型管理
				</h1>
			</div>
			
				<table class="table">
				<thead>
					<tr>
						<th>
							类型标识id
						</th>
					
                        <th>
							类型名称
						</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="bean" items="${pagebean.pageBeanList}">
				
				<tr class="success">
						<td>${bean.t_id}</td>
						<td>${bean.t_name}</td>
						<td>
							<a href="${pageContext.request.contextPath}/backgroundPage/type_edit.jsp?t_id=${bean.t_id}">编辑 </a><a href="<c:url value='/BookTypeServlet'><c:param name='method' value='typeDelete'/> <c:param name='t_id' value='${bean.t_id}'/></c:url>" onclick="return confirm('确定删除吗？')">删除</a>
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
						 <a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes&pagecode=1">首页</a>
					</li>
					
						 <c:if test="${pagebean.currentPage>1}"> 
					<li>	                                             
                         <a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes&pagecode=${pagebean.currentPage-1}">上一页</a>
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
  <a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes&pagecode=${i}">[${i}]</a>
  </li>   
 

</c:forEach>					
					
						<c:if test="${pagebean.currentPage<pagebean.totalPage}">
						   <li>
                                <a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes&pagecode=${pagebean.currentPage+1}">下一页</a>
                           </li>
                        </c:if>
                           <li>
                                <a href="${pageContext.request.contextPath}/BookTypeServlet?method=showTypes&pagecode=${pagebean.totalPage}">尾页</a>
                           </li>   
				
			
				</ul>
			</div>
			 
			
		
			</div>
		</div>
</body>
</html>
