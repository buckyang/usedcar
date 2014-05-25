<script src='<s:url value="/js/lib/jquery-1.7.2.min.js" />' type="text/javascript"></script>
<script type="text/javascript">
function sendMessage(phoneNumberInputId,codeType){
	if(phoneNumberInputId==null||$.trim(phoneNumberInputId)==''||codeType==null||$.trim(codeType)==''){
		alert("Phone number can not be empty ");
	}
     var phoneNumber=$("#"+phoneNumberInputId).val();
     $.ajax({
			type : "POST",
			url : "account/obtainCode",
			data : "phoneNumber=" + phoneNumber + "&type=" + codeType,
			success : function(data) {
				var result=$(data).find("#sendMessageResult");
      			if($.trim($(result).html())=='true'){
      				alert('send message success');
      			}else{
      				alert($(result).html());
      			}
			}
		});
}
</script>
<form:form action="resetPasswordByPhone" method="post" modelAttribute="resetPasswordDTO">
    <label>XXXX网密码找回：</label>
    <div class="telephone">
        <label for="telephone"><span>*</span>注册手机号码：</label>
        <form:input id="bindedPhone" path="principle" value="${phone}" readonly="true"/>
        <a onclick="sendMessage('bindedPhone',4);" style="color: #000000;" href="javascript:void(0);">免费获取验证码</a>
        <form:errors path="principle" cssClass="fieldError" />
    </div>
    <div class="mobile-code">
        <label for="mobileCode"><span>*</span>手机验证码：</label>
        <form:input path="activeCode" />
        <form:errors path="activeCode" cssClass="fieldError" />
    </div>
    <div class="password">
        <label for="password"><span>*</span>输入新密码：</label>
        <form:password path="newPassword" />
        <form:errors path="newPassword" cssClass="fieldError" />
    </div>
    <div class="confirm-pwd">
        <label for="confirmPwd"><span>*</span>确认新密码：</label>
       <form:password path="confirmPassword" />
       <form:errors path="confirmPassword" cssClass="fieldError" />
    </div>
    <button type="submit" value="confirm">确认</button>
</form:form>