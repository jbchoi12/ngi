

Proj4js.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";
Proj4js.defs["EPSG:5186"] = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs";
Proj4js.defs["EPSG:5181"] = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs ";

var htmapErrorMsg = 'IE8이하 버전에서는 기능이 제한됩니다.';
var htmap = null;
var map, format, info, dokak_layer = null, infoPopup = null, boxDrawControl=null, layer_map_box, map_bbox = null;
var www = function () {};
var map_initCneter = new OpenLayers.LonLat(1024822.0477521, 1770105.9133858);
var map_initZoom = 7;
var vectorList = [];	//  화면에 그린 도형정보 리스트.
/*
var map_fullExtent = new OpenLayers.Bounds(527823.2989999996 ,1432553.2129999995 ,1408901.9500000002 ,2584061.9450000003);
var map_maxExtent = new OpenLayers.Bounds(-4620900, -7792300, 4620900, 7792300);
var map_restrictedExtent = new OpenLayers.Bounds(500946, 1439111, 1429636, 2188413);
var map_scales = [3000000, 1280000, 640000, 320000, 160000, 80000, 40000, 20000, 10000, 5000, 2500, 1250];
var map_serverResolutions = [ 793.7515875031751, 338.6673440013547, 169.33367200067735, 84.66683600033868, 42.33341800016934, 21.16670900008467, 
           10.583354500042335, 5.291677250021167, 2.6458386250105836, 1.3229193125052918, 0.6614596562526459, 0.33072982812632296 ];
*/

var map_fullExtent = new OpenLayers.Bounds(776485.0796999997, 1458604.6555000003, 1387945.3227000004, 2044838.5966999996);
var map_maxExtent = new OpenLayers.Bounds(776485.0796999997, 1458604.6555000003, 1387945.3227000004, 2044838.5966999996);
var map_restrictedExtent = new OpenLayers.Bounds(776485.0796999997, 1458604.6555000003, 1387945.3227000004, 2044838.5966999996);
var map_scales = [
4.727961550848E8
,2.363980775424E8
,1.181990387712E8
,5.90995193856E7
,2.95497596928E7
,1.47748798464E7
,7387439.9232,
                  3693719.9616, 
                  1846859.9808, 
                  923429.9904, 
                  461714.9952, 
                  230857.4976, 
                  115428.7488, 
                  57714.3744, 
                  28857.1872, 
                  14428.5936, 
                  7214.2968, 
                  3607.1484,
                  1803.5742,
                  901.7871];
var map_serverResolutions = [ 
                               125094.23288798578
                              ,62547.11644399289
                              ,31273.558221996445
                              ,15636.779110998223
                              ,7818.389555499111
                              ,3909.1947777495557
                              ,1954.5973888747778
                              ,977.2986944373889
                              ,488.64934721869446
                              ,244.32467360934723
                              ,122.16233680467361
                              ,61.08116840233681
                              ,30.540584201168404
                              ,15.270292100584202
                              ,7.635146050292101
                              ,3.8175730251460505
                              ,1.9087865125730252
                              ,0.9543932562865126
                              ,0.4771966281432563
                              ,0.23859831407162815 ];


/*
 * 
 *  
 *  125094.23288798578
 *  ,62547.11644399289
 *  ,31273.558221996445
 *  ,15636.779110998223
 *  ,7818.389555499111
 *  ,3909.1947777495557
 *  ,1954.5973888747778
 *  ,977.2986944373889
                              ,488.64934721869446,244.32467360934723,122.16233680467361,61.08116840233681,30.540584201168404,15.270292100584202
                              ,7.635146050292101,3.8175730251460505,1.9087865125730252,0.9543932562865126,0.4771966281432563,0.23859831407162815 ];
                              
 */
// 화면에 그려진 도형정보 초기화.
function resetVectorList() {
	vectorList = [];
	vectorLayer.removeAllFeatures();
	while( map.popups.length )  
        map.removePopup(map.popups[0]);
	
	if( search_markers != null )
		search_markers.destroy();	// 마커삭제.
}

