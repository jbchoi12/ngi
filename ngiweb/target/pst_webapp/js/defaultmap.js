//script
function setPng24(obj) {
  obj.width=obj.height=1;
  obj.className=obj.className.replace(/\bpng24\b/i,'');
  obj.style.filter =
  "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');";
  obj.src=''; 
  return '';
}

var fontSize = 12;
// fontResize
function fontResize(dct){
	if(dct == "up"){
		fontSize++;
	}else{
		fontSize--;
	}
	document.getElementById("content").style.fontSize = fontSize + "px";
}
// menuNone
function menuNone(){
	var a = document.getElementById("tnbMenu");
	var b = a.getElementsByTagName("div");
	for(i = 0; i < b.length; i++){
		b[i].style.display = "none";
	}
}
// menuOverAction
function menuOverAction(dct){
	menuNone();
	dct.getElementsByTagName("div")[0].style.display = "block";
}
// menuOutAction
function menuOutAction(dct){
	dct.getElementsByTagName("div")[0].style.display = "none";
	menuCurrent();
}
// menuCurrent
function menuCurrent(){
//	if(main == 0){
//		return false;
//	}
//	var a = document.getElementById("menu" + main);
//	var b = document.getElementById("subMenu" + main);
//	var c = b.getElementsByTagName("li");
//	a.className = "on";
//	b.style.display = "block";
//	c[sub - 1].className = "current";
}
window.onload = function() {
	  contentHeight();
	 }
	 window.onresize = function() {
	  contentHeight();
	 }
	 function contentHeight() {
	  if (document.getElementById) {
	   var windowHeight = getWindowHeight();
	   var headerHeight = document.getElementById('header').offsetHeight;
	   var footerHeight = document.getElementById('footer').offsetHeight;
	   var contentElement = document.getElementById('Mapcontainer');
	   //alert("windowHeight:"+windowHeight+",\nheaderHeight: "+headerHeight+", \nfooterHeight:"+footerHeight);
	   contentElement.style.height = (windowHeight - (headerHeight + footerHeight)) + 'px';
	   $('#mapContent').height( $('#Mapcontainer').height() - $('.mapTop').height() );
	   $('#mapContent').width( $('#Mapcontainer').width() - $('.MapLL').width() - $('.MapLR').width() - $('.MapRight').width() );
	   $('#mapContent').css('left', $('.MapLL').width() + $('.MapLR').width());
	  }
	 }
	 function getWindowHeight() {
	  var windowHeight = 0;
	  if (typeof(window.innerHeight) == 'number') {
	   windowHeight = window.innerHeight;
	  } else {
	   if (document.documentElement && document.documentElement.clientHeight) {
	    windowHeight = document.documentElement.clientHeight  ;
	   } else {
	    if (document.body && document.body.clientHeight) {
	     windowHeight = document.body.clientHeight;
	    }
	   }
	  }
	  return windowHeight;
	 }
