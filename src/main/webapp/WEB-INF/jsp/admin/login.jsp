<body>
<form:form name="loginForm" id="loginForm" commandName="adminLoginDTO" method="post">
<div id="login">
	<div class="top whiteText"><c:out value="${siteConfiguration.siteName } "/> - 后台管理系统</div>
    <div class="content">
    <fieldset>
    	<c:if test="${!empty message }">
	    	<div class="errorMsg"><span class="errorText"><s:message code="${message}"/></span></div>
    	</c:if>
    	<dl>   
    		<dt><form:label path="loginName">用户名</form:label></dt>
			<dd>
				<form:input path="loginName"/>
				<div class="errorMsg"><form:errors path="loginName" cssClass="errorText"/></div>
            </dd>
    		<dt><form:label path="password">密码</form:label></dt>
			<dd>
				<form:password path="password"/>
				<div class="errorMsg"><form:errors path="password" cssClass="errorText"/></div>
            <dd>
            <dt></dt>
            <dd>
            <span class="loginBtn">
            <input type="submit" class="whiteText" value="登录">
            </span>
            </dd>
        </dl>
    </fieldset>
    </div>
</div>
</form:form>
</body>
