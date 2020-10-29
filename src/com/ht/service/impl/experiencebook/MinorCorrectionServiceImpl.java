package com.ht.service.impl.experiencebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.exception.CommonException;
import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.dao.inter.drawtask.tasksplit.TaskSplitDao;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.ChartStatusConstants;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleCompilationRecord;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleCorrectionNoRecord;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleHead;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleMapNoRecord;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleQualityTestingRecord;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.EleValidationRecord;
import com.ht.service.constant.experiencebook.electronicchartsmallcorrection.ElectronSmallPersonAndDate;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.CompilationRecord;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.Head;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.PaperItemNoIssue;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.PaperSmallPersonAndDate;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.QualityAcceptanceRecord;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.QualityTestingRecord;
import com.ht.service.constant.experiencebook.paperchartsmallcorrection.ValidationRecord;
import com.ht.service.constant.experiencebook.sourcedata.SourceDataPersonAndDate;
import com.ht.service.constant.experiencebook.sourcedatacorrection.ContentRecord;
import com.ht.service.constant.experiencebook.sourcedatacorrection.Cover;
import com.ht.service.constant.experiencebook.sourcedatacorrection.DataRegister;
import com.ht.service.constant.experiencebook.sourcedatacorrection.EngineeringRecord;
import com.ht.service.constant.experiencebook.sourcedatacorrection.HomePage;
import com.ht.service.constant.experiencebook.sourcedatacorrection.ProblemRecord;
import com.ht.service.constant.experiencebook.sourcedatacorrection.SDCQualityRecord;
import com.ht.service.constant.experiencebook.sourcedatacorrection.SDCValidationRecord;
import com.ht.service.constant.experiencebook.sourcedatacorrection.SourceDataSamllPersonAndDate;
import com.ht.service.constant.model.PersonAndDate;
import com.ht.service.inter.experiencebook.MinorCorrectionService;

/**
 * 小改正导出经历薄数据类
 * @author houchen
 */
public class MinorCorrectionServiceImpl implements MinorCorrectionService {
	
	/**
	 * 拆分dao
	 */
	@Resource
	TaskSplitDao taskSplitDao;
	
	@Resource
	FlowsDao flowsDao;
	
	@Resource
	TaskBookDao taskBookDao;
	
	DefectFormDao defectFormDao = null;
	
	@Resource
	CreateTaskDao createTaskDao;
	
