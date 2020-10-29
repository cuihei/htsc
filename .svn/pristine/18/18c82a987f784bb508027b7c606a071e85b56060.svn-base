package com.ht.action.system.document.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import com.ht.action.base.BaseAction;
import com.ht.common.util.File2PdfUtil;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.MSWordTool;
import com.ht.front.pages.system.document.template.DocTempletePage;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.document.template.FormDocument;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.complication.formprop.FormPropService;
import com.ht.service.inter.complication.formprop.FormValueService;
import com.ht.service.inter.system.document.template.FormDocumentService;

@SuppressWarnings("serial")
public class DocTempleteAction extends BaseAction {
	

	/**
	 * 注入表单值Service
	 */
	@Resource
	private FormValueService formValueService;
	/**
	 * 注入表单属性Service
	 */
	@Resource(name="formPropService")
	FormPropService formPropService;
	
	/**
	 * 注入表单Service
	 */
	@Resource(name="formService")
	FormService formService;
	
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="formDocumentService")
	FormDocumentService formDocumentService;
	//接收保存的模板文件
	private File Control;
	
	
	public File getControl() {
		return Control;
	}


	public void setControl(File control) {
		Control = control;
	}


	/**
	 * 初始化表单属性页面，返回成功列表页面
	 * @throws Exception 
	 */
	public String index() throws Exception{
		//获取前台传入参数
		String formId = getParam("formId");
		DocTempletePage doc=new DocTempletePage();
		//创建表单前台页面
		request.setAttribute("html", doc.getListNode(formId,formService));
		// 获取项目在服务器的路径
		String serverPath = FileUtil.ROOT_PATH;
		// 新建一个路径，在最后以当前年月日新建一个文件夹
		String path = "\\upload\\docTemplete\\";
		String doctempleteUrl=serverPath+path+formId+".pdf";
		File file=new File(doctempleteUrl);    
		if(file.exists())    
		{    
			doctempleteUrl="..\\upload\\docTemplete\\"+formId+".pdf";
			request.setAttribute("doctempleteUrl",doctempleteUrl);   
		} else{
			request.setAttribute("doctempleteUrl","");
		}
		return SUCCESS;
	}
	/**
	 * 模板匹配数据的整合页面
	 * @throws Exception 
	 */
	public String detilsPage() throws Exception{
		//获取前台传入参数
		String formId = getParam("formId");
		// 获取前台传入的任务ID
		String taskInstId = getParam("taskInstId");
		// 获取前台传入的流程ID
		String processInstId = getParam("processInstId");
		DocTempletePage doc=new DocTempletePage();
		//创建表单前台页面
		//生成文档
		getDocDetils(taskInstId, processInstId, formId);
		// 获取项目在服务器的路径
		String serverPath = FileUtil.ROOT_PATH;
		// 新建一个路径，在最后以当前年月日新建一个文件夹
		String path = "\\upload\\docTempleteDetils\\";
		String doctempleteUrl=serverPath+path+formId+".pdf";
		File file=new File(doctempleteUrl);    
		if(file.exists())    
		{    
			doctempleteUrl="..\\upload\\docTempleteDetils\\"+formId+".pdf";
			request.setAttribute("doctempleteUrl",doctempleteUrl);   
		} else{
			request.setAttribute("doctempleteUrl","");
			request.setAttribute("downLoadUrl","");
		}
		request.setAttribute("html", doc.formDetilsPage(formId,taskInstId,processInstId,file.exists()));
		return SUCCESS;
	}
	
	/**
	 * 获取表单
	 */
	public void getForm (){
		try {
			//获取前台传入参数
			String formId = getParam("formId");
			//执行查询操作
			List<FormPropFormView> formPropList = formPropService.getFormPropList(formId);
			//返回客户端信息
			writeSuccessResult(formPropList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 获取模板文件
	 */
	public void getDoc(){
		try {
			//获取前台传入参数
			HttpServletResponse response = this.respose;
			String formId = getParam("formId");
			FormDocument document = formDocumentService.getFormDocumentByFormId(formId);
			if(document!=null){
				Blob files = document.getFiles();
					if(files!=null){
						InputStream in = files.getBinaryStream();
						OutputStream outStream = response.getOutputStream();
						byte[] buf = new byte[1024];
						int bytes = 0;
						while ((bytes = in.read(buf)) != -1)
							outStream.write(buf, 0, bytes);
						in.close();
						outStream.close();
					}
			}else{
				
			}
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 根据模板生成完整表单文件
	 */
	public void getDocDetils(String taskInstId,String processInstId,String formId ){
		try {
			FormDocument document = formDocumentService.getFormDocumentByFormId(formId);
			if(document!=null){
				Blob files = document.getFiles();
				if(files!=null){
					//读取文档
					InputStream in = files.getBinaryStream();
					//设置存放地址
					// 获取项目在服务器的路径
					String serverPath = FileUtil.ROOT_PATH;
					// 新建一个路径
					String path = "\\upload\\docTempleteDetils\\";
					// 创建文件夹
					FileUtil.CreateFolder(serverPath + path);
					File tempDoc=new File(serverPath + path+"templete.doc");
					FileOutputStream outStream=new FileOutputStream(tempDoc);
//					//初始化工具类
					MSWordTool changer = new MSWordTool();
					//放入模板
					changer.setTemplate(in);
					List<String> bookMarksList = changer.getBookMarkStrings();
					// 当前表单属性个数
					List<FormProp> formProp = formPropService
							.getFormPropByFormId(formId);
					int propNum = formProp.size();
					// 数据
					List<FormValue> list = formValueService.getFormValueByPt(
							processInstId, taskInstId, formId);
					// 查询的数据个数
					int length = list.size();
					// 当前循环到第几个
					int index = 1;
					Map<String,String> map = new HashMap<String,String>();
					List<Map<String,String>> mapList= new ArrayList<Map<String,String>>();
					if(bookMarksList.size()>0){//拥有表格
						for (String bookMark : bookMarksList) {//第一次循环，进行重复性数据替换
							if(bookMark.startsWith("table")){//简单表格
								for (int i = 0; i < length / propNum; i++) {
									index = i + 1;
									String rowFlag = list.get(index * propNum - 1).getRowFlag();
									for (int j = 0; j < list.size(); j++) {
										//属性名
										String propKey = list.get(j).getPropKey();
										//属性值
										String propValue=list.get(j).getPropValue();
										if (list.get(j).getRowFlag().equals(rowFlag)) {
											for (FormProp prop: formProp) {
												if(prop.getPropKey().equals(propKey)){
													if(prop.getPropType().equals("text")){//text类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("select")){//select类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("textArea")){//textArea类型
														map.put("text_"+propKey,propValue);// 取值
													}
												}
											}
										}
									}
									mapList.add(map);
									map = new HashMap<String,String>();
								}
								changer.fillTableAtBookMark(bookMark, mapList,true);//进行重复性数据替换
							}
							if(bookMark.startsWith("ftable")){//复杂型表格
								if(length>0){
									index =	1;
									String rowFlag = list.get(index * propNum - 1).getRowFlag();
									for (int j = 0; j < list.size(); j++) {
										//属性名
										String propKey = list.get(j).getPropKey();
										//属性值
										String propValue=list.get(j).getPropValue();
										if (list.get(j).getRowFlag().equals(rowFlag)) {
											for (FormProp prop: formProp) {
												if(prop.getPropKey().equals(propKey)){
													if(prop.getPropType().equals("text")){//text类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("select")){//select类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("textArea")){//textArea类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("img")){//img类型
														map.put("img_"+propKey,propValue);// 取值
													}
												}
											}
										}
									}
									changer.replaceTitle(map);
								}
							}
							if(bookMark.startsWith("ztable")){//组合型表格
								String[] split = bookMark.split("_");//拆分书签
								String str=split[split.length-1];
								Integer size = Integer.valueOf(str);
								size=(length / propNum)>size?size:(length / propNum);//
								for (int i = 0; i <size; i++) {
									index = i + 1;
									String rowFlag = list.get(index * propNum - 1).getRowFlag();
									for (int j = 0; j < list.size(); j++) {
										//属性名
										String propKey = list.get(j).getPropKey();
										//属性值
										String propValue=list.get(j).getPropValue();
										if (list.get(j).getRowFlag().equals(rowFlag)) {
											for (FormProp prop: formProp) {
												if(prop.getPropKey().equals(propKey)){
													if(prop.getPropType().equals("text")){//text类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("select")){//select类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("textArea")){//textArea类型
														map.put("text_"+propKey,propValue);// 取值
													}
													if(prop.getPropType().equals("img")){//img类型
														map.put("img_"+propKey,propValue);// 取值
													}
												}
											}
										}
									}
									mapList.add(map);
									map = new HashMap<String,String>();
								}
								changer.fillTableAtBookMark(bookMark, mapList,false);//进行重复性数据替换
								changer.replaceTitle(mapList.get(0));
							}
						}
						
						changer.save(outStream);
					}else{//不含有标签，普通文本
							if(length>0){
								index =	1;
								String rowFlag = list.get(index * propNum - 1).getRowFlag();
								for (int j = 0; j < list.size(); j++) {
									//属性名
									String propKey = list.get(j).getPropKey();
									//属性值
									String propValue=list.get(j).getPropValue();
									if (list.get(j).getRowFlag().equals(rowFlag)) {
										for (FormProp prop: formProp) {
											if(prop.getPropKey().equals(propKey)){
												if(prop.getPropType().equals("text")){//text类型
													map.put("text_"+propKey,propValue);// 取值
												}
												if(prop.getPropType().equals("select")){//select类型
													map.put("text_"+propKey,propValue);// 取值
												}
												if(prop.getPropType().equals("textArea")){//textArea类型
													map.put("text_"+propKey,propValue);// 取值
												}
												if(prop.getPropType().equals("img")){//img类型
													map.put("img_"+propKey,propValue);// 取值
												}
											}
										}
									}
								}
								changer.replaceTitle(map);//替换文本
								changer.save(outStream);//输出文本
							}
						}
					//生成pdf,文档
					//读取文件
					InputStream is2 = new FileInputStream(tempDoc);
					//生成
					File2PdfUtil.convert(is2,serverPath + path+formId+".doc", serverPath + path+formId+".pdf");
				}
			}else{
				
			}
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 保存
	 */
	@SuppressWarnings("unchecked")
	public void saveDoc(){
		try {
			InputStream is = new FileInputStream(Control);
			InputStream is2 = new FileInputStream(Control);
			String formId = this.getParam("formId");
			FormDocument document = formDocumentService.getFormDocumentByFormId(formId);
			if(null!=document){
				Blob blob = Hibernate.createBlob(is);
				document.setFiles(blob);
				formDocumentService.modifyFormDocument(document);
			}else{
				formDocumentService.addFormDocument(formId, is);
			}
			// 获取项目在服务器的路径
			String serverPath = FileUtil.ROOT_PATH;
			// 新建一个路径
			String path = "\\upload\\docTemplete\\";
			// 创建文件夹
			FileUtil.CreateFolder(serverPath + path);
			//设置一个文件。
			File2PdfUtil.convert(is2,serverPath + path+formId+".doc", serverPath + path+formId+".pdf");
			//返回客户端信息
			writeSuccessResult("success");
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 下载说明文档
	 */
	public void downLoad() throws Exception {
		try {
			HttpServletResponse response = this.respose;
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=instruction.doc");
			// 获取项目在服务器的路径
			String serverPath = request.getServletContext().getRealPath("/");
			// 获取相应文件的流
			File file = new File(serverPath + "/ht/instruction.doc");
			// 设置文件长度
			response.setHeader("Content-Length", (int) file.length() + "");
			// IO流复制
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			int length;
			while ((length = inputStream.read()) != -1) {
				os.write(length);
			}
			// 释放资源
			inputStream.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
		}

	}
	
	
}
