<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>书籍分类</title>
    <%@include file="/common/header.jsp" %>
    <link rel="stylesheet" href="${basePath }css/list.css" />
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
    <div >
    <table class="table">
          <tr>
          <td><a href="${pageContext.request.contextPath}/BookServlet?method=sortByA">A 古典文学</a></td>
          <td><a>B 哲学宗教</a></td>
          <td><a>C 社会科学总论</a></td>
          <td><a>D 政治、法律</a></td>
          <td><a>E 军事</a></td>
          <td><a>F 经济</a></td>
          <td><a>G 文化、科学、教育、体育</a></td>
          </tr>
          <tr>
          <td><a>H 语言、文字</a></td>
          <td><a>I 文学</a></td>
          <td><a>J 艺术</a></td>
          <td><a>K 历史、地理</a></td>
          <td><a>L 自然科学总论</a></td>
          <td><a>M 数理科学与化学</a></td>
          <td><a>N 天文学、地球科学</a></td>
          </tr>
          <tr>
          <td><a>O 生物科学</a></td>
          <td><a>P 医药、卫生</a></td>
          <td><a>Q 农业科学</a></td>
          <td><a>R 工业技术</a></td>
          <td><a>S 交通运输</a></td>
          <td><a>T 航空、航天</a></td>
          <td><a>U 环境科学、安全科学</a></td>
          </tr>
          <tr>
          <td><a>V 综合性图书</a></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          </tr>
     </table>
     <table class="table">
        <tr>
          <th>书本编号</th>
          <th>书名</th>
          <th>作者</th>
          <th>出版社</th>
          <th>价格</th>
          <th>图书总数</th>
          <th>图书可借数</th>
          <th>图书位置</th>
        </tr>
        
          <c:forEach items="${pagebean.pageBeanList}" var="book">
				  
				  <tr>
				       <td>${book.b_id}</td>
                       <td>${book.b_name}</td>
         			   <td>${book.b_author}</td>
         			   <td>${book.b_pubname}</td>
        			   <td>${book.b_price}</td>
         			   <td>${book.b_sum}</td>
         			   <td>${book.b_surplus}</td>
          			   <td>${book.b_site}</td>
				   </tr>
				  </c:forEach> 
        <tr>
   
        </tr>
          
   
     
     </table>
     
  
  <c:choose>
			  <c:when test="${ pagebean.pageBeanList[0]eq NULL }">
			  </c:when>
			  <c:otherwise>		   	   	  
<div  id ="pagebottom"  class="pagination">
			
				<ul>
				    <li>
						第${pagebean.currentPage}页/共${pagebean.totalPage}页
					</li>
				
					<li>
						 <a href="${pageContext.request.contextPath}/BookServlet?method=sortByA&pagecode=1">首页</a>
					</li>
					
						 <c:if test="${pagebean.currentPage>1}"> 
					<li>	                                             
 <a href="${pageContext.request.contextPath}/BookServlet?method=sortByA&pagecode=${pagebean.currentPage-1}">上一页</a>
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
  <a href="${pageContext.request.contextPath}/BookServlet?method=sortByA&pagecode=${i}">[${i}]</a>
  </li>   
 

 </c:forEach>					
					
						<c:if test="${pagebean.currentPage<pagebean.totalPage}">
						   <li>
 <a href="${pageContext.request.contextPath}/BookServlet?method=sortByA&pagecode=${pagebean.currentPage+1}">下一页</a>
                           </li>
                        </c:if>
                     <li>
                      <a href="${pageContext.request.contextPath}/BookServlet?method=sortByA&pagecode=${pagebean.totalPage}">尾页</a>
                     </li>   
				
			
				</ul>
</div>   	   	  
	   </c:otherwise>
	   </c:choose>	
     </div>
    <%@include file="/common/footer.jsp" %>
  </body>
</html>
