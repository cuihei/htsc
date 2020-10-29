package com.ht.service.impl.background.dicdata.basedata;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;

/**
 * 基础数据类service实现
 * @author 侯晨
 *
 */
public class BaseDataServiceImpl implements BaseDataService {
	
	//注入baseDataDao
	@Resource(name = "baseDataDao") 
	private BaseDataDao baseDataDao;
	
	/**
	 * 新增基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void addBaseData(String baseDataParam,String userName) throws Exception {
		try {
			//将json转换为baseData对象
			BaseData baseData = (BaseData) DataConverter.convertJson2Object(baseDataParam, BaseData.class);
			//添加BaseData
			baseData.setId(GenerateSequenceUtil.generateSequenceNo());
			baseDataDao.addBaseData(baseData);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}
	
	
	/**
	 * 新增基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void addBaseData(String baseDataParam) throws Exception {
		try {
			//将json转换为baseData对象
			BaseData baseData = (BaseData) DataConverter.convertJson2Object(baseDataParam, BaseData.class);
			//添加BaseData
			baseData.setId(GenerateSequenceUtil.generateSequenceNo());
			baseDataDao.addBaseData(baseData);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 修改基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void modifyBaseData(String baseDataParam) throws Exception {
		try {
			//将json转换为baseData对象
			BaseData baseData = (BaseData) DataConverter.convertJson2Object(baseDataParam, BaseData.class);
			// 更新baseData
			baseDataDao.modifyBaseData(baseData);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 修改基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void modifyBaseDataById(String baseDataParam,String userName) throws Exception {
		try {
			//将json转换为baseData对象
			BaseData baseData = (BaseData) DataConverter.convertJson2Object(baseDataParam, BaseData.class);
			baseData.setModifyDate(new Date());
			//根据ID查询
			BaseData bData = baseDataDao.getBaseData(baseData);
			if (bData!=null) {
				//设置创建时间
				baseData.setCreationDate(bData.getCreationDate());
				baseData.setCreator(bData.getCreator());
			}
			//设置更新者
			baseData.setModifier(userName);
			baseDataDao.evict(bData);
			// 更新baseData
			baseDataDao.modifyBaseData(baseData);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void delBaseData(String typeParam) throws Exception {
		try {
			 @SuppressWarnings("unchecked")
			 List<BaseData> list = (List<BaseData>) DataConverter.convertJson2List(typeParam, BaseData.class);
				// 删除baseData
			 for(BaseData bd :list){
				 baseDataDao.delBaseData(bd);
			 }
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询所有基础数据
	 * @throws Exception
	 */
	@Override
	public List<BaseData> getBaseData() throws Exception {
		try {
			// 获取所有baseData
			return baseDataDao.getBaseData();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询一条基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public BaseData getBaseData(String baseDataParam) throws Exception {
		try {
			BaseData baseData = new BaseData();
			baseData.setId(baseDataParam);
			// 根据id获取BaseData
			return baseDataDao.getBaseData(baseData);
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 导出
	 * 
	 * @throws Exception
	 */ 
	@Override
	public void export() throws Exception {
		List<BaseData> list = baseDataDao.getBaseData(); 
	    ExcelUtil<BaseData> util1 = new ExcelUtil<BaseData>(BaseData.class);  
	    util1.getListToExcel(list, "目录类别","目录类别导出");  
	   }

	/**
	 * 根据基础数据类别获取基础数据列表
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public List<BaseData> getBaseDataByType(String type) {
		BaseData data = new BaseData();
		data.setTypeName(type);
		return baseDataDao.getBaseDataByType(data);
	}
	

	/**
	 * 根据基础数据类别ID获取基础数据列表
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public List<BaseData> getBaseDataByTypeId(String typeId) {
		BaseData data = new BaseData();
		data.setTypeId(typeId);
		return baseDataDao.getBaseDataByTypeId(data);
	}
	
	/**
	 * 根据基础数据code获取基础数据列表
	 * @param baseDataParam 
	 * @throws Exception
	 */
	@Override
	public List<BaseData> getBaseDataById(String id) {
		BaseData data = new BaseData();
		data.setId(id);
		return baseDataDao.getBaseDataById(data);
	}
	
	/**
	 * 根据value和typeId获取一条BaseData
	 * @param baseDataParam 
	 * @throws Exception
	 */
	@Override
	public BaseData getDataByValue(String typeId,String value) {
		BaseData data = new BaseData();
		data.setTypeId(typeId);
		data.setValue(value);
		return baseDataDao.getDataByValue(data);
	}
}
