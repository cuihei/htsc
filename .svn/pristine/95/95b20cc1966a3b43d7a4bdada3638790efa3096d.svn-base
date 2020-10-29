package com.ht.persistence.dao.impl.statisticalanalysis;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationWorkDaysDao;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;

/**
 * CompilationWorkDays数据映射操作类
 * @author zyd
 *
 */
@SuppressWarnings("unchecked")
public class CompilationWorkDaysDaoImpl extends BaseDaoImpl implements CompilationWorkDaysDao {
	
	/**
	 * 获取工天总数
	 */
	@Override
	public int getCompilationWorkDaysSum(CompilationWorkDays cw) {
		// 执行查询
		String[] paramNames={"content","revision","mapNo"};
		String[] values={cw.getContent(),cw.getRevision(),cw.getMapNo()};
		List<Integer> result = this.findByNamedQueryAndNamedParam("getCompilationWorkDaysSum", paramNames, values);
		if (result.size()>0) {
			return result.get(0);
		}
		return 0;
	}
	
	/**
	 * 获取工天计划列表
	 */
	@Override
	public List<CompilationWorkDays>  getCompilationWorkDaysList(CompilationWorkDays cw) {
		// 执行查询
		String[] paramNames={"content","revision","mapNo"};
		String[] values={cw.getContent(),cw.getRevision(),cw.getMapNo()};
		List<CompilationWorkDays> result = this.findByNamedQueryAndNamedParam("getCompilationWorkDaysList", paramNames, values);
		return result;
	}
	
	/**
	 * 实际工天list
	 */
	@Override
	public List<CompilationRealWorkDays> getRealWorkDaysList() {
		// 执行查询
		List<CompilationRealWorkDays> result = this.findByNamedQuery("getRealWorkDaysList");
		return result;
	}
	
	/**
	 * 额定工天list
	 */
	@Override
	public List<CompilationWorkDays> getWorkDaysList() {
		// 执行查询
		List<CompilationWorkDays> result = this.findByNamedQuery("getWorkDaysList");
		return result;
	}
	
	/**
	 * 额定工天保存
	 */
	@Override
	public void modifyWorkDays(CompilationWorkDays compilationWorkDays) {
		this.save(compilationWorkDays);
	}
	
	/**
	 * 实际工天保存
	 */
	@Override
	public void modifyRealWorkDays(CompilationRealWorkDays compilationRealWorkDays) {
		this.save(compilationRealWorkDays);
	}
	
	/**
	 * 额定工天删除
	 */
	@Override
	public void delWorkDays(CompilationWorkDays compilationWorkDays) {
		this.delete(this.load(CompilationWorkDays.class, compilationWorkDays.getId()));
	}
	
	/**
	 * 实际工天删除
	 */
	@Override
	public void delRealWorkDays(CompilationRealWorkDays compilationRealWorkDays) {
		this.delete(this.load(CompilationRealWorkDays.class, compilationRealWorkDays.getId()));
	}
	
	/**
	 * 实际工天by id
	 */
	@Override
	public CompilationRealWorkDays getRealWorkDaysListById(CompilationRealWorkDays crw) {
		// 执行查询
		List<CompilationRealWorkDays> result = this.findByNamedQueryAndNamedParam("getRealWorkDaysListById", "id", crw.getId());
		return result.get(0);
	}
	
	/**
	 * 额定工天by id
	 */
	@Override
	public CompilationWorkDays getWorkDaysListById(CompilationWorkDays cw) {
		// 执行查询
		List<CompilationWorkDays> result = this.findByNamedQueryAndNamedParam("getWorkDaysListById", "id", cw.getId());
		return result.get(0);
	}
	
	/**
	 * 额定工天更新
	 */
	@Override
	public void updateWorkDays(CompilationWorkDays compilationWorkDays) {
		this.update(compilationWorkDays);
	}
	
	/**
	 * 实际工天更新
	 */
	@Override
	public void updateRealWorkDays(CompilationRealWorkDays compilationRealWorkDays) {
		this.update(compilationRealWorkDays);
	}
	

	/**
	 * 校验额定工天图号是否已经存在
	 */
	@Override
	public Boolean ifExistWorkDays(CompilationWorkDays compilationWorkDays) {
		List<CompilationWorkDays> result = this.findByNamedQueryAndNamedParam("ifExistWorkDays", "mapNo",compilationWorkDays.getMapNo());
		if(result.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 *  校验实际工天图号是否已经存在
	 */
	@Override
	public Boolean ifExistRealWorkDays(CompilationRealWorkDays CompilationRealWorkDays) {
		List<CompilationRealWorkDays> result = this.findByNamedQueryAndNamedParam("ifExistRealWorkDays", "mapNo",CompilationRealWorkDays.getMapNo());
		if(result.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public CompilationRealWorkDays getRealWorkDaysListByMapNo(
			CompilationRealWorkDays c) {
		String param[] = {"mapNo","content"};
		String values[] = {c.getMapNo(),c.getContent()};
		List<CompilationRealWorkDays> result = this.findByNamedQueryAndNamedParam("getRealWorkDaysListByMapNo", param,values);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public CompilationWorkDays getWorkDaysListByMapNo(
			CompilationWorkDays c) {
		String param[] = {"mapNo","content"};
		String values[] = {c.getMapNo(),c.getContent()};
		List<CompilationWorkDays> result = this.findByNamedQueryAndNamedParam("getCompilationWorkDaysByMapNo", param,values);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	
}
