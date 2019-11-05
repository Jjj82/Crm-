var path = "http://localhost:8080/crm/";

layui.use([ 'table', 'form', 'layedit', 'upload' ], function() {
	var layedit = layui.layedit;
	var table = layui.table;
	var form = layui.form;
	var upload = layui.upload;
	var $ = layui.jquery;
	var index;
	var ramarksData;
	var ramarksAddData;
	var ins1;
	
	// 渲染
	ins1 = table.render({
		elem : '#show-table',
		height : 'full-200',
		url : path + 'EmployeeServlet?action=sel',
		id : 'testReload',
		page : true,
		layout : [ 'prev', 'page', 'next', 'limit', 'refresh', 'skip' ],
		limit : 10,
		parseData : function(res) { // res 即为原始返回的数据
			return {
				"code" : res.code, // 解析接口状态
				"msg" : res.msg, // 解析提示文本
				"count" : res.count, // 解析数据长度
				"data" : res.data// 解析数据列表
			};
		},
		cols : [[ 
			{field : 'id',title : 'ID',width : 80,sort : true}, 
			{field : 'name',title : '员工名称',width : 120}, 
			{field : 'gender',title : '性别',width : 60}, 
			{field : 'telephone',title : '员工电话',minWidth : 120}, 
			{field : 'date',title : '日期',minWidth : 120}, 
			{field : 'address',title : '地址',minWidth : 120}, 
			{field : 'password',title : '密码',minWidth : 120}, 
			{field : 'role',title : '角色',minWidth : 90}, 
			{field : 'remarks',title : '备注',minWidth : 160}, 
			{fixed : '',title : '操作',width : 160,align : 'center',toolbar : '#barDemo'} 
		]],
		  defaultToolbar: ['filter', 'print', 'exports', {
			    title: '提示' // 标题
			    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
			    ,icon: 'layui-icon-tips' // 图标类名
			  }],
		even : true
	});
	// 渲染结束

	// 监听每一行的删除和编辑按钮
	table.on('tool(demo)', function(obj) {// 注：tool 是工具条事件名，test 是 table原始容器的属性 lay-filter="对应的值"
		var data = obj.data;// 获得当前行数据
		if (obj.event === 'del') {// obj.event; 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			layer.confirm('真的删除行么', function(index) {
				$.ajax({
					url : path + "EmployeeServlet?action=del",
					xhrFields : {
						withCredentials : true
					},
					data : {
						id : data.id,
					},
					success : function(result) {
						obj.del();
						layer.close(index);
						layer.msg('删除成功', {
							icon : 1
						});
						location.reload();
					}
				})
			});
		} else if (obj.event === 'edit') {
			layer.open({
				type : 1,
				title : '商品修改',
				shadeClose : false,
				shade : 0.8,
				maxmin : true,
				area : [ '75%', '70%' ],
				content : $("#update"), // 此处关联修改界面的id
			});
			
			// 修改界面富文本框开始
			layedit.set({
				uploadImage : {
					url : 'http://localhost:8080/crm/UploadServlet', // 接口url
					type : 'post' // 默认post
				}
			});
			ramarksData = layedit.build('remarks'); // 建立编辑器
			// 修改界面富文本框结束
			
			setFormValue(data); // 更新 lay-filter="test2" 所在容器内的全部 select元素的 状态
		}
		// 监听删除和编辑按钮结束

		// 编辑界面显示数据
		function setFormValue(data) {
			$('#id').val(data.id);
			$("#name").val(data.name);
			$('#gender').val(data.gender);
			$("#telephone").val(data.telephone);
			$("#date").val(data.date);
			$("#address").val(data.address);
			$("#password").val(data.password);
			$("#role").val(data.role);
			$("#remarks").val(data.remarks);
			alert(data.remarks);
		}
		// 编辑界面显示数据结束

		// 修改界面提交按钮
		form.on('submit(demo1)', function() {
			var id = parseInt($('#id').val());
			var name = $('#name').val();
			var gender = $('#gender').val();
			var telephone = $('#telephone').val();
			var date = $('#date').val();
			var address = $('#address').val();
			var password = $('#password').val();
			var role = $('#role').val();
			var remarks = layedit.getContent(ramarksData);
			$.ajax({
				url : path + 'EmployeeServlet?action=upd',
				type : "POST",// 方法类型
				xhrFields : {
					withCredentials : true
				},
				data : {
					id : id,
					name : name,
					gender : gender,
					telephone : telephone,
					date : date,
					address : address,
					password : password,
					role : role,
					remarks : remarks,
				},
				success : function(data) {
					var res = JSON.parse(data);// 把json字符串转化成json对象
					if (res.code == 0) {
						layer.msg(res.msg,function(){
							window.parent.location.reload();
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					} else {
						layer.msg('修改失败');
					}
				},
			})
			return false;
		});
		// 修改界面提交按钮结束
	});
	// 监听每一行的删除和编辑按钮结束
	
	// 搜索框和添加按钮的事件
    // 通过class选择元素，为元素添加点击事件
    $('.demoTable .layui-btn').on('click', function () {    	
      var type = $(this).data('type'); // 获取点击的元素的 data-type属性值data-type="reload"
      active[type] ? active[type].call(this) : '';        
    });
	
	// 搜索框和添加按钮的事件的实现
      var $ = layui.$, active = {
    	        increase: function () {
    	            layer.open({
    	                type: 1,    // 此处是1的话，content可以接受一个div作为内容,是2的话，需要一个独立网页
    	                title: '区域添加',
    	                shadeClose: false,
    	                shade: 0.7,
    	                maxmin: true,
    	                area: ['75%', '70%'],
    	                content: $("#add")
    	            });
    	            
    	          // 添加界面富文本框
    				layedit.set({
    					uploadImage : {
    						url : 'http://localhost:8080/crm/UploadServlet', // 接口url
    						type : 'post' // 默认post
    					}
    				});
    				ramarksAddData = layedit.build('remarksadd'); // 建立编辑器
    				// 添加界面富文本框结束
    	        },
		    	search : function() {
					var condition = $('#condition').val();
					var key = $('#key').val();
					table.reload('testReload', {
						url : path + 'EmployeeServlet?action=selbycon',
						page : {
							curr : 1
						// 重新从第 1 页开始
						},
						where : {
							condition : condition,
							key : key
						}
					});
		},
		daochu : function(obj) {
			table.exportFile(ins1.config.id, ins1.config.data, "xls");
		}
		,daochuAll : function(obj) {
			location.href=path + "ExcelServlet";
		}
	};
	
	// 添加保存
      form.on('submit(save-add)', function () { 
          var nameadd = $('#nameadd').val();
          var genderadd = $('#genderadd').val();
          var telephoneadd = $('#telephoneadd').val();            
          var dateadd = $('#dateadd').val();
          var addressadd = $('#addressadd').val();
          var passwordadd = $('#passwordadd').val();
          var roleadd = $('#roleadd').val();
          var remarksadd = layedit.getContent(ramarksAddData);
          $.ajax({
        	  url : path + 'EmployeeServlet?action=ins',
              type: "POST",// 方法类型
              xhrFields: {
                  withCredentials: true
              },
              data: {  
                  nameadd: nameadd,
                  genderadd : genderadd,
                  telephoneadd : telephoneadd,
                  dateadd: dateadd,                   
                  addressadd: addressadd,                 
                  passwordadd : passwordadd,                 
                  roleadd : roleadd,                 
                  remarksadd : remarksadd,                 
              },
              success: function (result) {
              	var res=JSON.parse(result);// 把json字符串转化成JSON对象
                  if (res.code == 0) {
                      layer.msg(res.msg, function(){
                    	  window.parent.location.reload();
                          var index = parent.layer.getFrameIndex(window.name);
                          parent.layer.close(index);                        	
                      }); 
                  } else {
                      layer.msg('添加失败');
                  }
              },
          })
          return false;
      });
    // 添加保存结束

});
