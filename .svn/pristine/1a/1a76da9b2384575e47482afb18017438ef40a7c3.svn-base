package com.ht.front.pages.drawtask.taskbill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ht.front.model.Base;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class TaskBillPage {
	
	/**
	 * 初始化编绘任务清单
	 * @return 节点字符串
	 * */
	public String getListNode(String flag) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		if(flag.equals("TASK_BOOK_CORRECTION_NOTICE")){
			util.createHeaderBar(root, "改正通告清单");
		}else if(flag.equals("TASK_BOOK_SEA_MAP")){
			util.createHeaderBar(root, "海图编绘清单");
		}else if(flag.equals("TASK_BOOK_SMALL_CORRECTION")){
			util.createHeaderBar(root, "海图小改正清单");
		}else if(flag.equals("TASK_BOOK_PROJECT_SPECIAL")){
			util.createHeaderBar(root, "工程&专题图清单");
		}
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"taskBill");
		InputHidden hiddenId = InputHidden.getInstance("flag",flag);
		root.addChildNode(hiddenId);
		/** 创建Grid行  结束*/
		//返回节点字符串
		return root.getNode();
	}

	public String getSeeNode(String id,TaskBookService taskBookService,OrganizationService organizationService) {
		List<Base> param = new ArrayList<Base>();
		Organization organization = null;
		TaskBook task = null;
		try {
			task = taskBookService.findById(id);
			String executeDeptId = task.getExecuteDeptId();
			organization = organizationService.getOrganization(executeDeptId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputGroup tb = InputGroup.getInGroup(true,"任务名称", "taskName", task.getTaskName(), "任务名称");
		param.add(tb);
		tb = InputGroup.getInGroup(true,"任务来源", "taskFrom", task.getTaskFrom(), "任务来源");
		param.add(tb);
		tb = InputGroup.getInGroup(true,"执行部门", "taskFrom", organization.getOrgName(), "执行部门");
		param.add(tb);
		SimpleDateFormat sdf = new SimpleDateFormat();
		String time = sdf.format(task.getExecuteTime());
		tb = InputGroup.getInGroup(true,"执行时间", "executeTime", time , "执行时间");
		param.add(tb);
		tb = InputGroup.getInGroup(true,"版次", "revision", task.getRevision(), "任务来源");
		param.add(tb);
		
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "编绘任务清单查看");
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < param.size(); i++) {
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row,"6","4","4","1");
			// 将组件放入到列
			column.addChildNode(param.get(i));
			// 将行放入到容器中
			root.addChildNode(rowSpace);
			root.addChildNode(rowSpace);
			root.addChildNode(row);
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建取消按钮列
		Base column = util.createColumn(rowOperation, "2","1","1","1");
		// 创建取消按钮
		util.createCancelButton(column);
		// 将操作行添加到div
		root.addChildNode(rowSpace);
		root.addChildNode(rowSpace);
		root.addChildNode(rowOperation);
		//返回节点字符串
		return root.getNode();
	}
	
}
