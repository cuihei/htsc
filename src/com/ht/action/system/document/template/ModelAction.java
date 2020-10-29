package com.ht.action.system.document.template;

import java.util.List;
import javax.annotation.Resource;
import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.system.document.template.Model;
import com.ht.service.inter.system.document.template.ModelService;

/**
 * Model 模板类
 * @author 平子金
 */
@SuppressWarnings("serial")
public class ModelAction extends BaseAction{
	
	/**
	 * 注入通知service
	 * */
	@Resource(name="modelService")
	ModelService modelService;

	/**
	 * 初始化人员数据页面，返回成功列表页面
	 * */
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 新增Model数据
	 */
	public void addModel(){
		try {
			// 获取前台传入参数
			String model = getParam("model");
			// 执行保存操作
			modelService.addModel(model);
			// 返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Model数据
	 */
	public void modifyModel(){
		// 获取前台传入数据
		String model = getParam("model");
		// 将对象转换为Json数据
		String modelParam = DataConverter.convertObject2Json(model);
		try {
			modelService.modifyModel(modelParam);
			writeSuccessResult(modelParam);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除Model数据
	 */
	public void removeModel(){
		// 获取Model标识
		String id = getParam("id");
		try {
			modelService.delModel(id);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Model列表
	 */
	public void getModel(){
		try {
			List<Model> modelList = modelService.getModel();
			writeSuccessResult(modelList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Model
	 */
	public void getModelByKey(){
		try {
			String id = getParam("id");
			Model model = modelService.getModel(id);
			writeSuccessResult(model);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
}
