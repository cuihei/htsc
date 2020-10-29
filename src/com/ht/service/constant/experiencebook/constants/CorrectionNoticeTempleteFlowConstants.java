package com.ht.service.constant.experiencebook.constants;
/**
 * 改正通告模版编辑 task_def_id
 * @author huodesheng
 *
 */
public class CorrectionNoticeTempleteFlowConstants {
	/**
	 *  编辑情况记录表   编辑员、编辑时间
	 */
	public static final String  RECORDCOMPILATIONFLOW= "u_task_qkjlb";
	/**
	 * 部门质量检查记录表   编辑员、编辑时间
	 */
	public static final String  DEPARTMENTQUALITYCOMPILATIONFLOW= "u_task_bjy_queren";
	/**
	 *部门质量检查记录表  质检员、质检时间	
	 */
	public static final String  DEPARTMENTQUALITYQUALITYFLOW= "u_task_zljcjlb";
	/**
	 * 部门质量检查记录表  科长、操作时间
	 */
	public static final String  DEPARTMENTQUALITYCHIEFFLOW= "u_task_ztkz_shenhe";
	/**
	 * 中心质量检查记录表	质检员、质检时间
	 */
	public static final String  CENTREQUALITYQUALITYFLOW= "u_task_zjy_queren";
	/**
	 * 中心质量检查记录表 	审定员、审定时间
	 */
	public static final String  CENTREQUALITYVALADITONFLOW= "u_task_zxzljcjlb";
	/**
	 * 中心质量检查记录表   科长、操作时间	
	 */
	public static final String  CENTREQUALITYCHIEFFLOW= "u_task_zlkz_shenhe";
	/**
	 * 总工程师
	 */
	public static final String  ENGINNERPERSONFLOW= "u_task_zgcszlysjl";



	/**
	 * 工序流程 	创建	分配任务至通告编辑员
	 */
	public static final String CREATE = "u_task_fenpei_bjy";
	/**
	 * 工序流程 	编绘	填写本期项号
	 */
	public static final String COMPILATIONBEGIN = "u_task_bqxh";
	
	/**
	 * 工序流程 	编绘		填写编辑情况记录表
	 */
	public static final String COMPILATIONEND = "u_task_qkjlb";
	
	/**
	 * 工序流程 	部门质检 填写源数据小改正质检记录表	重复，取第一次。
	 */
	public static final String DEPARTMENTTESTING = "u_task_zljcjlb";
	
	
	/**
	 * 工序流程 	质检后改图 质检退回改图
	 */
	public static final String TESTINGCHANGE = "u_task_gaitu";
	
	/**
	 * 工序流程 	质检改后确认 填写部门质量检查记录表
	 */
	public static final String TESTINGCONFIRM = "u_task_zljcjlb";
	
	/**
	 * 工序流程 	中心审定 填写中心质量检查记录表	重复，取第一次。
	 */
	public static final String CENTRALVALIDATION = "u_task_zxzljcjlb";
	
	/**
	 * 工序流程 	审定后改图 改图质检
	 */
	public static final String VALADITONDCHANGE = "u_task_gaitu_zj";
	
	/**
	 * 工序流程 	审定改后确认 填写中心质量检查记录表
	 */
	public static final String VALADITONDCONFIRM = "u_task_zxzljcjlb";
	/**
	 * 工序流程 	排除审定后改图  审定退回改图
	 */
	public static final String EXCLUDEVALADITONDCONFIRM = "u_task_sdgaitu";


}
