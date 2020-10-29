package com.ht.service.impl.experiencebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

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
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.service.constant.experiencebook.ChartStatusConstants;
import com.ht.service.constant.experiencebook.correctionnotice.CentreQualityInspect;
import com.ht.service.constant.experiencebook.correctionnotice.CorrectionNoticePersonAndDate;
import com.ht.service.constant.experiencebook.correctionnotice.Cover;
import com.ht.service.constant.experiencebook.correctionnotice.DepartmentQualityInspect;
import com.ht.service.constant.experiencebook.correctionnotice.EditDataRegister;
import com.ht.service.constant.experiencebook.correctionnotice.EditSituationRecord;
import com.ht.service.constant.experiencebook.correctionnotice.IssueItemNoRecord;
import com.ht.service.constant.experiencebook.correctionnotice.NewCorrectionItemNo;
import com.ht.service.constant.experiencebook.correctionnotice.QualityAcceptanceRecord;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.CorrectionNoticeTempletePersonAndDate;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.ItemNoIssueRecord;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempCentreQualityInspect;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempCover;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempDepartmentQualityInspect;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempEditSituationRecord;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempFlows;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempHomePage;
import com.ht.service.constant.experiencebook.correctionnoticeTemplete.TempQualityAcceptanceRecord;
import com.ht.service.constant.experiencebook.electron.EleQualityInspectionConclusionRecord;
import com.ht.service.constant.experiencebook.electron.EleValidationConclusionRecord;
import com.ht.service.constant.experiencebook.electron.ElectronPersonAndDate;
import com.ht.service.constant.experiencebook.paper.PaperPersonAndDate;
import com.ht.service.constant.experiencebook.paper.PaperQualityInspectionConclusionRecord;
import com.ht.service.constant.experiencebook.paper.PaperValidationConclusionRecord;
import com.ht.service.constant.experiencebook.paperqualityevaluate.PaperQualityEvaluate;
import com.ht.service.constant.experiencebook.paperqualitytestreport.PaperQualityTestReport;
import com.ht.service.constant.experiencebook.sourcedata.HomePage;
import com.ht.service.constant.experiencebook.sourcedata.SourceDataPersonAndDate;
import com.ht.service.constant.experiencebook.sourcedata.ValidationConclusionRecord;
import com.ht.service.constant.model.PersonAndDate;
import com.ht.service.inter.experiencebook.CorrectionNoticeService;

/**
 * 改正通告编辑
 * @author huodesheng
 *
 */
public class CorrectionNoticeServiceImpl implements CorrectionNoticeService {
	/**
	 * 拆分dao
	 */
	@Resource
	TaskSplitDao taskSplitDao;
	
	DefectFormDao defectFormDao = null;
	
	@Resource
	FlowsDao flowsDao;
	
	@Resource
	CreateTaskDao createTaskDao;
	
	@Resource
	TaskBookDao taskBookDao;
	
	@Override
	public Map<String, Object> NoticeMap(String processInstId) throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{"CORRECTION_NOTICE_TEMPLATE"};
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_fenpei_bjy");
			List<Flows> beginFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(processInstId,taskDefId);
			String beginTime="";
			if(beginFlows!=null&&beginFlows.size()>0){
				beginTime=sdf.format(beginFlows.get(0).getEndTime());
			}
			taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_zj_queren");
			List<Flows> endFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(workProcessInstId,taskDefId);
			String endTime="";
			if(endFlows!=null&&endFlows.size()>0){
				endTime=sdf.format(endFlows.get(0).getEndTime());
			}
			// 设置实施日期
			cover.setTEXT_COVER_DATE(beginTime+"至"+endTime);
			// 设置部门
			cover.setTEXT_COVER_DEPARTMENT("制图事业部");
			// 设置封皮logo图片（路径）
			cover.setIMG_COVER_LOGO("");
			// 本期项号
			cover.setTEXT_COVER_ITEM_NO(taskBook.getCorrectItemNo());
			// 通告期号
			cover.setTEXT_COVER_NOTICE_NO(taskBook.getNoticeNo());
			// 获得封皮
			Map<String, Object> map = cover.getCoverMap();
			data.put(cover.MARKER, map);

			/**
			 * 首页
			 */
			HomePage homePage = new HomePage();
			homePage.setINSTRUCTION_TITLE("说明");
			String instructionContent = "1、《改正通告》编辑经历簿是《改正通告》编辑过程的真实记录和技术档案，必须认真填写，不得任意涂改。"+
										"2、《改正通告》编辑过程中有关资料采用、问题处理、质量检查、资料交接等信息必须详细记载，以便追溯。"+
										"3、每一道工序完成后作业人员应及时填写经历簿，且应做到填写内容准确无误，格式规范统一、文字简练清晰。"+
										"4、《改正通告》编辑经历簿应与其它编辑资料一起流转，编辑结束后归档保存。";
			homePage.setINSTRUTION_CONTENT(instructionContent);
			homePage.setTITLE("中华人民共和国海事局");
			map = homePage.getHomePageMap();
			data.put(homePage.MARKER, map);

