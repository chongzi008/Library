<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加借阅记录</title>
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

   #borrowForm label.error {
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
 
        $("#borrowForm").validate({
           rules:{
       
        
           "r_id":{
           required:true,
           checkcId:true
           },
           "b_id":{
           required:true,
           checkcId:true
           },
          
        
           
           },
           messages:{
           "r_id":"请输入正确的类型，只能是数字",
           "b_id":"请输入正确的类型，只能是数字"
     
           }
        
        });
     
 
    
       $.validator.addMethod("checkcId", function(value, element, params) {
     
       var regax=/^\d+$/;
       if(regax.test(value)==false){
          return false;
       }else{
          return true;
       }
       
     });
     
   
    
     
});
   
     


</script>

<body>

<div class="container-fluid">
	
		<div>
			<form id="borrowForm" action="${pageContext.request.contextPath}/BorrowServlet" method="post">
					 <legend>添加借阅记录</legend> 
					 <input type="hidden" name="bor_id"/><br/>
                     <label>读者编号:</label> <input type="text" name="r_id"/><br/>
                      <label>书籍编号:</label> <input type="text" name="b_id"/><br/>
                    <input type="hidden" name="method" value="addBorrow"/>
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>
 