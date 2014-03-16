<html>
<head>
<link rel="stylesheet" type="text/css" href="/usedcar/css/managerUserInfo.css"></link>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script  type="text/javascript">
function modifyCertificateNumber(id){
	var sourceValue=${managerUserInfoDTO.certificateNumber}
	$("#"+id).removeAttr("style");
	$("#"+id).val(sourceValue);
}
</script>
</head>
<body>
<div id="resultMessage">
<c:if test="${not empty message }">
${message}
</c:if>
</div>
<div class="form">
<form:form modelAttribute="managerUserInfoDTO" method="post" action="managerUserInfo">
								<div class="item">
									<span class="label"><em>*</em>昵称：</span>
									<div class="fl">
										<div>
											<form:input path="nickname" value="${managerUserInfoDTO.nickname}"></form:input>
                                            <form:errors path="nickname" cssClass="fieldError" />
										</div>
									</div>

									<div class="clr"></div>
								</div>
								
								<div class="item">
									<span class="label"><em>*</em>性别：</span>
									<div class="fl">
										<form:radiobutton path="sex" value="true"/><label>男</label> 
										<form:radiobutton path="sex" value="false"/><label>女</label>
										<form:errors path="nickname" cssClass="fieldError" /> 
									</div>
									<div class="clr"></div>
								</div>
								
								<div class="item">
									<span class="label"><em>*</em>手机号码：</span>
									<div class="fl">
										<div><form:input path="phone" value="${fn:substring(managerUserInfoDTO.phone,0,3)}*****${fn:substring(managerUserInfoDTO.phone,8,-1)}" disabled="true" cssStyle="background:transparent;border:0"/>
											<c:choose>
												<c:when test="${not empty managerUserInfoDTO.bindPhone and managerUserInfoDTO.bindPhone }">
													<a target="_blank" class="smod" href="">修改</a><span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
												</c:when>
												<c:otherwise>
													<a target="_blank" class="smod" href="">立即绑定</a>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="clr"></div>
								</div>
								
								<div class="item">
									<span class="label">邮箱：</span>
									<div class="fl">
										<c:set var="point" value="${fn:indexOf(managerUserInfoDTO.email,'@')}"></c:set>
										<c:set var="emailPrefix" value="${fn:substring(managerUserInfoDTO.email,0,point)}"></c:set>
										<c:set var="emailSufix" value="${fn:substring(managerUserInfoDTO.email,point+1,-1)}"></c:set>
										<div><form:input path="email" value="${fn:substring(emailPrefix,0,2)}*****${fn:substring(emailPrefix,point-2,-1)}@${emailSufix}" disabled="true" cssStyle="background:transparent;border:0"/>
											<c:choose>
												<c:when test="${not empty managerUserInfoDTO.bindEmail and managerUserInfoDTO.bindEmail }">
													<a target="_blank" class="smod" href="">修改</a><span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
												</c:when>
												<c:otherwise>
													<a target="_blank" class="smod" href="">立即绑定</a>
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
											<form:input path="realName"/>
											<form:errors path="realName" cssClass="fieldError" /> 
										</div>
									</div>
									<div class="clr"></div>
								</div>
								
                                <div class="item">
									<span class="label">生日：</span>
									<div class="fl">
										<form:input path="birthdate"/>
										<form:errors path="birthdate" cssClass="fieldError" /> 
									</div>
									<div class="clr"></div>
								</div>
							
								<div class="item">
									<span class="label">身份证号码：</span>
									<div class="fl">
										<c:choose>
												<c:when test="${not empty managerUserInfoDTO.certificateNumber}">
													<c:set var="cerfificateNumberLen" value="${fn:length(managerUserInfoDTO.certificateNumber)}"></c:set>
													<form:input id="certificateNumber" cssStyle="background:transparent;border:0" path="certificateNumber" value="${fn:substring(managerUserInfoDTO.certificateNumber,0,3)}******${fn:substring(managerUserInfoDTO.certificateNumber,cerfificateNumberLen-3,-1)}"/><a href="javascript:void(0);" onclick="modifyCertificateNumber('certificateNumber')">修改</a>
												</c:when>
												<c:otherwise>
													<form:input path="certificateNumber"/>
												</c:otherwise>
										</c:choose>
									<form:errors path="certificateNumber" cssClass="fieldError" />
									</div>
									<div class="clr"></div>
								</div>
								
								<div class="item">
									<span class="label">所在地：</span>
									<div class="fl">
										<form:select path="homeAddress.province"><form:option value="1">四川省</form:option><form:option value="2">云南省</form:option></form:select>
										<form:select path="homeAddress.city"><form:option value="1">成都市</form:option><form:option value="2">绵羊市</form:option></form:select>
										<form:select path="homeAddress.county"><form:option value="1">青羊区</form:option></form:select>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">&nbsp;</span>
									<div class="fl">
										<form:input path="homeAddress.street" size="60"/>
										<form:errors path="homeAddress.street" cssClass="fieldError" />
									</div>
									<div class="clr"></div>
								</div>
							<form:button value="submit">确认修改</form:button>							
							</form:form>
							</div>
	</body>
	</html>