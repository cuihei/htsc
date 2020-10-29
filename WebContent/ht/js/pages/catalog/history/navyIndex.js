$(function(){
	detail.init();
})

var detail = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("detail");
		loading.init();
		try{
			detail.createUserGrid();
			detail.requestUserData();
			detail.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createUserColumns : function(){
		grid.resetColumn();
		grid.addColumn("80px","mapNo","图号");
		grid.addColumn("80px","mapName","图名");
		grid.addColumn("100px","scale","比例尺 1:");
		grid.addColumn("170px","starLatitude","图幅范围（纬度）");
		grid.addColumn("170px","starLongitude","图幅范围（经度）");
		//grid.addColumn("80px","nature","性质");
		//grid.addColumn("100px","measurementPeriod","测量周期");
		//grid.addColumn("100px","publicationYear","出版年份");
		//grid.addColumn("100px","datumLatitude","基准纬度");
		//grid.addColumn("80px","mapProportion","图积");
		//grid.addColumn("100px","adjustmentProperty","调整性质");
		grid.addColumn("150px","publicationDate","出版日期","#= publicationDate ? kendo.toString(new Date(publicationDate), 'yyyy-MM-dd') : '' #" );
		grid.addColumn("80px","revision","版次");
		return grid.addColumn("150px","remarks","备注",null,null,null,false);
	},
	
	/**
	 * 创建资源列表
	 */
	createUserGrid : function(){
		var columns = detail.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../hcd/list?type=2","POST",detail.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		if (result.code == 0) {
			loading.close();
			layer.msg(result.value);
			return;
		}
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
					var starLongArray = starLong.split("°");
					var starResult = Number(starLongArray[0])-180;
					if(Number(starResult)>0){
						if(starLongArray.length == 1){
							starLong = starResult+"°"+ "E";
						}else{
							starLong = starResult+"°"+starLongArray[1] + "E";
						}
					}else{
						if(starLongArray.length == 1){
							starLong = Math.abs(starResult)+"°"+ "W";
						}else{
							starLong = Math.abs(starResult)+"°"+starLongArray[1] + "W";
						}
						
					}
					var endLongArray = endLong.split("°");
					var endResult = Number(endLongArray[0])-180;
					if(Number(endResult)>0){
						if(endLongArray.length == 1){
							endLong = endResult+"°"+ "E";
						}else{
							endLong = endResult+"°"+endLongArray[1] + "E";
						}
					}else{
						if(endLongArray.length == 1){
							endLong = Math.abs(endResult)+"°"+ "W";
						}else{
							endLong = Math.abs(endResult)+"°"+endLongArray[1] + "W";
						}
					}
					var starLongitude = starLong+"-"+endLong;
					data[i]["starLongitude"] = starLongitude;
				}
				//拼接纬度
				var starLati = data[i].starLatitude;
				var endLati = data[i].endLatitude;
				if(starLati!=null && endLati!=null){
					var starLatiArray = starLati.split("°");
					var starLatiResult = Number(starLatiArray[0])-90;
					if(Number(starLatiResult)>0){
						if(starLatiArray.length == 1){
							starLati = starLatiResult+"°"+ "N";
						}else{
							starLati = starLatiResult+"°"+starLatiArray[1] + "N";
						}
					}else{
						if(starLatiArray.length == 1){
							starLati = Math.abs(starLatiResult)+"°"+ "S";
						}else{
							starLati = Math.abs(starLatiResult)+"°"+starLatiArray[1] + "S";
						}
					}
					var endLatiArray = endLati.split("°");
					var endLatiResult = Number(endLatiArray[0])-90;
					if(Number(endLatiResult)>0){
						if(endLatiArray.length == 1){
							endLati = endLatiResult+"°"+ "N";
						}else{
							endLati = endLatiResult+"°"+endLatiArray[1] + "N";
						}
					}else{
						if(endLatiArray.length == 1){
							endLati = Math.abs(endLatiResult)+"°"+ "S";
						}else{
							endLati = Math.abs(endLatiResult)+"°"+endLatiArray[1] + "S";
						}
						
					}
					var starLatitude = starLati+"-"+endLati;
					data[i]["starLatitude"] = starLatitude;
				}
		  }
		}
		grid.bindData(result);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定导出模板类型列表窗口按钮的click事件*/
		$("#btn").click(function(){
			common.toPage("../hcd/export?type=2");
		});
	}
}