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

