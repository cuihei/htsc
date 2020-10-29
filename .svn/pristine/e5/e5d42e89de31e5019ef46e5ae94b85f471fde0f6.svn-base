package com.ht.service.inter.datum.productupdsourcedata;

import java.util.List;
import java.util.Map;

import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;

/**
 * 产品修改源数据service
 * @author Mr_zyd
 * @date 2017/12/12
 */
public interface ProductUpdSourceDataService {

	/**
	 *保存pusd
	 * @param ProductUpdSourceData 实体
	 * @return 
	 */
	public String addProductUpdSourceData(String productUpdSourceData,String draw) throws Exception;

	/**
	 * 修改pusd
	 * @param pusd
	 * @return 
	 */
	public String modifyPusd(ProductUpdSourceData pusd) throws Exception;
	
	/**
	 *查询所有pusd
	 * @param string 
	 * @param ProductUpdSourceData 实体
	 */
	public List<ProductUpdSourceData> getProductUpdSourceData() throws Exception;

	/**
	 * 根据图号获取任务
	 * @param mapNo
	 * @param status 
	 * @param childTskType 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getPersonByMapNo(String mapNo, String childTskType, String status) throws Exception;

	/**
	 * 根据图号挂起流程
	 * @param flag 
	 * @param mapNo
	 * @return 
	 * @throws Exception
	 */
	public String suspended(String Id, String flag,String type) throws Exception;

	/**
	 * 根据状态和类型获取任务
	 * @param status
	 * @param type
	 * @return
	 */
	public List<CreateTask> getTaskListByStatusAndType(String status, String type);

	/**
	 * 通过id获取数据
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

	/**
	 * 通过图号和类型获取数据
	 * @param draw
	 * @param type
	 * @return
	 */
	public List<CreateTask> getTaskListByDrawAndType(String draw, String type);

}
