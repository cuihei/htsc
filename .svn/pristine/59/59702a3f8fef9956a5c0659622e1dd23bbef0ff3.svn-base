package com.ht.persistence.dao.impl.system.document.template;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.system.doument.template.FormDocumentDao;
import com.ht.persistence.model.system.document.template.FormDocument;
import com.ht.persistence.model.system.document.template.ModelItem;

public class FormDocumentDaoImpl extends BaseDaoImpl implements FormDocumentDao{

	@Override
	public void addFormDocument(FormDocument FormDocument) {
		this.save(FormDocument);
	}

	@Override
	public void modifyFormDocument(FormDocument FormDocument) {
		this.update(FormDocument);
	}

	@Override
	public void delFormDocument(FormDocument FormDocument) {
		this.delete(FormDocument);
	}

	@Override
	public List<FormDocument> getFormDocument() {
		try {
			@SuppressWarnings("unchecked")
			List<FormDocument> result = this.findByNamedQuery("getFormDocument");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public FormDocument getFormDocument(FormDocument FormDocument) {
		@SuppressWarnings("unchecked")
		List<FormDocument> result = this.findByNamedQueryAndNamedParam("getFormDocumentById", "id", FormDocument.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public FormDocument getFormDocumentByFormId(FormDocument FormDocument) {
		@SuppressWarnings("unchecked")
		List<FormDocument> result = this.findByNamedQueryAndNamedParam("getFormDocumentByFormId", "form_id", FormDocument.getForm_id());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

}
