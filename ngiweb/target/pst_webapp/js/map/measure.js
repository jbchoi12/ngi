/**
 * 지도 거리, 면적 분배 함수
 * @param type : 81 : line / 80 : polygon
 */
function setWorkMode(type) {
    if(type == 81) {
    	toggleControl_action('line');
    } else if(type == 80) {
    	toggleControl_action('polygon');
    } 
    $('.tooltip').html('마우스를 더블클릭하여<br>종료합니다.');
    $('.tooltip').show();
}

var measureControls, vectorLayer;	//거리, 면적 측정 컨트롤 및 vectorlayer 변수
var measurePoint_style, measureLine_style, measurePolygon_style, measurePolygon_Point_style; //거리, 면적 측정 스타일
var sketchSymbolizers;
var tempFea = new Array(); //거리, 면적 측정 점 배열
var tempPop = new Array(); //거리, 면적 측정 팝업 배열

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

/**
 * 초기화 함수
 */
function btnClearMap() {
	measureReset();	// 거리면적.
}

/**
 * 지도 거리, 면적 실행 함수
 * @param type : 'point', 'line', 'polygon'
 */
function toggleControl_action(element) {
    for(key in measureControls) {
        var control = measureControls[key];
        if(element == key) {
//            control.setImmediate(element.checked)
            control.activate();
            if(key == 'line') {
            	sketchSymbolizers.Point.strokeColor = "#E41536";
            } else if(key == 'polygon') {
            	sketchSymbolizers.Point.strokeColor = "#214DD8";
            } else if(key == 'point2') {
            	sketchSymbolizers.Point.strokeColor = "#214DD8";
            }
        } else {
            control.deactivate();
        }
    }
}

/**
 * 지도 거리, 면적 초기화 함수
 */
