<html>
<head>
<link rel="stylesheet" type="text/css" href="/usedcar/css/managerUserInfo.css"></link>
<script src='<s:url value="/js/lib/jquery-1.7.2.min.js" />' type="text/javascript"></script>
<script src='<s:url value="/js/common/managerUserInfo.js" />' type="text/javascript"></script>
<script type="text/javascript">
	var selectYear=parseInt('${userInfoDTO.birthyear}');
	var selectMonth=parseInt('${userInfoDTO.birthmonth}');
	var selectDay=parseInt('${userInfoDTO.birthday}');
</script>
</head>
<body>
	<div id="resultMessage">
		<c:if test="${not empty message }">
			${message}
		</c:if>
	</div>
	<div class="form">
		<form:form modelAttribute="userInfoDTO" method="post"
			action="updateUserInfo">
			<div class="item">
				<span class="label"><em>*</em>昵称：</span>
				<div class="fl">
					<div>
						<form:input path="nickname" value="${userInfoDTO.nickname}"></form:input>
						<form:errors path="nickname" cssClass="fieldError" />
					</div>
				</div>

				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label"><em>*</em>性别：</span>
				<div class="fl">
					<form:radiobutton path="sex" value="true" />
					<label>男</label>
					<form:radiobutton path="sex" value="false" />
					<label>女</label>
					<form:errors path="sex" cssClass="fieldError" />
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label"><em>*</em>手机号码：</span>
				<div class="fl">
					<div>
					<c:if test="${not empty userInfoDTO.phone}">
						<form:input path="phone"
							value="${fn:substring(userInfoDTO.phone,0,3)}*****${fn:substring(userInfoDTO.phone,8,-1)}"
							disabled="true" cssStyle="background:transparent;border:0" />
					</c:if>
						<c:choose>
							<c:when
								test="${not empty userInfoDTO.bindPhone and userInfoDTO.bindPhone }">
								<a onclick="bindPhone();" class="smod" href="javascript:void(0);" style="color: #000000;">修改</a>
								<span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
							</c:when>
							<c:otherwise>
								<a onclick="bindPhone();" class="smod" href="javascript:void(0);" style="color: #000000;">立即绑定</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label">邮箱：</span>
				<div class="fl">
					<c:set var="point"
						value="${fn:indexOf(userInfoDTO.email,'@')}"></c:set>
					<c:set var="emailPrefix"
						value="${fn:substring(userInfoDTO.email,0,point)}"></c:set>
					<c:set var="emailSufix"
						value="${fn:substring(userInfoDTO.email,point+1,-1)}"></c:set>
					<div>
					<c:if test="${not empty userInfoDTO.email }">
						<form:input path="email"
							value="${fn:substring(emailPrefix,0,2)}*****${fn:substring(emailPrefix,point-2,-1)}@${emailSufix}"
							disabled="true" cssStyle="background:transparent;border:0" />
					</c:if>
						
						<c:choose>
							<c:when
								test="${not empty userInfoDTO.bindEmail and userInfoDTO.bindEmail }">
								<a onclick="bindMail();" class="smod" href="javascript:void(0);" style="color: #000000;">修改</a>
								<span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
							</c:when>
							<c:otherwise>
								<a onclick="bindMail();" class="smod" href="javascript:void(0);" style="color: #000000;">立即绑定</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label">真实姓名：</span>
				<div class="fl">
					<div>
						<form:input path="realName" />
						<form:errors path="realName" cssClass="fieldError" />
					</div>
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label">生日：</span>
				<div class="fl" id="birthDate">
					<form:select path="birthyear" id="birthYear" onchange="YYYYMM(this.value)">
						<form:option value="">请选择</form:option>
					</form:select>
					<form:select path="birthmonth" id="birthMonth" onchange="MMDD(this.value)">
						<form:option value="">请选择</form:option>
					</form:select>
					<form:select path="birthday" id="birthDay">
						<form:option value="-1">请选择</form:option>
					</form:select>
					<form:errors path="birthyear" cssClass="fieldError" />
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label">身份证号码：</span>
				<div class="fl">
					<c:choose>
						<c:when test="${not empty userInfoDTO.certificateNumber}">
							<c:set var="cerfificateNumberLen"
								value="${fn:length(userInfoDTO.certificateNumber)}"></c:set>
							<input id="certificateNumber"
								style="background: transparent; border: 0" disabled="disabled"
								value="${fn:substring(userInfoDTO.certificateNumber,0,3)}******${fn:substring(userInfoDTO.certificateNumber,cerfificateNumberLen-3,-1)}" />
							<form:input id="certificateValue" style="display:none"
								path="certificateNumber"
								value="${userInfoDTO.certificateNumber}" />
							<a href="javascript:void(0);"
								onclick="modifyCertificateNumber('certificateNumber')">修改</a>
						</c:when>
						<c:otherwise>
							<form:input path="certificateNumber" />
						</c:otherwise>
					</c:choose>
					<form:errors path="certificateNumber" cssClass="fieldError" />
				</div>
				<div class="clr"></div>
			</div>

			<div class="item">
				<span class="label">所在地：</span>
				<div class="fl">
					<form:select path="province">
						<form:option value="1">四川省</form:option>
						<form:option value="2">云南省</form:option>
					</form:select>
					<form:select path="city">
						<form:option value="1">成都市</form:option>
						<form:option value="2">绵羊市</form:option>
					</form:select>
					<form:select path="county">
						<form:option value="1">青羊区</form:option>
					</form:select>
				</div>
				<div class="clr"></div>
			</div>
			<div class="item">
				<span class="label">&nbsp;</span>
				<div class="fl">
					<form:input path="street" size="60" />
					<form:errors path="street" cssClass="fieldError" />
				</div>
				<div class="clr"></div>
			</div>
			<form:button value="submit">确认修改</form:button>
		</form:form>
	</div>
</body>
</html>