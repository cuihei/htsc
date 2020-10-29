package com.ht.front.pages.background.workspace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.B;
import com.ht.front.model.Base;
import com.ht.front.model.Div;
import com.ht.front.model.H5;
import com.ht.front.model.H6;
import com.ht.front.model.P;
import com.ht.front.model.Small;
import com.ht.front.model.Span;
import com.ht.front.model.Table;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.service.impl.system.workflow.util.BusinessUtil;

public class WorkSpacePage
{
	
	/**
	 * 初始化个人工作台页面
	 * @param pusdList. 
	 * @param usernoticeviewList
	 * @param otherNoticeList
	 * @param size1
	 * @param ruTaskList
	 * @return 节点字符串
	 */
	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public String getListNode(List<TaskBookList> booklist,Map<String, ProcessTypeCount> ruTaskListMap,List<Map<String,Object>> taskCompletetion,int cnt, List<ProductUpdSourceData> pusdList)
	{

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "12", "12","12",null);

		// 创建我的待办panel
		Base panel = createPanelHeader("task", "我的待办");
		Map<String, ProcessTypeCount> list = new HashMap<String, ProcessTypeCount>();
		list.putAll(ruTaskListMap);
		int source_data_size = 0;
		int paper_size = 0;
		int electronic_size = 0;
		if (ruTaskListMap != null)
		{
			for (Map.Entry entry : ruTaskListMap.entrySet())
			{
				String processType = entry.getKey().toString();
				ProcessTypeCount ruTaskList = (ProcessTypeCount) entry.getValue();
				if (ruTaskList != null)
				{
						int size = ruTaskList.getCnt();
						if (processType.contains("sea_map_compilation_source_data") || processType.contains("SEA_MAP_COMPILATION_SOURCE_DATA"))
						{
							if (source_data_size != 0)
							{
								list.remove(processType);
							}
							processType = "SEA_MAP_COMPILATION_SOURCE_DATA";
							source_data_size += size;
						}
						if (processType.contains("sea_map_compilation_paper") || processType.contains("SEA_MAP_COMPILATION_PAPER"))
						{
							if (paper_size != 0)
							{
								list.remove(processType);
							}
							processType = "SEA_MAP_COMPILATION_PAPER";
							paper_size += size;
						}
						if (processType.contains("sea_map_compilation_electronic") || processType.contains("SEA_MAP_COMPILATION_ELECTRONIC"))
						{
							if (electronic_size != 0)
							{
								list.remove(processType);
							}
							processType = "SEA_MAP_COMPILATION_ELECTRONIC";
							electronic_size += size;
						}

				}
			}
			for (Map.Entry entry : list.entrySet())
			{
				String processType = entry.getKey().toString();
				ProcessTypeCount ruTaskList = (ProcessTypeCount) entry.getValue();
				if (ruTaskList != null)
				{
						int size = ruTaskList.getCnt();
						if (processType.contains("sea_map_compilation_source_data") || processType.contains("SEA_MAP_COMPILATION_SOURCE_DATA"))
						{
							processType = "SEA_MAP_COMPILATION_SOURCE_DATA";
							size = source_data_size;
						}
						if (processType.contains("sea_map_compilation_paper") || processType.contains("SEA_MAP_COMPILATION_PAPER"))
						{
							processType = "SEA_MAP_COMPILATION_PAPER";
							size = paper_size;
						}
						if (processType.contains("sea_map_compilation_electronic") || processType.contains("SEA_MAP_COMPILATION_ELECTRONIC"))
						{
							processType = "SEA_MAP_COMPILATION_ELECTRONIC";
							size = electronic_size;
						}
						String content = BusinessUtil.getInstance().getDecriptionNameByProcess(processType);
						if (processType.contains("task_book") || processType.contains("TASK_BOOK"))
						{
							processType = "TASK_BOOK";
						}
						String href = "../workflow/task_init?processDefKey="+processType;
						Base row = createPanelList(content, href, size);
						panel.addChildNode(row);
				}
			}
		}
		
