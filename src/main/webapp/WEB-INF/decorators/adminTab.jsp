<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
	<head>
	    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
	    <meta http-equiv="Content-Language" content="en,zh-CN;"/>
	    <decorator:head/>
	    <title><decorator:title default="${siteConfiguration.siteName } - 后台管理系统"/></title>
	    <link href="<s:url value="/css/admin/global.css" />" type="text/css" rel="stylesheet"/>
	    <script src="<s:url value="/js/lib/jquery-1.4.1.min.js" />" type="text/javascript"></script>
		<script src="<s:url value="/js/admin/global.js" />" type="text/javascript"></script>
		<script src="<s:url value="/js/admin/pagination.js" />" type="text/javascript"></script>
	</head>
    <body>
    	<c:choose>
			<c:when test="${!empty user }">
				<c:set var="tabTitle">校长</c:set>
				<s:url value="/admin/customer/customerList" var="returnURL"/>	
				<c:set var="paramName" value="profileId"/>
				<c:set var="paramValue" value="${user.repositoryId}"/>		
			</c:when>
		</c:choose>	
		<c:set var="currentPage" value="${fn:substringAfter(pageContext.request.requestURI, pageContext.request.contextPath)}" />
		<div class="tabHeader">
		    <div class="topTitle">
		        <strong><c:out value="${tabTitle}"/></strong>
			        <c:if test="${empty param.isDisplayReturnUrl || param.isDisplayReturnUrl}">
				        <c:if test="${!empty returnURL }">
				        	<span><a href="${returnURL }" class="back">返回搜索页面</a></span>		        
				        </c:if>
			        </c:if>
		        <div class="clearfix"></div>
		    </div>
		    <c:if test="${!empty tabGroup }">
			    <div class="tabTitle">
			    	<div class="left"></div>
			        <div><ul>	
        			<c:forEach var="tab" items="${tabGroup }">
        				<c:choose>
	        				<c:when test="${tab.url == currentPage }">
	       						<li class="selected"><a href="javascript:void(0)"><c:out value="${tab.name }"/></a></li>
	        				</c:when>
        					<c:otherwise>
        						<s:url value="${tab.url }" var="unselectedURL">
        							<s:param name="isDisplayReturnUrl" value="${param.isDisplayReturnUrl }"/>
        						</s:url>
        						<li><a href="${unselectedURL }"><c:out value="${tab.name }"/></a></li>
        					</c:otherwise>
        				</c:choose>
        			</c:forEach>
	        		</ul></div>
		        	<div class="right"></div>
		    	</div>    	    
		    </c:if>
		</div>  		
		<decorator:body />														
    </body>
</html>
