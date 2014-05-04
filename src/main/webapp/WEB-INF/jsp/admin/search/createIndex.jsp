
<div class="tabContent detailContent">
	<div>
	  	<c:if test="${!empty message }">
	   		<div class="errorMsg"><span class="errorText"><s:message code="${message}"/></span></div>
	  	</c:if>
	</div>
	<div id="generalInfo">
		<div class="content largerHeight">
			<ul>
				<li><span>操作：</span><a href='<s:url value="/admin/search/deleteIndex"/>'>删除索引</a></li>
				<li><span>操作：</span><a href='<s:url value="/admin/search/buildIndex"/>'>创建测试索引</a></li>
			</ul>
		</div>
	</div>
	<form id="querySku" name="querySku" action="<s:url value="testQuery"/>" method="post">
		<fieldset>
			<div class="searchBox">
				<dl>
					<dt><label for="query">搜索关键字</label></dt>
					<dd><input name="query" type="text"/></dd>
				</dl>
				<dl>
					<dt>
						<span class="blackBtn"><input value="搜索" class="whiteText" type="submit"></span>
					</dt>
					<dd></dd>
				</dl>
			</div>
		</fieldset>
	</form>
</div>
<div class="tabFooter">
    <div class="left"></div>
    <div class="right"></div>
 </div> 