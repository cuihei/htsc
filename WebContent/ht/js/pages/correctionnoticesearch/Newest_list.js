$(function(){
	noticeCns.init();
})
var noticeCns ={
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("newestNotice");
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
		grid.resetColumn();
		grid.addColumn("5%","itemNo","项号");
		grid.addColumn("10%","title","描述");
		return grid.addColumn("10%","chartNo","关联图号");
		
	},
	/**
	 * 创建改正通知公告列表
	 */
	createnoticeCnsGrid : function(){
		var columns = noticeCns.createnoticeCnsColumns();
		grid.createGrid(columns);
	},
	/**
	 * 发送数据请求
	 */
	requestnoticeCnsData : function(){
		common.init("../cns/newest_list","POST",noticeCns.bindnoticeCnsGrid);
		common.do_submit();
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindnoticeCnsGrid : function(result){
		grid.bindData(result);
	},
	/**
	 * 改正通告查询
	 */
	sendnoticeCnsSearch : function(){
		var ChartNo= $("#ChartNo").val();
		var key= $("#key").val();
		var ddlType=$("#ddlType").val();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var ddlAct=$("#ddlAct").val();
		var data={
        		'ChartNo' : ChartNo,
        		'key' : key,
        		'ddlType' : ddlType,
        		'startTime' : startTime,
        		'endTime' : endTime,
        		'ddlAct' : ddlAct
        	};
		loading.init();
		try{
			common.init("../cns/newest_list","POST",newestNoticeCns.bindnoticeCnsGrid);
			common.do_submit(data);//重新加载数据。
		}catch(err){
			loading.close();
		}
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