package com.ht.front.pages.background.dicdata.defect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.model.TextArea;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.coefficient.CoefficientService;
import com.ht.service.inter.background.dicdata.defect.DefectService;
import com.ht.service.inter.background.dicdata.defectitem.DefectItemService;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;

/**
 * @ClassName: DefectPage
 * @Description: TODO(缺陷页面处理类)
 * @author penghao
 * @date 2016年11月6日 下午3:58:52
 */
public class DefectPage
{

	/**
	 * 页面实例
	 */
	private static DefectPage page = null;

	/**
	 * 获取页面实例
	 * @return
	 */
	public static DefectPage getInstance()
	{
		if (page == null)
		{
			page = new DefectPage();
		}
		return page;
	}

	public String getListPage()
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "缺陷管理");
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting search");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "defect");
		/** 创建Grid行 结束 */
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDefect");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		return root.getNode() + script.getNode();
	}

	/**
	 * 初始化Form页面
	 * @param
	 * @return 节点字符串
	 */
	public String getEditPage(BaseDataService baseDataService, DefectTypeService defectTypeService, DefectService defectService,
			DefectItemService defectItemServie, String id)
	{
		EditPage edit = new EditPage();
		Base editPage = null;
		// 从基础字典表获取海图类型列表
		List<BaseData> seamapList = null;
		// 从基础字典表获取缺陷类别
		List<BaseData> deepList = null;
		// 获取缺陷
		Defect defect = null;
		try
		{
			// 获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			// 获取海图类型
			deepList = baseDataService.getBaseDataByTypeId(BaseDataConstants.DEFECT_TYPE_ID);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		if (StringUtils.isNotBlank(id))
		{
			try
			{
				// 根据ID获取缺陷
				defect = defectService.getDefectById(id);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			String seamapId = null;
			if (defect.getCharttype() != null && StringUtils.isNotBlank(defect.getCharttype().getId()))
			{
				seamapId = defect.getCharttype().getId();
			}
			Base selectgroup = util.creatDefaultSelectGroup("海图类型", "charttype", seamapList, "id", "value", seamapId);
			param.add(selectgroup);
			// 根据选中的海图类型id获取类别的列表
			List<DefectType> charttypeList = defectTypeService.getDefectTypeListByCharttypeId(seamapId);
			String typeId = null;
			if (defect.getType() != null && StringUtils.isNotBlank(defect.getType().getId()))
			{
				typeId = defect.getType().getId();
			}
			selectgroup = util.creatDefaultSelectGroup("类&#12288&#12288别", "defecttype", charttypeList, "id", "typeName", typeId);
			param.add(selectgroup);
			// 根据选中的缺陷类别id获取项目的列表
			List<DefectItem> itemList = defectItemServie.getDefectItemListByDefectTypeId(typeId);
			String itemId = null;
			if (defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId()))
			{
				itemId = defect.getItem().getId();
			}
			selectgroup = util.creatDefaultSelectGroup("项&#12288&#12288目", "defectitem", itemList, "id", "item", itemId);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("缺陷类别", "deep", deepList, "value", "value",
					defect.getDeep() == null ? null : defect.getDeep());
			param.add(selectgroup);
			
			InputGroup textArea= InputGroup.getTextAreaGroup("缺陷内容","discription","5",defect.getDiscription() == null ? null : defect.getDiscription());
			param.add(textArea);
			
			InputGroup ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", defect.getScore() == null ? null : defect.getScore(), "扣分");
			param.add(ig);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "缺陷编辑");
			InputHidden hiddenId = InputHidden.getInstance("defectId", id);
			editPage.addChildNode(hiddenId);
		}
		else
		{
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("海图类型", "charttype", seamapList, "id", "value", false);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("类&#12288&#12288别", "defecttype", null, "id", "typeName", false);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("项&#12288&#12288目", "defectitem", null, "id", "item", false);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("缺陷级别", "deep", deepList, "value", "value", false);
			param.add(selectgroup);
			// 缺陷内容
			InputGroup textArea= InputGroup.getTextAreaGroup("缺陷内容","discription","5",null);
			param.add(textArea);
			InputGroup ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", null, "扣分");
			param.add(ig);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "缺陷创建");
			InputHidden hiddenId = InputHidden.getInstance("defectId", id);
			editPage.addChildNode(hiddenId);
		}
		return editPage.getNode();
	}

	/**
	 * 源数据编绘、源数据小改正
	 * @param coefficientService
	 * @param revision
	 * @param baseDataService
	 * @param defectTypeService
	 * @param defectService
	 * @param defectItemServie
	 * @param id
	 * @return
	 */
	public String getSourceFormPage(String splitId, String formId,String taskInstId, String processInstId, String charttype, String mapNo, String revision,
			CoefficientService coefficientService, BaseDataService baseDataService,String processDefId,String taskDefId,String parentProcessInstId)
	{
		List<BaseData> seamapList = null;
		List<BaseData> layerList = null;
		String charttypeId = null;
		Coefficient cff = null;
		try
		{
			// 获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			// 获取海图类型id
			for (BaseData base : seamapList)
			{
				if (base.getCode().equals(charttype))
				{
					charttypeId = base.getId();
					break;
				}
			}
			// 根据图号和海图类型获取调整系数
			cff = coefficientService.getCoefficient(mapNo, charttypeId);
			// 获取图层列表
			layerList = baseDataService.getBaseDataByTypeId(BaseDataConstants.LAYER_TYPE_ID);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建div
		Div splitterdiv = Div.getBlankDiv("splitter");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow : hidden");
		splitterdiv.addProp(prop);
		Div leftDiv = Div.getBlankDiv("left");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		leftDiv.addProp(prop);
		//右侧div
		splitterdiv.addChildNode(leftDiv);
		Div rightDiv = Div.getBlankDiv("right");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow:hidden");
		rightDiv.addProp(prop);
		Div rightTopDiv = Div.getBlankDiv("righttop");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightTopDiv.addProp(prop);
		Div rightBottomDiv = Div.getBlankDiv("rightbottom");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightBottomDiv.addProp(prop);
		rightDiv.addChildNode(rightTopDiv);
		rightDiv.addChildNode(rightBottomDiv);
		splitterdiv.addChildNode(rightDiv);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(rightTopDiv, "bacthAdd");
		/** 创建Grid行 结束 */
		List<Base> param = new ArrayList<Base>();
		InputGroup ig = InputGroup.getSelectGroup("涉及图层", "layer",layerList, "id", "value", true);
		param.add(ig);
		Div opiDiv = Div.getInstance("", new CssClass("input-group"), "");
		opiDiv.addChildNode(Span.getDefault("存在问题及</br>处理意见："));
		CssClass opiCss = new CssClass("form-control");
		TextArea textArea = TextArea.getInstance("opinion", opiCss, "5", null);
		opiDiv.addChildNode(textArea);
		param.add(opiDiv);
		ig = InputGroup.getInGroup("类&#12288&#12288别", "defecttype", null, "类别");
		param.add(ig);
		ig = InputGroup.getInGroup("检查项目", "defectitem", null, "检查项目");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷内容", "discription", null, "缺陷内容");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷个数", "number", null, "缺陷个数");
		param.add(ig);
		ig = InputGroup.getInGroup(true, "缺陷类别", "deep", null, "缺陷类别");
		param.add(ig);
		ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", null, "扣分");
		param.add(ig);
		ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
		param.add(ig);
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < param.size(); i++)
		{
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row, "10", "8", "8", "1");
			// 将组件放入到列
			column.addChildNode(param.get(i));
			// 将行放入到容器中
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(row);
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建确定按钮列
		Base column = util.createColumn(rowOperation, "3", "3", "3", "1");
		// 创建确定按钮
		util.createSubmitButton(column);
		// 创建取消按钮列
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		// 创建取消按钮
		util.createCancelButton(column);
		
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		CssClass css = new CssClass("btn btn-info");
		Button button = Button.getInstance("processplan", css, "编绘计划问题");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("margin-left:35px");
		// 绑定属性
		button.addProp(prop);
		column.addChildNode(button);
		// 评分
		ig = InputGroup.getInGroup(true,"累计扣分", "total", null, "缺陷累计扣分");
		// 创建行
		Base row = util.createRow();
		// 创建列
		Base column1 = util.createColumn(row, "6", "4", "2", "1");
		Base column2 = util.createColumn(row, "6", "4", "2", "1");
		column1.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row);
		ig = InputGroup.getInGroup( "调整系数", "coefficient", null, "调整系数");
		column2.addChildNode(ig);
		ig = InputGroup.getInGroup(true,"实际扣分", "actualTotal", null, "实际扣分");
		// 创建行
		Base row2 = util.createRow();
		// 创建列
		Base column3 = util.createColumn(row2, "6", "4", "2", "1");
		Base column4 = util.createColumn(row2, "6", "4", "2", "1");
		column3.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row2);
		ig = InputGroup.getInGroup("质量评分", "grading", null, "质量评分");
		column4.addChildNode(ig);
		leftDiv.addChildNode(rowOperation);
		root.addChildNode(splitterdiv);
		InputHidden hiddenId = InputHidden.getInstance("charttypeId", charttypeId);
		root.addChildNode(hiddenId);
		// 流程实例和任务实例id
		InputHidden hiddensplitId = InputHidden.getInstance("splitId", splitId);
		root.addChildNode(hiddensplitId);
		InputHidden hiddenFormId = InputHidden.getInstance("formId", formId);
		root.addChildNode(hiddenFormId);
		InputHidden hiddentaskInsId = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(hiddentaskInsId);
		InputHidden hiddenprocessInsId = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(hiddenprocessInsId);
		InputHidden hiddenprocessDefId = InputHidden.getInstance("processDefId", processDefId);
		root.addChildNode(hiddenprocessDefId);
		InputHidden hiddentaskDefId = InputHidden.getInstance("taskDefId", taskDefId);
		root.addChildNode(hiddentaskDefId);
		InputHidden hiddenparentProcessInstIdId = InputHidden.getInstance("parentProcessInstId", parentProcessInstId);
		root.addChildNode(hiddenparentProcessInstIdId);
		// 实际扣分
		InputHidden hiddenActual = InputHidden.getInstance("actual", null);
		root.addChildNode(hiddenActual);

		String coefficient = "";
		if(revision == null){
			if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
			{
				coefficient = cff.getReprint();
			}
		}else{
			if (revision.equals("1"))
			{
				if (cff != null && StringUtils.isNotBlank(cff.getFirstEdition()))
				{
					coefficient = cff.getFirstEdition();
				}
			}
			else
			{
				if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
				{
					coefficient = cff.getReprint();
				}
			}
		}
		if(StringUtils.isBlank(coefficient)){
			coefficient = "";
		}
		InputHidden hiddenCff = InputHidden.getInstance("cff", coefficient);
		root.addChildNode(hiddenCff);
		// 添加删除的按钮
		CssClass editCss = new CssClass("fa fa-times");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-danger bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("removeDefect");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("删除");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("removePage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("removeTemplate");
		script.addChildNode(tempelate);
		root.addChildNode(planModal());
		return root.getNode()+script.getNode();
	}

	/**
	 * 电子海图编绘、电子海图小改正、工程/专题图（电子）
	 * @param charttypeId
	 * @param coefficientService
	 * @param revision
	 * @return
	 */
	public String getElectFormPage(String type,String splitId,String formId,String taskInstId, String processInstId, String charttype, String mapNo, String revision,
			CoefficientService coefficientService, BaseDataService baseDataService,String processDefId,String taskDefId,String parentProcessInstId)
	{
		List<BaseData> seamapList = null;
		List<BaseData> layerList = null;
		String charttypeId = null;
		Coefficient cff = null;
		try
		{
			// 获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			// 获取海图类型id
			for (BaseData base : seamapList)
			{
				if (base.getCode().equals(charttype))
				{
					charttypeId = base.getId();
					break;
				}
			}
			// 获取调整系数
			cff = coefficientService.getCoefficient(mapNo, charttypeId);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建div
		Div splitterdiv = Div.getBlankDiv("splitter");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow : hidden");
		splitterdiv.addProp(prop);
		Div leftDiv = Div.getBlankDiv("left");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		leftDiv.addProp(prop);
		splitterdiv.addChildNode(leftDiv);
		//右侧div
		Div rightDiv = Div.getBlankDiv("right");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow:hidden");
		rightDiv.addProp(prop);
		Div rightTopDiv = Div.getBlankDiv("righttop");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightTopDiv.addProp(prop);
		Div rightBottomDiv = Div.getBlankDiv("rightbottom");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightBottomDiv.addProp(prop);
		rightDiv.addChildNode(rightTopDiv);
		rightDiv.addChildNode(rightBottomDiv);
		splitterdiv.addChildNode(rightDiv);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(rightTopDiv, "bacthAdd");
		/** 创建Grid行 结束 */
		List<Base> param = new ArrayList<Base>();
		Div opiDiv = Div.getInstance("", new CssClass("input-group"), "");
		opiDiv.addChildNode(Span.getDefault("存在问题及</br>处理意见："));
		CssClass opiCss = new CssClass("form-control");
		TextArea textArea = TextArea.getInstance("opinion", opiCss, "5", null);
		opiDiv.addChildNode(textArea);
		param.add(opiDiv);
		InputGroup ig = InputGroup.getInGroup("类&#12288&#12288别", "defecttype", null, "类别");
		param.add(ig);
		ig = InputGroup.getInGroup("检查项目", "defectitem", null, "检查项目");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷内容", "discription", null, "缺陷内容");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷个数", "number", null, "缺陷个数");
		param.add(ig);
		ig = InputGroup.getInGroup(true, "缺陷类别", "deep", null, "缺陷类别");
		param.add(ig);
		ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", null, "扣分");
		param.add(ig);
		ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
		param.add(ig);
		if(type.equals(ProcessTypeEnum.SMALL_CORRECTION_ELECTRONIC.name())){
			if(taskDefId.contains("shending")){
				opiDiv = Div.getInstance("", new CssClass("input-group"), "");
				opiDiv.addChildNode(Span.getDefault("&#12288&#12288&#12288&#12288&#12288"));
				opiCss = new CssClass("form-control");
				textArea = TextArea.getInstance("defaultValue", opiCss, "5", "除中国海事珠三角电子海图目录中的39幅电子海图外， 其他电子海图小改正不经审定。");
				opiDiv.addChildNode(textArea);
				param.add(opiDiv);
			}
		}
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < param.size(); i++)
		{
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row, "10", "8", "8", "1");
			// 将组件放入到列
			column.addChildNode(param.get(i));
			// 将行放入到容器中
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(row);
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建确定按钮列
		Base column = util.createColumn(rowOperation, "3", "3", "3", "1");
		// 创建确定按钮
		util.createSubmitButton(column);
		// 创建取消按钮列
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		// 创建取消按钮
		util.createCancelButton(column);
		
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		CssClass css = new CssClass("btn btn-info");
		Button button = Button.getInstance("processplan", css, "编绘计划问题");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("margin-left:35px");
		// 绑定属性
		button.addProp(prop);
		column.addChildNode(button);
		
		
		// 评分
		ig = InputGroup.getInGroup(true, "累计扣分", "total", null, "缺陷累计扣分");
		// 创建行
		Base row = util.createRow();
		// 创建列
		Base column1 = util.createColumn(row, "6", "4", "2", "1");
		Base column2 = util.createColumn(row, "6", "4", "2", "1");
		column1.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row);
		ig = InputGroup.getInGroup(true,"调整系数", "coefficient", null, "调整系数");
		column2.addChildNode(ig);
		ig = InputGroup.getInGroup(true,"实际扣分", "actualTotal", null, "实际扣分");
		// 创建行
		Base row2 = util.createRow();
		// 创建列
		Base column3 = util.createColumn(row2, "6", "4", "2", "1");
		Base column4 = util.createColumn(row2, "6", "4", "2", "1");
		column3.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row2);
		//true 不可编辑 没有 true 可编辑
		ig = InputGroup.getInGroup("质量评分", "grading", null, "质量评分");
		column4.addChildNode(ig);
		leftDiv.addChildNode(rowOperation);
		root.addChildNode(splitterdiv);
		InputHidden hiddenId = InputHidden.getInstance("charttypeId", charttypeId);
		root.addChildNode(hiddenId);
		// 流程实例和任务实例id
		InputHidden hiddenFormId = InputHidden.getInstance("formId", formId);
		root.addChildNode(hiddenFormId);
		InputHidden hiddensplitId = InputHidden.getInstance("splitId", splitId);
		root.addChildNode(hiddensplitId);
		InputHidden hiddentaskInsId = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(hiddentaskInsId);
		InputHidden hiddenprocessInsId = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(hiddenprocessInsId);
		InputHidden hiddenprocessDefId = InputHidden.getInstance("processDefId", processDefId);
		root.addChildNode(hiddenprocessDefId);
		InputHidden hiddentaskDefId = InputHidden.getInstance("taskDefId", taskDefId);
		root.addChildNode(hiddentaskDefId);
		InputHidden hiddenparentProcessInstIdId = InputHidden.getInstance("parentProcessInstId", parentProcessInstId);
		root.addChildNode(hiddenparentProcessInstIdId);
		// 实际扣分
		InputHidden hiddenActual = InputHidden.getInstance("actual", null);
		root.addChildNode(hiddenActual);
		String coefficient = "";
		if(revision == null){
			if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
			{
				coefficient = cff.getReprint();
			}
		}else{
			if (revision.equals("1"))
			{
				if (cff != null && StringUtils.isNotBlank(cff.getFirstEdition()))
				{
					coefficient = cff.getFirstEdition();
				}
			}
			else
			{
				if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
				{
					coefficient = cff.getReprint();
				}
			}
		}
		if(coefficient.equals("")){
			if(type.equals(ProcessTypeEnum.PROJECT_SPECIAL_ELECTRONIC.name())){
				coefficient = "1.1";
			}else{
				coefficient = "";
			}
		}
		InputHidden hiddenCff = InputHidden.getInstance("cff", coefficient);
		root.addChildNode(hiddenCff);
		// 添加删除的按钮
		CssClass editCss = new CssClass("fa fa-times");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-danger bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("removeDefect");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("删除");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("removePage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("removeTemplate");
		script.addChildNode(tempelate);
		root.addChildNode(planModal());
		return root.getNode()+script.getNode();
	}

	/**
	 * 改正通告编辑、改正通告模板编辑
	 * @param charttypeId
	 * @return
	 */
	public String getNoticeFormPage(String splitId,String formId, String taskInstId, String processInstId, String charttype, String mapNo, String revision,
			CoefficientService coefficientService, BaseDataService baseDataService,String processDefId,String taskDefId)
	{
		List<BaseData> seamapList = null;
		List<BaseData> layerList = null;
		String charttypeId = null;
		try
		{
			// 获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			for (BaseData base : seamapList)
			{
				if (base.getCode().equals(charttype))
				{
					charttypeId = base.getId();
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		Div splitterdiv = Div.getBlankDiv("splitter");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow : hidden");
		splitterdiv.addProp(prop);
		Div leftDiv = Div.getBlankDiv("left");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		leftDiv.addProp(prop);
		splitterdiv.addChildNode(leftDiv);
		Div rightDiv = Div.getBlankDiv("right");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow:hidden");
		rightDiv.addProp(prop);
		Div rightTopDiv = Div.getBlankDiv("righttop");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightTopDiv.addProp(prop);
		Div rightBottomDiv = Div.getBlankDiv("rightbottom");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightBottomDiv.addProp(prop);
		rightDiv.addChildNode(rightTopDiv);
		rightDiv.addChildNode(rightBottomDiv);
		splitterdiv.addChildNode(rightDiv);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(rightTopDiv, "bacthAdd");
		/** 创建Grid行 结束 */
		List<Base> param = new ArrayList<Base>();
		Div opiDiv = Div.getInstance("", new CssClass("input-group"), "");
		opiDiv.addChildNode(Span.getDefault("存在问题及</br>处理意见："));
		CssClass opiCss = new CssClass("form-control");
		TextArea textArea = TextArea.getInstance("opinion", opiCss, "5", null);
		opiDiv.addChildNode(textArea);
		param.add(opiDiv);
		InputGroup ig = InputGroup.getInGroup("缺陷描述", "discription", null, "缺陷描述");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷个数", "number", null, "缺陷个数");
		param.add(ig);
		ig = InputGroup.getInGroup(true, "缺陷级别", "deep", null, "缺陷级别");
		param.add(ig);
		ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", null, "扣分");
		param.add(ig);
		ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
		param.add(ig);
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < param.size(); i++)
		{
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row, "10", "8", "8", "1");
			// 将组件放入到列
			column.addChildNode(param.get(i));
			// 将行放入到容器中
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(row);
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建确定按钮列
		Base column = util.createColumn(rowOperation, "3", "3", "3", "1");
		// 创建确定按钮
		util.createSubmitButton(column);
		// 创建取消按钮列
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		// 创建取消按钮
		util.createCancelButton(column);
		// 评分
		// 创建行
		Base row = util.createRow();
		// 创建列
		Base column1 = util.createColumn(row, "6", "4", "2", "1");
		Base column2 = util.createColumn(row, "6", "4", "2", "1");
		ig = InputGroup.getInGroup(true,"累计扣分", "total", null, "缺陷累计扣分");
		column1.addChildNode(ig);
		ig = InputGroup.getInGroup(true, "调整系数", "coefficient", "1", "调整系数");
		prop = new Prop();
		prop.setPropKey("readonly");
		prop.setPropValue("readonly");
		
		column2.addChildNode(ig);
		
		Base row2 = util.createRow();
		Base column3 = util.createColumn(row2, "6", "4", "2", "1");
		ig = InputGroup.getInGroup("质量评分", "grading", null, "质量评分");
		column3.addChildNode(ig);
		rightBottomDiv.addChildNode(row);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row2);
		leftDiv.addChildNode(rowOperation);
		root.addChildNode(splitterdiv);
		InputHidden hiddenId = InputHidden.getInstance("charttypeId", charttypeId);
		root.addChildNode(hiddenId);
		// 流程实例和任务实例id
		InputHidden hiddenFormId = InputHidden.getInstance("formId", formId);
		root.addChildNode(hiddenFormId);
		InputHidden hiddensplitId = InputHidden.getInstance("splitId", splitId);
		root.addChildNode(hiddensplitId);
		InputHidden hiddentaskInsId = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(hiddentaskInsId);
		InputHidden hiddenprocessInsId = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(hiddenprocessInsId);
		InputHidden hiddenprocessDefId = InputHidden.getInstance("processDefId", processDefId);
		root.addChildNode(hiddenprocessDefId);
		InputHidden hiddentaskDefId = InputHidden.getInstance("taskDefId", taskDefId);
		root.addChildNode(hiddentaskDefId);
		// 实际扣分
		InputHidden hiddenActual = InputHidden.getInstance("actual", null);
		root.addChildNode(hiddenActual);
		// 实际扣分
		InputHidden hiddenActualT = InputHidden.getInstance("actualTotal", null);
		root.addChildNode(hiddenActualT);
		// 添加删除的按钮
		CssClass editCss = new CssClass("fa fa-times");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-danger bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("removeDefect");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("删除");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("removePage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("removeTemplate");
		script.addChildNode(tempelate);
		return root.getNode()+script.getNode();
	}

	/**
	 * 纸海图编绘、纸海图小改正、工程/专题图（纸海图）
	 * @param charttypeId
	 * @return
	 */
	public String getPaperFormPage(String type,String splitId, String formId,String taskInstId, String processInstId, String charttype, String mapNo, String revision,
			CoefficientService coefficientService, BaseDataService baseDataService,String processDefId,String taskDefId,String parentProcessInstId)
	{
		List<BaseData> seamapList = null;
		List<BaseData> layerList = null;
		String charttypeId = null;
		Coefficient cff = null;
		try
		{
			// 获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			// 获取海图类型id
			for (BaseData base : seamapList)
			{
				if (base.getCode().equals(charttype))
				{
					charttypeId = base.getId();
					break;
				}
			}
			// 根据图号和海图类型获取调整系数
			cff = coefficientService.getCoefficient(mapNo, charttypeId);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		Div splitterdiv = Div.getBlankDiv("splitter");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow : hidden");
		splitterdiv.addProp(prop);
		Div leftDiv = Div.getBlankDiv("left");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		leftDiv.addProp(prop);
		//右侧div
		splitterdiv.addChildNode(leftDiv);
		Div rightDiv = Div.getBlankDiv("right");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow:hidden");
		rightDiv.addProp(prop);
		Div rightTopDiv = Div.getBlankDiv("righttop");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightTopDiv.addProp(prop);
		Div rightBottomDiv = Div.getBlankDiv("rightbottom");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("overflow-x : hidden");
		rightBottomDiv.addProp(prop);
		rightDiv.addChildNode(rightTopDiv);
		rightDiv.addChildNode(rightBottomDiv);
		splitterdiv.addChildNode(rightDiv);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(rightTopDiv, "bacthAdd");
		/** 创建Grid行 结束 */
		List<Base> param = new ArrayList<Base>();
		Div opiDiv = Div.getInstance("", new CssClass("input-group"), "");
		opiDiv.addChildNode(Span.getDefault("存在问题及</br>处理意见："));
		CssClass opiCss = new CssClass("form-control");
		TextArea textArea = TextArea.getInstance("opinion", opiCss, "5", null);
		opiDiv.addChildNode(textArea);
		param.add(opiDiv);
		InputGroup ig = InputGroup.getInGroup("检查内容", "defecttype", null, "检查内容");
		param.add(ig);
		ig = InputGroup.getInGroup("扣分项目", "defectitem", null, "扣分项目");
		param.add(ig);
		ig = InputGroup.getInGroup("缺陷个数", "number", null, "缺陷个数");
		param.add(ig);
		ig = InputGroup.getInGroup("扣&#12288&#12288分", "score", null, "扣分");
		param.add(ig);
		ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
		param.add(ig);
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < param.size(); i++)
		{
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row, "10", "8", "8", "1");
			// 将组件放入到列
			column.addChildNode(param.get(i));
			// 将行放入到容器中
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(rowSpace);
			leftDiv.addChildNode(row);
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建确定按钮列
		Base column = util.createColumn(rowOperation, "3", "3", "3", "1");
		// 创建确定按钮
		util.createSubmitButton(column);
		// 创建取消按钮列
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		// 创建取消按钮
		util.createCancelButton(column);
		
		column = util.createColumn(rowOperation, "3", "3", "3", null);
		CssClass css = new CssClass("btn btn-info");
		Button button = Button.getInstance("processplan", css, "编绘计划问题");
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("margin-left:35px");
		// 绑定属性
		button.addProp(prop);
		column.addChildNode(button);
		// 评分
		ig = InputGroup.getInGroup(true,"累计扣分", "total", null, "缺陷累计扣分");
		// 创建行
		Base row = util.createRow();
		// 创建列
		Base column1 = util.createColumn(row, "6", "4", "2", "1");
		Base column2 = util.createColumn(row, "6", "4", "2", "1");
		column1.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row);
		ig = InputGroup.getInGroup("调整系数", "coefficient", null, "调整系数");
		column2.addChildNode(ig);
		ig = InputGroup.getInGroup(true,"实际扣分", "actualTotal", null, "实际扣分");
		// 创建行
		Base row2 = util.createRow();
		// 创建列
		Base column3 = util.createColumn(row2, "6", "4", "2", "1");
		Base column4 = util.createColumn(row2, "6", "4", "2", "1");
		column3.addChildNode(ig);
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(util.createRowSpace());
		rightBottomDiv.addChildNode(row2);
		ig = InputGroup.getInGroup("质量评分", "grading", null, "质量评分");
		column4.addChildNode(ig);
		leftDiv.addChildNode(rowOperation);
		root.addChildNode(splitterdiv);
		// 类型
		InputHidden hiddenId = InputHidden.getInstance("charttypeId", charttypeId);
		root.addChildNode(hiddenId);
		// 流程实例和任务实例id
		InputHidden hiddenFormId = InputHidden.getInstance("formId", formId);
		root.addChildNode(hiddenFormId);
		InputHidden hiddensplitId = InputHidden.getInstance("splitId", splitId);
		root.addChildNode(hiddensplitId);
		InputHidden hiddentaskInsId = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(hiddentaskInsId);
		InputHidden hiddenprocessInsId = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(hiddenprocessInsId);
		InputHidden hiddenprocessDefId = InputHidden.getInstance("processDefId", processDefId);
		root.addChildNode(hiddenprocessDefId);
		InputHidden hiddenparentProcessInstIdId = InputHidden.getInstance("parentProcessInstId", parentProcessInstId);
		root.addChildNode(hiddenparentProcessInstIdId);
		InputHidden hiddentaskDefId = InputHidden.getInstance("taskDefId", taskDefId);
		root.addChildNode(hiddentaskDefId);
		// 实际扣分
		InputHidden hiddenActual = InputHidden.getInstance("actual", null);
		root.addChildNode(hiddenActual);
		String coefficient = "";
		if(revision == null){
			if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
			{
				coefficient = cff.getReprint();
			}
		}else{
			if (revision.equals("1"))
			{
				if (cff != null && StringUtils.isNotBlank(cff.getFirstEdition()))
				{
					coefficient = cff.getFirstEdition();
				}
			}
			else
			{
				if (cff != null && StringUtils.isNotBlank(cff.getReprint()))
				{
					coefficient = cff.getReprint();
				}
			}
		}
		if(coefficient.equals("")){
			if(type.equals(ProcessTypeEnum.PROJECT_SPECIAL_PAPER.name())){
				coefficient = "1.1";
			}else{
				coefficient = "";
			}
		}
		InputHidden hiddenCff = InputHidden.getInstance("cff", coefficient);
		root.addChildNode(hiddenCff);
		// 添加删除的按钮
		CssClass editCss = new CssClass("fa fa-times");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-danger bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("removeDefect");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("删除");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("removePage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("removeTemplate");
		script.addChildNode(tempelate);
		root.addChildNode(planModal());
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 创建任务提交模态窗口
	 * @param processDefKey
	 * @return
	 */
	private Div planModal(){
		/** 创建Modal Dialog 审批 开始*/
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("myModal", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div 
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("height:400px");
		contentDiv.addProp(prop);
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
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createGrid(root,"plan");
		headerDiv.addChildNode(root);
		/** Modal Dialog 结束*/
		return modalDiv;
	}

}
