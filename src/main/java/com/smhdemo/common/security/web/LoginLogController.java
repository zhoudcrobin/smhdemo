package com.smhdemo.common.security.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhdemo.common.query.DataGrid;
import com.smhdemo.common.query.DataGridModel;
import com.smhdemo.common.query.Order;
import com.smhdemo.common.query.Resultable;
import com.smhdemo.common.query.jpa.QueryCondition;
import com.smhdemo.common.query.jpa.QueryFactory;
import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.common.security.entity.LoginLog;
import com.smhdemo.web.BaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/loginlog")
public class LoginLogController extends BaseController{
	@Autowired
	private QueryFactory query;
	
	
	@Override
	protected String getPagePath() {
		return "common/security/loginlog";
	}

	@RequestMapping(value = "/index")
	public String indexLoginLog() {
		return getForwardPage("index");
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public Object queryRole(@ModelAttribute DataGridModel model) {
		Map<String, String> parameters = model.getParameters();
		List<QueryParameter> qps = new ArrayList<QueryParameter>();

		String accountName = parameters.get("accountName");
		if (accountName != null && accountName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("accountName", accountName,
					QueryOperateType.Equal);
			qps.add(qp2);
		}

		String ipAddress = parameters.get("ipAddress");
		if (ipAddress != null && ipAddress.length() > 0) {
			QueryParameter qp3 = new QueryParameter("ipAddress", ipAddress,
					QueryOperateType.Equal);
			qps.add(qp3);
		}
		
		SimpleDateFormat format =   new SimpleDateFormat("yyyy-MM-dd");
		Date loginTimeStart=null,loginTimeEnd=null;
		try{
			loginTimeStart= format.parse(parameters.get("loginTimeStart"));
			loginTimeEnd= format.parse(parameters.get("loginTimeEnd"));
		}catch(Exception e){}
		if (loginTimeStart != null && loginTimeEnd != null) {
			Object[] betweenVaue={loginTimeStart,loginTimeEnd};
			QueryParameter qp4 = new QueryParameter("loginTime", betweenVaue,
					QueryOperateType.Between);
			qps.add(qp4);
		}
		
		String orderName = model.getOrder();
		Order order;
		if (orderName == null || orderName.length() == 0) {
			order = new Order("loginTime", "desc");
		} else {
			order = new Order(model.getSort(), orderName);
		}
		return createDataGrid(query.queryByConditions(new QueryCondition(model
				.getPage(), model.getRows(), order, LoginLog.class.getSimpleName(),
				qps)));
	}

	/**
	 * 创建DataGrid VO
	 * 
	 * <p>
	 * 生成前台显示数据值对象
	 * </p>
	 * 
	 * @param result
	 *            查询结果
	 * @return DataGrid
	 */
	protected DataGrid createDataGrid(Resultable result) {
		return new DataGrid(result.getCount(), result.getResultList());
	}
}
