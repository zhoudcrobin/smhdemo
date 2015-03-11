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

/**
 * 权限组管理前端控制
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/role")
public class RoleController {
	private static final Logger logger = LoggerFactory
			.getLogger(RoleController.class);
	@Autowired
	private SecurityFac securityFac;
	@Autowired
	private QueryFactory query;

	@RequestMapping(value = "/index")
	public String indexRole() {
		return "common/security/role/index";
	}
	
	@RequestMapping(value = "/edit")
	public String editRole(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		if ((selections == null || selections.length == 0)) {
			model.addAttribute("role", new Role());
			model.addAttribute("eventOP", "add");
		} else {
			model.addAttribute("role", getRoleVO(Integer.parseInt(selections[0])));
			model.addAttribute("eventOP", "upd");
		}
		return "common/security/role/edit";
	}

	/**
	 * 新增或修改用户.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public String save(
			@RequestParam(value = "addrecordlist") String addrecordlist,
			@RequestParam(value = "eventOP") String eventOP,
			@Valid @ModelAttribute Role role, BindingResult result, Model model) {
		model.addAttribute("eventOP",eventOP);
		model.addAttribute("addrecordlist", addrecordlist);
        if(result.hasErrors()){  
            return "common/security/role/edit";
        } 
		try {
			if (eventOP.equals("upd")) {
				securityFac.updRole(role);
			} else if (eventOP.equals("add")) {
				int id = securityFac.addRole(role);
				if (addrecordlist.length() == 0) {
					model.addAttribute("addrecordlist", addrecordlist
							+ id);
				} else {
					model.addAttribute("addrecordlist", addrecordlist + ","
							+ id);
				}
				model.addAttribute("eventOP", "add");
				
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		model.addAttribute("role", new Role());
		return "common/security/role/edit";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delRole(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		for (String pk : selections) {
			securityFac.delRole(Integer.parseInt(pk));
		}
	}

	protected Role getRoleVO(int pk) {
		try {
			return securityFac.getRole(pk);
		} catch (Exception e) {
			return new Role();
		}
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public Object queryRole(@ModelAttribute DataGridModel model) {
		Map<String, String> parameters = model.getParameters();
		List<QueryParameter> qps = new ArrayList<QueryParameter>();
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
					QueryOperateType.Equal);
			qps.add(qp2);
		}

		String remark = parameters.get("remark");
		if (remark != null && remark.length() > 0) {
			QueryParameter qp3 = new QueryParameter("remark", remark,
					QueryOperateType.CharIn);
			qps.add(qp3);
		}

		String orderName = model.getOrder();
		Order order;
		if (orderName == null || orderName.length() == 0) {
			order = new Order("id", "asc");
		} else {
			order = new Order(model.getSort(), orderName);
		}
		return createDataGrid(query.queryByConditions(new QueryCondition(model
				.getPage(), model.getRows(), order, Role.class.getSimpleName(),
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
	
	@RequestMapping(value = "/permissiondetail")
	public String indexPermissiondetail(@RequestParam(value = "roleID") String roleID,Model model) {
		model.addAttribute("roleID",roleID);
		return "common/security/role/permissiondetail";
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

