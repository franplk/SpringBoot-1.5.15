$(function() {
	$("ul.banner").on('click', 'a', function(e) {
		var aimUrl = $(e.target).attr("aim");
		if (aimUrl.slice(0, 4) === 'http') {
			window.open(aimUrl);
		} else {
			window.location.href = aimUrl;
		}
	});
});