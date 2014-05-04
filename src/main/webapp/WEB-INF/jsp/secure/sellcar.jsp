<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript"
	src="<s:url value="/js/lib/jquery-1.11.0.min.js" />"></script>
<script type="text/javascript"
	src="<s:url value="/js/lib/ajaxfileupload.js" />"></script>

<script type="text/javascript">
	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url : '${request.contextPath}/product/imageupload',
			secureuri : false,
			fileElementId : 'image',
			dataType : 'text',
			success : function(data, status) {
				data = data.replace(/<pre.*?>/g, '');
				data = data.replace(/<PRE.*?>/g, '');
				data = data.replace("<PRE>", '');
				data = data.replace("</PRE>", '');
				data = data.replace("<pre>", '');
				data = data.replace("</pre>", '');
				if (data.substring(0, 1) == 0) {
					var imgTxt = document.createElement("img");
					$(imgTxt).attr("src", data.substring(2));
					var hiddenTxt = document.createElement("input");
					$(hiddenTxt).attr("type", "hidden");
					$(hiddenTxt).attr("name", "imageUrls");
					$(hiddenTxt).attr(
							"value",
							";0;" + data.substring(2) + "_jumbo.jpg;"
									+ data.substring(2) + "_large.jpg;"
									+ data.substring(2) + "_regular.jpg;"
									+ data.substring(2) + "_small.jpg;"
									+ data.substring(2) + "_thumbnail.jpg;");
					$("td[id='imageId']").append(imgTxt, hiddenTxt);
					alert("图片上传成功");
				} else {
					alert("图片上传失败，请重试！");
				}
			},
			error : function(data, status, e) {
				alert("图片上传失败，请重试！");
			}
		});
	}

	$(document).ready(function(){
		
		$("#brandId").change(
			function() {
				$("#seriesId>option").remove();
				$("#modelId>option").remove();
				$("#seriesId").append("<option>请选择车系</option>");
				$("#modelId").append("<option>请选择车型</option>");
				var brandId = $("#brandId").val();
				$.ajax({
					url: "${request.contextPath}/product/getSeries?brandId=" + brandId,
					success: function(data) {
						$.each(data,function(key,value) { 
							$("#seriesId").append("<option value="+key+">"+ value + "</option>");
			            }); 
					}
				});
			}
		);
		
		$("#seriesId").change(
			function() {
				$("#modelId>option").remove();
				$("#modelId").append("<option>请选择车型</option>");
				var seriesId = $("#seriesId").val();
				$.ajax({
					url: "${request.contextPath}/product/getModels?seriesId=" + seriesId,
					success: function(data) {
						$.each(data,function(key,value) { 
							$("#modelId").append("<option value="+key+">"+ value + "</option>");
				        }); 
					}
				});
			}
		);
		
	});
</script>

<div class="sell">
	<form:form method="POST" modelAttribute="usedCarDTO"
		enctype="multipart/form-data">
		<form:hidden path="productId" />
		<h3>基本信息</h3>
		<div class="basic-info">
			<div class="location">
				<label><span class="required">*</span>车辆交易地：</label>
				<div>
					<form:select path="provinceId" id="provinceId">
						<form:option value="28">四川</form:option>
					</form:select>
					<form:select path="cityId" id="cityId">
						<form:option value="225">成都</form:option>
					</form:select>
					<form:select path="countyId" id="countyId">
						<form:option value="">请选择区县</form:option>
						<form:options items="${countyMap}" />
					</form:select>
					<form:input path="street" id="street" />
					<form:errors path="provinceId" cssClass="error" />
					<form:errors path="cityId" cssClass="error" />
					<form:errors path="countyId" cssClass="error" />
					<form:errors path="street" cssClass="error" />
				</div>
			</div>

			<div class="type">
				<label><span class="required">*</span>车型：</label>
				<form:select path="brandId" id="brandId">
					<form:option value="">请选择品牌</form:option>
					<form:options items="${brandMap}" />
				</form:select>
				<form:select path="seriesId" id="seriesId">
					<form:option value="">请选择车系</form:option>
				</form:select>
				<form:select path="modelId" id="modelId">
					<form:option value="">请选择车型</form:option>
				</form:select>
				<form:errors path="brandId" cssClass="error" />
				<form:errors path="seriesId" cssClass="error" />
				<form:errors path="modelId" cssClass="error" />
			</div>

			<div class="purchase-date">
				<label><span class="required">*</span>初次购买时间：</label>

				<form:select path="purchaseDate" id="purchaseDate">
					<form:option value="">请选择年份</form:option>
					<form:option value="2009-10">2009-10</form:option>
					<form:option value="2010-10">2010-10</form:option>
				</form:select>
				<form:errors path="purchaseDate" cssClass="error" />
			</div>

			<div class="mile">
				<label><span class="required">*</span>表显里程：</label>
				<form:input path="odometer" id="odometer" />
				<span>万公里</span>
				<form:errors path="odometer" cssClass="error" />
			</div>

			<div class="price">
				<label><span class="required">*</span>预售价格：</label>
				<form:input path="listPrice" id="listPrice" />
				万
				<form:radiobutton path="priceType" value="可议价" />
				<span>可议价</span>
				<form:radiobutton path="priceType" value="一口价" />
				<span>一口价</span>
				<form:errors path="listPrice" cssClass="error" />
			</div>

			<div class="car-identity">
				<label><span class="required">*</span>车辆识别码/VIN：</label>
				<form:input path="carVin" id="carVin" />
				<span>由17位数字组成，大多车辆可在行驶证或者仪表板左侧、挡风玻璃下找到</span>
				<form:errors path="carVin" cssClass="error" />
			</div>

		</div>

		<h3>车辆外观</h3>
		<div class="appearance">
			<div class="images">
				<table>
					<tr>
						<td id="imageId"></td>
						<td><input type="file" name="image" id="image"
							onchange="ajaxFileUpload()"></td>
					</tr>
				</table>
			</div>
			<p>
				请点击相应的位置图片按照要求上传至少4张对应的车源图片，推荐上传800<span class="required">*</span>600额图片获得最佳显示，大小不超过2M
			</p>
		</div>

		<h3>车主信息</h3>
		<div class="owner">
			<div class="contactor">
				<label for="carContact"><span class="required">*</span>联系人：</label>
				<form:input path="carContact" id="carContact" />
				<form:errors path="carContact" cssClass="error" />
			</div>
			<div class="telphone">
				<label for="contactPhone"><span class="required">*</span>手机：</label>
				<form:input path="contactPhone" id="contactPhone" />
				<form:errors path="contactPhone" cssClass="error" />
			</div>
		</div>
		<label class="agreement"> <form:radiobutton path="acceptTerm"
				value="true" />我同意XXXX服务条款<form:errors path="acceptTerm"
				cssClass="error" /> <input type="submit" value="发布" />
		</label>
	</form:form>
</div>