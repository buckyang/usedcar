<script src='<s:url value="/js/common/bindMail.js" />' type="text/javascript"></script>
<div id="resultMessage">
<c:if test="${not empty validMailErrorMessage }">
${validMailErrorMessage}
</c:if>
</div>
<div id="title"><h1>身份验证：</h1></div>
<form:form modelAttribute="bindMailDTO" method="post"
	action="applyValidBindedMail">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">已验证邮箱：</td>
				<td><form:input path="mail" readonly="true" value="${bindMailDTO.mail }"/></td>
				<td><form:errors path="mail" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">提交</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>