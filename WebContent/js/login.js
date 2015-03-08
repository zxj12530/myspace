$(function(){
  
  //登录账号
    $('#login').click(function(){
    	var login_email = $("#login_email").val();
    	var login_password = $("#login_password").val();
    	var regmail =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    	if(!regmail.test(login_email)){
    		alert("邮箱格式不正确");
    		return;
    	}
    	var regpwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,9}$/;
    	if(!regpwd.test(login_password)){
    		alert("密码格式不正确");
    		return;
    	}
    	
         $.ajax({
             type: "post",
             url: "./login",
             data: {login_email:login_email,login_password:login_password},
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 location.href=('./');
            	 }else{
            		 if(data.key){
            			 alert("连续多次登录失败，账号被锁定，请明天再次登录");
            		 }else{
            			 alert("用户不存在或者密码错误");
            		 }
            	 }
             }

         });
    });
});