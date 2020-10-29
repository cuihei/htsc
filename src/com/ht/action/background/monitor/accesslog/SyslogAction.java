package com.ht.action.background.monitor.accesslog;

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
import com.ht.front.pages.background.monitor.accesslog.Accesslog;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.system.document.type.ModelType;
import com.ht.service.inter.background.monitor.accesslog.SyslogService;

/**
 * 系统访问日志类控制器
 * @author 侯晨
 */
@SuppressWarnings("serial")
public class SyslogAction extends BaseAction{
	/**
	 * 注入syslogService
	 */
	@Resource(name="syslogService")
	SyslogService syslogService;
	
	/**
	 * 初始化日志数据页面，返回成功列表页面
	 */
	public String init(){
		//创建系统访问日志前台页面初始化类
		Accesslog accesslog = new Accesslog();
		request.setAttribute("html", accesslog.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 初始化新增日志数据页面，返回成功页面
	 * */
	public String addInit() {
		//创建系统访问日志前台页面初始化类
		Accesslog accesslog = new Accesslog();
		request.setAttribute("html", accesslog.getAddNode());
		return SUCCESS;
	}
	
	/**
	 * 新增Syslog数据
	 */
	public void addSyslog(){
		try {
			//获取前台传入参数
			String syslog = getParam("syslog");
			//执行保存操作
			syslogService.addSyslog(syslog);
			//返回客户端消息
			writeSuccessResult(syslog);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Syslog数据
	 */
	public void modifySyslog(){
		// 获取Syslog修改数据
		String syslog = getParam("syslog");
		try {
			//执行修改操作
			syslogService.modifySyslog(syslog);
			//返回客户端消息
			writeSuccessResult(syslog);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除Syslog数据
	 */
	public void removeSyslog(){
		// 获取Syslog标识
		String syslog = getParam("syslog");
		try {
			//执行删除操作
			syslogService.delSyslog(syslog);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Syslog列表
	 */
	public void getSyslog(){
		try {
			//执行查询操作
			List<Syslog> syslogList = syslogService.getSyslog();
			//返回客户端信息
			writeSuccessResult(syslogList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Syslog
	 */
	public void getSyslogByKey(){
		//获取Syslog标识
		String id = getParam("id");
		try {
			//执行查询操作
			Syslog syslog = syslogService.getSyslog(id);
			//返回客户端消息
			writeSuccessResult(syslog);
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
		String syslog = getParam("syslog");
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH+"dzy\\export\\" + "syslog";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath+"\\"+("系统访问日志")+GenerateSequenceUtil.generateSequenceNo()+".xls";
		List<Syslog> list = (List<Syslog>) DataConverter.convertJson2List(syslog, Syslog.class);
		
		String[] col = {"访问者","访问者ip","操作行为","操作结果","访问时间"};
		String[] zd = {"handleId","handleIp","handleBehavior","handleResult","handleTime"};
		
		List<Syslog> syslogList = new ArrayList<Syslog>();
		for (int i = 0; i < list.size(); i++) {
			Syslog sl;
			sl = syslogService.getSyslog(list.get(i).getId());
			syslogList.add(sl);
		}
		if(syslogList.size() > 0){
			ExportExcel ee = new ExportExcel();
			boolean exportResult = ee.exportExcel("文档模板类型导出", col, zd, syslogList,path);
			if(exportResult){
				ExcelFileUtil.download(path, resp); 
			}
		}
	}
}