// 벡터 iframe 정보 폼 값에 설정.
function saveVectorList(iframeName) {
	if(iframeName==undefined) { 
		alert("iframeName 에러");
	} else {
		var XCrdntLo = $(iframeName).contents().find('input[name="XCrdntLo"]').val();
		var YCrdntLa = $(iframeName).contents().find('input[name="YCrdntLa"]').val();
		if( XCrdntLo.length > 0 ) {
			pointStr = 'POINT (' +XCrdntLo + ' '+YCrdntLa +  ')';
			parent.vectorList.push(pointStr);
		}
		
		var vectors = parent.vectorList; 
		var vectorStrings = '';
		for(var index=0;index<vectors.length;index++) {
			vectorStrings = vectorStrings + vectors[index] + '|';
		}
		
		if(vectorStrings.length>0) {
			vectorStrings = vectorStrings.substring(0, vectorStrings.lastIndexOf('|'));
			$(iframeName).contents().find('input[name="vectorList"]').val(vectorStrings);
		}
	}
}



$(function() {
	
	// 베이스맵
	var basemap = new OpenLayers.Layer.WMTS({
        name: "NGII_EMAP",
        url: "http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/WMTS/",
        layer: "NGII_EMAP",
        matrixSet: "nativeTileMatrixSet",
       // matrixIds: matrixIds,
        format: "image/png",
        style: "_null"
        //, opacity: 0.7
        , isBaseLayer: true
        , requestEncoding:"KVP"
        , tileOrigin : new OpenLayers.LonLat(-200000.0, 4000000.0)
    });           
	
	//항공사진.
	var airmap = new OpenLayers.Layer.WMS("NGII_AIR",
	    "http://air.ngii.go.kr/ServletConnector/apiimage.do",
	    {
			layers:'apiImage',
	        transparent: true,
	        version: '1.1.1'
	    }, {
	        opacity: 1.0,
	        singleTile: true,
	        visibility: false,
	        getURL: function(bounds){
	        	bounds = this.adjustBounds(bounds);
	            var imageSize = this.getImageSize();
	            var newParams = {};
	            // WMS 1.3 introduced axis order
	            var reverseAxisOrder = this.reverseAxisOrder();
	            newParams.BBOX = this.encodeBBOX ?
	                bounds.toBBOX(null, reverseAxisOrder) :
	                bounds.toArray(reverseAxisOrder);
	            newParams.WIDTH = imageSize.w;
	            newParams.HEIGHT = imageSize.h;
	            // 주석처리. 15/04/23
	            //bounds.transform(new OpenLayers.Projection("EPSG:5179"), new OpenLayers.Projection("EPSG:5186"));
	            // 내용추가. 15/04/25
	            newBounds = bounds.clone();
				newBounds.transform(new OpenLayers.Projection("EPSG:5179"), new OpenLayers.Projection("EPSG:5186"));
				newParams.BBOX = newBounds.toArray();
				// 
	            var requestString = this.getFullRequestString(newParams);
	            return requestString;
	        }
	    }
    );
	
	// 5.인덱스.
	var index5k = new OpenLayers.Layer.WMS("5k-dokak", geoserver_host,
			{
		LAYERS: '5k-dokak',
		STYLES: '',
		format: 'image/png',
		transparent: 'true'
			},
			{
				buffer: 0,
				//displayOutsideMaxExtent: true,
				isBaseLayer: false, 
				visibility: false,
				singleTile: true,
				yx : {'EPSG:5179' : true}
			} 
	);
	
	// 25.인덱스.
	var index25k = new OpenLayers.Layer.WMS("25k-dokak", geoserver_host,
			{
		LAYERS: '25k-dokak',
		STYLES: '',
		format: 'image/png',
		transparent: 'true'
			},
			{
				buffer: 0,
				//displayOutsideMaxExtent: true,
				isBaseLayer: false, 
				visibility: false,
				singleTile: true,
				yx : {'EPSG:5179' : true}
			} 
	);
	
	// 50.인덱스.
	var index50k = new OpenLayers.Layer.WMS("50k-dokak", geoserver_host,
        {
            LAYERS: '50k-dokak',
            STYLES: '',
            format: 'image/png',
            transparent: 'true'
        },
        {
            buffer: 0,
            //displayOutsideMaxExtent: true,
            isBaseLayer: false, 
            visibility: false,
            singleTile: true,
            yx : {'EPSG:5179' : true}
        } 
    );
	
	// lc_legend_view 
	var lc_legend_view = new OpenLayers.Layer.WMS(
			"lc_legend_view", geoserver_host,
			{
				LAYERS: 'tn_change_lc_legend_view',
				STYLES: '',
				format: 'image/png',
				transparent: 'true'
			},
			{
				buffer: 0,
				//displayOutsideMaxExtent: true,
				isBaseLayer: false,
				visibility: false,
				singleTile: true,
				yx : {'EPSG:5179' : true}
			} 
	);
	
	// 새움터
	var eais_bldg_chg = new OpenLayers.Layer.WMS(
			"eais_bldg_chg", geoserver_host,
			{
				LAYERS: 'eais_bldg_chg',
				STYLES: '',
				format: 'image/png',
				transparent: 'true'
			},
			{
				buffer: 0,
				//displayOutsideMaxExtent: true,
				isBaseLayer: false,
				visibility: false,
				singleTile: true,
				yx : {'EPSG:5179' : true}
			} 
	); 
	
	// 새주소.
	var kais_bldg_chg = new OpenLayers.Layer.WMS(
			"kais_bldg_chg", geoserver_host,
			{
				LAYERS: 'kais_bldg_chg',
				STYLES: '',
				format: 'image/png',
				transparent: 'true'
			},
			{
				buffer: 0,
				//displayOutsideMaxExtent: true,
				isBaseLayer: false,
				visibility: false,
				singleTile: true,
				yx : {'EPSG:5179' : true}
			} 
	);
	
	
	
	layer_map_box = new OpenLayers.Layer.Vector("box_layer");
	//layer_map_box.setVisibility(false);

    map = new OpenLayers.Map({
        div: "mapContent",
        projection: new OpenLayers.Projection("EPSG:5179"),
        displayProjection: new OpenLayers.Projection("EPSG:5179"),
        theme: null,
        units:'m',
        controls: [],
        //allOverlays: true,
        maxExtent: map_maxExtent,
        // maxExtent: new OpenLayers.Bounds(564447.7698608811, 1589527.528386692, 1493137.1272395973, 2146741.1428139214),
        scales: map_scales,
        resolutions: map_serverResolutions,
        //restrictedExtent: map_restrictedExtent,
        maxScale: 1250,
        minScale: 3000000,
        maxResolution: 977.2986944373889
//      minResolution: 0.0439453125,
    });
    map.addLayer(basemap);
 	map.addLayer(airmap);
 	map.addLayer(index5k);
 	map.addLayer(index25k);
 	map.addLayer(index50k);
 	map.addLayer(kais_bldg_chg);
 	map.addLayer(eais_bldg_chg);
 	map.addLayer(lc_legend_view);
 	map.addLayer(layer_map_box);
 	
 	boxDrawControl = new OpenLayers.Control.DrawFeature(layer_map_box,
		OpenLayers.Handler.RegularPolygon, {
		    handlerOptions: {
		       sides: 4,
		       irregular: true,
		    },
		    callbacks: {
		    	"create" : function(geometry) {  
		    		layer_map_box.removeAllFeatures();
		    	} ,
		    	"done" : function(geometry) {
		    		var feature = new OpenLayers.Feature.Vector(geometry);
		           var proceed = this.layer.events.triggerEvent(
		               "sketchcomplete", {feature: feature}
		           );
		           if(proceed !== false) {
		               feature.state = OpenLayers.State.INSERT;
		               this.layer.addFeatures([feature]);
		               this.featureAdded(feature);
		               this.events.triggerEvent("featureadded",{feature : feature});
		           } 	                   
		    		boxDrawControl.deactivate();
		    		map_bbox = geometry.getBounds().toGeometry().toString();
		    	}
		    }
		}
 	);
 	map.addControl(boxDrawControl);
 	boxDrawControl.deactivate();
 	
// 	map.addLayer(lc_point_view);
// 	map.addLayer(lc_ln_view);
// 	map.addLayer(lc_myeon_view);
    
    if(window.location.hostname == 'localhost')
    	map.addControl(new OpenLayers.Control.LayerSwitcher());
    
    //map.zoomToExtent(map_restrictedExtent);
//    map.addControl(new OpenLayers.Control.Navigation());
    // 컨트롤.
    map.addControl(new OpenLayers.Control.PanZoomBar({panIcons:false}));
    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.ArgParser());
    map.addControl(new OpenLayers.Control.Attribution());
    nav = new OpenLayers.Control.NavigationHistory();
    map.addControl(nav);

    panel = new OpenLayers.Control.Panel(
        {div: document.getElementById("navi")}
    );
    panel.addControls([nav.next, nav.previous]);
    map.addControl(panel);
    
    var layerOverview = new OpenLayers.Layer.ArcGIS93Rest("NGII_EMAP",
    		"http://sd.ngii.go.kr:6080/arcgis/rest/services/NGII_EMAP/MapServer/export?",
    		{
                layer: "NGII_EMAP",
                //matrixSet: "nativeTileMatrixSet",
                format: "png",
                transparent: "false",
                mapScale: 0
                //style: "_null"
    		}, {
    			singleTile: true,
    	        projection: new OpenLayers.Projection("EPSG:5179"),
    	        displayProjection: new OpenLayers.Projection("EPSG:5179"),
    	        maxExtent: map_maxExtent
    			// scales: map_scales
    		}
    );
    var overview = new OpenLayers.Control.OverviewMap({
    	div: document.getElementById('divOverviewMap'), 
    	maximized: true 
        ,layers: [layerOverview]
	    ,size: new OpenLayers.Size(210, 270)
    	,minRatio: 20000
    });
    map.addControl(overview);
    map.setCenter(map_initCneter, map_initZoom);
    
    // 이벤트.
    map.events.register('zoomend', map, handleZoomEnd);
    map.events.register('moveend', map, handleMoveEnd);
    
    setMapScale();	//줌레벨축척을 화면에 표시 시작
    
    info = new OpenLayers.Control.WMSGetFeatureInfo({
        url: geoserver_host, 
        //title: 'Identify features by clicking',
        layers: [lc_legend_view],
        queryVisible: true,
        infoFormat:'application/json',
        eventListeners: {
            getfeatureinfo: function(event) {
            	var lonlat = map.getLonLatFromPixel(event.xy);
            	var jsonFormat = new OpenLayers.Format.JSON();
            	var jsonObj = jsonFormat.read(event.text);
            	
            	var contentHTML = '<div class="popChInfo"><table class="featureInfo">';
            	contentHTML += '<caption class="featureInfo">tn_change_lc_legend_view</caption>';
            	contentHTML += '<tbody><tr>';
            	contentHTML += '<th>제목</th><th>구분</th><th>유형</th><th>등록일</th><th>조회</th></tr>';

            	var linkURL = "";
            	
            	$( jsonObj.features ).each(function( index, data ) {
            		var prop = data.properties;
            		var html = '';
            		if(index%2==0)  html += '<tr><td>' + prop.change_sj + '</td>';
            		else  html += '<tr class="odd"><td>' + prop.change_sj + '</td>';
            		
            		if(prop.change_cl == '01') // 신고 일 경우.
            			linkURL = "NgiMngView.do?tabNum=1&changeInfoId=" + prop.change_info_id;
            		else
            			linkURL = "NgiMngView.do?tabNum=2&changeInfoId=" + prop.change_info_id;
            		
            		html += '<td>' + (prop.change_cl == '01' ? '변경신고' : '변동보고')  + '</td>';
        			html += '<td>' + prop.change_ty + '</td>';
        			html += '<td>' + prop.regist_de.substring(0,10) + '</td>';
        			html += '<td><a href="' + linkURL + '">상세정보</a></td>';
        			html += '</tr>';
        			contentHTML += html;
        		});
            	contentHTML += '</tbody></table></div>';
            	
            	if( infoPopup != null ) map.removePopup(infoPopup);
            	if(jsonObj.features.length>0) {
            		infoPopup = new OpenLayers.Popup.FramedCloud('featurePopup',
                			lonlat,
                		    new OpenLayers.Size(80, 120),
                		    contentHTML,
                		    null, true, function(){map.removePopup(infoPopup);}
                		);
                	infoPopup.calculateRelativePosition = function () { return 'br'; }
                	infoPopup.anchor.offset = new OpenLayers.Pixel(0, 40);
                	map.addPopup(infoPopup);
            	}
            }
        }
    });
    map.addControl(info);
    info.deactivate();
});

