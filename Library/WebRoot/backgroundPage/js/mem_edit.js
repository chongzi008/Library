				
window.onload = function(){
  //当文档加载完毕的时候调用
    //该方法获取xmlrequest对象
    function createXMLHttpRequest(){
        try {
            return new XMLHttpRequest();//大多数浏览器
        } 
        catch (e) {
            try {
                return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
            } 
            catch (e) {
                try {
                    return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
                } 
                catch (e) {
                    throw e;
                }
            }
        }
    }
    
    var username=document.getElementById("username");
            username.onblur=function(){
	
	//开启ajax四步
    //得打xmlhttprequest对象
    var xhr = createXMLHttpRequest();
    //打开与服务器的链接
    xhr.open("GET", "${pageContext.request.contextPath}/ManagerServlet?method=checkMemEdit", true);
    //发送请求
    xhr.send(null);
    //取得服务器返回数据
    //首先我们要注册监听
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {//双重判断
			var data=xhr.responseText;
			alert(data);
			
			
		}
	};
	
	
};
    
    
 

};