package com.ht.service.constant.experiencebook;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.FileUtil;
import com.ht.common.util.MSWordTool;
import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.workflow.task.Flows;

public class PersonAndDateUtil {
	UserDao userDao = null;
	public PersonAndDateUtil() {
		super();
		userDao = (UserDao) SpringUtil.getBean("userDao");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取当前task_def_id的流转信息
	 * @param task_def_id 
	 * @param flowList 流转列表
	 * @return
	 */
	public Flows getPersonAndDateByTaskDefIds(String task_def_id, List<Flows> flowList)
	{
		Flows result=null;
		if (flowList != null)
		{
			for (int i = 0; i < flowList.size(); i++)
			{
				Flows flow = flowList.get(i);
				String taskDefId = flow.getTaskDefId();
				if (task_def_id!=null&&task_def_id.equals(taskDefId)) {
					if(result!=null&&flow.getEndTime()!=null&&result.getEndTime().before(flow.getEndTime())){
						result= flow;
					}else if(result==null){
						result= flow;
					}
					
				}
			}
		}
		return result;
	}
	/**
	 * 获取当前task_def_id的流转信息
	 * @param task_def_id 
	 * @param flowList 流转列表
	 * @return
	 */
	public Flows getPersonAndDateByTaskDefIds(String task_def_id1,String task_def_id2, List<Flows> flowList)
	{
		Flows result=null;
		if (flowList != null)
		{
			for (int i = 0; i < flowList.size(); i++)
			{
				Flows flow = flowList.get(i);
				String taskDefId = flow.getTaskDefId();
				if (StringUtils.isNoneBlank(task_def_id1)&&StringUtils.isNotBlank(task_def_id2)&&(task_def_id1.equals(taskDefId)||task_def_id2.equals(taskDefId))) {
					if(result!=null&&flow.getEndTime()!=null&&result.getEndTime().before(flow.getEndTime())){
						result= flow;
					}else if(result==null){
						result= flow;
					}
					
				}
			}
		}
		return result;
	}
	/**
	 * 获取用户签章
	 * @param userNo 工号
	 * @return 签章地址
	 */
	public String getUserSignature(String userNo)
	{
		User user = new User();
		user.setUserNo(userNo);
		List<User> users = userDao.getUserByNo(user);
		if (users != null)
		{
			if (users.size() > 0) { 
				String url=users.get(0).getUserImg();
				if(StringUtils.isNotEmpty(url)&&url.lastIndexOf(".")!=-1){
					String imageUrl = FileUtil.getRootPath() + url.substring(3);
					BufferedImage image = MSWordTool.getBufferedImage(imageUrl);
					if(image!=null){
						return users.get(0).getUserImg();
					}else{
						return null;
					}
				}else{
					return null;
				}
				
			}
		}
		return null;
	}
	/**
	 * 格式化时间
	 * @param date
	 * @return
	 */
	public String formatDate(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null){
			return df.format(date);
		}
		return null;
	}
	/**
	 * 获取当前task_def_id的多条流转信息
	 * @param task_def_id 
	 * @param flowList 流转列表
	 * @return
	 */
	public List<Flows>  getFlowsListByTaskDefIds(String task_def_id, List<Flows> flowList)
	{
		List<Flows> result=new ArrayList<Flows>();
		if (flowList != null)
		{
			for (int i = 0; i < flowList.size(); i++)
			{
				Flows flow = flowList.get(i);
				String taskDefId = flow.getTaskDefId();
				if (task_def_id!=null&&task_def_id.equals(taskDefId)) {
					result.add(flow);
				}
			}
		}
		if(result.size()>0){
			Collections.sort(result,new Comparator<Flows>(){
	            public int compare(Flows arg0, Flows arg1) {
	                return arg0.getStartTime().compareTo(arg1.getStartTime());
	            }
	        });
		}
		return result;
	}
	/**
	 * 去重，根据list1中流程的开始时间，和list2流程中的结束时间比较，相同则去除。
	 * 主要用于流程中多个开始节点的情况 例如<源数据小改正中审定后改图步骤和改后确认步骤>。
	 * @param flowList1
	 * @param flowList2
	 * @return
	 */
	public List<Flows>  excludeFlows(List<Flows> flowList1,List<Flows> flowList2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Flows> result=new ArrayList<Flows>();
		result.addAll(flowList1);
		for (Flows flows1 : flowList1) {
			String startTime=sdf.format(flows1.getStartTime());
			for (Flows flows2 : flowList2) {
				String endTime=sdf.format(flows2.getEndTime());
				if(startTime.equals(endTime)){
					result.remove(flows1);
					break;
				}
			}
		}
		return result;
	}
}
