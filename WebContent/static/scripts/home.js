var path = "http://localhost:8080/crm/";

layui.use([ 'layer','element'], function () {
 
  var $ = layui.jquery;
  var layer = layui.layer;
  var element=layui.element;
  
  //获取并显示用户名
  $.ajax({
      url: path + 'LoginServlet?action=selusername',
      xhrFields: {
          withCredentials: true
      },
		success : function(result) {
			var res = JSON.parse(result);//把json字符串转化成json对象
			var username = res.username;
			$('#username').text(username);
		}
  })
  
  $('.out').on('click',function(){
	  layer.confirm('确认退出？', {
		  btn: ['退出', '取消'] //可以无限个按钮
		  ,yes: function(index, layero){
			  $.ajax({
	              url: path + "LoginServlet?action=logout",
	              type: "POST",//方法类型
	              xhrFields: {
	                  withCredentials: true
	              },
	              success: function (result) {
	              	var res=JSON.parse(result);//把json字符串转化成JSON对象
	                  if (res.code== 0) {
	                	  window.location.href=path+"static/login.html";
	                  } 
	              }
	          })
			  layer.close(index);
		  }
		});
	  
  });
 
  
	  
});