(function($){
	$(window).load(function(){
		$.webStore.setLeftNavigatHeight();
	});
	$(window).resize(function(){
		$.webStore.setLeftNavigatHeight();
	});
	var leftNav = $('.left .leftNavigat')
	leftNav.find('dl dt').live('click',function(){
		$.webStore.setNavigatStyle(this);
	});
	leftNav.find('dl dd a').live('click',function(e){
		e.preventDefault();
		$.webStore.setNavigatChecked(this);
	})
	$('#backToHome a').live('click',function(e){
		e.preventDefault();
		$.webStore.setIframeSrc($(this).attr('href'));
		$.webStore.closeLeftNavigat();
	})
	$('#switchSite .siteChangeSelect').live('change',function(){
		$('#switchSite').submit();
	})
})(jQuery)