<div>
<div id="childMenuItemsForRefresh">
		<c:forEach items="${applicationScope.MENU_ITEMS}" var="menuItem">
			<c:choose>
				<c:when test="${not empty menuItem.value.childMenuItems and fn:length(menuItem.value.childMenuItems)>0 and menuItem.value.menuId==parentMenuId}">
					<div class="album" id="childItems${menuItem.value.menuId}">
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
				<c:when test="${menuItem.value.menuId==parentMenuId}">
					<div id="childItems${menuItem.value.menuId}" style="height: 215px;">
						<div class="CODE_snippets">
							<div style="margin: 0 auto;width: 85px;">
								<a onclick="addChildItem('${menuItem.value.menuId}',-1,0,this)" class="btn btn-mini" href="javascript:void(0);" style="padding: 16px 36px;margin: 0 auto;">+</a>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
</div>
</div>