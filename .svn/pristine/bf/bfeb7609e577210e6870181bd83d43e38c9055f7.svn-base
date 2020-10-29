package com.ht.front.pages.catalog.type;

import java.util.ArrayList;
import java.util.List;

import com.ht.exception.DBException;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.template.EditPage;
import com.ht.front.template.ListPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;

/** 
* @ClassName: CatalogTypePage 
* @Description: 目录类型页面初始化类
* @author wangxin
* @date 2016年10月15日 下午1:00:00 
*  
*/
public class CatalogTypePage {

	/**
	 * 
	 * 初始化目录类型页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createListPage("", "type");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, "目录类别管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editType");
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
	 * 初始化编辑目录信息页面
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditNode(BaseDataService service,String id) throws Exception {
		// 获取目录类型数据
		try {
			InputGroup tb=null;
			FrontUtil util = FrontUtil.getInstance();
			BaseData type = service.getBaseData(id);
			EditPage edit = new EditPage();
			List<Base> param = new ArrayList<Base>();
			Base editPage = null;
			if(null == id){
				 tb = InputGroup.getInGroup("类型编码", "code",null, "请输入类型编码");
				 param.add(tb);
				 tb = InputGroup.getInGroup("类型名称", "value",null, "请输入类型名称");
				 param.add(tb);
				 editPage = edit.createEditPage(param);
				 util.createHeaderBar(editPage, "目录类别创建");
			}else{
				 tb = InputGroup.getInGroup("类型编码", "code",type.getCode(), "请输入编码名称");
				 param.add(tb);
				 tb = InputGroup.getInGroup("类型名称", "value",type.getValue(), "请输入类型名称");
				 param.add(tb);
				 editPage = edit.createEditPage(param);
				 util.createHeaderBar(editPage, "目录类别编辑");
			}
			InputHidden hidden = InputHidden.getInstance("typeId", id);
			editPage.addChildNode(hidden);
			//返回节点字符串
			return editPage.getNode();
		} catch (DBException e) {
			return e.getMessageCode();
		}
	}

}
