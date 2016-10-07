// 위치로 주소검색을 위한 컨트롤 커스텀.
OpenLayers.Control.regAddrPointControl = OpenLayers.Class(OpenLayers.Control, {                
    defaultHandlerOptions: {
        'single': true,
        'double': false,
        'pixelTolerance': 0,
        'stopSingle': false,
        'stopDouble': false
    },
    initialize: function(options) {
        this.handlerOptions = OpenLayers.Util.extend(
            {}, this.defaultHandlerOptions
        );
        OpenLayers.Control.prototype.initialize.apply(
            this, arguments
        ); 
        this.handler = new OpenLayers.Handler.Click(
            this, {
                'click': this.trigger
            }, this.handlerOptions
        );
    }, 
    trigger: function(e) {
        //var lonlat = map.getLonLatFromPixel(e.xy);
        regAddrPointCallback(e);
    }
});

var search_markers;
var regAddrPointControl;

function search_createMarker(name, xpos, ypos){
	
	if( search_markers != null )
		search_markers.destroy();
	
    search_markers = new OpenLayers.Layer.Markers( "search_markers" );
    map.addLayer(search_markers);
    var lonlat = new OpenLayers.LonLat(xpos, ypos).transform('EPSG:4326', 'EPSG:5179');
    var size = new OpenLayers.Size(40,40);
    var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    var icon = new OpenLayers.Icon('../../js/openlayers/imgs/marker_test.png', size, offset);
    search_markers.addMarker(new OpenLayers.Marker(lonlat,icon));
    icon.imageDiv.title = name;
    //markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(0,0),icon.clone()));
    map.setCenter(lonlat, 14);
}

// 주소 검색 결과.
function addrResultCallback(req) {
    context = $('#SerarchLi');
    context.empty();
    if( req.ERROR != null ) {
    	context.append(req.ERROR);
    } else if( req.result != null ) {
    	context.append(req.result);
    } else {
        /*
         * NCODE: "6244"
         * PNU: "4159010100020000000
         * "RD_NM: "시청로"
         * WEIGHT: "260"
         * ZIP_CL: "445-702"
         * codeName: "행정기관 > 도/시/구/군청 > 시청"
         * juso: "경기 화성시 남양동 2000"
         * nId: "35449600"
         * nameDp: " "
         * nameFull: "화성시청"
         * njuso: "경기도 화성시 시청로 159"
         * xpos: "126.831332"
         * ypos: "37.199644"
         */
        $(req.LIST).each(function(index, data) {
        	
        	// $("<div></div>").attr('id','new').appendTo('body');   
        	
        	var textHtml = document.createElement('li');
        	$(textHtml).attr('onclick', 'javascript:search_loca_poi(this);');
        	$(textHtml).append('<p class="title"><a href="#">' + data.nameFull+ '</a><a href="#"> <img src="../../images/common/search_de.png" alt="상세페이지" title="상세페이지"/></a></p>');
        	$(textHtml).append('<p class="Oldaddress">지번) ' + data.juso + '</p>');
        	$(textHtml).append('<p class="Newaddress">도로명) ' + data.njuso + '</p>');
        	$(textHtml).append('<input type="hidden" name="nameFull" value="'+data.nameFull+ '('+data.nameDp+')">');
			$(textHtml).append('<input type="hidden" name="xpos" value="'+data.xpos+'">');
			$(textHtml).append('<input type="hidden" name="ypos" value="'+data.ypos+'">');
        	
        	//<input type="hidden" name='ypos_143302100' id='ypos_143302100' value="37.199783">
        	context.append(textHtml);
		});
        
        /*
         * "paginationInfo" : 
         * 		{"currentPageNo" : "1", 	// 잘못된값 리턴됨		pageUnit=10 에 고정된 값으로만 맞음.
         * 		"firstPageNo" : "1",		// 잘못된값 리턴됨
         * 		 "lastPageNo" : "52",	 // 잘못된값 리턴됨
         * 		 "totalPageCount" : "52",  // 잘못된값 리턴됨
         * 		 "totalRecordCount" : "517"}
         */
        $('#show_search_page').empty();
        var page = req.paginationInfo;
        // 페이징
        
        var block_set = 5;	// 한페이지에 보여질 페이지
        
        block = Math.ceil (page.currentPageNo / block_set); // 현재블럭(올림함수) 
        first_page = ((block - 1) * block_set) + 1; // 첫번째 페이지번호 
        last_page = Math.min(page.totalPageCount, block * block_set); // 마지막 페이지번호 
              
        $('#SerarchTt').html('<button type="button" class="AXButtonSmall Classic" style="margin-right:5px;" alt="지우기" onclick="search_reset();"><i class="fa fa-repeat fa-lg"></i></button>');
        $('#SerarchTt').append('총 <span>'+ page.totalRecordCount +'</span>건이 검색되었습니다.');
        var p = $('<p style="text-align: center;"></p>');
        if( page.totalRecordCount > 0 ) {
        	if( first_page > block_set ) {
	        	prevNo = first_page - 1;
	        	p.append('<span style="margin:4px;"><a href="#" onclick="javascript:searchResultPage('+prevNo+');"><<</a></span>');
        	}
	        for(var index=first_page;index<=last_page;index++) {
	        	text = page.currentPageNo == index ? '<b>'+index+'</b>' : index;
	        	p.append('<span style="margin:4px;"><a href="#" onclick="javascript:searchResultPage('+index+');">'+text+'</a></span>');
	        }
	        if( page.lastPageNo > last_page ) {
	        	nextNo = last_page + 1;
	        	p.append('<span style="margin:4px;"><a href="#" onclick="javascript:searchResultPage('+nextNo+');">>></a></span>');
        	}
        }
        $('#show_search_page').append(p);
        
       	if( $('#MapLR').css('display') == 'none' ) 
       		$("#MapLeftWin_close").click();
       	
    }
    
    $(".MapLL li")[0].click();
    $('#SearchList').show();
    $('#show_search_page').show();
    $('#show_legent').hide();
    $('#show_chart').hide();
    $('#show_fav').hide();
    
}

// 주소검색 결과 페이징
function searchResultPage(page) {
	search_text = $("#search-input").val();
	var reqHost = 'http://map.vworld.kr/search.do';
    var param = 'q='+search_text+'&apiKey='+vworld_key+'&category=Poi';
    param = param + '&output=json&pageUnit=8&pageIndex=' + page;
    
    $.ajax({
        url : reqHost,
        data : param,
        dataType : "jsonp",
        //jsonp : "callback",
        success : addrResultCallback
    });
    
}

//주소검색 마커 생성.
function search_loca_poi(e){
	var name = $(e).find( "input:hidden[name='nameFull']" ).val();
	var xpos = $(e).find( "input:hidden[name='xpos']" ).val();
	var ypos = $(e).find( "input:hidden[name='ypos']" ).val();
   	search_createMarker(name, xpos, ypos);
};

function coord2addr(lon, lat) {
    //      http://apis.vworld.kr/coord2jibun.do?x=[X좌표]&y=[Y좌표]&apiKey=[인증키]
    //      &domain=[도메인]&output=[리턴타입]&epsg=[좌표계]&callback=[func]
    // 새주소.
    reqHost = 'http://apis.vworld.kr/coord2new.do';
    param = 'x='+lon+'&y='+lat+'&apiKey='+vworld_key+'&domain='+vworld_domain;
    param = param + '&output=json&epsg=EPSG:4326';
    
    $.ajax({
        url : reqHost,
        data : param,
        dataType : "jsonp",
        success : function(data) {
        	
        	if( data.NEW_JUSO != undefined ) { 
    			$('#iframeChgSttemntinfo').contents().find('#changeRnAdresCn').val(data.NEW_JUSO);
    			//$('#iframeChgSttemntinfo').contents().find('input[name="addrTy"]').val("02");
        	} else {
        		$('#iframeChgSttemntinfo').contents().find('#changeRnAdresCn').val('검색결과가 없습니다.');
        	}
        	//else {
        	    // 지번주소 
        	    var reqHost = 'http://apis.vworld.kr/coord2jibun.do';
        	    param = 'x='+lon+'&y='+lat+'&apiKey='+vworld_key+'&domain='+vworld_domain;
        	    param = param + '&output=json&epsg=EPSG:4326';
        	    $.ajax({
        	        url : reqHost,
        	        data : param,
        	        dataType : "jsonp",
        	        //jsonp : "callback",
        	        success : function(data) {
        	        	if(data.ADDR != undefined) {
        	        		$('#iframeChgSttemntinfo').contents().find('#changeLnmAdresCn').val(data.ADDR);
        	        		//$('#iframeChgSttemntinfo').contents().find('input[name="addrTy"]').val("01");
        	        	}
        	        	else if(data.result != undefined) {
        	        		$('#iframeChgSttemntinfo').contents().find('#changeLnmAdresCn').val(data.result);
        	        		//$('#iframeChgSttemntinfo').contents().find('input[name="addrTy"]').val("01");
        	        	}
        	        }
        	    });
        	//}
        	var lonlat = new OpenLayers.LonLat(lon, lat).transform('EPSG:4326', 'EPSG:5179');
        	setXCrdntLoYCrdntLa(lonlat);
        }
    });
    regAddrPointControl.deactivate();
}

// 변동신고 위치기반입력 주소가져오기.
function regAddrPoint() {
	
	load = false;
	  for(v=0;v<parent.map.controls.length;v++) {
		obj = map.controls[v];
		if( obj instanceof OpenLayers.Control.regAddrPointControl ) {
			load = true;
		}
	};
	if( load == false ) {
		regAddrPointControl = new OpenLayers.Control.regAddrPointControl();
		map.addControl(regAddrPointControl);
	}	
	toggleControl_action('point2');
	regAddrPointControl.activate();
}

function regAddrPointCallback(evt) {

	toggleControl_action('reset');
	
    var lonlat = map.getLonLatFromViewPortPx(evt.xy);
    lonlat = new OpenLayers.LonLat(lonlat.lon, lonlat.lat).transform('EPSG:5179', 'EPSG:4326');
    
    coord2addr(lonlat.lon, lonlat.lat);
    // 마커.
    if(search_markers!=null)
    	search_markers.destroy();
    
    search_markers = new OpenLayers.Layer.Markers( "search_markers" );
    map.addLayer(search_markers);
    
    var lonlat = map.getLonLatFromViewPortPx(evt.xy);
    var size = new OpenLayers.Size(40,40);
	var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	var icon = new OpenLayers.Icon('../../js/openlayers/imgs/marker_test.png', size, offset);
	search_markers.addMarker(new OpenLayers.Marker(lonlat,icon));
	
	// pnu 구하기.
	var tempPoint = map.getLonLatFromViewPortPx(evt.xy);
	var pnuForLonLat = new OpenLayers.LonLat(tempPoint.lon, tempPoint.lat).transform('EPSG:5179', 'EPSG:4326');
	//console.log(pnuForLonLat);
	var geomPoint = 'point('+ pnuForLonLat.lon + '%20' + pnuForLonLat.lat + ')';
	
	var reqURL = 'http://apis.vworld.kr/2ddata/cadastral/data?';
	var reqParam = 'apiKey=' + vworld_key + '&geometry=' + geomPoint + '&domain='+ vworld_domain
			+ '&output=json&propertyname=pnu,addr'
			+ '&buffer=1&pageIndex=1&pageUnit=1';
	
	var request = OpenLayers.Request.GET({
	    url: reqURL+reqParam,
	    proxy: proxy_url,
	    callback: function(request) {
	    	
	    	var err = request.responseText.search("NullPointerException");
	    	if(err < 0) {
	    		var data = JSON.parse(request.responseText);
	    		pnu = data.featureCollection.features[0].properties.pnu;
	        	if(pnu.length>0) {
	        		$('#iframeChgSttemntinfo').contents().find('input[name="pnuCd"]').val(pnu);
	        		$('#iframeChgCntrwkinfo').contents().find('input[name="pnuCd"]').val(pnu);
	        	}
	    	} else {
	    		$('#iframeChgSttemntinfo').contents().find('input[name="pnuCd"]').val("");
	        	$('#iframeChgCntrwkinfo').contents().find('input[name="pnuCd"]').val("");
	    	}
	    	
	    }
	});
	
//	
//	$.ajax({
//		type: 'GET',
//        //url : '../../proxy.jsp?resourceUrl=' + encodeURIComponent(reqURL + reqParam),
//		url : reqURL + reqParam,
//        data : null,
//        dataType : "json",
//        success : function(data) {
//        	//console.log('pnu: ' + data.featureCollection.features[0].properties.PNU);
//        	//console.log('addr: ' + data.featureCollection.features[0].properties.ADDR);
//        	pnu = data.featureCollection.features[0].properties.PNU;
//        	if(pnu.length>0) {
//        		$('#iframeChgSttemntinfo').contents().find('input[name="pnuCd"]').val(pnu);
//        		$('#iframeChgCntrwkinfo').contents().find('input[name="pnuCd"]').val(pnu);
//        	}
//        }, error: function(err) {
//        	$('#iframeChgSttemntinfo').contents().find('input[name="pnuCd"]').val("");
//        	$('#iframeChgCntrwkinfo').contents().find('input[name="pnuCd"]').val("");
//        }
//	});
	
}

function search_reset() {
	if( search_markers != null )
		search_markers.destroy();
	$('#search-input').val('');
	$('#SerarchTt').empty();
	$('#SerarchTt').text('검색 위치명을 입력하세요.');
	$('#SerarchLi').empty();
	$('#show_search_page').empty();
	$('#show_search_page').css('display', 'block');
}


/** 켜져있는 레이어 구하기. **/
function showLayers() {
	var value = "";
	$.each(map.layers, function(k, v) {
		if( v.getVisibility() == true ) {
			style = (v.style == undefined && v.style == null)  ? "" : v.style;
			layername = v.name;
			
			if(v instanceof OpenLayers.Layer.Vector) {
				// 벡터정보 제외.
			} else if (layername == "NGII_EMAP" ) {
				// 베이스맵은 제외.
			} else {
				if( value == "" )
					value = value + layername + ',' + style;
				else 
					value = value + '#' + layername + ',' + style;
			}
			
//			$.each(layers, function(i, data) {
//				if( data.title == v.name ) {
//					//console.log(data.layername);
//					if( value == "" )
//						value = value + data.layername + ',' + data.style;
//					else 
//						value = value + '#' + data.layername + ',' + data.style;
//				}
//			});
		}
	});	
	return value;
}

