$(function(){
	var checkFileUrl = "/chunk/fileCheck";
	var checkChunUrl = "/chunk/chunkCheck";
	var chunkSize = 256 * 1024;// 分片大小256k
	
	var fileMd5 = "";
	WebUploader.Uploader.register({
		'name' : 'chunkUpload',
		'before-send-file': 'beforeSendFile',
		"before-send" : "beforeSendBlock"
	}, {
	beforeSendFile : function(file) {
		var task = new WebUploader.Deferred();
		var fileName = file.name;
		var fileSize = file.size;
		// 通过文件的MD5进行文件验证
		(new WebUploader.Uploader()).md5File(file, 0, chunkSize)
		.progress(function(percentage) {})
		.then(function(md5) {
			fileMd5 = md5;
			$.ajax({
				type: "POST",url: checkFileUrl,cache: false,async: false,dataType: "json",
				data: {fileName:fileName,fileMd5:md5,fileSize:fileSize},
				error: function(XMLHttpRequest,textStatus, errorThrown) {
					file.statusText = '服务器异常';
					task.reject();
				}
			}).then(function(result, textStatus, jqXHR) {
				if (result.code == '0000') {
					console.log("文件验证通过:开始上传");
					task.resolve();
				} else {
					file.statusText = result.msg;
					console.log(result.msg);
					task.reject();
				}
			});
		});
		return task.promise();
	}, beforeSendBlock: function(block) {
		// 文件分片验证保证文件完整
		var task = new WebUploader.Deferred();
        $.ajax({
			type: "POST", url: checkChunUrl,cache: false, async: false, // 同步  
			data: {chunkNum: block.chunk,fileMd5: fileMd5},
			error: function(XMLHttpRequest,textStatus, errorThrown) {
				file.statusText = '服务器异常';
				task.reject();
			}
        }).then(function(result, textStatus, jqXHR) {
        	if (result.code == '0000') {
				console.log("分片验证通过:上传第", block.chunk, "块分片");
				task.resolve();
			} else {
				file.statusText = result.msg;
				console.log(result.msg);
				task.reject();
			}
		});
        this.owner.options.formData.fileMd5 = fileMd5;
        this.owner.options.formData.chunkSize = chunkSize;
		return task.promise();
	}});
	
	var uploader = WebUploader.create({
	    auto: true, threads: 1,
		resize: false, compress: false,
		chunked: true, chunkRetry: 2, chunkSize: chunkSize,
		swf: '/webuploader/Uploader.swf',
		server: 'http://localhost:8080/chunk/upload',
		pick: {id:'#picker',innerHTML:'选择文件',multiple:false}
	});

	// 添加文件判断
	uploader.on('beforeFileQueued', function(file) {
		if (file.size > 10 * 1024 * 1024) {
			alert("文件太大...");
			return false;
		}
		return true;
	});

	// 有文件添加后
	uploader.on('fileQueued', function(file) {
		var fileId = file.id;
		var fileName = file.name;
		// 创建文件传输对象 <td><span>fileName</span></td>
		var $trItem = $('<tr id="' + fileId + '"></tr>');
		$trItem.append('<td class="fileName"><span>' + fileName + '</span></td>');
		var $progressbar = $('<div class="progress-bar" style="width: 0%"></div>');
		var $progress = $('<td class="progress"><div class="progress-text">0%</></td>');
		$progress.append($progressbar);
		$trItem.append($progress);
		$('#fileList').append($trItem);
	});

	//文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percent) {
		var progress = toDecimal(percent * 100, 2) + '%';
		var $trItem = $('tr#' + file.id);
		$trItem.find('.progress-bar').css('width', progress);
		$trItem.find('.progress-text').text(progress);
	});

	uploader.on('uploadSuccess', function(file, response) {
		alert('文件上传成功');
		var data = response.data;
		var $trItem = $('tr#' + file.id);
		$trItem.append('<td><a href="' + data.fileUrl + '" target="_blank">查看</a></td>');
	});

	uploader.on('uploadError', function(file) {
		console.log('文件上传失败:', file.statusText);
		alert(file.statusText);
		var $trItem = $('tr#' + file.id);
		var retryItem = $trItem.find('a.retry');
		if (retryItem.length > 0) {
			return;
		}
		$trItem.append('<td><a class="retry" href="javasctipt:void(0)">重试</a></td>');
	});
	
	// 重试
	$(document).on('click', 'a.retry', function(e) {
		uploader.retry();
    });
	
	function toDecimal(x, n) {
		var f = parseFloat(x);
		function getN(n) {
			var sum = 1;
			for (var i = 0; i < n; i++) {
				sum *= 10;
			}
			return sum;
		}
		if (isNaN(f)) {
			return 0;
		}
		return Math.round(x * getN(n)) / getN(n);
	}
});