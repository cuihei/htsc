package com.ht.common.util;


/**
 * 匹配判断(日志使用)
 * @author houchen
 *
 */
public class MatchingUtil{
	/**
	 * 判断传入的url是不是action
	 * @param url 传入的路径
	 * @return true 是action  false 不是action
	 */
	public static boolean matchingAction(String url){
		String s = url.substring(url.lastIndexOf("/"));
		if(s.indexOf(".")>=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断传入的url是不是Visit
	 * @param url 传入的路径
	 * @return true 是Visit  false 不是Visit
	 */
	public static boolean matchingVisit(String url) {
		String s = url.substring(0, url.lastIndexOf('/'));
		String o = s.substring(s.lastIndexOf("/"));
		String i = url.substring(url.lastIndexOf("/"));
		if (i.indexOf("/init")>=0 && i.length()==5) {
			if (o.indexOf("/index")>=0 && o.length()==6) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	/**
	 * 判断传入的url是不是Behavior
	 * @param url 传入的路径
	 * @return true 是Behavior  false 不是Behavior
	 */
	public static boolean matchingBehavior(String url){
		String s = url.substring(url.lastIndexOf("/"));
		if(s.indexOf(".")>=0){
			return false;
		}else if(s.indexOf("add")>=0 && s.length()==4){
			return true;
		}else if(s.indexOf("edit")>=0 && s.length()==5){
			return true;
		}else if(s.indexOf("remove")>=0 && s.length()==7){
			return true;
		}else if(s.indexOf("export")>=0 && s.length()==7){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断用户访问行为,获取操作行为
	 * @return 操作行为
	 */
	public static String getBehavior(String url){
		String s = url.substring(url.lastIndexOf("/"));
		if(s.indexOf(".")>=0){
			return "访问了非action";
		}else if(s.indexOf("add")>=0 && s.length()==4){
			return "添加";
		}else if(s.indexOf("edit")>=0 && s.length()==5){
			return "编辑";
		}else if(s.indexOf("remove")>=0 && s.length()==7){
			return "删除";
		}else if(s.indexOf("export")>=0 && s.length()==7){
			return "导出";
		}else if(s.indexOf("split")>=0 && s.length()==6){
			return "拆分";
		}else{
			return "其他";
		}
	}
	
	/**
	 * 根据路径获取操作对象
	 * @param url 传入的路径
	 * @return 
	 */
	public static String getBehaviorObject(String url){
		String s = url.substring(0, url.lastIndexOf('/'));
		String o = s.substring(s.lastIndexOf("/"));
		if (o.indexOf("/user")>=0&&o.length()==5) {
			return "用户";
		}else if(o.indexOf("/basedata")>=0&&o.length()==9){
			return "基础数据";
		}else if(o.indexOf("/type")>=0&&o.length()==5){
			return "类别数据";
		}else if(o.indexOf("/org")>=0&&o.length()==4){
			return "组织机构";
		}else if(o.indexOf("/auth")>=0&&o.length()==5){
			return "权限";
		}else if(o.indexOf("/role")>=0&&o.length()==5){
			return "角色";
		}else if(o.indexOf("/syslog")>=0&&o.length()==7){
			return "系统访问日志";
		}else if(o.indexOf("/so")>=0&&o.length()==3){
			return "系统操作日志";
		}else if(o.indexOf("/notice")>=0&&o.length()==7){
			return "通知";
		}else if(o.indexOf("/une")>=0&&o.length()==4){
			return "人员通知关系";
		}else if(o.indexOf("/plan")>=0&&o.length()==5){
			return "计划";
		}else if(o.indexOf("/year")>=0&&o.length()==5){
			return "指令性任务";
		}else if(o.indexOf("/temp")>=0&&o.length()==5){
			return "临时性任务";
		}else if(o.indexOf("/taskbook")>=0&&o.length()==9){
			return "编绘任务书";
		}else if(o.indexOf("/app")>=0&&o.length()==4){
			return "应用资源";
		}else if(o.indexOf("/datumCategory")>=0&&o.length()==14){
			return "资料类别";
		}else if(o.indexOf("/datumFile")>=0&&o.length()==10){
			return "资料维护";
		}else if(o.indexOf("/month")>=0&&o.length()==6){
			return "月度任务";
		}else if(o.indexOf("/historyTaskBook")>=0&&o.length()==16){
			return "历史任务书";
		}else if(o.indexOf("/detail")>=0&&o.length()==7){
			return "目录明细";
		}else if(o.indexOf("/cta")>=0&&o.length()==4){
			return "目录类型";
		}else if(o.indexOf("/area")>=0&&o.length()==5){
			return "目录区域";
		}else if(o.indexOf("/catalogHistoryDetail")>=0&&o.length()==21){
			return "历史目录详情";
		}else if(o.indexOf("/model")>=0&&o.length()==6){
			return "模板";
		}else if(o.indexOf("/mim")>=0&&o.length()==4){
			return "模板项";
		}else if(o.indexOf("/mte")>=0&&o.length()==4){
			return "模板类型";
		}else if(o.indexOf("/cns")>=0&&o.length()==4){
			return "改正通告";
		}else if(o.indexOf("/hcd")>=0&&o.length()==4){
			return "历史明细";
		}else if(o.indexOf("/taskbill")>=0&&o.length()==9){
			return "编绘任务清单";
		}else if(o.indexOf("/unv")>=0&&o.length()==4){
			return "人员通知关系视图";
		}else if(o.indexOf("/dictionariesType")>=0&&o.length()==17){
			return "字典类型";
		}else if(o.indexOf("/dictionaries")>=0&&o.length()==13){
			return "字典";
		}else if(o.indexOf("/source")>=0&&o.length()==7){
			return "源数据编绘";
		}else if(o.indexOf("/paper")>=0&&o.length()==6){
			return "纸海图编绘";
		}else if(o.indexOf("/electron")>=0&&o.length()==9){
			return "电子海图编绘";
		}else if(o.indexOf("/correctionnotice")>=0&&o.length()==17){
			return "改正通告";
		}else if(o.indexOf("/statistic")>=0&&o.length()==10){
			return "统计分析";
		}else if(o.indexOf("/auditrole")>=0&&o.length()==10){
			return "审批角色";
		}else if(o.indexOf("/form")>=0&&o.length()==5){
			return "表单管理";
		}else if(o.indexOf("/formProp")>=0&&o.length()==9){
			return "表单属性管理";
		}else if(o.indexOf("/formValue")>=0&&o.length()==10){
			return "表单明细管理";
		}else if(o.indexOf("/tasksplit")>=0&&o.length()==10){
			return "编绘任务拆分";
		}else if(o.indexOf("/books")>=0&&o.length()==6){
			return "海图";
		}else if(o.indexOf("/bookinfo")>=0&&o.length()==9){
			return "图书资料";
		}else if(o.indexOf("/filedData")>=0&&o.length()==10){
			return "外业数据";
		}else if(o.indexOf("/defect")>=0&&o.length()==7){
			return "缺陷";
		}else if(o.indexOf("/defectitem")>=0&&o.length()==11){
			return "缺陷项目";
		}else if(o.indexOf("/coefficient")>=0&&o.length()==12){
			return "系数";
		}else if(o.indexOf("/scaleControl")>=0&&o.length()==13){
			return "比例尺(1:)";
		}else if(o.indexOf("/maps")>=0&&o.length()==5){
			return "图幅";
		}else if(o.indexOf("/workDays")>=0&&o.length()==9){
			return "工天";
		}else if(o.indexOf("/dynamic")>=0&&o.length()==8){
			return "港口航道图月度在编动态";
		}else if(o.indexOf("/submission")>=0&&o.length()==11){
			return "港口航道图月度汇交";
		}else if(o.indexOf("/compilationYearPlan")>=0&&o.length()==20){
			return "港口航道图年度编绘计划";
		}else if(o.indexOf("/yeartaskbook")>=0&&o.length()==20){
			return "年度任务书";
		}else{
			return o;
		}
	}
}
