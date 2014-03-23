<head>
	<title>二手车 -- 用户注册</title>
</head>
<body>
<div class="register">
	    <div class="category">
	        <div class="individual">
	            个人注册
	        </div>
	        <div class="merchant">
	        	<a href="<s:url value='/resellerSignon.html'/>">经销商注册</a>
	        </div>
	    </div>
	    <p class="declaration">所有注册信息均按照相关服务条款收到法律保护</p>
	
		<form:form id="signonForm" method="post" modelAttribute="registrationDTO">
	        <div class="telephone">
	            <label for="phone"><span>*</span>注册手机号码：</label>
	            <form:input path="phone" /><form:errors path="phone" cssClass="error" />
	            <button>免费获取验证码</button>
	        </div>
	        <div class="code">
	            <label for="phoneVerifyCode"><span>*</span>手机验证码：</label>
	            <form:input path="phoneVerifyCode" /><form:errors path="phoneVerifyCode" cssClass="error" />
	        </div>
	        <div class="username">
	            <label for="nickname"><span>*</span>用户名：</label>
	            <form:input path="nickname" /><form:errors path="nickname" cssClass="error" />
	        </div>
	        <div class="password">
	            <label for="password"><span>*</span>密码：</label>
	            <form:password path="password" /><form:errors path="password" cssClass="error" />
	        </div>
	        <div class="pwd-confirm">
	            <label for="repassword"><span>*</span>确认密码：</label>
	            <form:password path="repassword" /><form:errors path="repassword" cssClass="error" />
	        </div>
	        <div class="email">
	            <label for="email">邮箱：</label>
	            <form:input path="email" /><form:errors path="email" cssClass="error" />
	        </div>
	        <div class="agreement">
	            <label for="acceptTerm">
	                <form:checkbox path="acceptTerm" value="true"/><form:errors path="acceptTerm" cssClass="error" />
	                我已阅读并同意《XXXX网服务条款》</label>
	        </div>
	        <form:hidden path="accountType" value="1"/>
	        <button type="submit">注册</button>
	    </form:form>
	</div>	
</body>