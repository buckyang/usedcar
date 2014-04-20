var selectItemId="";
function showChildItems(id,title){
	$("#childItems"+selectItemId).hide();
	$("#childItems"+id).show();
	$("#topMenuTitleId").val(title);
	$("#topMenuTitleId").attr("name",id);
	selectItemId=id;
}

function editTopMenuTitle(type){
	if(type=='edit'){
		$("#topMenuTitleId").attr("disabled",false);
		$("#topMenuEditBtn").hide();
		$("#topMenuSubmitBtn").show();
	}else if(type=='submit'){
		var newTitle = $("#topMenuTitleId").val();
		var menuId=$("#topMenuTitleId").attr("name");
		var parentMenuId=-1;
		$.ajax({
			type : "POST",
			url : "updateItemTitle",
			data : "menuId=" + menuId + "&title=" + newTitle
					+ "&parentMenuId=" + parentMenuId,
			success : function(data) {
				$("#topMenuTitleId").attr("disabled",true);
				$("#topMenuEditBtn").show();
				$("#topMenuSubmitBtn").hide();
				$("#topMenuId"+menuId).html(newTitle);
			}
		});
	}
	
}

function addChildItem(parentMenuId,childMenuId,displayOrder,self){
		var menuTitle=prompt('Please input menu title');
		if(menuTitle==null||$.trim(menuTitle)==''){
			return;
		}
		$("#menuItemTitle").val(menuTitle);
		$("#menuItemContents").val(null);
		$("#menuItemParentMenuId").val(parentMenuId);
		$("#menuItemDisplayOrder").val(parseInt(displayOrder)+1);
		$("#menuItemEditAllProperty").val(true);
		var options ={   
                type:'post',                    
                success:function(data){
                alert(data);
	                var childItems=$(data).find("#childMenuItemsForRefresh");
	      			if(childItems.length>0){
	      				$("#childItems"+parentMenuId).html($(childItems).html());
	      			}
                }   
             };
         $("#managerMenuForm").ajaxSubmit(options);  
}

function deleteChildItem(parentMenuId,childMenuId){
	$.ajax({
		   type: "POST",
		   url: "deleteItem",
		   data: "menuId="+childMenuId+"&parentMenuId="+parentMenuId,
		   success: function(data){
			   var childItems=$(data).find("#childMenuItemsForRefresh");
			   if(childItems.length>0){
				   $("#childItems"+parentMenuId).html($(childItems).html());
			   }
		   	}
		});
}


	function editChildItem(parentMenuId, menuId, self) {
		if ($(self).html() == 'e') {
			$("#childItemTitle" + menuId).attr("disabled", false);
			$(self).html('√');
		} else {
			var newTitle = $("#childItemTitle" + menuId).val();
			$("#childItemTitle" + menuId).attr("disabled", true);
			$.ajax({
				type : "POST",
				url : "updateItemTitle",
				data : "menuId=" + menuId + "&title=" + newTitle
						+ "&parentMenuId=" + parentMenuId,
				success : function(data) {
					var childItems = $(data).find("#childMenuItemsForRefresh");
					if (childItems.length > 0) {
						$("#childItems" + parentMenuId).html(
								$(childItems).html());
					}
					$(self).html('e');
				}
			});
		}
	}

	function changeItemLocation(parentMenuId,menuId, order, type) {
		if (type == 'up') {
			var self = $("#childItem_" + menuId);
			var prevNode = self.prev();
			if (prevNode.length < 1) {
				alert('current menu is first menu');
			} else {
				var prevNodeId = $(prevNode).attr('id').split('_')[1];
				var prevOrder = $(prevNode).attr('name');
				$.ajax({
					   type: "POST",
					   url: "exchangeItemOrder",
					   data: "sourceMenuId="+menuId+"&sourceMenuOrder="+order+"&targetMenuId="+prevNodeId+"&targetMenuOrder="+prevOrder+"&parentMenuId="+parentMenuId,
					   success: function(data){
						   var childItems=$(data).find("#childMenuItemsForRefresh");
						   if(childItems.length>0){
							   $("#childItems"+parentMenuId).html($(childItems).html());
						   } 
					   	}
					});
			}
		} else {
			var self = $("#childItem_" + menuId);
			var nextNode = self.next();
			if (nextNode.length < 1) {
				alert('current menu is last menu');
			} else {
				var nextNodeId = $(nextNode).attr('id').split('_')[1];
				var nextOrder = $(nextNode).attr('name');
				$.ajax({
					   type: "POST",
					   url: "exchangeItemOrder",
					   data: "sourceMenuId="+menuId+"&sourceMenuOrder="+order+"&targetMenuId="+nextNodeId+"&targetMenuOrder="+nextOrder+"&parentMenuId="+parentMenuId,
					   success: function(data){
						   var childItems=$(data).find("#childMenuItemsForRefresh");
						   if(childItems.length>0){
							   $("#childItems"+parentMenuId).html($(childItems).html());
						   }
					   	}
					});

			}
		}
	}