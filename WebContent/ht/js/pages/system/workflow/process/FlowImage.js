var flowImage = {
		init:function(){
//			var xmlPath = $("#xml").val();
//			var check = graph.checkBrowser();
//			if (!check) {
//				return;
//			}
//			graph.createConnectImage();
//			var graphIns = graph.createGraph("main");
//			graph.getGraphFromXml(".."+xmlPath,graphIns);
//			
//			$("#submit").on("click",function(){
//				var xml = graph.getXml(graphIns);
//				common.init("../workflow/init_layout","POST",function(result){
//					
//				});
//			})
			$("#back").one("click",function(){
				history.back();
			})
		}
}