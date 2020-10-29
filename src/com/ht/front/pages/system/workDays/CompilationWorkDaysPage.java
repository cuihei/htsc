package com.ht.front.pages.system.workDays;

import java.util.ArrayList;
import java.util.List;

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
import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;
import com.ht.service.inter.system.workDays.CompilationWorkDaysService;

public class CompilationWorkDaysPage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public String getListNode(String flag) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		if("planWorkDays".equals(flag)){
			util.createHeaderBar(root, "额定工天管理");
		}else if("realWorkDays".equals(flag)){
			util.createHeaderBar(root, "实际工天管理");
		}
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "12", "12");
		// 创建按钮组
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success search");
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
		// 创建Grid
		util.createGrid(root, "workDays");
		/** 创建Grid行 结束 */
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDetail");
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
		InputHidden hiddenId = InputHidden.getInstance("flag",flag);
		root.addChildNode(hiddenId);
		return root.getNode() + script.getNode();
	}
	
	/**
	 * 初始化新增、编辑页面
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public String getEditNode(CompilationWorkDaysService  workDaysService,String flag,String id ) throws Exception{
		String mapNo = "";
		String mapName = "";
		String scale = "";
		String content = null;
		String revision  = null;
		String compilationWorkDays = "1";
		String checkWorkDays = "1";
		String examineWorkDays = "1";
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		FrontUtil util = FrontUtil.getInstance();
		//编绘类型下拉框
		List<BaseModel> typeList = new ArrayList<BaseModel>();
		BaseData type = new BaseData();
		type.setTypeId("源数据");
		type.setTypeName("源数据");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("纸海图");
		type.setTypeName("纸海图");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("电子海图");
		type.setTypeName("电子海图");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("源数据小改正");
		type.setTypeName("源数据小改正");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("改正通告编辑");
		type.setTypeName("改正通告编辑");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("改正通告模版编辑");
		type.setTypeName("改正通告模版编辑");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("纸海图小改正");
		type.setTypeName("纸海图小改正");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("电子海图小改正");
		type.setTypeName("电子海图小改正");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("工程/专题纸海图");
		type.setTypeName("工程/专题纸海图");
		typeList.add(type);
		type = new BaseData();
		type.setTypeId("工程/专题电子海图");
		type.setTypeName("工程/专题电子海图");
		typeList.add(type);
		//版次下拉框
		List<BaseModel> revisionList = new ArrayList<BaseModel>();
		BaseData rev = new BaseData();
		rev.setTypeId("0");
		rev.setTypeName("首版");
		revisionList.add(rev);
		rev = new BaseData();
		rev.setTypeId("1");
		rev.setTypeName("再版");
		revisionList.add(rev);
		
		if(id!=null && id!=""){
			if("planWorkDays".equals(flag)){
				CompilationWorkDays workDaysList = workDaysService.getWorkDaysListById(id);
				mapNo = workDaysList.getMapNo();
				mapName = workDaysList.getMapName();
				scale = workDaysList.getScale();
				content = workDaysList.getContent();
				revision  = workDaysList.getRevision();
				compilationWorkDays = workDaysList.getCompilationWorkDays();
				checkWorkDays = workDaysList.getCheckWorkDays();
				examineWorkDays = workDaysList.getExamineWorkDays();
			}else if("realWorkDays".equals(flag)){
				CompilationRealWorkDays realWorkDaysList = workDaysService.getRealWorkDaysListById(id);
				mapNo = realWorkDaysList.getMapNo();
				mapName = realWorkDaysList.getMapName();
				scale = realWorkDaysList.getScale();
				content = realWorkDaysList.getContent();
				revision  = realWorkDaysList.getRevision();
				compilationWorkDays = realWorkDaysList.getCompilationWorkDays();
				checkWorkDays = realWorkDaysList.getCheckWorkDays();
				examineWorkDays = realWorkDaysList.getExamineWorkDays();
			}
		}
		Base selectgroup = util.creatDefaultSelectGroup("编绘类型", "content", typeList, "typeId", "typeName", content);
		param.add(selectgroup);
		/*InputGroup ig = InputGroup.getInGroup("版&#12288&#12288次", "revision",revision, "版次");
		param.add(ig);*/
		selectgroup = util.creatDefaultSelectGroup("版&#12288&#12288次", "revision", revisionList, "typeId", "typeName", revision);
		param.add(selectgroup);
		InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo",mapNo, "图号");
		param.add(ig);
		ig = InputGroup.getInGroup("图&#12288&#12288名", "mapName",mapName, "图名 ");
		param.add(ig);
		ig = InputGroup.getInGroup("比例尺(1:)", "scale",scale, "比例尺");
		param.add(ig);
		ig = InputGroup.getInGroup("编绘工天", "compilationWorkDays",compilationWorkDays, "编绘工天 ");
		param.add(ig);
		ig = InputGroup.getInGroup("质检工天", "checkWorkDays",checkWorkDays, "质检工天 ");
		param.add(ig);
		ig = InputGroup.getInGroup("审定工天", "examineWorkDays",examineWorkDays, "审定工天 ");
		param.add(ig);
		editPage = edit.createEditPage(param);
		if("planWorkDays".equals(flag))
		{
			if(null==id || id.isEmpty()){
				util.createHeaderBar(editPage, "额定工天新增");
			}else{
				util.createHeaderBar(editPage, "额定工天编辑");
			}
			
		}
		else if ("realWorkDays".equals(flag))
		{
			if(null==id || id.isEmpty()){
				util.createHeaderBar(editPage, "实际工天新增");
			}else{
				util.createHeaderBar(editPage, "实际工天编辑");
			}
		}
		InputHidden hiddenId = InputHidden.getInstance("id",id);
		editPage.addChildNode(hiddenId);
		hiddenId = InputHidden.getInstance("flag",flag);
		editPage.addChildNode(hiddenId);
		return editPage.getNode();
	}
	
}
