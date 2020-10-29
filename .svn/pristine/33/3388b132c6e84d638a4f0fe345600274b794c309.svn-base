package com.ht.persistence.dao.inter.datum.productupdsourcedata;

import java.util.List;

import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;

/**
 * 产品修改源数据dao
 * @author Mr_zyd
 * @date 2017/12/12
 */
public interface ProductUpdSourceDataDao {
		
	/**
	 * 增加一条数据
	 * @param ProductUpdSourceData实体
	 */
	public void addProductUpdSourceData(ProductUpdSourceData productUpdSourceData);

	/**
	 * 修改pusd
	 * @param pusd
	 */
	public void modifyPusd(ProductUpdSourceData pusd);
	
	/**
	 * 获取所有ProductUpdSourceData
	 * @param ProductUpdSourceData 实体
	 */
	public List<ProductUpdSourceData> getProductUpdSourceData();

	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	public ProductUpdSourceData getProductUpdSourceDataById(String id);

	/**
	 * 根据不同状态获取数据
	 * @param status
	 * @param loginUser 
	 * @return
	 */
	public List<ProductUpdSourceData> getProductUpdSourceDataByStatus(String status, String userName);

}
