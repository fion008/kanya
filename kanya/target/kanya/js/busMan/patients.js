var type=getQueryString("type");
var app_user_id=getQueryString("app_user_id");
var param = {};
var laypage;
var form;
var layer;
// 启用layer模块
layui.use(['layer','laypage', 'form'], function(){
	laypage = layui.laypage;
	form = layui.form;
	layer = layui.layer;
	initPage();
	initEvent();
	
});

function initPage() {
	// 页面初始化时即重置查询条件，所有条件为空
	initTable();


}
function initEvent() {
	$('#searchBtn').click(function () {
		initTable();
	});
}



function initTable() {
	// 根据用户设置的查询条件获取数据，生成分页并跳转至第一页
	var url = 'patients/pagelist';
	var paramForm = document.getElementById('paramFrom');
	param = formToJson(paramForm);
	param.pageNum=1;
	param.numPerPage=10;
	if(type == 0){
		param.doctorId=app_user_id;
	}else if(type == 1){
		param.app_user_id=app_user_id;
	}
	callAjax(url, param,function(data){
		if(!(data.code == 200 && data.dataList)) {
			return;
		}
		initTableBack(data);
	});
}

// 获取信息后初始化分页并装载数据
function initTableBack(data) {
	// 重新获取laypage实例
	laypage.render({
		elem : 'my-table',
		limit : 10,
		count : data.totalCount,
	
		// 首次加载不执行jumpTo，避免重复ajax浪费性能
		jump : function (obj, first) {
			if(!first){
				jumpTo(obj.curr);
			}
		},
		layout : ['prev', 'page', 'next', 'count']
	});
	printTable(data.dataList);
}
// 跳页不涉及查询对象属性的改变
function jumpTo(page) {
	$("#data-table").empty();
	param.pageNum=page;
	var url = 'patients/pagelist';
	callAjax(url, param, function (data) {printTable(data.dataList);});
}

function printTable(data) {
	var target = document.getElementById('data-table');
	var html = '';
	for (var x = 0; x < data.length; x++) {
	    html += '<tr>';
	    html += 	'<td>' + (x+1) + '</td	>';
	    html += 	'<td>' + isnull(data[x].name) + '</td>';
	    html += 	'<td>' + isnull(data[x].age) + '</td>';
/*	    html += 	'<td>' + isnull(data[x].avatar) + '</td>';
*/	    html += '</tr>';
	}
	$('#data-table').empty();
	$('#data-table').append(html);
}




