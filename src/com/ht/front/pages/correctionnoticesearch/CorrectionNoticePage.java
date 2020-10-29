package com.ht.front.pages.correctionnoticesearch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.correctionnotice.NoticeBaseData;
import com.ht.persistence.model.correctionnotice.NoticeYear;
import com.ht.service.inter.correctionnoticesearch.CorrectionNoticeService;

public class CorrectionNoticePage{

	FrontUtil util = null;
	
	Base root = null;
	
	public CorrectionNoticePage(){
		// 获取前端工具实例
		util = FrontUtil.getInstance();
	}
	
	/**
	 * 绘制页面
	 * @return
	 */
	public String getPage(CorrectionNoticeService correctionNoticeService,String gridName){
		//创建整个页面
		this.root = util.createRoot();
		util.createHeaderBar(root, "改正通告查询");
		//创建搜索模块
		this.createSearchPage(correctionNoticeService);
		//创建改正通告列表模块
		this.createNoticeGrid(gridName);
		//创建最新改正通告列表模块
		this.createNoticeGrid("newestNotice");
		// 返回整个页面
		return root.getNode();
	}
	/**
	 * 创建搜索模块
	 */
	private void createSearchPage(CorrectionNoticeService correctionNoticeService){
		//添加4个行间距
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row,"3","3", "3",null);
		// 创建列
		Base col2 = util.createColumn(row,"3","3", "3",null);
		// 创建列
		Base col3 = util.createColumn(row,"3","3", "3",null);
		// 创建列
		Base col4 = util.createColumn(row,"3","3", "3",null);
		//创建图号搜索框
		InputGroup mapNumInput = InputGroup.getInGroup("图&#12288&#12288号", "ChartNo",null, "请输入图号");
		col1.addChildNode(mapNumInput);
		//创建关键字搜索框
		InputGroup keyInput = InputGroup.getInGroup("关键字&#12288", "key",null, "请输入关键词,使用空格分隔");
		col2.addChildNode(keyInput);
		//创建通告类型搜索框
		List<NoticeBaseData> ddlTypeList=correctionNoticeService.getDdlTypeList();
		Base ddlType = util.creatDefaultSelectGroup("通告类型", "ddlType", ddlTypeList, "ID", "name", false);
		col3.addChildNode(ddlType);
		/*List<NoticeBaseData> ddlActList=correctionNoticeService.getDdlActList();
		Base ddlAct = util.creatDefaultSelectGroup("改正行为", "ddlAct", ddlActList, "ID", "name", false);
		col4.addChildNode(ddlAct);*/
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"2","2", "2",null);
		// 创建列
		col2 = util.createColumn(row,"2", "2","2",null);
		// 创建列
		col3 = util.createColumn(row,"2","2", "2",null);
		// 创建列
		col4 = util.createColumn(row,"2","2", "2",null);
		// 创建列
		Base col5 = util.createColumn(row,"2","2", "2",null);
		List<NoticeBaseData> list = new ArrayList<NoticeBaseData>();
		List<NoticeYear> yearList = correctionNoticeService.getYearList();
		for (int i = 0; i <yearList.size(); i++) {
			NoticeBaseData b = new NoticeBaseData();
			String str = yearList.get(i).getName();
			b.setID(str);
			b.setName(str);
			list.add(b);
		}
		//创建年份搜索框
		Base yearInput = util.creatDefaultSelectGroup("年&#12288&#12288份", "startTime", list, "ID", "name",false);
		col1.addChildNode(yearInput);
		//创建项号搜索框
		InputGroup itemNumInput1 = InputGroup.getInGroup("项&#12288&#12288号", "itemNo1",null, "请输入项号");
		col2.addChildNode(itemNumInput1);
		//创建年份搜索框
		Base yearInput2 = util.creatDefaultSelectGroup("年&#12288&#12288份", "endTime", list, "ID", "name", false);
		col3.addChildNode(yearInput2);
		//创建项号搜索框
		InputGroup itemNumInput2 = InputGroup.getInGroup("项&#12288&#12288号", "itemNo2",null, "请输入项号");
		col4.addChildNode(itemNumInput2);
		//创建搜索按钮
		Button searchButton = Button.getButtonWithIcon("search", new CssClass("btn btn-primary"), "查询", I.getInstance(new CssClass("fa fa-search")));
		col5.addChildNode(searchButton);
	}
	
	/**
	 * 创建列表模块(改正通告，和最新改正通告列表用一个模板)
	 * @param listName div的名字
	 */
	private void createNoticeGrid(String listName){
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,listName);
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
	}
}
