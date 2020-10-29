package com.ht.service.constant.experiencebook.constants;
/**
 * 源数据小改正流程节点 task_def_id
 * @author huodesheng
 *
 */
public class SourceDataSamllFlowConstants {
	/**
	 * 问题处理记录表  编绘人，编绘时间
	 */
	public static final String PROBLEMCOMPILATIONFLOW = "u_task_wtcljlb";
	/**
	 * 问题处理记录表  质检人，质检时间
	 */
	public static final String PROBLEMQUALITYFLOW = "u_task_clyj";
	/**
	 * 质检记录表 科长、操作时间
	 */
	public static final String QUALITYCHIEFFLOW = "u_task_ztkz_shenhe";
	/**
	 * 质检记录表 质检人，质检时间
	 */
	public static final String TESTQUALITYFLOW = "u_task_zjjilub";	
	
	/**
	 * 审定记录表  审定人，审定日期
	 */
	public static final String VALADITIONFLOW = "u_task_sdjilub";
	/**
	 * 审定记录表 科长，科长审定时间
	 */
	public static final String VALADITONAPPROVEFLOW = "u_task_zlkz_shenhe";
	
	/**
	 * 总工程师
	 */
	public static final String ENGINNERPERSONFLOW = "u_task_zgcs_shenhe";
	
	

	/**
	 * 工序流程 	创建	分配任务至通告编辑员
	 */
	public static final String CREATE = "u_task_fenpei_bjy";
	/**
	 * 工序流程 	编绘	填写本期项号
	 */
	public static final String COMPILATIONBEGIN = "u_task_gcmc";
	
	/**
	 * 工序流程 	编绘		填写编辑情况记录表
	 */
	public static final String COMPILATIONEND = "u_task_bjqkjlb";
	
	/**
	 * 工序流程 	部门质检 填写源数据小改正质检记录表	重复，取第一次。
	 */
	public static final String DEPARTMENTTESTING = "u_task_zjjilub";
	
	
	/**
	 * 工序流程 	质检后改图 修改资料登记表
	 */
	public static final String TESTINGCHANGE = "u_task_xgzldjb";
	
	/**
	 * 工序流程 	质检改后确认 填写源数据小改正质检记录表
	 */
	public static final String TESTINGCONFIRM = "u_task_zjjilub";
	
	/**
	 * 工序流程 	中心审定  填写源数据小改正审定记录表 	重复，取第一次。
	 */
	public static final String CENTRALVALIDATION = "u_task_sdjilub";
	
	/**
	 * 工序流程 	审定后改图 改图质检
	 */
	public static final String VALADITONDCHANGE = "u_task_gaitu_zj";
	
	/**
	 * 工序流程 	审定改后确认  填写源数据小改正审定记录表
	 */
	public static final String VALADITONDCONFIRM = "u_task_sdjilub";
	/**
	 * 工序流程 	排除审定后改图  修改内容记录表
	 */
	public static final String EXCLUDEVALADITONDCONFIRM = "u_task_sdxgnrjlb";
	
	
	
}
