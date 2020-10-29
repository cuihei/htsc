package com.ht.persistence.dao.impl.complication.formprop;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.formprop.FormFormValueDao;
import com.ht.persistence.model.complication.formprop.FormFormValue;


/** 
* @ClassName: FormFormValueDaoImpl 
* @Description: 数据处理类
* @author penghao
* @date 2016年10月31日 下午7:52:23 
*  
*/
@SuppressWarnings("unchecked")
public class FormFormValueDaoImpl extends BaseDaoImpl implements FormFormValueDao{

	/**
	 * 获取formId集合
	 * @param FormValue FormValue对象
	 * @return FormValue实体
	 */
	@Override
	public List<FormFormValue> getFormIdByTTIdAndPIId(FormFormValue formValue) {
		try {
			String[] names = {"taskInstId","processInstId"};
			String[] params = {formValue.getTaskInstId(),formValue.getProcessInstId()};
			List<FormFormValue> result = this.findByNamedQueryAndNamedParam("getFormIdByTTIdAndPIId", names, params);
			if(result.size()>0){
				return result;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
		}
		return null;
	}
}