//거리, 면적 초기화 함수
function measureReset() {
    vectorLayer.removeAllFeatures();
    for(var k=map.popups.length;k>0;k--) {
        map.removePopup(map.popups[0]);
    }
    toggleControl_action('reset');
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

/**
 * 지도 거리, 면적 측정 진행 이벤트 함수
 * @param event
 */
function handleMeasurements(event) {
    var geometry = event.geometry;
    var geometryTopLi = geometry.getVertices(true);
    var geometryTopPo = geometry.getVertices(false);
    var units = event.units;
    var order = event.order;
    var measure = event.measure;
//    var tempNum = (numberCommaFormat(measure.toFixed(0)).length / 3).toFixed(0);
//    var tempNum = numberCommaFormat(measure.toFixed(0)).length;
//    var popupWidth = ((tempNum - 0) * 4) * 4 + 43;
//    var popupWidth = tempNum;
    if(order == 1) {
        popup = new OpenLayers.Popup(
            "measureDistance",
            new OpenLayers.LonLat(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y),
            (measure.toFixed(0) == 0)? null:null,
            (measure.toFixed(0) == 0)?'<div class="measure_popup">시작점</div>': 
            	'<div class="measure_popup">'+numberCommaFormat(measure.toFixed(2)) + ' ' + units +  '</div>',
            false
        );
        popup.autoSize = true;
        popup.setBorder('1px solid #E41536');
        var poLi = new OpenLayers.Geometry.Point(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y);
        var pointLi_feature = new OpenLayers.Feature.Vector(poLi, null, measurePoint_style );
        vectorLayer.addFeatures(pointLi_feature);
        tempFea.push(pointLi_feature);
        tempPop.push(popup);
        map.addPopup(popup);
    } else {
        popup = new OpenLayers.Popup(
            "measureDistance",
            new OpenLayers.LonLat(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y),
            null,
            '<div class="measure_popup">총면적222    <br>' + numberCommaFormat(measure.toFixed(2)) + ' ' + units + "<sup>2</sup></div>",
            false
        );
        popup.autoSize = true;
        tempPop.push(popup);
        var poPo = new OpenLayers.Geometry.Point(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y);
        var pointPo_feature = new OpenLayers.Feature.Vector(poPo, null, measurePolygon_Point_style );
        vectorLayer.addFeatures(pointPo_feature);
        tempFea.push(pointPo_feature);
    }
    popup.updateSize();
}

/**
 * 지도 거리, 면적 측정 종료 이벤트 함수
 * @param event
 */
function complateEx(event) {
    var geometry = event.geometry;
    
    vectorList.push(geometry.toString());	// 벡터정보 입력.
    
    var geometryTopLi = geometry.getVertices(true);
    var geometryTopPo = geometry.getVertices(false);
    var units = event.units;
    var order = event.order;
    var measure = event.measure;
    //var tempNum = (numberCommaFormat(measure.toFixed(0)).length / 3).toFixed(0);
    //var popupWidth = ((tempNum - 0) * 4) * 4 + 43 + 10;
    if(order == 1 ) {                
//        popup = new OpenLayers.Popup(
//            "measureDistance",
//            new OpenLayers.LonLat(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y),
//            new OpenLayers.Size(popupWidth,17),
//            '<div class="measure_popup">' + numberCommaFormat(measure.toFixed(2)) + ' ' + units + '</div>',
//            false
//        );
        popup = new OpenLayers.Popup(
                "measureDistance",
                new OpenLayers.LonLat(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y),
                null,
                '<div class="measure_popup">총거리    <br>' + numberCommaFormat(measure.toFixed(2)) + ' ' + units + '</div>',
                false
            );
        popup.autoSize = true;
        popup.setBorder('1px solid #E41536');
        var poLi = new OpenLayers.Geometry.Point(geometryTopLi[geometryTopLi.length-1].x,geometryTopLi[geometryTopLi.length-1].y);
        var pointLi_feature = new OpenLayers.Feature.Vector(poLi, null, measurePoint_style );
        vectorLayer.addFeatures(pointLi_feature);
        tempFea.push(pointLi_feature);
        var line_feature = new OpenLayers.Feature.Vector(geometry, null, measureLine_style );                
        vectorLayer.addFeatures(line_feature);
        tempPop.push(popup);
        map.addPopup(popup);
        var obj = tempPop;
        var obj2 = tempFea;
        popup.addCloseBox(function(){
        	deleteVectorList(line_feature.geometry.toString());	// 벡터삭제
            vectorLayer.removeFeatures(line_feature);
            for(var t in obj) {
                map.removePopup(obj[t]);
                vectorLayer.removeFeatures(obj2[t]);
            }
        });
        tempPop = new Array();
        tempFea = new Array();
        var control = measureControls['line'];
        control.deactivate();
    }
    else {
        popup = new OpenLayers.Popup(
            "measureDistance",
            new OpenLayers.LonLat(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y),
            null,
            '<div class="measure_popup" style="color:#214DD8 !important;">총면적    <br>' + numberCommaFormat(measure.toFixed(2)) + ' ' + units + '<sup>2</sup></div>',
            false
        );
        popup.autoSize = true;
        popup.setBorder('1px solid #214DD8');
        tempPop.push(popup);
        map.addPopup(popup);
        var poPo = new OpenLayers.Geometry.Point(geometryTopPo[geometryTopPo.length-1].x,geometryTopPo[geometryTopPo.length-1].y);
        var pointPo_feature = new OpenLayers.Feature.Vector(poPo, null, measurePolygon_Point_style );
        vectorLayer.addFeatures(pointPo_feature);
        tempFea.push(pointPo_feature);
        var polygon_feature = new OpenLayers.Feature.Vector(geometry, null, measurePolygon_style );                
        vectorLayer.addFeatures(polygon_feature);
        var obj = tempPop;
        var obj2 = tempFea;
        popup.addCloseBox(function(){
        	deleteVectorList(polygon_feature.geometry.toString());
            vectorLayer.removeFeatures(polygon_feature);
            for(var t in obj2) {
                map.removePopup(obj[t]);
                vectorLayer.removeFeatures(obj2[t]);
            }
        });
        tempPop = new Array();
        tempFea = new Array();
        var control = measureControls['polygon'];
        control.deactivate();
    }
    $('.tooltip').hide();
}

/**
 * 거리, 면적 측정 초기화 함수
 */
function initMeasure() {
	//거리, 면적 측정 기능 추가
    measurePoint_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);
    measureLine_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);
    measurePolygon_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);
    measurePolygon_Point_style = OpenLayers.Util.extend({}, OpenLayers.Feature.Vector.style['default']);

    measurePoint_style.pointRadius = 6;
    measurePoint_style.graphicName = "circle";
    measurePoint_style.fillColor = "#ffffff";
    measurePoint_style.fillOpacity = 0.5;
    measurePoint_style.strokeWidth = 2;
    measurePoint_style.strokeOpacity = 0.6;
    measurePoint_style.strokeColor = "#E41536";
    
    measurePolygon_Point_style.pointRadius = 6;
    measurePolygon_Point_style.graphicName = "circle";
    measurePolygon_Point_style.fillColor = "#ffffff";
    measurePolygon_Point_style.fillOpacity = 0.5;
    measurePolygon_Point_style.strokeWidth = 2;
    measurePolygon_Point_style.strokeOpacity = 0.6;
    measurePolygon_Point_style.strokeColor = "#214DD8";
    
    
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
    
    // style the sketch fancy   //triangle
    sketchSymbolizers = {
        "Point": {
            pointRadius: 6,
            graphicName: "circle",
            fillColor: "#ffffff",
            fillOpacity: 0.5,
            strokeWidth: 2,
            strokeOpacity: 0.6,
            strokeColor: "#E41536"
        },
        "Line": {
            strokeWidth: 2,
            strokeOpacity: 0.6,
            strokeColor: "#E41536",
            strokeDashstyle: "solid"
        },
        "Polygon": {
            strokeWidth: 2,
            strokeOpacity: 0.6,
            strokeColor: "#214DD8",
            strokeDashstyle: 'solid',
            fillColor: "#214DD8",
            fillOpacity: 0.2
        }
    };

    var style = new OpenLayers.Style();
    style.addRules([
        new OpenLayers.Rule({symbolizer: sketchSymbolizers})
    ]);
    var styleMap = new OpenLayers.StyleMap({"default": style});

    // allow testing of specific renderers via "?renderer=Canvas", etc
    var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
    
    measureControls = {
        point: new OpenLayers.Control.Measure(
            OpenLayers.Handler.Point, {
                persist: true,
                handlerOptions: {
                    layerOptions: {
                        renderers: renderer,
                        styleMap: styleMap
                    }
                }                       
            }
        ),
        point2: new OpenLayers.Control.Measure(
                OpenLayers.Handler.Point, {
                    persist: true,
                    handlerOptions: {
                        layerOptions: {
                            renderers: renderer,
                            styleMap: styleMap
                        }
                    }                       
                }
            ),
        line: new OpenLayers.Control.Measure(
            OpenLayers.Handler.Path, {
                persist: true,
//                immediate: true,
                handlerOptions: {
                    layerOptions: {
                        renderers: renderer,
                        styleMap: styleMap
                    }
                }
            }
        ),
        polygon: new OpenLayers.Control.Measure(
            OpenLayers.Handler.Polygon, {
                persist: true,
//                immediate: true,
                handlerOptions: {
                    layerOptions: {
                        renderers: renderer,
                        styleMap: styleMap
                    }
                }
            }
        )
    };
    
    vectorLayer = new OpenLayers.Layer.Vector("vectorLayer");
    map.addLayer(vectorLayer);
    
    var control;
    for(var key in measureControls) {
        control = measureControls[key];
        if(key=='point2') {
        	control.events.on({
               // "measure": complateEx,
                //"measurepartial": handleMeasurements
            });
        } else {
        	control.events.on({
                "measure": complateEx,
                "measurepartial": handleMeasurements
            });	
        }
        
        
        map.addControl(control);
    }
}

$(function(){
	initMeasure();	//거리,면적 초기화
	$('#mapContent').append('<div class="tooltip"></div>');
	
	map.events.register("mousemove", map, function (e) {
	    var position = map.getLayerPxFromViewPortPx(e.xy);
	    if( $('.tooltip').css('display') == 'block' ) {
	    	$('.tooltip').css('left', position.x+15);
		    $('.tooltip').css('top', position.y+15);
	    }
	    //OpenLayers.Util.getElement("#status").innerHTML = "<label>Latitude: " + position.lat + "</label><br/><label>Longitude: " + position.lon + "</label>";
	});
	
});