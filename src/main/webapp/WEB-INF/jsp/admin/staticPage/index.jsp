<div class="tabContent detailContent">
	<c:choose>
		<c:when test="${empty tabGroup }">
			<div>对不起，你没有权限访问该菜单下的任何页面</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="tab" items="${tabGroup}" varStatus="status">
				<c:if test="${status.first }">
					<c:set value="${tab }" var="forwardTab" />
				</c:if>
			</c:forEach>
			<c:redirect url="${forwardTab.url }"/>
		</c:otherwise>
	</c:choose>
</div>