package com.ht.front.pages.system.workflow.publish;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.Script;
import com.ht.front.util.FrontUtil;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;

/**
 * 初始化工作流转日志视图页面
 * @author huodesheng
 * @date 2016/10/28
 */
public class VProcessDetailPage {
	FrontUtil util = null;

	Base root = null;

	public VProcessDetailPage() {
		// 获取前端工具实例
		util = FrontUtil.getInstance();
		 root = util.createRoot();
		 util.createHeaderBar(root, "工作流转");
	}
	
	public String getPage(VProcessDetailService vProcessDetailService,String userNo){
		
		this.createGrid("table1");
		
		return root.getNode();
	}
	/**
	 * 创建列表模块
	 * 
	 * @param listName
	 *            div的名字
	 */
	@SuppressWarnings("unused")
	private void createGrid(String listName) {
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行 开始 */
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12", "12");
		// 创建Grid
		util.createGrid(columnGrid, listName);
		/** 创建Grid行 结束 */
		// 将行加入到容器
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
	}
	/**
	 * 在script中放入按钮的html。
	 * 
	 * @param btnCss 按钮的css
	 * @param iCss	i标签的css
	 * @param btnName 按钮的name值
	 * @param scriptId	按钮所在的script的id
	 * @return
	 */
	public String createButtonInScript(CssClass btnCss, CssClass iCss,
			String btnName, String scriptId) {
		// 创建属性组件
		Prop prop = new Prop();
		// 创建按钮
		Button btn = Button.getButtonWithIcon(null, btnCss, null,
				I.getInstance(iCss));
		// 为按钮设置属性
		prop.setPropKey("name");
		prop.setPropValue(btnName);
		// 为按钮添加属性
		btn.addProp(prop);
	
		// 创建scrpit模块
		Script script = Script.getInstance(scriptId);
		// 将按钮添加到script中。
		script.addChildNode(btn);
		return script.getNode();
	}
}
