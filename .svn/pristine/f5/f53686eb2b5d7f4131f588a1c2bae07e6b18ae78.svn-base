$(function(){
	$("#year").datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		 format: 'yyyy',
		 autoclose: true
	}).on('changeDate',gotoDate);
	function gotoDate(){
		var issue=$("#issue").val();
		var issues=issue.split("年");
		var year=$("#year").val();
		var issueText=issues[1];
		if(issueText==null||issueText==''||issueText==undefined){
			issueText="第（）期";
		}
		$("#issue").val(year+"年"+issueText);
	};
	$("#back").on('click',function(){
		 common.toPage("../issue/index");
	});
	$("#submit").on('click',function(){
		var yearIssue = {};
		var year=$("#year").val();
		if(year != null && year != ""){
			yearIssue.year = $("#year").val();
		}else {
			layer.msg("请选择年份！");
			return false;
		}
		var issue = $("#issue").val();
		if(issue!=null){
			issue=issue.substring(issue.lastIndexOf("（")+1,issue.lastIndexOf("）")).trim();
			if(issue != null && issue != ""){
				yearIssue.issue = $("#issue").val();
			}else {
				layer.msg("请输入改正期号!");
				return false;
			}
		}else{
			layer.msg("请输入改正期号!");
			return false;
		}
		var beginDate=$("#beginDate").val();
		if(beginDate != null && beginDate != ""){
			yearIssue.beginDate =beginDate;
		}else {
			layer.msg("请输入开始时间!");
			return false;
		}
		var endDate=$("#endDate").val();
		if(endDate != null && endDate != ""){
			yearIssue.endDate = endDate;
		}else {
			layer.msg("请输入结束时间!");
			return false;
		}
		if(compareDate(beginDate,endDate)){
			layer.msg("结束日期不能早于开始时间");
			return;
		}
		var id=$("#id").val();
		if(id != null && id != ""){
			yearIssue.id = id;
		}
		var yearIssueJson = JSON.stringify(yearIssue);
		var param = common.add_param("yearIssue",yearIssueJson);
		common.init("../issue/add","POST",function(result){
			//调回用户列表显示页面
			if (result.code == 1) {
				if(result.value !="" && result.value != null){
					layer.msg(result.value);
					if(result.value=="添加成功"||result.value=="修改成功"){
						common.toPage("../issue/index");
					}
				}else{
					layer.msg("保存失败！");
				}
			}
			
		});
		common.do_submit(param);
	});
})
/**
 *时间比较的方法，如果d1时间比d2时间大，则返回true   
 * @param d1
 * @param d2
 * @returns {Boolean}
 */
function compareDate(d1, d2) {  
    return Date.parse(d1.replace(/-/g, "/"))==Date.parse(d2.replace(/-/g, "/"))|| Date.parse(d1.replace(/-/g, "/"))>Date.parse(d2.replace(/-/g, "/"))  
} 