function addrCallback(data) {
	
	if( search_markers != null )
		search_markers.destroy();
	
	Proj4js.defs["EPSG:4326"] = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";
	Proj4js.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";
	
	var xpos = data.EPSG_4326_X;
	var ypos = data.EPSG_4326_Y;
	var lonlat = new OpenLayers.LonLat(xpos, ypos).transform('EPSG:4326', 'EPSG:5179');
	
	search_markers = new OpenLayers.Layer.Markers( "search_markers" );
    map.addLayer(search_markers);

    var size = new OpenLayers.Size(40,40);
    var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    var icon = new OpenLayers.Icon('../../js/openlayers/imgs/marker_test.png', size, offset);
    search_markers.addMarker(new OpenLayers.Marker(lonlat,icon));
    lonlat.clone();
    //map.setCenter(lonlat, 10);
    map.setCenter(lonlat, 18);
}

// 지도 신고위치 등록페이지 좌표 설정
function setXCrdntLoYCrdntLa(lonlat, pnu, addrType) {
	$('#iframeChgSttemntinfo').contents().find('input[name="XCrdntLo"]').val(lonlat.lon);
	$('#iframeChgSttemntinfo').contents().find('input[name="YCrdntLa"]').val(lonlat.lat);
	
	$('#iframeChgCntrwkinfo').contents().find('input[name="XCrdntLo"]').val(lonlat.lon);
	$('#iframeChgCntrwkinfo').contents().find('input[name="YCrdntLa"]').val(lonlat.lat);
	
	if(pnu != undefined)
		$('#iframeChgSttemntinfo').contents().find('input[name="pnuCd"]').val(pnu);
	
	if(addrType != undefined) 
		$('#iframeChgSttemntinfo').contents().find('input[name="addrTy"]').val(addrType);
}

