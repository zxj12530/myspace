$(function(){
  
	$(".main .year .list").each(function (e, target) {
	    var $target=  $(target),
	        $ul = $target.find("ul");
	    $target.height($ul.outerHeight()), $ul.css("position", "absolute");
	}); 
	$(".main .year>h2>a").click(function (e) {
	    e.preventDefault();
	    $(this).parents(".year").toggleClass("close");
	});
	
	$.getUrlParam = function(string) {
	    var obj = new Object();
	    if (string.indexOf("?") != -1) {
	      var string = string.substr(string.indexOf("?") + 1);
	      var strs = string.split("&");
	      for (var i = 0; i < strs.length; i++) {
	        var tempArr = strs[i].split("=");
	        obj[tempArr[0]] = tempArr[1];
	      }
	    }
	    return obj;
	  };
	
	var data = $.getUrlParam(window.location.href);
	var htm = "<div class='year'><h2><a href='#'>2015年<i></i></a></h2><div class='list'> <ul>";
	 $.ajax({
         type: "get",
         url: "./"+data.agr,
         dataType: "json",
         success: function(data){
        	 if(data.isSuc){
        		for(var i = 0;i < data.agr.length;i++){
        			htm += "<li class='cls'><p class='date'>"+data.agr[i].movice_time+"</p><p class='intro'>"+data.agr[i].movice_title+"</p><p class='version'>&nbsp;</p><div class='more'> <p><a href='"+data.agr[i].movice_url+"'>"+data.agr[i].movice_message+"</a></p></div> </li>";
        		}
        		 htm +="</ul></div></div>";
        		$("#movice").html(htm);
        	 }else{
        		 alert("您没有访问权限,请登录。");
        	 }
         }
     });
});