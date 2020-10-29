package com.ht.action.statisticalanalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.front.pages.datum.bookinfo.BookInfoPage;
import com.ht.front.pages.datum.file.DatumFilePage;
import com.ht.front.pages.statisticalanalysis.DynamicTablePage;
import com.ht.front.pages.statisticalanalysis.MonthlyPlanPage;
import com.ht.front.pages.statisticalanalysis.SubmissionSummaryPage;
import com.ht.front.pages.statisticalanalysis.YearPlanPage;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.persistence.model.statisticalanalysis.CompilationDynamicSummary;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTable;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.bookinfo.BookFileService;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.bookinfo.ViewBookInfoService;
import com.ht.service.inter.datum.type.DatumCategoryService;
import com.ht.service.inter.statisticalanalysis.CompilationYearPlanService;
import com.ht.service.inter.statisticalanalysis.DynamicTableService;

/**
 * 港口航道图月度在编动态action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class DynamicTableAction extends BaseAction {
	
	/**
	 * 注入港口航道图月度在编动态service
	 */
	@Resource(name = "dynamicTableService")
	DynamicTableService dynamicTableService;
	
	/**
	 * 初始化月度在编动态表数据页面，返回成功列表页面
	 * */
	public String initDynamicTable() {
		DynamicTablePage dtp = new DynamicTablePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", dtp.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取月度在编动态
	 */
	public void getDynamicTable(){
		String startTime = getParam("startTime");
		String endTime = getParam("endTime");
		String year = getParam("year");
		try{
			// 根据查询年月获取月度在编动态列表
			List<DynamicTable> list = dynamicTableService.getDynamicTable(startTime,endTime,year);
			// 排除状态为“完成”的数据不显示
			if(list != null && list.size()>0){
				Iterator<DynamicTable> it = list.iterator();
				while(it.hasNext()){
					DynamicTable x = it.next();
				    if(x.getRemarks().equals("完成")){
				        it.remove();
				    }
				}
			}
			writeSuccessResult(list);
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出
	 * @throws Exception 
	 */
	public void export() throws Exception{
		try {
			// 获取参数
			String dynamic = getParam("dynamicTables");
			dynamicTableService.export(dynamic,respose);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