	/**
	 * 源数据小改正编绘经历簿导出
	 * @param processInstId
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> sourceDataMap(String processInstId)
			throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始源数据小改正数据准备
			 */
			String[] processDefKeys =
			{ "CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION"};
			// 获取当前流程实例操作的表数据
			// 编绘作业流程ID
			String workProcessInstId = processInstId;
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);
			
			/**
			 * 封皮
			 */
			Cover cover = new Cover();
			// 设置实施日期
			cover.setTEXT_COVER_DATE(taskBook.getExecuteTime() == null ? null : sdf.format(taskBook.getExecuteTime()));
			// 设置部门
			cover.setTEXT_COVER_DEPARTMENT("制图事业部");
			// 设置封皮logo图片（路径）
			cover.setIMG_COVER_LOGO("");
			// 改正期号
			//cover.setTEXT_COVER_CORRECT_NO(taskBook.getCorrectNo());
			// 工程名称
			//cover.setTEXT_COVER_PROJECT_NAME(taskBook.getProjectName());
			// 获得封皮
			Map<String, Object> map = cover.getCoverMap();
			data.put(cover.MARKER, map);
			
			
			/**
			 * 首页
			 */
			HomePage homePage = new HomePage();
			homePage.setINSTRUCTION_TITLE("说明");
			String instructionContent = "1、源数据小改正经历簿是源数据小改正过程的真实记录和技术档案，必须认真填写，不得任意涂改。"+
										"2、源数据小改正过程中有关工序流转、资料采用、改正内容、质量检查等信息必须详细记载，以便追溯。"+
										"3、每一道工序完成后作业人员应及时填写经历簿，且应做到填写内容准确无误，格式规范统一、文字简练清晰。"+
										"4、源数据小改正经历簿应与其它小改正资料一起流转，工程结束后归档保存。";
			homePage.setINSTRUTION_CONTENT(instructionContent);
			homePage.setTITLE("中华人民共和国海事局");
			map = homePage.getHomePageMap();
			data.put(homePage.MARKER, map);
			/**
			 * 源数据小改正工序流程表
			 */
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			SourceDataSamllPersonAndDate pd=new SourceDataSamllPersonAndDate(flowListAll);
			//添加节点
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text_gx_order", "交出版管理科");
			pd.getListMap().add(m);
			data.put(new com.ht.service.constant.experiencebook.sourcedatacorrection.Flows().MARKER, pd.getListMap());
			/**
			 * 改正期号&工程名称
			 */
			EngineeringRecord engineeringrecord =new EngineeringRecord();
			List<List<FormValue>> engineeringrecords = base.getFormValueByRowFlag(engineeringrecord.EngineeringRecord_FORM_ID, planProcessInstId);
			if (engineeringrecords != null)
			{
				if (engineeringrecords.size() > 0)
				{
					engineeringrecord =new EngineeringRecord();
					for (List<FormValue> list : engineeringrecords) {
						engineeringrecord.setDataProps(list);
					}
					data.put(engineeringrecord.MARKER, engineeringrecord.getMap());
				}
			}
			/**
			 * 源数据小改正资料登记表
			 */
			DataRegister dataregister=new DataRegister();
			listMap = new ArrayList<Map<String, Object>>();
			List<List<FormValue>> dataregisters = base.getFormValueByRowFlag(dataregister.getYGZZLDJB_FORM_ID(), planProcessInstId);
			for (int i = 0; i < dataregisters.size(); i++) {
				 dataregister=new DataRegister();
				dataregister.setDataProps(dataregisters.get(i));
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(dataregister.getMap());
				listMap.add(maps);
			}
			data.put(dataregister.getMARKER(), listMap);
			/**
			 * 源数据小改正内容记录表
			 */
			ContentRecord contentrecord=new ContentRecord();
			listMap = new ArrayList<Map<String, Object>>();
			List<List<FormValue>> contentrecords = base.getFormValueByRowFlag(contentrecord.getContentRecord_FORM_ID(), planProcessInstId);
			for (int i = 0; i < contentrecords.size(); i++) {
				contentrecord=new ContentRecord();
				contentrecord.setDataProps(contentrecords.get(i));
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(contentrecord.getMap());
				listMap.add(maps);
			}
			data.put(contentrecord.getMARKER(), listMap);
			/**
			 * 源数据小改正问题处理记录表
			 */
			ProblemRecord problemrecord=new ProblemRecord();
			listMap = new ArrayList<Map<String, Object>>();
			List<List<FormValue>> problemrecords = base.getFormValueByRowFlag(problemrecord.getYXGZWTCLB_FORM_ID(), planProcessInstId);
			for (int i = 0; i < problemrecords.size(); i++) {
				problemrecord=new ProblemRecord();
				problemrecord.setDataProps(problemrecords.get(i));
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(problemrecord.getMap());
				listMap.add(maps);
			}
	
			problemrecord.setCOMPILATION_PERSON(pd.getProblemCompilationPerson());
			problemrecord.setCOMPILATION_DATE(pd.getProblemCompilationDate());
			problemrecord.setQUALITY_PERSON(pd.getProblemQualityPerson());
			problemrecord.setQUALITY_DATE(pd.getProblemQualityDate());
			Map<String, Object> problemrecordMap = problemrecord.getMap();
			problemrecordMap.put(problemrecord.getMARKER(), listMap);
			data.put(problemrecord.getMARKER(), problemrecordMap);
			
			/**
			 * 编绘作业
			 */
			
			/**
			 * 源数据小改正质检记录表
			 */
			SDCQualityRecord sdcqualityrecord = new SDCQualityRecord();
			map = getSDCQualityDefective(workProcessInstId, pd.getTestQualityPerson(), pd.getTestQualityDate(), null,pd.getTestQualityApprovePerson(),pd.getTestQualityApproveDate());
			data.put(sdcqualityrecord.MARKER, map);
			
			/**
			 * 源数据小改正审定记录表
			 */
			SDCValidationRecord sdcvalidationrecord= new SDCValidationRecord();
			map = getSDCValidationDefective(workProcessInstId, pd.getValaditionPerson(), pd.getValaditionDate(), null, pd.getValaditonApprovePerson(), pd.getValaditonApproveDate());
			data.put(sdcvalidationrecord.MARKER, map);
			/**
			 *总工程师质量验收记录表
			 */
			com.ht.service.constant.experiencebook.sourcedatacorrection.QualityAcceptanceRecord qa=new com.ht.service.constant.experiencebook.sourcedatacorrection.QualityAcceptanceRecord();
			List<List<FormValue>> qaList = base.getFormValueByRowFlag(qa.getZGCSZLYSB_FORM_ID(), workProcessInstId);
			listMap = new ArrayList<Map<String, Object>>();
			// 验收结论
			String ysjl = "";
			// 质量评分及等级
			String pfdj = "";
			if(qaList!=null){
				if(qaList.size()>0){
					for (int i = 0; i < qaList.size(); i++)
					{
						qa = new com.ht.service.constant.experiencebook.sourcedatacorrection.QualityAcceptanceRecord();
						List<FormValue> qaListI = qaList.get(i);
						qa.setDataProps(qaListI);
						Map<String, Object> mapI = qa.getQualityAcceptanceRecordMap();
						ysjl += mapI.get("text_zgcszlysb_ysjl") == null ? "" : mapI.get("text_zgcszlysb_ysjl").toString();
						pfdj += mapI.get("text_zgcszlysb_pfdj") == null ? "" : mapI.get("text_zgcszlysb_pfdj").toString();
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(mapI);
						listMap.add(maps);
					}
				}
			}
			qa = new com.ht.service.constant.experiencebook.sourcedatacorrection.QualityAcceptanceRecord();
			qa.setCHIEF_ENGINEER(pd.getEnginnerAdvice()==null?"同意":pd.getEnginnerAdvice());
			qa.setCHIEF_ENGINEER_DATE(pd.getEnginnerDate());
			// 总工程师签章
			qa.setCHIEF(pd.getEnginnerPerson());
			// 验收结论 
			qa.setYSJL(ysjl);
			qa.setPFDJ(pfdj);
			map = qa.getQualityAcceptanceRecordMap();
			map.put(qa.MARKER, listMap);
			data.put(qa.MARKER, map);
			return data;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 纸海图小改正经历簿导出
	 * @param processInstId
	 * @return 
	 */
	@Override
	public Map<String, Object> paperMap(String processInstId) throws Exception {
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			/**
			 * 开始纸海图小改正数据准备
			 */
			String[] processDefKeys =
				{ "SMALL_CORRECTION_PAPER"};
			// 获取当前流程实例操作的表数据
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);
			
			/**
			 * 纸海图小改正经历簿
			 */
			Head head = new Head();
			//设置标题
			head.setTITLE("纸海图小改正经历簿");
			//设置图名
			head.setMAP_NAME(complicationTask.getTaskName());
			//设置图号
			head.setMAP_NO(complicationTask.getMapNo());
			//设置版次
			head.setREVISION(complicationTask.getRevision());
			//设置比例尺
			head.setSCALE(complicationTask.getScale());
