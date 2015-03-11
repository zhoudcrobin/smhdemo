package com.smhdemo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.smhdemo.common.security.entity.User;
/**
 * 
 *  
 * @author zhoudongchu
 */
public abstract class CrudBaseController<T,PK> {
	@Autowired
	private QueryFactory query;
	
	public enum OperatorState {

		add, upd;
	}
	
	public String getForwardPage(String pageFileName){
		return getPagePath()+"/"+pageFileName;
	}
	protected abstract String getPagePath();
	
	@RequestMapping(value = "/index")
	public String index() {
		return getForwardPage("index");
	}

	@RequestMapping(value = "/edit")
	public String edit(
			@RequestParam(value = "selections", required = false) PK[] selections,
			Model model) throws Exception {
		if ((selections == null || selections.length == 0)) {
			model.addAttribute(getCommondName(), getVO(null));
			model.addAttribute("eventOP", OperatorState.add);
		} else {
			model.addAttribute(getCommondName(), getVO(selections[0]));
			model.addAttribute("eventOP", OperatorState.upd);
		}
		return getForwardPage("edit");
	}
	protected abstract T getVO(PK pk);
	protected abstract String getCommondName();
	
	/**
	 * 
	 * 新增或修改用户.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public String save(
			@RequestParam(value = "addrecordlist") String addrecordlist,
			@RequestParam(value = "eventOP") OperatorState eventOP,
			@Valid @ModelAttribute T vo, BindingResult result, Model model) {
		model.addAttribute("eventOP",eventOP);
		model.addAttribute("addrecordlist", addrecordlist);
        if(result.hasErrors()){  
        	return getForwardPage("edit");
        } 
		try {
			if (eventOP.equals(OperatorState.upd)) {
				updSaveOperator(vo);
			} else if (eventOP.equals(OperatorState.add)) {
				PK id = addSaveOperator(vo,result);
				if(id == null)return getForwardPage("edit");
				if (addrecordlist.length() == 0) {
					model.addAttribute("addrecordlist", addrecordlist+ id);
				} else { 
					model.addAttribute("addrecordlist", addrecordlist + ","	+ id);
				}

				model.addAttribute("eventOP", OperatorState.add);
			}
		} catch (Exception e) {

		}
		model.addAttribute(getCommondName(), getVO(null));
		return getForwardPage("edit");
	}
	protected abstract PK addSaveOperator(T vo,BindingResult result)throws Exception;
	protected abstract PK updSaveOperator(T vo)throws Exception;
	
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(
			@RequestParam(value = "selections", required = false) PK[] selections,
			Model model) {
		for (PK pk : selections) {
			try{
				deleteOperator(pk);
			} catch (Exception e) {

			}
		}
		return "true";
	}
	
	protected abstract void deleteOperator(PK pk)throws Exception;
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public Object query(@ModelAttribute DataGridModel model) {
		Map<String, String> parameters = model.getParameters();
		List<QueryParameter> qps = new ArrayList<QueryParameter>();
		String orderName = model.getOrder();
		Order order;
		if (orderName == null || orderName.length() == 0) {
			order = new Order("id", "asc");
		} else {
			order = new Order(model.getSort(), orderName);
		}
		queryOperator(qps,parameters);
		return createDataGrid(query.queryByConditions(new QueryCondition(model
				.getPage(), model.getRows(), order, User.class.getSimpleName(),
				qps)));
	}
	
	protected abstract void queryOperator(List<QueryParameter> qps,Map<String, String> parameters);
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
