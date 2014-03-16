<div class="header">
    <div class="logo">
        <img src="logo.png" alt="logo"/>
    </div>

    <div class="auth">
        <c:choose>
	    	<c:when test="${profile.status == 0 }">
		        <a class="login" href='<s:url value="/login.html"/>'>登陆</a>
		        <a class="login" href="<s:url value='/signon.html'/>">注册</a>	    		
	    	</c:when>
	    	<c:otherwise>
	    		<c:out value="${profile.nickname }"/><a class="login" href="<s:url value='/logout.html'/>">注销</a>
	    	</c:otherwise>
    	</c:choose>

    </div>
</div>