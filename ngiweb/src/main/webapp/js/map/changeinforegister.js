Proj4js.defs["EPSG:4326"] = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";
Proj4js.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";

measurePoint_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);
measureLine_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);
measurePolygon_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);

measurePoint_style.pointRadius = 6;
measurePoint_style.graphicName = "circle";
measurePoint_style.fillColor = "#ffffff";
measurePoint_style.fillOpacity = 0.5;
measurePoint_style.strokeWidth = 2;
measurePoint_style.strokeOpacity = 0.6;
measurePoint_style.strokeColor = "#E41536";

measureLine_style.strokeWidth = 2;
measureLine_style.strokeOpacity = 0.6;
measureLine_style.strokeColor = "#E41536";
measureLine_style.strokeDashstyle = "solid";

measurePolygon_style.strokeWidth = 2;
measurePolygon_style.strokeOpacity = 0.6;
measurePolygon_style.strokeColor = "#214DD8";
measurePolygon_style.strokeDashstyle = "solid";
measurePolygon_style.fillColor = "#214DD8";
measurePolygon_style.fillOpacity = 0.2;

/**
 * 돈단위 같은 3자릿수 마다 ',' 추가 함수
 * @param
 * @returns
 */
function numberCommaFormat(num) {
	var str = String(num);
    var pattern = /(-?[0-9]+)([0-9]{3})/;
    while(pattern.test(str)) {
    	str = str.replace(pattern,"$1,$2");
    }
    return str;
}

/** 변경신고 위치입력 */
function regAddrPoint() {
	parent.regAddrPoint();
}

/** 변경신고 주소검색 팝업 */
function regAddrSearch() {
	var popUrl = "../../regaddrsearch.jsp";	//팝업창에 출력될 페이지 URL
	var popOption = "width=310, height=40, top=200, left=200, resizable=no, scrollbars=no, status=no, location=no, menubar=no";    //팝업창 옵션(optoin)
	var popupWindow = window.open(popUrl,"regAddrSearch",popOption);
	popupWindow.focus();
}

/**
 * Method: getBestArea
 * Based on the <displaySystem> returns the area of a geometry.
 *
 * Parameters:
 * geometry - {<OpenLayers.Geometry>}
 *
 * Returns:
 * {Array([Float, String])}  Returns a two item array containing the
 *     area and the units abbreviation.
 */
var displaySystem = 'metric';
var displaySystemUnits = {
     geographic: ['dd'],
     english: ['mi', 'ft', 'in'],
     metric: ['km', 'm']
 };
 
/**
 * Method: getArea
 *
 * Parameters:
 * geometry - {<OpenLayers.Geometry>}
 * units - {String} Unit abbreviation
 *
 * Returns:
 * {Float} The geometry area in the given units.
 */
 function getArea(geometry, units) {
    var area, geomUnits;
    if(this.geodesic) {
        area = geometry.getGeodesicArea(parent.map.getProjectionObject());
        geomUnits = "m";
    } else {
        area = geometry.getArea();
        geomUnits = parent.map.getUnits();
    }
    var inPerDisplayUnit = OpenLayers.INCHES_PER_UNIT[units];
    if(inPerDisplayUnit) {
        var inPerMapUnit = OpenLayers.INCHES_PER_UNIT[geomUnits];
        area *= Math.pow((inPerMapUnit / inPerDisplayUnit), 2);
    }
    return area;
};

 function getBestArea(geometry) {
    var units = this.displaySystemUnits[this.displaySystem];
    var unit, area;
    for(var i=0, len=units.length; i<len; ++i) {
        unit = units[i];
        area = this.getArea(geometry, unit);
        if(area > 1) {
            break;
        }
    }
    return [area, unit];
};

