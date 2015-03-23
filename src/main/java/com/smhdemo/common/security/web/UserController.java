package com.smhdemo.common.security.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.common.security.SecurityFac;
import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.web.CrudBaseController;

/**
 * 用户前端控制
 * 
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/user")
public class UserController extends CrudBaseController<User,Integer>{
	@Autowired
	private SecurityFac securityFac;
	
	@Override
	public String getPagePath() {
		return "common/security/user";
	}
	@Override
	protected User getVO(Integer pk,Model model) {
		if(pk != null){
			try {
				return securityFac.getUser(pk);
			} catch (Exception e) {
			}			
		}
		return new User();
	}

	@Override
	protected String getCommondName() {
		return "user";
	}

	@Override
	protected Integer addSaveOperator(User vo,BindingResult result,Model model) throws Exception{
		if(securityFac.getUser(vo.getAccountName())!=null){
			result.rejectValue("accountName", "账号已存在","账号已存在");
			return null;
		}
		return securityFac.addUser(vo);
	}
	@Override
	protected Integer updSaveOperator(User vo, BindingResult result, Model model) throws Exception{
		return securityFac.updUser(vo);
	}

	@Override
	protected void deleteOperator(Integer pk)throws Exception {
		securityFac.delUser(pk);
		
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

		String accountName = parameters.get("accountName");
		if (accountName != null && accountName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("accountName", accountName,
					QueryOperateType.Equal);
			qps.add(qp2);
		}

		String niceName = parameters.get("realName");
		if (niceName != null && niceName.length() > 0) {
			QueryParameter qp3 = new QueryParameter("userInfo.realName", niceName,
					QueryOperateType.CharIn);
			qps.add(qp3);
		}
		
		return User.class.getSimpleName();
	}

	@RequestMapping(value = "/roleallocate", method = RequestMethod.GET)
	public String permissionAllocate(@RequestParam(value = "userID", required = true) String userID,Map<String, Object> map) {
		Map<String, String> roleMap = new HashMap<String, String>(); 
		List<String> roles = new ArrayList<String>();
		RoleSelect roleSelectVo = new RoleSelect();
		User vo = new User();
		try{
			vo = securityFac.getUser(Integer.parseInt(userID));
			List<Role> roleList = vo.getRoleList();
			for(Role roleVo:roleList){
				roles.add(roleVo.getId().toString());
			}
			roleSelectVo.setRoles(roles);
			List<Role> allRoleList = securityFac.getAllRole();
			for(Role roleVo:allRoleList){
				roleMap.put(roleVo.getId().toString(), roleVo.getRoleName()+"  ");
			}
		}catch(Exception e){}
		map.put("roleSelect", roleSelectVo);
		map.put("roleMap", roleMap);
		map.put("userID", userID);
		map.put("accountName", vo.getAccountName());
		return getForwardPage("roleallocate");
	}
	
	@RequestMapping(value = "/roleallocate", method = RequestMethod.POST)
	public String permissionAllocate(@ModelAttribute RoleSelect roleSelect,@RequestParam(value = "userID", required = true) int userID) {
		try{
			User vo = securityFac.getUser(userID);
			List<String> roles =roleSelect.getRoles();
			List<Role> roleList = new ArrayList<Role>();
			for(String roleID:roles){
				Role roleVo = new Role();
				roleVo.setId(Integer.parseInt(roleID));
				roleList.add(roleVo);
			}
			vo.setRoleList(roleList);
			securityFac.updUser(vo);
			
		}catch(Exception e){}
		return getForwardPage("allocatesuccess");
	}
}
