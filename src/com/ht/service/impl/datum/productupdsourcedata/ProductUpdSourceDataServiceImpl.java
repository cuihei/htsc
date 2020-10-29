package com.ht.service.impl.datum.productupdsourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.datum.productupdsourcedata.ProductUpdSourceDataDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.dao.inter.system.assign.AssignUserRecordDao;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.system.workflow.assign.AssignUserRecord;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.constant.ComplicationTaskProcessArgs;
import com.ht.service.constant.model.ComlicationTaskPersonAndDate;
import com.ht.service.inter.datum.productupdsourcedata.ProductUpdSourceDataService;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 产品修改源数据serviceImpl
 * @author Mr_zyd
 * @date 2017/12/12
 */
public class ProductUpdSourceDataServiceImpl implements ProductUpdSourceDataService {
	
	/**
	 * 注入service
	 */
	@Resource
	IWorkflowService service;
	
	/**
	 * 注入vProcessDetailService
	 */
	@Resource
	VProcessDetailService vProcessDetailService;
	
	/**
	 * 注入productUpdSourceDataDaoDao
	 */
	@Resource(name = "productUpdSourceDataDao") 
	ProductUpdSourceDataDao productUpdSourceDataDao;
	
	/**
	 * 注入taskDao
	 */
	@Resource
	TaskDao taskDao;
	
	/**
	 * 注入vProcessDetailDao
	 */
	@Resource
	VProcessDetailDao vProcessDetailDao;
	
	/**
	 * 注入createTaskDao
	 */
	@Resource
	CreateTaskDao createTaskDao;
	
	/**
	 * 注入roleUsersDao
	 */
	@Resource
	AssignUserRecordDao assignUserRecordDao;
	
	/**
	 * 注入UsersDao
	 */
	@Resource
	UserDao userDao;

