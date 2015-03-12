package com.smhdemo.common.security.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.smhdemo.common.security.SecurityFac;
import com.smhdemo.common.security.entity.Permission;
import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.web.CrudBaseController;

/**
 * 权限组管理前端控制
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/role")
public class RoleController extends CrudBaseController<Role,Integer>{
	private static final Logger logger = LoggerFactory
			.getLogger(RoleController.class);
	@Autowired
	private SecurityFac securityFac;

	@Override
	protected Role getVO(Integer pk, Model model) {
		if(pk != null){
			try {
				return securityFac.getRole(pk);
			} catch (Exception e) {
			}			
		}
		return new Role();
	}

	@Override
	protected String getCommondName() {
		return "role";
	}

	@Override
	protected Integer addSaveOperator(Role vo, BindingResult result, Model model)
			throws Exception {
		return securityFac.addRole(vo);
	}

	@Override
	protected Integer updSaveOperator(Role vo) throws Exception {
		return securityFac.updRole(vo);
	}

	@Override
	protected void deleteOperator(Integer pk) throws Exception {
		securityFac.delRole(pk);		
	}

	@Override
	protected String queryOperator(List<QueryParameter> qps,
			Map<String, String> parameters) {
		String selections = parameters.get("selections");
		if (selections != null && selections.length() > 0) {
			List<Integer> pks = new ArrayList<Integer>();
			List<String> sls = Arrays.asList(selections.split(","));
			for(String pk :sls){
				pks.add(Integer.parseInt(pk));
			}
			QueryParameter qp1 = new QueryParameter("id", pks,
					QueryOperateType.In);

			qps.add(qp1);
		}

		String roleName = parameters.get("roleName");
		if (roleName != null && roleName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("roleName", roleName,
					QueryOperateType.CharIn);
			qps.add(qp2);
		}

		String remark = parameters.get("remark");
		if (remark != null && remark.length() > 0) {
			QueryParameter qp3 = new QueryParameter("remark", remark,
					QueryOperateType.CharIn);
			qps.add(qp3);
		}
		return Role.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/security/role";
	}


	
	@RequestMapping(value = "/permissiondetail")
	public String indexPermissiondetail(@RequestParam(value = "roleID") String roleID,Model model) {
		model.addAttribute("roleID",roleID);
		return getForwardPage("permissiondetail");
	}	
	
	@RequestMapping(value = "/permissionquery")
	@ResponseBody
	public Object queryPermission(@RequestParam(value = "roleID") String roleID,@ModelAttribute DataGridModel model) {
		List<QueryParameter> qps = new ArrayList<QueryParameter>();
			QueryParameter qp3 = new QueryParameter("role.id", Integer.parseInt(roleID),
					QueryOperateType.Equal);
			qps.add(qp3);
		

		String orderName = model.getOrder();
		Order order;
		if (orderName == null || orderName.length() == 0) {
			order = new Order("id", "asc");
		} else {
			order = new Order(model.getSort(), orderName);
		}
		return createDataGrid(query.queryByConditions(new QueryCondition(model
				.getPage(), model.getRows(), order, Permission.class.getSimpleName(),
				qps)));
	}
}