// 변동신고내역 위치 지도 이동  
function setPointOfMove(xpos, ypos) {
	
	if(xpos == 0) {
		alert('좌표값이 없습니다.');
		return;
	} 

	var lonlat = new OpenLayers.LonLat(xpos, ypos);
	
	if( search_markers != null )
		search_markers.destroy();
	
	search_markers = new OpenLayers.Layer.Markers( "search_markers" );
    map.addLayer(search_markers);

    var size = new OpenLayers.Size(40,40);
    var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    var icon = new OpenLayers.Icon('../../js/openlayers/imgs/marker_test.png', size, offset);
    search_markers.addMarker(new OpenLayers.Marker(lonlat,icon));
    lonlat.clone();

    //map.setCenter(lonlat, 7);
    map.setCenter(lonlat, 18);
}

/**
 * 지오메트리 삭제
 */
function deleteVectorList(wkt) {
	for(var index=0;index<vectorList.length;index++) {
		if( vectorList[index] == wkt ) {
			vectorList.splice(index,1);
		}
	}
}

/** 지도 zoomend 이벤트 */
function handleZoomEnd() {
	setMapScale(); //줌레벨축척을 화면에 표시 시작
	//setMapBoxLayer();
	if( map.zoom < 7 )
		map.setCenter(map_initCneter, map_initZoom);
}

