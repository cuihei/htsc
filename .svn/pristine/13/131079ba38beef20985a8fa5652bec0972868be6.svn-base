package com.ht.front.template;

import java.util.List;

import com.ht.front.model.Base;
import com.ht.front.util.FrontUtil;

/**
 * 编辑页面
 * @author 王有为
 * @date 2016/10/14
 */
public class EditPage {
	
	public Base createEditPage(List<Base> groupList){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 当行数大于5时 循环 一行俩列 
		Boolean isNewRow = false;
		Base row = null;
		for (int i = 0; i < groupList.size(); i++) {
			if (groupList.size() > 5)
			{
				if (i%2 == 0)
				{
					isNewRow = true;
				}
				else {
					isNewRow = false;	
				}
				if (isNewRow)
				{
					// 将行放入到容器中
					root.addChildNode(rowSpace);
					// 创建行
					row = util.createRow(root);
				}
			}
			else{
				// 将行放入到容器中
				root.addChildNode(rowSpace);
				// 创建行
				row = util.createRow(root);
			}
			// 创建列
			Base column = util.createColumn(row,"6","6","6",null);
			// 将组件放入到列
			column.addChildNode(groupList.get(i));
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		Base column = util.createColumn(rowOperation, "12", "12", "12", null);
		// 创建确定按钮
		util.createSubmitButton(column);
		// 创建取消按钮
		util.createCancelButtonBk5(column);
		// 将操作行添加到div
		root.addChildNode(rowSpace);
		root.addChildNode(rowSpace);
		root.addChildNode(rowOperation);
		return root;
	}
	
	public Base createRowEditPage(List<Base> groupList){
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		Base rowSpace = util.createRowSpace();
		for (int i = 0; i < groupList.size(); i++) {
			Base row = util.createRow();
			Base column = util.createColumn(row,"8","8","8",null);
			column.addChildNode(groupList.get(i));
			root.addChildNode(rowSpace);
			root.addChildNode(rowSpace);
			root.addChildNode(row);
		}
		Base rowOperation = util.createRow();
		Base column = util.createColumn(rowOperation, "12", "12", "12", null);
		util.createSubmitButton(column);
		util.createCancelButtonBk5(column);
		root.addChildNode(rowSpace);
		root.addChildNode(rowSpace);
		root.addChildNode(rowOperation);
		return root;
	}
}
