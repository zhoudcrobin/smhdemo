package com.smhdemo.common.report.web;

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
import com.smhdemo.common.report.ReportFac;
import com.smhdemo.common.report.entity.Category;
import com.smhdemo.web.CrudBaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/report/category")
public class CategoryController extends CrudBaseController<Category,Long>{
	@Autowired
	private ReportFac reportFac;
	@Override
	protected Category getVO(Long pk, Model model) {
		if(pk != null){
			try {
				return reportFac.getCategory(pk);
			} catch (Exception e) {
			}			
		}
		return new Category();
	}

	@Override
	protected String getCommondName() {
		return "category";
	}

	@Override
	protected Long addSaveOperator(Category vo, BindingResult result,
			Model model) throws Exception {
		return reportFac.addCategory(vo);
	}

	@Override
	protected Long updSaveOperator(Category vo, BindingResult result, Model model) throws Exception {
		return reportFac.updCategory(vo);
	}

	@Override
	protected void deleteOperator(Long pk) throws Exception {
		reportFac.delCategory(pk);		
	}

	@Override
	protected String queryOperator(List<QueryParameter> qps,
			Map<String, String> parameters) {
		String selections = parameters.get("selections");
		if (selections != null && selections.length() > 0) {
			List<Long> pks = new ArrayList<Long>();
			List<String> sls = Arrays.asList(selections.split(","));
			for(String pk :sls){
				pks.add(Long.parseLong(pk));
			}
			QueryParameter qp1 = new QueryParameter("id", pks,QueryOperateType.In);
			qps.add(qp1);
		}

		String categoryName = parameters.get("categoryName");
		if (categoryName != null && categoryName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("name", categoryName,QueryOperateType.CharIn);
			qps.add(qp2);
		}
		return Category.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/report/category";
	}

}

