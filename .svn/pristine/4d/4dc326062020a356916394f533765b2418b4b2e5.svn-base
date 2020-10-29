package com.ht.action.background.dicdata.scalecontrol;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.dicdata.scalecontrol.ScaleControlPage;
import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;
import com.ht.service.inter.background.dicdata.scalecontrol.ScaleControlService;

/**
 * 比例尺action
 * @author houchen
 *
 */
@SuppressWarnings("serial")
public class ScaleControlAction extends BaseAction {
	
	/**
	 * 注入比例尺service
	 */
	@Resource(name="scaleControlService")
	ScaleControlService scaleControlService;
	
	/**
	 * 初始化比例尺页面，返回成功列表页面
	 * @return
	 */
	public String init(){
		// 创建图书资料页面初始化类
		ScaleControlPage page = new ScaleControlPage();
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", page.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 初始化比例尺编辑页面
	 * @return 
	 */
	public String editInit(){
		try {
			// 接收前台传来的id
			String id = getParam("id");
			//根据id获得scaleControl对象
			ScaleControl scaleControl = scaleControlService.getScaleControl(id);
			// 创建海图页面初始化类
			ScaleControlPage page = new ScaleControlPage();
			// 将获取的节点字符串返回给前台页面
			request.setAttribute("html", page.getEditNode(scaleControl));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 获取所有海图
	 * @return
	 */
	public void getScaleControl(){
		try {
			// 执行查询操作
			List<ScaleControl> list = scaleControlService.getScaleControl();
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 添加比例尺
	 * @return
	 */
	public void addScaleControl(){
		try {
			// 获取参数
			String scaleControl = getParam("scaleControl");
			// 执行添加
			scaleControlService.addScaleControl(scaleControl);
			// 成功返回消息
			writeSuccessResult(scaleControl);
		} catch (Exception e) {
			// 错误返回消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除比例尺
	 */
	public void removeScaleControl(){
		// 获取参数
		String scaleControl = getParam("scaleControl");
		try {
			scaleControlService.deleteScaleControl(scaleControl);
			writeSuccessResult(scaleControl);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出
	 * @throws Exception 
	 */
	public void exportExcel() throws Exception{
		scaleControlService.export();
	}
}
