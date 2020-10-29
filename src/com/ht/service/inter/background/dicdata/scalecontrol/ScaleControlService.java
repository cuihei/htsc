package com.ht.service.inter.background.dicdata.scalecontrol;

import java.util.List;

import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;


/**
 * 比例尺Service
 * @author houchen
 *
 */
public interface ScaleControlService {
	
	/**
	 * 保存比例尺
	 * @param ScaleControl
	 * @throws Exception
	 */
	public void addScaleControl(String scaleControl) throws Exception;
	
	/**
	 * 修改比例尺
	 * @param ScaleControl
	 * @throws Exception
	 */
	public void modifyScaleControl(String scaleControl) throws Exception;
	
	/**
	 * 删除比例尺
	 * @param id
	 * @throws Exception
	 */
	public void deleteScaleControl(String scaleControl) throws Exception;
	
	/**
	 * 获取所有比例尺
	 * @return
	 * @throws Exception
	 */
	public List<ScaleControl> getScaleControl() throws Exception;
	
	/**
	 * 获取一条比例尺
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScaleControl getScaleControl(String id) throws Exception;
	
	/**
	 * 导出基础数据
	 * @param lists
	 * @return 
	 * @throws Exception
	 */
	void export() throws Exception;
}
