$(function(){

    //注册账号
    $('#sign_up').click(function(){
    	var sign_email = $("#sign_email").val();
    	var sign_pssword1 = $("#sign_pssword1").val();
    	var sign_pssword2 = $("#sign_pssword2").val();
    	var secret_no = $("#secret_no").val();
    	
    	var regmail =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    	if(!regmail.test(sign_email)){
    		alert("====邮箱格式不正确====");
    		return;
    	}
    	var regpwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,9}$/;
    	if(!regpwd.test(sign_pssword1) || !regpwd.test(sign_pssword2)){
    		alert("====密码格式不正确====");
    		return;
    	}
    	if(sign_pssword1 != sign_pssword2){
    		alert("====两次输入的密码不一致====");
    		return;
    	}
    	var regsecret = /^\d{6}$/;
    	if(!regsecret.test(secret_no)){
    		alert("====安全密码格式不正确====");
    		return;
    	}
    	
         $.ajax({
             type: "post",
             url: "./sign",
             data: {sign_email:sign_email,sign_pssword1:sign_pssword1,sign_pssword2:sign_pssword2,secret_no:secret_no},
             dataType: "json",
             success: function(data){
            	 if(data.isSuc){
            		 alert("====注册成功，请返回登录页面登录====");
            		 location.href=('./');
            	 }else{
            		 if(data.key){
            			 alert("====达到每日注册用户上限====");
            		 }else{
            			 alert("====用户已存在====");
            		 }
            	 }
             }

         });
    });
});