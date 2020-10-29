package com.ht.persistence.dao.inter.system.symbol;
import java.util.List;

import com.ht.persistence.model.system.symbol.Symbol;

/**
 * SymbolDao
 * @author huodesheng
*/
public interface SymbolDao {
	
	/**
	 * 获取所有Symbol
	 * @param Symbol Symbol实体
	 */
	public List<Symbol> getSymbolList() throws Exception;
	
	/**
	 * 获取所有Symbol
	 * @param Symbol Symbol实体
	 */
	public List<Symbol> getAllSymbolList() throws Exception;
	
	/**
	 *根据id查询
	 * @param Symbol Symbol实体
	 */
	public Symbol getSymbolsListById(Symbol symbol) throws Exception;
	/**
	 *根据code查询
	 * @param Symbol Symbol实体
	 */
	public Symbol getSymbolsListByCode(Symbol symbol) throws Exception;
	
	/**
	 *保存
	 * @param Symbol Symbol实体
	 */
	public void add(Symbol symbol);
	
	/**
	 *更新
	 * @param Symbol Symbol实体
	 */
	public void modify(Symbol symbol);
	
	/**
	 * 删除
	 * @param  Symbol 实体
	 */
	public void del(Symbol symbol);
	/**
	 * 只更新特征码
	 * @param symbol
	 */
	public void modifyWithoutImg(Symbol symbol);
}
