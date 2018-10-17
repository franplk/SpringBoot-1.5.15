$(function() {
	// 初始化 EasyUI DataGrid
	$('#tableData').datagrid({
		rownumbers:true, striped:true,
		singleSelect:true, showHeader:true, showFooter:true,
		columns:[[
			{title:'分组名称',field:'jobGroup',width:100,align:'center'},
			{title:'任务名称',field:'jobName',width:100,align:'center'},
			{title:'任务表达式',field:'cronExps',width:100,align:'center'},
			{title:'功能描述',field:'funcDesc',width:200,align:'center'},
			{title:'上次触发时间',field:'lastTriggerTime',width:120,align:'center'},
			{title:'下次触发时间',field:'nextTriggerTime',width:120,align:'center'},
			{title:'操作',field:'state',width:200,align:'center',
				formatter : function(val, row, index) {
					var html = '';
					var jobName = row.jobName;
					var jobGroup = row.jobGroup;
					html += '<a class="btnAction" href="javascript:void(0)" action="launch"'
						+ 'jobName="' + jobName + '" jobGroup="' + jobGroup + '">启动</a>';
					html += '<a class="btnAction" href="javascript:void(0)" action="cease"'
						+ 'jobName="' + jobName + '" jobGroup="' + jobGroup + '">停止</a>';
					html += '<a class="btnAction" href="javascript:void(0)" action="trigger"'
						+ 'jobName="' + jobName + '" jobGroup="' + jobGroup + '">立即触发</a>';
					return html;
				}
			}
		]], toolbar: [{
			text: "重新加载", iconCls: 'icon-reload',
			handler: function() { window.location.reload();}
		}], onLoadSuccess:function(data){  
	        $('a.btnAction').linkbutton({plain : false});  
	    }
	});
	
	$('body').on('click','a.btnAction', function(e) {
		var $this = $(e.currentTarget);
		var action = $this.attr('action');
		var jobName = $this.attr('jobName');
		var jobGroup = $this.attr('jobGroup');
		$.ajax({
			type:"POST", dataType:"json", url:"schedule/" + action,
			data:{"jobName":jobName, "jobGroup":jobGroup},
			success:function(result) {
				if (result.code == '0000') {
					$.messager.alert('操作提示','执行成功','info');
				} else {
					$.messager.alert('操作提示',result.msg,'error');
				}
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('操作提示',"网络不佳，请稍后再试",'warning');
			},complete:function(){
				$("#tableData").datagrid('loaded');
			}
		});
	});

	// 查询与显示数据
	function query () {
		$("#tableData").datagrid('loading');
		$.ajax({
			url:"schedule/list", dataType:"json",
			success:function(result) {
				if (result.code == '0000') {
					$('#tableData').datagrid({
						data:result.data
					});
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