function getLength(geometry, units) {
    var length, geomUnits;
    if(this.geodesic) {
        length = geometry.getGeodesicLength(parent.map.getProjectionObject());
        geomUnits = "m";
    } else {
        length = geometry.getLength();
        geomUnits = parent.map.getUnits();
    }
    var inPerDisplayUnit = OpenLayers.INCHES_PER_UNIT[units];
    if(inPerDisplayUnit) {
        var inPerMapUnit = OpenLayers.INCHES_PER_UNIT[geomUnits];
        length *= (inPerMapUnit / inPerDisplayUnit);
    }
    return length;
};
 
 function getBestLength(geometry) {
     var units = this.displaySystemUnits[this.displaySystem];
     var unit, length;
     for(var i=0, len=units.length; i<len; ++i) {
         unit = units[i];
         length = this.getLength(geometry, unit);
         if(length > 1) {
             break;
         }
     }
     return [length, unit];
 };
 
 /**
  * 벡터 팝업 생성,
  * @param geom
  * @param edit  편집 가능 여부? - boolean
  */
 var aaa = null;
 function createNewPopup(geom, edit) {
	 
		if (geom.geometry instanceof OpenLayers.Geometry.Point) {
			// point는 아무것도 안함.
		}
		if(geom.geometry instanceof OpenLayers.Geometry.MultiLineString) {
			//선형
			geom.style = measureLine_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
				
			geometryTopLi = geom.geometry.getVertices();
			
			length = getBestLength(geom.geometry)[0];
			unit = getBestLength(geom.geometry)[1];

			var popup = new OpenLayers.Popup(
					null,
	                new OpenLayers.LonLat(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y),
	                null,
	                '<div class="measure_popup">총거리    <br>' + numberCommaFormat(length.toFixed(2)) + ' ' + unit + '</div>',
	                false
	            );
	        popup.autoSize = true;
	        popup.setBorder('1px solid #E41536');
	        parent.map.addPopup(popup);
	        
	        if( edit == true ) {
	        	popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		        });
	        }
		        
		}
		if (geom.geometry instanceof OpenLayers.Geometry.LineString ) {
			//선형
			geom.style = measureLine_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopLi = geom.geometry.getVertices();
			
			length = getBestLength(geom.geometry)[0];
			unit = getBestLength(geom.geometry)[1];

			var popup = new OpenLayers.Popup(
					null,
	                new OpenLayers.LonLat(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y),
	                null,
	                '<div class="measure_popup">총거리    <br>' + numberCommaFormat(length.toFixed(2)) + ' ' + unit + '</div>',
	                false
	            );
	        popup.autoSize = true;
	        popup.setBorder('1px solid #E41536');
	        parent.map.addPopup(popup);
	        
	        if( edit == true ) {
	        	popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		        });
	        }
	        
		} 
		if (geom.geometry instanceof OpenLayers.Geometry.Polygon) {
			//면형.
			geom.style = measurePolygon_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopPo = geom.geometry.getVertices();
			
			area = getBestArea(geom.geometry)[0];
			unit = getBestArea(geom.geometry)[1];
			
	        var popup = new OpenLayers.Popup(
	        	null,
             new OpenLayers.LonLat(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y),
             null,
             '<div class="measure_popup" style="color:#214DD8 !important;">총면적    <br>'
             + numberCommaFormat( area.toFixed(2) ) + ' ' + unit + '<sup>2</sup></div>',
             false
	         );
	         popup.autoSize = true;
	         popup.setBorder('1px solid #214DD8');
	         parent.map.addPopup(popup);
	         
	         if( edit == true ) {
		         popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		         });
	         }
		}
		
		if (geom.geometry instanceof OpenLayers.Geometry.MultiPolygon) {
			//면형.
			geom.style = measurePolygon_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopPo = geom.geometry.getVertices();
			
			area = getBestArea(geom.geometry)[0];
			unit = getBestArea(geom.geometry)[1];
			
	        var popup = new OpenLayers.Popup(
	        	null,
             new OpenLayers.LonLat(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y),
             null,
             '<div class="measure_popup" style="color:#214DD8 !important;">총면적    <br>'
             + numberCommaFormat( area.toFixed(2) ) + ' ' + unit + '<sup>2</sup></div>',
             false
	         );
	         popup.autoSize = true;
	         popup.setBorder('1px solid #214DD8');
	         parent.map.addPopup(popup);
	         
	         if( edit == true ) {
		         popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		         });
	         }
		}		
		
 }
 
 function createNewPopup2(geom, edit) {
	 
		if (geom.geometry instanceof OpenLayers.Geometry.Point) {
			// point는 아무것도 안함.
		}
		if(geom.geometry instanceof OpenLayers.Geometry.MultiLineString) {
			//선형
			geom.style = measureLine_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
				
			geometryTopLi = geom.geometry.getVertices();
			
			length = getBestLength(geom.geometry)[0];
			unit = getBestLength(geom.geometry)[1];

	        if( edit == true ) {
	        	popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		        });
	        }
		        
		}
		if (geom.geometry instanceof OpenLayers.Geometry.LineString ) {
			//선형
			geom.style = measureLine_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopLi = geom.geometry.getVertices();
			
			length = getBestLength(geom.geometry)[0];
			unit = getBestLength(geom.geometry)[1];

	        
	        if( edit == true ) {
	        	popup.addCloseBox(function(){
		        	parent.map.removePopup(popup);
		            parent.vectorLayer.removeFeatures(geom);
		            parent.deleteVectorList(geom.geometry.toString());
		        });
	        }
	        
		} 
		if (geom.geometry instanceof OpenLayers.Geometry.Polygon) {
			//면형.
			geom.style = measurePolygon_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopPo = geom.geometry.getVertices();
			
			area = getBestArea(geom.geometry)[0];
			unit = getBestArea(geom.geometry)[1];
			

		}
		
		if (geom.geometry instanceof OpenLayers.Geometry.MultiPolygon) {
			//면형.
			geom.style = measurePolygon_style;
			parent.vectorLayer.addFeatures(geom);
			parent.vectorList.push(geom.geometry.toString());
			
			geometryTopPo = geom.geometry.getVertices();
			
			area = getBestArea(geom.geometry)[0];
			unit = getBestArea(geom.geometry)[1];


		}		
		
} 
 
 
 
