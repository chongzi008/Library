<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div class="header"> 
    <nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="homepage.jsp">图书管理系统</a>
    </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
     
      <form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath }/BookServlet?method=findBook" method="post">
        <div class="form-group">
          <input type="text" class="form-control" name="bookName" placeholder="输入书名">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
         <ul class="nav navbar-nav">
        
      </ul>
       <ul class="nav navbar-nav">
       
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="list.jsp">分类浏览</a></li>
       <li><a href="readhistory.jsp">借阅记录</a></li>
        <li><a href="#" onclick="fun1()">联系我们</a></li>
        <li><a href="${basePath }WebPage/PageLogin.jsp">退出登录</a></li>
      </ul>
    </div>
  </div>
</nav>
</div>
<script type="text/javascript">
function fun1()
{
alert("电话联系：6666666666");
}
</script>


