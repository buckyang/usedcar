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
    <body <decorator:getProperty property="body.class" writeEntireProperty="true" />>
		<decorator:body />														
    </body>
</html>
