package com.ht.service.impl.system.maps;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.maps.MapsDao;
import com.ht.persistence.model.system.maps.Maps;
import com.ht.service.inter.system.maps.MapsService;

public class MapsServiceImpl implements MapsService{
	
	/**
	 * 注入图幅管理Dao
	 * */
	@Resource
	private MapsDao mapsDao;
	

	/**
	 * 获取所有Maps
	 * @return Maps列表
	 */
	@Override
	public List<Maps> getMapsList() throws Exception {
		try {
			return mapsDao.getMapsList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id查询
	 * @return Maps 实体
	 */
	@Override
	public Maps getMapsListById(String id) throws Exception {
		try {
			Maps maps = new Maps();
			maps.setId(id);
			return mapsDao.getMapsListById(maps);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 更新所选
	 * @throws Exception
	 */
	@Override
	public void modify(String id,String mapNo,File myfiles,String myfilesFileName) throws Exception {
		try {
			Maps maps = new Maps();
			maps.setMapNo(mapNo);
			maps.setId(id);
			if(!(null == myfiles)){
				//存入图片格式
				int i = myfilesFileName.lastIndexOf(".");
				String imgType = myfilesFileName.substring(i+1, myfilesFileName.length());
				maps.setPictureType(imgType);
				//存入图片
				BASE64Encoder encoder = new BASE64Encoder();
				BufferedImage  bi = ImageIO.read(myfiles);      
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();      
		        ImageIO.write(bi,maps.getPictureType(), baos);      
		        byte[] bytes = baos.toByteArray();      
		        String img = encoder.encodeBuffer(bytes).trim();  
			    maps.setPicture(img);
			}
			if(maps.getId()==null || "".equals(maps.getId())){
				 //新增保存
				maps.setId(GenerateSequenceUtil.generateSequenceNo());
				mapsDao.add(maps);
			}else if(!(null == myfiles) && !(maps.getId()==null || "".equals(maps.getId()))){
				//修改保存
				mapsDao.modify(maps);
			}else if(null == myfiles && !(maps.getId()==null || "".equals(maps.getId()))){
				mapsDao.modifyWithoutImg(maps);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除所选
	 * 
	 * @param ids
	 *            多选id字符串
	 * @throws Exception
	 */
	@Override
	public void del(String maps) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<Maps> list = (List<Maps>) DataConverter.convertJson2List(maps,Maps.class);
			for (Maps i:list) {
				// 删除
				mapsDao.del(i);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
}