/** 지도 zoomend 이벤트 */
function handleMoveEnd() {
	//setMapBoxLayer();
	heamapUpdate();
}

/** 지도 click 이벤트 */
function handleMapClick(e) {
	
}

function setMapBoxLayer() {
	layer_map_box.removeAllFeatures();
	var gm = new OpenLayers.Geometry.fromWKT(map.getExtent().toGeometry().toString());
	var f = new OpenLayers.Feature.Vector(gm);
	layer_map_box.addFeatures(f);
}

/**
 info = new OpenLayers.Control.WMSGetFeatureInfo({
            url: 'http://demo.opengeo.org/geoserver/wms', 
            title: 'Identify features by clicking',
            layers: [water],
            queryVisible: true,
            eventListeners: {
                getfeatureinfo: function(event) {
                    map.addPopup(new OpenLayers.Popup.FramedCloud(
                        "chicken", 
                        map.getLonLatFromPixel(event.xy),
                        null,
                        event.text,
                        null,
                        true
                    ));
                }
            }
        });
        map.addControl(info);
        info.activate();
 */

/** 현재지도 스케일 */
function setMapScale() {
	$('#zoomScale').empty();
	$('#zoomScale').text( '현재축척: 1/' + numberCommaFormat(map_scales[map.zoom])  );
}




