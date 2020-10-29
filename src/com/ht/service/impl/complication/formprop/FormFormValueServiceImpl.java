package com.ht.service.impl.complication.formprop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.formprop.FormFormValueDao;
import com.ht.persistence.dao.inter.complication.formprop.FormValueDao;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.complication.formprop.FormFormValue;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.inter.complication.formprop.FormFormValueService;
import com.ht.service.inter.complication.formprop.FormValueService;


/** 
* @ClassName: FormFormValueServiceImpl 
* @Description: form和formValue视图实现类 
* @author penghao
* @date 2016年10月31日 下午7:48:44 
*  
*/
public class FormFormValueServiceImpl implements FormFormValueService {
	/**
	 * 注入formValueDao
	 * */
	@Resource(name = "formFormValueDao") 
	private FormFormValueDao formFormValueDao;
	
	/**
	 * 获取formId
	 * @param taskInstId 流程任务实例ID
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<FormFormValue> getFormIdByTTIdAndPIId(String taskInstId,String processInstId) throws Exception {
		try {
			//创建FormValue对象
			FormFormValue formValue = new FormFormValue();
			formValue.setTaskInstId(taskInstId);
			formValue.setProcessInstId(processInstId);
			return formFormValueDao.getFormIdByTTIdAndPIId(formValue);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
