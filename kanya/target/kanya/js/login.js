layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;

	
	//登录按钮事件
	$('.login_btn').click(function (){
		var params={};
		params.account=$('#account').val();
		params.password=$('#password').val();
		params.code=$('#code').val();
		callAjaxNoCookie("user/login",params, function(data) {
		 if(data.code != 200 ) {
		    $('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
		    window.location.href = "login.html";
			return false;
		 }
		    setCookie("user",data.data.id);
			window.location.href = "index.html";
			return true;
		});		
		return false;
	});
	
	$('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
	
	$('.code img').click(function(){
		$('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
	});
	
});
