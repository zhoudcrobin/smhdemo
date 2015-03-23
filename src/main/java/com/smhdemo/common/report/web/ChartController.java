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
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.util.ChartUtil;
import com.smhdemo.web.CrudBaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/report/chart")
public class ChartController extends CrudBaseController<Chart,Long>{
	@Autowired
	private ReportFac reportFac;
	@Override
	protected Chart getVO(Long pk, Model model) {
		model.addAttribute("type", Chart.Type.values());
		model.addAttribute("fontNameMap", ChartUtil.getFontNameMap());
		model.addAttribute("fontStyleMap", ChartUtil.getFontStyleMap());
		model.addAttribute("fontSizeMap", ChartUtil.getFontSizeMap());
		model.addAttribute("rotateMap", ChartUtil.getRotateMap());
		model.addAttribute("positionMap", ChartUtil.getPositionMap());
		model.addAttribute("alignmentMap", ChartUtil.getAlignmentMap());
		if(pk != null){
			try {
				return reportFac.getChart(pk);
			} catch (Exception e) {
			}			
		}
		return new Chart();

		
	}

	@Override
	protected String getCommondName() {
		return "chart";
	}

	@Override
	protected Long addSaveOperator(Chart vo, BindingResult result, Model model)
			throws Exception {
		return reportFac.addChart(vo);
	}

	@Override
	protected Long updSaveOperator(Chart vo, BindingResult result, Model model)
			throws Exception {
		return reportFac.updChart(vo);
	}

	@Override
	protected void deleteOperator(Long pk) throws Exception {
		reportFac.delChart(pk);
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

		String reportName = parameters.get("reportName");
		if (reportName != null && reportName.length() > 0) {
			QueryParameter qp2 = new QueryParameter("name", reportName,QueryOperateType.CharIn);
			qps.add(qp2);
		}
		String reportId = parameters.get("reportId");
		if (reportId != null&& reportName.length() > 0) {
			QueryParameter qp3 = new QueryParameter("id", Long.parseLong(reportId),QueryOperateType.Equal);
			qps.add(qp3);
		}
		return Chart.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/report/chart";
	}

}

