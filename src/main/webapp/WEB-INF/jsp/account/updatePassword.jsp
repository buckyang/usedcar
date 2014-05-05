<div id="resultMessage">
<c:if test="${not empty message }">
${message}
</c:if>
</div>
<form:form modelAttribute="updatePasswordDTO" method="post"
	action="forgetPasswordDTO">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">用户名：</td>
				<td><form:password path="loginName" /></td>
				<td><form:errors path="loginName" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">提交</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>