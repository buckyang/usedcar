
<form:form modelAttribute="resetPasswordDTO" method="post"
	action="resetPasswordByMail">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">新密码：</td>
				<td><form:password path="newPassword" /></td>
				<td><form:errors path="newPassword" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top">确认新密码：</td>
				<td><form:password path="confirmPassword" /></td>
				<td><form:errors path="confirmPassword" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td><form:hidden path="principle" value="${mail}"/></td>
			</tr>
			<tr>
				<td><form:hidden path="activeCode" value="${activeCode }"/></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">提交</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>