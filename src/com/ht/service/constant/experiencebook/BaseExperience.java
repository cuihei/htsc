package com.ht.service.constant.experiencebook;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ht.common.constant.CommonConstants;
import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.complication.formprop.FormPropDao;
import com.ht.persistence.dao.inter.system.symbol.SymbolDao;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.symbol.Symbol;

public class BaseExperience
{
	public void setProps(Map<String,Object> map,String formId,List<FormValue> valueList,String name){
		FormPropDao formPropDao = (FormPropDao) SpringUtil.getBean("formPropDao");
		SymbolDao symbolDao = (SymbolDao) SpringUtil.getBean("symbolDao");
		List<FormPropFormView> props = formPropDao.getFormPropList(formId);
		String[] templeteTextArray = CommonConstants.TEMPLETE_TEXT_ARRAY;
		for (int i = 0; i < props.size(); i++)
		{
			FormPropFormView propViews = props.get(i);
			String propKey = propViews.getPropKey();
			String propType = propViews.getPropType();
			if (valueList != null)
			{
				for (int j = 0; j < valueList.size(); j++)
				{
					if (valueList.get(j).getPropKey().equals(propKey)&&!StringUtils.isEmpty(valueList.get(j).getPropValue()))
					{
						Object value= valueList.get(j).getPropValue();
						/*if(Arrays.binarySearch(templeteTextArray,"kendoselect")!=-1){
							propType="text";
						}*/
						for (String string : templeteTextArray) {
							if(string.equalsIgnoreCase(propType)){
								propType="text";
					 		}
						}
						if("imgselect".equals(propType)){
							propType="img";
							Symbol symbol=new Symbol();
							symbol.setCode((String)value);
							try {
								symbol = symbolDao.getSymbolsListByCode(symbol);
								if(symbol!=null){
									value="/ht"+symbol.getImgUrl();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						//纸海图图历簿中的chart,sheet,panel属性信息中的换行符应如何消除，如无好的办法，  王娟 2018.9.20
						//请修改系统流程中该项输入时的格式为图片格式，并测试能否将编绘员上传的格式，
						//缩小到固定的合适格式。电子海图图历簿中的“产品属性信息”eleattributes 处理方式同上。
						
					if(value.toString()!= null|| value.toString().length() != 0|| value.toString()!="") {
					/*	System.out.println(value);
						System.out.println(propKey);
						System.out.println(propType);*/
						
						if(propKey.contains("chart")||propKey.contains("sheet")||propKey.contains("panel")||propKey.contains("eleattributes"))
						{
							if(value.toString().contains("ht\\upload\\formImg")){
								map.put("img_"+name+"_"+propKey,value);	
							    map.put("text_"+name+"_"+propKey,null);		
							}else {
								map.put("text_"+name+"_"+propKey,value);	
							    map.put("img_"+name+"_"+propKey,null);
							};
							
						}else {
                         map.put(propType+"_"+name+"_"+propKey,value);	
						};
						
						
				    };
				    }
				}
			}
		}
	}
}
