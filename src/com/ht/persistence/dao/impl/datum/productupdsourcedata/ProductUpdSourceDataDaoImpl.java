package com.ht.persistence.dao.impl.datum.productupdsourcedata;

import java.util.ArrayList;
import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.productupdsourcedata.ProductUpdSourceDataDao;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.system.workflow.task.RuTask;

/**
 * ProductUpdSourceData 数据映射操作类
 * @author Mr_zyd
 * @date 2017/12/12
 */
public class ProductUpdSourceDataDaoImpl extends BaseDaoImpl implements ProductUpdSourceDataDao{

	/**
	 * 增加一个ProductUpdSourceData
	 * @param ProductUpdSourceData 实体
	 */
	@Override
	public void addProductUpdSourceData(ProductUpdSourceData productUpdSourceData) {
		this.save(productUpdSourceData);
	}

	/**
	 * 更新一个pusd
	 * @param pusd pusd实体
	 */
	@Override
	public void modifyPusd(ProductUpdSourceData pusd) {
		this.update(pusd);
	}
	
	/**
	 * 获取所有ProductUpdSourceData
	 * @return ProductUpdSourceData列表
	 */
	@Override
	public List<ProductUpdSourceData> getProductUpdSourceData() {
		try {
			@SuppressWarnings("unchecked")
			List<ProductUpdSourceData> result = this.findByNamedQuery("getProductUpdSourceDataView");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 根据id活获取数据
	 */
	@Override
	public ProductUpdSourceData getProductUpdSourceDataById(String id) {
		@SuppressWarnings("unchecked")
		List<ProductUpdSourceData> result = this.findByNamedQueryAndNamedParam("getProductUpdSourceDataById", "id", id);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 根据不同状态获取数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductUpdSourceData> getProductUpdSourceDataByStatus(String status,String userName) {
		try {
			String[] params = {"status","userName"};
			String[] values = {status,userName};
			
			List<ProductUpdSourceData> result = new ArrayList<ProductUpdSourceData>();
			
			if(status.equals("1")){
				result = this.findByNamedQueryAndNamedParam("getPusdByStatusAndBhy", params, values);
			}else if(status.equals("2")){
				result = this.findByNamedQueryAndNamedParam("getPusdByStatusAndZjy", params, values);
			}else if(status.equals("3")){
				result = this.findByNamedQueryAndNamedParam("getPusdByStatusAndSdy", params, values);
			}
			
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


}
