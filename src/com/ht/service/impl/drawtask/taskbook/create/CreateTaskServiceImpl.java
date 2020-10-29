package com.ht.service.impl.drawtask.taskbook.create;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ht.common.exception.CommonException;
import com.ht.common.util.CellValueUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.taskbook.create.ViewCreateTask;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.drawtask.taskbook.create.CreateTaskService;

public class CreateTaskServiceImpl implements CreateTaskService
{

	@Resource
	private CreateTaskDao createTaskDao;
	
	@Resource
	private BaseDataService baseDataService;
	@Override
	public List<ViewCreateTask> getTaskList(String parentTaskBookId) throws Exception {
		try
		{
			return createTaskDao.getTaskList(parentTaskBookId);
		}
		catch (Exception e)
		{
			throw new CommonException("数据库操作错误");
		}
		
	}
	
	@Override
	public List<BaseData> getChildTaskType(String taskType) throws Exception {
		return createTaskDao.getChildTaskType(taskType);
	}
	
	@Override
	public void modify(String taskParam) throws Exception {
		try {
			CreateTask task = (CreateTask) DataConverter.convertJson2Object(taskParam, CreateTask.class);
			String childTaskTypeIds = task.getChildTaskType();
			if(childTaskTypeIds != null){
				if(childTaskTypeIds.split(",").length>0){
					for (int i = 0; i < childTaskTypeIds.split(",").length; i++) {
						CreateTask ct = new CreateTask();
						ct.setId(GenerateSequenceUtil.generateSequenceNo());
						ct.setTaskType(task.getTaskType());
						ct.setChildTaskType(childTaskTypeIds.split(",")[i]);
						ct.setTaskName(task.getTaskName());
						ct.setMapNo(task.getMapNo());
						ct.setScale(task.getScale());
						ct.setRevision(task.getRevision());
						ct.setParentTaskbookId(task.getParentTaskbookId());
						createTaskDao.modify(ct);
					}
				}
			}
			
			/*if(!task.getYearMonth().isEmpty()){
				task.setYear(task.getYearMonth().substring(0,4));
				task.setMonth(task.getYearMonth().substring(5,7));
			}*/
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
	}
	/**
	 * 编绘任务导入
	 * @throws Exception 
	 */
	@Override
	public String uploadFile(String parentTaskId,File file,String fileName) throws Exception
	{
		String message = "";
		try
		{
			// 创建文件流
			FileInputStream is = new FileInputStream(file);
			// 加载文件流
			XSSFWorkbook wbs = new XSSFWorkbook(is);
			// 读取第一个Sheet
			XSSFSheet firstSheet = wbs.getSheetAt(0);
			int rows = firstSheet.getLastRowNum();//总行数
			int clos = firstSheet.getRow(0).getPhysicalNumberOfCells();//总列数
			XSSFCell cell;// 产生单元格  
			cell = firstSheet.getRow(0).createCell(clos);
        	// 任务书map
			Map<String, String> map = new HashMap<String, String>();
			 List<BaseData> taskBookTypeList = baseDataService.getBaseDataByTypeId(BaseDataConstants.TASK_BOOK_TYPE);
             if(null != taskBookTypeList){
             	for(BaseData taskBookType :taskBookTypeList){
             		 map.put(taskBookType.getValue(), taskBookType.getCode());  
             	}
             }
             // 子任务书map
 			Map<String, String> childMap = new HashMap<String, String>();
 			 List<BaseData> childTaskBookTypeList = baseDataService.getBaseDataByTypeId(BaseDataConstants.CHILD_TASK_BOOK_TYPE);
              if(null != childTaskBookTypeList){
              	for(BaseData childTaskBookType :childTaskBookTypeList){
              		childMap.put(childTaskBookType.getValue(), childTaskBookType.getCode());  
              	}
             }
			for (int i = 1; i < rows+1; i++)
			{
				XSSFRow row = firstSheet.getRow(i);
				CreateTask ct = new CreateTask();
				int j = 0;
			 	//任务类型
                String taskType=row.getCell(j++)==null?"":CellValueUtil.getCellValue(row.getCell(j-1)).trim();
                if (taskType == null || taskType.isEmpty())
				{
                	message = "任务类型不能为空！";
					continue;
				}
               if(!map.containsKey(taskType)){
            	   message = "任务书类型不存在！(任务书类型：海图编绘任务书,改正通告任务书,工程&专题图任务书,小改正任务书,其他航海图任务书)";
               	   continue;
               }
                ct.setTaskType(map.get(taskType));
                //子类型
                String childTaskType=row.getCell(j++)==null?"":CellValueUtil.getCellValue(row.getCell(j-1)).trim();
                if (childTaskType == null || childTaskType.isEmpty())
				{
                	message = "子类型不能为空！"; 
					continue;
				}
				if(childMap.get(childTaskType)==null || !childMap.get(childTaskType).startsWith(map.get(taskType))){
					String type="";
					for (Map.Entry<String, String> entry : childMap.entrySet()) {
						if(entry.getValue().startsWith(map.get(taskType))){
							type+=entry.getKey()+",";
						}
					}
					if(type.length()>0){
						message = "子类型不存在此任务书内！此类型任务书包括子类型："+type.substring(0, type.length()-1);
						continue;
					}
	            }
                ct.setChildTaskType(childMap.get(childTaskType));
                //任务名称
                String taskName=row.getCell(j++)==null?"":CellValueUtil.getCellValue(row.getCell(j-1)).trim();
                if (taskName == null || taskName.isEmpty())
				{
                	message = "任务名称不能为空！";
					continue;
				}
                ct.setTaskName(taskName);
                //图号
                String mapNo=CellValueUtil.getCellValue(row.getCell(j++));
                ct.setMapNo(mapNo);
                //比例尺
                String scale=CellValueUtil.getCellValue(row.getCell(j++));
                ct.setScale(scale);
                //版次
                String revision=CellValueUtil.getCellValue(row.getCell(j++));
                ct.setRevision(revision);
                //年
               /* String year=CellValueUtil.getCellValue(row.getCell(j++));
                if (year!=null && !year.isEmpty())
				{
                	if(year.length()!=4){
                		message = "年输入格式错误，例：1900";
    					continue;
                	}
				}
                ct.setYear(year);
                //月
                String month=CellValueUtil.getCellValue(row.getCell(j++));
                if (month!=null && !month.isEmpty())
				{
                	if(month.length()!=2){
                		message = "月输入格式错误，例：01";
    					continue;
                	}
				}
                ct.setMonth(month);*/
                
                ct.setParentTaskbookId(parentTaskId);
                ct.setId(GenerateSequenceUtil.generateSequenceNo());
                createTaskDao.modify(ct);
                message = "导入成功";
			}
			/*HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/msexcel");
			String resultType = fileName.substring(fileName.lastIndexOf("."));
			fileName=fileName.substring(0,fileName.lastIndexOf("."))+"导入结果"+resultType;
			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + "\";");
		    OutputStream out = response.getOutputStream();   
		    wbs.write(out);  
            out.flush();
            out.close();*/
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
		return message;
	}

	@Override
	public String delete(String id) throws Exception {
		// TODO Auto-generated method stub
		CreateTask createTask = createTaskDao.getTaskById(id);
		if(createTask!=null){
			if(createTask.getPublishTimes()!=null){
				return "发布次数大于0，不可删除";
			}else{
				createTaskDao.delete(createTask);
			}
		}
	
		return null;
	}

	@Override
	public String edit(String taskParam) throws Exception {
		try {
			CreateTask task = (CreateTask) DataConverter.convertJson2Object(taskParam, CreateTask.class);
			CreateTask createTask = createTaskDao.getTaskById(task.getId());
			if(createTask!=null){
				if(createTask.getPublishTimes()!=null){
					return "发布次数大于0，不可修改";
				}else{
					createTask.setTaskType(task.getTaskType());
					createTask.setChildTaskType(task.getChildTaskType());
					createTask.setTaskName(task.getTaskName());
					createTask.setMapNo(task.getMapNo());
					createTask.setScale(task.getScale());
					createTask.setRevision(task.getRevision());
					createTaskDao.edit(createTask);
				}
			}
			
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
		return null;
	}

	@Override
	public CreateTask getTask(String id) throws Exception {
		// TODO Auto-generated method stub
		return  createTaskDao.getTaskById(id);
	}
	
}
