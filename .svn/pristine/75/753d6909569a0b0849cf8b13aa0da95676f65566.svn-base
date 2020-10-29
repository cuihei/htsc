$(function(){
	noticeCns.init();
});
function showItem(obj){
	var tr = $(obj).parent().parent();
	grid.init("notice");
	var rowData = grid.getSelectRowDataByRow(tr);
	common.init("../cns/getItem?id="+rowData.id,"POST",function(result){
		$("#title").text(rowData.itemNo+"."+rowData.title);
		$("#content").html(result.value.name);
		$("#mapNo").text(rowData.chartNo);
		$("#source").text(result.value.enName);
		// 显示编辑页面
		$("#editMoadl").modal("show");
	});
	common.do_submit();
}
function showItem2(obj){
	var tr = $(obj).parent().parent();
	grid.init("newestNotice");
	var rowData = grid.getSelectRowDataByRow(tr);
	common.init("../cns/getItem?id="+rowData.id,"POST",function(result){
		$("#title").text(rowData.itemNo+"."+rowData.title);
		$("#content").html(result.value.name);
		$("#mapNo").text(rowData.chartNo);
		$("#source").text(result.value.enName);
		// 显示编辑页面
		$("#editMoadl").modal("show");
	});
	common.do_submit();
}
var noticeCns ={
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("notice");
		loading.init();
		try{
			noticeCns.createnoticeCnsGrid();
			noticeCns.requestnoticeCnsData();
			noticeCns.bindPageEvent();
		}catch(err){
			loading.close();
		}
	},
	/**
	 * 构建改正通知公告表格。
	 */
	createnoticeCnsColumns : function(){
		grid.init("notice");
		grid.resetColumn();
		grid.addColumn("150px","issueNo","期号");
		grid.addColumn("150px","itemNo","项号","<a style='color:blue;cursor:pointer;' onclick='showItem(this)'> #= itemNo # </a>");
		grid.addColumn("150px","noticeType","通告类型");
		grid.addColumn("300px","title","描述");
		grid.addColumn("300px","chartNo","关联图号");
		return 	grid.addColumn("150px","createTime","创建时间");
	},
	/**
	 * 构建最新改正通知公告表格。
	 */
	createNewestNoticeCnsColumns : function(){
		grid.init("newestNotice");
		grid.resetColumn();
		grid.addColumn("150px","itemNo","项号","<a style='color:blue;cursor:pointer;' onclick='showItem2(this)'> #= itemNo # </a>");
		grid.addColumn("300px","title","描述");
		return grid.addColumn("300px","chartNo","关联图号");
	},
	/**
	 * 创建改正通知公告列表
	 */
	createnoticeCnsGrid : function(){
		grid.init("notice");
		var columns = noticeCns.createnoticeCnsColumns();
		grid.createGrid(columns);
		grid.init("newestNotice");
		var columns = noticeCns.createNewestNoticeCnsColumns();
		grid.createGrid(columns);
		
	},
	/**
	 * 发送数据请求
	 */
	requestnoticeCnsData : function(){
		grid.init("notice");
		common.init("../cns/notice_itemNo_list","POST",noticeCns.bindnoticeCnsGrid);
		common.do_submit();
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindnoticeCnsGrid : function(result){
		grid.init("notice");
		grid.bindData(result);
		//设置高度
		var grid1 = $("#notice").data("kendoGrid");
		var height = "400px";
		grid1.setOptions({
			height:height,
		});
		grid.bindTrDbClick(function(e){
			var IssueID=$("#notice").data("kendoGrid").dataItem($(e.target).parent()).issueID;
			var data={'IssueID' : IssueID};
			loading.init();
			try{
				common.init("../cns/newest_list","POST",noticeCns.bindNewestNoticeCnsGrid);
				common.do_submit(data);
			}catch(err){
				loading.close();
			}
		});
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindNewestNoticeCnsGrid : function(result){
		grid.init("newestNotice");
		grid.bindData(result);
		//设置高度
		var grid1 = $("#newestNotice").data("kendoGrid");
		var height = "400px";
		grid1.setOptions({
			height:height,
		});
	},
	/**
	 * 改正通告查询
	 */
	sendnoticeCnsSearch : function(){
		var itemNo= $("#itemNo").val();
		var key= $("#key").val();
		var ddlType=$("#ddlType").val();
		var startTime=$("#startTime").val();
		var ddlAct=$("#ddlAct").val();
		var data={
        		'itemNo' : itemNo,
        		'key' : key,
        		'ddlType' : ddlType,
        		'startTime' : startTime,
        		'ddlAct' : ddlAct
        	};
		loading.init();
		try{
			common.init("../cns/notice_itemNo_list","POST",noticeCns.bindnoticeCnsGrid);
			common.do_submit(data);
		}catch(err){
			loading.close();
		}
	
//		$.ajax({
//        	type:"post",
//        	url:"../cns/search",
//        	async:true,
//        	dataType: "json",
//        	data: {
//        		'ChartNo' : ChartNo,
//        		'key' : key,
//        		'ddlType' : ddlType,
//        		'startTime' : startTime,
//        		'endTime' : endTime,
//        		'ddlAct' : ddlAct
//        	},
//        	success: function(data){
//        		newestNoticeCns.bindnoticeCnsGrid();
//        		common.do_submit();
//			}
//        });
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/**
		 * 绑定查询事件
		 */
		$("#search").click(function(){
			noticeCns.sendnoticeCnsSearch();
		});
		
	}
	
	
}



/**
 *时间比较的方法，如果d1时间比d2时间大，则返回true   
 * @param d1
 * @param d2
 * @returns {Boolean}
 */
function compareDate(d1, d2) {  
    return Date.parse(d1.replace(/-/g, "/"))==Date.parse(d2.replace(/-/g, "/"))|| Date.parse(d1.replace(/-/g, "/"))>Date.parse(d2.replace(/-/g, "/"))  
}   