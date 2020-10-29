var flowLayout = {
		init:function(){
			var xmlPath = $("#xml").val();
			var check = graph.checkBrowser();
			if (!check) {
				return;
			}
			graph.createConnectImage();
			var graphIns = graph.createGraph("main");
			graph.getGraphFromXml("../"+xmlPath,graphIns);
			
			$("#submit").on("click",function(){
				var xml = graph.getXml(graphIns);
				parent.$("#submit").val(xml);
				parent.$("#submit").click();
			})
		}
}