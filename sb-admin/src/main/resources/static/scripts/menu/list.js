$(function() {
	// 初始化ADD窗口
	$('#win-add').window({
		title: '添加菜单', href: 'menu/add',
		closed: true, collapsible: false,
		minimizable: false, maximizable: false,
	    width: 400, height: 260,modal: true,
	    top: 0.5 * $(window).height() - 130,
	    left: 0.5 * $(window).width() - 200
	});
	
	// 初始化 EasyUI TreeGrid
	$("#tableData").treegrid({
		url: "menu/list", method: 'get',
		queryParams: {parentId: 0},
		idField: 'id', treeField: 'name', parentField: 'parentId',
		columns: [[
			{title:'菜单名称',field:'name',width:150,align:'left'},
			{title:'导航地址',field:'url',width:160,align:'center'},
			{title:'创建时间',field:'createDate',width:150,
				formatter : function(val, row, index) {
					if (val) {
						return new Date(val).format("yyyy-MM-dd hh:mm:ss");
					}
				}
			},
			{title:'更新时间',field:'updateDate',width:150,
				formatter : function(val, row, index) {
					if (val) {
						return new Date(val).format("yyyy-MM-dd hh:mm:ss");
					}
				}
			},
			{title:'描述信息',field:'descInfo',width:150,align:'left'},
			{title:'操作',field:'id',width:100,align:'center',
				formatter: function(val, row, index) {
					return '<a class="btnAction" href="javascript:void(0)" action="edit-' + val + '">编辑</a>';
				}
			}
		]], toolbar: [{
			text: "添加菜单", iconCls: 'icon-add',
			handler: function() {
				$('#win-add').window('open');
			}
		}, {
			text: "重新加载", iconCls: 'icon-reload',
			handler: function() {
				window.location.reload();
			}
		}], onBeforeExpand: function(row) {// 此处就是异步加载地所在
			if ("closed" == row.state && !row.children) {
				$(this).treegrid('options').queryParams = {parentId: row.id};
			}
			return true;
		}, loadFilter: function(result){
			var rows = result.data;
			for (var i = 0; i < rows.length; ++i) {
				if (rows[i].parentId == 0) {
					rows[i].state = 'closed';
				}
			}
			return rows;
		}, onLoadSuccess:function(data){  
	        $('a.btnAction').linkbutton({plain : false});  
	    }
	});
	
	// 注册按钮事件
	$('body').on('click','a.btnAction', function(e) {
		var $this = $(e.currentTarget);
		var action = $this.attr('action');
		var actionArr = action.split('-');
		var actionUrl = 'menu/' + actionArr.join('/');
		$('#win-edit').window({
			title: '编辑菜单', href: actionUrl,
			closed: false, collapsible: false,
			minimizable: false, maximizable: false,
		    width: 400, height: 260,modal: true,
		    top: 0.5 * $(window).height() - 130,
		    left: 0.5 * $(window).width() - 200
		});
	});
});