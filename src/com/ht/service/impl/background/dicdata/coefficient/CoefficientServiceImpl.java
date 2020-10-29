package com.ht.service.impl.background.dicdata.coefficient;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.coefficient.CoefficientDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.inter.background.dicdata.coefficient.CoefficientService;

/**
 * @ClassName: DefectServiceImpl
 * @Description: TODO(调整系数处理类)
 * @author penghao
 * @date 2016年11月22日 下午7:36:22
 * 
 */
public class CoefficientServiceImpl implements CoefficientService {

	@Resource
	private CoefficientDao coefficientDao;

	/**
	 * 获取调整系数列表
	 */
	@Override
	public List<Coefficient> getList() {
		return coefficientDao.getList();
	}

	/**
	 * 保存数据
	 */
	@Override
	public void add(String params) throws Exception {
		try {
			// 将json格式数据转换成对象
			Coefficient cff = (Coefficient) DataConverter.convertJson2Object(params, Coefficient.class);
			String scale = cff.getScale();

			if (scale != null) {
				if (scale.split(":").length > 1) {
					scale = scale.split(":")[1];
				}
			}
			if (cff.getId().length() > 0) {
				// 更新数据
				cff.setScale(scale);
				coefficientDao.modifyCoefficient(cff);
			} else {
				// 截取比例尺
				cff.setScale(scale);
				// 生成id
				cff.setId(GenerateSequenceUtil.generateSequenceNo());
				// 保存数据
				coefficientDao.add(cff);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 删除数据
	 */
	@Override
	public void delete(String ids) {
		try {
			// 将用户String类型转化成集合
			@SuppressWarnings("unchecked")
			List<Coefficient> list = (List<Coefficient>) DataConverter.convertJson2List(ids, Coefficient.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除对象
				coefficientDao.delCoefficient(list.get(i));
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 根据id获取调整系数
	 */
	@Override
	public Coefficient getCoefficientById(String id) {
		Coefficient cff = new Coefficient();
		cff.setId(id);
		return coefficientDao.getCoefficientById(cff);
	}

	/**
	 * 根据mapNo获取调整系数
	 */
	@Override
	public List<Coefficient> getCoefficientBymapNo(String mapNo) {
		Coefficient cff = new Coefficient();
		cff.setMapNo(mapNo);
		return coefficientDao.getCoefficientByMapNo(cff);
	}

	/**
	 * 获取调整系数
	 */
	@Override
	public Coefficient getCoefficient(String mapNo, String typeId) {
		Coefficient coefficient = null;
		try {
			Coefficient cff = new Coefficient();
			// 处理图号
			if (mapNo.indexOf("(") > 0) {
				mapNo = mapNo.substring(0, mapNo.indexOf("("));
			}
			/// 2018.5.17 质检找不到带()的图号 现添加中文的括号。
			if (mapNo.indexOf("（") > 0) {
				mapNo = mapNo.substring(0, mapNo.indexOf("（"));
			}
			cff.setMapNo(mapNo);
			BaseData data = new BaseData();
			data.setId(typeId);
			cff.setType(data);
			coefficient = coefficientDao.getCoefficient(cff);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return coefficient;
	}

	@Override
	public int mapcount(String mapno, String typeid) {
		int mapcount = coefficientDao.mapcount(mapno, typeid);
		return mapcount;
	}

	public List<String> mapNoexi(String mapno) {
		List<String> mapNoexi = coefficientDao.mapNoexi(mapno);
		return mapNoexi;
	}

}