package com.ht.persistence.dao.impl.background.dicdata.coefficient;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.coefficient.CoefficientDao;
import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;
import com.ht.persistence.model.system.workflow.task.RuTask;

/** 
* @ClassName: CoefficientDaoImpl 
* @Description: TODO(调整系数数据操作实现类) 
* @author penghao
* @date 2016年11月22日 下午7:50:41 
*  
*/
@SuppressWarnings("unchecked")
public class CoefficientDaoImpl extends BaseDaoImpl implements CoefficientDao {

	/**
	 * 获取调整系数列表
	 */
	@Override
	public List<Coefficient> getList() {
		List<Coefficient> list = null;
		try {
			list = this.findByNamedQuery("getList");
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 添加调整系数数据
	 */
	@Override
	public void add(Coefficient coefficient) {
		this.save(coefficient);
	}

	/**
	 * 更新调整系数数据
	 */
	@Override
	public void modifyCoefficient(Coefficient coefficient) {
		this.update(coefficient);
	}

	/**
	 * 删除调整系数
	 */
	@Override
	public void delCoefficient(Coefficient coefficient) {
		this.delete(coefficient);
	}

	/**
	 * 根据id获取调整系数
	 */
	@Override
	public Coefficient getCoefficientById(Coefficient coefficient) {
		List<Coefficient> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getCoefficientById", "id", coefficient.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list.get(0);
	}
	

	/**
	 * 判断数据是否存在
	 */
	@Override
	public List<Coefficient> isExsit(Coefficient coefficient) {
		List<Coefficient> list = null;
		String[] names = {"mapNo","typeId"};
		String mapNo = null;
		if(StringUtils.isNotBlank(coefficient.getMapNo())){
			mapNo = coefficient.getMapNo();
		}
		String typeId = null;
		if(coefficient.getType() != null && StringUtils.isNotBlank(coefficient.getType().getId())){
			typeId = coefficient.getType().getId();
		}
		String[] params = {mapNo,typeId};
		try {
			list = this.findByNamedQueryAndNamedParam("getDefect", names, params);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取调整系数
	 */
	@Override
	public Coefficient getCoefficient(Coefficient coefficient) {
		List<Coefficient> list = null;
		String[] names = {"mapNo","typeId"};
		String mapNo = null;
		if(StringUtils.isNotBlank(coefficient.getMapNo())){
			mapNo = coefficient.getMapNo();
		}
		String typeId = null;
		if(coefficient.getType() != null && StringUtils.isNotBlank(coefficient.getType().getId())){
			typeId = coefficient.getType().getId();
		}
		String[] params = {mapNo,typeId};
		try {
			list = this.findByNamedQueryAndNamedParam("getDefect", names, params);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list.get(0);
	}

	@Override
	public int mapcount(String mapno, String typeid) {
	    String sql = "SELECT * from coefficient where map_no='"+mapno+"' and TYPE_ID='"+typeid+"'";
	    SQLQuery query = getSession().createSQLQuery(sql);
	    int count = query.list().size();
	    return count;
	}
	
	
	@Override
	public  List<Coefficient> getCoefficientByMapNo(Coefficient coefficient) {
		List<Coefficient> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getCoefficientByMapNo", "mapNo", coefficient.getMapNo());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
	

	
	
	@Override
	public List<String> mapNoexi(String mapno) {
		List<String> list=new ArrayList<String>();
		List<String> list1=new ArrayList<String>();
		// var type_id = ["1106193540120002", "11061936168310003","11061935001990001"];
		list.add("11061935001990001");
		list.add("1106193540120002");
		list.add("11061936168310003");
		for (int i = 0; i < list.size(); i++) {
		    String sql = "SELECT * from coefficient where map_no='"+mapno+"' and TYPE_ID='"+list.get(i)+"'";
		    SQLQuery query = getSession().createSQLQuery(sql);
		    int count = query.list().size();	
			if (count > 0) {
				list1.add(list.get(i));
			}
		}
	    return list1;
	}



}
