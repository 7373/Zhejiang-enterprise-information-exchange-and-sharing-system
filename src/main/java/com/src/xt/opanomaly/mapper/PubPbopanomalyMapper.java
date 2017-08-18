/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年10月08日
 * @version 2.0
 */
package com.icinfo.cs.opanomaly.mapper;

import java.util.List;
import java.util.Map;

import com.icinfo.cs.opanomaly.dto.PubPbopanomalyDto;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

/**
 * 描述:    cs_pub_pbopanomaly 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年10月08日
 */
public interface PubPbopanomalyMapper extends Mapper<PubPbopanomaly> {
	/**
	 * 
	 * 描述   个体户经营异常状态查询 分页查询
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return List<PubPbopanomalyDto>
	 * @throws
	 */
	public List<PubPbopanomalyDto> selectPubPbopanomalySearchList(Map<String,Object> queryMap);
	
	/**
	 * 
	 * 描述   获取已注销且还在异常列表内的个体户
	 * @author 赵祥江
	 * @date 2017年1月16日 上午10:53:33 
	 * @param 
	 * @return List<PubPbopanomalyDto>
	 * @throws
	 */
	public List<PubPbopanomalyDto> selectpubPbOpanomalyDXAutoOutList(Map<String,Object> queryMap);
	
	
	/**
	 * 
	 * 描述   个体户经营异常状态查询 统计被标记记录的户数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	public Integer  selectEntCount(Map<String,Object> queryMap);
	
	/**
	 * 
	 * 描述  个体户  统计标记未恢复的条数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	public Integer selectMarkEntCount(Map<String,Object> queryMap);
	
	/**
	 * 
	 * 描述   个体户 统计标记已恢复的条数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	public Integer selectRenewEntCount(Map<String,Object> queryMap);
	
	/**
	 * 
	 * 描述   查询已经年报且在经营异常名录（未按时年报）中的个体户
	 * @author 赵祥江
	 * @date 2016年12月20日 下午3:39:18 
	 * @param 
	 * @return List<PubPbopanomalyDto>
	 * @throws
	 */
	public List<PubPbopanomalyDto> selectPubPbopanomalyIsReported(
			Map<String, Object> searchMap)throws Exception;
	
	

	/**
	 * 
	 * 描述: 查询个转企且存在标记异常名
	 * @auther gaojinling
	 * @date 2017年1月16日 
	 * @param searchMap
	 * @return
	 * @throws Exception
	 */
	public List<PubPbopanomalyDto> selectPubPbopanomalyIsIndividualed(
			Map<String, Object> searchMap)throws Exception;

	/**
	 * 描述：根据pripid查询列入经营异常名录信息
	 * @date 2017年4月19日 
	 * @auther baifangfang
	 * @param priPID
	 * @return
	 */
	public List<PubPbopanomalyDto> selectPubPbopanomalyPriPID(Map<String, Object> map);
	
	/**
	 * 
	 * 描述   个体户未按时年报列入异常
	 * @author 赵祥江
	 * @date 2017年6月29日 上午10:17:51 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void doPubPbopanomalyNoReport(Map<String, Object> map) throws Exception; 
}