		/** 产品修改源数据部分 */
		if(pusdList.size() > 0){
			String pusdContent = "产品修改源数据";
			String pusdHref = "../product/index";
			Base pusdRow = createPanelList(pusdContent, pusdHref, pusdList.size());
			panel.addChildNode(pusdRow);
		}
		
		column.addChildNode(panel);
		CssClass css = new CssClass("panel panel-default item");
		panel = Div.getInstance("systemNotice", css, null);
		column.addChildNode(panel);
		// 创建任务书panelHeader
		css = new CssClass("panel-heading panel-heading-style");
		Base header = Div.getInstance(null, css, null);
		Prop hprop = new Prop();
		hprop.setPropKey("style");
		hprop.setPropValue("padding-left: 0px;");
		header.addProp(hprop);
		panel.addChildNode(header);
		// 创建任务书H6
		// ------------------------------------
		css = new CssClass("panel-body bk-bg-very-light-gray text-left bk-padding-top-10 bk-padding-bottom-10");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("height:60px;");
		css = new CssClass("row");
		Div row = Div.getInstance(null, css, null);
		header.addChildNode(row);

		css = new CssClass("col-xs-8 text-left bk-vcenter");
		Div rowleft = Div.getInstance(null, css, null);
		row.addChildNode(rowleft);

		css = new CssClass("panel-heading");
		Div title = Div.getInstance(null, css, null);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("margin-top:11px;");
		title.addProp(prop);
		rowleft.addChildNode(title);

		css = new CssClass("h6-span");
		Span span1 = Span.getInstance(css, "任务书列表");
		title.addChildNode(span1);

		css = new CssClass("col-xs-4 bk-vcenter text-right");
		Div rowright = Div.getInstance(null, css, null);
		row.addChildNode(rowright);

		css = new CssClass("panel-headings");
		Div titles = Div.getInstance(null, css, null);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("text-align:right;");
		titles.addProp(prop);
		rowright.addChildNode(titles);

