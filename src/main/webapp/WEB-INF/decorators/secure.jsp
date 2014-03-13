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
    <link href="<s:url value="/css/home.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/sellcar.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/userinfo.css" />" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/sections/header.jsp"/>
    <jsp:include page="/WEB-INF/jsp/sections/navigation.jsp"/>

    <div class="user-info">
        <div class="logo">
            Logo个人中心
        </div>
        <div class="private">
            昵称、消息等个人相关信息
        </div>
    </div>
    <div class="user-nav">
        <ul>
            <li class="submenu">
                <a href="#">账号管理</a>
                <ul>
                    <li><a href="/secure/basicinfo">基本信息</a></li>
                    <li><a href="/secure/details">详细信息</a></li>
                    <li><a href="/secure/changepassword">修改密码</a></li>
                </ul>
            </li>
            <li><a href="#">出售二手车</a></li>
            <li><a href="#">身份认证</a></li>
            <li><a href="#">我的收藏</a></li>
            <li><a href="#">我的订单</a></li>
            <li><a href="#">买卖记录</a></li>
        </ul>
    </div>
    <decorator:body/>

    <jsp:include page="/WEB-INF/jsp/sections/footer.jsp"/>
    <p class="site-info">XXX网站备案</p>
</div>
</body>
</html>
