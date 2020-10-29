package com.ht.front.pages.system.workflow.taskconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ht.action.system.workflow.taskconfig.TaskConfigurationBaseDate;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.process.ProcessStatus;
import com.ht.persistence.model.system.workflow.process.ProcessTaskRelation;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.type.TypeService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.system.workflow.process.ProcessFormService;
import com.ht.service.inter.system.workflow.process.ProcessStatusService;
import com.ht.service.inter.system.workflow.process.ProcessTaskRelationService;
import com.ht.service.inter.system.workflow.taskconfig.TaskConfigurationService;
import com.ht.workflow.common.TaskDef;

/**
 * 流程任务配置页面生成类
 * @author 王有为
 * @date 2016/10/26
 */
public class TaskConfigurationPage
{

	/**
	 * 生成
	 * @return
	 */
	public String getPage(String processDefId)
	{
		// 实例前端工具类
		FrontUtil util = FrontUtil.getInstance();
		// 创建容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "流程任务配置");
		// 创建grid
		util.createGrid(root, "dv_task");
		// 创建分隔
		util.createRowSpace(root);
		// 创建返回行
		Base row = util.createRow(root);
		// 创建返回列
		Base column = util.createColumn(row, "12", "12", "12", null);
		// 创建返回按钮
		util.createCancelButton(column);
		// 创建hidden
		util.createHidden(root, "processDefId", processDefId);
		// 返回页面数据
		String template = buildTemplate();
		return root.getNode() + template;
	}