/** 화면에 그려져있는 벡터 정보 가져오기 **/
function getVectorInfo() {
	var vectorString = '';
	var vectors = [];
	if(vectorList.length>0) {
		vectors = vectorList;
	}
	$.each(map.layers, function(k,layer) {
		// 마커정보 가져옴.
		if( layer instanceof OpenLayers.Layer.Markers ) {
			// 마커.
			if( layer.markers.length > 0 ) {
				var lonlat = layer.markers[0].lonlat;
				var geom = new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat);
				var wkt = geom.toString();
				//console.log(wkt);
				vectors.push(wkt);
			}
		}
	});
	
	$.each(vectors, function(index, wkt) {
		vectorString = vectorString + wkt + '#';
	});
	
	return vectorString;
}

/**
 * 이미지저장.
 */
function saveScreenimg() {
	var mapHeight = $('#mapContent').height(); 	// 지도영역 
	var mapWidth = $('#mapContent').width();		// 지도 영역 
	
	// base map url
	var base_url = 'http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/tile';
	var saveImgURL = '../../ngi/mng/map_saveimg.do';	// 이미지 저장 url
	
	var mapCenter = new OpenLayers.LonLat(map.getCenter().lon,map.getCenter().lat);
	var postLayers = showLayers(); // 레이어 체크한것 가져오기.
	//console.log(postLayers);
	var vectors = getVectorInfo();	// 벡터정보.
	
	// 서버에 요청
	$.post(saveImgURL, {
		base_url : base_url,
		base_x : mapCenter.lon,
		base_y : mapCenter.lat,
		base_w: mapWidth,
		base_h: mapHeight,
		zoom : map.getZoom(),
		bbox : map.getExtent().toBBOX(),
//		wmsurl : 'http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/WMTS' + '?REQUEST=GetCapabilities',
//		layers : postLayers,
		wmsurl : null,
		layers : null,
		vectors : vectors
	}, function(data){
		if(data!=null) {
			var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "../../util/map_saveimg.jsp");
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'imgdata';
            input.value = data.savefile;
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
            document.body.removeChild(form);
		}
	});
}

/**
 * 이미지 프린트.
 */
function printScreen() {
	var mapHeight = $('#mapContent').height(); 	// 지도영역 
	var mapWidth = $('#mapContent').width();		// 지도 영역 
	
	// base map url
	var base_url = 'http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/tile';
	var saveImgURL = '../../ngi/mng/map_saveimg.do';	// 이미지 저장 url
	
	var mapCenter = new OpenLayers.LonLat(map.getCenter().lon,map.getCenter().lat);
	var postLayers = showLayers(); // 레이어 체크한것 가져오기.
	var vectors = getVectorInfo();	// 벡터정보.
	
	// 서버에 요청 
	$.post(saveImgURL, {
		base_url : base_url,
		base_x : mapCenter.lon,
		base_y : mapCenter.lat,
		base_w: mapWidth,
		base_h: mapHeight,
		zoom : map.getZoom(),
		bbox : map.getExtent().toBBOX(),
//		wmsurl : geoHost + wms_service_url + 'REQUEST=GetCapabilities',
//		layers : postLayers,
		wmsurl : null,
		layers : null,
		vectors : vectors
	}, function(data){
		if(data!=null) {
			var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "../../util/printmap.jsp");
            form.setAttribute("target", "_newprint");
 
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'imgdata';
            input.value = data.savefile;
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
            document.body.removeChild(form);
		}
	});
}


/**
 * 이미지 프린트.
 */
function printSaveImage(changeInfoId) {
	var mapHeight = 497; 	// 지도영역 
	var mapWidth = 500;		// 지도 영역 
	
	// base map url
	var base_url = 'http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/tile';
	var saveImgURL = '../../ngi/mng/map_saveimg.do';	// 이미지 저장 url
	
	var mapCenter = new OpenLayers.LonLat(map.getCenter().lon,map.getCenter().lat);
	var postLayers = showLayers(); // 레이어 체크한것 가져오기.
	var vectors = getVectorInfo();	// 벡터정보.
	
	// 서버에 요청 
	$.post(saveImgURL, {
		base_url : base_url,
		base_x : mapCenter.lon,
		base_y : mapCenter.lat,
		base_w: mapWidth,
		base_h: mapHeight,
		zoom : map.getZoom(),
		bbox : map.getExtent().toBBOX(),
//		wmsurl : geoHost + wms_service_url + 'REQUEST=GetCapabilities',
//		layers : postLayers,
		wmsurl : null,
		layers : null,
		vectors : vectors
	}, function(data){
		if(data!=null) {
			var wnd = launchPopupCenter("", "_newprint_p", 1050, 600);
			if (wnd != null) {
				wnd.focus ();
			}		
			
			var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "../../ngi/chg/ChangeInfoPrint.do?changeInfoId="+changeInfoId);
            form.setAttribute("target", "_newprint_p");
 
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'imgdata';
            input.value = data.savefile;
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
            document.body.removeChild(form);
		}
	});
}


