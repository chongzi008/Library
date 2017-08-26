<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书编辑</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/bootstrap-combined.min.css"></link>
<link rel="stylesheet" type="text/css" href="<c:url value="/backgroundPage/"/>css/layoutit.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/backgroundPage"/>/css/screen.css" />
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/backgroundPage/jquery/jquery.validate.js" type="text/javascript"></script>
</head>
  
<style type="text/css">

   #bookeditForm label.error {
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
 
        $("#bookeditForm").validate({
           rules:{
  
           "b_surplus":{
           required:true,
           checkcId:true
           },
           "b_sum":{
           required:true,
           checkcId:true
           },
            "b_site":{
           required:true
           
           }
      
           
           },
           messages:{
           "b_surplus":"请输入正确的格式,只能输纯数字整数组成",
           "b_sum":"请输入正确的格式,只能输纯数字整数组成",
           "b_image":"请输入正确的格式，有数字以及字符组成",
           "b_site":"请输入正确的格式，有数字以及字符组成"
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
		
			<form id="bookeditForm"action="${pageContext.request.contextPath}/BookServlet" method="post">
					 <legend>图书编辑</legend> 
                     <label>剩余数量</label>
                     <input type="text" name="b_surplus" value="${book.b_surplus}"/>
                     <label>总数</label>
                     <input type="text" name="b_sum" value="${book.b_sum}" />
                     <label>图书位置</label>
                     <input type="text" name="b_site" value="${book.b_site}" />
                     <label class="checkbox"></label>
                     <input type="hidden" name="b_id" value="${param.b_id }" />
                     <input type="hidden" name="method" value="bookEdit" />
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>