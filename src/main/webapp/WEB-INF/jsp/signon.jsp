<head>
	<title>用户注册</title>
</head>
<body>
	<div id="formsContent">
		<form:form id="form" method="post" modelAttribute="registrationDTO">
			<div class="header">
		  		<h2>Form</h2>
		  		<c:if test="${not empty message}">
					<div id="message" class="success"><s:message code="${message}"/></div>	
		  		</c:if>
		  		<s:bind path="*">
		  			<c:if test="${status.error}">
				  		<div id="message" class="error">输入信息有误</div>
		  			</c:if>
		  		</s:bind>
			</div>
		  	<fieldset>
		  		<legend>普通用户注册</legend>
		  		<c:choose>
			  		<c:when test="${registrationDTO.accountType == 1}">
				  		<form:label path="phone">注册手机号码</form:label>
				  		<form:input path="phone" /><form:errors path="phone" cssClass="error" /><br />
				  		<form:label path="phoneVerifyCode">手机验证码</form:label>
				  		<form:input path="phoneVerifyCode" /><form:errors path="phoneVerifyCode" cssClass="error" /><br />  				  				  				  		
				  		<form:label path="nickname">用户名</form:label>
				  		<form:input path="nickname" /><form:errors path="nickname" cssClass="error" /><br />		  				  			
				  		<form:label path="password">密码</form:label>
				  		<form:password path="password" /><form:errors path="password" cssClass="error" /><br />
				  		<form:label path="repassword">确认密码</form:label>
				  		<form:password path="repassword" /><form:errors path="repassword" cssClass="error" /><br />
				  		<form:label path="email">电子邮箱</form:label>
				  		<form:input path="email" /><form:errors path="email" cssClass="error" /><br />
				  		<form:label path="acceptTerm">同意服务条款</form:label>
				  		<form:checkbox path="acceptTerm" value="true"/><form:errors path="acceptTerm" cssClass="error" /><br />
				  		<form:hidden path="accountType" value="1"/>
			  		</c:when>
			  		<c:otherwise>
				  		<form:label path="phone">注册手机号码</form:label>
				  		<form:input path="phone" /><br />
				  		<form:label path="phoneVerifyCode">手机验证码</form:label>
				  		<form:input path="phoneVerifyCode" /><br />  				  				  				  		
				  		<form:label path="nickname">用户名</form:label>
				  		<form:input path="nickname" /><br />	  				  			
				  		<form:label path="password">密码</form:label>
				  		<form:password path="password" /><br />
				  		<form:label path="repassword">确认密码</form:label>
				  		<form:password path="repassword" /><br />
				  		<form:label path="email">电子邮箱</form:label>
				  		<form:input path="email" /><br />
				  		<form:label path="acceptTerm">同意服务条款</form:label>
				  		<form:checkbox path="acceptTerm" value="true"/><br />
				  		<form:hidden path="accountType" value="1"/>			  			
			  		</c:otherwise>
		  		</c:choose>
		  	</fieldset>
			<p><button type="submit">Submit</button></p>
		</form:form>
	</div>
	<div>
		<form:form id="resellerForm" method="post" modelAttribute="registrationDTO">
		  	<fieldset>
		  		<legend>经销商注册</legend>
		  		<c:choose>
			  		<c:when test="${registrationDTO.accountType == 2}">		  		
				  		<form:label path="phone">注册手机号码</form:label>
				  		<form:input path="phone" /><form:errors path="phone" cssClass="error" /><br />
				  		<form:label path="phoneVerifyCode">手机验证码</form:label>
				  		<form:input path="phoneVerifyCode" /><form:errors path="phoneVerifyCode" cssClass="error" /><br />
				  		<form:label path="resellerName">经销商名称</form:label>
				  		<form:input path="resellerName" /><form:errors path="resellerName" cssClass="error" /><br />
				  		<form:radiobuttons path="resellerType" itemLabel="optionDisplayName" itemValue="optionValue" items="${preLoadBeans.resellerTypes }" />
				  		<form:errors path="resellerType" cssClass="error" /><br />	  				  				  		  				  				  				  		
				  		<form:label path="nickname">用户名</form:label>
				  		<form:input path="nickname" /><form:errors path="nickname" cssClass="error" /><br />		  				  			
				  		<form:label path="password">密码</form:label>
				  		<form:password path="password" /><form:errors path="password" cssClass="error" /><br />
				  		<form:label path="repassword">确认密码</form:label>
				  		<form:password path="repassword" /><form:errors path="repassword" cssClass="error" /><br />
				  		<form:label path="email">电子邮箱</form:label>
				  		<form:input path="email" /><form:errors path="email" cssClass="error" /><br />
				  		<form:label path="acceptTerm">同意服务条款</form:label>
				  		<form:checkbox path="acceptTerm" value="true"/><form:errors path="acceptTerm" cssClass="error" /><br />
				  		<form:hidden path="accountType" value="2"/>
					</c:when>
					<c:otherwise>
				  		<form:label path="phone">注册手机号码</form:label>
				  		<form:input path="phone" /><br />
				  		<form:label path="phoneVerifyCode">手机验证码</form:label>
				  		<form:input path="phoneVerifyCode" /><br />
				  		<form:label path="resellerName">经销商名称</form:label>
				  		<form:input path="resellerName" /><br />
				  		<form:radiobuttons path="resellerType" itemLabel="optionDisplayName" itemValue="optionValue" items="${preLoadBeans.resellerTypes }" /><br />	  				  				  		  				  				  				  		
				  		<form:label path="nickname">用户名</form:label>
				  		<form:input path="nickname" /><br />				  			
				  		<form:label path="password">密码</form:label>
				  		<form:password path="password" /><br />
				  		<form:label path="repassword">确认密码</form:label>
				  		<form:password path="repassword" /><br />
				  		<form:label path="email">电子邮箱</form:label>
				  		<form:input path="email" /><br />
				  		<form:label path="acceptTerm">同意服务条款</form:label>
				  		<form:checkbox path="acceptTerm" value="true"/><br />
				  		<form:hidden path="accountType" value="2"/>					
					</c:otherwise>
				</c:choose>									  		
		  	</fieldset>
			<p><button type="submit">Submit</button></p>
		</form:form>
	</div>	
</body>