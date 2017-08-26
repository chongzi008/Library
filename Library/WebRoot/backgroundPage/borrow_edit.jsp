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
         
            "isback":{
           required:true
           
           }
           
         
           },
           messages:{
          
           }
        });
       $.validator.addMethod("checkDate", function(value, element, params) {
       var reg = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}$/; 
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
		
		
			<form id="borrowForm" action="${pageContext.request.contextPath}/BorrowServlet" method="post">
					 <legend>读者编辑</legend> 
					 <input  type="hidden" name="bor_id" value="${param.bor_id }"/>
					 <input  type="hidden" name="bid" value="${param.bid }"/>
					 <input  type="hidden" name="rid" value="${param.rid }"/>
					 <input  type="hidden" name="method" value="editBorrow"/>
					 <input  type="hidden" name="lendtime" value="${param.lend_date }"/>
                     <h3>是否归还:</h3><br/> <input type="radio" name="isback" value="是">是
                     <input type="radio" name="isback" value="否">否
                     <label  style="display: none" for="isback" class="error">请选择归还状态</label> <br/> 
                     <button class="btn" type="submit">提交</button>
			</form>
		</div>
	</div>

</body>

</html>
