/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icinfo.cs.sccheck.dto.PubScentDto;
import com.icinfo.cs.sccheck.service.IPubScentService;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageResponse;

/**
 * 描述:    cs_pub_scent 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
@Controller
@RequestMapping({"/reg/server/sccheck/pubscent","/syn/server/sccheck/pubscent"})
public class PubScentController extends BaseController {
	
	@Autowired
	private IPubScentService pubScentService;
	
	
	/**
	 * 
	 * 描述: 通过部门任务id分页查询已抽取的企业
	 * @auther gaojinling
	 * @date 2017年5月20日 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.json",method= RequestMethod.GET)
    @ResponseBody
    public PageResponse<PubScentDto> listJSON(PageRequest request,HttpSession session) throws Exception {
		List<PubScentDto> data = null;
		data = pubScentService.selectPubScentDtoList(request);
        return new PageResponse<PubScentDto>(data);
    }
}