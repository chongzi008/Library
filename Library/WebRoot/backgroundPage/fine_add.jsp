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
           "r_id":{
           required:true
           
           } ,
           "b_id":{
            required:true
            
           },
           "f_num":{
            required:true,
            checkAuthor:true
           },
           "time":{
            required:true,
            
           },
           "f_fnum":{
           required:true
           },
            "fine":{
            required:true
            },
           },
           messages:{
           "b_id":"请输入正确的书籍编号",
           "r_id":"请输入正确的读者id",
           "f_num":"请输入正确的格式",
           "time":"请输入正确的格式",
           "f_fnum":"请输入正确的格式不能为空"
          
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
      
       if(value.length<1||value.length>30){
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
     
   
    
     
});
   
     


</script>

<body>

<div class="container-fluid">
	
		<div>
			<form id="bookForm" action="${pageContext.request.contextPath}/FineServlet" method="post">
					 <legend>添加罚款记录</legend> 
					 <input type="hidden" name="f_id"/><br/>
					 <input type="hidden" name="time"/><br/>
                     <label>罚款读者编号:</label> <input type="text" name="r_id"/><br/>
                     <label>图书编号:</label><input type="text" name="b_id"/><br/>
                     <input type="radio" name="fine" value="overdate">超期未还
                     <input type="radio" name="fine" value="loss">丢失书本
                     <label  style="display: none" for="fine" class="error">请选择你的罚款方式</label> <br/> 
                     <input type="hidden" name="method" value="addFine"/>
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>
 