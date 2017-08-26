<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>书籍详情</title>
		<%@include file="/common/header.jsp" %>
		<link rel="stylesheet" href="${basePath }css/book.css">
	</head>
	<body>
	    <%@include file="/common/top.jsp" %>
		
			
		  <form>
			<div class="_detail">
				<div class="_detail_good">
					<div class="_detail_good_img">
						<img src="${pageContext.request.contextPath}/${requestScope.book.b_image}" />
					</div>
					<div class="_detail_good_text">
						<div class="_detail_good_name">
							书本名称:<span>&nbsp;&nbsp;${requestScope.book.b_name}</span>
						</div>
						<div class="_detail_good_name">
							作者:<span>&nbsp;&nbsp;${requestScope.book.b_author}</span>
						</div>
						<div class="_detail_good_name">
							价钱:&nbsp;&nbsp;<span>¥${requestScope.book.b_price} </span>
						</div>
						
						<div class="_detail_good_description">
							书籍简介:&nbsp;&nbsp;<span>类型:${requestScope.book.type.t_name}
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 出版社:${requestScope.book.b_pubname}</span>
						</div>
						<div class="_detail_good_name">
							<p>书本存放地点:${requestScope.book.b_site}&nbsp;&nbsp;</p>
						</div>
						<div class="_detail_good_number">
							<div class="_detail_good_number_l">
							书本剩余总数:${requestScope.book.b_surplus}&nbsp;&nbsp;
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		
		<%@include file="/common/footer.jsp" %>
</body>
</html>
		
