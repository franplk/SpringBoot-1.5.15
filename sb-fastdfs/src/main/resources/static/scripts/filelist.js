$(function() {
	$('td.action').on('click', 'a.delete', function(e){
		var $this = $(e.currentTarget);
		var fileId = $this.attr('id');
		$.ajax({
			type: 'POST',url: '/file/delete',
			data: {fileId: fileId},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('服务器异常');
			}
		}).then(function(result, textStatus, jqXHR) {
			if (result.code == '0000') {
				console.log('删除成功');
				window.location.reload();
			} else {
				console.log(result.msg);
				alert(result.msg);
			}
		});
	});
});