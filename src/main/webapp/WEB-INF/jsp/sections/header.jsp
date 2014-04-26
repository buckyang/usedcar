<div class="header">
    <div class="logo">
        <img src="logo.png" alt="logo"/>
    </div>

    <div class="auth">
        <c:choose>
	    	<c:when test="${profile.status == 0 }">
		        <a class="login" href='<s:url value="/login"/>'>登陆</a>
		        <a class="login" href="<s:url value='/signon'/>">注册</a>	    		
	    	</c:when>
	    	<c:otherwise>
	    		<c:if test="${ profile.accountType == 2}">
	    			[经销商]
	    		</c:if>
	    		<c:out value="${empty profile.nickname? profile.accountId : profile.nickname }"/><a class="login" href="<s:url value='/logout'/>">注销</a>
	    	</c:otherwise>
    	</c:choose>

    </div>
</div>