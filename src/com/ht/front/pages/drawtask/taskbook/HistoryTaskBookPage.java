package com.ht.front.pages.drawtask.taskbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.ButtonGroup;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.model.TextArea;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.drawtask.taskbook.history.HistoryTaskBookService;
import com.ht.service.inter.drawtask.taskbook.relation.TaskBookPlanRelationService;

/**
 * 编绘任务书历史版本生成前端页面类
 * 
 * @author huodesheng
 * @date 2016/10/21
 */
public class HistoryTaskBookPage {
	FrontUtil util = null;

	Base root = null;

	public HistoryTaskBookPage() {
		// 获取前端工具实例
		util = FrontUtil.getInstance();
	}

	/**
	 * 生成列表页面
	 * 
	 * @param taskBookService
	 *            传入service
	 * @return 返回html
	 */
	public String getListPage(HistoryTaskBookService historyTaskBookService,String taskBookId,String taskBookType) {
		//获取根节点
		this.root = util.createRoot();
		util.createHeaderBar(root, "编绘任务书历史");
		//设置任务书类型
		root.addChildNode(InputHidden.getInstance("taskBookType", taskBookType));
		// 创建编会任务书列表模块
		this.createTaskBookGrid("hTaskBooks");
		//添加列表下方的按钮组。
		ButtonGroup buttonGroup = ButtonGroup.getInstance(this.createButtonList(true,true));
		root.addChildNode(util.createRowSpace());
		root.addChildNode(buttonGroup);
		//设置隐藏的Id
		root.addChildNode(InputHidden.getInstance("taskBookId",taskBookId));
		//创建编会任务书列表中的按钮
		String buttons = this.createButtonInGrid();
		//返回拼接好的html
		return root.getNode() + buttons;
	}
	/**
	 * 生成编辑页面
	 * 
	 * @param taskBookService
	 *            传入service
	 * @return 返回html
	 * @throws Exception 
	 */
	public String getDetailPage(HistoryTaskBookService historyTaskBookService,OrganizationService organizationService,HistoryTaskBook taskbook,TaskBookPlanRelationService taskBookPlanRelationService) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Date date = new Date();
		String planId=null;
		if(null!=taskbook){
			List<TaskBookPlanRelation> list = taskBookPlanRelationService.findListByBookId(taskbook.getId());
			for (TaskBookPlanRelation taskBookPlanRelation : list) {
				if(taskBookPlanRelation!=null){
					if(planId==null){
						planId=taskBookPlanRelation.getPlanId();
					}else{
						planId+=","+taskBookPlanRelation.getPlanId();
					}
				}
			}
		}
		//获取根节点
		this.root = util.createRoot();
		util.createHeaderBar(root, "编绘任务书历史编辑");
		root.addChildNode(InputHidden.getInstance("planId",planId));
		//隐藏id
		root.addChildNode(InputHidden.getInstance("taskBookId",taskbook.getId()));
		Base row = util.createRow(root);
		Base col = util.createColumn(row, "8", "8","4",null);
		//一、选择任务：级联选择
		Div div =null; 
//		Div.getInstance("", new CssClass("input-group"),Span.getDefault("一、选择任务：").getNode());
//		col.addChildNode(div);
		//创建选择任务页面
		util.createGrid(col, "taskShow");
		//设置任务书编号
		Span taskbookNo = Span.getDefault(taskbook.getTaskbookNo());
		col.addChildNode(taskbookNo);
		//二、任务名称：
		col.addChildNode(InputGroup.getInGroup("二、名&#12288&#12288称", null, taskbook.getTaskbookName(),""));
		//三、任务来源：
		col.addChildNode(InputGroup.getInGroup("三、任务来源", null, taskbook.getTaskFrom(),""));
		//四、执行部门：
		Organization organization = organizationService.getOrganization(taskbook.getExecuteDeptId());//"executeDeptId", organization, "id", "orgName",false
		if(organization!=null){
			col.addChildNode(InputGroup.getInGroup("四、执行部门", null, organization.getOrgName(),""));
		}else{
			col.addChildNode(InputGroup.getInGroup("四、执行部门", null, "",""));
		}
		//五、执行日期：
		col.addChildNode(InputGroup.getInGroup("五、执行日期", null, taskbook.getExecuteTime()+"",""));
		//六、技术标准：text
		div = Div.getInstance("", new CssClass("input-group"),"");
		div.addChildNode(Span.getDefault("六、技术标准："));//"technologyStandard","10"
		div.addChildNode(TextArea.getInstance("technologyStandard",  new CssClass("form-control"), "10", taskbook.getTechnologyStandard()));
		col.addChildNode(div);
		//七、技术要求：
		div = Div.getInstance("", new CssClass("input-group"),"");
		div.addChildNode(Span.getDefault("七、技术要求："));
		div.addChildNode(TextArea.getInstance("technologyDemand",new CssClass("form-control"),"10",taskbook.getTechnologyDemand()));
		col.addChildNode(div);
		//八、其他要求：
		div = Div.getInstance("", new CssClass("input-group"),"");
		div.addChildNode(Span.getDefault("八、其他要求："));
		div.addChildNode(TextArea.getInstance("otherDemand", new CssClass("form-control"), "10", taskbook.getOtherDemand()));
		col.addChildNode(div);
		//编绘管理科
		col.addChildNode(Span.getInstance(new CssClass("page_footer"), "编绘管理科"));
		//2016-09-09
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		col.addChildNode(Span.getInstance(new CssClass("page_footer"), sdf.format(date)));
		//保存，返回按钮
		ButtonGroup buttonGroup = ButtonGroup.getInstance(this.createButtonList(true,false,false));
		col.addChildNode(util.createRowSpace());
		col.addChildNode(buttonGroup);
		return root.getNode();
	}

	/**
	 * 创建列表模块
	 * 
	 * @param listName
	 *            div的名字
	 */
	@SuppressWarnings("unused")
	private void createTaskBookGrid(String listName) {
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行 开始 */
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12", "12");
		// 创建Grid
		util.createGrid(columnGrid, listName);
		/** 创建Grid行 结束 */
		// 将行加入到容器
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
	}

	/**
	 * 在script中放入按钮的html。
	 * 
	 * @param btnCss 按钮的css
	 * @param iCss	i标签的css
	 * @param btnName 按钮的name值
	 * @param scriptId	按钮所在的script的id
	 * @return
	 */
	public String createButtonInScript(CssClass btnCss, CssClass iCss,
			String btnName, String scriptId) {
		// 创建属性组件
		Prop prop = new Prop();
		// 创建按钮
		Button btn = Button.getButtonWithIcon(null, btnCss, null,
				I.getInstance(iCss));
		// 为按钮设置属性
		prop.setPropKey("name");
		prop.setPropValue(btnName);
		// 为按钮添加属性
		btn.addProp(prop);
		// 创建scrpit模块
		Script script = Script.getInstance(scriptId);
		// 将按钮添加到script中。
		script.addChildNode(btn);
		return script.getNode();
	}
	/**
	 * 创建编会任务书列表中的按钮,将创建好的按钮组装好
	 * @return
	 */
	public String createButtonInGrid(){
		//编辑按钮
		String buttons =  this.createButtonInScript(new CssClass("btn btn-info"),
				new CssClass("fa fa-github-alt"), "info", "editTemplate");
			// 下载附件按钮
			Div Enclosure = Div.getBlankDiv(null);
			Prop Enclosureprop = new Prop();
			Enclosureprop.setPropKey("name");
			Enclosureprop.setPropValue("download");
			Enclosure.addProp(Enclosureprop);
			// 创建scrpit模块
			Script script = Script.getInstance("downloadTemplate");
			// 将按钮添加到script中。
			script.addChildNode(Enclosure);
			buttons += 	script.getNode();
		//删除按钮
		buttons += this.createButtonInScript(new CssClass("btn btn-danger btn-setting"),
				new CssClass("fa fa-trash-o"), "delete", "delTemplate");
		return buttons;
	}
	/**
	 * 创建列表下方的按钮组
	 * @param isBack 是否包含返回按钮
	 * @param isSubmit 是否包含确认按钮
	 * @param isAdd 是否包含新增
	 * @return
	 */
	public List<Button> createButtonList(boolean isBack,boolean isSubmit){
		//创建按钮组
		List<Button> btnList=new ArrayList<Button>();
		/*//如果条件成立，创建提交按钮。
		if(isSubmit){
			btnList.add(Button.getInstance("submit", new CssClass("btn btn-success"), "提交"));
		}*/
		//如果条件成立，创建返回按钮。
		if(isBack){
			btnList.add(Button.getInstance("backPage", new CssClass("btn btn-default"), "返回"));
		}
		return btnList;
	}
	/**
	 * 创建列表下方的按钮组
	 * @param isBack 是否包含返回按钮
	 * @param isSubmit 是否包含确认按钮
	 * @param isAdd 是否包含新增
	 * @return
	 */
	public List<Button> createButtonList(boolean isBack,boolean isSubmit,boolean isAdd){
		//创建按钮组
		List<Button> btnList=new ArrayList<Button>();
		//如果条件成立，创建新增按钮。
		if(isAdd){
			btnList.add(Button.getInstance("addTaskBook", new CssClass("btn btn-success"), "新增"));
		}
		//如果条件成立，创建提交按钮。
		if(isSubmit){
			btnList.add(Button.getInstance("submit", new CssClass("btn btn-success"), "提交"));
		}
		//如果条件成立，创建返回按钮。
		if(isBack){
			btnList.add(Button.getInstance("backPage", new CssClass("btn btn-default"), "返回"));
		}
		return btnList;
	}
	
}