		A b = A.getInstance("../taskbook/index?write=0", "共" + booklist.size() + "条&nbsp;&nbsp;更多>");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("color:#3150DD;");
		b.addProp(prop);
		titles.addChildNode(b);
		// ------------------------------------
		// 如果任务书条数大于等于5条
		if (booklist != null && booklist.size() >= 5)
		{
			// 则获取前五条
			List<TaskBookList> sysList = booklist.subList(0, 5);
			for (int i = 0; i < sysList.size(); i++)
			{
				Base body = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(sysList.get(i).getIssueTime() != null){
					body = createPanelList(sysList.get(i).getTaskbookName()+"&#12288&#12288下发时间："+sdf.format(booklist.get(i).getIssueTime()), null, null);
				}else{
					body = createPanelList(sysList.get(i).getTaskbookName(), null, null);
				}
				panel.addChildNode(body);
			}
		}
		else
		{
			for (int i = 0; i < booklist.size(); i++)
			{
				Base body = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(booklist.get(i).getIssueTime() != null){
					body = createPanelList(booklist.get(i).getTaskbookName()+"&#12288&#12288下发时间："+sdf.format(booklist.get(i).getIssueTime()), null, null);
				}else{
					body = createPanelList(booklist.get(i).getTaskbookName(), null, null);
				}
				panel.addChildNode(body);
			}
		}
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH时");
		if (taskCompletetion != null)
		{
			for (int i = 0; i < taskCompletetion.size(); i++)
			{
				Map<String,Object> tcMap = taskCompletetion.get(i);
				Map<String,Object> map = new LinkedHashMap<String, Object>();
				map.put("mapName",tcMap.get("mapName"));
				map.put("mapNo",tcMap.get("mapNo"));
				map.put("scale",tcMap.get("scale"));
				map.put("status",tcMap.get("status"));
				map.put("compilationGroup",tcMap.get("compilationGroup"));
				map.put("compilationPerson",tcMap.get("compilationPerson"));
				map.put("compilationStartTime",tcMap.get("compilationStartTime")==null?null:format.format(tcMap.get("compilationStartTime")));
				map.put("compilationEndTime",tcMap.get("compilationEndTime")==null?null:format.format(tcMap.get("compilationEndTime")));
				map.put("qualityPerson",tcMap.get("qualityPerson"));
				map.put("qualityStartTime",tcMap.get("qualityStartTime")==null?null:format.format(tcMap.get("qualityStartTime")));
				map.put("qualityEndTime",tcMap.get("qualityEndTime")==null?null:format.format(tcMap.get("qualityEndTime")));
				map.put("qualityScore",tcMap.get("qualityScore"));
				map.put("apprvoePerson",tcMap.get("apprvoePerson"));
				map.put("apprvoeStartTime",tcMap.get("apprvoeStartTime")==null?null:format.format(tcMap.get("apprvoeStartTime")));
				map.put("apprvoeEndTime",tcMap.get("apprvoeEndTime")==null?null:format.format(tcMap.get("apprvoeEndTime")));
				map.put("score",tcMap.get("score"));
				mapList.add(map);
			} 
		}
		Base table = createTable(mapList,cnt,"海图编绘任务完成情况");
		root.addChildNode(table);
		return root.getNode();
	}
	
	/**
	 * 创建编绘任务
	 * @param taskBook
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unused")
	private Base createRuTaskBody(String processType, Map<String, Object> ruTaskMap, int i)
	{
		Base a = A.getInstance("", null);
		Prop prop = new Prop();
		prop.setPropKey("class");
		prop.setPropValue("panel-body bk-bg-very-light-gray bk-bg-lighten bk-padding-off-top bk-padding-off-bottom");
		a.addProp(prop);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("padding:15px;");
		a.addProp(prop);
		// div2
		CssClass css = new CssClass("");
		Base div2 = Div.getInstance(null, css, null);
		a.addChildNode(div2);
		String taskName = "";
		Object tn = ruTaskMap.get("taskName");
		if (tn != null)
		{
			taskName = tn.toString();
		}
		if ((i + 1) == 1 || (i + 1) % 3 == 1)
		{
			css = new CssClass("bk-fg-warning bk-fg-darken bk-margin-off-bottom");
			Base h5 = H5.getInstance(css);
			Base b = B.getInstance(null, taskName);
			h5.addChildNode(b);
		}
		else if ((i + 1) == 2 || (i + 1) % 3 == 2)
		{
			css = new CssClass("bk-fg-primary bk-fg-darken bk-margin-off-bottom");
			Base h5 = H5.getInstance(css);
			div2.addChildNode(h5);
			Base b = B.getInstance(null, taskName);
			h5.addChildNode(b);
		}
		else
		{
			css = new CssClass("bk-fg-info bk-fg-darken bk-margin-off-bottom");
			Base h5 = H5.getInstance(css);
			div2.addChildNode(h5);
			Base b = B.getInstance(null, taskName);
			h5.addChildNode(b);
		}

		Base p = P.getInstance(null);
		div2.addChildNode(p);

		String date = "无";
		if (ruTaskMap.get("createTime") != null)
		{
			Date createTime = (Date) ruTaskMap.get("createTime");
			date = (new SimpleDateFormat("yyyy-MM-dd")).format(createTime);
		}
		String mapName = "";
		if (ruTaskMap.get("mapName") != null)
		{
			mapName = ruTaskMap.get("mapName").toString();
		}
		String mapNo = "";
		if (ruTaskMap.get("mapNo") != null)
		{
			mapNo = ruTaskMap.get("mapNo").toString();
		}
		String scale = "";
		if (ruTaskMap.get("scale") != null)
		{
			scale = ruTaskMap.get("scale").toString();
		}
		String processDefName = "";
		if (ruTaskMap.get("processDefName") != null)
		{
			processDefName = ruTaskMap.get("processDefName").toString();
		}
		Base small = Small.getInstance(null, "<br> 流程名称：" + processDefName + "　　　发布时间：<" + date + ">　　　图名：" + mapName + "　　　图号：" + mapNo
				+ "　　　比例尺：  1:" + scale);
		p.addChildNode(small);
		return a;
	}

	/**
	 * 创建编绘任务
	 * @param taskBook
	 * @param i
	 * @return
	 */
	private Base createCompletionListBody(Map<String, Object> ruTaskMap, int i)
	{
		Base a = A.getInstance("", null);
		Prop prop = new Prop();
		prop.setPropKey("class");
		prop.setPropValue("panel-body bk-bg-very-light-gray bk-bg-lighten bk-padding-off-top bk-padding-off-bottom");
		a.addProp(prop);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("padding:15px");
		a.addProp(prop);
		// div2
		CssClass css = new CssClass("");
		Base div2 = Div.getInstance(null, css, null);
		a.addChildNode(div2);
		String taskName = "";
		Object tn = ruTaskMap.get("taskName");
		if (tn != null)
		{
			taskName = tn.toString();
		}

		String date = "无";
		if (ruTaskMap.get("createTime") != null)
		{
			Date createTime = (Date) ruTaskMap.get("createTime");
			date = (new SimpleDateFormat("yyyy-MM-dd")).format(createTime);
		}
		String mapName = "";
		if (ruTaskMap.get("mapName") != null)
		{
			mapName = ruTaskMap.get("mapName").toString();
		}
		String status = "";
		if (ruTaskMap.get("status") != null)
		{
			status = ruTaskMap.get("status").toString();
		}
		String group = "";
		if (ruTaskMap.get("group") != null)
		{
			group = ruTaskMap.get("group").toString();
		}
		String performer = "";
		if (ruTaskMap.get("performer") != null)
		{
			performer = ruTaskMap.get("performer").toString();
		}
		String processDefName = "";
		if (ruTaskMap.get("processDefName") != null)
		{
			processDefName = ruTaskMap.get("processDefName").toString();
		}
		css = new CssClass("bk-fg-primary bk-fg-darken bk-margin-off-bottom");
		Base h5 = H5.getInstance(css);
		div2.addChildNode(h5);
		css = new CssClass("h6-span-title");
		Base b = B.getInstance(css, processDefName + ":" + mapName);
		h5.addChildNode(b);

		Base p = P.getInstance(null);
		div2.addChildNode(p);
		css = new CssClass("h6-span-detail");
		if (StringUtils.isNotEmpty(performer))
		{
			Base small = Small.getInstance(css, "<br>当前任务：" + taskName + "<br>发布时间：<" + date + "><br> 状态：" + status + "<br>执行组：" + group + "<br>执行人:"
					+ performer);
			prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("line-height:20px");
			small.addProp(prop);
			p.addChildNode(small);
		}
		else
		{
			Base small = Small.getInstance(css, "<br>当前任务：" + taskName + "<br>发布时间：<" + date + "><br>状态：" + status + "<br>执行组：" + group);
			prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("line-height:20px");
			small.addProp(prop);
			p.addChildNode(small);
		}
		return a;
	}

	/**
	 * 创建panelBody
	 * @param content
	 * @return
	 */
	private Base createPanelBody(String bodyId)
	{
		CssClass css = new CssClass("panel-body panel-light");
		Base body = Div.getInstance(bodyId, css, null); 
		return body;
	}
	
	/**
	 * 创建表格
	 * @return
	 */
	private Base createTable(List<Map<String,Object>> mapList,int cnt,String name)
	{
		FrontUtil util = FrontUtil.getInstance();
		Base row = util.createRow();
		Base col = util.createColumn(row, "12", "12", "12", null);
		CssClass css = new CssClass("box border");
		Base boxBoder = Div.getInstance(null, css, null);
		col.addChildNode(boxBoder);
		css = new CssClass("box-title");
		Base boxTitle = Div.getInstance(null, css, null);
		Base h4 = H5.getH4(name);
		boxTitle.addChildNode(h4);
		
		A b = A.getInstance("../tabpage/init?key=map_completion2", "共" + cnt + "条&nbsp;&nbsp;更多>");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("color:#3150DD;float: right;");
		b.addProp(prop);
		boxTitle.addChildNode(b);
		
		boxBoder.addChildNode(boxTitle);
		css = new CssClass("box-body");
		Base boxBody = Div.getInstance(null, css, null);
		boxBoder.addChildNode(boxBody);
		Base table = Table.getDefault(null);
		boxBody.addChildNode(table);
		
		List<String> listThead = new ArrayList<String>();
		listThead.add("图名");
		listThead.add("图号");
		listThead.add("比例尺");
		listThead.add("状态");
		listThead.add("编绘组");
		listThead.add("编绘员");
		listThead.add("开始时间");
		listThead.add("结束时间");
//		listThead.add("编绘开始时间");
//		listThead.add("编绘结束时间");
		listThead.add("质检员");
//		listThead.add("质检开始时间");
//		listThead.add("质检结束时间");
		listThead.add("开始时间");
		listThead.add("结束时间");
		listThead.add("质检评分");
		listThead.add("审定员");
		listThead.add("开始时间");
		listThead.add("结束时间");
//		listThead.add("审定开始时间");
//		listThead.add("审定结束时间");
		listThead.add("审定评分");
		Table.creatThead(table, listThead);
		if(mapList.size()>5){
			List<Map<String, Object>> sysList = mapList.subList(0, 5);
			Table.creatTBody(table, sysList);
		}else{
			Table.creatTBody(table, mapList);
		}
		return row;
	}
	
	private Base createPanelHeader(String id, String title)
	{
		CssClass css = new CssClass("panel panel-default");
		Base panel = Div.getInstance(id, css, null);
		// 创建我的待办panelHeader
		css = new CssClass("panel-heading panel-heading-style");
		Base header = Div.getInstance(null, css, null);
		panel.addChildNode(header);
		// 创建我的待办H6
		Base h6 = H6.getInstance(null);
		css = new CssClass("h6-span");
		Base span = Span.getInstance(css, title);
		h6.addChildNode(span);
		header.addChildNode(h6);
		return panel;
	}

	private Base createPanelList(String content, String href, Integer size)
	{
		FrontUtil util = FrontUtil.getInstance();
		Base row = util.createRow();
		CssClass css = new CssClass("col-xs-8 col-md-8 col-sm-8 text-left bk-vcenter");
		Div rowleft = Div.getInstance(null, css, null);
		row.addChildNode(rowleft);
		css = new CssClass("h6-span-detail");
		//Span span1 = Span.getInstance(css, content);
		A bb = A.getInstance(href,content+"&nbsp（共&nbsp"+size+"&nbsp条)");
		if(size==null||size==0) {	bb = A.getInstance(href,content);};
		Prop propa = new Prop();
		propa.setPropKey("style");
		propa.setPropValue("color:#3150DD; display:inline-block;padding:0 0 0 20px;line-height:2;");
		bb.addProp(propa);
		
		rowleft.addChildNode(bb);
		css = new CssClass("col-xs-4 col-md-4 col-sm-4 bk-vcenter text-right");
		if (StringUtils.isNotEmpty(href))
		{
			Div rowright = Div.getInstance(null, css, null);
			Prop prop1 = new Prop();
			prop1.setPropKey("style");
			prop1.setPropValue("margin-top: 8px;");
			rowright.addProp(prop1);
			row.addChildNode(rowright);
			A b = A.getInstance(href, "点击查看>");
			Prop prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("color:#3150DD;padding:0 5 0 0;line-height:2");
			b.addProp(prop);
			rowright.addChildNode(b);
		}
		// 列表区域
		css = new CssClass("item");
		Base allmid = Div.getInstance(null, css, null);
		row.addChildNode(allmid);
		return row;
	}
}
