function sendMessage(phoneNumberInputId,codeType){
	if(phoneNumberInputId==null||$.trim(phoneNumberInputId)==''||codeType==null||$.trim(codeType)==''){
		alert("Phone number can not be empty ");
	}
     var phoneNumber=$("#"+phoneNumberInputId).val();
     $.ajax({
			type : "POST",
			url : "obtainCode",
			data : "phoneNumber=" + phoneNumber + "&type=" + codeType,
			success : function(data) {
				var result=$(data).find("#sendMessageResult");
      			if($.trim($(result).html())=='true'){
      				alert('send message success');
      			}else{
      				alert($(result).html());
      			}
			}
		});
}