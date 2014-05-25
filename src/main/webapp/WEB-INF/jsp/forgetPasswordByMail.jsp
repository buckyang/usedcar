<div id="resultMessage">
	<c:choose>
		<c:when test="${not empty message }">
			${message}
		</c:when>
		<c:when test="${not empty resetPasswordErrorMessage }">
			${resetPasswordErrorMessage}
		</c:when>
	</c:choose>
</div>
<c:choose>
<c:when test="${not empty sendMail }">
<div class="user-info">
              <ul>
                <li>
                	${sendMail}
                </li>
              </ul>
</div>
</c:when>
<c:otherwise>
<form:form modelAttribute="forgetPasswordDTO" method="post"
	action="forgetPasswordByMail">
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
</c:otherwise>
</c:choose>
