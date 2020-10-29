package com.ht.front.pages.system.issue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ht.front.model.Span;
import com.ht.front.model.Templete;
import com.ht.front.model.TextBox;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.system.issue.YearIssue;
import com.ht.persistence.model.system.symbol.Symbol;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.system.issue.YearIssueService;
import com.ht.service.inter.system.symbol.SymbolService;

public class YearIssuePage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "改正通告期号时段管理");
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
		util.createGrid(root, "yearIssue");
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
		return root.getNode() + script.getNode();
	}
	
	/**
	 * 初始化新增、编辑页面
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public String getEditNode(YearIssueService yearIssueService,String id) throws Exception{

		EditPage edit = new EditPage();
		Base editPage = null;
		// 改正通告期号时段list
		YearIssue yearIssue = null;
		List<Base> param = new ArrayList<Base>();
		FrontUtil util = FrontUtil.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(id!=null){
			yearIssue = yearIssueService.getYearIssueById(id);
			Span span = Span.getDefault("年&#12288份");
			CssClass css = new CssClass("fa fa-calendar");
			I i = I.getInstance(css);
			span.addChildNode(i);
			TextBox text = TextBox.getDefault("year",yearIssue.getYear(), "年份");
			InputGroup ig = InputGroup.getInstance(span, text);
			param.add(ig);
			ig = InputGroup.getInGroup(false,"改正期号", "issue",yearIssue.getIssue(), "改正期号");
			param.add(ig);
			String beginDate = sdf.format(yearIssue.getBeginDate());
			String endDate = sdf.format(yearIssue.getEndDate());
			ig = (InputGroup) util.createYearMonthDatePickerGroup("起止日期", "beginDate",beginDate,"请输入开始日期", "endDate",endDate, "请输入结束日期");
			param.add(ig);
		}else{
			Span span = Span.getDefault("年&#12288份");
			CssClass css = new CssClass("fa fa-calendar");
			I i = I.getInstance(css);
			span.addChildNode(i);
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			TextBox text = TextBox.getDefault("year",String.valueOf(year), "年份");
			InputGroup ig = InputGroup.getInstance(span, text);
			param.add(ig);
			ig = InputGroup.getInGroup(false,"改正期号", "issue",year+"年第（）期", "改正期号");
			param.add(ig);
			ig = (InputGroup) util.createYearMonthDatePickerGroup("起止日期", "beginDate",null,"请输入开始日期", "endDate",null, "请输入结束日期");
			param.add(ig);
		}
		
		editPage = edit.createEditPage(param);
		
		InputHidden hiddenId = InputHidden.getInstance("id",id);
		editPage.addChildNode(hiddenId);
		return editPage.getNode();
	}
	
}
