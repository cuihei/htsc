package com.ht.service.inter.system.symbol;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.system.maps.Maps;
import com.ht.persistence.model.system.symbol.Symbol;

/**
 * 小改正符号service
 * @author	huodesheng
 * */
public interface SymbolService {
	
  	/**
	 *查询所有图幅
	 * @param Maps Maps实体
	 */
  public List<Symbol> getSymbolList() throws Exception;
  /**
   *查询所有图幅
   * @param Maps Maps实体
   */
  public List<Symbol> getAllSymbolList() throws Exception;
  
  /**
	 *根据id查询
	 * @param 
	 */
  public Symbol getSymbolsListById(String id) throws Exception;
  
  /**
   * 更新
   * @throws Exception
   */
  public void modify(String id,String code,File myfiles,String myfilesFileName) throws Exception;
  
  /**
   * 删除所选
   * @param ids 多选id字符串
   * @throws Exception
   */
  public void del(String symbols) throws Exception;
  /**
	 *根据code查询
	 * @param Symbol Symbol实体
	 */
	public Symbol getSymbolsListByCode(String code) throws Exception;
}
