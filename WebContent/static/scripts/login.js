var path = "http://localhost:8080/crm/";

$(function() {
	//登陆按钮点击事件
	$("#submit").on("click", function() {
		var username = $("#username").val();
		var password = $("#password").val();
		var check = $("#check").val();
		$.ajax({
			url : path + 'LoginServlet?action=login',
			type : "POST",// 方法类型
			xhrFields : {
				withCredentials : true
			},
			data : {
				username : username,
				password : password,
				check : check
			},
			success : function(data) {
				var res = JSON.parse(data);//把json字符串转化成json对象
				if (res.code == 0) {
					window.location.href = path + "static/customer.html";
				} else {
					alert(res.msg);
					window.location.reload();
				}
			},

		})
		return false;
	});

});
