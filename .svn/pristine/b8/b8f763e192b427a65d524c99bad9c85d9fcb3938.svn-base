package com.ht.front.pages.background.dicdata.defecttype;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;

/** 
* @ClassName: DefectPage 
* @Description: TODO(缺陷类别页面处理类) 
* @author penghao
* @date 2016年11月6日 下午3:58:52 
*  
*/
public class DefectTypePage {
	
	/**
	 * 页面实例
	 */
	private static DefectTypePage page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static DefectTypePage getInstance(){
		if (page == null) {
			page = new DefectTypePage();
		}
		return page;
	}

	public String getListPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "缺陷类别管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
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
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"defecttype");
		/** 创建Grid行  结束*/
		// 创建列
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editType");
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
		return root.getNode()+script.getNode();
	}

	/**
	 * 
	 * 初始化Form页面
	 * @param
	 * @return 节点字符串
	 */
	public String getEditPage(BaseDataService baseDataService,DefectTypeService defectTypeService,String id) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 从基础字典表获取海图类型列表
		List<BaseData> seamapList = null;
		// 获取目录
		DefectType type = null;
		try {
			//获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			// 根据ID获取目录
			type = defectTypeService.getDefectTypeById(id);
		} catch (Exception e) {
			e.getMessage();
		}
		String charttype = null;
		if(type != null && type.getCharttype()!= null 
				&&StringUtils.isNotBlank(type.getCharttype().getId())){
			charttype = type.getCharttype().getId();
		}
		if (StringUtils.isNotBlank(id)) {
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("海图类型", "charttype", seamapList, "id", "value",
					charttype);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("类&#12288&#12288别", "typeName", type.getTypeName(), "类别");
			param.add(ig);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "缺陷类别编辑");
			InputHidden hiddenId = InputHidden.getInstance("typeId", id);
			editPage.addChildNode(hiddenId);
		}else{
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("海图类型", "charttype", seamapList, "id", "value",
					charttype);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("类&#12288&#12288别", "typeName", null, "类别");
			param.add(ig);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "缺陷类别创建");
			InputHidden hiddenId = InputHidden.getInstance("typeId", id);
			editPage.addChildNode(hiddenId);
			}
			return editPage.getNode();
	}

}
