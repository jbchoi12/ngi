
$(document).ready(function() {
  $("a.Logo");
  
  var subs = $("#Sidebar ul").attr("class");
  
  
	$("div.menuAll a").toggle(function(){
		$(".allView").slideDown("slow");
		$(this).addClass("current");
	},function(){
		$(".allView").slideUp("slow");
		$(this).removeClass("current");
	});
});