	@Override
	public String addProductUpdSourceData(String productUpdSourceDataParam,String draw) throws Exception {
		// 增加ProductUpdSourceData
		try {
			ProductUpdSourceData pusd =(ProductUpdSourceData)DataConverter.convertJson2Object(productUpdSourceDataParam, ProductUpdSourceData.class);
			String pusdId = GenerateSequenceUtil.generateSequenceNo();
			pusd.setId(pusdId);		// id
			pusd.setDraw(draw);		// 图号
			pusd.setStatus("1");	// 设置状态
			productUpdSourceDataDao.addProductUpdSourceData(pusd);		// 保存
			return "success";
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改pusd
	 * @return 
	 */
	@Override
	public String modifyPusd(ProductUpdSourceData pusd) throws Exception {
		try {
			// 更新Pusd
			productUpdSourceDataDao.modifyPusd(pusd);
			return "success";
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有pusd
	 */
	@Override
	public List<ProductUpdSourceData> getProductUpdSourceData() throws Exception {
		try {
			// 获取所有Notice
			return productUpdSourceDataDao.getProductUpdSourceData();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 通过图号获取任务
	 * @param map
	 * @param recordId
	 * @throws Exception
	 */
	public Map<String, String> getPersonByMapNo(String mapNo,String childTskType, String status) throws Exception
	{
		CreateTask task = createTaskDao.getTaskByMapNo(mapNo,childTskType,status);
		VProcessDetail detail = vProcessDetailService.getByDetailRecordId(task.getId());
		
		Map<String, String> map = new HashMap<String, String>();
		if(detail != null){
			RuTask rt = taskDao.getRtByParentProcessInstId(detail.getProcessInstId());
			List<HiTask> ht = taskDao.getTaskByParentProcessInstId(detail.getProcessInstId());
			ComlicationTaskPersonAndDate compeletion = new ComlicationTaskPersonAndDate();
			if(rt != null){
				compeletion = setTaskCompeletion(rt.getProcessInstId());
			}else if(ht.size() > 0){
				compeletion = setTaskCompeletion(ht.get(0).getProcessInstId());
			}
			map.put("compilationPerson", compeletion.getCompilationPerson());	// 编绘员
			map.put("qualityPerson", compeletion.getQualityPerson());			// 质检员
			map.put("apprvoePerson", compeletion.getApprvoePerson());			// 审定员
		}
		map.put("taskName", task.getTaskName());							// 图名
		return map;
	}
	
	
	/**
	 * 设置任务完成进度情况
	 * @return
	 * @throws Exception 
	 */
	public ComlicationTaskPersonAndDate setTaskCompeletion(String processInstId) throws Exception
	{
		ComlicationTaskPersonAndDate compeletion = new ComlicationTaskPersonAndDate();
		// 设置编绘人
		Object compilationPerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.compilationPerson);
		if (compilationPerson != null)
		{
			String compilationPersonName = getUserName(compilationPerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.compilationPerson);
				if(recordList != null && recordList.size() > 0){
					compilationPersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			compeletion.setCompilationPerson(compilationPersonName);
		}
		
		// 设置质检员
		Object qualityPerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.qualityPerson);
		if (qualityPerson != null)
		{
			String qualityPersonName = getUserName(qualityPerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.qualityPerson);
				if(recordList != null && recordList.size() > 0){
					qualityPersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			compeletion.setQualityPerson(qualityPersonName);
		}
		
		// 设置审定员
		Object apprvoePerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.apprvoePerson);
		if (apprvoePerson != null)
		{
			String apprvoePersonName = getUserName(apprvoePerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.apprvoePerson);
				if(recordList != null && recordList.size() > 0){
					apprvoePersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			compeletion.setApprvoePerson(apprvoePersonName.toString());
		}
		return compeletion;
	}
	
	/**
	 * 获取用户ID
	 * @param loginNo 工号
	 * @return 取用户ID
	 */
	public String getUserName(String userId)
	{
		// 获取用户ID
		User userParam = new User();
		userParam.setId(userId);
		// 根据userNo获取User
		User user = userDao.getUser(userParam);
		if (user != null)
		{
			return user.getUserName();
		}
		return null;
	}

	/**
	 * 根据图号挂起流程
	 * @param mapNo
	 * @return 
	 */
	@Override
	public String suspended(String mapNo,String flag,String type) throws Exception {
		try {
			// 获取纸海图任务，判断是否有纸海图任务
			List<CreateTask> task = createTaskDao.getTaskListByDrawAndType(mapNo,type);
			if(task.size() > 0){
				String recordId = task.get(0).getId();
				VProcessDetail detail = vProcessDetailService.getByDetailRecordId(recordId);
				
				if(detail != null){
					RuTask rt = taskDao.getRtByParentProcessInstId(detail.getProcessInstId());
					if(rt != null){
						// 挂起流程
						if(flag.equals("suspended")){
							service.suspendProcessInstanceById(rt.getProcessInstId());
							// 启动流程
						}else if(flag.equals("activate")){
							service.activateProcessInstanceById(rt.getProcessInstId());
						}
					}
				}
			}
			
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return "success";
	}

	/**
	 * 根据状态和类型获取任务
	 */
	@Override
	public List<CreateTask> getTaskListByStatusAndType(String status, String type) {
		List<CreateTask> ctList = createTaskDao.getTaskListByStatusAndType(status,type);
		return ctList;
	}

	/**
	 * 根据id获取数据
	 */
	@Override
	public ProductUpdSourceData getProductUpdSourceDataById(String id) {
		return productUpdSourceDataDao.getProductUpdSourceDataById(id);
	}

	/**
	 * 根据不同状态获取数据
	 */
	@Override
	public List<ProductUpdSourceData> getProductUpdSourceDataByStatus(String status,String userName) {
		try {
			// 获取所有Notice
			return productUpdSourceDataDao.getProductUpdSourceDataByStatus(status,userName);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 通过图号和类型获取数据
	 */
	@Override
	public List<CreateTask> getTaskListByDrawAndType(String draw, String type) {
		List<CreateTask> ctList = createTaskDao.getTaskListByDrawAndType(draw,type);
		return ctList;
	}
	
}
