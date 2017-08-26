<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员添加</title>
<script type="text/javascript" src="<c:url value="/backgroundPage/"/>js/test.js"></script>
<script type="text/javascript" src="<c:url value="/backgroundPage/"/>jquery/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/bootstrap-combined.min.css"></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/layoutit.css"></link>
<!--jquery  -->
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/backgroundPage"/>/css/screen.css" />
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.validate.js" type="text/javascript"></script>
</head>
  
<style type="text/css">

   #bookForm label.error {
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
 
        $("#bookForm").validate({
           rules:{
         
           "t_name":{
           required:true
           }
  
           },
           messages:{
       
           "t_name":"请输入正确的格式不能为空,"
          
           }
        
        });
     
  

   
    
     
});
   
     


</script>

<body>

<div class="container-fluid">
	
		<div>
			<form id="bookForm" action="${pageContext.request.contextPath}/BookTypeServlet" method="post">
					 <legend>修改分类名称</legend> 
					 <input type="hidden" name="t_id" value="${param.t_id }"/><br/>
                     <label>分类名称:</label><input type="text" name="t_name"/><br/>
                    <input type="hidden" name="method" value="typeEdit"/>
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>
 