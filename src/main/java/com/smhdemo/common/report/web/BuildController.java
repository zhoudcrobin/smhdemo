package com.smhdemo.common.report.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhdemo.common.report.ReportFac;
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.entity.Text.Type;
import com.smhdemo.common.report.generate.factory.ChartFactoryable;
import com.smhdemo.common.report.generate.factory.TextFactoryable;
import com.smhdemo.common.report.generate.util.ParamConversionPage;
import com.smhdemo.web.BaseController;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/report/build")
public class BuildController extends BaseController{
	@Autowired
	private ReportFac reportFac;
	@Autowired
	private TextFactoryable textFactory;
	@Autowired
	private ChartFactoryable chartFactory;	
	@Override
	protected String getPagePath() {
		return "common/report/build";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("texts", reportFac.findAllText());
		model.addAttribute("charts", reportFac.findAllChart());
		return getForwardPage("index");
	}	
	
	@RequestMapping(value = "/make", method = RequestMethod.GET)
	public String make(@RequestParam(value = "reportId", required = true) long reportId,
			@RequestParam(value = "reportType", required = true) String reportType,
			Model model) {
		model.addAttribute("reportId",reportId);
		model.addAttribute("reportType", reportType);
		if (reportType.equals("text")) {
			model.addAttribute("parameters", ParamConversionPage.conversion(reportFac.getText(reportId).getParameters()));

		}

		if (reportType.equals("chart")) {
			model.addAttribute("parameters", ParamConversionPage.conversion(reportFac.getChart(reportId).getParameters()));
		}	
		model.addAttribute("typeEnum", Text.Type.values());
		return getForwardPage("parameterset");
	}
	
	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public void make(@RequestParam(value = "reportId", required = true) long reportId,
			@RequestParam(value = "reportType", required = true) String reportType,
			@ModelAttribute ParameterMap model,HttpServletRequest request, HttpServletResponse response) {

		if (reportType.equals("text")) {
			
			buildText(reportId,request,response,model.getParaMap(),model.getTextType());
		}

		if (reportType.equals("chart")) {
			buildChart(reportId,response,model.getParaMap());
		}	

	}
	
	private void buildText(Long reportId,HttpServletRequest request,HttpServletResponse response,Map<String, String> paraMap,Type textType) {
		PrintWriter pw = null;
		InputStream in = null;
		try {
			Text report = reportFac.getText(reportId);
			pw = response.getWriter();
			response.reset();// 清空输出
			response.setContentLength(0);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			byte[] bytes = textFactory.export(paraMap, report, textType, response, request);
			in = new ByteArrayInputStream(bytes);
			int len = 0;
			while ((len = in.read()) > -1) {
				pw.write(len);
			}
			pw.flush();
		} catch (Exception e) {
			// log.error(e.toString());
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

	private void buildChart(Long reportId,HttpServletResponse response,Map<String, String> paraMap) {
		PrintWriter pw = null;
		InputStream in = null;
		try {
			Chart chart = reportFac.getChart(reportId);
			pw = response.getWriter();

			response.reset();// 清空输出
			response.setContentLength(0);
			byte[] bytes = chartFactory.export(chart, paraMap);
			response.setContentLength(bytes.length);
			response.setHeader("Content-Type", "image/png");
			in = new ByteArrayInputStream(bytes);
			int len = 0;
			while ((len = in.read()) > -1) {
				pw.write(len);
			}
			pw.flush();
		} catch (Exception e) {
			// log.error(e.toString());
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
}

