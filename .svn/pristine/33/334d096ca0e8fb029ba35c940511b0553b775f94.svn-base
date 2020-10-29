package com.ht.service.impl.system.symbol;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.symbol.SymbolDao;
import com.ht.persistence.model.system.maps.Maps;
import com.ht.persistence.model.system.symbol.Symbol;
import com.ht.service.inter.system.symbol.SymbolService;
/**
 * 
 * @author huodesheng
 *
 */
public class SymbolServiceImpl implements SymbolService {

	/**
	 * 注入小改正符号管理Dao
	 * */
	@Resource(name="symbolDao")
	private SymbolDao symbolDao;
	

	
	@Override
	public List<Symbol> getSymbolList() throws Exception {
		try {
			return symbolDao.getSymbolList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public Symbol getSymbolsListById(String id) throws Exception {
		try {
			Symbol symbol = new Symbol();
			symbol.setId(id);
			return symbolDao.getSymbolsListById(symbol);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void modify(String id,String code,File myfiles,String myfilesFileName)
			throws Exception {
		try {
			Symbol symbol = new Symbol();
			symbol.setId(id);
			symbol.setCode(code);
			if(!(null == myfiles)){
				//存入图片格式
				int i = myfilesFileName.lastIndexOf(".");
				String imgType = myfilesFileName.substring(i+1, myfilesFileName.length());
				symbol.setPictureType(imgType);
				//将图片存入服务器中
				// 获取项目在服务器的路径
		 		String serverPath = FileUtil.ROOT_PATH;
		 		// 新建一个路径
		 		String path = "\\upload\\symbol\\";
		 		// 创建文件夹
		 		FileUtil.CreateFolder(serverPath+path);
		        InputStream is = new FileInputStream(myfiles);
		        OutputStream os = new FileOutputStream(new File(serverPath+path,code+"."+imgType));
		        byte[] buffer = new byte[1024];
		        int length = 0;
		        while(-1 != (length = is.read(buffer, 0, buffer.length)))
		        {
		            os.write(buffer);
		        }
		        os.close();
		        is.close();
		        //将图片地址存入数据库
		        symbol.setImgUrl(path+code+"."+imgType);
				//存入图片
				BASE64Encoder encoder = new BASE64Encoder();
				BufferedImage  bi = ImageIO.read(myfiles);      
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();      
		        ImageIO.write(bi,symbol.getPictureType(), baos);      
		        byte[] bytes = baos.toByteArray();      
		        String img = encoder.encodeBuffer(bytes).trim();  
		        symbol.setImg(img);
			}
			if(symbol.getId()==null || "".equals(symbol.getId())){
				 //新增保存
				symbol.setId(GenerateSequenceUtil.generateSequenceNo());
				symbolDao.add(symbol);
			}else if(!(null == myfiles) && !(symbol.getId()==null || "".equals(symbol.getId()))){
				//修改保存
				symbolDao.modify(symbol);
			}else if(null == myfiles && !(symbol.getId()==null || "".equals(symbol.getId()))){
				symbolDao.modifyWithoutImg(symbol);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void del(String symbols) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<Symbol> list = (List<Symbol>) DataConverter.convertJson2List(symbols,Symbol.class);
			for (Symbol i:list) {
				// 删除
				symbolDao.del(i);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public Symbol getSymbolsListByCode(String  code) throws Exception {
		// TODO Auto-generated method stub
		try {
			Symbol symbol = new Symbol();
			symbol.setCode(code);
			return symbolDao.getSymbolsListByCode(symbol);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<Symbol> getAllSymbolList() throws Exception {
		try {
			return symbolDao.getAllSymbolList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

}
