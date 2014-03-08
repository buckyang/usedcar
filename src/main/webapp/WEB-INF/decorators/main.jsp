<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Language" content="en,zh-CN;"/>
    <decorator:head/>
    <title><decorator:title default="二手车"/></title>
    <link href="/css/reset.css" type="text/css" rel="stylesheet"/>
    <link href="/css/home.css" type="text/css" rel="stylesheet"/>
    <link href="/css/register.css" type="text/css" rel="stylesheet"/>
    <link href="/css/sellcar.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/sections/header.jsp"/>
    <jsp:include page="/WEB-INF/jsp/sections/navigation.jsp"/>
    <decorator:body/>
    <jsp:include page="/WEB-INF/jsp/sections/footer.jsp"/>
    <p class="site-info">XXX网站备案</p>
</div>
</body>
</html>
