$(function() {
	// 初始化表单
	$('#form-user-login').form({
		url: "verify",
		onSubmit: function(param) {
			return $(this).form('validate');
		}, success: function(data) {
			var result = eval('(' + data + ')');
			if (result.code != '0000') {
				alert(result.msg);
				return;
			}
			window.location.href = "";
		}
	});
	
	// 表单事件注册
	$('#btn-form-submit').on('click', function(e){
		$('#form-user-login').form('submit');
	});
	
	// 回车提交表单
	$("body").keydown(function(event) {
		if (event.keyCode == "13") {// keyCode=13是回车键
			$('#form-user-login').form('submit');
		}
	});
});