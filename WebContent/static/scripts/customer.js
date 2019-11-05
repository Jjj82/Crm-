var path = "http://localhost:8080/crm/";

layui.use(['table', 'form', 'layedit', 'upload'], function () {
	
    var layedit = layui.layedit;
    var table = layui.table;
    var form = layui.form;
    var upload = layui.upload;
    var $ = layui.jquery;
    var index; 
    
    
    //渲染
    table.render({
        elem: '#idTest'
        , height: 'full-200'
        , url: path + 'CustomerServlet?action=sel'
        , id: 'testReload'
        , page: true
        , layout: ['prev', 'page', 'next', 'limit', 'refresh', 'skip']
        , limit: 10
        , cols: [[
            {field: 'id', title: 'ID', width: 80, sort: true}
            ,{field: 'ygid', title: '员工编号', width: 120}
            ,{field: 'name', title: '客户姓名', minWidth: 150}
            ,{field: 'telephone', title: '联系电话', minWidth: 160}
            ,{field: 'date', title: '日期', minWidth: 160}
            ,{field: 'state', title: '状态', minWidth: 160}
            ,{field: 'remarks', title: '备注', minWidth: 160}
            , { fixed: '', width: 178, align: 'center', toolbar: '#barDemo' }
        ]]
          ,even: true
        });
  //监听
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: path + 'CustomerServlet?action=del',
                    xhrFields: {
                        withCredentials: true
                    },
                    data: {
                        id: data.id,
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
                type: 1,
                title: '商品修改',
                shadeClose: false,
                shade: 0.8,
                maxmin: true,
                area: ['75%', '70%'],
                content: $("#update"),
            });
            setFormValue(data);
        }
        //修改界面显示数据
        function setFormValue(data){
        	$('#id').val(data.id);
        	$('#ygid').val(data.ygid);
        	$('#name').val(data.name);
        	$('#telephone').val(data.telephone);
        	$('#date').val(data.date);
        	$('#state').val(data.state);
        	$('#remarks').val(data.remarks);
        }
        form.on('submit(save-update)', function() {
        	var id = parseInt($('#id').val());
    		var ygid = parseInt($('#ygid').val());
    		var name = $('#name').val();
    		var telephone = $('#telephone').val();
    		var date = $('#date').val();
    		var state = $('#state').val();
    		var remarks = $('#remarks').val();
    		$.ajax({
    			url : path + 'CustomerServlet?action=upd',
    			type : "POST",// 方法类型
    			xhrFields : {
    				withCredentials : true
    			},
    			data : {
    				id : id,
    				ygid : ygid,
    				name : name,
    				telephone : telephone,
    				date : date,
    				state : state,
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
        
        
        
    });

    

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
  	            });
  	        },
		    search : function() {
					var condition = $('#condition').val();
					var key = $('#key').val();
					table.reload('testReload', {
						url : path + 'CustomerServlet?action=selbycon',
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
        var ygidadd = $('#ygidadd').val();
        var nameadd = $('#nameadd').val();
        var telephoneadd = $('#telephoneadd').val();            
        var dateadd = $('#dateadd').val();
        var stateadd = $('#stateadd').val();
        var remarksadd = $('#remarksadd').val();
        $.ajax({
      	  url : path + 'CustomerServlet?action=ins',
            type: "POST",// 方法类型
            xhrFields: {
                withCredentials: true
            },
            data: {  
            	ygidadd : ygidadd,
          	  	nameadd: nameadd,
                telephoneadd: telephoneadd,
                dateadd: dateadd,                   
                stateadd: stateadd,                 
                remarksadd: remarksadd,                 
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
                    layer.msg(res.msg);
                }
            },
        })
        return false;
    });
  //添加保存结束












});

