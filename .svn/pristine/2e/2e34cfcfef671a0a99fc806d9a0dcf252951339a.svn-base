package com.ht.action.background.dicdata.basedata;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;


/**
 * baseData 基础数据类控制器
 * @author liukai
 */
@SuppressWarnings("serial")
public class BaseDataAction extends BaseAction{
	
	//注入基础数据baseDataService
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	/**
	 * 返回页面
	 * @return 页面
	 */
	public String index(){
		return "success";
	}
	
	/**
	 * 新增基础数据数据
	 */
	public void addBaseData(){
		try {
			//获取baseDataParam对象
			String baseData = getParam("baseData");
			//将baseDataParam对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			//执行新增方法
			baseDataService.addBaseData(baseDataParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新basedata数据
	 */
	public void modifyBaseData(){
		try {
			// 获取baseData
			String baseData = getParam("baseData");
			//将baseData对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			//执行修改方法
			baseDataService.modifyBaseData(baseDataParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除BaseData数据
	 */
	public void removeBaseData(){
		try {
			// 获取基础数据标识
			String id = getParam("id");
			// 创建BaseData对象
			BaseData baseData = new BaseData(); 
			baseData.setId(id);
			//将auth对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			baseDataService.delBaseData(baseDataParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取基础数据列表
	 */
	public void getBaseData(){
		try {
			// 创建baseData对象
			BaseData baseData = new BaseData(); 
			//将baseData对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			//执行查询方法
			List<BaseData> baseDataList = baseDataService.getBaseData();
			writeSuccessResult(baseDataList);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取基础数据
	 */
	public void getBaseDataByKey(){
		try {
			String id = getParam("id");
			// 创建baseData对象
			BaseData bd = new BaseData();
			bd.setId(id);
			//将baseData对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(bd);
			//执行查询方法
			BaseData baseData = baseDataService.getBaseData(baseDataParam);
			writeSuccessResult(baseData);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
}
