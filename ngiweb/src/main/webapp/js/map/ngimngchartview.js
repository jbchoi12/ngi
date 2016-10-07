var chartArray = new Array();
var chart0 = null;
var chart1 = null;
var chart2 = null;
var chart3 = null;
var chart0_opt = null;
var chart1_opt = null;
var chart2_opt = null;
var chart3_opt = null;
var prev_type = null;



function chartDataReload(type, year) {
	prev_type = type;
	$.ajax({
		  type: "POST",
		  url: "../../ngi/mng/chartInfo.do",
		  data: { t: type, y: year },
		  success: function(data){
			  
			  if( type == 0 ) {
				  chart0.setTitle({text: "유형별 변경신고"});
				  chart1.setTitle({text: "월별 변경신고"});
				  chart2.setTitle({text: "변경신고 처리상태"});
				  chart3.setTitle({text: "년도별 변경신고"});
			  } else if( type==1 ) {
				  chart0.setTitle({text: "유형별 변동보고"});
				  chart1.setTitle({text: "월별 변동보고"});
				  chart2.setTitle({text: "변동보고 처리상태"});
				  chart3.setTitle({text: "년도별 변동보고"});
			  } else if( type==2 ) {
				  chart0.setTitle({text: "유형별 변화정보"});
				  chart1.setTitle({text: "월별 변화정보"});
				  chart2.setTitle({text: "사업지구별 건수"});
				  chart3.setTitle({text: "년도별 변화정보"});
			  }
				
			  chart0.xAxis[0].setCategories( data.chart0.categories, false);
			  chart0.series[0].name = data.chart0.series.name;
			  chart0.series[0].setData( data.chart0.series.data, false );
			  chart0.redraw();
			  
			  chart1.xAxis[0].setCategories( data.chart1.categories, false);
			  chart1.series[0].name = data.chart1.series.name;
			  chart1.series[0].setData( data.chart1.series.data, false );
			  chart1.redraw();
			  
//			  chart2.xAxis[0].setCategories( data.chart2.categories, false);
			  chart2.series[0].name = data.chart2.series.name;
			  chart2.series[0].setData( data.chart2.series.data, false );
			  chart2.redraw();
			  
			  chart3.xAxis[0].setCategories( data.chart3.categories, false);
			  chart3.series[0].name = "년도별 신고건수";
			  chart3.series[0].setData( data.chart3.series.data, false );
			  chart3.redraw();
			  
		  }
		})
}


$(window).load(function(){
	$('.chartContent').height( $('#mapContent').height() );
	
	chart0_opt = {
        chart: { type: 'column', renderTo: 'chart0' },
        title: { text: '유형별 변경신고' },
        xAxis: { type: 'category', labels: { rotation: -45, style: { fontSize: '12px', fontFamily: 'Verdana, sans-serif' } } },
        yAxis: { min: 0, title: { text: '신고건수' } },
        tooltip: { valueSuffix: '건', headerFormat: '<span style="font-size:12px">{point.key}</span><br/>'},
        legend: { enabled: false },
        series: [{ name: '', data: [ ['', 0], ],
            dataLabels: { enabled: true, rotation: -90, color: '#FFFFFF', align: 'right', x: 4, y: 10,
                style: { fontSize: '12px', fontFamily: 'Verdana, sans-serif', textShadow: '0 0 3px black' } } }],
        colors: ['#058DC7']
    };
	chart1_opt = {
		chart: {  renderTo: 'chart1' },
		title: { text: '월별 변경신고', x: -20 },
        xAxis: { categories: [''] },
        yAxis: { title: { text: '신고건수' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
        tooltip: { valueSuffix: '건', headerFormat: '<span style="font-size:12px">{point.key}</span><br/>'},
        plotOptions: { line: { dataLabels: { enabled: true }, enableMouseTracking: true } },
        series: [{ name: '신고건수', data: [0] }],
        colors: ['#A00000']
	};
	
	chart2_opt = { 
		chart: { plotBackgroundColor: null, plotBorderWidth: 1, plotShadow: false, renderTo: 'chart2' },
        title: { text: '신고접수 처리상태' },
        tooltip: { valueSuffix: '/{point.total}건', headerFormat: '<span style="font-size:12px">{point.key}</span><br/>'},
        plotOptions: {
            pie: { allowPointSelect: true, cursor: 'pointer', 
            	dataLabels: { enabled: true, format: '<b>{point.name}</b>: {point.y}건' } } },
        series: [{ type: 'pie', name: '', data: [ ['',   0] ] }]
	};
	
	chart3_opt = {
		chart: { type: 'column', renderTo: 'chart3' },
        title: { text: '년도별 변경신고' },
        xAxis: { categories: [ '' ] },
        yAxis: { min: 0, title: { text: '신고건수' } },
        tooltip: { valueSuffix: '건', headerFormat: '<span style="font-size:12px">{point.key}</span><br/>'},
        plotOptions: { column: { pointPadding: 0.2, borderWidth: 0 } },
        series: [{ name: '년도별 신고건수', data: [0] }],
        colors: [ '#6633CC']
	};
	
	chart0 = new Highcharts.Chart(chart0_opt);
	chart1 = new Highcharts.Chart(chart1_opt);
	chart2 = new Highcharts.Chart(chart2_opt);
	chart3 = new Highcharts.Chart(chart3_opt);
	
	chartArray.push(chart0);
	chartArray.push(chart1);
	chartArray.push(chart2);
	chartArray.push(chart3);
	
	chartResize();
	
	chartDataReload(0, $('select[name=chart_year] option:selected').val() );
	
});

$( window ).bind("resize", function() {
	chartResize();
});

function chartResize() {
	
	newH = $('#mapContent').height() / 2;
	newW = $('#mapContent').width() / 2;
	
	var index = 0;
	for(var top=0;top<2;top++) {
		for(var left=0;left<2;left++) {
			$('#chart'+index).css('top', newH*top);		// top
			$('#chart'+index).css('left', newW*left);	// left
			chartArray[index].setSize(newW-15, newH-15, false);		// width
//			$('#chart'+index).height();		// heigth
			index++;
		}
	}
	
}

$(document).ready(function(){
	
	$('#chart_year').bindSelect();
	
	$( "select[name=chart_year]" ).change(function () {
		chartDataReload(prev_type, $('select[name=chart_year] option:selected').val() );
	});
	  
	$('.charts_ul li').click(function(){
		var index = $('.charts_ul li').index($(this));
		$('.charts_ul li').removeClass('on');
		$(this).addClass('on');
		
		chartDataReload(index, $('select[name=chart_year] option:selected').val() );
		
//		$('.charts_content li div').removeClass('on');
//		$('.charts_content li div:eq('+index+')').addClass('on');
	});
	
});