package com.smhdemo.common.datasource.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smhdemo.common.datasource.DataSourceFac;
import com.smhdemo.common.datasource.entity.Bean;
import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.web.CrudBaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/datasource/bean")
public class BeanController extends CrudBaseController<Bean,Long>{
	@Autowired
	private DataSourceFac dataSourceFac;
	@Override
	protected Bean getVO(Long pk, Model model) {
		if(pk != null){
			try {
				return (Bean)dataSourceFac.getBase(pk);
			} catch (Exception e) {
			}			
		}
		return new Bean();
	}

	@Override
	protected String getCommondName() {
		return "bean";
	}

	@Override
	protected Long addSaveOperator(Bean vo, BindingResult result, Model model)
			throws Exception {
		return dataSourceFac.addBase(vo);
	}

	@Override
	protected Long updSaveOperator(Bean vo, BindingResult result, Model model)
			throws Exception {
		return dataSourceFac.updBase(vo);
	}

	@Override
	protected void deleteOperator(Long pk) throws Exception {
		dataSourceFac.deletedBase(pk);		
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

		String name = parameters.get("name");
		if (name != null && name.length() > 0) {
			QueryParameter qp2 = new QueryParameter("name", name,QueryOperateType.CharIn);
			qps.add(qp2);
		}
		return Bean.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/datasource/bean";
	}

}

