package com.ht.front.pages.drawtask.taskbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ht.common.util.SpringUtil;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.drawtask.taskbill.TaskBillService;
import com.ht.service.inter.drawtask.taskbook.create.CreateTaskService;

/**
 * 创建编绘任务生成前端页面类
 * @author yp
 */
@SuppressWarnings("unused")
public class CreateTaskPage
{
	FrontUtil util = null;
	Base root = null;

	/**
	 * 生成列表页面
	 */
	public String getListNode(boolean jurisdiction) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row, "4", "4", "4", null);
		// 创建列
		Base col2 = util.createColumn(row, "2", "2", "2", null);
		// 创建列
		Base col3 = util.createColumn(row, "2", "2", "2", "4");
		Span span = Span.getDefault("年份:");
		CssClass css = new CssClass("fa fa-calendar");
		I i = I.getInstance(css);
		span.addChildNode(i);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		TextBox text = TextBox.getDefault("year", String.valueOf(year), "年份");
		InputGroup ig = InputGroup.getInstance(span, text);
		col3.addChildNode(ig);
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		// 创建Grid
		util.createGrid(root, "createTask");
		/** 创建Grid行 结束 */
		/** 创建Modal Dialog 开始 */
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("myModal", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		dialogDiv.addChildNode(contentDiv);
		// 创建header div
		CssClass headerCss = new CssClass("modal-header");
		Div headerDiv = Div.getInstance(null, headerCss, null);
		contentDiv.addChildNode(headerDiv);
		// 构建关闭按钮
		CssClass closeCss = new CssClass("close");
		Button closeBtn = Button.getInstance(null, closeCss, "&times;");
		Prop closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		Prop closeProps = new Prop();
		closeProps.setPropKey("aria-hidden");
		closeProps.setPropValue("true");
		// 绑定属性
		closeBtn.addProp(closeProp);
		closeBtn.addProp(closeProps);
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "导入编绘任务");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("importForm", formCss, null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		Prop formProps = new Prop();
		formProps.setPropKey("enctype");
		formProps.setPropValue("multipart/form-data");
		form.addProp(formProps);
		headerDiv.addChildNode(form);
		// 文件导入和模板下载
		CssClass inputCss = new CssClass("row");
		Div inputDiv = Div.getInstance(null, inputCss, null);
		form.addChildNode(inputDiv);
		// 创建文件导入input框
		CssClass fileCss = new CssClass("file-input");
		File file = File.getInstance("uploadFile", fileCss);
		Prop fileProp = new Prop();
		fileProp.setPropKey("name");
		fileProp.setPropValue("upload");
		file.addProp(fileProp);
		inputDiv.addChildNode(file);
		// 模板下载按钮
		CssClass tempCss = new CssClass("btn btn-primary btn-export");
		Button tempBtn = Button.getInstance("exportTemplate", tempCss, "下载模板");
		inputDiv.addChildNode(tempBtn);
		//标识隐藏
		InputHidden bookInfoIdHidden = InputHidden.getInstance("id", null);
		Prop bookInfoIdProp = new Prop();
		bookInfoIdProp.setPropKey("name");
		bookInfoIdProp.setPropValue("id");
		bookInfoIdHidden.addProp(bookInfoIdProp);
		form.addChildNode(bookInfoIdHidden);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importSubmit", importCss, "导入");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束 */
		root.addChildNode(modalDiv);
		Script script = Script.getInstance("editTemplate");
		if(jurisdiction){
			//添加编辑按钮
			CssClass editCss = new CssClass("fa fa-plus");
			I editI = I.getInstance(editCss);
			editCss = new CssClass("btn btn-success bk-margin-4");
			Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("create");
			// 绑定属性
			tempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("title");
			prop.setPropValue("创建任务");
			tempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("create(this)");
			tempelate.addProp(prop);
			// 导入
			CssClass uploadCss = new CssClass("fa fa-sign-in");
			I uploadI = I.getInstance(uploadCss);
			uploadCss = new CssClass("btn btn-info bk-margin-5");
			Button uploadTempelate = Button.getButtonWithIcon(null, uploadCss, null, uploadI);
			Prop uploadProp = new Prop();
			uploadProp.setPropKey("name");
			uploadProp.setPropValue("c");
			uploadTempelate.addProp(uploadProp);
			uploadProp = new Prop();
			uploadProp.setPropKey("title");
			uploadProp.setPropValue("导入任务");
			uploadTempelate.addProp(uploadProp);
			uploadProp = new Prop();
			uploadProp.setPropKey("onclick");
			uploadProp.setPropValue("uploadPage(this)");
			uploadTempelate.addProp(uploadProp);
		
			script.addChildNode(tempelate);
			script.addChildNode(uploadTempelate);
		}
		
		// 查看任务书按钮
		Script script1 = Script.getInstance("showTaskBook");
		css = new CssClass("fa fa-eye");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning");
		Button show = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop("title","查看任务书");
		show.addProp(prop);
		prop = new Prop("onclick","createTask.showTaskBook(this)");
		show.addProp(prop);
		script1.addChildNode(show);
		root.addChildNode(InputHidden.getInstance("jurisdiction", jurisdiction+""));
		return root.getNode() + script.getNode()+ script1.getNode();
	}
	
	public String getChildTaskCreatePage(String id,String taskBookName,BaseDataService baseDataService) throws Exception{
		// 获取任务书中任务的图号 图名 比例尺
		String mapNo = null;
		String mapName = null;
		String scale = null;
		String revision = null;
		TaskBillService taskBillService = (TaskBillService) SpringUtil.getBean("taskBillService");
		List<TaskBill> taskBill = taskBillService.findListByTaskBookId(id);
		if (taskBill  != null)
		{
			if (taskBill.size()>0)
			{
				Plan plan = taskBill.get(0).getPlan();
				mapNo = plan.getMapNo();
				mapName = plan.getMapName();
				scale = plan.getScale();
				revision = taskBill.get(0).getRevision();
			}
		}
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		util = FrontUtil.getInstance();
		List<BaseData> taskBookTypeList = baseDataService.getBaseDataByTypeId(BaseDataConstants.TASK_BOOK_TYPE);
		FrontUtil util = FrontUtil.getInstance();
		Base selectgroup = util.creatDefaultSelectGroup("&#12288类型", "taskType", taskBookTypeList, "code", "value", false);
		param.add(selectgroup);
		selectgroup = util.creatDefaultSelectGroup("子类型", "childTaskType", null,"code", "value", false);
		param.add(selectgroup);
		InputGroup ig= InputGroup.getInGroup("&#12288任务名", "taskName", mapName,  "任务名");
		param.add(ig);
		ig = InputGroup.getInGroup("版&#12288次", "revision", revision, "版次");
		param.add(ig);
		ig = InputGroup.getInGroup("&#12288图&#12288号", "mapNo", mapNo, "图号");
		param.add(ig);
		ig = InputGroup.getInGroup("比例尺1", "scale", scale, "比例尺1:");
		param.add(ig);
		
		
		
		/*String dateStr=new SimpleDateFormat("yyyy-MM").format(new Date());
		ig = InputGroup.getDatePickerYearMonth("年&#12288&#12288月","yearMonth",dateStr,null);
		param.add(ig);*/
		editPage = edit.createEditPage(param);
		InputHidden hiddenId = InputHidden.getInstance("id",id);
		editPage.addChildNode(hiddenId);
		editPage.addChildNode(util.createHeaderBar(taskBookName));
		return editPage.getNode();
	}
	public String getChildTaskEditPage(String id,String taskBookName,BaseDataService baseDataService,CreateTaskService createTaskService) throws Exception{
		// 获取任务书中任务的图号 图名 比例尺
		String mapNo = null;
		String mapName = null;
		String scale = null;
		String revision = null;
		String taskType = null;
		String childTaskType = null;
		CreateTask task = createTaskService.getTask(id);
		if(task!=null){
			mapNo=task.getMapNo();
			mapName=task.getTaskName();
			scale=task.getScale();
			revision=task.getRevision();
			taskType=task.getTaskType();
			childTaskType=task.getChildTaskType();
			
		}
	
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		util = FrontUtil.getInstance();
		List<BaseData> taskBookTypeList = baseDataService.getBaseDataByTypeId(BaseDataConstants.TASK_BOOK_TYPE);
		List<BaseData> childTaskTypeList =null;
		if(taskType!=null){
			childTaskTypeList=createTaskService.getChildTaskType(taskType.replace("TASK_BOOK_", ""));
		}
		FrontUtil util = FrontUtil.getInstance();
		Base selectgroup = util.creatDefaultSelectGroup("&#12288类型", "taskType", taskBookTypeList, "code", "value",taskType);
		param.add(selectgroup);
		selectgroup = util.creatDefaultSelectGroup("子类型", "childTaskType", childTaskTypeList,"code", "value",childTaskType);
		param.add(selectgroup);
		InputGroup ig= InputGroup.getInGroup("&#12288任务名", "taskName", mapName,  "任务名");
		param.add(ig);
		ig = InputGroup.getInGroup("版&#12288次", "revision", revision, "版次");
		param.add(ig);
		ig = InputGroup.getInGroup("&#12288图&#12288号", "mapNo", mapNo, "图号");
		param.add(ig);
		ig = InputGroup.getInGroup("比例尺1", "scale", scale, "比例尺1:");
		param.add(ig);
		
		editPage = edit.createEditPage(param);
		InputHidden hiddenId = InputHidden.getInstance("id",id);
		editPage.addChildNode(hiddenId);
		editPage.addChildNode(util.createHeaderBar(mapName==null?"":mapName));
		return editPage.getNode();
	}
	
	public String getChildListPage(boolean jurisdiction){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建按钮组
		Base row = util.createRow(root);
		Base column = util.createColumn(row, "12", "12", "12", null);
		if(jurisdiction){
			// 创建发布按钮
			CssClass css = new CssClass("fa fa-check");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-5");
			Base publish = Button.getButtonWithIcon("publish", css, "发布", i);
			column.addChildNode(publish);
		}
		// 创建刷新按钮
		CssClass	css = new CssClass("fa fa-refresh");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5");
		Base refresh = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(refresh);
		Script script = Script.getInstance("publishTempelate");
		// 创建grid
					Base grid = util.createGrid(root, "compilation_child_task");
		if(jurisdiction){
			// 创建编辑按钮
			css = new CssClass("fa fa-edit");
			i = I.getInstance(css);
			css = new CssClass("btn btn-success  bk-margin-5");
			Base edit = Button.getButtonWithIcon("edit", css, "编辑", i);
			column.addChildNode(edit);
			// 创建删除按钮
			css = new CssClass("fa fa-times ");
			i = I.getInstance(css);
			css = new CssClass("btn btn-danger");
			Base delete = Button.getButtonWithIcon("delete", css, "删除", i);
			column.addChildNode(delete);	

			util.createRowSpace(root);
			
			// 发布模版按钮
			css = new CssClass("fa fa-check");
			i = I.getInstance(css);
			css = new CssClass("btn btn-success");
			Button publishTempelate = Button.getButtonWithIcon(null, css, null, i);
			Prop prop = new Prop();
			publishTempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("title");
			prop.setPropValue("发布");
			publishTempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("childTask.publish(this)");
			publishTempelate.addProp(prop);
			script.addChildNode(publishTempelate);
		}
		root.addChildNode(InputHidden.getInstance("jurisdiction", jurisdiction+""));
		root.addChildNode(script);
		// 返回结果
		return root.getNode();
	}
}
