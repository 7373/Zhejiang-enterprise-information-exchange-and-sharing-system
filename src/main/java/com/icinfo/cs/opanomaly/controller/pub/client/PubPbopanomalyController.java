/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.opanomaly.controller.pub.client;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.cs.opanomaly.service.IPubPbopanomalyService;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageResponse;


/**
 * 描述:   个体户异常.<br>
 *
 * @author framework generator
 * @date 2016年10月08日
 */
@Controller("ClientPubPbopanomalyController")
@RequestMapping("/pub/client/pubpbopanomaly")
public class PubPbopanomalyController extends BaseController {
	
	@Autowired IPubPbopanomalyService pubPbopanomalyService;
	
	/** 
	 * 描述: 纳入/移出经营异常名录信息（公示单个个体户展示）
	 * @auther ZhouYan
	 * @date 2016年11月4日 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list/list.json",method= RequestMethod.POST)
    @ResponseBody
    public PageResponse<PubPbopanomaly> listJSON(PageRequest request) throws Exception {
		List<PubPbopanomaly> data = pubPbopanomalyService.queryPubPbopanomalyListForPub(request);
        return new PageResponse<PubPbopanomaly>(data);
    }	
}