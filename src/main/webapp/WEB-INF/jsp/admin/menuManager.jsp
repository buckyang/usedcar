<link href='<s:url value="/css/menuManager.css" />' type="text/css" rel="stylesheet"/>
<%-- <script src='<s:url value="/js/lib/jquery-1.7.2.min.js" />' type="text/javascript"></script> --%>
<script src='<s:url value="/js/lib/jquery.form.js" />' type="text/javascript"></script>
<script src='<s:url value="/js/common/menuManager.js" />' type="text/javascript"></script>

<div style="display: none;">
	<form:form id="managerMenuForm" action="addItem" method="post"
		modelAttribute="menuManagerDTO">
		<form:input id="menuItemTitle" path="title" />
		<form:input id="menuItemContents" path="contents" />
		<form:input id="menuItemParentMenuId" path="parentMenuId" />
		<form:input id="menuItemDisplayOrder" path="displayOrder" />
		<form:input id="menuItemEditAllProperty" path="editAllProperty" />
		<form:button value="submit"></form:button>
	</form:form>
</div>

<div class="wrap">
	<!-- the side menu area -->
	<div class="side">
		<!-- The side menu -->
		<div class="menu">
			<ul>
				<c:forEach items="${applicationScope.MENU_ITEMS}" var="menuItem">
					<li><a id="topMenuId${menuItem.value.menuId}" class="album" href="javascript:void(0);" onclick="showChildItems('${menuItem.value.menuId}','${menuItem.value.title}')">${menuItem.value.title}</a></li>
				</c:forEach>
			</ul>
		</div>
		<!-- //The side menu -->
	</div>
	<!-- the middle area -->
	<div class="right">
		<h1 class="tit_1" id="topMenuItemId">
			<input id="topMenuTitleId" type="text" disabled="disabled" value="" name=""></input>
			<a style="display:block;float: right;margin-right: 540px;margin-top: 11px;" id="topMenuEditBtn" class="btn btn-mini" href="javascript:void(0);" style="height: 25px;margin-left: 6px;" onclick="editTopMenuTitle('edit')">e</a>
			<a style="display:none;" id="topMenuSubmitBtn" class="btn btn-mini" href="javascript:void(0);" style="height: 25px;margin-left: 6px;" onclick="editTopMenuTitle('submit')">√</a>
		</h1>
		<c:forEach items="${applicationScope.MENU_ITEMS}" var="menuItem">
			<c:choose>
				<c:when test="${not empty menuItem.value.childMenuItems and fn:length(menuItem.value.childMenuItems)>0}">
						<div class="album" id="childItems${menuItem.value.menuId}" style="display:none;height: 215px;overflow-y: auto;overflow-x: hidden;">
							<div class="CODE_snippets">
								<c:forEach items="${menuItem.value.childMenuItems}" var="childItem">
									<h5 class="clearfix" id="childItem_${childItem.menuId}" name="${childItem.displayOrder}">
										<div class="pull-left">
											<input id="childItemTitle${childItem.menuId}" type="text" disabled="disabled" value="${childItem.title }"></input>
										</div>
										<div class="pull-right">
											<a onclick="addChildItem('${menuItem.value.menuId}','${childItem.menuId}','${childItem.displayOrder}',this)" class="btn btn-mini" href="javascript:void(0);">+</a>
											<a onclick="deleteChildItem('${menuItem.value.menuId}','${childItem.menuId}')" class="btn btn-mini" href="javascript:void(0);">-</a>
											<a onclick="editChildItem('${menuItem.value.menuId}','${childItem.menuId}',this)" class="btn btn-mini" href="javascript:void(0);">e</a>
											<a onclick="changeItemLocation('${menuItem.value.menuId}','${childItem.menuId}','${childItem.displayOrder}','up')" class="btn btn-mini" href="javascript:void(0);">↑</a>
											<a onclick="changeItemLocation('${menuItem.value.menuId}','${childItem.menuId}','${childItem.displayOrder}','down')" class="btn btn-mini" href="javascript:void(0);">↓</a>
										</div>
									</h5>
								</c:forEach>
							</div>
						</div>
				</c:when>
				<c:otherwise>
					<div id="childItems${menuItem.value.menuId}" style="display:none;height: 215px;">
						<div class="CODE_snippets">
							<div style="margin: 0 auto;width: 85px;">
								<a onclick="addChildItem('${menuItem.value.menuId}',-1,0,this)" class="btn btn-mini" href="javascript:void(0);" style="padding: 16px 36px;margin: 0 auto;">+</a>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>

</div>