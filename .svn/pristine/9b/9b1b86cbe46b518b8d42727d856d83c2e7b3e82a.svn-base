package com.ht.action.background.dicdata.type;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.dicdata.type.TypeService;


/**
 * Type 类别数据action
 * @author liukai
 */
@SuppressWarnings("serial")
public class TypeAction extends BaseAction{
	
	/**
	 * 注入类别数据service
	 * */
	@Resource(name="typeService")
	TypeService typeService;
	
	/**
	 * 初始化类别数据页面，返回成功列表页面
	 * */
	public String index(){
		return "success";
	}
	
	/**
	 * 新增Type数据
	 */
	public void addType(){
		try {
			//获取前台传入参数
			String type = getParam("type");
			//将对象转换为JSON数据
			String typeParam = DataConverter.convertObject2Json(type);
			//执行保存操作
			typeService.addType(typeParam);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Type数据
	 */
	public void modifyType(){
		//获取前台传入参数
		String type = getParam("type");
		//将对象转换为JSON数据
		String typeParam = DataConverter.convertObject2Json(type);
		try {
			//执行修改操作
			typeService.modifyType(typeParam);
			//返回客户端消息
			writeSuccessResult(typeParam);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除Type数据
	 */
	public void removeType(){
		// 获取Type标识
		String id = getParam("id");
		try {
			//执行删除操作
			typeService.delType(id);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Type列表
	 */
	public void getType(){
		try {
			//执行查询操作
			List<Type> typeList = typeService.getType();
			//返回客户端消息
			writeSuccessResult(typeList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Type
	 */
	public void getTypeByKey(){
		// 获取Type标识
		String id = getParam("id");
		try {
			//执行查询操作
			Type type = typeService.getType(id);
			//返回客户端消息
			writeSuccessResult(type);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
