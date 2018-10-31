$(function() {
	// 初始化表单
	$('#form-user-apply').form({
		url: "user/apply",
		onSubmit: function(param) {
			return $(this).form('validate');
		}, success: function(data) {
			var result = eval('(' + data + ')');
			if (result.code != '0000') {
				$.messager.alert('操作提示', result.msg, 'error');
				return;
			}
			$.messager.alert('操作提示', '申请成功，系统已发送邮件至您注册的邮箱', 'info');
			window.location.href = "login";// 导航到登录页
		}
	});

	// 表单事件注册
	$('#btn-form-submit').on('click', function(e) {
		$('#form-user-apply').form('submit');
	});
});