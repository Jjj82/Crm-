var path = "http://localhost:8080/crm/";

layui.use([ 'table', 'form', 'layedit', 'upload' ], function() {
	var layedit = layui.layedit;
	var table = layui.table;
	var form = layui.form;
	var upload = layui.upload;
	var $ = layui.jquery;
	var index;
	
	// 渲染
	table.render({
		elem : '#show-table',
		height : 'full-200',
		url : path + 'RegionServlet?action=sel',
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
			{field : 'customerid',title : '客户编号',width : 80,sort : true}, 
			{field : 'name',title : '区域名称',width : 120}, 
			{field : 'telephone',title : '区域电话',minWidth : 150}, 
			{field : 'date',title : '日期',minWidth : 160}, 
			{field : 'charge',title : '状态',minWidth : 160}, 
			{field : 'remarks',title : '备注',minWidth : 160}, 
			{fixed : '',title : '操作',width : 178,align : 'center',toolbar : '#barDemo'} 
		]],
		even : true
	});
	// 渲染
	


	//监听每一行的删除和编辑按钮
	table.on('tool(demo)', function(obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		var data = obj.data;//获得当前行数据
		if (obj.event === 'del') {//obj.event; 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			layer.confirm('真的删除行么', function(index) {
				$.ajax({
					url : path + "RegionServlet?action=del",
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
				content : $("#update"), //此处关联修改界面的id
			});
			setFormValue(data); //更新 lay-filter="test2" 所在容器内的全部 select元素的 状态
			//form.render('select', 'updateA');
		}
		// 监听删除和编辑按钮结束

		// 显示数据
		function setFormValue(data) {
			$('#id').val(data.id);
			$('#customerid').val(data.customerid);
			$("#name").val(data.name);
			$("#charge").val(data.charge);
			$("#telephone").val(data.telephone);
			$("#date").val(data.date);
			$("#remarks").val(data.remarks);
		}
		// 显示数据结束

		//修改界面提交按钮
		form.on('submit(demo1)', function() {
			var id = parseInt($('#id').val());
			var customerid = parseInt($('#customerid').val());
			var name = $('#name').val();
			var telephone = $('#telephone').val();
			var date = $('#date').val();
			var charge = $('#charge').val();
			var remarks = $('#remarks').val();
			$.ajax({
				url : path + 'RegionServlet?action=upd',
				type : "POST",// 方法类型
				xhrFields : {
					withCredentials : true
				},
				data : {
					id : id,
					customerid : customerid,
					name : name,
					telephone : telephone,
					date : date,
					charge : charge,
					remarks : remarks,
				},
				success : function(data) {
					var res = JSON.parse(data);//把json字符串转化成json对象
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
		//修改界面提交按钮结束
	});
	//监听每一行的删除和编辑按钮结束
	
	//搜索框和添加按钮的事件
    //通过class选择元素，为元素添加点击事件
    $('.demoTable .layui-btn').on('click', function () {    	
      var type = $(this).data('type'); //获取点击的元素的 data-type属性值 data-type="reload"
      active[type] ? active[type].call(this) : '';        
    });
	
	//搜索框和添加按钮的事件的实现
      var $ = layui.$, active = {
    	        increase: function () {
    	            layer.open({
    	                type: 1,    //此处是1的话，content可以接受一个div作为内容,  是2的话，需要一个独立网页
    	                title: '区域添加',
    	                shadeClose: false,
    	                shade: 0.7,
    	                maxmin: true,
    	                area: ['75%', '70%'],
    	                content: $("#add")
    	                //content: 'commodityListAdd2.html' //如果是2的话，需要单独的页面
    	            });
    	        },
		    	search : function() {
					var condition = $('#condition').val();
					var key = $('#key').val();
					table.reload('testReload', {
						url : path + 'RegionServlet?action=selbyid',
						page : {
							curr : 1
						// 重新从第 1 页开始
						},
						where : {
							condition : condition,
							key : key
						}
		
					});
		}
	};
	
	// 添加保存
      form.on('submit(save-add)', function () { 
          var customerid = $('#customeridadd').val();
          var name = $('#nameadd').val();
          var telephone = $('#telephoneadd').val();            
          var date = $('#dateadd').val();
          var charge = $('#chargeadd').val();
          var remarks = $('#remarksadd').val();
          $.ajax({
        	  url : path + 'RegionServlet?action=ins',
              type: "POST",// 方法类型
              xhrFields: {
                  withCredentials: true
              },
              data: {  
            	  customerid : customerid,
                  name: name,
                  telephone: telephone,
                  date: date,                   
                  charge: charge,                 
                  remarks: remarks,                 
              },
              success: function (result) {
              	var res=JSON.parse(result);//把json字符串转化成JSON对象
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
    //添加保存结束
      
      
  
      

      
    
      
    

});
