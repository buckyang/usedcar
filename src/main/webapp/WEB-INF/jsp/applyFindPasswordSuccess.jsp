<div id="applyResetPasswordResult">
<c:choose>
<c:when test="${not empty sendMail }">
${sendMail }
</c:when>
</c:choose>
</div>