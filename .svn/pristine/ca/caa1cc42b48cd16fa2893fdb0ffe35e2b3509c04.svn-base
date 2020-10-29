package com.ht.service.inter.system.document.template;

import java.io.InputStream;
import java.util.List;

import com.ht.persistence.model.system.document.template.FormDocument;
import com.ht.persistence.model.system.document.template.ModelItem;

public interface FormDocumentService {
	/**
	 *保存
	 */
  public void addFormDocument(String FormDocument) throws Exception;
  
  
  public void addFormDocument(String formID , InputStream inputStream) throws Exception;
  
  	/**
	 *修改
	 */
  public void modifyFormDocument(String FormDocument) throws Exception;
  
  public void modifyFormDocument(FormDocument FormDocument) throws Exception;
  
  	/**
	 *删除
	 */
  public void delFormDocument(String id) throws Exception;
  
  	/**
	 *查询所有
	 */
  public List<FormDocument> getFormDocument() throws Exception;
  
  	/**
	 *查询一条
	 */
  public FormDocument getFormDocument(String id) throws Exception;
  
	/**
	 *根据formId查询一条
	 */
  public FormDocument getFormDocumentByFormId(String form_id) throws Exception;
  
  
}
