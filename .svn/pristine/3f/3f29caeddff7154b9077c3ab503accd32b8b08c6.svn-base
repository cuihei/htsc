package com.ht.service.impl.background.organization.organization;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.Tree;
import com.ht.common.util.TreeNode;
import com.ht.persistence.dao.inter.background.organization.organization.OrganizationDao;
import com.ht.persistence.dao.inter.background.organization.organization.OrganizationUsersRelationDao;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;
import com.ht.service.inter.background.organization.organization.OrganizationService;

/**
 * 组织机构
 * @author 侯晨
 */
public class OrganizationServiceImpl implements OrganizationService {
	/**
	 * 注入组织机构organizationDao
	 * */
	@Resource(name = "organizationDao") 
	private OrganizationDao organizationDao;
	
	/**
	 * 注入组织机构人员organizationDao
	 * */
	@Resource(name = "organizationUsersRelationDao") 
	private OrganizationUsersRelationDao organizationUsersRelationDao;
	
	/**
	 * 新增组织机构
	 * @param organizationParm 
	 * @throws Exception
	 */
	@Override
	public void addOrganization(String organizationParm) throws Exception {
		try {
			// 将用户String类型转成Organization类型
			Organization organization = (Organization) DataConverter.convertJson2Object(organizationParm, Organization.class);
			if(organization.getParentId().equals("")){//根组织传回来的parentId为空，直接设置为根组织的id
				organization.setParentId("1019142929270002");
			}
			// 如果id存在，则执行修改操作
			// 先进行判重操作
			
			if(organization.getId()!=null){
				// 判断组织机构名称如果存在则不能修改（排除自己）
				/*List<Organization> list = organizationDao.getOrganization();
				// 排除自己后的组织机构列表
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getId().equals(organization.getId())){//排除自己
						list.remove(i);
					}
				}
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getOrgName().equals(organization.getOrgName())){//如果登陆账号已存在
						Exception e = new Exception();
						throw e;
					}
				}*/
				organizationDao.modifyOrganization(organization);
			}else{//如果id不存在，则执行新增操作
				// 如果组织机构名称已经存在
				List<Organization> list = organizationDao.getOrganization();
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getOrgName().equals(organization.getOrgName())){//如果登陆账号已存在
						Exception e = new Exception();
						throw e;
					}
				}
				organization.setId(GenerateSequenceUtil.generateSequenceNo());
				organizationDao.addOrganization(organization);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 更新组织机构
	 * @param organizationParm 
	 * @throws Exception
	 */
	@Override
	public void modifyOrganization(String organizationParam) throws Exception {
		try {
			//将用户String类型转成Organization类型
			Organization organization = (Organization) DataConverter.convertJson2Object(organizationParam, Organization.class);
			organizationDao.modifyOrganization(organization);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除组织机构
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public void delOrganization(String organizationParam) throws Exception {
		try {
			// 将机构String类型转成Org对象
			Organization org = (Organization) DataConverter.convertJson2Object(organizationParam,Organization.class);
			// 获得包含本身的树结构，下面的所有子节点都能取到
			List<Organization> result = organizationDao.getOrganizationChilds(org);
			//先删除组织人员关系，全部放到公共部门中
			for (int i = 0; i < result.size(); i++) {//循环每一个机构
				OrganizationUsersRelation our = new OrganizationUsersRelation();
				our.setOrgId(result.get(i).getId());
				// 根据orgId取得所有该机构下的人员
				List<OrganizationUsersRelation> list = organizationUsersRelationDao.getUsersByOrgId(our);
				if(list.size()>0){
					for (int j = 0; j < list.size(); j++) {
						// 删除机构下的人员
						organizationUsersRelationDao.delOrganizationUsersRelation(list.get(j));
						// 在将人员放到公共部门中
						OrganizationUsersRelation orgUser = new OrganizationUsersRelation();
						orgUser.setId(GenerateSequenceUtil.generateSequenceNo());
						orgUser.setOrgId("10191641226710006");//公共部门的id
						orgUser.setUserId(list.get(j).getUserId());
						orgUser.setCreator("");
						orgUser.setCreationDate(new Date());
						orgUser.setModifier("");
						orgUser.setModifyDate(new Date());
						organizationUsersRelationDao.addOrganizationUsersRelation(orgUser);
					}
				}
				organizationDao.delOrganization(result.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询组织机构
	 * @throws Exception
	 */
	@Override
	public List<Organization> getOrganization() throws Exception {
		try {
			// 获取所有Organization
			return organizationDao.getOrganization();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id获取查询组织机构
	 * @throws Exception
	 */
	@Override
	public Organization getOrganization(String id) throws Exception {
		try {
			// 根据id获取Organization
			Organization organization = new Organization();
			organization.setId(id);
			return organizationDao.getOrganization(organization);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
		
	
	
	

	/**
	 * 获取Organization对象父节点下的所有子节点，形成树结构
	 * @return 返回树结构字符串
	 */
	@Override
	public String getOrganizationTree() throws Exception {
		try {
			List<Organization> result = organizationDao.getOrganizationTree();
			Tree tree = new Tree();
			TreeNode root = null;
			for (int i = 0; i < result.size(); i++) {
				TreeNode node = new TreeNode(result.get(i).getId(), result.get(i).getOrgName(), result.get(i).getParentId());
				if (result.get(i).getParentId() == null) {
					root = node;
				}
				else{
					tree.addNode(node);
				}
			}
			return tree.getTreeJson(tree, root);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

}
