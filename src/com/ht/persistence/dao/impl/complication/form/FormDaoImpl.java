package com.ht.persistence.dao.impl.complication.form;

import java.util.List;

import org.hibernate.Query;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.form.FormDao;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.complication.form.FormBaseDataView;

/**
 * 表单类型下的列表接口的实现类
 * @author zqy
 *
 */
public class FormDaoImpl extends BaseDaoImpl implements FormDao{

	/** 
	 *  增加一个表单列表
	 *  @param Form Form对象
	 */
	public void addForm(Form form) {
		this.save(form);
	}	

	/** 
	 *  修改一个表单列表
	 *  @param Form Form对象
	 */
	public void modifyForm(Form form) {
		this.update(form);
	}

	/** 
	 *  删除一个表单列表
	 *  @param Form Form对象
	 */
	public void delForm(Form form) {
		this.delete(form);
	}

	/** 
	 * 查询多个表单列表
	 *  @param Form Form对象=
	 */
	public List<Form> getForm() {
		@SuppressWarnings("unchecked")
		List<Form> formList = this.findByNamedQuery("getForm");
		return formList;
	}

	/** 
	 *  查询一个表单列表对象
	 *  @param Form Form对象
	 */
	public Form getForm(Form form) {
		@SuppressWarnings("unchecked")
		List<Form> result = (List<Form>) this.findByNamedQueryAndNamedParam("getFormById", "id", form.getId());
		if(result.size()>0){
			//获取第一条
			return result.get(0);
		}
		return null;
	}

	/**
	 * 根据BasedataId查询所有的表单列表
	 * @param BaseDataId Form对象的BaseDataId属性
	 * @return List<Form> Form对象集合
	 * @throws Exception
	 */
	@Override
	public List<Form> getFormByBaseDataId(Form form) {
		@SuppressWarnings("unchecked")
		List<Form> result = (List<Form>) this.findByNamedQueryAndNamedParam("getFormByBaseDataId", "baseDataId", form.getBaseDataId());
		return result;
	}
	
	/** 
	 * 查询多个表单列表
	 *  @param Form Form对象=
	 */
	public List<FormBaseDataView> getFormList() {
		@SuppressWarnings("unchecked")
		List<FormBaseDataView> formList = this.findByNamedQuery("getFormBaseDataView");
		return formList;
	}
///评分记录数
	@Override
	public Integer getFormValueNum(String taskinstId, String processInstId, String taskName) {

		if(taskName.indexOf("质检结论表")>0) {
			String sql="SELECT count(*) FROM FORM_VALUE where  PROCESS_INST_ID='"+processInstId+"' and PROP_KEY='zlpf' and PROP_VALUE is not null";
		    Query query1 = getSession().createSQLQuery(sql);
		    Integer count = Integer.valueOf(((Number)query1.uniqueResult()).intValue());
		    return count;
			
		};
		if(taskName.indexOf("审定结论表")>0) {
			String sql="SELECT count(*) FROM FORM_VALUE where  PROCESS_INST_ID='"+processInstId+"' and PROP_KEY='sdjl' and PROP_VALUE is not null";
		    Query query1 = getSession().createSQLQuery(sql);
		    Integer count = Integer.valueOf(((Number)query1.uniqueResult()).intValue());
		    return count;
			
		};
	  		String sql="SELECT count(*) FROM FORM_VALUE where  PROCESS_INST_ID='"+processInstId+"' and PROP_KEY='pfdj' and PROP_VALUE is not null";
		    Query query1 = getSession().createSQLQuery(sql);
		    Integer count = Integer.valueOf(((Number)query1.uniqueResult()).intValue());
	        return count;
					
		
	}
	
	
	
}
