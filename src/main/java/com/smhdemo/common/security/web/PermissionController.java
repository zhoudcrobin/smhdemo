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
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/permission")
public class PermissionController {
	private static final Logger logger = LoggerFactory
			.getLogger(PermissionController.class);
	@Autowired
	private SecurityFac securityFac;
	@Autowired
	private QueryFactory query;

	@RequestMapping(value = "/index")
	public String indexPermission() {
		return "common/security/permission/index";
	}
	
	@RequestMapping(value = "/edit")
	public String editPermission(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		if ((selections == null || selections.length == 0)) {
			model.addAttribute("permission", new Permission());
			model.addAttribute("eventOP", "add");
		} else {
			model.addAttribute("permission", getPermissionVO(Integer.parseInt(selections[0])));
			model.addAttribute("eventOP", "upd");
		}
		List<Role> roleList = securityFac.getAllRole();
		model.addAttribute("roleList", roleList);
		return "common/security/permission/edit";
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
			@Valid @ModelAttribute Permission permission, BindingResult result, Model model) {
		model.addAttribute("eventOP",eventOP);
		model.addAttribute("addrecordlist", addrecordlist);
        if(result.hasErrors()){  
            return "common/security/permission/edit";
        } 
		try {
			if (eventOP.equals("upd")) {
				securityFac.updPermission(permission);
			} else if (eventOP.equals("add")) {
				if(permission.getRole().getId()==-1){
					permission.setRole(null);
				}
				int id = securityFac.addPermission(permission);
				if (addrecordlist.length() == 0) {
					model.addAttribute("addrecordlist", addrecordlist
							+ id);
				} else {
					model.addAttribute("addrecordlist", addrecordlist + ","
							+ id);
				}
				model.addAttribute("eventOP", "add");
				List<Role> roleList = securityFac.getAllRole();
				model.addAttribute("roleList", roleList);
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		model.addAttribute("permission", new Permission());
		return "common/security/permission/edit";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delRole(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		for (String pk : selections) {
			securityFac.delPermission(Integer.parseInt(pk));
		}
	}

	protected Permission getPermissionVO(int pk) {
		try {
			return securityFac.getPermission(pk);
		} catch (Exception e) {
			return new Permission();
		}
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public Object queryPermission(@ModelAttribute DataGridModel model) {
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

		String permissionName = parameters.get("permissionName");
		if (permissionName != null && permissionName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("permissionName", permissionName,
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
				.getPage(), model.getRows(), order, Permission.class.getSimpleName(),
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
