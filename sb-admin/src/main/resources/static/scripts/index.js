$(function() {
	$("ul.banner").on('click', 'a', function(e) {
		var aimUrl = $(e.target).attr("aim");
		if (aimUrl.slice(0, 4) === 'http') {
			window.open(aimUrl);
		} else {
			window.location.href = aimUrl;
		}
	});
	
	$("ul.menu").on('click', 'a', function(e) {
		var title = $(e.target).text();
		var url = $(e.currentTarget).attr("aim");
		if ($('#mainTabs').tabs('exists', title)) {
			$('#mainTabs').tabs('select', title);
		} else {
			title || (title = '新页面');
			$('#mainTabs').tabs('add', {
				title:title, closable:true, iconCls:'icon-ok',
				content:'<iframe frameborder="0" src="' + url + '"></iframe>'
			});
		}
	});
});