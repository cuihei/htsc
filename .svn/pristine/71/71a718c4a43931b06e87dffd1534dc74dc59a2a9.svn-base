package com.ht.front.pages.complication.seamap.source;

import java.util.List;

import com.ht.common.util.LogHelper;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Templete;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.complication.formprop.FormFormValue;
import com.ht.service.inter.complication.formprop.FormFormValueService;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class SourcePage {

	FrontUtil util = null;
	
	public SourcePage(){
		util = FrontUtil.getInstance();
	}
	
	/**
	 * 初始化编绘任务清单海图编绘
	 * @return 节点字符串
	 * */
	public String getListNode() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, "源数据编绘管理");
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"source");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		
		//返回节点字符串
		return root.getNode() + buildTemplate();
	}
	
	/**
	 * 初始化流转状态页面
	 * @return 节点字符串
	 * */
	public String getStatusPage() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, "源数据编绘流转状态查看");
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"sourceStatus");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		//返回节点字符串
		/*InputHidden process_inst = InputHidden.getInstance("process_inst_id", process_inst_id);
		InputHidden task = InputHidden.getInstance("task_id", task_id);
	    root.addChildNode(process_inst);
	    root.addChildNode(task);*/
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
	
	/**
	 * 初始化源数据采用登记列表页面
	 * @return 节点字符串
	 * */
	public String getAdoptPage(String id) {
		Div div = Div.getInstance("sourceAdopt", null, null);
		//返回节点字符串
		InputHidden hidden = InputHidden.getInstance("sourceId", id);
		div.addChildNode(hidden);
		return div.getNode();
	}
	
	/**
	 * 初始化源数据幅索引图数据
	 * @return 节点字符串
	 * */
	public String getIndexMapPage(String id) {
		CssClass css = new CssClass("form-group");
		Div div = Div.getInstance("sourceIndexMap", css, null);
		CssClass css2 = new CssClass("col-lg-2 col-md-3 col-sm-3 col-xs-8");
		Div div2 = Div.getInstance(null, css2, null);
		
		InputGroup ig = InputGroup.getInGroup("纬度", "areaName","aa",null);
		div2.addChildNode(ig);
		
		InputGroup ig2 = InputGroup.getInGroup("经度", "areaName","bb", null);
		div2.addChildNode(ig2);
		
		div.addChildNode(div2);
		
		InputHidden hidden = InputHidden.getInstance("sourceId", id);
		div.addChildNode(hidden);
		return div.getNode();
	}
	
	private String buildTemplate(){
		String scripts = "";
		Script script = Script.getInstance("operation");
		// 创建操作按钮
		CssClass css = new CssClass("fa fa-file-text-o");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("operation");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("操作");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		css = new CssClass("fa fa-eye");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		tempelate = Button.getButtonWithIcon(null, css, null, i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("formValue");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		scripts += script.getNode();
		// 创建审批按钮
		script = Script.getInstance("approve");
		css = new CssClass("fa fa-user");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info");
		tempelate = Button.getButtonWithIcon(null, css, null, i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("approve");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("审批");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		scripts += script.getNode();
		// 创建流转状态按钮
		script = Script.getInstance("flow");
		css = new CssClass("fa fa-exchange");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning");
		tempelate = Button.getButtonWithIcon(null, css, null, i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("flow");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("查看流转状态");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		scripts += script.getNode();
		return scripts;
	}
}
