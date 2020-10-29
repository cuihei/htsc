package com.ht.persistence.dao.impl.background.organization.employee;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.employee.UserOrgView;

/**
 * User 数据操作类
 * @author liukai 
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	/**
	 * 增加一个User数据
	 * @param user User实体
	 */
	@Override
	public void addUser(User user) {
		//执行保存操作
		this.save(user);
	}

	/**
	 * 更新一个User数据
	 * @param user User实体
	 */
	@Override
	public void modifyUser(User user) {
		user  = (User) this.merge(user);
		//执行修改操作
		this.update(user);
	}
	
	/**
	 * 计算得分
	 */
	@Override
	public void modifyPassword(User user)
	{
		try
		{
			
			String sql =  "update User u set password = '"+user.getPassword()+"' where id ='"+user.getId()+"'";
			
			this.getSession().createQuery(sql).executeUpdate();		
			
			
			/*String hql = "update User u set password = '"+user.getPassword()+"' where id ='"+user.getId()+"'";
			Query query = this.getSession().createQuery(hql);
			query.executeUpdate();
			*/
			
		
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 删除一条User数据
	 * @param user User实体
	 */
	@Override
	public void delUser(User user) {
		//执行删除操作
		this.delete(user);
	}

	/**
	 * 获取所有User数据
	 * @return List<User> 用户信息列表
	 */
	@Override
	public List<User> getUserList() {
		try {
			@SuppressWarnings("unchecked")
			//执行查询操作
			List<User> result = this.findByNamedQuery("getUser");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}



	@Override
	public List<User> getUserByUserIds(List<String> ids)
	{
		//执行查询操作
		List<User> result = this.findByNamedQueryAndNamedParam("getUserByUserIds", "ids", ids);
		return result;
	}
	
	/**
	 * 获取一条User
	 * @param user User实体
	 * @return User实体
	 */
	@Override
	public User getUser(User user) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<User> result = this.findByNamedQueryAndNamedParam("getUserById", "id", user.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 通过userNo登陆账号获取用户信息
	 * @param user实体 
	 * @return user实体
	 */
	@Override
	public List<User> getUserByNo(User user) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<User> result = this.findByNamedQueryAndNamedParam("getUserByNo", "userNo", user.getUserNo());
		return result;
	}

	/**
	 * 通过userNo登陆账号和密码获取用户信息
	 * @param user实体 
	 * @return 员工列表
	 */
	@Override
	public List<User> getUserByNoAndPassword(User user) {
		SQLQuery query = this.getSession().createSQLQuery("select * from USERS t where t.user_no = ? and t.password = ?");
		query.setString(0,user.getUserNo());
		query.setString(1,user.getPassword());
		query.addEntity(User.class);
		@SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		return userList;
	}
	
	/**
	 * 获取所有UserOrgView数据
	 * @return List<UserOrgView> 用户信息列表
	 */
	@Override
	public List<UserOrgView> getUserOrgViewList() {
		try {
			@SuppressWarnings("unchecked")
			//执行查询操作
			List<UserOrgView> result = this.findByNamedQuery("getUserOrgView");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<UserOrgView> getUserOrgViewByOrgId(UserOrgView user) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<UserOrgView> result = this.findByNamedQueryAndNamedParam("getUserOrgViewByOrgId", "orgId", user.getOrgId());
		return result;
	}
}
