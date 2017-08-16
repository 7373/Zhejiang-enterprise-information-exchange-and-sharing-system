package com.icinfo.cs.quartz.controller.reg.server;

import com.icinfo.cs.quartz.service.IRegistInfoQuartzJobService;
import com.icinfo.framework.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * Copyright 2003-2013 浙江汇信科技有限公司, All Rights Reserved <br/>
 * 描述: TODO
 * @author: 刘海强
 * @date: 2016年11月07日 上午10:08:55
 * @version: V1.0  
 */
@Controller
@RequestMapping("/quartz/registInfoPush")
public class RegistInfoQuartzJobController extends BaseController{
	@Autowired
	private IRegistInfoQuartzJobService registInfoQuartzJobService;
	


	/**
	 * 
	 * 描述:主体户口定时任务：查找不在建档表里的MID数据,插入建档表，有管辖单位的
	 * 随机分配选择管辖人员。
	 * @auther gaojinling
	 * @date 2017年4月25日 
	 * @throws Exception
	 */
	public void registInfoSyn() throws Exception{
		registInfoQuartzJobService.registInfoSyn();
	}

}
