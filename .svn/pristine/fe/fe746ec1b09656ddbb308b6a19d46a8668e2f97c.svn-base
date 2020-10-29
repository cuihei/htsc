$(function(){
	completion.init();
})

var completion = {
	
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("year_plan");
		loading.init();
		try{
			completion.createCompletionGrid();
			completion.requestCompletionData();
			completion.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建编绘计划列表
	 */
	createCompletionGrid : function(){
		    $("#year_plan").kendoGrid({
		    	selectable: "multiple row",
		        height: 550,
		        resizable : true,
    			reorderable : true,
    			sortable : true,
    			filterable : {
    				extra: false,
    				messages: {
    					 filter: "过滤",//过滤按钮
    					 clear: "清空"//清空按钮
    				},
    				operators:{
    					string:{
    						contains:"包含",
    						eq: "等于"
    					}
    				}
    			},
                pageable : {
    				pageSizes : true,
    				buttonCount : 5,
    				pageSize : 50
    			},
		        columns: [{
					 title: "基本信息",
					 headerAttributes: {
					      "class": "table-header-cell",
					      style: "text-align: center"
					    },
					    
			            columns:[
			                 {field: "",title:"<input type='checkbox' onclick='common.bind_select_all_row(this)' >",width:"40px",template:$("#tmplt_name_checkbox").html()},
			            	 {field: "mapNo",title: "图号",width:"80px"},
			            	 {field: "mapName",title: "图名",width:"160px"},
			            	 {field: "scale",title: "比例尺 1:",width:"140px"},
			            	 {field: "type",title: "本年度测量性质",width:"170px"},
			            	 {field: "frederickCycle",title: "测量周期(基)",width:"140px",hidden:true},
			            	 {field: "testingCycle",title: "测量周期(检)",width:"140px",hidden:true},
			            	 {field: "lastTimeProperty",title: "上次测量/编绘性质",width:"190px",hidden:true},
			            	 {field: "lastTimeDate",title: "上次测量年份",width:"150px",hidden:true}
			            	]
		        		},{
		        	title: "任务信息及要求",
		        	 headerAttributes: {
					      "class": "table-header-cell",
					      style: "text-align: center"
					    },
		        	 columns: [
		        	   {field: "planType",title: "任务类别",width:"150px"},
		               {field: "planExchangeTime",title: "计划汇交时间",width:"150px",template:"#= planExchangeTime ? kendo.toString(new Date(planExchangeTime), 'yyyy-MM-dd') : '' #"},
		               {field: "actualExchangeTime",title: "实际汇交时间",width:"150px",template:"#= actualExchangeTime ? kendo.toString(new Date(actualExchangeTime), 'yyyy-MM-dd') : '' #"},
		               {field: "taskReleaseTime",title: "计划编绘时间",width:"150px",template:"#= taskReleaseTime ? kendo.toString(new Date(taskReleaseTime), 'yyyy-MM-dd') : '' #"},
		               {field: "planCompleteTime",title: "计划完成时间",width:"150px",template:"#= planCompleteTime ? kendo.toString(new Date(planCompleteTime), 'yyyy-MM-dd') : '' #"},
		              // {field: "actualExchangeTime",title: "资料提取时间",width:"150px",template:"#= actualExchangeTime ? kendo.toString(new Date(actualExchangeTime), 'yyyy-MM-dd') : '' #"},
		               {field: "compilationProperty",title: "编绘性质",width:"120px"},
		               {field: "compilationContent",title: "编绘内容",width:"120px",filterable: {multi: true,checkAll: false}},
		               {field: "adjustmentCoefficient",title: "调整系数",width:"120px",hidden:true}
		               ]
		        		},{
		    	    title: "编 绘 作 业",
		    	    headerAttributes: {
					      "class": "table-header-cell",
					      style: "text-align: center"
					    },
		    	    columns: [
		    	       {field: "compilationWorkDays",title: "工天",width:"100px",hidden:true},
		               {field: "compilationClerk",title: "编绘员",width:"150px",hidden:true},
		               {field: "compilationStartTime",title: "开始时间",width:"150px",template:"#= compilationStartTime ? kendo.toString(new Date(compilationStartTime), 'yyyy-MM-dd') : '' #"},
		               {field: "compilationEndTime",title: "完成时间",width:"150px",template:"#= compilationEndTime ? kendo.toString(new Date(compilationEndTime), 'yyyy-MM-dd') : '' #"}
		               ]
		        		},{
		    	    title: "编绘质检作业",
		    	    headerAttributes: {
					      "class": "table-header-cell",
					      style: "text-align: center"
					    },
		    	    columns: [
		    	       {field: "qualityWorkDays",title: "工天",width:"100px",hidden:true},
		               {field: "inspector",title: "质检员",width:"150px",hidden:true},
		               {field: "qualityStartTime",title: "开始时间",width:"150px",template:"#= qualityStartTime ? kendo.toString(new Date(qualityStartTime), 'yyyy-MM-dd') : '' #"},
		               {field: "qualityEndTime",title: "完成时间",width:"150px",template:"#= qualityEndTime ? kendo.toString(new Date(qualityEndTime), 'yyyy-MM-dd') : '' #"},
		               {field: "qualityScore",title: "得分",width:"100px",hidden:true}
		               ]
		        		},{
		        	title: "审定作业",
		        	 headerAttributes: {
					      "class": "table-header-cell",
					      style: "text-align: center"
					    },
		        	columns: [
		        	   {field: "authorizedOfficer",title: "审定员",width:"150px",hidden:true},
		               {field: "authorizedStartTime",title: "开始日期",width:"150px",template:"#= authorizedStartTime ? kendo.toString(new Date(authorizedStartTime), 'yyyy-MM-dd') : '' #"},
		               {field: "authorizedEndTime",title: "完成日期",width:"150px",template:"#= authorizedEndTime ? kendo.toString(new Date(authorizedEndTime), 'yyyy-MM-dd') : '' #"},
		               {field: "authorizedWorkDays",title: "工天",width:"100px",hidden:true},
		               {field: "qualityAchievement",title: "质量评分",width:"150px"}
		               ]
		        }],
		        columnMenu: true
		 });
	},
	
/*	testFilter :function(element) {
        element.kendoDropDownList({
            dataSource:  ["源数据编绘", "电子海图编绘", "纸海图编绘", "源数据小改正", "电子海图小改正", "纸海图小改正","改正通告编辑","改正通告模板编辑","工程&专题纸海图","工程&专题电子海图","其他航海图书"]
        });
    },
	*/
	/**
	 * 发送数据请求
	 */
	requestCompletionData : function(){
		common.init("../compilationYearPlan/list","POST",completion.bindCompletionGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindCompletionGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 绑定页面按钮点击事件
	 */
	bindPageEvent : function(){
		/** 绑定导出通知按钮的click事件*/
		$("#export").on("click",function(){
			window.location.href="../detaildownload/excelyearplan";
		});
	}

}