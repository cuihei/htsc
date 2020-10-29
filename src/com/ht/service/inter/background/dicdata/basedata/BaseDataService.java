package com.ht.service.inter.background.dicdata.basedata;

import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;

public interface BaseDataService {
  /**
   * 保存基础数据
   * @param baseDataParam json对象
   * @throws Exception
   */
  public void addBaseData(String baseDataParam,String userName) throws Exception;
  
  public void addBaseData(String baseDataParam) throws Exception;
  
  /**
   * 修改基础数据
   * @param baseDataParam json对象
   * @throws Exception
   */
  public void modifyBaseData(String baseDataParam) throws Exception;
  
	/**
	 * 修改基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	public void modifyBaseDataById(String baseDataParam,String userName) throws Exception;
  
  /**
   * 删除基础数据
   * @param baseDataParam json对象
   * @throws Exception
   */
  public void delBaseData(String typeParam) throws Exception;
  
  /**
   * 查询基础数据
   * @return List 查询到的集合
   * @throws Exception
   */
  public List<BaseData> getBaseData() throws Exception;
  
  /**
   * 查询一条基础数据
   * @param baseDataParam json对象
   * @return BaseData 查询到的对象
   * @throws Exception
   */
  public BaseData getBaseData(String baseDataParam) throws Exception;
  
  /**
   * 导出
   * @throws Exception
   */
  public void export() throws Exception;

  /**
   * 根据类别获取基础数据列表
   * @param
   * @return
   */
  public List<BaseData> getBaseDataByType(String type);
  
  /**
   * 根据基础数据类别ID获取基础数据列表
   * @param baseDataParam json对象
   * @throws Exception
   */
  public List<BaseData> getBaseDataByTypeId(String typeId) ;
  
  /**
   * 根据基础数据code获取基础数据
   * @param baseDataParam json对象
   * @throws Exception
   */
  public List<BaseData> getBaseDataById(String id) ;

  /**
   * 根据value和typeId获取一条BaseData
   * @param 
   * @throws Exception
   */
  public BaseData getDataByValue(String typeId, String value);

  
}
