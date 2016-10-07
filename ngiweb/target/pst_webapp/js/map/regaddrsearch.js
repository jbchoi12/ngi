//주소 검색 결과.
function addrResultCallback(req) {
    context = $('#SerarchLi');
    context.empty();
    if( req.ERROR != null ) {
    	context.append(req.ERROR);
    } else if( req.result != null ) {
    	context.append(req.result);
    } else {
        $(req.LIST).each(function(index, data) {
        	
        	// $("<div></div>").attr('id','new').appendTo('body');   
        	
        	var textHtml = document.createElement('li');
        	//$(textHtml).attr('onclick', 'javascript:search_loca_poi(this);');
        	$(textHtml).append('<p class="title">' + data.nameFull+ '</p>');
        	$(textHtml).append('<p class="Oldaddress"><img src="images/common/search_de.png" alt="상세페이지" title="상세페이지"/> 지번) <a href="#" onclick="javascript:search_loca_poi(this, \'01\');">' + data.juso + '</a></p>');
        	$(textHtml).append('<p class="Newaddress"><img src="images/common/search_de.png" alt="상세페이지" title="상세페이지"/> 도로명) <a href="#" onclick="javascript:search_loca_poi(this, \'02\');">' + data.njuso + '</a></p>');
        	$(textHtml).append('<input type="hidden" name="nameFull" value="'+data.nameFull+ '('+data.nameDp+')">');
			$(textHtml).append('<input type="hidden" name="xpos" value="'+data.xpos+'">');
			$(textHtml).append('<input type="hidden" name="ypos" value="'+data.ypos+'">');
			$(textHtml).append('<input type="hidden" name="pnu" value="'+data.PNU+'">');
        	context.append(textHtml);
		});
        $('#show_search_page').empty();
        var page = req.paginationInfo;
        // 페이징
        
        var block_set = 5;	// 한페이지에 보여질 페이지
        
        block = Math.ceil (page.currentPageNo / block_set); // 현재블럭(올림함수) 
        first_page = ((block - 1) * block_set) + 1; // 첫번째 페이지번호 
        last_page = Math.min(page.totalPageCount, block * block_set); // 마지막 페이지번호 
              
        $('#SerarchTt').html('총 <span>'+ page.totalRecordCount +'</span>건이 검색되었습니다.');
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
    }
    $('#SearchList').show();
    $('#show_search_page').show();
    
    window.resizeTo(310, $('#regAddrSearch_pop').height()+100);
    
}

//주소검색 결과 페이징
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

//주소검색 마커 생성하고 부모창 폼값에 데이터 입력.
function search_loca_poi(e, addrType){
	var name = $(e).text(); 	//$(e).find( "input:hidden[name='nameFull']" ).val();
	var xpos = $(e).parent().parent().find( "input:hidden[name='xpos']" ).val(); 	//$(e).find( "input:hidden[name='xpos']" ).val();
	var ypos = $(e).parent().parent().find( "input:hidden[name='ypos']" ).val(); 	//$(e).find( "input:hidden[name='ypos']" ).val();
	var pnu = $(e).parent().parent().find( "input:hidden[name='pnu']" ).val(); 	//$(e).find( "input:hidden[name='ypos']" ).val();
	pnu = pnu.replace(/(^\s*)|(\s*$)/, '');	// 공백제거.
	
	var oldAddr = $(e).parent().parent().find("p.Oldaddress > a ").text();
	var newAddr = $(e).parent().parent().find("p.Newaddress > a ").text();
	window.opener.parent.search_createMarker(name, xpos, ypos);
	window.opener.parent.iframeChgSttemntinfo.search_loca_setname(oldAddr, newAddr, xpos, ypos, pnu, addrType);
	window.self.close();
}

$().ready(function(){
	$("#search-input").keyup(function(event){
		if(event.keyCode == 13) {
			$("#search-go").click();
		}
	});
	
	// 주소검색
	$("#search-go").click(function(){
		
		var f = $("#search-input").val();
		var input = f.replace(/(^\s*)|(\s*$)/, '');

		if (input.length < 3 || f.length < 3) {
			alert("세자리 미만의 이름을 입력하셨습니다!");
			return;
		} 
		
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
});
