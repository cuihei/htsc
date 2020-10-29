$(function(){
	catalog_detail.init();
})

/** 绑定编辑目录窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	catalog_detail.toDetailEditPage(tr);
}

/** 绑定查看图片按钮的click事件*/
function viewPage(obj) {
	var tr = $(obj).parent().parent();
	catalog_detail.toViewImg(tr);
}

var catalog_detail = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("detail");
		loading.init();
		try{
			catalog_detail.createDetailGrid();
			catalog_detail.requestDetialData();
			catalog_detail.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
		//初始化时间控件
		$("#year").datepicker({
			startView: 2, 
			maxViewMode: 2,
			minViewMode:2,
			 format: 'yyyy',
			 autoclose: true
		}).on('changeDate',gotoDate);
		function gotoDate(){
			var categoryId = $("#categoryId").val();
			var year = $("#year").val();
			loading.init();
			common.init("../detail/list?categoryId="+categoryId+"&year="+year,"POST",catalog_detail.bindDetailGrid);
    		common.do_submit();
		}
	},
	
	/**
	 * 创建目录列表
	 */
	createDetailGrid : function(){
		var columns = catalog_detail.createDetailColumns();
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
		gridInst.reorderColumn(0, gridInst.columns[2]);
		gridInst.reorderColumn(1, gridInst.columns[3]);
	},
	
	/**
	 * 构建目录列表列集合
	 */
	createDetailColumns : function(){
		grid.resetColumn();
		grid.addColumn("170px","area.areaName","海区");
		grid.addLockedColumn("100px","mapNo","图号");
		grid.addLockedColumn("100px","mapName","图名");
		grid.addColumn("100px","scale","比例尺 1:");
		grid.addColumn("150px","starLatitude","图幅范围（纬度）");
		grid.addColumn("150px","starLongitude","图幅范围（经度）");
		grid.addColumn("150px","publicationDate","出版日期","#= publicationDate ? kendo.toString(new Date(publicationDate), 'yyyy-MM') : '' #" );
		grid.addColumn("100px","printNum","印次");
		grid.addColumn("150px","remarks","备注");
		grid.addColumn("150px","status","状态");
		grid.addColumn("100px","handle","区域图片",kendo.template($("#viewTemplate").html()))
		return grid.addColumn("150px","handle","操作",kendo.template($("#editTemplate").html()));
	},
		
	/**
	 * 发送数据请求
	 */
	requestDetialData : function(){
		var categoryId = $("#categoryId").val();
		var year = $("#year").val();
		common.init("../detail/list?categoryId="+categoryId+"&year="+year,"POST",catalog_detail.bindDetailGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindDetailGrid : function(result){
		var data = result.value;
		if(data != null){
			for(var i=0;i<data.length;i++){
				//区域判断
				var area = data[i].area;
				if(area != null &&　area.areaName != null){
					data[i]["areaName"] = area.areaName;
				}
				//拼接经度
				var starLong = data[i].starLongitude;
				var endLong = data[i].endLongitude;
				if(starLong!=null && endLong!=null){
					var starLongitude = starLong+"-"+endLong;
					data[i]["starLongitude"] = starLongitude;
				}
				//拼接纬度
				var starLati = data[i].starLatitude;
				var endLati = data[i].endLatitude;
				if(starLati!=null && endLati!=null){
					var starLatitude = starLati+"-"+endLati;
					data[i]["starLatitude"] = starLatitude;
				}
		  }
		}
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到添加页面
	 */
	toDetailAddPage : function(){
		var type = $("#type").val();
		var categoryId = $("#categoryId").val();
		common.toPage("../detail/init_edit?type="+type+"&categoryId="+categoryId);
	},
	
	/**
	 * 跳转到图片查看页面
	 */
	toViewImg : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取目录ID
		var id = rowData.id;
		//判断是否有图片
		if(rowData.area != null && rowData.area != ""){
			if(rowData.area.areaImg != null && rowData.area.areaImg != ""){
				var img = rowData.area.areaImg;
			}else{
				layer.msg("该区域还没有上传图片！");
				return;
			}
		}
		// 标识
		var type = $("#type").val();
		var categoryId = $("#categoryId").val();
		var year = $("#year").val();
		// 跳转到区域图片查看页面
		common.toPage("../detail/init_view?id="+id+"&type="+type+"&categoryId="+categoryId+"&year="+year);
	},
	
	/**
	 * 删除目录
	 */
	removeDetails : function(){
		//获取选中行数据对象
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var details = [];
			$.each(rowDatas,function(i,item){
				var id = $("#detail").data("kendoGrid").dataItem(item).id;
				var detail = {};
				detail.id = id;
				details.push(detail);
			});
			var res = [details[0]];
			for (var i = 1; i < details.length; i++) {
                var repeat = false;
                for (var j = 0; j < res.length; j++) {
                    if (details[i].id== res[j].id) {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                	res.push(details[i]);
                }
            }
			var paramJson = JSON.stringify(res);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("ids",paramJson);
			common.init("../detail/remove","POST",catalog_detail.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("detail");
		layer.close(1);
		if(result.code != 1){
			layer.msg("删除失败！");
			return;
		}
		layer.msg('删除成功');
		catalog_detail.requestDetialData();
	},
	
	/**
	 * 跳转到目录编辑页面
	 */
	toDetailEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取目录ID
		var id = rowData.id;
		// 标识
		var type = $("#type").val();
		// 目录id
		var categoryId = $("#categoryId").val();
		// 跳转到目录编辑页面
		common.toPage("../detail/init_edit?id="+id+"&type="+type+"&categoryId="+categoryId);
	},
	
	/**
	 * 上传文件
	 */
	importExcel : function(){
		// 判空
		var data = $('#uploadFile').val();
		if(data==""){
			layer.msg('请选择文件！');
			return;
		}
		var fileType=data.substr(data.lastIndexOf(".")).toLowerCase();
	    if(fileType != '.xls'&&fileType != '.xlsx'){
	    	layer.msg('请导入xls或xlsx格式的文件');
			return;
	    }
		loading.init();
		$("#importForm").ajaxSubmit({  
            type: 'post',  
            url: "../detail/importdetail" ,  
            beforeSubmit: function() { 
            	return true;
            } ,  
            success: function(result){ 
            	result = eval("("+result+")");
                if(result.code != 1 ){
                	loading.close();
                	layer.msg("导入目录失败，请检查数据无误后再导入！");
                	return;
                }
                loading.close();
                layer.msg(result.value);
                catalog_detail.requestDetialData();
            },  
            error: function(XmlHttpRequest, textStatus, errorThrown){ 
            	loading.close();
            	layer.msg("系统错误，请联系管理员！");  
            	
            }  
        });  
	},
	
	exportSuccess : function(){
		layer.msg('导出成功');
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加目录窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			catalog_detail.toDetailAddPage();
		});
		
		/** 
		 * 绑定删除目录窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			catalog_detail.removeDetails();
		});
		
		/** 绑定刷新目录列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定上传文件的click事件*/
		$("#importSubmit").on("click",function(){
			catalog_detail.importExcel();
		});
		
		/** 绑定导出文件模板的click事件*/
		$("#exportTemplate").on("click",function(){
			$("#importForm").attr("action", "../detaildownload/template");
			$("#importForm").submit();
		});
		
		/** 绑定导出文件的click事件*/
		$("#export").on("click",function(){
			var categoryId = $("#categoryId").val();
			var type = $("type");
			var year = $("#year").val();
			window.location.href="../detaildownload/excel?type="+type+"&categoryId="+categoryId+"&year="+year;
		});
		
		/** 绑定搜索的click事件*/
		$("#search").on("click",function(){
			var catalog ={};
			//获取东经
			catalog.starLongitude = $("#starLongitude").val();
			if(catalog.starLongitude == null || catalog.starLongitude == ""){
				layer.msg("请填写起始经度！");
				return;
			}
			//获取西经
			catalog.endLongitude = $("#endLongitude").val();
			if(catalog.endLongitude == null || catalog.endLongitude == ""){
				layer.msg("请填写终止经度！");
				return;
			}
			//获取北纬
			catalog.starLatitude = $("#starLatitude").val();
			if(catalog.starLatitude == null || catalog.starLatitude == ""){
				layer.msg("请填写起始纬度！");
				return;
			}
			//获取南纬
			catalog.endLatitude = $("#endLatitude").val();
			if(catalog.endLatitude == null || catalog.endLatitude == ""){
				layer.msg("请填写终止纬度！");
				return;
			}
			loading.init();
			var paramJson = JSON.stringify(catalog);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("catalog",paramJson);
			var categoryId = $("#categoryId").val();
			var year = $("#year").val();
			common.init("../detail/list?categoryId="+categoryId+"&year="+year,"POST",catalog_detail.bindDetailGrid);
    		common.do_submit(param);
		});
		
		$("#searchMapNo").on("click",function(){
			var catalog ={};
			catalog.mapNo = $("#mapNo").val();
			if(catalog.mapNo == null || catalog.mapNo == ""){
				layer.msg("请填写图名！");
				return;
			}
			loading.init();
			var paramJson = JSON.stringify(catalog);
			// 添加参数 @param 参数key；参数value
			var categoryId = $("#categoryId").val();
			var year = $("#year").val();
			var param = common.add_param("catalog",paramJson);
			common.init("../detail/list?categoryId="+categoryId+"&year="+year,"POST",catalog_detail.bindDetailGrid);
    		common.do_submit(param);
		});

		$("#audit").on("click",function(){
			//获取选中行数据对象
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var details = [];
			$.each(rowDatas,function(i,item){
				var id = $("#detail").data("kendoGrid").dataItem(item).id;
				var status = $("#detail").data("kendoGrid").dataItem(item).status;
				var detail = {};
				detail.id = id;
				detail.status = status;
				details.push(detail);
			});
			var res = [details[0]];
			for (var i = 1; i < details.length; i++) {
                var repeat = false;
                for (var j = 0; j < res.length; j++) {
                    if (details[i].id== res[j].id) {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                	res.push(details[i]);
                }
            }
			for (var int = 0; int < res.length; int++) {
				if(res[int].status == '待审核'){
					layer.msg("只能提交状态为创建的数据,请重新选择！");
					return;
				}
			}
			layer.confirm('确认要提交这'+res.length+'条目录吗？',function(index){
				// 添加参数 @param 参数key；参数value
				var paramJson = JSON.stringify(res);
				var param = common.add_param("ids",paramJson);
				common.init("../workflow/publishcatalog","POST",function(result){
					if (result.code == 0) {
						layer.msg(result.value);
						return;
					}
					layer.msg("发布成功");
					catalog_detail.requestDetialData();
				});
				common.do_submit(param);
			});
		});
	}
}