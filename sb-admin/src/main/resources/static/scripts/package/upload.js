$(function() {
	// 初始化表单
	$('#form-package-add').form({
		url: "app/package/upload",
		onSubmit: function(param) {
			return $(this).form('validate');
		}, success: function(data) {
			var result = eval('(' + data + ')');
			if (result.code != '0000') {
				alert(result.msg);
				return;
			}
			window.location.href = "app/package";
		}
	});
	
	// 表单事件注册
	$('#btn-form-submit').on('click', function(e){
		if ($(this).linkbutton('options').disabled) {
			return;
		}
		$('#form-package-add').form('submit');
	});
	
	$('#btn-retry-file').on('click', function(e){
		$('#btn-retry-file').hide();
		$('#progress-file').show();
		$uploader.retry();
	});
	
	// 有文件添加后
	$uploader.on('fileQueued', function(file) {
		$('#progress-file').show();
		$('input[name="sourceName"]').val(file.name);
	});

	//文件上传过程中创建进度条实时显示。
	$uploader.on('uploadProgress', function(file, percent) {
		var progress = formatNumber(percent * 100, 2);
		$('#progress-file').progressbar('setValue', progress);
	});

	$uploader.on('uploadSuccess', function(file, result) {
		var data = result.data;
		$('input[name="fileMd5"]').val(file.md5);
		$('input[name="linkurl"]').val(data.fileUrl);
		$('#btn-form-submit').linkbutton({disabled:false});
	});

	$uploader.on('uploadError', function(file) {
		console.log('文件上传失败:', file.statusText);
		$('#btn-retry-file').show();
		$('#progress-file').hide();
//		$uploader.retry();
	});
	
	function formatNumber(num, cent) {
		isNaN(cent) || (cent = 2);
		// 把指定的小数位先转换成整数.多余的小数位四舍五入.
		var numLevel = Math.pow(10, cent);
		num = Math.round(num * numLevel);
		// 把小数位转换成字符串,以便求小数位长度.
		var cents = num % numLevel + '';
		// 补足小数位到指定的位数.
		while (cents.length < cent) {
			cents = "0" + cents;
		}
		// 求出整数位数值.
		num = Math.floor(num / numLevel).toString();

		return num + '.' + cents;
	}
});