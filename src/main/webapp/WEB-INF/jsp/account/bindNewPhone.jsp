<script src='<s:url value="/js/common/bindPhone.js" />' type="text/javascript"></script>
<div id="title">
<h1>绑定新手机：</h1>
</div>
<form:form modelAttribute="bindPhoneDTO" method="post"
	action="bindNewPhone">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">手机号码：</td>
				<td><form:input id="bindNewPhoneNumber" path="phoneNumber" value="${bindPhoneDTO.phoneNumber}"/></td>
				<td><a href="javascript:void(0);" onclick="sendMessage('bindNewPhoneNumber','6');" style="color: #000000;">获取验证码</a></td>
				<td><form:errors path="phoneNumber" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top">验证码：</td>
				<td><form:input path="code" /></td>
				<td><form:errors path="code" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">确认修改</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>