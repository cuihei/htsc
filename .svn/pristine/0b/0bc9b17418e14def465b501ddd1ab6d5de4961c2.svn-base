package com.ht.service.impl.system.workDays;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationWorkDaysDao;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;
import com.ht.service.inter.system.workDays.CompilationWorkDaysService;

public class CompilationWorkDaysServiceImpl implements CompilationWorkDaysService{
	
	/**
	 * 注入工天管理Dao
	 * */
	@Resource
	private CompilationWorkDaysDao workDaysDao;

	/**
	 *额定工天list
	 * */
	@Override
	public List<CompilationWorkDays> getWorkDaysList() {
		List<CompilationWorkDays> list = workDaysDao.getWorkDaysList();
		return list;
	}
	
	/**
	 *实际工天list
	 * */
	@Override
	public List<CompilationRealWorkDays> getRealWorkDaysList() {
		List<CompilationRealWorkDays> list = workDaysDao.getRealWorkDaysList();
		return list;
	}
	
	/**
	 *保存
	 * */
	@Override
	public void modify(String workDays,String flag) throws Exception {
		if("planWorkDays".equals(flag)){
			//额定工天
			CompilationWorkDays cw =(CompilationWorkDays)DataConverter.convertJson2Object(workDays, CompilationWorkDays.class);
			/*if(ifExistWorkDays(flag,cw.getMapNo())){
				DBException e = new DBException("图号已经存在！");
				throw e;
			}*/
			if(!cw.getId().isEmpty()){
				//更新
				workDaysDao.updateWorkDays(cw);
			}else{
				//新增
				cw.setId(GenerateSequenceUtil.generateSequenceNo());
				workDaysDao.modifyWorkDays(cw);
			}
		}else if("realWorkDays".equals(flag)){
			//实际工天
			CompilationRealWorkDays crw =(CompilationRealWorkDays)DataConverter.convertJson2Object(workDays, CompilationRealWorkDays.class);
			/*if(ifExistRealWorkDays(flag,crw.getMapNo())){
				DBException e = new DBException("图号已经存在！");
				throw e;
			}*/
			if(!crw.getId().isEmpty()){
				//更新
				workDaysDao.updateRealWorkDays(crw);
			}else{
				//新增
				crw.setId(GenerateSequenceUtil.generateSequenceNo());
				workDaysDao.modifyRealWorkDays(crw);
			}
			
		}
	}
	
	/**
	 *删除
	 * */
	@Override
	public void delete(String workDays,String flag) {
		try {
			if("planWorkDays".equals(flag)){
				//额定工天
				@SuppressWarnings("unchecked")
				List<CompilationWorkDays> list = (List<CompilationWorkDays>) DataConverter.convertJson2List(workDays,CompilationWorkDays.class);
				for (CompilationWorkDays i:list) {
					// 删除
					workDaysDao.delWorkDays(i);
				}
			}else if("realWorkDays".equals(flag)){
				//实际工天
				@SuppressWarnings("unchecked")
				List<CompilationRealWorkDays> list = (List<CompilationRealWorkDays>) DataConverter.convertJson2List(workDays,CompilationRealWorkDays.class);
				for (CompilationRealWorkDays i:list) {
					// 删除
					workDaysDao.delRealWorkDays(i);
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 *实际工天 by id
	 * */
	@Override
	public CompilationRealWorkDays getRealWorkDaysListById(String id) {
		CompilationRealWorkDays crw = new CompilationRealWorkDays();
		crw.setId(id);
		CompilationRealWorkDays result = workDaysDao.getRealWorkDaysListById(crw);
		return result;
	}
	
	/**
	 *额定工天 by id
	 * */
	@Override
	public CompilationWorkDays getWorkDaysListById(String id) {
		CompilationWorkDays cw = new CompilationWorkDays();
		cw.setId(id);
		CompilationWorkDays result = workDaysDao.getWorkDaysListById(cw);
		return result;
	}
	
	/**
	 *校验额定工天图号是否已经存在
	 * */
	@Override
	public Boolean ifExistWorkDays(String flag,String mapNo) {
		CompilationWorkDays cw = new CompilationWorkDays();
		cw.setMapNo(mapNo);
		cw.setFlag(flag);
		Boolean result = workDaysDao.ifExistWorkDays(cw);
		return result;
	}
	
	/**
	 *校验实际工天图号是否已经存在
	 * */
	@Override
	public Boolean ifExistRealWorkDays(String flag,String mapNo) {
		CompilationRealWorkDays cw = new CompilationRealWorkDays();
		cw.setMapNo(mapNo);
		cw.setFlag(flag);
		Boolean result = workDaysDao.ifExistRealWorkDays(cw);
		return result;
	}
}
