package com.smhdemo.common.report.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smhdemo.common.query.DataGridModel;
import com.smhdemo.common.query.jpa.QueryParameter;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;
import com.smhdemo.common.report.ReportFac;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.web.CrudBaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/report/text")
public class TextController extends CrudBaseController<Text,Long>{
	@Autowired
	private ReportFac reportFac;
	@Override
	protected Text getVO(Long pk, Model model) {
		if(pk != null){
			try {
				return reportFac.getText(pk);
			} catch (Exception e) {
			}			
		}
		return new Text();
	}

	@Override
	protected String getCommondName() {
		return "text";
	}
	
	/**
	 * 
	 * 新增或修改用户.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/savetext")
	public String save(
			@RequestParam(value = "addrecordlist") String addrecordlist,
			@RequestParam(value = "eventOP") OperatorState eventOP,
			@RequestParam("reportFile") MultipartFile file,
			@Valid @ModelAttribute Text vo, BindingResult result, Model model) {
		model.addAttribute("eventOP",eventOP);
		model.addAttribute("addrecordlist", addrecordlist);
        if(result.hasErrors()){  
        	return getForwardPage("edit");
        } 
		try {
			if (file != null) {
				byte[] buffer = file.getBytes();
				vo.setTextEntity(buffer);
			}
			
			if (eventOP.equals(OperatorState.upd)) {
				updSaveOperator(vo, result,model);
			} else if (eventOP.equals(OperatorState.add)) {
				
				Long id = addSaveOperator(vo,result,model);
				if (addrecordlist.length() == 0) {
					model.addAttribute("addrecordlist", addrecordlist+ id);
				} else { 
					model.addAttribute("addrecordlist", addrecordlist + ","	+ id);
				}

				model.addAttribute("eventOP", OperatorState.add);
			}
		} catch (Exception e) {

		}
		model.addAttribute(getCommondName(), getVO(null,model));
		return getForwardPage("edit");
	}

	@Override
	protected Long addSaveOperator(Text vo, BindingResult result, Model model)
			throws Exception {
		return reportFac.addText(vo);
	}

	@Override
	protected Long updSaveOperator(Text vo, BindingResult result, Model model) throws Exception {
		return reportFac.updText(vo);
	}

	@Override
	protected void deleteOperator(Long pk) throws Exception {
		reportFac.delText(pk);
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
		return Text.class.getSimpleName();
	}

	@Override
	protected String getPagePath() {
		return "common/report/text";
	}
	/**
	 * 
	 * 下载报表文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void download(@RequestParam(value = "textId") long textId,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter pw = null;
		InputStream in = null;
		try {
				Text report = reportFac.getText(textId);
				if (report.getTextEntity() != null
						&& report.getTextEntity().length != 0) {
					String fileName = String.valueOf(report.getName());
					fileName = URLEncoder.encode(fileName, "UTF-8");
					response.setContentType("application/jrxml;charset=UTF-8");
					response.setHeader("Content-disposition",
							"attachment; filename=" + fileName + ".jrxml");

					byte[] bytes = new byte[report.getTextEntity().length];
					bytes = report.getTextEntity();
					pw = response.getWriter();
					response.setContentLength(bytes.length);
					in = new ByteArrayInputStream(bytes);
					int len = 0;
					while ((len = in.read()) > -1) {
						pw.write(len);
					}
					pw.flush();
				}
		} catch (IOException e) {
		} finally {
			if (pw != null) {
				try {
					pw.close();
					pw = null;
				} catch (Exception e) {
				}
			}
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (Exception e) {
				}
			}
		}
	}
	
	@RequestMapping(value = "/parameter", method = RequestMethod.GET)
	public String permissionAllocate(@RequestParam(value = "textId", required = true) long textId,Model model) {
		model.addAttribute("parameterList", reportFac.getText(textId).getParameters());
		model.addAttribute("typeEnum", Parameter.Type.values());
		model.addAttribute("textId", textId);
		return getForwardPage("parameter");
	}
	
	@RequestMapping(value = "/parameter", method = RequestMethod.POST)
	public String permissionAllocate(@RequestParam(value = "textId", required = true) long textId,@ModelAttribute ParameterList model) {
		try{
			reportFac.updTextParameter(textId, model.getParameterList());
		}catch(Exception e){}
		return getForwardPage("parameter");
	}
}