			/**
			 * 改正公告编辑工序流程表
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
			CorrectionNoticePersonAndDate pd=new CorrectionNoticePersonAndDate(flowListAll);
			//添加节点
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text_gx_order", "交出版管理科");
			pd.getListMap().add(m);
			data.put(new com.ht.service.constant.experiencebook.correctionnotice.Flows().MARKER, pd.getListMap());
			
			/**
			 * 通告期号&本期项号
			 */
			IssueItemNoRecord issueitemnorecord =new IssueItemNoRecord();
			List<List<FormValue>> issueitemnorecords = base.getFormValueByRowFlag(issueitemnorecord.IssueItemNoRecord_FORM_ID, planProcessInstId);
			if (issueitemnorecords != null)
			{
				if (issueitemnorecords.size() > 0)
				{
					issueitemnorecord =new IssueItemNoRecord();
					for (List<FormValue> list : issueitemnorecords) {
						issueitemnorecord.setDataProps(list);
					}
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(issueitemnorecord.getMap());
					data.put(issueitemnorecord.MARKER,maps );
				}
			}
			
			
			/**
			 * 编辑资料登记表
			 */
			EditDataRegister editdataregister=new EditDataRegister();
			listMap = new ArrayList<Map<String, Object>>();
			List<List<FormValue>> editdataregisters = base.getFormValueByRowFlag(editdataregister.getBJZLDJB_FORM_ID(), planProcessInstId);
			for (int i = 0; i < editdataregisters.size(); i++) {
				editdataregister=new EditDataRegister();
				editdataregister.setDataProps(editdataregisters.get(i));
				listMap.add(editdataregister.getMap());
			}
			
			
			editdataregister.setEDITOR(pd.getRegisterCompilationPerson());
			editdataregister.setEDITOR_DATE(pd.getRegisterCompilationDate());
			editdataregister.setQUALITY(pd.getRegisterQualityPerson());
			editdataregister.setQUALITY_DATE(pd.getRegisterQualityDate());
			
			Map<String, Object> editdataregisterMap = editdataregister.getMap();
			editdataregisterMap.put(editdataregister.getMARKER(), listMap);
			
			data.put(editdataregister.getMARKER(),editdataregisterMap);

			/**
			 * 改正通告编辑最新改正项号记录表
			 */
			NewCorrectionItemNo newcorrection=new NewCorrectionItemNo();
			List<List<FormValue>> newcorrections = base.getFormValueByRowFlag(newcorrection.getZXGZXHJLB_FORM_ID(), planProcessInstId);
			if(newcorrections!=null&&newcorrections.size()>0){
				newcorrection.setDataProps(newcorrections.get(0));
			}
			newcorrection.setEDITOR(pd.getNewCorrectionCompilationPerson());
			newcorrection.setEDITOR_DATE(pd.getNewCorrectionCompilationDate());
			newcorrection.setQUALITY(pd.getNewCorrectionQualityPerson());
			newcorrection.setQUALITY_DATE(pd.getNewCorrectionQualityDate());
			
			data.put(newcorrection.getMARKER(), newcorrection.getMap());
			
			/**
			 *  编辑情况记录表
			 */
			EditSituationRecord editsituationrecord=new EditSituationRecord();
			List<List<FormValue>> editsituationrecords = base.getFormValueByRowFlag(editsituationrecord.getBJQKJLB_FORM_ID(), planProcessInstId);
			listMap = new ArrayList<Map<String, Object>>();
			if(editsituationrecords!=null){
				for (int i = 0; i < editsituationrecords.size(); i++) {
					editsituationrecord=new EditSituationRecord();
					editsituationrecord.setDataProps(editsituationrecords.get(i));
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(editsituationrecord.getMap());
					listMap.add(maps);
				}
			}
			Map<String, Object> editsituationrecordMap = editsituationrecord.getMap();
			editsituationrecordMap.put(editsituationrecord.getMARKER(),listMap);
			editsituationrecord.setEDITOR(pd.getRecordCompilationPerson());
			editsituationrecord.setEDITOR_DATE(pd.getRecordCompilationDate());
			data.put(editsituationrecord.getMARKER(), editsituationrecord.getMap());
			
			/**
			 * 部门质量检查记录表
			 */
			DepartmentQualityInspect departmentqualityinspect = new DepartmentQualityInspect();
			map=getDepartmentQualityInspect(workProcessInstId,  pd.getDepartmentQualityCompilationPerson(), pd.getDepartmentQualityCompilationDate(), pd.getDepartmentQualityQualityPerson(),pd.getDepartmentQualityQualityDate(), null,pd.getDepartmentQualityChief(),pd.getDepartmentQualityChiefDate());
			data.put(departmentqualityinspect.getMARKER(), map);
			
			/**
			 * 中心质量检查记录表
			 */
			CentreQualityInspect centrequalityinspect=new CentreQualityInspect();
			map =getCentreQualityInspect(workProcessInstId, pd.getCentreQualityvaladitonPerson(), pd.getCentreQualityvaladitonDate(), pd.getCentreQualityChief(),pd.getCentreQualityChiefDate(), null,pd.getCentreQualityQualityPerson(),pd.getCentreQualityQualityDate());
			data.put(centrequalityinspect.MARKER, map);
			
			
			/**
			 *总工程师质量验收记录表
			 */
			QualityAcceptanceRecord qa=new QualityAcceptanceRecord();
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
						qa = new QualityAcceptanceRecord();
						List<FormValue> qaListI = qaList.get(i);
						qa.setDataProps(qaListI);
						Map<String, Object> mapI = qa.getQualityAcceptanceRecordMap();
						ysjl += mapI.get("text_zgcszlysb_jl") == null ? "" : mapI.get("text_zgcszlysb_jl").toString();
						pfdj += mapI.get("text_zgcszlysb_pfdj") == null ? "" : mapI.get("text_zgcszlysb_pfdj").toString();
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(mapI);
						listMap.add(maps);
					}
				}
			}
			qa = new QualityAcceptanceRecord();
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
			// 返回结果数据
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	@Override
	public Map<String, Object> NoticeTempleteMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{ "CORRECTION_NOTICE_TEMPLATE_EDIT"};
