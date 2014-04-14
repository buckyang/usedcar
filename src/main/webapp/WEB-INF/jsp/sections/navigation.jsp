<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="nav">
    <ul>
	    <c:forEach items="${applicationScope.MENU_ITEMS}" var="menuItem">
			<li><a href="javascript:void(0);">${menuItem.value.title}</a></li>
		</c:forEach>
    </ul>
</div>