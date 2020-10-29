package com.ht.service.impl.background.dicdata.scalecontrol;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.scalecontrol.ScaleControlDao;
import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;
import com.ht.service.inter.background.dicdata.scalecontrol.ScaleControlService;

/**
 * 比例尺Service实现类
 * @author houchen
 *
 */
public class ScaleControlServiceImpl implements ScaleControlService {
	
	/**
	 * 注入比例尺Dao
	 */
	@Resource(name="scaleControlDao")
	ScaleControlDao scaleControlDao;
	
	/**
	 * 添加，更新比例尺
	 */
	@Override
	public void addScaleControl(String scaleControlParam) throws Exception {
		try {
			// 将JSON转为对象
			ScaleControl scaleControl = (ScaleControl) DataConverter.convertJson2Object(scaleControlParam, ScaleControl.class);
			// 如果Id不为空，则进行更新
			if(scaleControl.getId() != null) {
				// 执行更新
				scaleControlDao.modifyScaleControl(scaleControl);
			}else {
				// 如果Id为空，则进行添加
				scaleControl.setId(GenerateSequenceUtil.generateSequenceNo());
				// 执行添加
				scaleControlDao.addScaleControl(scaleControl);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
		
	}
	
	/**
	 * 更新比例尺
	 */
	public void modifyScaleControl(String scaleControlParam) throws Exception{
		// TODO Auto-generated method stub
	}
	
	/**
	 * 删除比例尺
	 */
	@Override
	public void deleteScaleControl(String scaleControl) throws Exception {
		try {
			List<ScaleControl> list = (List<ScaleControl>) DataConverter.convertJson2List(scaleControl,ScaleControl.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除比例尺
				scaleControlDao.deleteScaleControl(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有比例尺
	 */
	@Override
	public List<ScaleControl> getScaleControl() throws Exception {
		try {
			// 获取所有比例尺
			return scaleControlDao.getScaleControl();
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取一条比例尺
	 */
	@Override
	public ScaleControl getScaleControl(String id) throws Exception {
		try {
			ScaleControl scaleControl = new ScaleControl();
			scaleControl.setId(id);
			// 根据id获取图书资料
			return scaleControlDao.getScaleControl(scaleControl);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 导出基础数据
	 * @param lists
	 * @return 
	 * @throws Exception
	 */
	@Override
	public void export() throws Exception {
		List<ScaleControl> scaleControlList = getScaleControl();
	    ExcelUtil<ScaleControl> util1 = new ExcelUtil<ScaleControl>(ScaleControl.class);  
	    util1.getListToExcel(scaleControlList, "标准比例尺","标准比例尺导出");  
	   }
}
