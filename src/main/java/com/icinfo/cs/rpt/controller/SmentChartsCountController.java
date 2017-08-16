package com.icinfo.cs.rpt.controller;

import java.util.ArrayList;
import java.util.List;

import com.icinfo.cs.rpt.rptmodel.SmCountAreaDist;
import com.icinfo.cs.rpt.rptmodel.SmCountMonthRoute;
import com.icinfo.cs.rpt.rptservice.ISmCountAreaDistService;
import com.icinfo.cs.rpt.rptservice.ISmCountMonthRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icinfo.framework.common.ajax.AjaxResult;


/**
 * 图表统计
 * 
 * @author zhuyong
 */
@Controller
@RequestMapping("/sment/server/chartscount" )
public class SmentChartsCountController {

	@Autowired
	private ISmCountAreaDistService smCountAreaDistService;
	@Autowired
	private ISmCountMonthRouteService smCountMonthRouteService;

	@RequestMapping("/mapList.json")
	@ResponseBody
	public AjaxResult mapList() throws Exception{
		JSONArray arr = smCountAreaDistService.selectAll();
		return AjaxResult.success("dataList", arr);
	}
	
	@RequestMapping("/zcList.json")
	@ResponseBody
	public AjaxResult zcList() throws Exception{
		JSONObject obj = smCountMonthRouteService.selectAllByZC();
		return AjaxResult.success("dataList", obj);
	}
	
	
	@RequestMapping("/xsList.json")
	@ResponseBody
	public AjaxResult xsList() throws Exception{
		JSONObject xsList = smCountMonthRouteService.selectAllByXS();
		JSONObject zxList = smCountMonthRouteService.selectAllByZX();
		JSONArray arr = new JSONArray();
		arr.add(xsList); arr.add(zxList);
		return AjaxResult.success("dataList", arr);
	}
	
	
	
}
