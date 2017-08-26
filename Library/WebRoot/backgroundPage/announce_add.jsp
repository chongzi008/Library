<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'news_update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value="/BackgroundPage"/>/css/bootstrap-combined.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/BackgroundPage"/>/css/layoutit.css">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/BackgroundPage"/>/css/screen.css" />
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.validate.js" type="text/javascript"></script>
  </head>
  
<style type="text/css">

   #newsForm label.error {
	margin-left: 10px;
	width: auto;
	font-style:"Courier New";
	font-size:18px;
	color:red;
	display: inline;
}

</style>
    <script type="text/javascript">

$(function(){
  
        $("#newsForm").validate({
           rules:{
           "Content":{
           required:true,
           checkContent:true
           } 
           
           },
           messages:{
           "Content":"长度不能小于1或者大于500"
           }
        
        });
     
       $.validator.addMethod("checkContent", function(value, element, params) {
     
       if(value.length<1||value.length>500){
          return false;
       }else{
          return true;
       }
       
     });
     
 
     
});
   
     


</script>
  <body>
  
  <div class="container-fluid">
	<div class="row-fluid">
		<div class="span10">
			<form id ="newsForm" action="${pageContext.request.contextPath}/AnnouncementServlet" method="post">
				
					 <legend>发布公告</legend> 
					 
                     <textarea rows="10" id="Content" name="Content" style="margin-left: 0px; margin-right: 0px; width: 865px;"></textarea>
                     <input type="hidden" name="method" value="addAnnounce"/><br>
                     <button class="btn" type="submit">提交</button>
				
			</form>
		</div>
	</div>
</div>

  </body>
</html>
