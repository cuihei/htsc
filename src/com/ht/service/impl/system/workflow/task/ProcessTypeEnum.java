package com.ht.service.impl.system.workflow.task;

/**
 * 流程业务类型枚举
 * @author 王有为
 * @date 2016/11/2
 */
public enum ProcessTypeEnum {
	/** 任务书*/
	TASK_BOOK,
//	TASK_BOOK_SEA_MAP,
//	TASK_BOOK_CORRECTION_NOTICE,
//	TASK_BOOK_SMALL_CORRECTION,
//	TASK_BOOK_PROJECT_SPECIAL,
//	TASK_BOOK_OTHER_NAVIGATIONAL,
	/** 源数据编绘总流程*/
	SEA_MAP_COMPILATION_SOURCE_DATA_ALL,
	/** 海图编绘源数据编绘*/
	SEA_MAP_COMPILATION_SOURCE_DATA,
	/** 海图编绘源数据编绘计划*/
	SEA_MAP_COMPILATION_SOURCE_DATA_PLAN,
	/** 纸海图编绘总流程*/
	SEA_MAP_COMPILATION_PAPER_ALL,
	/** 海图编绘纸海图编绘计划*/
	SEA_MAP_COMPILATION_PAPER_PLAN,
	/** 海图编绘纸海图编绘*/
	SEA_MAP_COMPILATION_PAPER,
	/** 电子海图编绘总流程*/
	SEA_MAP_COMPILATION_ELECTRONIC_ALL,
	/** 海图编绘电子海图编绘*/
	SEA_MAP_COMPILATION_ELECTRONIC,
	/** 海图编绘电子海图编绘计划*/
	SEA_MAP_COMPILATION_ELECTRONIC_PLAN,
	/** 改正通告 源数据小改正*/
	CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION,
	/** 改正通告 编辑*/
	CORRECTION_NOTICE_TEMPLATE,
	/** 改正通告 模版编辑*/
	CORRECTION_NOTICE_TEMPLATE_EDIT,
	/** 小改正 纸海图*/
	SMALL_CORRECTION_PAPER,
	/** 小改正 电子海图*/
	SMALL_CORRECTION_ELECTRONIC,
	/** 工程&专题图纸海图*/
	PROJECT_SPECIAL_PAPER,
	/** 工程&专题图电子海图*/
	PROJECT_SPECIAL_ELECTRONIC,
	/** 其他航海图*/
	OTHER_NAVIGATIONAL,
	/** 目录管理*/
	CATALOG_MANAGEMENT,
	/** 资料借阅*/
	DATA_BORROWING,
	/** 资料录入*/
	DATA_INPUT,
	/** 资料归还*/
	DATA_RETURN,
	BOOK_INFO,
	BOOKS,
	FILED_DATA,
	PROBLEM_SUBMIT,
}
