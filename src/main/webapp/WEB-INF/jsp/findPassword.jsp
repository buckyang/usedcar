<script src='<s:url value="/js/lib/jquery-1.7.2.min.js" />' type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	selectVerifyType();
});
function selectVerifyType(){
	var type=$("#findPasswordMethod").val();
	if(type=='email'){
		$("#mailPanel").show();
		$("#phonePanel").hide();
	}else if(type=='mobile'){
		$("#phonePanel").show();
		$("#mailPanel").hide();
	}
}
</script>
<label>XXXX网密码找回：</label>
<form:form method="post" action="findPassword" id="validUserInfoForm">
<label>请选选择密码找回方式：</label>
<select onchange="selectVerifyType();" id="findPasswordMethod" name="method" class="select">
			<c:if test="${not empty bindPhone and bindPhone}">
				<option value="mobile">已验证手机</option>
			</c:if>
			<c:if test="${not empty bindMail and bindMail }">
				<option value="email">邮箱</option>
			</c:if>
</select>
<label>呢称：</label><span class="label">${nickName}</span>
<div id="mailPanel" style="display:none;">
 	<span class="label">邮箱地址：</span>
 	<input name="mail" value="${mail}" readonly="readonly"/>
 	<button type="submit">发送邮件</button>
</div>
<div id="phonePanel" style="display:none">
     <span class="label">已验证手机：</span>
     <input id="bindedPhone" name="phone" value="${phone}" readonly="readonly"/>
 	<button type="submit">提交</button>
</div>
</form:form>


