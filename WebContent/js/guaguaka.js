$(function(){
	
    $('#chou').click(function(){
    	var phone_no = $("#phone_no").val();
    	if(phone_no == ''){
    		alert("请输入手机号");
    		return;
    	}
    	var regphone =/^([0-9]{11})?$/;
    	if(!regphone.test(phone_no)){
    		alert("请输入手机号");
    		return;
    	}
         $.ajax({
             type: "post",
             url: "./huodong/guaguaka/chou",
             dataType: "json",
             data: {phone_no:phone_no},
             success: function(data){
            	 if(data.isSuc){
            		 alert("恭喜您抽到"+data.value+"元");
            	 }else{
            		 alert("抽奖失败，请重试。");
            	 }
             }

         });
    });
    
});