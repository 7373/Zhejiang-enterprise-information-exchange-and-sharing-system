/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.rpt.rptservice;

import java.util.List;

import com.icinfo.cs.rpt.rptdto.YrRptDto;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_rpt_yr 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2017年01月18日
 */
public interface IYrRptService extends BaseService {
	
	/**
	 * 
	 * 描述: 年报进度统计
	 * @auther gaojinling
	 * @date 2017年1月18日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<YrRptDto> selectRptYrCount(PageRequest request) throws Exception ;
	
}