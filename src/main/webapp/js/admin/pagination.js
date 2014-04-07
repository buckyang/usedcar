//get url of current page.
var url=location.href;

//deal pagination function
$("#paginationBox a").live("click", function(){
	var currentPageNumber = $(this).html();
	var currentPage = eval($("#currentPageTransient").val());
	if($(this).attr("id") == "previousPage"){
		currentPageNumber = currentPage - 1;
	}else if($(this).attr("id") == "nextPage"){
		currentPageNumber = currentPage + 1;
	}
   $("#currentPage").val(currentPageNumber);
   var formName = $("#formNameTransient").val();
   $("#"+formName).submit();
});

//initialization
$(document).ready(function(){
	
	hideSearchPageLink();
	
});

//hide the search page link in header.jsp
function hideSearchPageLink(){
	if(url.indexOf('login') > -1 || url.indexOf('queryCustomerAndOrder') > -1 || url.indexOf('deniedAccess') > -1){
		$("#searchPage").css("display","none");			
	}
}

