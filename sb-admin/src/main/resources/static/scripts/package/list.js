$(function() {
	// 初始化 EasyUI DataGrid
	$('#tableData').datagrid({
		rownumbers:true, striped:true,
		showHeader:true, showFooter:true,
		singleSelect:true, //selectOnCheck: true, checkOnSelect: true,
		columns:[[
			{title:'文件名称',field:'packName',width:100,align:'center'},
			{title:'系统类型',field:'appType',width:100,align:'center',
				formatter: function(val, row, index) {
					return val == '0' ? 'Android' : 'IOS';
				}	
			},
			{title:'安装包版本',field:'version',width:100,align:'center'},
			{title:'上传时间',field:'uploadTime',width:150,align:'center',
				if (val) {
					return new Date(val).format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{title:'更新时间',field:'updateTime',width:120,align:'center',
				if (val) {
					return new Date(val).format("yyyy-MM-dd hh:mm:ss");
				}	
			},
			{title:'状态',field:'status',width:50,align:'center',
				formatter: function(val, row, index) {
					return val == '0' ? '初始' : '上线';
				}
			},
			{title:'操作',field:'linkurl',width:60,align:'center',
				formatter: function(val, row, index) {
					return '<a href="' + val + '">下载</a>';
				}
			}
		]], toolbar: [{
			text: "上传安装包", iconCls: 'icon-redo',
			handler: function() {
				window.location.href = 'app/package/upload';
			}
		}, '-', {
			text: "刷新页面", iconCls: 'icon-reload',
			handler: function() {
				window.location.reload();
			}
		}]
	});

	// 查询与显示数据
	function query () {
		$("#tableData").datagrid('loading');
		$.ajax({
			url:"app/package/list", dataType:"json",
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