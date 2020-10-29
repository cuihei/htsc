package com.ht.action.background.monitor.operationlog;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.monitor.operationlog.Operationlog;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.background.monitor.operationlog.SyslogOperation;
import com.ht.service.inter.background.monitor.operationlog.SyslogOperationService;

/**
 * 系统操作日志类控制器
 * @author 侯晨
 */
@SuppressWarnings("serial")
public class SyslogOperationAction extends BaseAction{
	
	/**
	 * 注入操作日志service
	 * */
	@Resource(name="syslogOperationService")
	SyslogOperationService syslogOperationService;
	
	/**
	 * 初始化日志数据页面，返回成功列表页面
	 */
	public String init(){
		//创建系统操作日志前台页面初始化类
		Operationlog operationlog = new Operationlog();
		request.setAttribute("html", operationlog.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 初始化新增日志数据页面，返回成功页面
	 * */
	public String addInit() {
		//创建系统操作日志前台页面初始化类
		Operationlog operationlog = new Operationlog();
		request.setAttribute("html", operationlog.getAddNode());
		return SUCCESS;
	}
	
	/**
	 * 新增SyslogOperation数据
	 */
	public void addSyslogOperation(){
		try {
			//获取前台传入参数
			String syslogOperation = getParam("syslogOperation");
			//执行保存操作
			syslogOperationService.addSyslogOperation(syslogOperation);
			//返回客户端消息
			writeSuccessResult(syslogOperation);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新系统操作日志数据
	 */
	public void modifySyslogOperation(){
		//获取前台传入参数
		String syslogOperation = getParam("syslogOperation");
		try {
			//执行修改操作
			syslogOperationService.modifySyslogOperation(syslogOperation);
			//返回客户端消息
			writeSuccessResult(syslogOperation);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除SyslogOperation数据
	 */
	public void removeSyslogOperation(){
		// 获取SyslogOperation标识
		String syslogOperation = getParam("syslogOperation");
		try {
			//执行删除操作
			syslogOperationService.delSyslogOperation(syslogOperation);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取SyslogOperation列表
	 */
	public void getSyslogOperation(){
		try {
			//执行查询操作
			List<SyslogOperation> soList = syslogOperationService.getSyslogOperation();
			//返回客户端消息
			writeSuccessResult(soList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取SyslogOperation
	 */
	public void getSyslogOperationByKey(){
		// 获取SyslogOperation标识
		String id = getParam("id");
		try {
			//执行查询操作
			SyslogOperation so = syslogOperationService.getSyslogOperation(id);
			//返回客户端消息
			writeSuccessResult(so);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出Excel
	 */
	public void exportExcel() throws Exception{
		// 获取ModelType标识
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// 获取预算编码集合
		String so = getParam("so");
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH+"dzy\\export\\" + "syslogOperation";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath+"\\"+("系统操作日志")+GenerateSequenceUtil.generateSequenceNo()+".xls";
		List<SyslogOperation> list = (List<SyslogOperation>) DataConverter.convertJson2List(so, SyslogOperation.class);
		
		String[] col = {"操作者","操作者ip","操作行为","操作结果","操作对象","操作时间"};
		String[] zd = {"operatorId","operatorIp","operationBehavior","operationResult","operationObject","operationTime"};
		
		List<SyslogOperation> soList = new ArrayList<SyslogOperation>();
		for (int i = 0; i < list.size(); i++) {
			SyslogOperation sl;
			sl = syslogOperationService.getSyslogOperation(list.get(i).getId());
			soList.add(sl);
		}
		if(soList.size() > 0){
			ExportExcel ee = new ExportExcel();
			boolean exportResult = ee.exportExcel("日志", col, zd, soList,path);
			if(exportResult){
				ExcelFileUtil.download(path, resp); 
			}
		}
	}
}
