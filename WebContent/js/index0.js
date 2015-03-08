$(function(){

   
	//转到登录页面
    $('#to_login').click(function(){
    	location.href=('./to_login');
    });
  //转到登录页面
    $('#to_sign').click(function(){
    	location.href=('./to_sign');
    });
	
    //获取统计信息
//    $('#viewmore').click(function(){
         $.ajax({
             type: "get",
             url: "./getpvuv",
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 $("#pv").html(data.pv);
            		 $("#uv").html(data.uv);
            	 }else{
            		 alert("您没有访问权限。");
            	 }
             }

         });
//    });
  
    //转到红包页面
    $('#huodong').click(function(){
    	location.href=('./huodong/guaguaka');
    });
    

    $('#movie1').click(function(){
         $.ajax({
             type: "get",
             url: "./movie1",
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 alert("资源地址："+data.movieurl);
            	 }else{
            		 alert("您没有访问权限,请登录。");
            	 }
             }

         });
    });
    
   
    $('#movie0').click(function(){
         $.ajax({
             type: "get",
             url: "./movie0",
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 alert("资源地址："+data.movieurl);
            	 }else{
            		 alert("您没有访问权限,请登录。");
            	 }
             }

         });
    });
    
    
  
    $('#more_movice').click(function(){
         $.ajax({
             type: "get",
             url: "./more_movie0",
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 window.location = "more_movice.html?agr="+data.agr;
            	 }else{
            		 alert("您没有访问权限,请登录。");
            	 }
             }

         });
    });
    
    
    //转到娱乐页面
    $('#to_app').click(function(){
    	location.href=('./to_app');
    });
    
    //转到吐槽页面
    $('#tucao').click(function(){
    	location.href=('./to_tucao');
    });
    
    //转到新闻页面
    $('#news').click(function(){
    	location.href=('./to_news');
    });
    
    //转到新闻页面
    $('#newshelp').click(function(){
    	location.href=('./to_newshelp');
    });
    
    //转到新闻页面
    $('#news1').click(function(){
    	location.href=('./to_news');
    });
    
    //转到新闻页面
    $('#newshelp1').click(function(){
    	location.href=('./to_newshelp');
    });
});