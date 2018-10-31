$(function() {
	// 初始化表单
	$('#form-pass-update').form({
		url: "account/pass/update",
		onSubmit: function(param) {
			if (param.newPass != param.repPass) {
				$.messager.alert('提示', '两次输入的密码不一致', 'error');
				return false;
			}
			return $(this).form('validate');
		}, success: function(data) {
			var result = eval('(' + data + ')');
			if (result.code != '0000') {
				$.messager.alert('操作提示', result.msg, 'error');
				return;
			}
			$.messager.alert('操作提示', '修改成功', 'info');
			window.location.href = "login";// 导航到登录页
		}
	});

	// 表单事件注册
	$('#btn-form-submit').on('click', function(e) {
		$('#form-pass-update').form('submit');
	});
});