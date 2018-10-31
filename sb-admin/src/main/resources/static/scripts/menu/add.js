$(function() {
	// 初始化复选框
	$('#menuPmId').combobox({
		onChange: function(newValue, oldValue) {
			if(newValue == oldValue) return;
			if (newValue == '0') {
				$('#menuLink').textbox({required:false});
			} else {
				$('#menuLink').textbox({required:true});
			}
		}
	});

	// 初始化表单
	$('#form-menu-add').form({
		url: "menu/addOrEdit",
		onSubmit: function(param) {
			return $(this).form('validate');
		}, success: function(data) {
			var result = eval('(' + data + ')');
			if (result.code != '0000') {
				$.messager.alert('添加失败', result.msg, 'info');
				return;
			}
			$.messager.confirm('提示', '添加成功，是否继续添加？', function(res){
				res || window.location.reload();
			});
		}
	});
	
	// 表单事件注册
	$('div#form-action').on('click', 'a', function(e){
		var $this = $(e.currentTarget);
		var id = $this.attr('id');
		if (id == 'btn-form-submit') {
			$('#form-menu-add').form('submit');
		} else {
			$('#form-menu-add').form('clear');
		}
	});
});