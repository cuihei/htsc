package com.ht.service.impl.background.dicdata.type;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.type.TypeDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.dicdata.type.TypeService;

/**
 * 类别数据实现类
 * @author liukai
 */
public class TypeServiceImpl implements TypeService {
	
	/**
	 * 注入类别数据Dao
	 * */
	@Resource(name = "typeDao") 
	private TypeDao typeDao;
	
	/**
	 * 新增类别数据
	 * @param typeParam 类别数据
	 */
	@Override
	public Map<String,Object> addType(String typeParam) throws Exception {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			//将类别String类型转成Type类型
			Type type = (Type) DataConverter.convertJson2Object(typeParam, Type.class);
			List<Type> list = typeDao.getTypeByName(type);
			if(null!=list&&list.size()>0){
				map.put("msg","保存失败，字典类别:"+type.getName()+"已存在！");
				map.put("flag", false);
				return map;
			}else{
				type.setId(GenerateSequenceUtil.generateSequenceNo());
				//执行保存操作
				typeDao.addType(type);
				map.put("msg","字典类别:"+type.getName()+"保存成功！");
				map.put("flag", true);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改类别数据
	 * @param typeParam 类别数据
	 */
	@Override
	public Map<String,Object> modifyType(String typeParam) throws Exception {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			//将类别String类型转成Type类型
			Type type = (Type) DataConverter.convertJson2Object(typeParam, Type.class);
			List<Type> list = typeDao.getTypeByName(type);
			if(null!=list&&list.size()>0){
				if(list.size()==1&&type.getId().equals(list.get(0).getId())){
					// 更新Type
					typeDao.modifyType(type);
					map.put("msg","字典类别:"+type.getName()+"保存成功！");
					map.put("flag", true);
					return map;
				}
				map.put("msg","保存失败，字典类别:"+type.getName()+"已存在！");
				map.put("flag", false);
				return map;
			}else{
				// 更新Type
				typeDao.modifyType(type);
				map.put("msg","字典类别:"+type.getName()+"保存成功！");
				map.put("flag", true);
				return map;
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	/**
	 * 修改类别数据
	 * @param typeParam 类别数据
	 */
	@Override
	public Map<String,Object> modifyTypeById(String typeParam) throws Exception {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			//将类别String类型转成Type类型
			Type type = (Type) DataConverter.convertJson2Object(typeParam, Type.class);
			List<Type> list = typeDao.getTypeByName(type);
			if(null!=list&&list.size()>0){
				if(list.size()==1&&type.getId().equals(list.get(0).getId())){
					Type type2 = typeDao.getType(type);
					if (type2!=null) {
						type.setCreationDate(type2.getCreationDate());
						type.setCreator(type2.getCreator());
					}
					type.setModifyDate(new Date());
					typeDao.evict(type2);
					// 更新Type
					typeDao.modifyType(type);
					map.put("msg","字典类别:"+type.getName()+"保存成功！");
					map.put("flag", true);
					return map;
				}
				map.put("msg","保存失败，字典类别:"+type.getName()+"已存在！");
				map.put("flag", false);
				return map;
			}else{
				Type type2 = typeDao.getType(type);
				if (type2!=null) {
					type.setCreationDate(type2.getCreationDate());
					type.setCreator(type2.getCreator());
				}
				type.setModifyDate(new Date());
				typeDao.evict(type2);
				// 更新Type
				typeDao.modifyType(type);
				map.put("msg","字典类别:"+type.getName()+"保存成功！");
				map.put("flag", true);
				return map;
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除一条类别数据
	 * @param id 类别数据主键Id
	 */
	@Override
	public void delType(String id) throws Exception {
		try {
			Type type = new Type();
			type.setId(id);
			// 删除User
			typeDao.delType(type);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	/**
	 * 删除基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void delTypes(String ids) throws Exception {
		try {
			 List<Type> list = (List<Type>) DataConverter.convertJson2List(ids, Type.class);
			 for(int i = 0 ; i < list.size() ; i++){
				 Type type = list.get(i);
				 typeDao.delType(type);
			 }
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取人员列表
	 */
	@Override
	public List<Type> getType() throws Exception {
		try {
			// 获取所有Type
			return typeDao.getType();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 根据类别id获取类别数据
	 * @param id 类别数据主键Id
	 */
	@Override
	public Type getType(String id) throws Exception {
		try {
			Type type = new Type();
			type.setId(id);
			// 根据id获取Type
			return typeDao.getType(type);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
