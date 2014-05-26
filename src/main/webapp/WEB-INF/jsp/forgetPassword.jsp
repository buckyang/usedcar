<div id="reesetPassowrdErrorMessage">
<c:if test="${not empty resetPasswordErrorMessage  }">
${resetPasswordErrorMessage }
</c:if>
</div>
<form:form modelAttribute="forgetPasswordDTO" method="post"
	action="forgetPassword">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<td width="150" class="right" valign="top">用户名：</td>
				<td><form:input path="loginName" /></td>
				<td><form:errors path="loginName" cssClass="fieldError" /></td>
			</tr>
			<tr>
				<td width="150" class="right" valign="top"></td>
				<td class="padding"><form:button value="submit">提交</form:button></td>
			</tr>
		</tbody>
	</table>
</form:form>
