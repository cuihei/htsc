package com.ht.front.pages.background.dicdata.coefficient;

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
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.coefficient.CoefficientService;

/** 
* @ClassName: CoefficientPage 
* @Description: TODO(调整系数处理类) 
* @author penghao
* @date 2016年11月6日 下午3:58:52 
*  
*/
public class CoefficientPage {
	
	/**
	 * 页面实例
	 */
	private static CoefficientPage page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static CoefficientPage getInstance(){
		if (page == null) {
			page = new CoefficientPage();
		}
		return page;
	}

	public String getListPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "调整系数管理");
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
		Base rowGrid = util.createGrid(root,"coe");
		/** 创建Grid行  结束*/
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editCoe");
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
	public String getEditPage(CoefficientService service,
			BaseDataService baseDataService,String id) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 从基础字典表获取海图类型列表
		List<BaseData> seamapList = null;
		// 获取目录
		Coefficient coe = null;
		try {
			//获取海图类型
			seamapList = baseDataService.getBaseDataByTypeId(BaseDataConstants.HAITU_TYPE_ID);
			for (int i = 0; i < seamapList.size(); i++) {
				String name = seamapList.get(i).getValue();
				if(name.contains("改正通告")){
					seamapList.remove(i);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		List<Base> param = new ArrayList<Base>();
		FrontUtil util = FrontUtil.getInstance();
		if (StringUtils.isNotBlank(id)) {
			try {
				// 根据ID获取目录
				coe = service.getCoefficientById(id);
			
			} catch (Exception e) {
				e.getMessage();
			}
			List<Coefficient> coeList=service.getCoefficientBymapNo(coe.getMapNo());
			
		
		
		/*	Base selectgroup = util.creatDefaultSelectGroup("调整系数类型", "type", seamapList, "id", "value",false);
			param.add(selectgroup);*/
			
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", coe.getMapNo()==null?"":coe.getMapNo(), "请输入图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288&#12288&#12288名", "mapName", coe.getMapName()==null?"":coe.getMapName(), "请输入图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)", "scale", coe.getScale()==null?"":coe.getScale(), "请输入比例尺");
			param.add(ig);
			/*ig = InputGroup.getInGroup("首&#12288&#12288&#12288&#12288版", "firstEdition", coe.getFirstEdition()==null?"":coe.getFirstEdition(), "请输入首版");
			param.add(ig);
			ig = InputGroup.getInGroup("再&#12288&#12288版", "reprint", coe.getReprint()==null?":":coe.getReprint(), "请输入再版");
			param.add(ig);*/
			
			
			//纸海图、源数据、电子海图--首版、再版
			InputHidden hiddenId1=null;
			InputHidden hiddenId2=null;
			InputHidden hiddenId3=null;
			InputHidden zhttype=null;
			InputHidden ysjtype=null;
			InputHidden dzhttype=null;
			
			Prop prop = new Prop("style","margin-top:10px;");
			Prop prop1 = new Prop("style","margin-top:50px;");	
			for (int i=0;i<coeList.size();i++) {
		
			String type_Name= coeList.get(i).getType().getValue();
		
			if("纸海图".equals(type_Name)) {
				
			
				ig = InputGroup.getInGroup("纸海图 &#12288首版", "zhtfirstEditionalter", coeList.get(i).getFirstEdition()==null?"":coeList.get(i).getFirstEdition(), "请输入纸海图 首版");
				ig.addProp(prop);
				param.add(ig);
				ig = InputGroup.getInGroup("纸海图 &#12288再版", "zhtreprintalter", coeList.get(i).getReprint()==null?":":coeList.get(i).getReprint(), "请输入纸海图 再版");
				ig.addProp(prop);
				param.add(ig);
				
			}else if("源数据".equals(type_Name)) {
				BaseData type = coeList.get(i).getType();
				Base selectgroup = util.createHidden("ysjtype", type.getId());
				param.add(selectgroup);
				
				
				
				ig = InputGroup.getInGroup("源数据 &#12288首版", "ysjfirstEditionalter",  coeList.get(i).getFirstEdition()==null?"":coeList.get(i).getFirstEdition(), "请输入源数据 首版");
				ig.addProp(prop);
				param.add(ig);
				ig = InputGroup.getInGroup("源数据 &#12288再版", "ysjreprintalter", coeList.get(i).getReprint()==null?":":coeList.get(i).getReprint(), "请输入源数据 再版");
				ig.addProp(prop);
				param.add(ig);
				
			}else if("电子海图".equals(type_Name)) {
				
				
					
			ig = InputGroup.getInGroup("电子海图 &#12288首版", "dzhtfirstEditionalter",  coeList.get(i).getFirstEdition()==null?"":coeList.get(i).getFirstEdition(), "请输入电子海图 首版");
			ig.addProp(prop);
			param.add(ig);
			ig = InputGroup.getInGroup("电子海图 &#12288再版", "dzhtreprintalter", coeList.get(i).getReprint()==null?":":coeList.get(i).getReprint(), "请输入电子海图 再版");
			ig.addProp(prop);
			param.add(ig);
			}
			
			BaseData type = coeList.get(i).getType();
			
			if("纸海图".equals(type_Name)) {
				
				 hiddenId1 = InputHidden.getInstance("zhtcoeId", coeList.get(i).getId());
				 zhttype = InputHidden.getInstance("zhttype", type.getId());
				 
				
			}else if("源数据".equals(type_Name)) {
				
				hiddenId2 = InputHidden.getInstance("ysjcoeId", coeList.get(i).getId());
				ysjtype = InputHidden.getInstance("ysjtype", type.getId());
			
			
			}else if("电子海图".equals(type_Name)) {
				 hiddenId3 = InputHidden.getInstance("dzhtcoeId", coeList.get(i).getId());
				 dzhttype = InputHidden.getInstance("dzhttype", type.getId());
				
			}
			

			}
			
			
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "调整系数编辑");
			InputHidden hiddenId = InputHidden.getInstance("coeId", id);
			editPage.addChildNode(hiddenId);
			editPage.addChildNode(hiddenId1);
			editPage.addChildNode(hiddenId2);
			editPage.addChildNode(hiddenId3);
			editPage.addChildNode(zhttype);
			editPage.addChildNode(ysjtype);
			editPage.addChildNode(dzhttype);
			
		}else{
			Base selectgroup = util.creatDefaultSelectGroup("调整系数类型", "type", seamapList, "id", "value",false);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", null, "请输入图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288&#12288&#12288名", "mapName", null, "请输入图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)", "scale", "", "请输入比例尺");
			param.add(ig);
			
		
			

			Prop prop = new Prop("style","margin-top:10px;");
			Prop prop1 = new Prop("style","margin-top:50px;");	
			ig = InputGroup.getInGroup("纸海图 &#12288首版", "zhtfirstEdition", null, "请输入纸海图 首版");
			ig.addProp(prop1);
			param.add(ig);
						
			ig = InputGroup.getInGroup("纸海图 &#12288再版", "zhtreprint", null, "请输入纸海图 再版");
			ig.addProp(prop1);
			param.add(ig);
			
			
			ig = InputGroup.getInGroup("源数据 &#12288首版", "ysjfirstEdition", null, "请输入源数据 首版");
			ig.addProp(prop);
			param.add(ig);
			ig = InputGroup.getInGroup("源数据 &#12288再版", "ysjreprint", null, "请输入源数据 再版");
			ig.addProp(prop);
			param.add(ig);
			
			
			ig = InputGroup.getInGroup("电子海图 &#12288首版", "dzhtfirstEdition", null, "请输入电子海图 首版");
			ig.addProp(prop);
			param.add(ig);
			ig = InputGroup.getInGroup("电子海图 &#12288再版", "dzhtreprint", null, "请输入电子海图 再版");
			ig.addProp(prop);
			param.add(ig);
			
			
			
			
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "调整系数创建");
			InputHidden hiddenId = InputHidden.getInstance("coeId", id);
			editPage.addChildNode(hiddenId);
			}
			return editPage.getNode();
	}
}