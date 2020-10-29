package com.ht.action.system.maps;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.maps.MapsPage;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.system.maps.Maps;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.catalog.detail.CatalogDetailService;
import com.ht.service.inter.system.maps.MapsService;

/**
 * 图幅管理
 * @author yp
 *
 */
@SuppressWarnings("serial")
public class MapsAction extends BaseAction{
	
	//文件上传
	private File myfiles;
	//文件上传名称
	private String myfilesFileName;
	//文件上传类型
	private String myfilesContentType;
	
	public File getMyfiles() {
		return myfiles;
	}

	public void setMyfiles(File myfiles) {
		this.myfiles = myfiles;
	}

	public String getMyfilesFileName() {
		return myfilesFileName;
	}

	public void setMyfilesFileName(String myfilesFileName) {
		this.myfilesFileName = myfilesFileName;
	}

	public String getMyfilesContentType() {
		return myfilesContentType;
	}

	public void setMyfilesContentType(String myfilesContentType) {
		this.myfilesContentType = myfilesContentType;
	}
	
	@Resource
	private MapsService mapsService;
	
	@Resource
	private CatalogDetailService catalogDetailService;
	
	@Resource
	private CatalogAreaService catalogAreaService;
	
	@Resource
	private BaseDataService baseDataService;
	
	/**
	 * 首页面
	 * @return 首页面
	 */
	public String init(){
		MapsPage mp = new MapsPage();
		request.setAttribute("html", mp.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 编辑页面
	 * @return 编辑页面
	 * @throws Exception 
	 */
	public String initEdit() throws Exception{
		String id = getParam("id");
		MapsPage mp = new MapsPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",mp.getEditNode(mapsService,catalogDetailService,catalogAreaService,baseDataService,id));
		return SUCCESS;
	}

	/**
	 * 获取图幅数据列表
	 */
	public void getMapsList(){
		try {
			//执行查询方法
			List<Maps> mapsList = mapsService.getMapsList();
			writeSuccessResult(mapsList);
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新区域管理数据
	 * @throws Exception 
	 */
	public void update() throws Exception{
		try{
			String mapNo = getParam("mapNo");
			String id = getParam("id");
			mapsService.modify(id,mapNo,myfiles,myfilesFileName);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 目录类型级联
	 * @throws Exception 
	 */
	public void changeCatalogType() throws Exception{
		try{
			String catalogTypeId = getParam("catalogTypeId");
			List<CatalogArea> areaList = catalogDetailService.getCatalogAreaListFromDetail(catalogTypeId);
			writeSuccessResult(areaList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 目录区域级联
	 * @throws Exception 
	 */
	public void changeCatalogAreaId() throws Exception{
		try{
			String catalogAreaId = getParam("catalogAreaId");
			String catalogTypeId = getParam("catalogTypeId");
			List<CatalogDetail> list = catalogDetailService.getDetailByCategoryId(catalogTypeId,catalogAreaId);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 图号级联
	 * @throws Exception 
	 */
	public void changeMapNo() throws Exception{
		try{
			String mapNo = getParam("mapNo");
			String catalogTypeId = getParam("catalogTypeId");
			List<CatalogDetail> list = catalogDetailService.getDetialListByMapNo(mapNo,catalogTypeId);
			writeSuccessResult(list.get(0));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 初始化图片查看
	 * @return String 返回类型
	 */
	public String initViewImg() {
		String id = getParam("id");
		try {
			MapsPage mp = new MapsPage();
			String editPage = mp.getViewPage(mapsService,id);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除数据
	 */
	public void remove(){
		//获取删除id
		String maps = getParam("maps");
		try {
			mapsService.del(maps);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
