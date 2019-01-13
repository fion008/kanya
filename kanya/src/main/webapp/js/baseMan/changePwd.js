var userId=null;
// 启用layer模块
layui.use([ 'laypage', 'form' ], function() {
	var laypage = layui.laypage;
	var form = layui.form;
	initPage();
	initEvent();

});

function initPage() {
    userId = getCookie('user');
	callAjax("user/byid", {id:userId},function(data){
		if(!(data.code == 200 && data.data)) {
			return;
		}
		 $('#userName').val(data.data.name);
	});
  

}
function initEvent() {
	//修改
	$('#tijiao').click(function (){
		var newPwd=$('#newPwd').val();
		var newPwd1=$('#newPwd1').val();
		var oldPwd=$('#oldPwd').val();
		if(!oldPwd){
			layer.msg("旧密码不能为空");
			return false;
		}
		if(!newPwd){
			layer.msg("新密码不能为空");
			return false;
		}
		if(newPwd!=newPwd1){
			layer.msg("两次密码不一致");
			return false;
		}
		callAjax("user/updatePwd", {id:userId,newPwd:newPwd,oldPwd:oldPwd}, function(data) {
			if(!(data.code == 200)) {
				layer.msg("修改失败");
				return false;
			}
			clearCookie('user');
			layer.msg("修改成功");
			window.top.location.href = "../../login.html";
			return true;
		});
		return false;
	});
}