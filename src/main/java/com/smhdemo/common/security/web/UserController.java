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
import com.smhdemo.common.security.SecurityFacable;
import com.smhdemo.common.security.entity.User;

/**
 * 用户前端控制
 * 
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/user")
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private SecurityFacable securityFac;
	@Autowired
	private QueryFactory query;

	@RequestMapping(value = "/index.do")
	public String indexUser() {
		return "common/security/user/index";
	}

	@RequestMapping(value = "/edit.do")
	public String editUser(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		if ((selections == null || selections.length == 0)) {
			model.addAttribute("user", new User());
			model.addAttribute("eventOP", "add");
		} else {
			model.addAttribute("user", getUserVO(selections[0]));
			model.addAttribute("eventOP", "upd");
		}
		return "common/security/user/edit";
	}

	/**
	 * 新增或修改用户.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save.do")
	public String save(
			@RequestParam(value = "addrecordlist") String addrecordlist,
			@RequestParam(value = "eventOP") String eventOP,
			@Valid @ModelAttribute User user, BindingResult result, Model model) {
		model.addAttribute("eventOP",eventOP);
		model.addAttribute("addrecordlist", addrecordlist);
        if(result.hasErrors()){  
            return "common/security/user/edit";
        } 
		try {
			if (eventOP.equals("upd")) {
				securityFac.updUser(user);
			} else if (eventOP.equals("add")) {
				if(getUserVO(user.getAccountName())!=null){
					result.rejectValue("accountName", "账号已存在","账号已存在");
					return "common/security/user/edit";
				}
				String accountName = securityFac.addUser(user);
				if (addrecordlist.length() == 0) {
					model.addAttribute("addrecordlist", addrecordlist
							+ accountName);
				} else {
					model.addAttribute("addrecordlist", addrecordlist + ","
							+ accountName);
				}
				model.addAttribute("eventOP", "add");
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		model.addAttribute("user", new User());
		return "common/security/user/edit";
	}

	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public String delUser(
			@RequestParam(value = "selections", required = false) String[] selections,
			Model model) throws Exception {
		for (String pk : selections) {
			securityFac.delUser(pk);
		}
		return "true";
	}

	protected User getUserVO(String pk) {
		try {
			return securityFac.getUser(pk);
		} catch (Exception e) {
			return new User();
		}
	}

	protected boolean isUpdate(User vo) {
		return getUserVO(vo.getAccountName()) != null;
	}

	@RequestMapping(value = "/query.do")
	@ResponseBody
	public Object queryUser(@ModelAttribute DataGridModel model) {
		Map<String, String> parameters = model.getParameters();
		List<QueryParameter> qps = new ArrayList<QueryParameter>();
		String selections = parameters.get("selections");

		if (selections != null && selections.length() > 0) {
			List<String> pks = Arrays.asList(selections.split(","));
			QueryParameter qp1 = new QueryParameter("accountName", pks,
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
			order = new Order("accountName", "desc");
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
