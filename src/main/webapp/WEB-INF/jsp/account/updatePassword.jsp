<div id="resultMessage">
<c:if test="${not empty message }">
${message}
</c:if>
</div>
<form:form modelAttribute="updatePasswordDTO" method="post"
	action="updatePassword">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">旧密码：</td>
				<td><form:password path="oldPWD" /></td>
				<td><form:errors path="oldPWD" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top">新密码：</td>
				<td><form:password path="newPWD" /></td>
				<td><form:errors path="newPWD" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top">再次输入新密码：</td>
				<td><form:password path="confirmPWD" /></td>
				<td><form:errors path="confirmPWD" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">确认修改</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>