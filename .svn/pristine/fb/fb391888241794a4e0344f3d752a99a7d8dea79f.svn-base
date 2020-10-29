package com.ht.action.system.workflow.publish;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.front.pages.system.workflow.publish.VProcessDetailPage;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;

@SuppressWarnings("serial")
public class VProcessDetailAction extends BaseAction{
	@Resource
	private VProcessDetailService vProcessDetailService;
	
	
	public String index(){
		VProcessDetailPage page=new VProcessDetailPage();
		String userNo="1";
		request.setAttribute("html",page.getPage(vProcessDetailService,userNo));
		return SUCCESS;
	}
	
	public void findList() throws Exception{
		List<VProcessDetail> detailList = vProcessDetailService.findListByUserNo("1");
		Map<String, List<Object>> map = vProcessDetailService.findListByTable(detailList);
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			List<Object> list = map.get(string);
		}
		System.out.println("@@@@@@@@@@@@@@@@@22");
	}
	
}