//			if (taskBook != null) {
//				//设置改正通告期号
//				head.setNOTICENO(taskBook.getCorrectNoticeNo());
//				//设置改正项号
//				head.setITEMNO(taskBook.getCorrectNo());
//			}
			//获得纸海图小改正经历簿
			map = head.getHeadMap();
			data.put(head.MARKER, map);
			/**
			 * 设置改正期号,改正项号 
			 */
			PaperItemNoIssue paperitemnoissue=new PaperItemNoIssue();
			List<List<FormValue>> paperitemnoissues = base.getFormValueByRowFlag(paperitemnoissue.PaperItemNoIssue_FORM_ID, planProcessInstId);
			if(paperitemnoissues!=null){
				if(paperitemnoissues.size()>0){
					for (List<FormValue> list : paperitemnoissues) {
						paperitemnoissue.setDataProps(list);
					}
					data.put(paperitemnoissue.MARKER, paperitemnoissue.getMap());
				}
			}
			
			/**
			 * 纸海图小改正编绘记录表
			 */
			List<Flows> flowList = new ArrayList<Flows>();
			
			CompilationRecord cr = new CompilationRecord();
			List<List<FormValue>> problemList = base.getFormValueByRowFlag(cr.COMPILATION_ID, planProcessInstId);
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < problemList.size(); i++)
			{
				cr = new CompilationRecord();
				List<FormValue> valueList = problemList.get(i);
				cr.setDataProps(valueList);
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(cr.getCompilationRecoredMap());
				listMap.add(maps);
			}
			cr = new CompilationRecord();
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			PaperSmallPersonAndDate pd=new PaperSmallPersonAndDate(flowListAll);
	
			// 设置编绘人
			cr.setZHTXGZBHJLB_COMPILATION_PERSON(pd.getCompilationPerson());
			// 设置编绘时间
			cr.setZHTXGZBHJLB_COMPILATION_DATE(pd.getCompilationDate());
		    // 设置标题
			cr.setZHTXGZBHJLB_TITLE("纸海图小改正编绘记录表");
			Map<String, Object> crMap = cr.getCompilationRecoredMap();
			crMap.put(cr.MARKER, listMap);
			data.put(cr.MARKER, crMap);
			
			/**
			 * 纸海图小改正质检记录表
			 */
			QualityTestingRecord qualityTestingRecord = new QualityTestingRecord();
			map = getQualityDefective(planProcessInstId, pd.getQualityPerson(), pd.getQualityDate(), pd.getChiefOpinion(),pd.getChiefPerson(),pd.getChiefDate());
			data.put(qualityTestingRecord.MARKER, map);
			
			/**
			 * 纸海图小改正审定记录表
			 */
			ValidationRecord validationRecord = new ValidationRecord();
			map = getValidationDefective(planProcessInstId, pd.getValidationPerson(), pd.getValidationDate(), pd.getQualityChiefchiefOpinion(), pd.getQualityChiefPerson(), pd.getQualityChiefDate());
			data.put(validationRecord.MARKER, map);
			
			/**
			 * 纸海图小改正验收记录表
			 */
			QualityAcceptanceRecord qa = new QualityAcceptanceRecord();
			List<List<FormValue>> qaList = base.getFormValueByRowFlag(qa.QUALITY_ACCEPTANCE_FORM_ID, planProcessInstId);
			listMap = new ArrayList<Map<String, Object>>();
			// 验收结论
			String ysjl = null;
			// 质量评分及等级
			String pfdj = "";
			for (int i = 0; i < qaList.size(); i++)
			{
				qa = new QualityAcceptanceRecord();
				List<FormValue> qaListI = qaList.get(i);
				qa.setDataProps(qaListI);
				Map<String, Object> mapI = qa.getQualityAcceptanceRecordMap();
				ysjl = mapI.get("text_zhtxgzysjlb_ysjl") == null ? null : mapI.get("text_zhtxgzysjlb_ysjl").toString();
				pfdj = mapI.get("text_zhtxgzysjlb_pfdj") == null ? null : mapI.get("text_zhtxgzysjlb_pfdj").toString();
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(mapI);
				listMap.add(maps);
			}
			qa = new QualityAcceptanceRecord();
			qa.setYJ(pd.getQualityChiefchiefOpinion());
			qa.setZGCS(pd.getCoreQualityRecordPerson());
			qa.setRQ(pd.getQualityChiefDate());
			// 验收结论 
			qa.setYSJL(ysjl);
			// 评分等级
			qa.setPFDJ(pfdj);
			// 设置标题
			qa.setTITLE("纸海图小改正验收记录表");
			map = qa.getQualityAcceptanceRecordMap();
			map.put(qa.MARKER, listMap);
			data.put(qa.MARKER, map);
			// 返回结果数据
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}
	
	/**
	 * 电子海图小改正经历簿导出
	 * @param processInstId
	 */
	@Override
	public Map<String, Object> electricMap(String processInstId)
			throws Exception {
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			/**
			 * 开始电子海图小改正数据准备
			 */
			String processDefKeys ="SMALL_CORRECTION_ELECTRONIC";
			// 获取当前流程实例操作的表数据
			// 编绘作业流程ID
			String workProcessInstId = processInstId;
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);
			/**
			 * 电子海图小改正经历簿
			 */
			EleHead head = new EleHead();
			//设置标题
			head.setTITLE("电子海图小改正经历簿");
			//设置图名
			//head.setMAP_NAME(complicationTask.getTaskName());
			//设置图号
			//head.setMAP_NO(complicationTask.getMapNo());
			////设置是否需要经过中心质量审定
			EleMapNoRecord elemapnorecord=new EleMapNoRecord();
			//读取涉及图幅，图号列表
			List<List<FormValue>> elemapnorecords = base.getFormValueByRowFlag(elemapnorecord.EleMapNoRecord_FORM_ID, planProcessInstId);
			if(elemapnorecords!=null){
				if(elemapnorecords.size()>0){
					//拼接列表
					int x=4;//4列
					for (int i = 0; i < elemapnorecords.size(); i++) {
						String propValue = elemapnorecords.get(i).get(0).getPropValue();
						int number=i+1;
						if(number>x){
							number=number%x;
						}
						map.put("text_itemnoissue_th_"+number,propValue);
						if(number%x==0||i==(elemapnorecords.size()-1)){
							listMap.add(map);
							map=new HashMap<String,Object>();
						}
					}
					data.put(elemapnorecord.MARKER, listMap);
				}
			}
			head.setYES("是");
			//获得电子海图小改正经历簿
			map = head.getHeadMap();
			data.put(head.MARKER, map);

			EleCorrectionNoRecord elecorrectionnorecord=new EleCorrectionNoRecord();
			List<List<FormValue>>  elecorrectionnorecords= base.getFormValueByRowFlag(elecorrectionnorecord.EleCorrectionNoRecord_FORM_ID, planProcessInstId);
			if(elecorrectionnorecords!=null){
				if(elecorrectionnorecords.size()>0){
					for (List<FormValue> list : elecorrectionnorecords) {
						elecorrectionnorecord.setDataProps(list);
					}
					data.put(elecorrectionnorecord.MARKER, elecorrectionnorecord.getMap());
				}
			}
			/**
			 * 电子海图小改正编绘记录表
			 */
			EleCompilationRecord cr = new EleCompilationRecord();
			List<List<FormValue>> problemList = base.getFormValueByRowFlag(cr.COMPILATION_ID, planProcessInstId);
			listMap = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < problemList.size(); i++)
			{
				cr = new EleCompilationRecord();
				List<FormValue> valueList = problemList.get(i);
				cr.setDataProps(valueList);
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.putAll(cr.getCompilationRecoredMap());
				listMap.add(maps);
			}
			cr = new EleCompilationRecord();
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			ElectronSmallPersonAndDate pd=new ElectronSmallPersonAndDate(flowListAll);
	
			// 设置编绘人
			cr.setDZHTXGZBHJLB_COMPILATION_PERSON(pd.getCompilationPerson());
			// 设置编绘时间
			cr.setDZHTXGZBHJLB_COMPILATION_DATE(pd.getCompilationDate());
			// 设置质检人
			cr.setDZHTXGZBHJLB_QUALITY_PERSON(pd.getQualityPerson());
			// 设置质检时间
			cr.setDZHTXGZBHJLB_QUALITY_DATE(pd.getQualityDate());
		    // 设置标题
			cr.setDZHTXGZBHJLB_TITLE("电子海图小改正编绘记录表");
			Map<String, Object> crMap = cr.getCompilationRecoredMap();
			crMap.put(cr.MARKER, listMap);
			data.put(cr.MARKER, crMap);
			
			/**
			 * 电子海图小改正质检记录表
			 */
			EleQualityTestingRecord qualityTestingRecord = new EleQualityTestingRecord();
			map = getEleQualityDefective(planProcessInstId, pd.getQualityPerson(), pd.getQualityDate(), pd.getChiefOpinion(),pd.getChiefPerson(),pd.getChiefDate());
			data.put(qualityTestingRecord.MARKER, map);
			
			/**
			 * 电子海图小改正审定记录表
			 */
			EleValidationRecord validationRecord = new EleValidationRecord();
			map = getEleValidationDefective(planProcessInstId, pd.getValidationPerson(), pd.getValidationDate(), pd.getQualityChiefchiefOpinion(), pd.getQualityChiefPerson(), pd.getQualityChiefDate());
			data.put(validationRecord.MARKER, map);
			// 返回结果数据
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}

	/**
	 * 纸海图缺陷记录表 （质检）
	 * @param workProcessInstId 编绘流程ID
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param qualityApproveAdvice 科长意见
	 * @param qualityApprovePerson 科长
	 * @param qualityApproveDate 科长意见日期
	 * @return
	 */
	public Map<String, Object> getQualityDefective(String workProcessInstId, String qualityPerson, String qualityDate, String qualityApproveAdvice,
			String qualityApprovePerson, String qualityApproveDate)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		QualityTestingRecord qualityRecored = new QualityTestingRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zljcjlb");
		String coefficient = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{	
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					coefficient = defectForm.getCoefficient();
					grading = defectForm.getGrading();
					qualityRecored = new QualityTestingRecord();
					// 设置图层
					qualityRecored.setZHTXGZZJJLB_SJTC(defectForm.getLayerName());
					// 设置存在问题
					//qualityRecored.setZHTXGZZJJLB_CZWT(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					qualityRecored.setZHTXGZZJJLB_CLYJ(defectForm.getOpinion()==null?"同意":defectForm.getOpinion());
					// 设置缺陷类别
					qualityRecored.setZHTXGZZJJLB_QXLB(defectForm.getDeep());
					// 设置缺陷扣分
					qualityRecored.setZHTXGZZJJLB_QXKF(defectForm.getScore());
					// 设置处理结果
					qualityRecored.setZHTXGZZJJLB_CLJG(defectForm.getRemarks());
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(qualityRecored.getQualityTestingRecordMap());
					listMap.add(maps);
				}
				qualityRecored = new QualityTestingRecord();
				// 设置调整系数
				qualityRecored.setZHTXGZZJJLB_COEFFICIENT(coefficient);
				// 设置质量评分
				qualityRecored.setZHTXGZZJJLB_GRADING(grading);

				
			}
		}
		// 设置质检人
		qualityRecored.setZHTXGZZJJLB_QUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setZHTXGZZJJLB_QUALITY_DATE(qualityDate);
		// 设置科长意见
		qualityRecored.setZHTXGZZJJLB_CHIEF_OPINION(qualityApproveAdvice);
		// 设置科长
		qualityRecored.setZHTXGZZJJLB_CHIEF_PERSON(qualityApprovePerson);
		// 设置科长意见时间
		qualityRecored.setZHTXGZZJJLB_CHIEF_OPINION_DATE(qualityApproveDate);
		// 设置标题
		qualityRecored.setZHTXGZZJJLB_TITLE("纸海图小改正质检记录表");
		Map<String, Object> result = qualityRecored.getQualityTestingRecordMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
	}
	/**
	 * 缺陷记录表 （质检） 源数据小改正
	 * @param workProcessInstId 编绘流程ID
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param qualityApproveAdvice 科长意见
	 * @param qualityApprovePerson 科长
	 * @param qualityApproveDate 科长意见日期
	 * @return
	 */
	public Map<String, Object> getSDCQualityDefective(String workProcessInstId, String qualityPerson, String qualityDate, String qualityApproveAdvice,
			String qualityApprovePerson, String qualityApproveDate)
			{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		SDCQualityRecord qualityRecored = new SDCQualityRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zjjilub");
		String coefficient = null;
		String total = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					coefficient = defectForm.getCoefficient();
					total=defectForm.getTotal();
					grading = defectForm.getGrading();
					qualityRecored = new SDCQualityRecord();
					// 设置图层
					qualityRecored.setLAYER_NAME(defectForm.getLayerName());
					// 设置存在问题
					// 	qualityRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					qualityRecored.setOPINION(defectForm.getOpinion()==null?"同意":defectForm.getOpinion());
					// 设置缺陷类别
					qualityRecored.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷扣分
					qualityRecored.setSCORE(defectForm.getScore());
					// 设置处理结果
					qualityRecored.setCLJG("已修改");
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(qualityRecored.getMap());
					listMap.add(maps);
				}
				qualityRecored = new SDCQualityRecord();
				// 设置质量评分
				qualityRecored.setGRADING(grading);
				//设置总分
				qualityRecored.setTOTAL(total);
			}
		}
		// 设置质检人
		qualityRecored.setQUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setQUALITY_DATE(qualityDate);
		// 设置科长
		qualityRecored.setCHIEF(qualityApprovePerson);
		// 设置科长意见时间
		qualityRecored.setCHIEF_DATE(qualityApproveDate);
		Map<String, Object> result = qualityRecored.getMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
}

	/**
	 * 纸海图缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param valaditonApproveAdvice 科长意见
	 * @param valaditonApprovePerson 审定科长
	 * @param valaditonApproveDate 科长审核日期
	 * @return
	 */
	public Map<String, Object> getValidationDefective(String workProcessInstId, String validationPerson, String validationDate, String valaditonApproveAdvice,
			String valaditonApprovePerson, String valaditonApproveDate)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		ValidationRecord validationRecored = new ValidationRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "sdjlb");
		String coefficient = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					coefficient = defectForm.getCoefficient();
					grading = defectForm.getGrading();
					validationRecored = new ValidationRecord();
					// 设置图层
					validationRecored.setZHTXGZSDJLB_SJTC(defectForm.getLayerName());
					// 设置存在问题
					//validationRecored.setZHTXGZSDJLB_CZWT(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					validationRecored.setZHTXGZSDJLB_CLYJ(defectForm.getOpinion()==null?"同意":defectForm.getOpinion());
					// 设置缺陷类别
					validationRecored.setZHTXGZSDJLB_QXLB(defectForm.getDeep());
					// 设置缺陷扣分
					validationRecored.setZHTXGZSDJLB_QXKF(defectForm.getScore());
					// 设置备注
					validationRecored.setZHTXGZSDJLB_CLJG(defectForm.getRemarks());
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(validationRecored.getValidationRecordMap());
					listMap.add(maps);
				}
				validationRecored = new ValidationRecord();
				// 设置调整系数
				validationRecored.setZHTXGZSDJLB_COEFFICIENT(coefficient);
				// 设置质量评分
				validationRecored.setZHTXGZSDJLB_GRADING(grading);

				
			}
		}
		// 设置审定人
		validationRecored.setZHTXGZSDJLB_VALIDATION_PERSON(validationPerson);
		// 设置审定时间
		validationRecored.setZHTXGZSDJLB_VALIDATION_DATE(validationDate);
		// 设置科长意见
		validationRecored.setZHTXGZSDJLB_CHIEF_OPINION(valaditonApproveAdvice);
		// 设置科长
		validationRecored.setZHTXGZSDJLB_CHIEF_PERSON(valaditonApprovePerson);
		// 设置科长审核时间
		validationRecored.setZHTXGZSDJLB_CHIEF_OPINION_DATE(valaditonApproveDate);
		// 设置标题
		validationRecored.setZHTXGZSDJLB_TITLE("纸海图小改正审定记录表");
		Map<String, Object> result = validationRecored.getValidationRecordMap();
		result.put(validationRecored.MARKER, listMap);
		return result;
	}
	/**
	 * 缺陷记录表 （审定）源数据小改正审定记录表
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param valaditonApproveAdvice 科长意见
	 * @param valaditonApprovePerson 审定科长
	 * @param valaditonApproveDate 科长审核日期
	 * @return
	 */
	public Map<String, Object> getSDCValidationDefective(String workProcessInstId, String validationPerson, String validationDate, String valaditonApproveAdvice,
			String valaditonApprovePerson, String valaditonApproveDate)
			{
			defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
			SDCValidationRecord validationRecored = new SDCValidationRecord();
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "sdjilub");
			String coefficient = null;
			String total = null;
			String grading = null;
			if (defectFormList != null)
			{
				if (defectFormList.size() > 0)
				{
					for (int i = 0; i < defectFormList.size(); i++)
					{
						DefectForm defectForm = defectFormList.get(i);
						coefficient = defectForm.getCoefficient();
						total=defectForm.getTotal();
						grading = defectForm.getGrading();
						validationRecored = new SDCValidationRecord();
						// 设置图层
						validationRecored.setLAYER_NAME(defectForm.getLayerName());
						// 设置存在问题
						//validationRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
						// 设置处理意见
						validationRecored.setOPINION(defectForm.getOpinion());
						// 设置缺陷类别
						validationRecored.setDEFECT_TYPE(defectForm.getDeep());
						// 设置缺陷扣分
						validationRecored.setSCORE(defectForm.getScore());
						//设置处理结果
						validationRecored.setCLJG("已修改");
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(validationRecored.getMap());
						listMap.add(maps);
					}
					validationRecored = new SDCValidationRecord();
					// 设置质量评分
					validationRecored.setGRADING(grading);
					// 设置总分
					validationRecored.setTOTAL(total);
					
				}
			}
			// 设置审定人
			validationRecored.setVALIDATION(validationPerson);
			// 设置审定时间
			validationRecored.setVALIDATION_DATE(validationDate);
			// 设置科长
			validationRecored.setCHIEF(valaditonApprovePerson);
			// 设置科长审核时间
			validationRecored.setCHIEF_DATE(valaditonApproveDate);
			Map<String, Object> result = validationRecored.getMap();
			result.put(validationRecored.MARKER, listMap);
			return result;
}
	
	/**
	 * 电子海图缺陷记录表 （质检）
	 * @param workProcessInstId 编绘流程ID
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param qualityApproveAdvice 科长意见
	 * @param qualityApprovePerson 科长
	 * @param qualityApproveDate 科长意见日期
	 * @return
	 */
	public Map<String, Object> getEleQualityDefective(String workProcessInstId, String qualityPerson, String qualityDate, String qualityApproveAdvice,
			String qualityApprovePerson, String qualityApproveDate)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		EleQualityTestingRecord qualityRecored = new EleQualityTestingRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.QUALITY_LIKE);
		String coefficient = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					coefficient = defectForm.getCoefficient();
					grading = defectForm.getGrading();
					qualityRecored = new EleQualityTestingRecord();
					// 设置图层
					qualityRecored.setDZHTXGZZJJLB_SJTC(defectForm.getLayerName());
					// 设置存在问题
					//qualityRecored.setDZHTXGZZJJLB_CZWT(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					qualityRecored.setDZHTXGZZJJLB_CLYJ(defectForm.getOpinion()==null?"同意":defectForm.getOpinion());
					// 设置缺陷类别
					qualityRecored.setDZHTXGZZJJLB_QXLB(defectForm.getDeep());
					// 设置缺陷扣分
					qualityRecored.setDZHTXGZZJJLB_QXKF(defectForm.getScore());
					// 设置处理结果
					qualityRecored.setDZHTXGZZJJLB_CLJG(defectForm.getRemarks());
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(qualityRecored.getQualityTestingRecordMap());
					listMap.add(maps);
				}
				qualityRecored = new EleQualityTestingRecord();
				// 设置调整系数
				qualityRecored.setDZHTXGZZJJLB_COEFFICIENT(coefficient);
				// 设置质量评分
				qualityRecored.setDZHTXGZZJJLB_GRADING(grading);

				
			}
		}
		// 设置质检人
		qualityRecored.setDZHTXGZZJJLB_QUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setDZHTXGZZJJLB_QUALITY_DATE(qualityDate);
		// 设置科长意见
		qualityRecored.setDZHTXGZZJJLB_CHIEF_OPINION(qualityApproveAdvice==null?"同意":qualityApproveAdvice);
		// 设置科长
		qualityRecored.setDZHTXGZZJJLB_CHIEF_PERSON(qualityApprovePerson);
		// 设置科长意见时间
		qualityRecored.setDZHTXGZZJJLB_CHIEF_OPINION_DATE(qualityApproveDate);
		// 设置标题
		qualityRecored.setDZHTXGZZJJLB_TITLE("电子海图小改正质检记录表");
		Map<String, Object> result = qualityRecored.getQualityTestingRecordMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 电子海图缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param valaditonApproveAdvice 科长意见
	 * @param valaditonApprovePerson 审定科长
	 * @param valaditonApproveDate 科长审核日期
	 * @return
	 */
	public Map<String, Object> getEleValidationDefective(String workProcessInstId, String validationPerson, String validationDate, String valaditonApproveAdvice,
			String valaditonApprovePerson, String valaditonApproveDate)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		EleValidationRecord validationRecored = new EleValidationRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.APPROVE_LIKE);
		String coefficient = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					coefficient = defectForm.getCoefficient();
					grading = defectForm.getGrading();
					validationRecored = new EleValidationRecord();
					// 设置图层
					validationRecored.setDZHTXGZSDJLB_SJTC(defectForm.getLayerName());
					// 设置存在问题
					//validationRecored.setDZHTXGZSDJLB_CZWT(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					validationRecored.setDZHTXGZSDJLB_CLYJ(defectForm.getOpinion());
					// 设置缺陷类别
					validationRecored.setDZHTXGZSDJLB_QXLB(defectForm.getDeep());
					// 设置缺陷扣分
					validationRecored.setDZHTXGZSDJLB_QXKF(defectForm.getScore());
					// 设置备注
					validationRecored.setDZHTXGZSDJLB_CLJG(defectForm.getRemarks());
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(validationRecored.getValidationRecordMap());
					listMap.add(maps);
				}
				validationRecored = new EleValidationRecord();
				// 设置调整系数
				validationRecored.setDZHTXGZSDJLB_COEFFICIENT(coefficient);
				// 设置质量评分
				validationRecored.setDZHTXGZSDJLB_GRADING(grading);
				String defaultValue = defectFormList.get(0).getDefaultValue();
				String def="除中国海事珠三角电子海图目录中的39幅电子海图外， 其他电子海图小改正不经审定。";
				//设置默认值
				validationRecored.setDZHTXGZSDJLB_DEFTVALUE(StringUtils.isBlank(defaultValue)?def:defaultValue);
			}
		}
		// 设置审定人
		validationRecored.setDZHTXGZSDJLB_VALIDATION_PERSON(validationPerson);
		// 设置审定时间
		validationRecored.setDZHTXGZSDJLB_VALIDATION_DATE(validationDate);
		// 设置科长意见
		validationRecored.setDZHTXGZSDJLB_CHIEF_OPINION(valaditonApproveAdvice==null?"同意":valaditonApproveAdvice);
		// 设置科长
		validationRecored.setDZHTXGZSDJLB_CHIEF_PERSON(valaditonApprovePerson);
		// 设置科长审核时间
		validationRecored.setDZHTXGZSDJLB_CHIEF_OPINION_DATE(valaditonApproveDate);
		// 设置标题
		validationRecored.setDZHTXGZSDJLB_TITLE("电子海图小改正审定记录表");
		Map<String, Object> result = validationRecored.getValidationRecordMap();
		result.put(validationRecored.MARKER, listMap);
		return result;
	}

	/* 
	 * @author huodesheng
	 * @date 2017-10-10
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.MinorCorrectionService#paperCorrenctionMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> paperCorrenctionMap(String processInstId)
			throws Exception {
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			/**
			 * 开始纸海图小改正数据准备
			 */
			String[] processDefKeys =
				{ "SMALL_CORRECTION_PAPER"};
			// 获取当前流程实例操作的表数据
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);
			
			/**
			 * 纸海图小改正流程表
			 */
			Head head = new Head();
			//设置图名
			head.setMAP_NAME(complicationTask.getTaskName());
			//设置图号
			head.setMAP_NO(complicationTask.getMapNo());
			//设置版次
			head.setREVISION(complicationTask.getRevision());
			//设置比例尺
			head.setSCALE(complicationTask.getScale());
			//获得纸海图小改正经历簿
			map = head.getHeadMap();
			data.put(head.MARKER, map);
			/**
			 * 设置改正期号,改正项号 
			 */
			PaperItemNoIssue paperitemnoissue=new PaperItemNoIssue();
			List<List<FormValue>> paperitemnoissues = base.getFormValueByRowFlag(paperitemnoissue.PaperItemNoIssue_FORM_ID, planProcessInstId);
			if(paperitemnoissues!=null){
				if(paperitemnoissues.size()>0){
					for (List<FormValue> list : paperitemnoissues) {
						paperitemnoissue.setDataProps(list);
					}
					data.put(paperitemnoissue.MARKER, paperitemnoissue.getMap());
				}
			}
			
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			PaperSmallPersonAndDate pd=new PaperSmallPersonAndDate(flowListAll);
			data.put("table_gx", pd.getListMap());
			// 返回结果数据
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}

	/* 
	 * @author huodesheng
	 * @date 2017-10-10
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.MinorCorrectionService#electricCorrenctionMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> electricCorrenctionMap(String processInstId)
			throws Exception {
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			/**
			 * 开始电子海图小改正数据准备
			 */
			String processDefKeys ="SMALL_CORRECTION_ELECTRONIC";
			// 获取当前流程实例操作的表数据
			// 编绘作业流程ID
			String workProcessInstId = processInstId;
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);
			/**
			 * 电子海图小改正经历簿
			 */
			EleHead head = new EleHead();
			//设置标题
			head.setTITLE("电子海图小改正经历簿");
			//设置是否需要经过中心质量审定
			EleMapNoRecord elemapnorecord=new EleMapNoRecord();
			//读取涉及图幅，图号列表
			List<List<FormValue>> elemapnorecords = base.getFormValueByRowFlag(elemapnorecord.EleMapNoRecord_FORM_ID, planProcessInstId);
			if(elemapnorecords!=null){
				if(elemapnorecords.size()>0){
					//拼接列表
					int x=4;//4列
					for (int i = 0; i < elemapnorecords.size(); i++) {
						String propValue = elemapnorecords.get(i).get(0).getPropValue();
						int number=i+1;
						if(number>x){
							number=number%x;
						}
						map.put("text_itemnoissue_th_"+number,propValue);
						if(number%x==0||i==(elemapnorecords.size()-1)){
							listMap.add(map);
							map=new HashMap<String,Object>();
						}
					}
					data.put(elemapnorecord.MARKER, listMap);
				}
			}
			head.setYES("是");
			//获得电子海图小改正经历簿
			map = head.getHeadMap();
			data.put(head.MARKER, map);

			EleCorrectionNoRecord elecorrectionnorecord=new EleCorrectionNoRecord();
			List<List<FormValue>>  elecorrectionnorecords= base.getFormValueByRowFlag(elecorrectionnorecord.EleCorrectionNoRecord_FORM_ID, planProcessInstId);
			if(elecorrectionnorecords!=null){
				if(elecorrectionnorecords.size()>0){
					for (List<FormValue> list : elecorrectionnorecords) {
						elecorrectionnorecord.setDataProps(list);
					}
					data.put(elecorrectionnorecord.MARKER, elecorrectionnorecord.getMap());
				}
			}
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			ElectronSmallPersonAndDate pd=new ElectronSmallPersonAndDate(flowListAll);
			data.put("table_gx", pd.getListMap());
			// 返回结果数据
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}
	
}
