layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
	$('.login_btn').click(function (){
		var params={};
		params.account=$('#account').val();
		params.password=$('#password').val();
		params.code=$('#code').val();
    	callAjax("user/login",params, function(data) {
		 if(!(data.code == 200 && data.data)) {
			    $('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
				return;
			}
			window.location.href = "../../index.html";
		});		
		
	});
	
	$('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
	
	$('.code img').click(function(){
		$('.code img').attr('src',globleURL+"/common/code?t="+Date.parse(new Date()));
	});
	
});
