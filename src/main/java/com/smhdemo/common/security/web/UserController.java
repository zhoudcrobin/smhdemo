package com.smhdemo.common.security.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhdemo.common.query.DataGrid;
import com.smhdemo.common.query.DataGridModel;
import com.smhdemo.common.query.Order;
import com.smhdemo.common.query.Resultable;
import com.smhdemo.common.query.jpa.QueryCondition;
import com.smhdemo.common.query.jpa.QueryFactory;
import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.common.security.SecurityFacable;
import com.smhdemo.common.security.entity.User;

/**
 * 用户前端控制 
 * 
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/security/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private SecurityFacable securityFac;
	@Autowired
	private QueryFactory query;
	
	@RequestMapping(value = "/index.do")
	public String indexUser() {
		return "security/user/index";
	}
	
	@RequestMapping(value = "/edit.do")
	public String editUser(@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		User vo = (selections == null || selections.length == 0) ? new User():getUserVO(selections[0]);
		model.addAttribute("user",vo);
		return "security/user/edit";
	}
	
	/**
	 * 新增或修改用户.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save.do")
	public String save(@ModelAttribute User user){
		try{
		if (isUpdate(user)) {
			securityFac.updUser(user);
		} else {
			securityFac.addUser(user);
		}
		}catch(Exception e){
			logger.debug(e.toString());
		}
		
		return "security/user/edit";
	}
	
	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public String delUser(@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		for (String pk : selections) {
			securityFac.delUser(pk);
		}
		return "true";
	}


	protected User getUserVO(String pk) {
		try{
			return securityFac.getUser(pk);
		}catch(Exception e){
			return new User();
		}
	}
	
	
	protected boolean isUpdate(User vo){
	    return getUserVO(vo.getAccountName()) != null;
	}
	
	@RequestMapping(value = "/query.do")
	@ResponseBody
	public Object queryUser(@ModelAttribute DataGridModel model) {
		Map<String, String> parameters = model.getParameters();
		List<QueryParameter> qps = new ArrayList<QueryParameter>();
		String selections = parameters.get("selections");
		if (selections != null && selections.length() > 0) {
			QueryParameter qp1 = new QueryParameter("accountName", selections,
					QueryOperateType.In);
			qps.add(qp1);
		}

		String accountName = parameters.get("accountName");
		if (accountName != null && accountName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("accountName", accountName,
					QueryOperateType.Equal);
			qps.add(qp2);
		}

		String niceName = parameters.get("niceName");
		if (niceName != null && niceName.length() > 0) {
			QueryParameter qp3 = new QueryParameter("niceName", niceName,
					QueryOperateType.CharIn);
			qps.add(qp3);
		}

		String orderName = model.getOrder();
		Order order;
		if (orderName == null || orderName.length() == 0) {
			order = new Order("accountName","DESC");
		} else {
			order = new Order(model.getSort(), orderName);
		}
		return createDataGrid(query.queryByConditions(new QueryCondition(model
				.getPage(), model.getRows(), order, User.class.getSimpleName(),
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
