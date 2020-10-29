package com.ht.service.inter.catalog.type;

import java.io.IOException;

public interface CatalogTypeService {

	/**
	 * 修改基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	public void modifyData(String baseDataParam) throws Exception;
	
	/**
	 * 导出基础数据
	 * @param baseDataParam json对象
	 * @return 
	 * @throws IOException 
	 * @throws Exception
	 */
	void export() throws Exception;
  
}
