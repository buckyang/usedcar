<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Language" content="en,zh-CN;"/>
    <decorator:head/>
    <title><decorator:title default="${siteConfiguration.siteName }"/></title>
    <link href="<s:url value="/css/reset.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/login.css" />" type="text/css" rel="stylesheet"/>
</head>
<body>
    <decorator:body/>
</body>
</html>
