/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2017年01月18日
 * @version 2.0
 */
package com.icinfo.cs.rpt.rptmapper;

import java.util.List;
import java.util.Map;

import com.icinfo.cs.rpt.rptdto.YrRptDto;
import com.icinfo.cs.rpt.rptmodel.YrRpt;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

/**
 * 描述:    cs_rpt_yr 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2017年01月18日
 */
public interface YrRptMapper extends Mapper<YrRpt> {
	
	/**
	 * 
	 * 描述: 年报进度统计
	 * @auther gaojinling
	 * @date 2017年1月18日 
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	public List<YrRptDto> selectRptYrCount(Map<String,Object> queryMap)throws Exception;

}