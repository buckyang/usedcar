<body class="index">
	<script src="<s:url value="/js/admin/homePage.js" />" type="text/javascript"></script>
	<div id="header" class="whiteText">
		<div class="logo">${siteConfiguration.siteName} - 后台管理系统</div>
	    <ul id="personalNav">
	    	<li>欢迎，${adminProfile.loginName }</li>
	        <li id="backToHome"><a class="home" href="<s:url value="/admin/welcome" />">主页</a></li>
	        <li><a href="<s:url value="/admin/logout" />" class="logOut">注销账户</a></li>
	    </ul>
	    <div class="clearfix"> </div>
	</div>	
	<div id="main">
		<div class="left">
			<div class="leftNavigat">
				<dl>
				<c:forEach var="firstLevelMenu" items="${menus}">
				    <c:set var="secondLevelMenus" value="${firstLevelMenu.childMenus}"/>
				    <dt><a href="javascript:void(0)"><c:out value="${firstLevelMenu.name}"/></a></dt>
			        <dd>
			        <c:forEach var="secondLevelMenu" items="${secondLevelMenus }">
			        	<s:url value="${secondLevelMenu.url }" var="secondMenuURL">
			        		<s:param name="menuId" value="${secondLevelMenu.pageId }"/>
			        	</s:url>
			        	<a href='${secondMenuURL }'> <c:out value="${secondLevelMenu.name}"/></a>
			        </c:forEach>
			        </dd>		
				</c:forEach>
				</dl>
			</div>
			<div class="leftNavigatFootBg"></div>
		</div>	
		<div class="right">
			<iframe src="<s:url value="/admin/welcome" />" id="mainIframe" name="mainIframe" frameborder="0"></iframe>
		</div>
	</div>
	<div id="footer"><span>版权所有</span></div>
	<div class="miniSize"></div>
</body>