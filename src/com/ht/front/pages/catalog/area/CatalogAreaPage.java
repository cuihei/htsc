package com.ht.front.pages.catalog.area;

import java.util.ArrayList;
import java.util.List;

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
import com.ht.front.template.ListPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.area.CatalogAreaService;


/**
 * 目录区域页面初始化类
 * */
public class CatalogAreaPage {
	
	
	FrontUtil util = null;
	
	public CatalogAreaPage(){
		util = FrontUtil.getInstance();
	}
	
	/**
	 * 初始化目录区域管理页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createListPage("", "catalogArea");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, "目录区域管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editArea");
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
		//返回节点字符串
		return root.getNode()+listPage.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑目录区域管理页面
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditNode(CatalogAreaService service,BaseDataService baseDataService,String catalogAreaId) throws Exception {
		CatalogArea area = new CatalogArea();
		if(catalogAreaId!=null){
			area = service.getCatalogAreaById(catalogAreaId);
		}
		
		EditPage edit = new EditPage();
		List<Base> param = new ArrayList<Base>();
		List<BaseData> listBaseData = baseDataService.getBaseDataByTypeId(BaseDataConstants.MULX_TYPE_ID);
		CssClass formCss = new CssClass("form-search");
		//创建form
		Form form = Form.getInstance("importForm",formCss,null);
		if(catalogAreaId!=null){
			FrontUtil util = FrontUtil.getInstance();
			util.createHeaderBar(form, "目录区域编辑");
		}else{
			FrontUtil util = FrontUtil.getInstance();
			util.createHeaderBar(form, "目录区域创建");
		}
		Prop formprop = new Prop();
		formprop.setPropKey("method");
		formprop.setPropValue("post");		
		
		Prop formProp = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		
		form.addProp(formprop);
		form.addProp(formProp);
		//目录类型
		Base sg = util.creatDefaultSelectGroup("目录类型","typeId",listBaseData,"id", "value",null==catalogAreaId?null:area.getBaseData().getId());
		form.addChildNode(sg);
		form.addChildNode(util.createRowSpace());
		//目录区域名称
		InputGroup ig = InputGroup.getInGroup("区域名称", "areaName",null==catalogAreaId?null:area.getAreaName(), "目录区域名称 ");
		form.addChildNode(ig);
		form.addChildNode(util.createRowSpace());
		//上传图片
		InputGroup span = InputGroup.getSpan("上传图片");
		form.addChildNode(util.createRowSpace());
		
		Div div = Div.getInstance(null, null, null);
		Div imgDiv = Div.getInstance("img", null, null);
		Img img = Img.getInstance("uploadImg", null, null);
		Prop prop = new Prop();
		prop.setPropKey("src");
		String src ="";
		if(null==area.getAreaImg()){
			 src =  "../../ht/upload/images/uploadpic.png";
		}else{
			src =  "data:image/"+area.getImgType()+";base64,"+area.getAreaImg();
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
		Base editPage = edit.createEditPage(param);
		InputHidden hiddenId = InputHidden.getInstance("id",catalogAreaId);
		editPage.addChildNode(hiddenId);
		//返回节点字符串
		return editPage.getNode();
	}
}