function IEver () {
	var myNav = navigator.userAgent.toLowerCase();
	return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
}

/** 히트맵 클러스터 에러 **/
function htmapError() {
	if(IEver()==false || IEver() > 8)
	{
		return true;
	} else {
		alert(htmapErrorMsg);
	}
	return false;
}

function heamapUpdate(year){
	
	if(year != undefined) {
		
		if(htmap!=null) {
			OpenLayers.Request.POST({
				url: geoserver_host_wfs+"?",
				proxy: proxy_url,
				params:{
					service:"wfs",
					request:"GetFeature",
					version:"1.0.0",
					typeName:"tn_change_lc_legend_view",
					//BBOX: map.getExtent().toBBOX(),
					//CQL_FILTER: "regist_de between '"+year+"-01-01' and '"+year+"-12-31'",
					CQL_FILTER: "BBOX(location,"+map.getExtent().toBBOX()+") AND regist_de between '"+year+"-01-01' and '"+year+"-12-31'",     
					srsName:"EPSG:5179",
					outputFormat:"json"
				},
				headers: {'Accept':'application/json'},
				// headers: {'Accept':'text/xml'},
				success: function (res)
				{
					htmap.points = [];
					htmap.redraw();
					
					var json = new OpenLayers.Format.JSON().read( res.responseText );
					for(var i=0;i<json.features.length;i++) {
						point = new Heatmap.Source(new OpenLayers.LonLat(json.features[i].geometry.coordinates[0], json.features[i].geometry.coordinates[1]));
						htmap.addSource(point);
					}
					
					htmap.defaultIntensity = 0.25; //0.1; //0.25;
					htmap.setOpacity(0.8);
					//htmap.setVisibility(true);
					htmap.redraw();
					
				}
			});
		}
		
	}
}


function heatmapOn(visible, year) {
	heamapUpdate(year);
	htmap.setVisibility(visible);
	map.raiseLayer(htmap);
}
	
$().ready(function(){
	if(IEver()==false || IEver() > 8)
	{
		if( htmap == null) {
			htmap = new Heatmap.Layer("htmap", {visibility: false});
			htmap.setOpacity(0.8);
			map.addLayer(htmap);
		}
	}
});



