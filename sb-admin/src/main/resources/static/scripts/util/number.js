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
	if (num.length > 3) {
		num = num.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	return num + '.' + cents;
}

function ruleFormat (value,row,index) {
	if (value == undefined) {
		return '--';
	}
	return value;
}

function digitFormat (value,row,index) {
	value = ruleFormat(value);
	if (isNaN(value)) {
		return value;
	}
	var text = formatNumber(Math.abs(value),2);
	if (value < 0) {
		text = "-" + text;
		return "<span class='minus'>" + text + "</span>";
	}
	return text;
}

function intFormat (value,row,index) {
	value = ruleFormat(value);
	if (isNaN(value)) {
		return value;
	}
	var num = value.toString();
	if (num.length > 3) {
		num = num.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	return num;
}