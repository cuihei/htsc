package com.ht.action.system.workDays;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.workDays.CompilationWorkDaysPage;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;
import com.ht.service.inter.system.workDays.CompilationWorkDaysService;

/**
 * 工天管理
 * @author yp
 *
 */
@SuppressWarnings("serial")
public class CompilationWorkDaysAction extends BaseAction{
	
	@Resource
	private  CompilationWorkDaysService  workDaysService;
	
	/**
	 * 首页面
	 * @return 首页面
	 */
	public String init(){
		String flag =  getParam("flag");
		CompilationWorkDaysPage cwp = new CompilationWorkDaysPage();
		request.setAttribute("html", cwp.getListNode(flag));
		return SUCCESS;
	}
	
	/**
	 * 编辑页面
	 * @return 编辑页面
	 * @throws Exception 
	 */
	public String initEdit() throws Exception{
		String id = getParam("id");
		String flag = getParam("flag");
		CompilationWorkDaysPage cwp = new CompilationWorkDaysPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cwp.getEditNode(workDaysService,flag,id));
		return SUCCESS;
	}
	
	/**
	 * 获取工天数据列表
	 */
	public void getWorkDaysList(){
		try {
			String flag = getParam("flag");
			//执行查询方法
			if("planWorkDays".equals(flag)){
				List<CompilationWorkDays> workDaysList = workDaysService.getWorkDaysList();
				if(workDaysList != null){
					if(workDaysList.size()>0){
						for (int i = 0; i < workDaysList.size(); i++)
						{
							String revision =workDaysList.get(i).getRevision();
							if(revision.equals("0")){
								workDaysList.get(i).setRevision("首版");
							}else if(revision.equals("1")){
								workDaysList.get(i).setRevision("再版");
							}
						}
					}
				}
				writeSuccessResult(workDaysList);
			}else if("realWorkDays".equals(flag)){
				List<CompilationRealWorkDays> realWorkDaysList = workDaysService.getRealWorkDaysList();
				if(realWorkDaysList != null){
					if(realWorkDaysList.size()>0){
						for (int i = 0; i < realWorkDaysList.size(); i++)
						{
							String revision =realWorkDaysList.get(i).getRevision();
							if(revision.equals("0")){
								realWorkDaysList.get(i).setRevision("首版");
							}else if(revision.equals("1")){
								realWorkDaysList.get(i).setRevision("再版");
							}
						}
					}
				}
				writeSuccessResult(realWorkDaysList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 保存
	 * @throws Exception 
	 */
	public void save() throws Exception{
		try {
			String workDays = getParam("workDays");
			String flag = getParam("flag");
			workDaysService.modify(workDays,flag);
			writeSuccessResult();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
		
	}
	

	/**
	 * 删除
	 * @throws Exception 
	 */
	public void remove() throws Exception{
		String workDays = getParam("workDays");
		String flag = getParam("flag");
		workDaysService.delete(workDays,flag);
		writeSuccessResult();
	}
}
