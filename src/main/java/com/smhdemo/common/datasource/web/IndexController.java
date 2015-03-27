package com.smhdemo.common.datasource.web;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhdemo.common.datasource.DataSourceFac;
import com.smhdemo.common.datasource.entity.Base;
import com.smhdemo.common.datasource.generate.factory.DataSourceFactoryable;
import com.smhdemo.common.datasource.generate.factory.init.InitDataSourceFactoryable;
import com.smhdemo.common.datasource.generate.service.DataSourceServiceable;
import com.smhdemo.web.BaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/datasource")
public class IndexController extends BaseController {
	@Autowired
	private DataSourceFac dataSourceFac;
	@Autowired
	private InitDataSourceFactoryable initDataSourceFactory;
	
	@RequestMapping(value = "/index")
	public String forwordHome(){
		return this.getForwardPage("index");
	}
	@Override
	protected String getPagePath() {
		return "common/datasource";
	}
	
	@RequestMapping(value = "/connect")
	@ResponseBody
	public String connectTest(@RequestParam(value = "id") Long datasourceId){
		DataSourceServiceable service = null;
		Connection con = null;
		String testResults = "测试数据库连接失败,请确认填写的内容正确!";
		try{
			Base alqcDataSource = dataSourceFac.getBase(datasourceId);

			DataSourceFactoryable factory = (DataSourceFactoryable) initDataSourceFactory.getBean(alqcDataSource.getClass());
			service = factory.createService(alqcDataSource);
			con = service.openConnection();
			
			if (!con.isClosed())
				testResults = "测试数据库连接正确,您可以在以后的程序中使用!";
		}catch(Exception e){
		}finally{
			try{
				if (con != null){
					con.close();
					con = null;
				}
			}catch(Exception e){
			}
			try{
				if (service != null){
					service.closeConnection();
					service = null;
				}
			}catch(Exception e){
			}
		}
		
		return testResults;
	}
}

