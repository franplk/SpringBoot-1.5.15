(function(w) {
	var checkFileUrl = "chunk/fileCheck";
	var chunkSize = 256 * 1024;// 分片大小256k
	
	var currChunkNum = 0;
	
	WebUploader.Uploader.register({
		'before-send-file': 'beforeSendFile'
	}, {
	beforeSendFile : function(file) {
		var task = new WebUploader.Deferred();
		var fileName = file.name;
		var fileSize = file.size;
		// 通过文件的MD5进行文件验证
		(new WebUploader.Uploader()).md5File(file)
		.then(function(md5) {
			$.ajax({
				type: "POST",url: checkFileUrl,
				data: {fileSize:fileSize,fileMd5:md5},
				cache: false,async: false,dataType: "json",
				error: function(XMLHttpRequest,textStatus, errorThrown) {
					file.statusText = '服务器异常';
					task.reject();
				}
			}).then(function(result, textStatus, jqXHR) {
				if (result.code == '0000') {
					file.md5 = md5;
					currChunkNum = result.data.chunkNum;
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
	}});
	
	var uploader = WebUploader.create({
		server: 'chunk/upload',
		swf: 'webuploader/Uploader.swf',
		chunked: true, chunkRetry: 2, chunkSize: chunkSize,
	    auto: true, threads: 1, resize: false, compress: false,
		pick: {id:'#picker',innerHTML:'选择文件',multiple:false}
	});
	
    // 发送前检查分块,并附加MD5数据
	uploader.on('uploadBeforeSend', function(block, data) {
		var task = new WebUploader.Deferred();
		if (block.chunk < currChunkNum) {
			console.log("分片", block.chunk, "已存在，跳过");
			task.reject();
		} else {
			console.log("分片", block.chunk, "开始上传");
			task.resolve();
		}
		data.fileMd5 = block.file.md5;
		data.chunkSize = chunkSize;
		return task.promise();
	});
	
	w.$uploader = uploader;
	
})(window);