$(function() {
	$('td.action').on('click', 'button', function(e){
		$this = $(e.currentTarget);
		var eid = $this.attr('id');
		var act = eid.split('-');
		$.ajax({
			type:"POST", dataType:"json", url:"/user/" + act[0],
			data:{"userid":act[1]},
			success:function(result) {
				if (result.code == '0000') {
					alert('执行成功');
					window.location.reload();
				} else {
					alert(result.msg);
				}
			},error:function(XMLHttpRequest, textStatus) {
				alert("网络不佳，请稍后再试");
			},complete:function(){
			}
		});
	});
});