//			// 获取当前流程实例操作的表数据
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
			TempCover cover = new TempCover();
			// 设置实施日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_fenpei_bjy");
			List<Flows> beginFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(processInstId,taskDefId);
			String beginTime="";
			if(beginFlows!=null&&beginFlows.size()>0){
				beginTime=sdf.format(beginFlows.get(0).getEndTime());
			}
			taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_zjy_queren");
			List<Flows> endFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(workProcessInstId,taskDefId);
			String endTime="";
			if(endFlows!=null&&endFlows.size()>0){
				endTime=sdf.format(endFlows.get(0).getEndTime());
			}
			// 设置实施日期
			cover.setTEXT_COVER_DATE(beginTime+"至"+endTime);
			// 设置部门
			cover.setTEXT_COVER_DEPARTMENT("上海海图中心制图");
			// 设置封皮logo图片（路径）
			cover.setIMG_COVER_LOGO("");
			// 本期项号
			//cover.setTEXT_COVER_ITEM_NO(taskBook.getCorrectItemNo());
			// 通告期号
			//cover.setTEXT_COVER_NOTICE_NO(taskBook.getNoticeNo());
			// 获得封皮
			Map<String, Object> map = cover.getCoverMap();
			data.put(cover.MARKER, map);

			/**
			 * 首页
			 */
			TempHomePage homePage = new TempHomePage();
			homePage.setINSTRUCTION_TITLE("说明");
			String instructionContent = "1、改正通告模板编辑经历簿是改正通告模板编辑过程的真实记录和技术档案，必须认真填写，不得任意涂改。"+
									"2、改正通告模板编辑过程中有关资料采用、问题处理、质量检查、资料交接等信息必须详细记载，以便追溯。"+
									"3、每一道工序完成后作业人员应及时填写经历簿，且应做到填写内容准确无误，格式规范统一、文字简练清晰。"+
									"4、改正通告模板编辑经历簿应与其它编辑资料一起流转，编辑结束后归档保存。";
			homePage.setINSTRUTION_CONTENT(instructionContent);
			homePage.setTITLE("中华人民共和国海事局");
			map = homePage.getHomePageMap();
			data.put(homePage.MARKER, map);

			/**
			 * 改正公告编辑工序流程表
			 */
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			CorrectionNoticeTempletePersonAndDate pd=new CorrectionNoticeTempletePersonAndDate(flowListAll);
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			//添加节点
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text_gx_order", "交出版管理科");
			pd.getListMap().add(m);
			data.put(new TempFlows().MARKER, pd.getListMap());
			
			/**
			 * 通告期号&本期项号
			 */
			ItemNoIssueRecord itemnoissuerecord =new ItemNoIssueRecord();
			List<List<FormValue>> itemnoissuerecords = base.getFormValueByRowFlag(itemnoissuerecord.ItemNoIssueRecord_FORM_ID, planProcessInstId);
			if (itemnoissuerecords != null)
			{
				if (itemnoissuerecords.size() > 0)
				{
					itemnoissuerecord =new ItemNoIssueRecord();
					for (List<FormValue> list : itemnoissuerecords) {
						itemnoissuerecord.setDataProps(list);
					}
					data.put(itemnoissuerecord.MARKER, itemnoissuerecord.getMap());
				}
			}
			
			
			/**
			 *  编辑情况记录表
			 */
			TempEditSituationRecord editsituationrecord=new TempEditSituationRecord();
			List<List<FormValue>> editsituationrecords = base.getFormValueByRowFlag(editsituationrecord.getBJQKJLB_FORM_ID(), planProcessInstId);
			listMap = new ArrayList<Map<String, Object>>();
			if(editsituationrecords!=null){
				for (int i = 0; i < editsituationrecords.size(); i++) {
					editsituationrecord=new TempEditSituationRecord();
					editsituationrecord.setDataProps(editsituationrecords.get(i));
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(editsituationrecord.getMap());
					listMap.add(maps);
				}
			}
			Map<String, Object> editsituationrecordMap = editsituationrecord.getMap();
			editsituationrecordMap.put(editsituationrecord.getMARKER(),listMap);
			editsituationrecord.setEDITOR(pd.getRecordCompilationPerson());
			editsituationrecord.setEDITOR_DATE(pd.getRecordCompilationDate());
			data.put(editsituationrecord.getMARKER(), editsituationrecordMap);
			
			
			/**
			 * 部门质量检查记录表
			 */
			TempDepartmentQualityInspect departmentqualityinspect = new TempDepartmentQualityInspect();
			map= getTempDepartmentQualityInspect(workProcessInstId,  pd.getDepartmentQualityCompilationPerson(), pd.getDepartmentQualityCompilationDate(), pd.getDepartmentQualityQualityPerson(),pd.getDepartmentQualityQualityDate(), null,pd.getDepartmentQualityChief(),pd.getDepartmentQualityChiefDate());
			data.put(departmentqualityinspect.getMARKER(), map);
			
			/**
			 * 中心质量检查记录表
			 */
			TempCentreQualityInspect centrequalityinspect=new TempCentreQualityInspect();
			map =getTempCentreQualityInspect(workProcessInstId, pd.getCentreQualityvaladitonPerson(), pd.getCentreQualityvaladitonDate(), pd.getCentreQualityChief(),pd.getCentreQualityChiefDate(), null,pd.getCentreQualityQualityPerson(),pd.getCentreQualityQualityDate());
			data.put(centrequalityinspect.MARKER, map);
			
			
			/**
			 *总工程师质量验收记录表
			 */
			TempQualityAcceptanceRecord qa=new TempQualityAcceptanceRecord();
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
						qa = new TempQualityAcceptanceRecord();
						List<FormValue> qaListI = qaList.get(i);
						qa.setDataProps(qaListI);
						Map<String, Object> mapI = qa.getQualityAcceptanceRecordMap();
						ysjl += mapI.get("text_zgcszlysb_jl") == null ? "" : mapI.get("text_zgcszlysb_jl").toString();
						pfdj += mapI.get("text_zgcszlysb_pfdj") == null ? "" : mapI.get("text_zgcszlysb_pfdj").toString();
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(mapI);
						listMap.add(maps);
					}
					
				}
			}
			qa = new TempQualityAcceptanceRecord();
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
			// 返回结果数据
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	/**
	 * 部门质量检查记录表
	 * @param workProcessInstId 编绘流程ID
	 * @param compilationPerson 编绘人
	 * @param compilationDate 编绘日期
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getDepartmentQualityInspect(String workProcessInstId, String compilationPerson, String compilationDate, String qualityPerson,
			String qualityDate, String notice,String chief, String chief_date)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		DepartmentQualityInspect departmentqualityinspect = new DepartmentQualityInspect();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "bmzljcjl");
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					departmentqualityinspect = new DepartmentQualityInspect();
					// 设置存在问题
					/*String defectitem=defectForm.getDefectitem()==null?"":defectForm.getDefectitem();
					departmentqualityinspect.setDISCRIPTION(defectitem+ defectForm.getDiscription());*/
					// 设置处理意见
					departmentqualityinspect.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					departmentqualityinspect.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					departmentqualityinspect.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					departmentqualityinspect.setSCORE(defectForm.getScore());
					// 设置处理结果
					departmentqualityinspect.setCLJG("已修改");
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(departmentqualityinspect.getMap());
					listMap.add(maps);
				}
				departmentqualityinspect = new DepartmentQualityInspect();
				// 设置缺陷累计扣分
				departmentqualityinspect.setTOTAL(total);
				// 质量等级
				departmentqualityinspect.setZLDJ(getZLDJ(grading));
				// 设置质量评分
				departmentqualityinspect.setGRADING(grading);

				
				
			}
		
		}
		// 设置编绘人
		departmentqualityinspect.setEDITOR(compilationPerson);
		// 设置编绘时间
		departmentqualityinspect.setEDITOR_DATE(compilationDate);
		// 设置质检人
		departmentqualityinspect.setQUALITY(qualityPerson);
		// 设置质检日期
		departmentqualityinspect.setQUALITY_DATE(qualityDate);
		//设置科长
		departmentqualityinspect.setCHIEF(chief);
		//设置科长时间
		departmentqualityinspect.setCHIEF_DATE(chief_date);
		Map<String, Object> result = departmentqualityinspect.getMap();
		result.put(departmentqualityinspect.MARKER, listMap);
		return result;
	}
	/**
	 * 部门质量检查记录表
	 * @param workProcessInstId 编绘流程ID
	 * @param compilationPerson 编绘人
	 * @param compilationDate 编绘日期
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getTempDepartmentQualityInspect(String workProcessInstId, String compilationPerson, String compilationDate, String qualityPerson,
			String qualityDate, String notice,String chief,String chief_date)
			{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		TempDepartmentQualityInspect departmentqualityinspect = new TempDepartmentQualityInspect();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "u_task_zljcjlb");
		String total = "";
		String coefficient = "";
		String actual = "";
		String grading = "";
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					departmentqualityinspect = new TempDepartmentQualityInspect();
					// 设置存在问题
					/*String defectitem=defectForm.getDefectitem()==null?"":defectForm.getDefectitem();
					departmentqualityinspect.setDISCRIPTION(defectitem+ defectForm.getDiscription());*/
					// 设置处理意见
					departmentqualityinspect.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					departmentqualityinspect.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					departmentqualityinspect.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					departmentqualityinspect.setSCORE(defectForm.getScore());
					// 设置处理结果
					departmentqualityinspect.setCLJG("已修改");
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(departmentqualityinspect.getMap());
					listMap.add(maps);
				}
				departmentqualityinspect = new TempDepartmentQualityInspect();
				// 设置缺陷累计扣分
				departmentqualityinspect.setTOTAL(total);
				// 设置质量评分
				departmentqualityinspect.setGRADING(grading);
				
				// 质量等级
				departmentqualityinspect.setZLDJ(getZLDJ(grading));
			
				
			}

			
		}
		// 设置编绘人
		departmentqualityinspect.setEDITOR(compilationPerson);
		// 设置编绘时间
		departmentqualityinspect.setEDITOR_DATE(compilationDate);
		// 设置质检人
		departmentqualityinspect.setQUALITY(qualityPerson);
		// 设置质检日期
		departmentqualityinspect.setQUALITY_DATE(qualityDate);
		//设置科长
		departmentqualityinspect.setCHIEF(chief);
		//设置科长时间
		departmentqualityinspect.setCHIEF_DATE(chief_date);
		Map<String, Object> result = departmentqualityinspect.getMap();
		result.put(departmentqualityinspect.MARKER, listMap);
		return result;
			}
	
	/**
	 * 缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param validationPerson 审定科长
	 * @param validationDate 科长审核日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getTempCentreQualityInspect(String workProcessInstId, String validationPerson, String validationDate, String chiefPerson,
			String chiefDate, String notice,String quality,String quality_date)
	{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		TempCentreQualityInspect centrequalityinspect = new TempCentreQualityInspect();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zxzljcjlb");
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					centrequalityinspect = new TempCentreQualityInspect();
					// 设置存在问题
					/*String defectitem=defectForm.getDefectitem()==null?"":defectForm.getDefectitem();
					centrequalityinspect.setDISCRIPTION(defectitem+ defectForm.getDiscription());*/
					// 设置处理意见
					centrequalityinspect.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					centrequalityinspect.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					centrequalityinspect.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					centrequalityinspect.setSCORE(defectForm.getScore());
					// 设置处理结果
					centrequalityinspect.setCLJG("已修改");
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(centrequalityinspect.getMap());
					listMap.add(maps);
				}
				centrequalityinspect = new TempCentreQualityInspect();
				// 设置缺陷累计扣分
				centrequalityinspect.setTOTAL(total);
				// 设置质量评分
				centrequalityinspect.setGRADING(grading);
				// 设置质量等级
				centrequalityinspect.setZLDJ(getZLDJ(grading));
			}
			
		}
		// 设置审定人
		centrequalityinspect.setVALIDATION(validationPerson);
		// 设置审定时间
		centrequalityinspect.setVALIDATION_DATE(validationDate);
		//设置质检人
		centrequalityinspect.setQUALITY(quality);
		//设置质检时间
		centrequalityinspect.setQUALITY_DATE(quality_date);
		// 设置科长
		centrequalityinspect.setCHIEF(chiefPerson);
		// 设置科长审核时间
		centrequalityinspect.setCHIEF_DATE(chiefDate);
		Map<String, Object> result = centrequalityinspect.getMap();
		result.put(centrequalityinspect.MARKER, listMap);
		return result;
	}
	/**
	 * 缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param validationPerson 审定科长
	 * @param validationDate 科长审核日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getCentreQualityInspect(String workProcessInstId, String validationPerson, String validationDate, String chiefPerson,
			String chiefDate, String notice,String quality,String quality_date)
			{
		defectFormDao=(DefectFormDao) SpringUtil.getBean("defectFormDao");
		CentreQualityInspect centrequalityinspect = new CentreQualityInspect();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zxzljcjl");
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					centrequalityinspect = new CentreQualityInspect();
					// 设置存在问题
					/*String defectitem=defectForm.getDefectitem()==null?"":defectForm.getDefectitem();
					centrequalityinspect.setDISCRIPTION(defectitem+ defectForm.getDiscription());*/
					// 设置处理意见
					centrequalityinspect.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					centrequalityinspect.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					centrequalityinspect.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					centrequalityinspect.setSCORE(defectForm.getScore());
					// 设置处理结果
					centrequalityinspect.setCLJG("已修改");
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(centrequalityinspect.getMap());
					listMap.add(maps);
				}
				centrequalityinspect = new CentreQualityInspect();
				// 设置缺陷累计扣分
				centrequalityinspect.setTOTAL(total);
				// 设置质量评分
				centrequalityinspect.setGRADING(grading);
				
				
			}
			
			//设置质量等级
			centrequalityinspect.setZLDJ(getZLDJ(grading));
		}
		// 设置审定人
		centrequalityinspect.setVALIDATION(validationPerson);
		// 设置审定时间
		centrequalityinspect.setVALIDATION_DATE(validationDate);
		//设置质检人
		centrequalityinspect.setQUALITY(quality);
		//设置质检时间
		centrequalityinspect.setQUALITY_DATE(quality_date);
		// 设置科长
		centrequalityinspect.setCHIEF(chiefPerson);
		// 设置科长审核时间
		centrequalityinspect.setCHIEF_DATE(chiefDate);
		
		Map<String, Object> result = centrequalityinspect.getMap();
		result.put(centrequalityinspect.MARKER, listMap);
		return result;
			}
	
	/**
	 * 获取质量等级
	 * @param grading
	 * @return
	 */
	public String getZLDJ(String grading){
		String zldj="不及格";
		try {
			Double g=Double.parseDouble(grading);
			if(g>=90){
				zldj="优";
			}else if(g>=75){
				zldj="良";
			}else if(g>=60){
				zldj="及格";
			}
		} catch (Exception e) {
			return "不及格";
		}
			
		return zldj;
	}

	/* 
	 * @author huodesheng
	 * @date 2017-9-26
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.CorrectionNoticeService#NoticeTempleteFlowMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> NoticeTempleteFlowMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{ "CORRECTION_NOTICE_TEMPLATE_EDIT"};
//			// 获取当前流程实例操作的表数据
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
			TempCover cover = new TempCover();
			// 设置实施日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			List<String> taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_fenpei_bjy");
			List<Flows> beginFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(processInstId,taskDefId);
			String beginTime="";
			if(beginFlows!=null&&beginFlows.size()>0){
				beginTime=sdf.format(beginFlows.get(0).getEndTime());
			}
			taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_zjy_queren");
			List<Flows> endFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(workProcessInstId,taskDefId);
			String endTime="";
			if(endFlows!=null&&endFlows.size()>0){
				endTime=sdf.format(endFlows.get(0).getEndTime());
			}
			// 设置实施日期
			cover.setTEXT_COVER_DATE(beginTime+"至"+endTime);
			// 设置部门
			cover.setTEXT_COVER_DEPARTMENT("上海海图中心制图");
			// 设置封皮logo图片（路径）
			cover.setIMG_COVER_LOGO("");
			// 获得封皮
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("text_flow_xmmc",complicationTask.getTaskName());
			map.put("text_flow_cbrq", beginTime);
			map.put("text_flow_gzxh",cover.getCoverMap().get("text_cover_item_no"));
			data.put(cover.MARKER, map);

			/**
			 * 改正公告编辑工序流程表
			 */
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			CorrectionNoticeTempletePersonAndDate pd=new CorrectionNoticeTempletePersonAndDate(flowListAll);
			//添加节点
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text_gx_order", "交出版管理科");
			pd.getListMap().add(m);
			data.put("table_flow", pd.getListMap());
			
			/**
			 * 通告期号&本期项号
			 */
			ItemNoIssueRecord itemnoissuerecord =new ItemNoIssueRecord();
			List<List<FormValue>> itemnoissuerecords = base.getFormValueByRowFlag(itemnoissuerecord.ItemNoIssueRecord_FORM_ID, planProcessInstId);
			if (itemnoissuerecords != null)
			{
				if (itemnoissuerecords.size() > 0)
				{
					itemnoissuerecord =new ItemNoIssueRecord();
					for (List<FormValue> list : itemnoissuerecords) {
						itemnoissuerecord.setDataProps(list);
					}
					data.put(itemnoissuerecord.MARKER, itemnoissuerecord.getMap());
				}
			}
			
			// 返回结果数据
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @author huodesheng
	 * @date 2017-9-27
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.CorrectionNoticeService#NoticeFlowMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> NoticeFlowMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{ "CORRECTION_NOTICE_TEMPLATE_EDIT"};
//			// 获取当前流程实例操作的表数据
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
			TempCover cover = new TempCover();
			// 设置实施日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			List<String> taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_fenpei_bjy");
			List<Flows> beginFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(processInstId,taskDefId);
			String beginTime="";
			if(beginFlows!=null&&beginFlows.size()>0){
				beginTime=sdf.format(beginFlows.get(0).getEndTime());
			}
			taskDefId=new ArrayList<String>();
			taskDefId.add("u_task_zjy_queren");
			List<Flows> endFlows = flowsDao.getProcessFlowsByProcessInstIdAndTaskDefId(workProcessInstId,taskDefId);
			String endTime="";
			if(endFlows!=null&&endFlows.size()>0){
				endTime=sdf.format(endFlows.get(0).getEndTime());
			}
			// 设置实施日期
			cover.setTEXT_COVER_DATE(beginTime+"至"+endTime);
			// 设置部门
			cover.setTEXT_COVER_DEPARTMENT("上海海图中心制图");
			// 设置封皮logo图片（路径）
			cover.setIMG_COVER_LOGO("");
			// 获得封皮
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("text_flow_xmmc",complicationTask.getTaskName());
			map.put("text_flow_cbrq", beginTime);
			map.put("text_flow_gzxh",cover.getCoverMap().get("text_cover_item_no"));
			data.put(cover.MARKER, map);

			/**
			 * 改正公告编辑工序流程表
			 */
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			CorrectionNoticePersonAndDate pd=new CorrectionNoticePersonAndDate(flowListAll);
			//添加节点
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text_gx_order", "交出版管理科");
			pd.getListMap().add(m);
			data.put("table_flow", pd.getListMap());
			
			/**
			 * 通告期号&本期项号
			 */
			ItemNoIssueRecord itemnoissuerecord =new ItemNoIssueRecord();
			List<List<FormValue>> itemnoissuerecords = base.getFormValueByRowFlag(itemnoissuerecord.ItemNoIssueRecord_FORM_ID, planProcessInstId);
			if (itemnoissuerecords != null)
			{
				if (itemnoissuerecords.size() > 0)
				{
					itemnoissuerecord =new ItemNoIssueRecord();
					for (List<FormValue> list : itemnoissuerecords) {
						itemnoissuerecord.setDataProps(list);
					}
					data.put(itemnoissuerecord.MARKER, itemnoissuerecord.getMap());
				}
			}
			
			// 返回结果数据
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.CorrectionNoticeService#NoticeSelfQualityEvaluateMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> NoticeSelfQualityEvaluateMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{"CORRECTION_NOTICE_TEMPLATE"};
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
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			//map
			PaperQualityEvaluate qualityEvaluate = new PaperQualityEvaluate();
			//maplist
			PaperQualityEvaluate qualityEvaluates = new PaperQualityEvaluate();
			
			
			ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			Map<String, Object> map =new HashMap<String,Object>();
			// 获取流程作业各操作人和状态
			CorrectionNoticePersonAndDate pd=new CorrectionNoticePersonAndDate(flowListAll);
			qualityEvaluate.setDATE(pd.getCentreQualityvaladitonDate() == null ? "" : pd.getCentreQualityvaladitonDate());
			/**
			 * 纸海图审定记录表
			 */
			List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId,"zxzljcjl");
			String total = null;
			String coefficient = null;
			String actual = null;
			String grading = null;
			if (defectFormList != null)
			{
				if (defectFormList.size() > 0)
				{
					for (int i = 0; i < defectFormList.size(); i++)
					{
						DefectForm defectForm = defectFormList.get(i);
						total = defectForm.getTotal();
						coefficient = defectForm.getCoefficient();
						actual = defectForm.getActual();
						grading = defectForm.getGrading();
						qualityEvaluates = new PaperQualityEvaluate();
						// 设置处理意见
						qualityEvaluates.setCONTENT(defectForm.getOpinion());
						// 设置质量扣分
						qualityEvaluates.setZLKF(defectForm.getScore());
						//设置缺陷个数
						qualityEvaluates.setQXGS(defectForm.getNumber());
						//设置缺陷类别
						qualityEvaluates.setQXLB(defectForm.getDeep());
						//序号
						qualityEvaluates.setNO((i+1)+"");
						//处理结果
						qualityEvaluates.setCLJG("已修改");
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(qualityEvaluates.getMap());
						listMap.add(maps);
					}
					// 设置质量累计扣分
					qualityEvaluate.setLJKF(total);
					//设置质量总分
					qualityEvaluate.setZLKF(grading);
					//设置质量等级
					qualityEvaluate.setZLDJ(getZLDJ(grading));
					}
				}
				//图号
				qualityEvaluate.setMAP_NAME(complicationTask.getTaskName());
				//期号
				qualityEvaluate.setNO_NAME(taskBook.getNoticeNo());
				/**
				 * 审定记录结论表
				 */
				PaperValidationConclusionRecord vc = new PaperValidationConclusionRecord();
				List<List<FormValue>> vcList = base.getFormValueByRowFlag(vc.VALIDATION_CONCLUSION_FROM_ID, workProcessInstId);
				// 质量评分及等级
				String zlpf="";
				String zldj="";
				if(vcList!=null){
					if(vcList.size()>0){
						for (int i = 0; i < vcList.size(); i++)
						{
							vc = new PaperValidationConclusionRecord();
							List<FormValue> vcListI = vcList.get(i);
							vc.setDataProps(vcListI);
							Map<String, Object> mapI = vc.getPaperValidationConclusionRecordMap();
							zlpf = mapI.get("text_sdjljlb_zlpf") == null ? "" : mapI.get("text_sdjljlb_zlpf").toString();
							zldj = mapI.get("text_sdjljlb_zldj") == null ? "" : mapI.get("text_sdjljlb_zldj").toString();
						}
						//最终得分
						qualityEvaluate.setZZDF(zlpf);
						//质量等级
						qualityEvaluate.setZLDJ(zldj);
					}
				}
				//作业部门质检员
				qualityEvaluate.setZYZJ(pd.getDepartmentQualityQualityPerson());
				//质量部门质检员
				qualityEvaluate.setZLZJ(pd.getCentreQualityvaladitonPerson());
				//编辑
				qualityEvaluate.setZLBH(pd.getRegisterCompilationPerson());
				//主任
				qualityEvaluate.setZLKZ(pd.getCentreQualityChief());
				map = qualityEvaluate.getMap();
				map.put(qualityEvaluates.MARKER, listMap);
				data.put(qualityEvaluate.MARKER, map);
				return data;
		} catch (Exception e) {
			throw new CommonException();
		}
	}

	/* 
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.CorrectionNoticeService#NoticeQualityEvaluateMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> NoticeQualityEvaluateMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正公告编辑计划数据准备
			 */
			String[] processDefKeys =
			{"CORRECTION_NOTICE_TEMPLATE"};
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
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			//map
			PaperQualityEvaluate qualityEvaluate = new PaperQualityEvaluate();
			//maplist
			PaperQualityEvaluate qualityEvaluates = new PaperQualityEvaluate();
			
			
			ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			Map<String, Object> map =new HashMap<String,Object>();
			// 获取流程作业各操作人和状态
			CorrectionNoticePersonAndDate pd=new CorrectionNoticePersonAndDate(flowListAll);
			qualityEvaluate.setDATE(pd.getCentreQualityvaladitonDate() == null ? "" : pd.getCentreQualityvaladitonDate());
			/**
			 * 纸海图审定记录表
			 */
			List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId,"zxzljcjl");
			String total = null;
			String coefficient = null;
			String actual = null;
			String grading = null;
			if (defectFormList != null)
			{
				if (defectFormList.size() > 0)
				{
					for (int i = 0; i < defectFormList.size(); i++)
					{
						DefectForm defectForm = defectFormList.get(i);
						total = defectForm.getTotal();
						coefficient = defectForm.getCoefficient();
						actual = defectForm.getActual();
						grading = defectForm.getGrading();
						qualityEvaluates = new PaperQualityEvaluate();
						// 设置处理意见
						qualityEvaluates.setCONTENT(defectForm.getOpinion());
						// 设置质量扣分
						qualityEvaluates.setZLKF(defectForm.getScore());
						//设置缺陷个数
						qualityEvaluates.setQXGS(defectForm.getNumber());
						//设置缺陷类别
						qualityEvaluates.setQXLB(defectForm.getDeep());
						//序号
						qualityEvaluates.setNO((i+1)+"");
						//处理结果
						qualityEvaluates.setCLJG("已修改");
						Map<String,Object> maps=new HashMap<String,Object>();
						maps.putAll(qualityEvaluates.getMap());
						listMap.add(maps);
					}
					// 设置质量累计扣分
					qualityEvaluate.setLJKF(total);
					//设置质量总分
					qualityEvaluate.setZLKF(grading);
					//设置质量等级
					qualityEvaluate.setZLDJ(getZLDJ(grading));
					}
				}
				//图号
				qualityEvaluate.setMAP_NAME(complicationTask.getTaskName());
				//期号
				qualityEvaluate.setNO_NAME(taskBook.getNoticeNo());
				/**
				 * 审定记录结论表
				 */
				defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zxzljcjl");
				grading = null;
				if (defectFormList != null)
				{
					if (defectFormList.size() > 0)
					{
						for (int i = 0; i < defectFormList.size(); i++)
						{
							DefectForm defectForm = defectFormList.get(i);
							grading = defectForm.getGrading();
						}
						//质量等级2
						qualityEvaluate.setZLDJ(getZLDJ(grading));
						//最终检查评分
						qualityEvaluate.setZZDF(grading);
						
					}
				}
				//作业部门质检员
				qualityEvaluate.setZYZJ(pd.getDepartmentQualityQualityPerson());
				//质量部门质检员
				qualityEvaluate.setZLZJ(pd.getCentreQualityvaladitonPerson());
				//编辑
				qualityEvaluate.setZLBH(pd.getRegisterCompilationPerson());
				//主任
				qualityEvaluate.setZLKZ(pd.getCentreQualityChief());
				map = qualityEvaluate.getMap();
				map.put(qualityEvaluates.MARKER, listMap);
				data.put(qualityEvaluate.MARKER, map);
				return data;
		} catch (Exception e) {
			throw new CommonException();
		}
	}

	/* 
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.service.inter.experiencebook.CorrectionNoticeService#NoticeQualityInspectMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> NoticeQualityInspectMap(String processInstId)
			throws Exception {
		try {
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			/**
			 * 开始改正通告编辑数据准备
			 */
			String[] processDefKeys =
			{"CORRECTION_NOTICE_TEMPLATE"};
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
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			//纸海图制作工序流程表
			PaperQualityTestReport qualityTestReport=new PaperQualityTestReport();
			qualityTestReport.setMAP_NAME(complicationTask.getTaskName());
			qualityTestReport.setMAP_NO(complicationTask.getMapNo());

			CorrectionNoticePersonAndDate pd=new CorrectionNoticePersonAndDate(flowListAll);
			//纸海图质检结论记录表
			List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "bmzljcjl");
			String grading = null;
			if (defectFormList != null)
			{
				if (defectFormList.size() > 0)
				{
					for (int i = 0; i < defectFormList.size(); i++)
					{
						DefectForm defectForm = defectFormList.get(i);
						grading = defectForm.getGrading();
					}
					//质量等级1
					qualityTestReport.setGRADE_1(getZLDJ(grading));
					//过程检查评分
					qualityTestReport.setPROCESS_SCORE(grading);
				}
			
			}
			//检查人1
			qualityTestReport.setEXAMINER_1(pd.getDepartmentQualityQualityPerson());
			/**
			 * 审定记录结论表
			 */
			defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, "zxzljcjl");
			grading = null;
			if (defectFormList != null)
			{
				if (defectFormList.size() > 0)
				{
					for (int i = 0; i < defectFormList.size(); i++)
					{
						DefectForm defectForm = defectFormList.get(i);
						grading = defectForm.getGrading();
					}
					//质量等级2
					qualityTestReport.setGRADE_2(getZLDJ(grading));
					//最终检查评分
					qualityTestReport.setFINAL_SCORE(grading);
					
				}
			}
			//期号
			qualityTestReport.setNO_NAME(taskBook.getNoticeNo());
			//检查人2
			qualityTestReport.setEXAMINER_2(pd.getCentreQualityvaladitonPerson());
			//过程检查意见
			qualityTestReport.setPROCESS_OPINION(pd.getDepartmentQualityCompilationAdvice());
			//过程检查意见,作业部门负责人
			qualityTestReport.setOPERATION_PEOPLE(pd.getDepartmentQualityQualityPerson());
			//过程检查意见,作业部门负责人时间
			qualityTestReport.setOPERATION_DATE(pd.getDepartmentQualityQualityDate());
			//最终检查意见
			qualityTestReport.setFINAL_OPINION(pd.getCentreQualityvaladitonAdvice());
			//最终检查意见,作业部门负责人
			qualityTestReport.setFINAL_PEOPLE(pd.getCentreQualityvaladitonPerson());
			//最终检查意见,作业部门负责人时间
			qualityTestReport.setFINAL_DATE(pd.getCentreQualityvaladitonDate());
			//验收结论
			qualityTestReport.setCONCLUSION_OPINION(pd.getEnginnerAdvice());
			//验收结论 作业部门负责人
			qualityTestReport.setCONCLUSION_PEOPLE(pd.getEnginnerPerson());
			//验收结论 作业部门负责人时间
			qualityTestReport.setCONCLUSION_DATE(pd.getEnginnerDate());
		
			
			data.put(qualityTestReport.MARKER,qualityTestReport.getMap());
			
			return data;
		} catch (Exception e) {
			throw new CommonException();
		}
	}
}