	private String buildTemplate()
	{
		Script script = Script.getInstance("operation");
		// 创建配置表单按钮
		CssClass css = new CssClass("fa fa-file-text-o");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button tempelate = Button.getButtonWithIcon(null, css, "表单", i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("formConfig");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("配置表单");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editConfig(this)");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		// 创建配置状态按钮
		css = new CssClass("fa fa-recycle");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		tempelate = Button.getButtonWithIcon(null, css, "状态", i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("statusConfig");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("配置状态");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editStatus(this)");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		
		css = new CssClass("fa fa-arrows-h");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning");
		tempelate = Button.getButtonWithIcon(null, css, "关联", i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("associated");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("关联");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editAssociated(this)");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		return script.getNode();
	}

	/**
	 * 初始化选择表单页面
	 * @param List<Type> 应用资源对象的List集合
	 * @throws Exception
	 * @retrun 节点字符串
	 */
	public String getFormNode(FormService formService, BaseDataService baseDataService, ProcessFormService processFormService, String processDefId,
			String processDefKey, String taskDefId,String taskName) throws Exception
	{

		FrontUtil util = FrontUtil.getInstance();
		// 查询
		ProcessForm processForm = processFormService.getProcessFormByProAndTask(processDefKey, taskDefId);
		// 获取应用资源对象的List集合
		List<Form> list = new ArrayList<Form>();
		List<BaseData> fromTypes = new ArrayList<BaseData>();
		// 子列表默认选项
		Form form = null;
		// 获取父列表数据
		fromTypes = baseDataService.getBaseDataByTypeId(TaskConfigurationBaseDate.FORM_TYPE_ID);
		BaseData baseData = null;
		// 排序号
		InputGroup txtOrder = null;
		// 数据委托
		InputGroup txtDelegate = null;
		if (processForm != null)
		{
			// 回显
			// 获取子列表的默认选项，及默认下拉值
			String formId = processForm.getFormId();
			txtOrder = InputGroup.getInGroup("序号", "orderNo", processForm.getOrderNo(), "请输入排序号", false);
			txtDelegate = InputGroup.getInGroup("数据委托", "delegate", processForm.getDelegate(), "请输入数据委托", false);
			if(formId != null){
				if(!formId.contains("请选择")){
					form = formService.getForm(formId);
					// 获取父列表及父列表、默认下拉值
					String baseDataId = form.getBaseDataId();
					baseData = baseDataService.getBaseData(baseDataId);
					list = formService.getFormByBaseDataId(baseDataId);
				}
			}
		}
		else{
			txtOrder = InputGroup.getInGroup("序号", "orderNo", null, "请输入排序号", false);
			txtDelegate = InputGroup.getInGroup("数据委托", "delegate", null, "请输入数据委托", false);
		}
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		InputGroup ipg = null;
		if (baseData != null)
		{
			ipg = InputGroup.getSelectGroup("表单类型", "formTypeId", fromTypes, "id", "value", baseData.getId());
		}
		else
		{
			ipg = InputGroup.getSelectGroup("表单类型", "formTypeId", fromTypes, "id", "value", false);
		}
		param.add(ipg);
		if (form != null)
		{
			ipg = InputGroup.getSelectGroup("表单", "formId", list, "id", "name", form.getId());
			
		}
		else
		{
			ipg = InputGroup.getSelectGroup("表单", "formId", list, "id", "name", false);
		}
		param.add(ipg);
		param.add(txtOrder);
		param.add(txtDelegate);
		Base row = util.createRow();
		Base col = util.createColumn(row, "12", "12", "12", null);
		Button btn = Button.getDefault("查看表单属性");
		Prop prop = new Prop("id", "prop");
		btn.addProp(prop);
		col.addChildNode(btn);
		param.add(row);
		editPage = edit.createEditPage(param);
		util.createHeaderBar(editPage, taskName);
		InputHidden hidden = InputHidden.getInstance("processDefId", processDefId);
		editPage.addChildNode(hidden);
		hidden = InputHidden.getInstance("taskDefName", taskName);
		editPage.addChildNode(hidden);
		hidden = InputHidden.getInstance("processDefKey", processDefKey);
		editPage.addChildNode(hidden);
		InputHidden hidden1 = InputHidden.getInstance("taskDefId", taskDefId);
		editPage.addChildNode(hidden1);
		return editPage.getNode();
	}

	/**
	 * 初始化选择状态页面
	 * @param List<Type> 应用资源对象的List集合
	 * @throws Exception
	 * @retrun 节点字符串
	 */
	public String getStatusNode(BaseDataService baseDataService, TypeService typeService, ProcessStatusService processStatusService,
			String processDefId, String processDefKey, String taskDefId) throws Exception
	{
		// 获取所有基础数据类型
		List<Type> types = new ArrayList<Type>();
		String[] stastusTypeIds = TaskConfigurationBaseDate.STASTUS_TYPE_IDS;
		if (stastusTypeIds != null && stastusTypeIds.length != 0)
		{
			for (int i = 0; i < stastusTypeIds.length; i++)
			{
				Type type = typeService.getType(stastusTypeIds[i]);
				types.add(type);
			}
		}

		// 获取数据记录
		ProcessStatus processStatus = processStatusService.getProcessStatusByProAndTask(processDefKey, taskDefId);
		// 获取应用资源对象的List集合
		List<BaseData> list = new ArrayList<BaseData>();
		BaseData baseData = null;
		Type type = null;
		if (processStatus != null)
		{
			// 回显
			// 获取记录关联的基础数据的ID
			String baseDateId = processStatus.getStatus();
			baseData = baseDataService.getBaseData(baseDateId);
			// 获取基础数据的所属类型及类型名称
			String typeId = baseData.getTypeId();
			type = typeService.getType(typeId);
			// 获取对应的下拉数据
			list = baseDataService.getBaseDataByTypeId(typeId);
		}

		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		InputGroup ipg = null;
		// 类型下拉ID statusType
		if (type != null)
		{
			ipg = InputGroup.getSelectGroup("状态类型", "statusType", types, "id", "name", type.getId());
		}
		else
		{
			ipg = InputGroup.getSelectGroup("状态类型", "statusType", types, "id", "name", false);
		}
		param.add(ipg);
		if (baseData != null)
		{
			ipg = InputGroup.getSelectGroup("状态", "status", list, "id", "value", baseData.getId());
		}
		else
		{
			ipg = InputGroup.getSelectGroup("状态", "status", list, "id", "value", false);
		}
		param.add(ipg);
		editPage = edit.createEditPage(param);
		InputHidden hidden = InputHidden.getInstance("processDefId", processDefId);
		editPage.addChildNode(hidden);
		hidden = InputHidden.getInstance("processDefKey", processDefKey);
		editPage.addChildNode(hidden);
		InputHidden hidden1 = InputHidden.getInstance("taskDefId", taskDefId);
		editPage.addChildNode(hidden1);
		return editPage.getNode();
	}
	
	/**
	 * 获取流程任务表单属性页面
	 * @return
	 */
	public String getProcessFormPropPage(String taskName){
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, taskName);
		Base grid = util.createGrid(root, "div_process_form_prop");
		Base row = util.createRow(root);
		Base col = util.createColumn(row, "12", "12", "12", null);
		util.createSubmitButton(col);
		util.createCancelButtonBk5(col);
		Script script = Script.getInstance("edit");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button tempelate = Button.getButtonWithIcon(null, css, "编辑", i);
		script.addChildNode(tempelate);
		return root.getNode() + script.getNode();
	}
	
	/**
	 * 初始化选择状态页面
	 * @param List<Type> 任务名称的List集合
	 * @throws Exception
	 * @retrun 节点字符串
	 */
	public String getAssociatedNode(TaskConfigurationService service,ProcessTaskRelationService processTaskRelationService,
			String processDefId, String processDefKey, String taskDefId) throws Exception
	{
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		List<TaskDef> taskList = service.getTaskListByProcessDefId(processDefId);
		Iterator<TaskDef> iter = taskList.iterator();
        while(iter.hasNext()){
        	TaskDef b = iter.next();
            if(b.getTaskDefId().equals(taskDefId)){
                iter.remove();
            }
        }
        
		InputGroup ipg = InputGroup.getSelectGroup("任务名称", "name", taskList, "taskDefId", "taskDefName", false);
		param.add(ipg);
		editPage = edit.createEditPage(param);
		InputHidden hidden = InputHidden.getInstance("processDefId", processDefId);
		editPage.addChildNode(hidden);
		hidden = InputHidden.getInstance("processDefKey", processDefKey);
		editPage.addChildNode(hidden);
		InputHidden hidden1 = InputHidden.getInstance("taskDefId", taskDefId);
		editPage.addChildNode(hidden1);
		
	    List<ProcessTaskRelation> pList= processTaskRelationService.findByTaskDefId(taskDefId,processDefId);
	   String relationIds = "";
        if(pList != null){
        	if(pList.size()>0){
        		for (int i = 0; i < pList.size(); i++) {
        			relationIds += ","+(pList.get(i).getRelationId());
        			
				}
        	}
        }
        if(relationIds.length()>0){
        	relationIds = relationIds.substring(1);
        }
        InputHidden hidden2 = InputHidden.getInstance("relationId", relationIds);
		editPage.addChildNode(hidden2);
		return editPage.getNode();
	}}
