package com.smhdemo.common.datasource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.datasource.entity.Base;
import com.smhdemo.common.datasource.service.BaseServiceable;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Service
public class DataSourceFac {
	@Autowired
	private BaseServiceable baseService;	
	
	/**
	 * 新增数据源
	 * 
	 * @param baseDS ReportDataSource对象
	 * @return Long 数据源对象编号
	 */
	public Long addBase(Base vo){
		return baseService.addBase(vo);
	}
	
	/**
	 * 修改数据源
	 * 
	 * @param baseDS ReportDataSource对象
	 * @return Long 数据源对象编号
	 */
	public Long updBase(Base vo){
		return baseService.updBase(vo);
	}
	/**
	 * 查询数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 * @return ReportDataSource对象
	 */
	public Base getBase(Long pk){
		return baseService.getBase(pk);
	}

	/**
	 * 查询所有数据源对象
	 * 
	 * @return List对象
	 */
	public List<Base> findAllBase(){
		return baseService.findAllBase();
	}

	/**
	 * 删除数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 */
	public void deletedBase(Long pk){
		baseService.deletedBase(pk);
	}
}

