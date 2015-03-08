$(function(){
		$("#login_person").html("对话窗口");
		var htm = "<ul>";
         $.ajax({
             type: "get",
             url: "./tucao",
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
             		for(var i = 0;i < data.agr.length;i++){
             			htm +="<li>";
             			if(data.agr[i].is_Online){
             				htm +="<label class='online'></label> ";
             			}else{
             				htm +="<label class='offline'></label> ";
             			}
             			htm +="<a href='javascript:;'><img src='img/head/2016.png'></a><a href='javascript:;'class='chat03_name'>"+data.agr[i].log_email+"</a></li>";
             		}
             		htm +="</ul>";
             		$("#all_users").html(htm);
             	 }else{
             		 alert("您没有访问权限,请登录。");
             	 }
             }
         });
});