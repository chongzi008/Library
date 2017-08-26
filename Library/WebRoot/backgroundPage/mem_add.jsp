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
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/backgroundPage"/>/css/screen.css" />
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.validate.js" type="text/javascript"></script>
</head>
  
<style type="text/css">

   #memForm label.error {
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
 
        $("#memForm").validate({
           rules:{
           "username":{
           required:true,
           checkAccount:true
           } ,
           "password":{
            required:true,
            checkPassword:true
           },
           "sex":{
            required:true
            },
           
           },
           messages:{
           "username":"请输入正确的书名，长度不低于1不大于15",
           "password":"请输入正确的格式，有数字以及字符组成长度在6到20",
           }
        
        });
     
      //value元素的值 element 元素本身 param 就是那个true 或者false那个地填的值
     $.validator.addMethod("checkTitle", function(value, element, params) {
      
       if(value.length<1||value.length>10){
          return false;
       }else{
          return true;
       }
       
     });
     
      $.validator.addMethod("checkAuthor", function(value, element, params) {
      
       if(value.length<1||value.length>15){
          return false;
       }else{
          return true;
       }
       
     });
     
     //账号的插件验证
       $.validator.addMethod("checkAccount", function(value, element, params) {
      
       if(value.length<1||value.length>15){
          return false;
       }else{
          return true;
       }
       
     });
     
         $.validator.addMethod("checkKeyWord", function(value, element, params) {
      
       if(value.length<1||value.length>10){
          return false;
       }else{
          return true;
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
     
     $.validator.addMethod("checkPassword", function(value, element, params) {
       var reg = /^\w{6,20}$/; 
       if(reg.test(value)==false){
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
			<form id="memForm" action="${pageContext.request.contextPath}/ReaderServlet" method="post">
					 <legend>添加读者</legend> 
					 <input type="hidden" name="id" /> <br/>
                     <label>用户名</label><input type="text" name="username" /> <br/>
                     <label>密码</label><input type="text" name="password"/>  <br/>
                                                           性别:<input type="radio" name="sex" value="男">男
                     <input type="radio" name="sex" value="女">女
                     <label  style="display: none" for="sex" class="error">请选择您的性别</label> <br/> 
                    <input type="hidden" name="method" value="readerAdd"/>
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>
 