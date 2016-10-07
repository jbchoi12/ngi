function mapAction(region, area){
	$("#step2").attr("src", "/ngiweb/images/main/"+region+".png");
	//$("#step2txt").Text("");
	
	$(".mapdo").attr("Style","display:none;")
	$("#"+region).attr("Style","display:block;")

	$.ajax({
		url : "../../ngi/chg/changeInfoChartCntJson.do?area="+area,
		type : "get",
		dataType : "json",
		success : function(res){
			
			var data = res.result;
			var tempArray = [];
			
			if(data.length>0) {
				for(var index=0;index<data.length;index++) {
					var temp = [ data[index].code_nm, data[index].cnt*=1 ];
					tempArray.push(temp);
				}
			} else {
				var temp = [ '변경신고가 없습니다.', 0 ];
				tempArray.push(temp);
			}
			chartRedraw(tempArray, area);
		}
	});
}

var plot1 = null;

function chartRedraw(data, area) {
	
	var areaData = {
		"45" : "전라북도",
		"46" : "전라남도",
		"28" : "인천광역시",
		"11" : "서울특별시",
		"41" : "경기도",
		"26" : "부산광역시",
		"43" : "충청북도",
		"44" : "충청남도",
		"27" : "대구광역시",
		"30" : "대전광역시",
		"42" : "강원도",
		"29" : "광주광역시",
		"47" : "경상북도",
		"48" : "경상남도",
		"50" : "제주특별자치도",
		"36" : "세종특별자치시",
		"31" : "울산광역시"
	};
	
	
	if( data == undefined ) {
		data = [ [ '변경신고가 없습니다.', 0 ] ];
	} 
	
	if(plot1 != null) {
		plot1.destroy();
	}
	
	plot1 = jQuery.jqplot ('chart1', [data], 
	    { 
	      seriesDefaults: {
	        // Make this a pie chart.
	        renderer: jQuery.jqplot.PieRenderer, 
	        rendererOptions: {
	          // Put data labels on the pie slices.
	          // By default, labels show the percentage of the slice.
	          showDataLabels: true,
	        }
	      }, 
	      grid: { background: '#ffffff', borderColor: '#ffffff' },
	      title: { text:areaData[area], show:true },
	      legend: { show:true, location: 'e' }
	    }
	  );
}

$(document).ready(function(){
	mapAction('gyeonggi','41');
});





