package com.ht.persistence.dao.impl.background.dicdata.defectform;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;

/**
 * @ClassName: DefectFormDaoImpl
 * @Description: TODO(缺陷表单的数据操作)
 * @author penghao
 * @date 2016年11月6日 下午2:43:41
 */
@SuppressWarnings("unchecked")
public class DefectFormDaoImpl extends BaseDaoImpl implements DefectFormDao {

	/**
	 * 获取缺陷表单列表
	 */
	@Override
	public List<DefectForm> getDefectFormList(DefectForm defectForm) {
		String hql = "from DefectForm where 1=1";
		if (defectForm != null && StringUtils.isNotBlank(defectForm.getTaskInstId())) {
			hql += " and taskInstId=?";
		}
		if (defectForm != null && StringUtils.isNotBlank(defectForm.getProcessInstId())) {
			hql += " and processInstId=?";
		}
		/*
		 * if (defectForm != null && StringUtils.isNotBlank(defectForm.getSplitId())) {
		 * hql += " and splitId=?"; }
		 */
		List<DefectForm> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			int i = 0;
			if (defectForm != null && StringUtils.isNotBlank(defectForm.getTaskInstId())) {
				String taskInstId = defectForm.getTaskInstId();
				query.setParameter(i, taskInstId);
				i++;
			}
			if (defectForm != null && StringUtils.isNotBlank(defectForm.getProcessInstId())) {
				String processInstId = defectForm.getProcessInstId();
				query.setParameter(i, processInstId);
			}
			/*
			 * if (defectForm != null && StringUtils.isNotBlank(defectForm.getSplitId())) {
			 * String splitId = defectForm.getSplitId(); query.setParameter(2, splitId); }
			 */
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	@Override
	public List<DefectForm> getDefectFormListByProcessInstIdAndTaskId(DefectForm defectForm) {
		String hql = "from DefectForm where 1=1";
		if (defectForm != null && StringUtils.isNotBlank(defectForm.getTaskInstId())) {
			hql += " and taskInstId=?";
		}
		if (defectForm != null && StringUtils.isNotBlank(defectForm.getProcessInstId())) {
			hql += " and processInstId=?";
		}
		List<DefectForm> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			if (defectForm != null && StringUtils.isNotBlank(defectForm.getTaskInstId())) {
				String taskInstId = defectForm.getTaskInstId();
				query.setParameter(0, taskInstId);
			}
			if (defectForm != null && StringUtils.isNotBlank(defectForm.getProcessInstId())) {
				String processInstId = defectForm.getProcessInstId();
				query.setParameter(1, processInstId);
			}
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取缺陷表单列表
	 */
	@Override
	public List<DefectForm> getDefectFormListByProcessInstIdAndFormId(DefectForm defectForm) {
		List<DefectForm> list = null;
		String processInstId = defectForm.getProcessInstId();
		String formId = defectForm.getFormId();
		String hql = "from DefectForm where processInstId = ? and formId = ? order by modifyDate asc";
		try {
			Query query = this.getSession().createQuery(hql);
			query.setString(0, processInstId);
			query.setString(1, formId);
			// 获取缺陷列表
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取缺陷表单列表
	 */
	@Override
	public List<DefectForm> getDefectFormListByProcessInstId(String processInstId, String type) {
		List<DefectForm> list = null;
		String sql = "select a.*,b.task_def_key_,b.name_ from form_defective a left join act_hi_taskinst b on a.process_inst_id=b.proc_inst_id_ and a.task_inst_id = b.id_  where a.process_inst_id = ? and b.task_def_key_ like ? order by a.creation_date desc";
		try {
			// 获取缺陷列表
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.setParameter(0, processInstId);
			query.setParameter(1, "%" + type + "%");
			query.addEntity(DefectForm.class);
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取缺陷表单列表
	 */
	@Override
	public List<DefectForm> getDefectFormListByProcessInstId(String processInstId, String type, String type2) {
		List<DefectForm> list = null;
		String sql = "select a.*,b.task_def_key_,b.name_ from form_defective a left join act_hi_taskinst b on a.process_inst_id=b.proc_inst_id_ and a.task_inst_id = b.id_  where a.process_inst_id = ? and (b.task_def_key_ like ? or b.task_def_key_ like ?)";
		try {
			// 获取缺陷列表
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.setParameter(0, processInstId);
			query.setParameter(1, "%" + type + "%");
			query.setParameter(2, "%" + type2 + "%");
			query.addEntity(DefectForm.class);
			return query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 添加缺陷记录
	 */
	@Override
	public void add(DefectForm defectForm) {
		try {
			this.save(defectForm);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 更新
	 */
	@Override
	public void modifyDefectForm(DefectForm defectForm) {
		defectForm = (DefectForm) this.merge(defectForm);
		this.update(defectForm);
	}

	/**
	 * 删除
	 */
	@Override
	public void delDefectForm(DefectForm defectForm) {
		defectForm = (DefectForm) this.merge(defectForm);
		this.delete(defectForm);
	}

	/**
	 * 根据id获取一条记录
	 */
	@Override
	public DefectForm getDefectFormById(DefectForm defectForm) {
		List<DefectForm> list = null;
		try {
			// 根据id获取缺陷
			list = this.findByNamedQueryAndNamedParam("getDefectFormById", "id", defectForm.getId());
			if (CollectionUtils.isEmpty(list)) {
				return null;
			}
		} catch (Exception e) {
		}
		return list.get(0);
	}

	/**
	 * 计算得分
	 */
	@Override
	public void modifyCount(DefectForm defectForm, String taskInstId, String processInstId) {
		try {
			List<DefectForm> result = null;
			String sql = "select * from FORM_DEFECTIVE where PROCESS_INST_ID = '" + processInstId
					+ "' and TASK_INST_ID in (" + taskInstId + ")";
			// 获取缺陷列表
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(DefectForm.class);
			result = query.list();
			BigDecimal actual = new BigDecimal(0);// 实际扣分
			BigDecimal total = new BigDecimal(0);// 累计扣分

			BigDecimal actualRes = new BigDecimal(0);// 输出实际扣分
			BigDecimal totalRes = new BigDecimal(0);// 输出累计扣分
			BigDecimal grading = new BigDecimal(100);// 质检评分
			if (result != null) {
				if (result.size() > 0) {
					String coefficient = result.get(0).getCoefficient() == null ? "1" : result.get(0).getCoefficient();
					for (int i = 0; i < result.size(); i++) {
						if (result.get(i).getDeep() != null) {
							if (result.get(i).getDeep().equals("Ⅰ")) {
								if (result.get(i).getScore() != null && !result.get(i).getScore().equals("")) {
									total = new BigDecimal(result.get(i).getScore());
									totalRes = total.add(totalRes);
									actualRes = totalRes;
									if (actualRes.intValue() > 100) {
										actualRes = new BigDecimal(100);
									}
								}
							} else {
								BigDecimal cofe = new BigDecimal(coefficient);
								if (result.get(i).getScore() != null && !result.get(i).getScore().equals("")) {
									total = new BigDecimal(result.get(i).getScore());
									totalRes = total.add(totalRes);
									actual = totalRes.divide(cofe, 1, BigDecimal.ROUND_HALF_UP);
									actualRes = actual;
									if (actualRes.intValue() > 100) {
										actualRes = new BigDecimal(100);
									}
									if (totalRes.intValue() > 100) {
										totalRes = new BigDecimal(100);
									}
								}
							}
						} else {
							BigDecimal cofe = new BigDecimal(coefficient);
							if (result.get(i).getScore() != null && !result.get(i).getScore().equals("")) {
								total = new BigDecimal(result.get(i).getScore());
								totalRes = total.add(totalRes);
								actual = totalRes.divide(cofe, 1, BigDecimal.ROUND_HALF_UP);
								actualRes = actual;
								if (actualRes.intValue() > 100) {
									actualRes = new BigDecimal(100);
								}
								if (totalRes.intValue() > 100) {
									totalRes = new BigDecimal(100);
								}
							}
						}

					}
					grading = grading.subtract(actualRes);
				}
			}
			for (int i = 0; i < result.size(); i++) {
				DefectForm d = result.get(i);
				d.setActual(actualRes.toString());
				d.setActualTotal(actualRes.toString());
				d.setTotal(totalRes.toString());
				d.setGrading(grading.toString());
				this.update(d);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 计算得分
	 */
	@Override
	public void updateGrading(DefectForm defectForm, String taskInstId, String processInstId) {
		try {
			List<DefectForm> result = null;
			String sql = "select * from FORM_DEFECTIVE where PROCESS_INST_ID = '" + processInstId
					+ "' and TASK_INST_ID in (" + taskInstId + ") order by CREATION_DATE desc";
			// 获取缺陷列表
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(DefectForm.class);
			result = query.list();
			if (result != null) {
				if (result.size() > 0) {
					DefectForm df = result.get(0);
					df.setDefaultValue(defectForm.getDefaultValue());
					this.update(df);
					for (int i = 0; i < result.size(); i++) {
						DefectForm d = result.get(i);
						d.setGrading(defectForm.getGrading());
						this.update(d);
					}
				}
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 获取缺陷表单列表 //总工程师进入缺陷列表页面
	 */
	@Override
	public List<DefectForm> getDefectFormListByTaskInstIds(String processInstId, String taskInstId) {
		// 总工程验收记录表无法自动获取质量评分及等级
		List<DefectForm> list = null;

		String sql = "select * from FORM_DEFECTIVE where PROCESS_INST_ID = '" + processInstId + "'and TASK_INST_ID in ("
				+ taskInstId + ")";
		// and TASK_INST_ID in ("+taskInstId+")" ;

		try {
			// 获取缺陷列表
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(DefectForm.class);
			List<DefectForm> resultList = query.list();
			if (resultList.size() == 0) {
				sql = "select * from FORM_DEFECTIVE where PROCESS_INST_ID = '" + processInstId + "'and FORM_ID !=1";
				// and TASK_INST_ID in ("+taskInstId+")" ;
				// 获取缺陷列表
				query = this.getSession().createSQLQuery(sql);
				query.addEntity(DefectForm.class);
				resultList = query.list();
				return resultList;
			} else {

				return resultList;
			}

		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	// 获取评分记录的记录数
	@Override
	public Integer getDefectFormNum(String taskinstId, String processInstId) {

		// String sql="SELECT count(*) FROM FORM_DEFECTIVE where
		// TASK_INST_ID='"+taskinstId+"' AND PROCESS_INST_ID='"+processInstId+"' and
		// GRADING is not null";
		String sql = "SELECT count(*) FROM FORM_DEFECTIVE where  PROCESS_INST_ID='" + processInstId
				+ "' and GRADING is not null";
		Query query1 = getSession().createSQLQuery(sql);
		Integer count = Integer.valueOf(((Number) query1.uniqueResult()).intValue());

		return count;
	}
}
