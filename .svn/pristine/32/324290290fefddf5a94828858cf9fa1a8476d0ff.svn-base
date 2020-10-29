package com.ht.service.inter.system.workflow.publish;

import java.util.List;
import java.util.Map;


/**
 * 流程发布业务处理类
 * @author 王有为
 * @date 2016/10/31
 */
public interface PublishService {

	/**
	 * 发布流程
	 * @param processDefId 流程定义ID
	 */
	public void publishProcess(String launcher,String processDefId) throws Exception;
	
	/**
	 * 发布流程
	 * @param processDefId 流程定义ID
	 * @return 
	 */
	public String publishProblemProcess(String launcher,String processKey,String quality,String approval) throws Exception;

	/**
	 * 发布任务书流程
	 * @param launcher 发布人
	 * @param processDefKey 流程定义key
	 * @param taskBookIds 任务书ID集合
	 * @throws Exception
	 */
	public void publishTaskBookProcess(String launcher,String processDefKey,List<String> taskBookIds) throws Exception;
	
	public void publishCatalogProcess(String launcher, String processKey, String ids) throws Exception;

	/**
	 * 图书资料借阅流程
	 * @param loginUser 当前登陆人
	 * @param processDefKey 流程定义key
	 * @param ids 借阅图书的id，多个以逗号隔开
	 * @param borrowNos 借阅的图书的数量，多个以逗号隔开
	 * @param secretInvolved 是否涉密 
	 * @throws Exception 
	 */
	public void publishBooksProcess(String loginUser, String processDefKey, String ids,String borrowNos,String type,String secretInvolved) throws Exception;

	/**
	 * 图书资料归还流程
	 * @param loginUser 当前登陆人
	 * @param processDefKey 流程定义key
	 * @param ids 归还图书的id，多个以逗号隔开
	 * @param returnNos 归还图书的数量，多个以逗号隔开
	 * @throws Exception
	 */
	public void publishReturnBooksProcess(String loginUser,String processDefKey, String ids, String returnNos)throws Exception;

	public void publishDataInputProcess(String loginUser, String processDefKey, String ids, String bookType)throws Exception;

	/**
	 * 发布编绘任务
	 * @param launcher 发布人登录号
	 * @param tasks 任务主键号集合
	 * @throws Exception
	 */
	public void publishTaskProcess(String launcher, List<Map<String, String>> tasks,String zj,String sd) throws Exception;
}
