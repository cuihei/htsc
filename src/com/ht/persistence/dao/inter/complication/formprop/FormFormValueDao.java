package com.ht.persistence.dao.inter.complication.formprop;

import java.util.List;

import com.ht.persistence.model.complication.formprop.FormFormValue;


/** 
* @ClassName: FormFormValueDao 
* @Description: form和formValue数据层接口 
* @author penghao
* @date 2016年10月31日 下午7:51:29 
*  
*/
public interface FormFormValueDao {

	/**
	 * 获取FormId
	 * @param FormValue FormValue对象
	 * @return FormValue实体
	 */
	public List<FormFormValue> getFormIdByTTIdAndPIId(FormFormValue formValue);
	
}
