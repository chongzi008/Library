<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加书籍</title>
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
           "b_name":{
           required:true,
           checkAccount:true
           } ,
           "b_type":{
            required:true
            
           },
           "b_author":{
            required:true,
            checkAuthor:true
           },
           "b_pubname":{
            required:true,
            checkAuthor:true
           },
           "b_price":{
           required:true
           },
           "b_surplus":{
           required:true,
           checkcId:true
           },
           "b_sum":{
           required:true,
           checkcId:true
           } ,
            "b_image":{
           required:true
          
           }
           ,
            "b_site":{
           required:true
           
           }
           
        
           
           },
           messages:{
           "b_type":"请输入正确的类型",
           "b_name":"请输入正确的书名，长度不低于1不大于30",
           "b_author":"请输入正确的作者名，长度不低于1不大于15",
           "b_pubname":"请输入正确的名称，长度不低于1不大于15",
           "b_price":"请输入正确的格式不能为空",
           "b_surplus":"请输入正确的格式,只能输纯数字整数组成",
           "b_sum":"请输入正确的格式,只能输纯数字整数组成",
           "b_image":"请输入正确的格式，有数字以及字符组成",
           "b_site":"请输入正确的格式，有数字以及字符组成"
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
			<form id="bookForm" action="${pageContext.request.contextPath}/BookServlet" method="post">
					 <legend>添加读者</legend> 
					 <input type="hidden" name="b_id"/><br/>
                     <label>图书名:</label> <input type="text" name="b_name" value="${book.b_name }"/><br/>
                     <label>图书类型:</label><input type="text" name="b_type" value="${msg }"/><br/>
                     <label>作者:</label><input type="text" name="b_author" value="${book.b_author }"/><br/>
                     <label>出版社名称:</label><input type="text" name="b_pubname" value="${book.b_pubname }"/><br/>
                     <label>价格:</label><input type="text" name="b_price" value="${book.b_price }"/><br/>
                  	<label>剩余数量:</label><input type="text" name="b_surplus" value="${book.b_surplus }"/><br/>
                  	 <label>总数:</label><input type="text" name="b_sum" value="${book.b_sum }"/><br/>
                 	<label>图书图片:</label><input type="text" name="b_image" value="${book.b_image }"/><br/>
                 	<label>图书位置:</label><input type="text" name="b_site" value="${book.b_site }"/><br/>
                    <input type="hidden" name="method" value="addBook"/>
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>
 