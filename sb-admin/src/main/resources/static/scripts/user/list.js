$(function() {
	// 初始化 EasyUI DataGrid
	$('#tableData').datagrid({
		rownumbers:true, striped:true,
		showHeader:true, showFooter:true,
		singleSelect:true, //selectOnCheck: true, checkOnSelect: true,
		columns:[[
			{field:'ck',checkbox:true },
			{title:'用户名称',field:'username',width:100,align:'center'},
			{title:'个性昵称',field:'nickname',width:100,align:'center'},
			{title:'注册邮箱',field:'email',width:150,align:'center'},
			{title:'注册时间',field:'regtime',width:120,align:'center'},
			{title:'账号状态',field:'status',width:120,align:'center',
				formatter: function(val, row, index) {
					return val == 2 ? '已冻结' : '正常';
				}
			},
			{title:'操作',field:'id',width:60,align:'center',
				formatter: function(val, row, index) {
					return '<a href="user/detail/' + val + '">详情</a>';
				}
			}
		]], toolbar: [{
			text: "删除", iconCls: 'icon-empty',
			handler: function() {
				userAction('delete');
			}
		}, '-',{
			text: "冻结", iconCls: 'icon-lock',
			handler: function() {
				userAction('freeze');
			}
		}, {
			text: "解冻", iconCls: 'icon-tip',
			handler: function() {
				userAction('unfreeze');
			}
		}, '-', {
			text: "导出", iconCls: 'icon-redo',
			handler: function() {
				window.location.href = 'user/download';
			}
		}, '-', {
			text: "刷新", iconCls: 'icon-reload',
			handler: function() {
				window.location.reload();
			}
		}]
	});
	
	function userAction(action) {
		var selectRow = $('#tableData').datagrid('getSelected');
		if (null == selectRow) {
			$.messager.alert('提示', '未选择', 'info');
			return;
		}
		$.ajax({
			dataType:"json", type: 'post',
			url:"user/" + action + '/' + selectRow.id,
			success:function(result) {
				if (result.code == '0000') {
					$.messager.alert('提示', '操作成功', 'error', function() {
						window.location.reload();
					});
				} else {
					$.messager.alert('提示', result.msg, 'error');
				}
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('提示', "网络不佳，请稍后再试", 'error');
			}
		});
	}

	// 查询与显示数据
	function query () {
		$("#tableData").datagrid('loading');
		$.ajax({
			url:"user/list", dataType:"json",
			success:function(result) {
				if (result.code == '0000') {
					$('#tableData').datagrid({data:result.data});
				} else {
					$.messager.alert('ERROR',result.msg,'error');
				}
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('ERROR',"网络不佳，请稍后再试",'error');
			},complete:function(){
				$("#tableData").datagrid('loaded');
			}
		});
	}
	// 初始化
	query();
});