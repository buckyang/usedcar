(function($){
	var settings = {
		headerHeight: 58,
		footerHeight: 40, 
		leftNavigat: '.left .leftNavigat',
		miniHeight: 620,
		miniWidth: 988,
		NavigatMarginBottom: 30,
		marginHeight: 20,
		mainiFrame: '#mainIframe',
		openSizeWidth: 800,
		openSizeHeight: 630
	};
	
	$.fn.webStore = function (options) {
		$.webStore();
	};
	
	$.webStore = function(options) {
		$.webStore.options = $.extend({}, settings, options);
		var leftNavigat = $.webStore.options.leftNavigat;
	};
	
	$.extend($.webStore,{
		setLeftNavigatHeight: function() {
			var leftNavigat = $.webStore.options.leftNavigat,
				bodyHeight = $(window).height(),
				bodyWidth = $(window).width();
			if($.webStore.options.miniHeight < bodyHeight) {
				var leftNavigatHeight = bodyHeight - $.webStore.options.headerHeight - $.webStore.options.footerHeight ;
				$('html').css('overflow-y','hidden');
			}else {
				var leftNavigatHeight = $.webStore.options.miniHeight - $.webStore.options.headerHeight - $.webStore.options.footerHeight;
				$('html').css('overflow-y','auto');
			}
			if($.webStore.options.miniWidth < bodyWidth) {
				var webWidth = bodyWidth;
				$('html').css('overflow-x','hidden');
			}else {
				var webWidth = $.webStore.options.miniWidth;
				$('html').css('overflow-x','auto');
			}
			$(leftNavigat).height(leftNavigatHeight - $.webStore.options.NavigatMarginBottom);
			$("#mainIframe").height(leftNavigatHeight - $.webStore.options.marginHeight);
			$("#main").width(webWidth);
			$("#header").width(webWidth);
			$("#footer").width(webWidth);
		},
		
		setNavigatStyle: function(nav) {
			var childItems = $(nav).next();
			$($.webStore.options.leftNavigat).find('dl dd').hide();
			$(childItems).show();
			$(nav).next().find('a:first').click();
		},
		
		setNavigatChecked: function(current) {
			var curr = $(current);
			curr.parent().find('a.checked').removeClass('checked');
			curr.addClass('checked');
			$.webStore.setIframeSrc(curr.attr('href'));
		},
		
		closeLeftNavigat: function() {
			$($.webStore.options.leftNavigat).find('dl dd').hide();
		},
		
		setIframeSrc: function(href) {
			var iframe = $.webStore.options.mainiFrame;
			$(iframe).attr("src",href);
		},
		
		showDetail: function(current) {
			if($(current).hasClass('clicked')) {
				$(current).next().fadeOut('fast');
				$(current).removeClass('clicked');
			}else {
				$(current).addClass('clicked');
				$(current).next().fadeIn('fast');
			}
		},
		
		setTabHigh: function(current) {
			var curr = $(current);
			curr.parent().find('li.selected').removeClass('selected');
			curr.addClass('selected');
			$(".detailContent > div").addClass('hidden');
			$("#" + curr.attr('str')).removeClass('hidden');
			$.webStore.closeInlinePopup();
		},
		
		windowOpenLink:function(obj,options) {
			$.webStore.options = $.extend({}, settings, options);
			var src = $(obj).attr('href'),
				oWidth = $.webStore.options.openSizeWidth,
				oHeight = $.webStore.options.openSizeHeight,
				scrollHeight = document.documentElement.scrollTop,
				bodyHeight = $(window.top).height(),
				bodyWidth = $(window.top).width(),
				oTop = (bodyHeight - oHeight)/2 + scrollHeight*1,
				oLeft = (bodyWidth - oWidth)/2;
			window.open (src,"_blank", "height="+oHeight+", width="+oWidth+", top="+oTop+",left="+oLeft+", toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no")
		},
		
		inlinePopup: function(current) {
			$.webStore.closeInlinePopup();
			var src = $(current).attr('href'),
				title = !$(current).attr('title')?'No description' : $(current).attr('title'),
				container = "<div class='inlinePopup'>";
			container += "<div class='title'>"; 
			container += "<h3>" + title + "</h3>";
			container += "<a href='javascript:void(0)' class='close'>close</a>";
			container += "</div>";
			
			container += "<div class='content'>";
			container += "<img src='" + src + "' onError='javascript:$(\".inlinePopup .content\").html(\"<h4>Not Found</h4><div>the requested image was not found on this server</div>\")'/>" ;
			container += "</div>";
		
			container += "</div>";
			$('body').append(container);
			var isLoad = false;
			$(".inlinePopup img").load(function(){
				$.webStore.inlinePopupPosition(); 
				isLoad = true;
			});
			if(!isLoad){
				$.webStore.inlinePopupPosition(); 
			}
					
		},
		
		inlinePopupPosition: function() {
			var bodyHeight = $(window).height(),
				bodyWidth = $(window).width(),
				scrollHeight = document.documentElement.scrollTop,
				containerHeight = $('.inlinePopup').height(),
				containerWidth = $('.inlinePopup').width();
			$('.inlinePopup').css({
				"left" : (bodyWidth-containerWidth)/2 + "px",
				"top" : (bodyHeight-containerHeight)/2 + scrollHeight*1 + "px",
				"opacity" : '1'
			});
		},
		
		closeInlinePopup: function() {
			if($('.inlinePopup')){
				$('.inlinePopup').detach();
			}
		}
	})
})(jQuery)

$(window).webStore();