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
				$.messager.alert('更新失败', result.msg, 'info');
				return;
			}
			$.messager.alert('提示', '更新成功', 'info', function() {
				window.location.reload();
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