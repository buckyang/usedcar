function modifyCertificateNumber(id) {
	$("#" + id).hide();
	$("#certificateValue").show();
}

$(document).ready(
		function() {
			strYYYY = document.getElementById('birthYear').outerHTML;
			strMM = document.getElementById('birthMonth').outerHTML;
			strDD = document.getElementById('birthDay').outerHTML;
			MonHead = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

			// 先给年下拉框赋内容
			var y = new Date().getFullYear();
			var str = strYYYY.substring(0, strYYYY.length - 9);
			for (var i = (y - 120); i < (y + 1); i++) // 以今年为准，前120年
			{
				str += "<option value='" + i + "'> " + i + "</option>\r\n";
			}
			document.getElementById('birthYear').outerHTML = str + "</select>";

			// 赋月份的下拉框
			var str = strMM.substring(0, strMM.length - 9);
			for (var i = 1; i < 13; i++) {
				str += "<option value='" + i + "'> " + i + "</option>\r\n";
			}
			strMM = document.getElementById('birthMonth').outerHTML = str
					+ "</select>";
			if(selectYear!=null&&$.trim(selectYear)!=''){
				document.getElementById('birthYear').value=selectYear;
				document.getElementById('birthMonth').value=selectMonth;
			}
		    var n = MonHead[selectMonth-1];  
		    if (selectMonth ==1 && IsPinYear(selectYear))
		    {
		    	n++;
		    }
		    writeDay(n); //赋日期下拉框  
		    document.getElementById('birthDay').value = selectDay;
		});

function YYYYMM(str) // 年发生变化时日期发生变化(主要是判断闰平年)
{
	var MMvalue = $("#birthMonth").val();
	if (str == "") {
		$("#birthMonth").val('');
		$("#birthDay").val('');
		return;
	}
	var n = MonHead[MMvalue - 1];
	if (MMvalue == 2 && IsPinYear(str))
		n++;
	writeDay(n);
}

function MMDD(str) // 月发生变化时日期联动
{
	var YYYYvalue = $("#birthYear").val();
	if (str == "") {
		document.getElementById('birthDay').outerHTML = strDD;
		$("#birthDay").val('');
		return;
	}
	var n = MonHead[str - 1];
	if (str == 2 && IsPinYear(YYYYvalue))
		n++;
	writeDay(n);
}

function writeDay(n) // 据条件写日期的下拉框
{
	var s = strDD.substring(0, strDD.length - 9);
	for (var i = 1; i < (n + 1); i++)
		s += "<option value='" + i + "'> " + i + "</option>\r\n";
	document.getElementById('birthDay').outerHTML = s + "</select>";
}

function IsPinYear(year)// 判断是否闰平年
{
	return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
}


function bindPhone(){
	window.location.href="bindPhone";
}

function bindMail(){
	window.location.href="bindMail";
}