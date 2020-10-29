package com.ht.front.pages.complication.seamap.electron;

import java.util.List;

import com.ht.common.util.LogHelper;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.Script;
import com.ht.front.model.Templete;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.complication.formprop.FormFormValue;
import com.ht.service.inter.complication.formprop.FormFormValueService;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class ElectronPage {
	
	FrontUtil util = null;
	
	public ElectronPage(){
		util = FrontUtil.getInstance();
	}
	
	/**
	 * 初始化编绘任务清单海图编绘
	 * @return 节点字符串
	 * */
	public String getListNode() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, "工程&专题电子海图");
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"electron");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		//流转状态按钮
		CssClass css = new CssClass("fa fa-arrows-v");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("status");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("查看流转状态");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("statusPage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("status");
		script.addChildNode(tempelate);
		root.addChildNode(script);
		//详情按钮
		CssClass detailCss = new CssClass("fa fa-github-alt");
		I detailI = I.getInstance(detailCss);
		detailCss = new CssClass("btn btn-primary");
		Button detailTempelate = Button.getButtonWithIcon(null, detailCss, null, detailI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("details");
		detailTempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("查看详情");
		detailTempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("detailsPage(this)");
		detailTempelate.addProp(prop);
		Script detailScript = Script.getInstance("details");
		detailScript.addChildNode(detailTempelate);
		root.addChildNode(detailScript);
		//导出按钮
		CssClass exportCss = new CssClass("fa fa-paste");
		I exportI = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button exportTempelate = Button.getButtonWithIcon(null, exportCss, null, exportI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("export");
		exportTempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("导出");
		exportTempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("exportPage(this)");
		exportTempelate.addProp(prop);
		Script exportScript = Script.getInstance("export");
		exportScript.addChildNode(exportTempelate);
		root.addChildNode(exportScript);
		//返回节点字符串
		return root.getNode();
	}
	
	/**
	 * 初始化流转状态页面
	 * @return 节点字符串
	 * */
	public String getStatusPage() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, "海图编绘流转状态查看");
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"electronStatus");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		//返回节点字符串
		/*InputHidden hidden = InputHidden.getInstance("statusId", id);
		root.addChildNode(hidden);*/
		return root.getNode();
	}
	
	/**
	 * 初始化详情页面
	 * @return 节点字符串
	 * */
	public String getDetailsPage(FormFormValueService formFormValueService,String taskInstId,String processInstId) {
		Div rowDiv2 = null;
		try {
			CssClass rowCss2 = new CssClass("panel-body bk-bg-white bk-padding-off-top bk-padding-off-bottom");
			rowDiv2 = Div.getInstance(null,rowCss2, null);
			
			taskInstId = "1";
			processInstId = "2";
			//获取form列表
			List<FormFormValue> list = formFormValueService.getFormIdByTTIdAndPIId(taskInstId,processInstId);
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					FormFormValue ffv = list.get(i);
					//列表
					CssClass rowCss = new CssClass("row");
					Div rowDiv = Div.getInstance(null,rowCss, null);
					CssClass css = new CssClass("col-xs-9 bk-vcenter");
					Div div = Div.getInstance(null,css, null);
					CssClass pointCss = new CssClass("point point-success point-lg");
					Div pointDiv = Div.getInstance(null,pointCss, null);
					CssClass h4Css = new CssClass("bk-fg-danger bk-margin-off-bottom");
					CssClass hrCss = new CssClass("bk-margin-off");
					Templete h4 = Templete.getInstance("h4",h4Css);
					Templete hr = Templete.getInstance("hr",hrCss);
					Templete p = Templete.getInstance("p",null);
					
					String href="../source/index?id="+ffv.getId();
					A a = A.getInstance(href,ffv.getName());
					h4.addChildNode(pointDiv);
					h4.addChildNode(a);
					div = Div.getInstance(null,css, null);
					div.addChildNode(h4);
					div.addChildNode(p);
					rowDiv = Div.getInstance(null,rowCss, null);
					if(i != 0){
						rowDiv.addChildNode(hr);
					}
					rowDiv.addChildNode(div);
					rowDiv2.addChildNode(rowDiv);
				}
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
		}
		//返回节点字符串
		return rowDiv2.getNode();
	}
}
