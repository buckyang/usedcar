<div id="resultMessage">
<c:if test="${not empty applyBindMailSuccess }">
${applyBindMailSuccess}
</c:if>
<script type="text/javascript">
function backHomePage(){
	window.location.href="/";
}
</script>
</div>
<a href="javascript:void(0);" onclick="backHomePage();" style="color: #000000;">点此返回首页</a>