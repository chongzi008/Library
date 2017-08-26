<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>读者信息编辑</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/bootstrap-combined.min.css"></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/layoutit.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/backgroundPage"/>/css/screen.css" />
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.validate.js" type="text/javascript"></script>
</head>
  
<style type="text/css">

   #readerForm label.error {
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
 
        $("#readerForm").validate({
           rules:{
  
           "r_username":{
           required:true,
           checkAuthor:true
           },
           "r_password":{
           required:true,
           checkPassword:true
           },
            "r_sex":{
           required:true
           
           }
           
         
           },
           messages:{
           "r_username":"请输入正确的格式,长度在1到15之间",
           "r_password":"请输入正确的格式,只能数字以及字符组成长度在6到20之间",
           }
        
        });

    
     
       $.validator.addMethod("checkAuthor", function(value, element, params) {
      
       if(value.length<1||value.length>15){
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
		
		
			<form id="readerForm" action="${pageContext.request.contextPath}/ReaderServlet" method="post">
					 <legend>读者编辑</legend> 
					 <input  type="hidden" name="r_id" value="${param.r_id }"/>
					 <input  type="hidden" name="method" value="editReader"/>
                     <label>读者姓名:</label><input id="r_username"  type="text" name="r_username"/> <br/>
                     <label>读者密码:</label><input type="text" name="r_password" /><br/>
                                                                      性别:<input type="radio" name="r_sex" value="男">男
                     <input type="radio" name="r_sex" value="女">女
                     <label  style="display: none" for="r_sex" class="error">请选择您的性别</label> <br/> 
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>

</body>

</html>
