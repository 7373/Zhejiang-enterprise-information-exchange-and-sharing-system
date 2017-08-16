/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.ext.service;

import java.util.List;

import com.icinfo.cs.ext.dto.MidAltitemDto;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_mid_altitem 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年10月20日
 */
public interface IMidAltitemService extends BaseService {

	/**
	 * 描述：根据priPID查询变更信息JSON数据列表
	 * @author baifangfang
	 * @date 2016年10月20日
	 * @param request
	 * @return
	 */
	List<MidAltitemDto> queryPageResult(PageRequest request);
}