package com.ht.front.pages.system.maps;

import java.util.ArrayList;
import java.util.List;

import com.ht.common.util.LogHelper;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.Img;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Templete;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.system.maps.Maps;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.catalog.detail.CatalogDetailService;
import com.ht.service.inter.system.maps.MapsService;

public class MapsPage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "图幅管理");
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
		util.createGrid(root, "maps");
		/** 创建Grid行 结束 */
		// 添加查看图片按钮
		CssClass viewCss = new CssClass("fa fa-eye");
		I viewI = I.getInstance(viewCss);
		viewCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate1 = Button.getButtonWithIcon(null, viewCss, null, viewI);
		Prop viewprop = new Prop();
		viewprop.setPropKey("name");
		viewprop.setPropValue("viewImg");
		// 绑定属性
		tempelate1.addProp(viewprop);
		viewprop = new Prop();
		viewprop.setPropKey("title");
		viewprop.setPropValue("查看");
		tempelate1.addProp(viewprop);
		viewprop = new Prop();
		viewprop.setPropKey("onclick");
		viewprop.setPropValue("viewPage(this)");
		tempelate1.addProp(viewprop);
		Script script1 = Script.getInstance("viewTemplate");
		script1.addChildNode(tempelate1);
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
		return root.getNode() + script.getNode() + script1.getNode();
	}
	
	/**
	 * 初始化新增、编辑页面
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public String getEditNode(MapsService mapsService,CatalogDetailService catalogDetailService,CatalogAreaService catalogAreaService,BaseDataService baseDataService,String id ) throws Exception{

		EditPage edit = new EditPage();
		Base editPage = null;
	/*	// 从基础字典表获取目录类型列表
		List<BaseData> typeList = null;
		// 获取目录区域列表
		List<CatalogArea> areaList = null;
		// 获取目录
		List<CatalogDetail> detail = null;*/
		// 获取图幅管理list
		Maps maps = new Maps();
		/*// 获取存在图号的目录类型
		typeList = catalogDetailService.getBaseDataFromDetail();
		// 获取存在图号的目录区域
		areaList = catalogDetailService.getCatalogAreaListFromDetail(typeList.get(0).getId());
		// 根据ID获取目录
		detail = catalogDetailService.getDetailByCategoryId(typeList.get(0).getId(),areaList.get(0).getId());*/
		// 获取图幅管理list
		if(id!=null){
			maps = mapsService.getMapsListById(id);
		}
		List<Base> param = new ArrayList<Base>();
		FrontUtil util = FrontUtil.getInstance();
		//Base selectgroup = util.creatDefaultSelectGroup("目录类型", "catalogTypeId", typeList, "id", "value", false);
		//param.add(selectgroup);
		//selectgroup = util.creatDefaultSelectGroup("目录区域", "catalogAreaId", null, "id", "areaName", false);
		//param.add(selectgroup);
		InputGroup selectgroup = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", maps.getMapNo()==null?"":maps.getMapNo(), "请输入图号");
		param.add(selectgroup);
		//InputGroup ig = InputGroup.getInGroup(true,"图&#12288&#12288名", "mapName","", "图名 ");
		//param.add(ig);
		//ig = InputGroup.getInGroup(true,"比例尺(1:)", "scale","", "比例尺(1:)");
		//param.add(ig);
		editPage = edit.createEditPage(param);
		CssClass formCss = new CssClass("form-search");
		//创建form
		Form form = Form.getInstance("importForm",formCss,null);
		if(id!=null){
			util = FrontUtil.getInstance();
			util.createHeaderBar(form, "图幅管理修改");
		}else{
			util = FrontUtil.getInstance();
			util.createHeaderBar(form, "图幅管理新增");
		}
		Prop formprop = new Prop();
		formprop.setPropKey("method");
		formprop.setPropValue("post");		
		
		Prop formProp = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		
		form.addProp(formprop);
		form.addProp(formProp);
		param.add(null);
		//上传图片
		InputGroup span = InputGroup.getSpan("上传图片");
		form.addChildNode(util.createRowSpace());
		
		Div div = Div.getInstance(null, null, null);
		Div imgDiv = Div.getInstance("img", null, null);
		Img img = Img.getInstance("uploadImg", null, null);
		Prop prop = new Prop();
		prop.setPropKey("src");
		String src ="";
		if(maps.getPicture()!=null){
			src =  "data:image/"+maps.getPictureType()+";base64,"+maps.getPicture();
		}else{
			src =  "../ht/upload/images/uploadpic.png";
		}
		prop.setPropValue(src);
		img.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("onclic()");
		img.addProp(prop);
		imgDiv.addChildNode(img);
		
		img = Img.getInstance("myImg", null, null);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("cursor:pointer");
		img.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("onclic()");
		img.addProp(prop);
		imgDiv.addChildNode(img);
		span.addChildNode(imgDiv);
		form.addChildNode(span);
		
		Templete input = Templete.getInstance("input", null);
		prop = new Prop();
		prop.setPropKey("type");
		prop.setPropValue("file");
		input.addProp(prop);
		prop.setPropKey("name");
		prop.setPropValue("myfiles");
		input.addProp(prop);
		prop.setPropKey("id");
		prop.setPropValue("myfiles");
		input.addProp(prop);
		prop.setPropKey("onchange");
		prop.setPropValue("changeImg()");
		input.addProp(prop);
		prop.setPropKey("style");
		prop.setPropValue("display:none");
		input.addProp(prop);
		form.addChildNode(input);
		div.addChildNode(form);
		
		param.add(div);
		editPage = edit.createEditPage(param);
		InputHidden hiddenId = InputHidden.getInstance("id",id);
		editPage.addChildNode(hiddenId);
		if(maps!=null){
			hiddenId = InputHidden.getInstance("mapsNo",maps.getMapNo());
			editPage.addChildNode(hiddenId);
		}
		return editPage.getNode();
	}
	
	/**
	 * 初始化图片查看页面
	 * @param
	 * @return 节点字符串
	 */
	public String getViewPage(MapsService mapsService, String id)
	{
		// 获取图片url
		String src = null;
		try
		{
			Maps maps = mapsService.getMapsListById(id);
			src =  "data:image/"+maps.getPictureType()+";base64,"+maps.getPicture();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "图片查看");
		// 创建一个div
		CssClass divCss = new CssClass("col-lg-4 col-md-4 col-sm-3 col-xs-3");
		Div div = Div.getInstance("content", divCss, null);
		// 图片
		CssClass css = new CssClass("view-img");
		Img img = Img.getInstance("myImg", null, css);
		CssClass cssbtn = new CssClass("btn btn-default img-top");
		Prop prop = new Prop();
		prop.setPropKey("src");
		prop.setPropValue(src);
		img.addProp(prop);
		div.addChildNode(img);
		// 返回按钮
		Button btn = Button.getInstance("back", cssbtn, "返回");
		div.addChildNode(btn);
		root.addChildNode(div);
		return root.getNode();
	}
}
