package com.ht.service.inter.correctionnoticesearch;

import java.util.List;

import com.ht.persistence.model.correctionnotice.CorrectionNotice;
import com.ht.persistence.model.correctionnotice.NoticeBaseData;
import com.ht.persistence.model.correctionnotice.NoticeYear;

/**
 * 改正通知公告业务接口 CorrectionNoticeService
 * @author huodesheng 
 * @date 2016/10/18
 */
public interface CorrectionNoticeService {
	/**
	 * 根据条件查询改正通告
	 * @param ChartNo 图号
	 * @param key	关键字
	 * @param ddlType	通告类型
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @param ddlAct	改正行为
	 * @return
	 * @throws Exception 
	 */
	public List<CorrectionNotice> queryNoticeByCondition(String ChartNo,String key,String ddlType,String startTime,String endTime,String ddlAct,String NoticesNo,String NoticesNo2) throws Exception;
	/**
	 * 根据条件查询改正通告
	 * @param ChartNo 图号
	 * @param key	关键字
	 * @param ddlType	通告类型
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @param ddlAct	改正行为
	 * @return
	 * @throws Exception 
	 */
	public List<CorrectionNotice> queryNoticeByItemNoCondition(String itemNo,String key,String ddlType,String startTime,String ddlAct) throws Exception;
	/**
	 * 根据id查询数据
	 * @param id 
	 * @return
	 */
	public NoticeBaseData queryById(String id);
	/**
	 * 获取通告类型列表
	 * @return
	 */
	public List<NoticeBaseData> getDdlTypeList();
	/**
	 * 获取改正行为列表
	 * @return
	 */
	public List<NoticeBaseData> getDdlActList();
	/**
	 * 获取改正行为列表
	 * @return
	 */
	public List<NoticeYear> getYearList();

	/**
	 * 根据条件查询最新改正通告
	 * @param IssueID 期号id
	 * @return
	 * @throws Exception 
	 */
	public List<CorrectionNotice> queryNewestByCondition(String IssueID) throws Exception;
	
	
}