function addrCallback(data) {
	var lonlat = new OpenLayers.LonLat(data.EPSG_4326_X, data.EPSG_4326_Y).transform('EPSG:4326', 'EPSG:5179');
    var textHtml = '<p>'+data.JUSO+', x:'+lonlat.lon+', y:'+lonlat.lat+'</p>';
    $('#regAddrSearch_result').append(textHtml);
    parent.addrCallback(data);
}

function search_loca_poi(data) {
	//주소검색 마커 생성.
	parent.search_loca_poi(data);
}

function search_loca_setname(oldAddr, newAddr, xpos, ypos, pnu, addrType) {
	$('#changeLnmAdresCn').val(oldAddr); // 지번주소.
	$('#changeRnAdresCn').val(newAddr);	// 도로명주소.
	
	var lonlat = new OpenLayers.LonLat(xpos, ypos).transform('EPSG:4326', 'EPSG:5179');
	parent.setXCrdntLoYCrdntLa(lonlat, pnu, addrType);
}

function initsetXCrdntLoYCrdntLa() {

	// pnu 구하기.
	var mapCenter = parent.map.getCenter();
	var pnuForLonLat = new OpenLayers.LonLat( mapCenter.lon, mapCenter.lat ).transform('EPSG:5179', 'EPSG:4326');
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
	        		parent.setXCrdntLoYCrdntLa(mapCenter, pnu);
	        	}
	    	} else {
	    		parent.setXCrdntLoYCrdntLa(mapCenter, '');
	    	}
	    	
	    }
	});
	
	/*
	$.ajax({
		type: 'GET',
        url : '../../proxy.jsp?resourceUrl=' + encodeURIComponent(reqURL + reqParam),
        data : null,
        dataType : "json",
        success : function(data) {
        	//console.log('pnu: ' + data.featureCollection.features[0].properties.PNU);
        	//console.log('addr: ' + data.featureCollection.features[0].properties.ADDR);
        	pnu = data.featureCollection.features[0].properties.PNU;
        	if(pnu.length>0) {
        		parent.setXCrdntLoYCrdntLa(mapCenter, pnu);
        	}
        }, error: function(err) {
        	//console.log('error');
        	parent.setXCrdntLoYCrdntLa(mapCenter, '');
        }
	});
	*/
	
}

$(function(){


	/** 변경신고 주소검색 팝업 */
	$('#regAddrSearch').click(function(){
		regAddrSearch();
	});
	
	/** 변경신고 위치입력 */
	$('#regAddrPoint').click(function() {
		parent.regAddrPoint();
	});
	$('#regAddrPoint_b').click(function() {
		parent.regAddrPoint();
	});
	
	// 위치입력.
	$('#regAddrPoint').mouseover(function(){
		$('#regAddrPoint_pop').show();
	});// 위치입력.
	$('#regAddrPoint').mouseout(function(){
		$('#regAddrPoint_pop').hide();
	});
	$('#regAddrPoint_b').mouseover(function(){
		$('#regAddrPoint_pop_b').show();
	});// 위치입력.
	$('#regAddrPoint_b').mouseout(function(){
		$('#regAddrPoint_pop_b').hide();
	});
	
	
//	$("#regAddrSearch_text").keyup(function(event){
//		if(event.keyCode == 13) {
//			$("#regAddrSearch_go").click();
//		}
//	});
	
	// 변경신고 선 추가.
	$('#regAddrAddLine').click(function(){
		parent.setWorkMode(81);
	});
	
	// 변경신고 면 추가.
	$('#regAddrAddPolygon').click(function(){
		parent.setWorkMode(80);
	});
	
});






