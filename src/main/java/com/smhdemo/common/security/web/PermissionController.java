package com.smhdemo.common.security.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.common.security.SecurityFac;
import com.smhdemo.common.security.entity.Permission;
import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.web.CrudBaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/permission")
public class PermissionController extends CrudBaseController<Permission,Integer>{
	@Autowired
	private SecurityFac securityFac;
	@Override
	protected Permission getVO(Integer pk,Model model) {
		List<Role> roleList = securityFac.getAllRole();
		model.addAttribute("roleList", roleList);
		if(pk != null){
			try {
				return securityFac.getPermission(pk);
			} catch (Exception e) {
			}			
		}
		return new Permission();
	}

	@Override
	protected String getCommondName() {
		return "permission";
	}

	@Override
	protected Integer addSaveOperator(Permission vo, BindingResult result,Model model)
			throws Exception {
		if(vo.getRole().getId()==-1){
			vo.setRole(null);
		}
		int id = securityFac.addPermission(vo);
		List<Role> roleList = securityFac.getAllRole();
		model.addAttribute("roleList", roleList);
		return id;
	}

	@Override
	protected Integer updSaveOperator(Permission vo, BindingResult result, Model model) throws Exception {
		return securityFac.updPermission(vo);
	}

	@Override
	protected void deleteOperator(Integer pk) throws Exception {
		securityFac.delPermission(pk);		
	}

	@Override
	protected String queryOperator(List<QueryParameter> qps,Map<String, String> parameters) {
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
		
		return Permission.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/security/permission";
	}
}
