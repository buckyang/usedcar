<div class='sessionOut'>对不起，你没有权限访问或会话超时 <br/>
<span id="timeCount">5</span>秒之后，你将被重定向到登录首页 </div>
<input type="hidden" value="<s:url value='login'/>" id="loginOutLink">
<script language="javascript" type="text/javascript">
(function($){
	var src = $("#loginOutLink").val();
	var timeCount = $("#timeCount").html();
	var autoPlsy = function() {
		if(timeCount == 0) {
			window.top.location = src;
		} else {
			$("#timeCount").html(timeCount);
			timeCount--;
			setTimeout(autoPlsy,1000);
		}
	}
	autoPlsy();
})(jQuery)
</script>