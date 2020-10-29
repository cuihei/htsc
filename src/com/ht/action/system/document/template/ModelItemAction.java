package com.ht.action.system.document.template;

import java.util.List;
import javax.annotation.Resource;
import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.system.document.template.ModelItem;
import com.ht.service.inter.system.document.template.ModelItemService;

/**
 * ModelItem 模板项类
 * @author 平子金
 * */
public class ModelItemAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	/**
	 * 注入通知service
	 * */
	@Resource(name="modelitemService")
	ModelItemService modelitemService;

	/**
	 * 初始化人员数据页面，返回成功列表页面
	 * */
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 新增ModelItem数据
	 */
	public void addModelItem(){
		try {
			// 获取前台传入参数
			String modelitem = getParam("modelitem");
			// 执行保存操作
			modelitemService.addModelItem(modelitem);
			// 返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新ModelItem数据
	 */
	public void modifyModelItem(){
		// 获取前台传入数据
		String modelitem = getParam("modeiItem");
		// 将对象转换为Json数据
		String modelitemParam = DataConverter.convertObject2Json(modelitem);
		try {
			modelitemService.modifyModelItem(modelitemParam);
			writeSuccessResult(modelitemParam);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除ModelItem数据
	 */
	public void removeModelItem(){
		// 获取ModelItem标识
		String id = getParam("id");
		try {
			modelitemService.delModelItem(id);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取ModelItem列表
	 */
	public void getModelItem(){
		try {
			List<ModelItem> modelitemList = modelitemService.getModelItem();
			writeSuccessResult(modelitemList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取ModelItem
	 */
	public void getModelItemByKey(){
		try {
			String id = getParam("id");
			ModelItem modelitem = modelitemService.getModelItem(id);
			writeSuccessResult(modelitem);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
