package com.ht.persistence.dao.impl.system.symbol;

import java.util.List;

import org.hibernate.Query;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.symbol.SymbolDao;
import com.ht.persistence.model.system.symbol.Symbol;

public class SymbolDaoImpl extends BaseDaoImpl  implements SymbolDao {


	@Override
	public Symbol getSymbolsListById(Symbol symbol) throws Exception {
		try {

			@SuppressWarnings("unchecked")
			List<Symbol> result = this.findByNamedQueryAndNamedParam("getSymbolsListById","id",symbol.getId());
			return result.get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@Override
	public Symbol getSymbolsListByCode(Symbol symbol) throws Exception {
		try {
			
			@SuppressWarnings("unchecked")
			List<Symbol> result = this.findByNamedQueryAndNamedParam("getSymbolsListByCode","code",symbol.getCode());
			return result.get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void add(Symbol symbol) {
		this.save(symbol);
	}

	@Override
	public void modify(Symbol symbol) {
		this.update(symbol);
	}

	@Override
	public void del(Symbol symbol) {
		this.delete(this.load(Symbol.class, symbol.getId()));
	}

	@Override
	public List<Symbol> getSymbolList() throws Exception {
		try {
			/*@SuppressWarnings("unchecked")
			List<Symbol> result = this.findByNamedQuery("getSymbolList");*/
			Query createQuery = this.getSession().createQuery("select new Symbol(id,code) from Symbol where 1=1");
			
			return createQuery.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void modifyWithoutImg(Symbol symbol) {
		String sql = "update Symbol s set s.code = '"+symbol.getCode()+"' where s.id = '"+symbol.getId()+"'";
		this.getSession().createQuery(sql).executeUpdate();
	}
	@Override
	public List<Symbol> getAllSymbolList() throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<Symbol> result = this.findByNamedQuery("getSymbolList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
