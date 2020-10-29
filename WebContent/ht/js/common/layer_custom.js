var loading = {
		init : function(){
			// 显示加载
			var index = layer.load(2, {
				  //0.1透明度的白色背景
				  shade: [0.1,'#fff'] 
			});
		},
		
		close : function(){
			layer.closeAll("loading");
		}
}