$(function() {
	
	$("#kais_bldg_chg").css("background-image", "url('"+geoserver_host+"?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&WIDTH=24&HEIGHT=24&LAYER=kais_bldg_chg')");
	$("#eais_bldg_chg").css("background-image", "url('"+geoserver_host+"?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&WIDTH=24&HEIGHT=24&LAYER=eais_bldg_chg')");
	
	// 새주소 새움터 체크.
	$("input[name=layer_bldg_chg]").click(function(){
		var layerName = $(this).val();
		if($(this).is(":checked")) {
			map.getLayersByName(layerName)[0].setVisibility(true);
		} else {
			map.getLayersByName(layerName)[0].setVisibility(false);
		}
	});
	
	$('#distance').click(function(){
		setWorkMode(81);
	});
	
	$('#area').click(function(){
		setWorkMode(80);
	});
	
	// 지도저장.
	$('#save').click(function(){
		saveScreenimg();
	});
	
	// 지도 인쇄.
	$('#print').click(function(){
		printScreen();
	});
	
	// 지도선택
	$('#sel_basemap').click(function() {
		if($('.div_basemap').css('display') == 'none') {
			$('.div_basemap').show();
		} else {
			$('.div_basemap').hide();
		}
	});
	
	// 지도선택 체크 박스.
	$('input:checkbox[name="selmap"]').click(function(){
		var isChecked = $(this).prop("checked");
		var layerName = $(this).val();
		
		map.getLayersByName(layerName)[0].setVisibility(isChecked);
	});
	
	// 지도선택 닫기.
	$('#sel_basemap_close').click(function(){
		$('.div_basemap').hide();
	});

	
	/*
	 * 검색엔진을 이용한 주소 및 장소 검색 API
	http://map.vworld.kr/search.do로 요청을 보내 결과값을 리턴 받습니다.
	[parameter] 
	- callback : output값이 json일 경우 callback함수를 지원 
	- q : 검색 키워드
	- category : Poi / Jibun / Juso. 세종류의 값을 입력하여 해당하는 검색대상을 설정합니다. Poi 는 장소 검색. Jibun은 구주소 검색. Juso는 새주소 검색입니다.
	- pageUnit : 한페이지에 나올 검색결과 갯수를 정의합니다.	(10개로만 됨 );
	- output : xml / json. 두 종류의 값을 입력하여 원하는 결과 형태를 설정합니다. 
	- pageIndex : 페이지 번호입니다. 
	- apiKey : 발급받은 api key 입니다.
	 */
	// 주소검색
	$("#search-go").click(function(){
		
		var f = $("#search-input").val();
		var input = f.replace(/(^\s*)|(\s*$)/, '');

		if (input.length < 3 || f.length < 3) {
			alert("세자리 미만의 이름을 입력하셨습니다!");
			return;
		} 
		  
		if( search_markers != null )
			search_markers.destroy();
		
		var search_text = $("#search-input").val();
		
		var reqHost = 'http://map.vworld.kr/search.do';
        var param = 'q='+search_text+'&apiKey='+vworld_key+'&category=Poi';
        param = param + '&output=json&pageUnit=8&pageIndex=1';
        
        $.ajax({
            url : reqHost,
            data : param,
            dataType : "jsonp",
            //jsonp : "callback",
            success : addrResultCallback
        });
	});
	
	$("#search-input").keyup(function(event){
		if(event.keyCode == 13) {
			$("#search-go").click();
		}
	});
	
	$("#MapLeftWin_close").click(function(e){
		if( $('#MapLR').css('display') == 'none' ) {
			$('#MapLR').show();
			$('#MapLR').css('width', '215px');
			$('.MapLeft').css('width', '270px');
			$('#MapLeftWin').css('left', '270px');
			$('.MapLeftWin_close').removeClass('open');
		} else {
			$('#MapLR').hide();
			$('#MapLR').css('width', '0px');
			$('.MapLeft').css('width', '0px');
			$('#MapLeftWin').css('left', '55px');
			$('.MapLeftWin_close').addClass('open');
		}
		contentHeight();
	});
	
	$("#MapLeftWin_close").click();
	
	// 검색 상단 탭 클릭 처리.
	$("#searchToolTab ul li").click(function(e){
		$(this).parent().children("li").find("div").removeClass("on");
		$(this).find("div").addClass("on");
		
		var index = $("#searchToolTab ul li").index($(this));
		for(var k=0;k<4;k++) {
			if(k==index)
				$("#searchSubTab"+k).css('display','block');
			else
				$("#searchSubTab"+k).css('display','none');	
		}
		
		$("select[name=dokak_select]").change();		
	});
	
	// 검색,범례,통계,즐겨찾기 클릭 처리.
	$(".MapLL li").click(function(e){
		$(this).parent().children("li").find("div").removeClass("active");
		$(this).find("div").addClass("active");
		
		var index = $(".MapLL li").index($(this));
		if( index==0 ) {
			$('#searchTab').css('display','block');
			$('#show_legend').css('display','none');
			$('#show_chart').css('display','none');
			$('#show_fav').css('display','none');
			if($('#MapLR').css('display') == 'none')  $("#MapLeftWin_close").click();
		} else if( index == 1 ) {
			$('#searchTab').css('display','none');
			$('#show_legend').css('display','block');
			$('#show_chart').css('display','none');
			$('#show_fav').css('display','none');
			if($('#MapLR').css('display') == 'none')  $("#MapLeftWin_close").click();
		} else if( index == 2 ) {
//			console.log('aa');
			$('#searchTab').css('display','none');
			$('#show_legend').css('display','none');
			$('#show_chart').css('display','block');
			$('#show_fav').css('display','none');
			if($('#MapLR').css('display') == 'none')  $("#MapLeftWin_close").click();
		} else if( index == 3 ) {
			$('#searchTab').css('display','none');
			$('#show_legend').css('display','none');
			$('#show_chart').css('display','none');
			$('#show_fav').css('display','block');
			if($('#MapLR').css('display') == 'none')  $("#MapLeftWin_close").click();
		}
	});
	
	// 범례 체크.
	$("input[name=legend_check]").click(function(){
		if( $('#inputBasic_AX_legendSwitch_AX_SwitchDisplay').text() == 'ON' ) {
			legendLayerVisible(true);
		}
	});
	
	
	$("#hspotSwitch").bindSwitch({off:"ON", on:"OFF", onChange:function(){
		if(this.value=="OFF") {
			heatmapOn(false);
		} else {
			var year = $("select[name=hspot_year] option:selected").val();	// 핫스팟 년도
			heatmapOn(true, year);
		}
	}});	
	
	
	// 범례 레이어. on, off
	function legendLayerVisible(visible) {
	 	var lc_legend_view_layer = map.getLayersByName("lc_legend_view")[0];
		if(visible) {
			filterAsString = legendMergeParam();
			
			delete lc_legend_view_layer.params["CQL_FILTER"];
			lc_legend_view_layer.params["CQL_FILTER"] = filterAsString;
//			console.log(filterAsString);
			lc_legend_view_layer.redraw(true);
			lc_legend_view_layer.setVisibility(true);
		} else {
			lc_legend_view_layer.setVisibility(false);
		}
	}
	
	function hspotLayerVisible() {
		heatmapOn();
	}
	
	function legendMergeParam() {
		var mergeParam = '';
		var year = $("select[name=legend_year] option:selected").val();	// 범례 년도
		
		var checkList = $("input[name=legend_check]");
		checkList.each(function(index,checkbox){
			if($(checkbox).prop("checked") == true) {
				
				if(mergeParam.length==0)
					mergeParam = "change_code in ( '"+$(checkbox).val()+"'";
				else
					mergeParam += ", '"+$(checkbox).val()+"'";
				
			}
		});
		mergeParam += ")";
		
		if(mergeParam.length==0) {
			mergeParam = "regist_de between '"+year+"-01-01' and '"+year+"-12-31'";
		} else {
//			mergeParam += " and regist_de >= '"+year+"-01-01' and regist_de <= '"+year+"-12-31'";
			mergeParam += " and regist_de between '"+year+"-01-01' and '"+year+"-12-31'";
		}

//		var format = new OpenLayers.Format.CQL();
//		var filter = format.read(mergeParam);
//		var parser = new OpenLayers.Format.Filter.v1_1_0();
//		var filterAsXml = parser.write(filter);
//		var xml = new OpenLayers.Format.XML();
//		var filterAsString = xml.write(filterAsXml);
		filterAsString = mergeParam;
	 	//console.log(filter);
	 	
	 	return filterAsString;
	}
	
	$("#legendSwitch").bindSwitch({off:"ON", on:"OFF", onChange:function(){
		if(this.value=="OFF") {
			legendLayerVisible(false);
			if( infoPopup != null ) map.removePopup(infoPopup);
			info.deactivate();
		} else {
			legendLayerVisible(true);
			if( infoPopup != null ) map.removePopup(infoPopup);
			info.activate();
		}
	}});		
	
	$("select[name=legend_year]").change(function () {
		legendLayerVisible(true);
	});
	
	$("select[name=hspot_year]").change(function () {
		var year = $("select[name=hspot_year] option:selected").val();	// 핫스팟 년도
		heatmapOn(htmap.visibility, year);
	});
	
	$("#AXInputSwitch2").bindSwitch({off:"변경신고 ON", on:"변경신고 OFF", onChange:function(){
		if(this.value=="변경신고 OFF") {
			$(".MapRight").css('display','none');
			$(".MapRight").css('width','0px');
		} else {
			$(".MapRight").css('display','block');
			$(".MapRight").css('width','480px');
		}
		contentHeight();
	}});
	
	// 도엽
//dokak_select, dokak_select_sub0, dokak_select_sub1
	$("select[name=dokak_select]").change(function () {
	
		var value = $("select[name=dokak_select]").val();
		var sub0_opts = $("select[name=dokak_select_sub0");
		var sub1_opts = $("select[name=dokak_select_sub1");
		
		if( value == "5k-dokak" ) {
			sub0_opts.empty();
			sub1_opts.empty();
			sub0_opts.append('<option value="가야">가야</option>');
			sub0_opts.append('<option value="간성">간성</option>');
			sub0_opts.append('<option value="갈담">갈담</option>');
			sub0_opts.append('<option value="갈말">갈말</option>');
			sub0_opts.append('<option value="감포">감포</option>');
			sub0_opts.append('<option value="강릉">강릉</option>');
			sub0_opts.append('<option value="강화">강화</option>');
			sub0_opts.append('<option value="개성">개성</option>');
			sub0_opts.append('<option value="거금">거금</option>');
			sub0_opts.append('<option value="거문">거문</option>');
			sub0_opts.append('<option value="거제">거제</option>');
			sub0_opts.append('<option value="거창">거창</option>');
			sub0_opts.append('<option value="경주">경주</option>');
			sub0_opts.append('<option value="고남">고남</option>');
			sub0_opts.append('<option value="고산">고산</option>');
			sub0_opts.append('<option value="고성">고성</option>');
			sub0_opts.append('<option value="고창">고창</option>');
			sub0_opts.append('<option value="고흥">고흥</option>');
			sub0_opts.append('<option value="곤양">곤양</option>');
			sub0_opts.append('<option value="공주">공주</option>');
			sub0_opts.append('<option value="관기">관기</option>');
			sub0_opts.append('<option value="관매">관매</option>');
			sub0_opts.append('<option value="광도">광도</option>');
			sub0_opts.append('<option value="광양">광양</option>');
			sub0_opts.append('<option value="광주">광주</option>');
			sub0_opts.append('<option value="교동">교동</option>');
			sub0_opts.append('<option value="구례">구례</option>');
			sub0_opts.append('<option value="구미">구미</option>');
			sub0_opts.append('<option value="구정">구정</option>');
			sub0_opts.append('<option value="군산">군산</option>');
			sub0_opts.append('<option value="군위">군위</option>');
			sub0_opts.append('<option value="근흥">근흥</option>');
			sub0_opts.append('<option value="금산">금산</option>');
			sub0_opts.append('<option value="금성">금성</option>');
			sub0_opts.append('<option value="금화">금화</option>');
			sub0_opts.append('<option value="기계">기계</option>');
			sub0_opts.append('<option value="길산">길산</option>');
			sub0_opts.append('<option value="길안">길안</option>');
			sub0_opts.append('<option value="김천">김천</option>');
			sub0_opts.append('<option value="김포">김포</option>');
			sub0_opts.append('<option value="김해">김해</option>');
			sub0_opts.append('<option value="나주">나주</option>');
			sub0_opts.append('<option value="난지">난지</option>');
			sub0_opts.append('<option value="남양">남양</option>');
			sub0_opts.append('<option value="남원">남원</option>');
			sub0_opts.append('<option value="남지">남지</option>');
			sub0_opts.append('<option value="남해">남해</option>');
			sub0_opts.append('<option value="내문">내문</option>');
			sub0_opts.append('<option value="내평">내평</option>');
			sub0_opts.append('<option value="논산">논산</option>');
			sub0_opts.append('<option value="눌옥">눌옥</option>');
			sub0_opts.append('<option value="단양">단양</option>');
			sub0_opts.append('<option value="담양">담양</option>');
			sub0_opts.append('<option value="당진">당진</option>');
			sub0_opts.append('<option value="대구">대구</option>');
			sub0_opts.append('<option value="대보">대보</option>');
			sub0_opts.append('<option value="대부">대부</option>');
			sub0_opts.append('<option value="대서">대서</option>');
			sub0_opts.append('<option value="대전">대전</option>');
			sub0_opts.append('<option value="덕산">덕산</option>');
			sub0_opts.append('<option value="덕적">덕적</option>');
			sub0_opts.append('<option value="도암">도암</option>');
			sub0_opts.append('<option value="도초">도초</option>');
			sub0_opts.append('<option value="독산">독산</option>');
			sub0_opts.append('<option value="돌산">돌산</option>');
			sub0_opts.append('<option value="동곡">동곡</option>');
			sub0_opts.append('<option value="두미">두미</option>');
			sub0_opts.append('<option value="마산">마산</option>');
			sub0_opts.append('<option value="만리">만리</option>');
			sub0_opts.append('<option value="매물">매물</option>');
			sub0_opts.append('<option value="매원">매원</option>');
			sub0_opts.append('<option value="맹골">맹골</option>');
			sub0_opts.append('<option value="모괴">모괴</option>');
			sub0_opts.append('<option value="모슬">모슬</option>');
			sub0_opts.append('<option value="목포">목포</option>');
			sub0_opts.append('<option value="무주">무주</option>');
			sub0_opts.append('<option value="무풍">무풍</option>');
			sub0_opts.append('<option value="묵호">묵호</option>');
			sub0_opts.append('<option value="문경">문경</option>');
			sub0_opts.append('<option value="문산">문산</option>');
			sub0_opts.append('<option value="미원">미원</option>');
			sub0_opts.append('<option value="밀양">밀양</option>');
			sub0_opts.append('<option value="방산">방산</option>');
			sub0_opts.append('<option value="방어">방어</option>');
			sub0_opts.append('<option value="백아">백아</option>');
			sub0_opts.append('<option value="법성">법성</option>');
			sub0_opts.append('<option value="병곡">병곡</option>');
			sub0_opts.append('<option value="보령">보령</option>');
			sub0_opts.append('<option value="보은">보은</option>');
			sub0_opts.append('<option value="복내">복내</option>');
			sub0_opts.append('<option value="볼음">볼음</option>');
			sub0_opts.append('<option value="봉평">봉평</option>');
			sub0_opts.append('<option value="부산">부산</option>');
			sub0_opts.append('<option value="부안">부안</option>');
			sub0_opts.append('<option value="불국">불국</option>');
			sub0_opts.append('<option value="비금">비금</option>');
			sub0_opts.append('<option value="사천">사천</option>');
			sub0_opts.append('<option value="산청">산청</option>');
			sub0_opts.append('<option value="삼가">삼가</option>');
			sub0_opts.append('<option value="삼척">삼척</option>');
			sub0_opts.append('<option value="상주">상주</option>');
			sub0_opts.append('<option value="서귀">서귀</option>');
			sub0_opts.append('<option value="서산">서산</option>');
			sub0_opts.append('<option value="서울">서울</option>');
			sub0_opts.append('<option value="서천">서천</option>');
			sub0_opts.append('<option value="서화">서화</option>');
			sub0_opts.append('<option value="선미">선미</option>');
			sub0_opts.append('<option value="설악">설악</option>');
			sub0_opts.append('<option value="성동">성동</option>');
			sub0_opts.append('<option value="성산">성산</option>');
			sub0_opts.append('<option value="소리">소리</option>');
			sub0_opts.append('<option value="소안">소안</option>');
			sub0_opts.append('<option value="소천">소천</option>');
			sub0_opts.append('<option value="속리">속리</option>');
			sub0_opts.append('<option value="속초">속초</option>');
			sub0_opts.append('<option value="손죽">손죽</option>');
			sub0_opts.append('<option value="수원">수원</option>');
			sub0_opts.append('<option value="순창">순창</option>');
			sub0_opts.append('<option value="순천">순천</option>');
			sub0_opts.append('<option value="신시">신시</option>');
			sub0_opts.append('<option value="신온">신온</option>');
			sub0_opts.append('<option value="신지">신지</option>');
			sub0_opts.append('<option value="아산">아산</option>');
			sub0_opts.append('<option value="안계">안계</option>');
			sub0_opts.append('<option value="안동">안동</option>');
			sub0_opts.append('<option value="안마">안마</option>');
			sub0_opts.append('<option value="안성">안성</option>');
			sub0_opts.append('<option value="안양">안양</option>');
			sub0_opts.append('<option value="안흥">안흥</option>');
			sub0_opts.append('<option value="양구">양구</option>');
			sub0_opts.append('<option value="양산">양산</option>');
			sub0_opts.append('<option value="양수">양수</option>');
			sub0_opts.append('<option value="어론">어론</option>');
			sub0_opts.append('<option value="어청">어청</option>');
			sub0_opts.append('<option value="언양">언양</option>');
			sub0_opts.append('<option value="엄정">엄정</option>');
			sub0_opts.append('<option value="여수">여수</option>');
			sub0_opts.append('<option value="여주">여주</option>');
			sub0_opts.append('<option value="연곡">연곡</option>');
			sub0_opts.append('<option value="연도">연도</option>');
			sub0_opts.append('<option value="연안">연안</option>');
			sub0_opts.append('<option value="영덕">영덕</option>');
			sub0_opts.append('<option value="영동">영동</option>');
			sub0_opts.append('<option value="영암">영암</option>');
			sub0_opts.append('<option value="영양">영양</option>');
			sub0_opts.append('<option value="영월">영월</option>');
			sub0_opts.append('<option value="영주">영주</option>');
			sub0_opts.append('<option value="영천">영천</option>');
			sub0_opts.append('<option value="예미">예미</option>');
			sub0_opts.append('<option value="예산">예산</option>');
			sub0_opts.append('<option value="예안">예안</option>');
			sub0_opts.append('<option value="예천">예천</option>');
			sub0_opts.append('<option value="오호">오호</option>');
			sub0_opts.append('<option value="옥포">옥포</option>');
			sub0_opts.append('<option value="와도">와도</option>');
			sub0_opts.append('<option value="완도">완도</option>');
			sub0_opts.append('<option value="왕징">왕징</option>');
			sub0_opts.append('<option value="왜관">왜관</option>');
			sub0_opts.append('<option value="외연">외연</option>');
			sub0_opts.append('<option value="욕지">욕지</option>');
			sub0_opts.append('<option value="용두">용두</option>');
			sub0_opts.append('<option value="용유">용유</option>');
			sub0_opts.append('<option value="용인">용인</option>');
			sub0_opts.append('<option value="운봉">운봉</option>');
			sub0_opts.append('<option value="울릉">울릉</option>');
			sub0_opts.append('<option value="울산">울산</option>');
			sub0_opts.append('<option value="울진">울진</option>');
			sub0_opts.append('<option value="원주">원주</option>');
			sub0_opts.append('<option value="위도">위도</option>');
			sub0_opts.append('<option value="음성">음성</option>');
			sub0_opts.append('<option value="의성">의성</option>');
			sub0_opts.append('<option value="이원">이원</option>');
			sub0_opts.append('<option value="이천">이천</option>');
			sub0_opts.append('<option value="익산">익산</option>');
			sub0_opts.append('<option value="인제">인제</option>');
			sub0_opts.append('<option value="인천">인천</option>');
			sub0_opts.append('<option value="일동">일동</option>');
			sub0_opts.append('<option value="임계">임계</option>');
			sub0_opts.append('<option value="임실">임실</option>');
			sub0_opts.append('<option value="임자">임자</option>');
			sub0_opts.append('<option value="자은">자은</option>');
			sub0_opts.append('<option value="장성">장성</option>');
			sub0_opts.append('<option value="장호">장호</option>');
			sub0_opts.append('<option value="장흥">장흥</option>');
			sub0_opts.append('<option value="전의">전의</option>');
			sub0_opts.append('<option value="전주">전주</option>');
			sub0_opts.append('<option value="정선">정선</option>');
			sub0_opts.append('<option value="정읍">정읍</option>');
			sub0_opts.append('<option value="제주">제주</option>');
			sub0_opts.append('<option value="제천">제천</option>');
			sub0_opts.append('<option value="조도">조도</option>');
			sub0_opts.append('<option value="죽굴">죽굴</option>');
			sub0_opts.append('<option value="죽도">죽도</option>');
			sub0_opts.append('<option value="죽변">죽변</option>');
			sub0_opts.append('<option value="진도">진도</option>');
			sub0_opts.append('<option value="진안">진안</option>');
			sub0_opts.append('<option value="진주">진주</option>');
			sub0_opts.append('<option value="진천">진천</option>');
			sub0_opts.append('<option value="창녕">창녕</option>');
			sub0_opts.append('<option value="창원">창원</option>');
			sub0_opts.append('<option value="철원">철원</option>');
			sub0_opts.append('<option value="청도">청도</option>');
			sub0_opts.append('<option value="청산">청산</option>');
			sub0_opts.append('<option value="청송">청송</option>');
			sub0_opts.append('<option value="청양">청양</option>');
			sub0_opts.append('<option value="청일">청일</option>');
			sub0_opts.append('<option value="청주">청주</option>');
			sub0_opts.append('<option value="청풍">청풍</option>');
			sub0_opts.append('<option value="초도">초도</option>');
			sub0_opts.append('<option value="추자">추자</option>');
			sub0_opts.append('<option value="춘양">춘양</option>');
			sub0_opts.append('<option value="춘천">춘천</option>');
			sub0_opts.append('<option value="충주">충주</option>');
			sub0_opts.append('<option value="태백">태백</option>');
			sub0_opts.append('<option value="통영">통영</option>');
			sub0_opts.append('<option value="평창">평창</option>');
			sub0_opts.append('<option value="평택">평택</option>');
			sub0_opts.append('<option value="포천">포천</option>');
			sub0_opts.append('<option value="포항">포항</option>');
			sub0_opts.append('<option value="표선">표선</option>');
			sub0_opts.append('<option value="하동">하동</option>');
			sub0_opts.append('<option value="하의">하의</option>');
			sub0_opts.append('<option value="한림">한림</option>');
			sub0_opts.append('<option value="한산">한산</option>');
			sub0_opts.append('<option value="함안">함안</option>');
			sub0_opts.append('<option value="함양">함양</option>');
			sub0_opts.append('<option value="합천">합천</option>');
			sub0_opts.append('<option value="해남">해남</option>');
			sub0_opts.append('<option value="현리">현리</option>');
			sub0_opts.append('<option value="홍성">홍성</option>');
			sub0_opts.append('<option value="홍천">홍천</option>');
			sub0_opts.append('<option value="화북">화북</option>');
			sub0_opts.append('<option value="화원">화원</option>');
			sub0_opts.append('<option value="화천">화천</option>');
			sub0_opts.append('<option value="회천">회천</option>');
		} else if( value == "25k-dokak" ) {
			sub0_opts.empty();
			sub0_opts.append('<option value="가남">가남</option>');
			sub0_opts.append('<option value="가덕">가덕</option>');
			sub0_opts.append('<option value="가례">가례</option>');
			sub0_opts.append('<option value="가리">가리</option>');
			sub0_opts.append('<option value="가사">가사</option>');
			sub0_opts.append('<option value="가야">가야</option>');
			sub0_opts.append('<option value="가음">가음</option>');
			sub0_opts.append('<option value="가의">가의</option>');
			sub0_opts.append('<option value="가전">가전</option>');
			sub0_opts.append('<option value="가조">가조</option>');
			sub0_opts.append('<option value="가평">가평</option>');
			sub0_opts.append('<option value="가회">가회</option>');
			sub0_opts.append('<option value="가흥">가흥</option>');
			sub0_opts.append('<option value="간동">간동</option>');
			sub0_opts.append('<option value="간성">간성</option>');
			sub0_opts.append('<option value="간월">간월</option>');
			sub0_opts.append('<option value="갈곶">갈곶</option>');
			sub0_opts.append('<option value="갈담">갈담</option>');
			sub0_opts.append('<option value="갈도">갈도</option>');
			sub0_opts.append('<option value="갈면">갈면</option>');
			sub0_opts.append('<option value="갈산">갈산</option>');
			sub0_opts.append('<option value="갈천">갈천</option>');
			sub0_opts.append('<option value="갈현">갈현</option>');
			sub0_opts.append('<option value="감천">감천</option>');
			sub0_opts.append('<option value="감포">감포</option>');
			sub0_opts.append('<option value="갑천">갑천</option>');
			sub0_opts.append('<option value="강구">강구</option>');
			sub0_opts.append('<option value="강릉">강릉</option>');
			sub0_opts.append('<option value="강정">강정</option>');
			sub0_opts.append('<option value="강진">강진</option>');
			sub0_opts.append('<option value="강화">강화</option>');
			sub0_opts.append('<option value="개도">개도</option>');
			sub0_opts.append('<option value="개령">개령</option>');
			sub0_opts.append('<option value="개야">개야</option>');
			sub0_opts.append('<option value="거금">거금</option>');
			sub0_opts.append('<option value="거문">거문</option>');
			sub0_opts.append('<option value="거아">거아</option>');
			sub0_opts.append('<option value="거제">거제</option>');
			sub0_opts.append('<option value="거진">거진</option>');
			sub0_opts.append('<option value="거차">거차</option>');
			sub0_opts.append('<option value="거창">거창</option>');
			sub0_opts.append('<option value="건천">건천</option>');
			sub0_opts.append('<option value="격렬">격렬</option>');
			sub0_opts.append('<option value="격포">격포</option>');
			sub0_opts.append('<option value="경산">경산</option>');
			sub0_opts.append('<option value="경정">경정</option>');
			sub0_opts.append('<option value="경주">경주</option>');
			sub0_opts.append('<option value="계산">계산</option>');
			sub0_opts.append('<option value="계호">계호</option>');
			sub0_opts.append('<option value="고금">고금</option>');
			sub0_opts.append('<option value="고단">고단</option>');
			sub0_opts.append('<option value="고대">고대</option>');
			sub0_opts.append('<option value="고도">고도</option>');
			sub0_opts.append('<option value="고령">고령</option>');
			sub0_opts.append('<option value="고미성">고미성</option>');
			sub0_opts.append('<option value="고산">고산</option>');
			sub0_opts.append('<option value="고성">고성</option>');
			sub0_opts.append('<option value="고양">고양</option>');
			sub0_opts.append('<option value="고이">고이</option>');
			sub0_opts.append('<option value="고잔">고잔</option>');
			sub0_opts.append('<option value="고창">고창</option>');
			sub0_opts.append('<option value="고파">고파</option>');
			sub0_opts.append('<option value="고현">고현</option>');
			sub0_opts.append('<option value="고흥">고흥</option>');
			sub0_opts.append('<option value="곡성">곡성</option>');
			sub0_opts.append('<option value="곤로">곤로</option>');
			sub0_opts.append('<option value="공전">공전</option>');
			sub0_opts.append('<option value="공주">공주</option>');
			sub0_opts.append('<option value="공항">공항</option>');
			sub0_opts.append('<option value="과역">과역</option>');
			sub0_opts.append('<option value="관기">관기</option>');
			sub0_opts.append('<option value="관매">관매</option>');
			sub0_opts.append('<option value="관산">관산</option>');
			sub0_opts.append('<option value="관촌">관촌</option>');
			sub0_opts.append('<option value="광덕">광덕</option>');
			sub0_opts.append('<option value="광동">광동</option>');
			sub0_opts.append('<option value="광양">광양</option>');
			sub0_opts.append('<option value="광적">광적</option>');
			sub0_opts.append('<option value="광주">광주</option>');
			sub0_opts.append('<option value="괴목">괴목</option>');
			sub0_opts.append('<option value="괴산">괴산</option>');
			sub0_opts.append('<option value="교동">교동</option>');
			sub0_opts.append('<option value="교암">교암</option>');
			sub0_opts.append('<option value="구례">구례</option>');
			sub0_opts.append('<option value="구룡">구룡</option>');
			sub0_opts.append('<option value="구룡포">구룡포</option>');
			sub0_opts.append('<option value="구만">구만</option>');
			sub0_opts.append('<option value="구산">구산</option>');
			sub0_opts.append('<option value="구자">구자</option>');
			sub0_opts.append('<option value="구정">구정</option>');
			sub0_opts.append('<option value="구화">구화</option>');
			sub0_opts.append('<option value="국수">국수</option>');
			sub0_opts.append('<option value="군산">군산</option>');
			sub0_opts.append('<option value="군위">군위</option>');
			sub0_opts.append('<option value="군포">군포</option>');
			sub0_opts.append('<option value="굴업">굴업</option>');
			sub0_opts.append('<option value="굴지">굴지</option>');
			sub0_opts.append('<option value="궁촌">궁촌</option>');
			sub0_opts.append('<option value="궁평">궁평</option>');
			sub0_opts.append('<option value="귀일">귀일</option>');
			sub0_opts.append('<option value="근흥">근흥</option>');
			sub0_opts.append('<option value="금남">금남</option>');
			sub0_opts.append('<option value="금당">금당</option>');
			sub0_opts.append('<option value="금덕">금덕</option>');
			sub0_opts.append('<option value="금령">금령</option>');
			sub0_opts.append('<option value="금리">금리</option>');
			sub0_opts.append('<option value="금부">금부</option>');
			sub0_opts.append('<option value="금산">금산</option>');
			sub0_opts.append('<option value="금화">금화</option>');
			sub0_opts.append('<option value="기계">기계</option>');
			sub0_opts.append('<option value="기산">기산</option>');
			sub0_opts.append('<option value="기성">기성</option>');
			sub0_opts.append('<option value="기장">기장</option>');
			sub0_opts.append('<option value="기좌">기좌</option>');
			sub0_opts.append('<option value="길산">길산</option>');
			sub0_opts.append('<option value="김제">김제</option>');
			sub0_opts.append('<option value="김천">김천</option>');
			sub0_opts.append('<option value="김포">김포</option>');
			sub0_opts.append('<option value="김해">김해</option>');
			sub0_opts.append('<option value="나주">나주</option>');
			sub0_opts.append('<option value="낙동">낙동</option>');
			sub0_opts.append('<option value="낙월">낙월</option>');
			sub0_opts.append('<option value="난지">난지</option>');
			sub0_opts.append('<option value="남내">남내</option>');
			sub0_opts.append('<option value="남대">남대</option>');
			sub0_opts.append('<option value="남명">남명</option>');
			sub0_opts.append('<option value="남선">남선</option>');
			sub0_opts.append('<option value="남양">남양</option>');
			sub0_opts.append('<option value="남원">남원</option>');
			sub0_opts.append('<option value="남지">남지</option>');
			sub0_opts.append('<option value="남창">남창</option>');
			sub0_opts.append('<option value="남평">남평</option>');
			sub0_opts.append('<option value="남포">남포</option>');
			sub0_opts.append('<option value="남해">남해</option>');
			sub0_opts.append('<option value="내라노">내라노</option>');
			sub0_opts.append('<option value="내성">내성</option>');
			sub0_opts.append('<option value="내수">내수</option>');
			sub0_opts.append('<option value="내포">내포</option>');
			sub0_opts.append('<option value="노곡">노곡</option>');
			sub0_opts.append('<option value="노도">노도</option>');
			sub0_opts.append('<option value="노동">노동</option>');
			sub0_opts.append('<option value="논산">논산</option>');
			sub0_opts.append('<option value="농산">농산</option>');
			sub0_opts.append('<option value="농암">농암</option>');
			sub0_opts.append('<option value="눌옥">눌옥</option>');
			sub0_opts.append('<option value="능교">능교</option>');
			sub0_opts.append('<option value="능서">능서</option>');
			sub0_opts.append('<option value="능주">능주</option>');
			sub0_opts.append('<option value="능평">능평</option>');
			sub0_opts.append('<option value="다대">다대</option>');
			sub0_opts.append('<option value="다목">다목</option>');
			sub0_opts.append('<option value="다부">다부</option>');
			sub0_opts.append('<option value="다인">다인</option>');
			sub0_opts.append('<option value="단성">단성</option>');
			sub0_opts.append('<option value="단양">단양</option>');
			sub0_opts.append('<option value="단월">단월</option>');
			sub0_opts.append('<option value="단촌">단촌</option>');
			sub0_opts.append('<option value="달산">달산</option>');
			sub0_opts.append('<option value="담양">담양</option>');
			sub0_opts.append('<option value="당거">당거</option>');
			sub0_opts.append('<option value="당동">당동</option>');
			sub0_opts.append('<option value="당리">당리</option>');
			sub0_opts.append('<option value="당진">당진</option>');
			sub0_opts.append('<option value="대광">대광</option>');
			sub0_opts.append('<option value="대구">대구</option>');
			sub0_opts.append('<option value="대덕">대덕</option>');
			sub0_opts.append('<option value="대도">대도</option>');
			sub0_opts.append('<option value="대량">대량</option>');
			sub0_opts.append('<option value="대모">대모</option>');
			sub0_opts.append('<option value="대보">대보</option>');
			sub0_opts.append('<option value="대부">대부</option>');
			sub0_opts.append('<option value="대산">대산</option>');
			sub0_opts.append('<option value="대성">대성</option>');
			sub0_opts.append('<option value="대소">대소</option>');
			sub0_opts.append('<option value="대술">대술</option>');
			sub0_opts.append('<option value="대아">대아</option>');
			sub0_opts.append('<option value="대야">대야</option>');
			sub0_opts.append('<option value="대연평">대연평</option>');
			sub0_opts.append('<option value="대율">대율</option>');
			sub0_opts.append('<option value="대전">대전</option>');
			sub0_opts.append('<option value="대천">대천</option>');
			sub0_opts.append('<option value="대청">대청</option>');
			sub0_opts.append('<option value="대평">대평</option>');
			sub0_opts.append('<option value="대합">대합</option>');
			sub0_opts.append('<option value="대현">대현</option>');
			sub0_opts.append('<option value="대흑산">대흑산</option>');
			sub0_opts.append('<option value="대흥">대흥</option>');
			sub0_opts.append('<option value="덕곡">덕곡</option>');
			sub0_opts.append('<option value="덕구">덕구</option>');
			sub0_opts.append('<option value="덕도">덕도</option>');
			sub0_opts.append('<option value="덕동">덕동</option>');
			sub0_opts.append('<option value="덕산">덕산</option>');
			sub0_opts.append('<option value="덕소">덕소</option>');
			sub0_opts.append('<option value="덕우">덕우</option>');
			sub0_opts.append('<option value="덕적">덕적</option>');
			sub0_opts.append('<option value="덕정">덕정</option>');
			sub0_opts.append('<option value="덕촌">덕촌</option>');
			sub0_opts.append('<option value="도계">도계</option>');
			sub0_opts.append('<option value="도곡">도곡</option>');
			sub0_opts.append('<option value="도관">도관</option>');
			sub0_opts.append('<option value="도룡">도룡</option>');
			sub0_opts.append('<option value="도양">도양</option>');
			sub0_opts.append('<option value="도원">도원</option>');
			sub0_opts.append('<option value="도전">도전</option>');
			sub0_opts.append('<option value="도천">도천</option>');
			sub0_opts.append('<option value="도초">도초</option>');
			sub0_opts.append('<option value="도평">도평</option>');
			sub0_opts.append('<option value="독거">독거</option>');
			sub0_opts.append('<option value="독도">독도</option>');
			sub0_opts.append('<option value="독천">독천</option>');
			sub0_opts.append('<option value="돌산">돌산</option>');
			sub0_opts.append('<option value="동가">동가</option>');
			sub0_opts.append('<option value="동곡">동곡</option>');
			sub0_opts.append('<option value="동노">동노</option>');
			sub0_opts.append('<option value="동두천">동두천</option>');
			sub0_opts.append('<option value="동래">동래</option>');
			sub0_opts.append('<option value="동량">동량</option>');
			sub0_opts.append('<option value="동복">동복</option>');
			sub0_opts.append('<option value="동선">동선</option>');
			sub0_opts.append('<option value="동창">동창</option>');
			sub0_opts.append('<option value="동촌">동촌</option>');
			sub0_opts.append('<option value="동항">동항</option>');
			sub0_opts.append('<option value="두문">두문</option>');
			sub0_opts.append('<option value="두미">두미</option>');
			sub0_opts.append('<option value="둔내">둔내</option>');
			sub0_opts.append('<option value="둔전">둔전</option>');
			sub0_opts.append('<option value="라원">라원</option>');
			sub0_opts.append('<option value="랑도">랑도</option>');
			sub0_opts.append('<option value="마곡">마곡</option>');
			sub0_opts.append('<option value="마교">마교</option>');
			sub0_opts.append('<option value="마도">마도</option>');
			sub0_opts.append('<option value="마산">마산</option>');
			sub0_opts.append('<option value="마상">마상</option>');
			sub0_opts.append('<option value="마석">마석</option>');
			sub0_opts.append('<option value="마전">마전</option>');
			sub0_opts.append('<option value="마지">마지</option>');
			sub0_opts.append('<option value="마차">마차</option>');
			sub0_opts.append('<option value="마포">마포</option>');
			sub0_opts.append('<option value="만경">만경</option>');
			sub0_opts.append('<option value="만승">만승</option>');
			sub0_opts.append('<option value="만재">만재</option>');
			sub0_opts.append('<option value="말도">말도</option>');
			sub0_opts.append('<option value="망운">망운</option>');
			sub0_opts.append('<option value="매곡">매곡</option>');
			sub0_opts.append('<option value="매물">매물</option>');
			sub0_opts.append('<option value="매포">매포</option>');
			sub0_opts.append('<option value="매화">매화</option>');
			sub0_opts.append('<option value="맹골">맹골</option>');
			sub0_opts.append('<option value="면천">면천</option>');
			sub0_opts.append('<option value="명파">명파</option>');
			sub0_opts.append('<option value="모곡">모곡</option>');
			sub0_opts.append('<option value="모서">모서</option>');
			sub0_opts.append('<option value="모슬포">모슬포</option>');
			sub0_opts.append('<option value="목동">목동</option>');
			sub0_opts.append('<option value="목포">목포</option>');
			sub0_opts.append('<option value="몽탄">몽탄</option>');
			sub0_opts.append('<option value="무계">무계</option>');
			sub0_opts.append('<option value="무룡">무룡</option>');
			sub0_opts.append('<option value="무산">무산</option>');
			sub0_opts.append('<option value="무안">무안</option>');
			sub0_opts.append('<option value="무의">무의</option>');
			sub0_opts.append('<option value="무장">무장</option>');
			sub0_opts.append('<option value="무주">무주</option>');
			sub0_opts.append('<option value="무풍">무풍</option>');
			sub0_opts.append('<option value="무학">무학</option>');
			sub0_opts.append('<option value="묵호">묵호</option>');
			sub0_opts.append('<option value="문갑">문갑</option>');
			sub0_opts.append('<option value="문경">문경</option>');
			sub0_opts.append('<option value="문곡">문곡</option>');
			sub0_opts.append('<option value="문내">문내</option>');
			sub0_opts.append('<option value="문등">문등</option>');
			sub0_opts.append('<option value="문막">문막</option>');
			sub0_opts.append('<option value="문산">문산</option>');
			sub0_opts.append('<option value="문수산">문수산</option>');
			sub0_opts.append('<option value="물금">물금</option>');
			sub0_opts.append('<option value="미로">미로</option>');
			sub0_opts.append('<option value="미원">미원</option>');
			sub0_opts.append('<option value="미조">미조</option>');
			sub0_opts.append('<option value="미탄">미탄</option>');
			sub0_opts.append('<option value="밀양">밀양</option>');
			sub0_opts.append('<option value="반곡">반곡</option>');
			sub0_opts.append('<option value="반성">반성</option>');
			sub0_opts.append('<option value="반암">반암</option>');
			sub0_opts.append('<option value="반월">반월</option>');
			sub0_opts.append('<option value="발리">발리</option>');
			sub0_opts.append('<option value="발안">발안</option>');
			sub0_opts.append('<option value="방갈">방갈</option>');
			sub0_opts.append('<option value="방내">방내</option>');
			sub0_opts.append('<option value="방동">방동</option>');
			sub0_opts.append('<option value="방림">방림</option>');
			sub0_opts.append('<option value="방산">방산</option>');
			sub0_opts.append('<option value="방어">방어</option>');
			sub0_opts.append('<option value="백령">백령</option>');
			sub0_opts.append('<option value="백아">백아</option>');
			sub0_opts.append('<option value="백운">백운</option>');
			sub0_opts.append('<option value="백일">백일</option>');
			sub0_opts.append('<option value="백학">백학</option>');
			sub0_opts.append('<option value="벌교">벌교</option>');
			sub0_opts.append('<option value="법성포">법성포</option>');
			sub0_opts.append('<option value="병천">병천</option>');
			sub0_opts.append('<option value="병풍">병풍</option>');
			sub0_opts.append('<option value="보령">보령</option>');
			sub0_opts.append('<option value="보성">보성</option>');
			sub0_opts.append('<option value="보은">보은</option>');
			sub0_opts.append('<option value="복내">복내</option>');
			sub0_opts.append('<option value="복흥">복흥</option>');
			sub0_opts.append('<option value="볼음">볼음</option>');
			sub0_opts.append('<option value="봉계">봉계</option>');
			sub0_opts.append('<option value="봉당">봉당</option>');
			sub0_opts.append('<option value="봉림">봉림</option>');
			sub0_opts.append('<option value="봉산">봉산</option>');
			sub0_opts.append('<option value="봉성">봉성</option>');
			sub0_opts.append('<option value="부곡">부곡</option>');
			sub0_opts.append('<option value="부남">부남</option>');
			sub0_opts.append('<option value="부동">부동</option>');
			sub0_opts.append('<option value="부론">부론</option>');
			sub0_opts.append('<option value="부림">부림</option>');
			sub0_opts.append('<option value="부산">부산</option>');
			sub0_opts.append('<option value="부석">부석</option>');
			sub0_opts.append('<option value="부안">부안</option>');
			sub0_opts.append('<option value="부여">부여</option>');
			sub0_opts.append('<option value="북산">북산</option>');
			sub0_opts.append('<option value="북지">북지</option>');
			sub0_opts.append('<option value="불국">불국</option>');
			sub0_opts.append('<option value="비금">비금</option>');
			sub0_opts.append('<option value="비룡">비룡</option>');
			sub0_opts.append('<option value="비아">비아</option>');
			sub0_opts.append('<option value="비안도">비안도</option>');
			sub0_opts.append('<option value="비진">비진</option>');
			sub0_opts.append('<option value="사가">사가</option>');
			sub0_opts.append('<option value="사강">사강</option>');
			sub0_opts.append('<option value="사곡">사곡</option>');
			sub0_opts.append('<option value="사기막">사기막</option>');
			sub0_opts.append('<option value="사량">사량</option>');
			sub0_opts.append('<option value="사리">사리</option>');
			sub0_opts.append('<option value="사창">사창</option>');
			sub0_opts.append('<option value="사천">사천</option>');
			sub0_opts.append('<option value="사촌">사촌</option>');
			sub0_opts.append('<option value="사태">사태</option>');
			sub0_opts.append('<option value="사평">사평</option>');
			sub0_opts.append('<option value="산북">산북</option>');
			sub0_opts.append('<option value="산월">산월</option>');
			sub0_opts.append('<option value="산정">산정</option>');
			sub0_opts.append('<option value="산청">산청</option>');
			sub0_opts.append('<option value="삼가">삼가</option>');
			sub0_opts.append('<option value="삼곡">삼곡</option>');
			sub0_opts.append('<option value="삼례">삼례</option>');
			sub0_opts.append('<option value="삼목">삼목</option>');
			sub0_opts.append('<option value="삼부">삼부</option>');
			sub0_opts.append('<option value="삼송">삼송</option>');
			sub0_opts.append('<option value="삼척">삼척</option>');
			sub0_opts.append('<option value="삼천포">삼천포</option>');
			sub0_opts.append('<option value="삼호">삼호</option>');
			sub0_opts.append('<option value="상두">상두</option>');
			sub0_opts.append('<option value="상북">상북</option>');
			sub0_opts.append('<option value="상서">상서</option>');
			sub0_opts.append('<option value="상운">상운</option>');
			sub0_opts.append('<option value="상주">상주</option>');
			sub0_opts.append('<option value="상천">상천</option>');
			sub0_opts.append('<option value="상판">상판</option>');
			sub0_opts.append('<option value="생극">생극</option>');
			sub0_opts.append('<option value="생일">생일</option>');
			sub0_opts.append('<option value="생초">생초</option>');
			sub0_opts.append('<option value="서귀">서귀</option>');
			sub0_opts.append('<option value="서넙도">서넙도</option>');
			sub0_opts.append('<option value="서당">서당</option>');
			sub0_opts.append('<option value="서벽">서벽</option>');
			sub0_opts.append('<option value="서산">서산</option>');
			sub0_opts.append('<option value="서상">서상</option>');
			sub0_opts.append('<option value="서석">서석</option>');
			sub0_opts.append('<option value="서운">서운</option>');
			sub0_opts.append('<option value="서울">서울</option>');
			sub0_opts.append('<option value="서정">서정</option>');
			sub0_opts.append('<option value="서천">서천</option>');
			sub0_opts.append('<option value="서하">서하</option>');
			sub0_opts.append('<option value="서화">서화</option>');
			sub0_opts.append('<option value="석곡">석곡</option>');
			sub0_opts.append('<option value="석남">석남</option>');
			sub0_opts.append('<option value="석도">석도</option>');
			sub0_opts.append('<option value="석모">석모</option>');
			sub0_opts.append('<option value="석묘">석묘</option>');
			sub0_opts.append('<option value="석문">석문</option>');
			sub0_opts.append('<option value="석병">석병</option>');
			sub0_opts.append('<option value="석성">석성</option>');
			sub0_opts.append('<option value="석포">석포</option>');
			sub0_opts.append('<option value="석현">석현</option>');
			sub0_opts.append('<option value="선갑">선갑</option>');
			sub0_opts.append('<option value="선미">선미</option>');
			sub0_opts.append('<option value="선산">선산</option>');
			sub0_opts.append('<option value="설악">설악</option>');
			sub0_opts.append('<option value="설천">설천</option>');
			sub0_opts.append('<option value="섭도">섭도</option>');
			sub0_opts.append('<option value="성남">성남</option>');
			sub0_opts.append('<option value="성내">성내</option>');
			sub0_opts.append('<option value="성동">성동</option>');
			sub0_opts.append('<option value="성산">성산</option>');
			sub0_opts.append('<option value="성전">성전</option>');
			sub0_opts.append('<option value="성주">성주</option>');
			sub0_opts.append('<option value="성환">성환</option>');
			sub0_opts.append('<option value="소록">소록</option>');
			sub0_opts.append('<option value="소리">소리</option>');
			sub0_opts.append('<option value="소보">소보</option>');
			sub0_opts.append('<option value="소사">소사</option>');
			sub0_opts.append('<option value="소산">소산</option>');
			sub0_opts.append('<option value="소안">소안</option>');
			sub0_opts.append('<option value="소연평">소연평</option>');
			sub0_opts.append('<option value="소원">소원</option>');
			sub0_opts.append('<option value="소청">소청</option>');
			sub0_opts.append('<option value="소태">소태</option>');
			sub0_opts.append('<option value="소흑산">소흑산</option>');
			sub0_opts.append('<option value="속리천">속리천</option>');
			sub0_opts.append('<option value="속초">속초</option>');
			sub0_opts.append('<option value="손죽">손죽</option>');
			sub0_opts.append('<option value="송계">송계</option>');
			sub0_opts.append('<option value="송당">송당</option>');
			sub0_opts.append('<option value="송덕">송덕</option>');
			sub0_opts.append('<option value="송서">송서</option>');
			sub0_opts.append('<option value="송우">송우</option>');
			sub0_opts.append('<option value="송이">송이</option>');
			sub0_opts.append('<option value="송정">송정</option>');
			sub0_opts.append('<option value="송하">송하</option>');
			sub0_opts.append('<option value="수동">수동</option>');
			sub0_opts.append('<option value="수산">수산</option>');
			sub0_opts.append('<option value="수원">수원</option>');
			sub0_opts.append('<option value="수항">수항</option>');
			sub0_opts.append('<option value="숙성">숙성</option>');
			sub0_opts.append('<option value="순창">순창</option>');
			sub0_opts.append('<option value="순천">순천</option>');
			sub0_opts.append('<option value="순흥">순흥</option>');
			sub0_opts.append('<option value="슬도">슬도</option>');
			sub0_opts.append('<option value="승봉">승봉</option>');
			sub0_opts.append('<option value="시산">시산</option>');
			sub0_opts.append('<option value="신기">신기</option>');
			sub0_opts.append('<option value="신남">신남</option>');
			sub0_opts.append('<option value="신대">신대</option>');
			sub0_opts.append('<option value="신도">신도</option>');
			sub0_opts.append('<option value="신령">신령</option>');
			sub0_opts.append('<option value="신룡">신룡</option>');
			sub0_opts.append('<option value="신림">신림</option>');
			sub0_opts.append('<option value="신북">신북</option>');
			sub0_opts.append('<option value="신선">신선</option>');
			sub0_opts.append('<option value="신시">신시</option>');
			sub0_opts.append('<option value="신양">신양</option>');
			sub0_opts.append('<option value="신원">신원</option>');
			sub0_opts.append('<option value="신월">신월</option>');
			sub0_opts.append('<option value="신읍">신읍</option>');
			sub0_opts.append('<option value="신점">신점</option>');
			sub0_opts.append('<option value="신정">신정</option>');
			sub0_opts.append('<option value="신지">신지</option>');
			sub0_opts.append('<option value="신창">신창</option>');
			sub0_opts.append('<option value="신촌">신촌</option>');
			sub0_opts.append('<option value="신탄진">신탄진</option>');
			sub0_opts.append('<option value="신평">신평</option>');
			sub0_opts.append('<option value="신풍">신풍</option>');
			sub0_opts.append('<option value="심천">심천</option>');
			sub0_opts.append('<option value="심포">심포</option>');
			sub0_opts.append('<option value="쌍룡">쌍룡</option>');
			sub0_opts.append('<option value="쌍봉">쌍봉</option>');
			sub0_opts.append('<option value="쌍전">쌍전</option>');
			sub0_opts.append('<option value="쌍정">쌍정</option>');
			sub0_opts.append('<option value="쌍책">쌍책</option>');
			sub0_opts.append('<option value="아산">아산</option>');
			sub0_opts.append('<option value="아화">아화</option>');
			sub0_opts.append('<option value="악양">악양</option>');
			sub0_opts.append('<option value="안강">안강</option>');
			sub0_opts.append('<option value="안계">안계</option>');
			sub0_opts.append('<option value="안남">안남</option>');
			sub0_opts.append('<option value="안덕">안덕</option>');
			sub0_opts.append('<option value="안동">안동</option>');
			sub0_opts.append('<option value="안마">안마</option>');
			sub0_opts.append('<option value="안면">안면</option>');
			sub0_opts.append('<option value="안보">안보</option>');
			sub0_opts.append('<option value="안성">안성</option>');
			sub0_opts.append('<option value="안양">안양</option>');
			sub0_opts.append('<option value="안의">안의</option>');
			sub0_opts.append('<option value="안인">안인</option>');
			sub0_opts.append('<option value="안중">안중</option>');
			sub0_opts.append('<option value="안창">안창</option>');
			sub0_opts.append('<option value="안천">안천</option>');
			sub0_opts.append('<option value="안흥">안흥</option>');
			sub0_opts.append('<option value="암태">암태</option>');
			sub0_opts.append('<option value="압해">압해</option>');
			sub0_opts.append('<option value="앙성">앙성</option>');
			sub0_opts.append('<option value="약목">약목</option>');
			sub0_opts.append('<option value="양간">양간</option>');
			sub0_opts.append('<option value="양구">양구</option>');
			sub0_opts.append('<option value="양덕원">양덕원</option>');
			sub0_opts.append('<option value="양동">양동</option>');
			sub0_opts.append('<option value="양산">양산</option>');
			sub0_opts.append('<option value="양수">양수</option>');
			sub0_opts.append('<option value="양양">양양</option>');
			sub0_opts.append('<option value="양지">양지</option>');
			sub0_opts.append('<option value="양평">양평</option>');
			sub0_opts.append('<option value="양항">양항</option>');
			sub0_opts.append('<option value="어불">어불</option>');
			sub0_opts.append('<option value="어성전">어성전</option>');
			sub0_opts.append('<option value="어일">어일</option>');
			sub0_opts.append('<option value="어청">어청</option>');
			sub0_opts.append('<option value="언양">언양</option>');
			sub0_opts.append('<option value="여수">여수</option>');
			sub0_opts.append('<option value="여주">여주</option>');
			sub0_opts.append('<option value="연도">연도</option>');
			sub0_opts.append('<option value="연무">연무</option>');
			sub0_opts.append('<option value="연산">연산</option>');
			sub0_opts.append('<option value="연일">연일</option>');
			sub0_opts.append('<option value="연천">연천</option>');
			sub0_opts.append('<option value="연파">연파</option>');
			sub0_opts.append('<option value="연하">연하</option>');
			sub0_opts.append('<option value="영광">영광</option>');
			sub0_opts.append('<option value="영덕">영덕</option>');
			sub0_opts.append('<option value="영동">영동</option>');
			sub0_opts.append('<option value="영북">영북</option>');
			sub0_opts.append('<option value="영산">영산</option>');
			sub0_opts.append('<option value="영산포">영산포</option>');
			sub0_opts.append('<option value="영암">영암</option>');
			sub0_opts.append('<option value="영양">영양</option>');
			sub0_opts.append('<option value="영월">영월</option>');
			sub0_opts.append('<option value="영종">영종</option>');
			sub0_opts.append('<option value="영주">영주</option>');
			sub0_opts.append('<option value="영천">영천</option>');
			sub0_opts.append('<option value="영춘">영춘</option>');
			sub0_opts.append('<option value="영평">영평</option>');
			sub0_opts.append('<option value="영해">영해</option>');
			sub0_opts.append('<option value="영흥">영흥</option>');
			sub0_opts.append('<option value="예내">예내</option>');
			sub0_opts.append('<option value="예미">예미</option>');
			sub0_opts.append('<option value="예산">예산</option>');
			sub0_opts.append('<option value="예안">예안</option>');
			sub0_opts.append('<option value="예천">예천</option>');
			sub0_opts.append('<option value="오라">오라</option>');
			sub0_opts.append('<option value="오방">오방</option>');
			sub0_opts.append('<option value="오산">오산</option>');
			sub0_opts.append('<option value="오식">오식</option>');
			sub0_opts.append('<option value="오이">오이</option>');
			sub0_opts.append('<option value="옥동">옥동</option>');
			sub0_opts.append('<option value="옥련">옥련</option>');
			sub0_opts.append('<option value="옥림">옥림</option>');
			sub0_opts.append('<option value="옥산">옥산</option>');
			sub0_opts.append('<option value="옥정">옥정</option>');
			sub0_opts.append('<option value="옥천">옥천</option>');
			sub0_opts.append('<option value="온산">온산</option>');
			sub0_opts.append('<option value="온양">온양</option>');
			sub0_opts.append('<option value="옹천">옹천</option>');
			sub0_opts.append('<option value="와산">와산</option>');
			sub0_opts.append('<option value="와수">와수</option>');
			sub0_opts.append('<option value="완도">완도</option>');
			sub0_opts.append('<option value="왕등">왕등</option>');
			sub0_opts.append('<option value="왜관">왜관</option>');
			sub0_opts.append('<option value="외라도">외라도</option>');
			sub0_opts.append('<option value="외산">외산</option>');
			sub0_opts.append('<option value="외천">외천</option>');
			sub0_opts.append('<option value="외촌">외촌</option>');
			sub0_opts.append('<option value="욕지">욕지</option>');
			sub0_opts.append('<option value="용강">용강</option>');
			sub0_opts.append('<option value="용계">용계</option>');
			sub0_opts.append('<option value="용궁">용궁</option>');
			sub0_opts.append('<option value="용담">용담</option>');
			sub0_opts.append('<option value="용덕">용덕</option>');
			sub0_opts.append('<option value="용문">용문</option>');
			sub0_opts.append('<option value="용산">용산</option>');
			sub0_opts.append('<option value="용소">용소</option>');
			sub0_opts.append('<option value="용앙">용앙</option>');
			sub0_opts.append('<option value="용연">용연</option>');
			sub0_opts.append('<option value="용유">용유</option>');
			sub0_opts.append('<option value="용인">용인</option>');
			sub0_opts.append('<option value="용정">용정</option>');
			sub0_opts.append('<option value="용진">용진</option>');
			sub0_opts.append('<option value="용탄">용탄</option>');
			sub0_opts.append('<option value="용하">용하</option>');
			sub0_opts.append('<option value="용화">용화</option>');
			sub0_opts.append('<option value="용흥">용흥</option>');
			sub0_opts.append('<option value="우도">우도</option>');
			sub0_opts.append('<option value="우보">우보</option>');
			sub0_opts.append('<option value="우이">우이</option>');
			sub0_opts.append('<option value="우천">우천</option>');
			sub0_opts.append('<option value="우학">우학</option>');
			sub0_opts.append('<option value="운교">운교</option>');
			sub0_opts.append('<option value="운남">운남</option>');
			sub0_opts.append('<option value="운봉">운봉</option>');
			sub0_opts.append('<option value="운산">운산</option>');
			sub0_opts.append('<option value="운학">운학</option>');
			sub0_opts.append('<option value="울릉">울릉</option>');
			sub0_opts.append('<option value="울산">울산</option>');
			sub0_opts.append('<option value="울진">울진</option>');
			sub0_opts.append('<option value="웅양">웅양</option>');
			sub0_opts.append('<option value="웅천">웅천</option>');
			sub0_opts.append('<option value="원동">원동</option>');
			sub0_opts.append('<option value="원둔">원둔</option>');
			sub0_opts.append('<option value="원등">원등</option>');
			sub0_opts.append('<option value="원리">원리</option>');
			sub0_opts.append('<option value="원산도">원산도</option>');
			sub0_opts.append('<option value="원전">원전</option>');
			sub0_opts.append('<option value="원주">원주</option>');
			sub0_opts.append('<option value="원창">원창</option>');
			sub0_opts.append('<option value="원천">원천</option>');
			sub0_opts.append('<option value="월곡">월곡</option>');
			sub0_opts.append('<option value="월내">월내</option>');
			sub0_opts.append('<option value="월미">월미</option>');
			sub0_opts.append('<option value="월암">월암</option>');
			sub0_opts.append('<option value="위도">위도</option>');
			sub0_opts.append('<option value="위미">위미</option>');
			sub0_opts.append('<option value="유구">유구</option>');
			sub0_opts.append('<option value="유성">유성</option>');
			sub0_opts.append('<option value="유천">유천</option>');
			sub0_opts.append('<option value="율리">율리</option>');
			sub0_opts.append('<option value="율산">율산</option>');
			sub0_opts.append('<option value="율포">율포</option>');
			sub0_opts.append('<option value="은행">은행</option>');
			sub0_opts.append('<option value="음성">음성</option>');
			sub0_opts.append('<option value="읍내">읍내</option>');
			sub0_opts.append('<option value="의당">의당</option>');
			sub0_opts.append('<option value="의령">의령</option>');
			sub0_opts.append('<option value="의성">의성</option>');
			sub0_opts.append('<option value="의정부">의정부</option>');
			sub0_opts.append('<option value="이원">이원</option>');
			sub0_opts.append('<option value="이천">이천</option>');
			sub0_opts.append('<option value="익산">익산</option>');
			sub0_opts.append('<option value="인계">인계</option>');
			sub0_opts.append('<option value="인동">인동</option>');
			sub0_opts.append('<option value="인산">인산</option>');
			sub0_opts.append('<option value="인제">인제</option>');
			sub0_opts.append('<option value="인천">인천</option>');
			sub0_opts.append('<option value="일산">일산</option>');
			sub0_opts.append('<option value="일평">일평</option>');
			sub0_opts.append('<option value="임계">임계</option>');
			sub0_opts.append('<option value="임곡">임곡</option>');
			sub0_opts.append('<option value="임당">임당</option>');
			sub0_opts.append('<option value="임동">임동</option>');
			sub0_opts.append('<option value="임실">임실</option>');
			sub0_opts.append('<option value="임원">임원</option>');
			sub0_opts.append('<option value="임자">임자</option>');
			sub0_opts.append('<option value="입실">입실</option>');
			sub0_opts.append('<option value="자개">자개</option>');
			sub0_opts.append('<option value="자월">자월</option>');
			sub0_opts.append('<option value="자은">자은</option>');
			sub0_opts.append('<option value="자인">자인</option>');
			sub0_opts.append('<option value="장고">장고</option>');
			sub0_opts.append('<option value="장기">장기</option>');
			sub0_opts.append('<option value="장동">장동</option>');
			sub0_opts.append('<option value="장봉">장봉</option>');
			sub0_opts.append('<option value="장사">장사</option>');
			sub0_opts.append('<option value="장선">장선</option>');
			sub0_opts.append('<option value="장성">장성</option>');
			sub0_opts.append('<option value="장수">장수</option>');
			sub0_opts.append('<option value="장승포">장승포</option>');
			sub0_opts.append('<option value="장자">장자</option>');
			sub0_opts.append('<option value="장파">장파</option>');
			sub0_opts.append('<option value="장호">장호</option>');
			sub0_opts.append('<option value="장호원">장호원</option>');
			sub0_opts.append('<option value="장흥">장흥</option>');
			sub0_opts.append('<option value="재산">재산</option>');
			sub0_opts.append('<option value="저지">저지</option>');
			sub0_opts.append('<option value="적만">적만</option>');
			sub0_opts.append('<option value="적목">적목</option>');
			sub0_opts.append('<option value="적상">적상</option>');
			sub0_opts.append('<option value="전동">전동</option>');
			sub0_opts.append('<option value="전의">전의</option>');
			sub0_opts.append('<option value="전주">전주</option>');
			sub0_opts.append('<option value="점동">점동</option>');
			sub0_opts.append('<option value="점촌">점촌</option>');
			sub0_opts.append('<option value="접우">접우</option>');
			sub0_opts.append('<option value="정동">정동</option>');
			sub0_opts.append('<option value="정산">정산</option>');
			sub0_opts.append('<option value="정선">정선</option>');
			sub0_opts.append('<option value="정송">정송</option>');
			sub0_opts.append('<option value="정윤">정윤</option>');
			sub0_opts.append('<option value="정읍">정읍</option>');
			sub0_opts.append('<option value="정자">정자</option>');
			sub0_opts.append('<option value="정평">정평</option>');
			sub0_opts.append('<option value="제원">제원</option>');
			sub0_opts.append('<option value="제주">제주</option>');
			sub0_opts.append('<option value="제천">제천</option>');
			sub0_opts.append('<option value="조도">조도</option>');
			sub0_opts.append('<option value="조성">조성</option>');
			sub0_opts.append('<option value="조암">조암</option>');
			sub0_opts.append('<option value="조치원">조치원</option>');
			sub0_opts.append('<option value="좌사">좌사</option>');
			sub0_opts.append('<option value="좌운">좌운</option>');
			sub0_opts.append('<option value="좌천">좌천</option>');
			sub0_opts.append('<option value="좌항">좌항</option>');
			sub0_opts.append('<option value="주문진">주문진</option>');
			sub0_opts.append('<option value="주암">주암</option>');
			sub0_opts.append('<option value="주천">주천</option>');
			sub0_opts.append('<option value="주파">주파</option>');
			sub0_opts.append('<option value="죽령">죽령</option>');
			sub0_opts.append('<option value="죽변">죽변</option>');
			sub0_opts.append('<option value="죽산">죽산</option>');
			sub0_opts.append('<option value="죽석">죽석</option>');
			sub0_opts.append('<option value="죽장">죽장</option>');
			sub0_opts.append('<option value="줄포">줄포</option>');
			sub0_opts.append('<option value="중청">중청</option>');
			sub0_opts.append('<option value="증평">증평</option>');
			sub0_opts.append('<option value="지도">지도</option>');
			sub0_opts.append('<option value="지례">지례</option>');
			sub0_opts.append('<option value="지산">지산</option>');
			sub0_opts.append('<option value="지전">지전</option>');
			sub0_opts.append('<option value="지제">지제</option>');
			sub0_opts.append('<option value="지포">지포</option>');
			sub0_opts.append('<option value="진도">진도</option>');
			sub0_opts.append('<option value="진동">진동</option>');
			sub0_opts.append('<option value="진보">진보</option>');
			sub0_opts.append('<option value="진부">진부</option>');
			sub0_opts.append('<option value="진상">진상</option>');
			sub0_opts.append('<option value="진안">진안</option>');
			sub0_opts.append('<option value="진영">진영</option>');
			sub0_opts.append('<option value="진조">진조</option>');
			sub0_opts.append('<option value="진주">진주</option>');
			sub0_opts.append('<option value="진천">진천</option>');
			sub0_opts.append('<option value="진해">진해</option>');
			sub0_opts.append('<option value="차항">차항</option>');
			sub0_opts.append('<option value="창동">창동</option>');
			sub0_opts.append('<option value="창령">창령</option>');
			sub0_opts.append('<option value="창봉">창봉</option>');
			sub0_opts.append('<option value="창선">창선</option>');
			sub0_opts.append('<option value="창원">창원</option>');
			sub0_opts.append('<option value="창촌">창촌</option>');
			sub0_opts.append('<option value="창평">창평</option>');
			sub0_opts.append('<option value="천안">천안</option>');
			sub0_opts.append('<option value="천지">천지</option>');
			sub0_opts.append('<option value="철산">철산</option>');
			sub0_opts.append('<option value="철암">철암</option>');
			sub0_opts.append('<option value="철원">철원</option>');
			sub0_opts.append('<option value="청도">청도</option>');
			sub0_opts.append('<option value="청산">청산</option>');
			sub0_opts.append('<option value="청송">청송</option>');
			sub0_opts.append('<option value="청안">청안</option>');
			sub0_opts.append('<option value="청암">청암</option>');
			sub0_opts.append('<option value="청양">청양</option>');
			sub0_opts.append('<option value="청운">청운</option>');
			sub0_opts.append('<option value="청주">청주</option>');
			sub0_opts.append('<option value="청평">청평</option>');
			sub0_opts.append('<option value="청하">청하</option>');
			sub0_opts.append('<option value="초도">초도</option>');
			sub0_opts.append('<option value="추도">추도</option>');
			sub0_opts.append('<option value="추자">추자</option>');
			sub0_opts.append('<option value="추풍령">추풍령</option>');
			sub0_opts.append('<option value="춘양">춘양</option>');
			sub0_opts.append('<option value="춘천">춘천</option>');
			sub0_opts.append('<option value="충주">충주</option>');
			sub0_opts.append('<option value="칠곡">칠곡</option>');
			sub0_opts.append('<option value="칠량">칠량</option>');
			sub0_opts.append('<option value="칠보">칠보</option>');
			sub0_opts.append('<option value="칠산">칠산</option>');
			sub0_opts.append('<option value="칠전">칠전</option>');
			sub0_opts.append('<option value="칠포">칠포</option>');
			sub0_opts.append('<option value="탄천">탄천</option>');
			sub0_opts.append('<option value="탕곡">탕곡</option>');
			sub0_opts.append('<option value="태도">태도</option>');
			sub0_opts.append('<option value="태백">태백</option>');
			sub0_opts.append('<option value="태안">태안</option>');
			sub0_opts.append('<option value="태인">태인</option>');
			sub0_opts.append('<option value="토지">토지</option>');
			sub0_opts.append('<option value="통도사">통도사</option>');
			sub0_opts.append('<option value="통영">통영</option>');
			sub0_opts.append('<option value="통진">통진</option>');
			sub0_opts.append('<option value="퇴곡">퇴곡</option>');
			sub0_opts.append('<option value="파산">파산</option>');
			sub0_opts.append('<option value="판교">판교</option>');
			sub0_opts.append('<option value="판문점">판문점</option>');
			sub0_opts.append('<option value="판정">판정</option>');
			sub0_opts.append('<option value="팔금">팔금</option>');
			sub0_opts.append('<option value="팔미">팔미</option>');
			sub0_opts.append('<option value="평도">평도</option>');
			sub0_opts.append('<option value="평일">평일</option>');
			sub0_opts.append('<option value="평장">평장</option>');
			sub0_opts.append('<option value="평창">평창</option>');
			sub0_opts.append('<option value="평촌">평촌</option>');
			sub0_opts.append('<option value="평택">평택</option>');
			sub0_opts.append('<option value="평해">평해</option>');
			sub0_opts.append('<option value="포천">포천</option>');
			sub0_opts.append('<option value="포항">포항</option>');
			sub0_opts.append('<option value="표선">표선</option>');
			sub0_opts.append('<option value="풍곡">풍곡</option>');
			sub0_opts.append('<option value="풍기">풍기</option>');
			sub0_opts.append('<option value="풍천">풍천</option>');
			sub0_opts.append('<option value="하동">하동</option>');
			sub0_opts.append('<option value="하령">하령</option>');
			sub0_opts.append('<option value="하마">하마</option>');
			sub0_opts.append('<option value="하봉">하봉</option>');
			sub0_opts.append('<option value="하서">하서</option>');
			sub0_opts.append('<option value="하양">하양</option>');
			sub0_opts.append('<option value="하원">하원</option>');
			sub0_opts.append('<option value="하의">하의</option>');
			sub0_opts.append('<option value="학곡">학곡</option>');
			sub0_opts.append('<option value="학림">학림</option>');
			sub0_opts.append('<option value="한계">한계</option>');
			sub0_opts.append('<option value="한라산">한라산</option>');
			sub0_opts.append('<option value="한림">한림</option>');
			sub0_opts.append('<option value="한산">한산</option>');
			sub0_opts.append('<option value="함덕">함덕</option>');
			sub0_opts.append('<option value="함백">함백</option>');
			sub0_opts.append('<option value="함안">함안</option>');
			sub0_opts.append('<option value="함양">함양</option>');
			sub0_opts.append('<option value="함열">함열</option>');
			sub0_opts.append('<option value="함평">함평</option>');
			sub0_opts.append('<option value="합덕">합덕</option>');
			sub0_opts.append('<option value="합천">합천</option>');
			sub0_opts.append('<option value="해남">해남</option>');
			sub0_opts.append('<option value="해미">해미</option>');
			sub0_opts.append('<option value="해평">해평</option>');
			sub0_opts.append('<option value="향로봉">향로봉</option>');
			sub0_opts.append('<option value="현동">현동</option>');
			sub0_opts.append('<option value="현리">현리</option>');
			sub0_opts.append('<option value="현풍">현풍</option>');
			sub0_opts.append('<option value="호계">호계</option>');
			sub0_opts.append('<option value="호명">호명</option>');
			sub0_opts.append('<option value="홍도">홍도</option>');
			sub0_opts.append('<option value="홍산">홍산</option>');
			sub0_opts.append('<option value="홍성">홍성</option>');
			sub0_opts.append('<option value="홍천">홍천</option>');
			sub0_opts.append('<option value="화가">화가</option>');
			sub0_opts.append('<option value="화북">화북</option>');
			sub0_opts.append('<option value="화서">화서</option>');
			sub0_opts.append('<option value="화순">화순</option>');
			sub0_opts.append('<option value="화양">화양</option>');
			sub0_opts.append('<option value="화원">화원</option>');
			sub0_opts.append('<option value="화전">화전</option>');
			sub0_opts.append('<option value="화천">화천</option>');
			sub0_opts.append('<option value="화평">화평</option>');
			sub0_opts.append('<option value="환호">환호</option>');
			sub0_opts.append('<option value="황간">황간</option>');
			sub0_opts.append('<option value="황강">황강</option>');
			sub0_opts.append('<option value="황도">황도</option>');
			sub0_opts.append('<option value="황산">황산</option>');
			sub0_opts.append('<option value="황포">황포</option>');
			sub0_opts.append('<option value="회북">회북</option>');
			sub0_opts.append('<option value="회수">회수</option>');
			sub0_opts.append('<option value="횡성">횡성</option>');
			sub0_opts.append('<option value="흥정">흥정</option>');
			sub1_opts.empty();
		} else if ( value == "50k-dokak" ) {
			sub0_opts.empty();
			sub0_opts.append('<option value="(고산)">(고산)</option>');
			sub0_opts.append('<option value="(길산)">(길산)</option>');
			sub0_opts.append('<option value="(대서)">(대서)</option>');
			sub0_opts.append('<option value="(백도)">(백도)</option>');
			sub0_opts.append('<option value="(비양도)">(비양도)</option>');
			sub0_opts.append('<option value="(십이동파)">(십이동파)</option>');
			sub0_opts.append('<option value="(여서)">(여서)</option>');
			sub0_opts.append('<option value="(옥포)">(옥포)</option>');
			sub0_opts.append('<option value="(학리)">(학리)</option>');
			sub0_opts.append('<option value="가야">가야</option>');
			sub0_opts.append('<option value="간성">간성</option>');
			sub0_opts.append('<option value="갈담">갈담</option>');
			sub0_opts.append('<option value="갈말">갈말</option>');
			sub0_opts.append('<option value="감포">감포</option>');
			sub0_opts.append('<option value="강릉">강릉</option>');
			sub0_opts.append('<option value="강화">강화</option>');
			sub0_opts.append('<option value="개성">개성</option>');
			sub0_opts.append('<option value="거금">거금</option>');
			sub0_opts.append('<option value="거문">거문</option>');
			sub0_opts.append('<option value="거제">거제</option>');
			sub0_opts.append('<option value="거창">거창</option>');
			sub0_opts.append('<option value="경주">경주</option>');
			sub0_opts.append('<option value="고남">고남</option>');
			sub0_opts.append('<option value="고성">고성</option>');
			sub0_opts.append('<option value="고창">고창</option>');
			sub0_opts.append('<option value="고흥">고흥</option>');
			sub0_opts.append('<option value="곤양">곤양</option>');
			sub0_opts.append('<option value="공주">공주</option>');
			sub0_opts.append('<option value="관기">관기</option>');
			sub0_opts.append('<option value="관매">관매</option>');
			sub0_opts.append('<option value="광도">광도</option>');
			sub0_opts.append('<option value="광양">광양</option>');
			sub0_opts.append('<option value="광주">광주</option>');
			sub0_opts.append('<option value="교동">교동</option>');
			sub0_opts.append('<option value="구례">구례</option>');
			sub0_opts.append('<option value="구미">구미</option>');
			sub0_opts.append('<option value="구정">구정</option>');
			sub0_opts.append('<option value="군산">군산</option>');
			sub0_opts.append('<option value="군위">군위</option>');
			sub0_opts.append('<option value="근흥">근흥</option>');
			sub0_opts.append('<option value="금산">금산</option>');
			sub0_opts.append('<option value="금성">금성</option>');
			sub0_opts.append('<option value="금화">금화</option>');
			sub0_opts.append('<option value="기계">기계</option>');
			sub0_opts.append('<option value="길안">길안</option>');
			sub0_opts.append('<option value="김천">김천</option>');
			sub0_opts.append('<option value="김포">김포</option>');
			sub0_opts.append('<option value="김해">김해</option>');
			sub0_opts.append('<option value="나주">나주</option>');
			sub0_opts.append('<option value="난지">난지</option>');
			sub0_opts.append('<option value="남양">남양</option>');
			sub0_opts.append('<option value="남원">남원</option>');
			sub0_opts.append('<option value="남지">남지</option>');
			sub0_opts.append('<option value="남해">남해</option>');
			sub0_opts.append('<option value="내문">내문</option>');
			sub0_opts.append('<option value="내평">내평</option>');
			sub0_opts.append('<option value="논산">논산</option>');
			sub0_opts.append('<option value="눌옥">눌옥</option>');
			sub0_opts.append('<option value="단양">단양</option>');
			sub0_opts.append('<option value="담양">담양</option>');
			sub0_opts.append('<option value="당진">당진</option>');
			sub0_opts.append('<option value="대구">대구</option>');
			sub0_opts.append('<option value="대보">대보</option>');
			sub0_opts.append('<option value="대부">대부</option>');
			sub0_opts.append('<option value="대전">대전</option>');
			sub0_opts.append('<option value="대흑산">대흑산</option>');
			sub0_opts.append('<option value="덕산">덕산</option>');
			sub0_opts.append('<option value="덕적">덕적</option>');
			sub0_opts.append('<option value="도암">도암</option>');
			sub0_opts.append('<option value="도초">도초</option>');
			sub0_opts.append('<option value="독도">독도</option>');
			sub0_opts.append('<option value="독산">독산</option>');
			sub0_opts.append('<option value="돌산">돌산</option>');
			sub0_opts.append('<option value="동곡">동곡</option>');
			sub0_opts.append('<option value="두미">두미</option>');
			sub0_opts.append('<option value="마산">마산</option>');
			sub0_opts.append('<option value="만리포">만리포</option>');
			sub0_opts.append('<option value="만재">만재</option>');
			sub0_opts.append('<option value="매물">매물</option>');
			sub0_opts.append('<option value="매원">매원</option>');
			sub0_opts.append('<option value="맹골">맹골</option>');
			sub0_opts.append('<option value="모괴">모괴</option>');
			sub0_opts.append('<option value="모슬포">모슬포</option>');
			sub0_opts.append('<option value="목포">목포</option>');
			sub0_opts.append('<option value="무주">무주</option>');
			sub0_opts.append('<option value="무풍">무풍</option>');
			sub0_opts.append('<option value="묵호">묵호</option>');
			sub0_opts.append('<option value="문경">문경</option>');
			sub0_opts.append('<option value="문산">문산</option>');
			sub0_opts.append('<option value="미원">미원</option>');
			sub0_opts.append('<option value="밀양">밀양</option>');
			sub0_opts.append('<option value="방산">방산</option>');
			sub0_opts.append('<option value="방어진">방어진</option>');
			sub0_opts.append('<option value="백령">백령</option>');
			sub0_opts.append('<option value="백아">백아</option>');
			sub0_opts.append('<option value="법성">법성</option>');
			sub0_opts.append('<option value="병곡">병곡</option>');
			sub0_opts.append('<option value="보령">보령</option>');
			sub0_opts.append('<option value="보은">보은</option>');
			sub0_opts.append('<option value="복내">복내</option>');
			sub0_opts.append('<option value="볼음">볼음</option>');
			sub0_opts.append('<option value="봉평">봉평</option>');
			sub0_opts.append('<option value="부남">부남</option>');
			sub0_opts.append('<option value="부산">부산</option>');
			sub0_opts.append('<option value="부안">부안</option>');
			sub0_opts.append('<option value="불국사">불국사</option>');
			sub0_opts.append('<option value="비금">비금</option>');
			sub0_opts.append('<option value="사천">사천</option>');
			sub0_opts.append('<option value="산청">산청</option>');
			sub0_opts.append('<option value="삼가">삼가</option>');
			sub0_opts.append('<option value="삼척">삼척</option>');
			sub0_opts.append('<option value="상주">상주</option>');
			sub0_opts.append('<option value="서귀">서귀</option>');
			sub0_opts.append('<option value="서산">서산</option>');
			sub0_opts.append('<option value="서울">서울</option>');
			sub0_opts.append('<option value="서천">서천</option>');
			sub0_opts.append('<option value="서화">서화</option>');
			sub0_opts.append('<option value="석도">석도</option>');
			sub0_opts.append('<option value="선미">선미</option>');
			sub0_opts.append('<option value="설악">설악</option>');
			sub0_opts.append('<option value="성동">성동</option>');
			sub0_opts.append('<option value="성산">성산</option>');
			sub0_opts.append('<option value="소리">소리</option>');
			sub0_opts.append('<option value="소안">소안</option>');
			sub0_opts.append('<option value="소천">소천</option>');
			sub0_opts.append('<option value="소청">소청</option>');
			sub0_opts.append('<option value="소흑산">소흑산</option>');
			sub0_opts.append('<option value="속리">속리</option>');
			sub0_opts.append('<option value="속초">속초</option>');
			sub0_opts.append('<option value="손죽">손죽</option>');
			sub0_opts.append('<option value="수원">수원</option>');
			sub0_opts.append('<option value="순창">순창</option>');
			sub0_opts.append('<option value="순천">순천</option>');
			sub0_opts.append('<option value="신시">신시</option>');
			sub0_opts.append('<option value="신온">신온</option>');
			sub0_opts.append('<option value="신지">신지</option>');
			sub0_opts.append('<option value="아산">아산</option>');
			sub0_opts.append('<option value="안계">안계</option>');
			sub0_opts.append('<option value="안동">안동</option>');
			sub0_opts.append('<option value="안마">안마</option>');
			sub0_opts.append('<option value="안성">안성</option>');
			sub0_opts.append('<option value="안양">안양</option>');
			sub0_opts.append('<option value="안흥">안흥</option>');
			sub0_opts.append('<option value="양구">양구</option>');
			sub0_opts.append('<option value="양산">양산</option>');
			sub0_opts.append('<option value="양수">양수</option>');
			sub0_opts.append('<option value="어론">어론</option>');
			sub0_opts.append('<option value="어청">어청</option>');
			sub0_opts.append('<option value="언양">언양</option>');
			sub0_opts.append('<option value="엄정">엄정</option>');
			sub0_opts.append('<option value="여수">여수</option>');
			sub0_opts.append('<option value="여주">여주</option>');
			sub0_opts.append('<option value="연곡">연곡</option>');
			sub0_opts.append('<option value="연도">연도</option>');
			sub0_opts.append('<option value="연안">연안</option>');
			sub0_opts.append('<option value="연평">연평</option>');
			sub0_opts.append('<option value="영덕">영덕</option>');
			sub0_opts.append('<option value="영동">영동</option>');
			sub0_opts.append('<option value="영암">영암</option>');
			sub0_opts.append('<option value="영양">영양</option>');
			sub0_opts.append('<option value="영월">영월</option>');
			sub0_opts.append('<option value="영주">영주</option>');
			sub0_opts.append('<option value="영천">영천</option>');
			sub0_opts.append('<option value="예미">예미</option>');
			sub0_opts.append('<option value="예산">예산</option>');
			sub0_opts.append('<option value="예안">예안</option>');
			sub0_opts.append('<option value="예천">예천</option>');
			sub0_opts.append('<option value="오호">오호</option>');
			sub0_opts.append('<option value="와도">와도</option>');
			sub0_opts.append('<option value="완도">완도</option>');
			sub0_opts.append('<option value="왕징">왕징</option>');
			sub0_opts.append('<option value="왜관">왜관</option>');
			sub0_opts.append('<option value="외연">외연</option>');
			sub0_opts.append('<option value="욕지">욕지</option>');
			sub0_opts.append('<option value="용두">용두</option>');
			sub0_opts.append('<option value="용유">용유</option>');
			sub0_opts.append('<option value="용인">용인</option>');
			sub0_opts.append('<option value="운봉">운봉</option>');
			sub0_opts.append('<option value="울릉">울릉</option>');
			sub0_opts.append('<option value="울산">울산</option>');
			sub0_opts.append('<option value="울진">울진</option>');
			sub0_opts.append('<option value="원주">원주</option>');
			sub0_opts.append('<option value="위도">위도</option>');
			sub0_opts.append('<option value="음성">음성</option>');
			sub0_opts.append('<option value="의성">의성</option>');
			sub0_opts.append('<option value="이원">이원</option>');
			sub0_opts.append('<option value="이천">이천</option>');
			sub0_opts.append('<option value="익산">익산</option>');
			sub0_opts.append('<option value="인제">인제</option>');
			sub0_opts.append('<option value="인천">인천</option>');
			sub0_opts.append('<option value="일동">일동</option>');
			sub0_opts.append('<option value="임계">임계</option>');
			sub0_opts.append('<option value="임실">임실</option>');
			sub0_opts.append('<option value="임자">임자</option>');
			sub0_opts.append('<option value="자은">자은</option>');
			sub0_opts.append('<option value="장성">장성</option>');
			sub0_opts.append('<option value="장호원">장호원</option>');
			sub0_opts.append('<option value="장흥">장흥</option>');
			sub0_opts.append('<option value="전의">전의</option>');
			sub0_opts.append('<option value="전주">전주</option>');
			sub0_opts.append('<option value="정선">정선</option>');
			sub0_opts.append('<option value="정읍">정읍</option>');
			sub0_opts.append('<option value="제주">제주</option>');
			sub0_opts.append('<option value="제천">제천</option>');
			sub0_opts.append('<option value="조도">조도</option>');
			sub0_opts.append('<option value="죽굴">죽굴</option>');
			sub0_opts.append('<option value="죽도">죽도</option>');
			sub0_opts.append('<option value="죽변">죽변</option>');
			sub0_opts.append('<option value="진도">진도</option>');
			sub0_opts.append('<option value="진안">진안</option>');
			sub0_opts.append('<option value="진주">진주</option>');
			sub0_opts.append('<option value="진천">진천</option>');
			sub0_opts.append('<option value="창녕">창녕</option>');
			sub0_opts.append('<option value="창원">창원</option>');
			sub0_opts.append('<option value="철원">철원</option>');
			sub0_opts.append('<option value="청도">청도</option>');
			sub0_opts.append('<option value="청산">청산</option>');
			sub0_opts.append('<option value="청송">청송</option>');
			sub0_opts.append('<option value="청양">청양</option>');
			sub0_opts.append('<option value="청일">청일</option>');
			sub0_opts.append('<option value="청주">청주</option>');
			sub0_opts.append('<option value="청풍">청풍</option>');
			sub0_opts.append('<option value="초도">초도</option>');
			sub0_opts.append('<option value="추자">추자</option>');
			sub0_opts.append('<option value="춘양">춘양</option>');
			sub0_opts.append('<option value="춘천">춘천</option>');
			sub0_opts.append('<option value="충주">충주</option>');
			sub0_opts.append('<option value="태도">태도</option>');
			sub0_opts.append('<option value="태백">태백</option>');
			sub0_opts.append('<option value="통영">통영</option>');
			sub0_opts.append('<option value="평창">평창</option>');
			sub0_opts.append('<option value="평택">평택</option>');
			sub0_opts.append('<option value="포천">포천</option>');
			sub0_opts.append('<option value="포항">포항</option>');
			sub0_opts.append('<option value="표선">표선</option>');
			sub0_opts.append('<option value="하동">하동</option>');
			sub0_opts.append('<option value="하의">하의</option>');
			sub0_opts.append('<option value="한림">한림</option>');
			sub0_opts.append('<option value="한산">한산</option>');
			sub0_opts.append('<option value="함안">함안</option>');
			sub0_opts.append('<option value="함양">함양</option>');
			sub0_opts.append('<option value="합천">합천</option>');
			sub0_opts.append('<option value="해남">해남</option>');
			sub0_opts.append('<option value="현리">현리</option>');
			sub0_opts.append('<option value="홍도">홍도</option>');
			sub0_opts.append('<option value="홍성">홍성</option>');
			sub0_opts.append('<option value="홍천">홍천</option>');
			sub0_opts.append('<option value="화북">화북</option>');
			sub0_opts.append('<option value="화원">화원</option>');
			sub0_opts.append('<option value="화천">화천</option>');
			sub0_opts.append('<option value="회천">회천</option>');
			sub0_opts.append('<option value="횡도">횡도</option>');
			sub0_opts.append('<option value="흑도">흑도</option>');
			sub1_opts.empty();
		}
		$("select[name=dokak_select_sub0]").change();
	});
	
	
	$("select[name=dokak_select_sub0]").change(function () {
		
		var indexName = $("select[name=dokak_select]").val();
		var value = $("select[name=dokak_select_sub0]").val();
		
		if(value != null) {
			
			var sub1_opts = $("select[name=dokak_select_sub1]");
			sub1_opts.empty();
			
//			if( indexName == "5k-dokak") {
//				
//			}
			var cql_filter = "z0k_name like '"+value+"%'";
			OpenLayers.Request.POST({
				url: geoserver_host_wfs,
				params:{
					service:"wfs",
					request:"GetFeature",
					version:"1.1.0",
					typeName:indexName,
					propertyName:"z0k_name,z0k_number",
					srsName:"EPSG:5179",
					CQL_FILTER: cql_filter,
					outputFormat:"application/json"
				},
				headers: {'Accept':'text/xml'},
				success: function (res)
				{
					var jsonObj = new OpenLayers.Format.GeoJSON();
					var dataList = jsonObj.read(res.responseText); 
					$(dataList).each(function(index,feature) {
						sub1_opts.append('<option value="' + feature.data.z0k_number + '">' + feature.data.z0k_name + ' - ' + feature.data.z0k_number + '</option>');
					});
				}
			});
		}
	});
	
	$('#dokak_reset').click(function() {
		map.getLayersByName("5k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("25k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("50k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("5k-dokak")[0].setVisibility(false);
		map.getLayersByName("25k-dokak")[0].setVisibility(false);
		map.getLayersByName("50k-dokak")[0].setVisibility(false);
	});
	
	$('#dokak_move').click(function(){
		map.getLayersByName("5k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("25k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("50k-dokak")[0].params.CQL_FILTER;
		map.getLayersByName("5k-dokak")[0].setVisibility(false);
		map.getLayersByName("25k-dokak")[0].setVisibility(false);
		map.getLayersByName("50k-dokak")[0].setVisibility(false);

		var indexName = $("select[name=dokak_select]").val();
		var valueSub0 = $("select[name=dokak_select_sub0]").val();
		var valueSub1 = $("select[name=dokak_select_sub1]").val();
		
		if( indexName == null || valueSub0 == null ||  valueSub1 == null ) 
			return;
		
		if( dokak_layer != null ) {
			delete dokak_layer.params.CQL_FILTER;
		}
		dokak_layer = map.getLayersByName(indexName)[0];
		var cql_filter = "z0k_name like '"+valueSub0+"%' and z0k_number=" + valueSub1;
		console.log(cql_filter);
		dokak_layer.mergeNewParams({'CQL_FILTER': cql_filter});
		dokak_layer.redraw();
		dokak_layer.setVisibility(true);
		
		OpenLayers.Request.POST({
			url: geoserver_host_wfs,
			params:{
				service:"wfs",
				request:"GetFeature",
				version:"1.1.0",
				typeName:indexName,
				propertyName:"geom",
				srsName:"EPSG:5179",
				CQL_FILTER: cql_filter,
				outputFormat:"application/json"
			},
			headers: {'Accept':'text/xml'},
			success: function (res)
			{
				var jsonObj = new OpenLayers.Format.GeoJSON();
				var dataList = jsonObj.read(res.responseText); 
				map.zoomToExtent(dataList[0].geometry.getBounds());
			}
		});
	});
	
	
	 function urldecode (str) {  
	    return decodeURIComponent((str + '').replace(/\+/g, '%20'));  // 공백 문자인 + 를 처리하기 위해 +('%20') 을 공백으로 치환
	  }
	
	
});
