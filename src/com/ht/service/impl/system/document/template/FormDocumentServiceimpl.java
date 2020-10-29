package com.ht.service.impl.system.document.template;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.doument.template.FormDocumentDao;
import com.ht.persistence.model.system.document.template.FormDocument;
import com.ht.persistence.model.system.document.template.ModelItem;
import com.ht.service.inter.system.document.template.FormDocumentService;

public class FormDocumentServiceimpl implements FormDocumentService{
	
	/**
	 * 注入Dao
	 * */
	@Resource(name = "formDocumentDao")
	private FormDocumentDao formDocumentDao;
	
	@Override
	public void addFormDocument(String FormDocument) throws Exception {
		// 增加ModelItem
		try {
			FormDocument FormDocument1 =(FormDocument)DataConverter.convertJson2Object(FormDocument, FormDocument.class);
			formDocumentDao.addFormDocument(FormDocument1);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void modifyFormDocument(String FormDocument1) throws Exception {
		try {
			FormDocument FormDocument = (FormDocument) DataConverter.convertJson2Object(FormDocument1, ModelItem.class);
			// 更新ModelItem
			formDocumentDao.modifyFormDocument(FormDocument);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void delFormDocument(String id) throws Exception {
		try {
			FormDocument FormDocument = new FormDocument();
			FormDocument.setId(id);
			// 删除FormDocument
			formDocumentDao.delFormDocument(FormDocument);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<FormDocument> getFormDocument() throws Exception {
		try {
			// 获取所有FormDocument
			return formDocumentDao.getFormDocument();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public FormDocument getFormDocument(String id) throws Exception {
		try {
			FormDocument FormDocument = new FormDocument();
			FormDocument.setId(id);
			// 根据id获取FormDocument
			return formDocumentDao.getFormDocument(FormDocument);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public FormDocument getFormDocumentByFormId(String form_id) throws Exception {
		try {
			FormDocument FormDocument = new FormDocument();
			FormDocument.setForm_id(form_id);
			// 根据id获取FormDocument
			return formDocumentDao.getFormDocumentByFormId(FormDocument);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addFormDocument(String formId, InputStream inputStream)
			throws Exception {
		FormDocument formDocument = new FormDocument();
		formDocument.setId(GenerateSequenceUtil.generateSequenceNo());
		formDocument.setForm_id(formId);
		Blob blob = Hibernate.createBlob(inputStream);
		formDocument.setFiles(blob);
		formDocumentDao.addFormDocument(formDocument);
	}

	@Override
	public void modifyFormDocument(FormDocument FormDocument) throws Exception {
		try {
			// 更新ModelItem
			formDocumentDao.modifyFormDocument(FormDocument);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

}
