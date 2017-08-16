/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.opanomaly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.ext.mapper.MidBaseInfoMapper;
import com.icinfo.cs.ext.model.MidBaseInfo;
import com.icinfo.cs.opanomaly.dto.PubPbopanomalyDto;
import com.icinfo.cs.opanomaly.mapper.PubPbopanomalyMapper;
import com.icinfo.cs.opanomaly.model.PubOpanoMaly;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.cs.opanomaly.service.IPubPbopanomalyService;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.tools.utils.DateUtils;


/**
 * 描述:    cs_pub_pbopanomaly 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年10月08日
 */
@Service
public class PubPbopanomalyServiceImpl extends MyBatisServiceSupport implements IPubPbopanomalyService {
	
	private static final Logger logger = LoggerFactory.getLogger(PubPbopanomalyServiceImpl.class);

	@Autowired
	private PubPbopanomalyMapper pubPbopanomalyMapper; 

	@Autowired
	MidBaseInfoMapper midBaseInfoMapper;
	
	
	/** 
	 * 描述: 列表加载
	 * @author ZhouJun
	 * @date 2016年10月12日 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<MidBaseInfo> selectmidBaseInfolist(PageRequest request) throws Exception{
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Map<String, Object> maps =request.getParams();
		List<MidBaseInfo> ls =midBaseInfoMapper.selectPbMidBaseinfoByManyConditions(maps);
		return ls;
	}

	/** 
	 * 描述: 根据注册号查找MidBaseInfo
	 * @author ZhouJun
	 * @date 2016年10月12日 
	 * @param regNO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public MidBaseInfo selectByRegNO(String regNO) throws Exception {
		Map<String, Object> maps =new HashMap<String, Object>();
		maps.put("regNO", regNO);
		return midBaseInfoMapper.selectPbMidBaseinfoByManyConditions(maps).get(0);
	}
	
	
	
	/** 
	 * 描述: 新增异常
	 * @author ZhouJun
	 * @date 2016年10月12日 
	 * @param pbopanomaly
	 * @return
	 * @throws Exception 
	 */
	public int addSign(PubPbopanomaly pbopanomaly) throws Exception{
		if(pbopanomaly!=null){
			pbopanomaly.setCogDate(new Date());
			pbopanomaly.setCreateTime(new Date());
			return pubPbopanomalyMapper.insert(pbopanomaly);
		}else{
			return 0;
		}
		
	}

	
	
	

	/**
	 * 
	 * 描述   经营状态查询 分页查询
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:16:39 
	 * @param 
	 * @return List<PubPbopanomalyDto>
	 * @throws
	 */

	@Override
	public List<PubPbopanomalyDto> queryPubPbopanomalySearchList(
			PageRequest request) throws Exception {
		try {
			Map<String, Object> searchMap=  request.getParams();
			PageHelper.startPage(request.getPageNum(), request.getLength());
			return pubPbopanomalyMapper.selectPubPbopanomalySearchList(searchMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "经营状态查询失败!");
            throw new BusinessException("经营状态查询失败!");
		}
	}

	/**
	 * 
	 * 描述   个体户经营异常状态查询 统计被标记记录的户数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	@Override
	public Integer selectEntCount(Map<String, Object> queryMap) {
		try {
			Integer entCount=pubPbopanomalyMapper.selectEntCount(queryMap); 
			return entCount==null?0:entCount;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "个体户经营异常状态查询 统计被标记记录的户数失败!");
            throw new BusinessException("个体户经营异常状态查询 统计被标记记录的户数失败!");
		} 
	}

	/**
	 * 
	 * 描述  个体户  统计标记未恢复的条数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	@Override
	public Integer selectMarkEntCount(Map<String, Object> queryMap) {
		try {
			Integer markEntCount= pubPbopanomalyMapper.selectMarkEntCount(queryMap);
			return markEntCount==null?0:markEntCount;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "统计标记未恢复的条数失败!");
            throw new BusinessException("统计标记未恢复的条数失败!");
		}  
	}

	/**
	 * 
	 * 描述   个体户 统计标记已恢复的条数
	 * @author 赵祥江
	 * @date 2016年10月9日 下午2:20:38 
	 * @param 
	 * @return Integer
	 * @throws
	 */
	@Override
	public Integer selectRenewEntCount(Map<String, Object> queryMap) {
		try {
			Integer renewEntCount= pubPbopanomalyMapper.selectRenewEntCount(queryMap);
			return renewEntCount==null?0:renewEntCount;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "个体户 统计标记已恢复的条数失败!");
            throw new BusinessException("个体户 统计标记已恢复的条数失败!");
		}  
	}

	/** 
	 * 描述: 根据maps搜索个体户异常信息
	 * @author ZhouJun
	 * @date 2016年10月10日 
	 * @param maps
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<PubPbopanomalyDto> PubPbopanomalySearchList(Map<String, Object> maps) throws Exception {
	 return pubPbopanomalyMapper.selectPubPbopanomalySearchList(maps);
	}
	
	/** 
	 * 描述: 异常恢复
	 * @author ZhouJun
	 * @date 2016年10月13日 
	 * @param pbopanomaly
	 * @return
	 * @throws Exception 
	 */
	public int pubPbopanomalyRecover(PubPbopanomaly pbopanomaly) throws Exception{
		Example ex = new Example(PubPbopanomaly.class);
		if(pbopanomaly != null && StringUtil.isNotEmpty(pbopanomaly.getBusExcList())&& StringUtil.isNotEmpty(pbopanomaly.getPriPID())){
			try {
				pbopanomaly.setNorDate(new Date());
				pbopanomaly.setCreateTime(new Date());
				pbopanomaly.setIsRecovery("0");
				ex.createCriteria().andEqualTo("busExcList", pbopanomaly.getBusExcList()).andEqualTo("priPID", pbopanomaly.getPriPID());
				return pubPbopanomalyMapper.updateByExampleSelective(pbopanomaly, ex);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		return 0;
	}

	/** 
	 * 描述: 查找可以恢复标记的异常列表
	 * @author ZhouJun
	 * @date 2016年10月12日 
	 * @param request
	 * @return 
	 */
	@Override
	public List<PubPbopanomalyDto> queryPubPbopanomalySearchRecoverList(
			PageRequest request) {
		try {
			Map<String, Object> searchMap=  new HashMap<>();
			if(request.getParams()!=null){
				searchMap.putAll(request.getParams());
			}
			searchMap.put("isRecovery",1);
			searchMap.put("handFlag",1);  //手动列入标志
			PageHelper.startPage(request.getPageNum(), request.getLength());
			return pubPbopanomalyMapper.selectPubPbopanomalySearchList(searchMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "经营状态查询失败!");
            throw new BusinessException("经营状态查询失败!");
		}
	}
	
	
	/** 
	 * 描述: 查找可以恢复标记的异常列表(不分页 ，更新索引用)
	 * @author ZhouJun
	 * @date 2016年10月12日 
	 * @param request
	 * @return 
	 */
	public List<PubPbopanomalyDto> pubPbopanomalySearchRecoverList(Map<String, Object> searchMap) {
		try {
			searchMap.put("isRecovery",1);
			return pubPbopanomalyMapper.selectPubPbopanomalySearchList(searchMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "经营状态查询失败!");
            throw new BusinessException("经营状态查询失败!");
		}
	}
	
	
	

	/** 
	 * 描述: 纳入/移出经营异常名录信息（公示单个个体户展示）
	 * @auther ZhouYan
	 * @date 2016年11月4日 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<PubPbopanomaly> queryPubPbopanomalyListForPub(
			PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		String priPID = request.getParams().get("priPID").toString();
		if(StringUtils.isNotBlank(priPID)) {
			Example example = new Example(PubPbopanomaly.class);
			example.createCriteria().andEqualTo(Constants.CS_PRIPID, priPID);
			return pubPbopanomalyMapper.selectByExample(example);
		}else{
			return new ArrayList<PubPbopanomaly>();
		}
	}

	/**
	 * 
	 * 描述   查询已经年报且在经营异常名录（未按时年报）中的个体户
	 * @author 赵祥江
	 * @date 2016年12月20日 下午3:39:18 
	 * @param 
	 * @return List<PubPbopanomalyDto>
	 * @throws
	 */
	@Override
	public List<PubPbopanomalyDto> selectPubPbopanomalyIsReported(
			Map<String, Object> searchMap)throws Exception { 
		return pubPbopanomalyMapper.selectPubPbopanomalyIsReported(searchMap);
	}

	/**
	 * 
	 * 描述   根据主体代码和年度查询
	 * @author 赵祥江
	 * @date 2016年12月20日 下午6:24:15 
	 * @param 
	 * @return List<PubPbopanomaly>
	 * @throws
	 */
	@Override
	public List<PubPbopanomaly> selectPubPbopanomalyByYearAndPriPID(
			Integer year, String priPID) throws Exception {
		if(year!=null&&com.icinfo.framework.mybatis.mapper.util.StringUtil.isNotEmpty(priPID)){
			PubPbopanomaly pubPbopanomaly=new PubPbopanomaly();
			pubPbopanomaly.setPriPID(priPID);
			pubPbopanomaly.setYear(year);
			return pubPbopanomalyMapper.select(pubPbopanomaly);
		} 
		return null;
	}

	/**
	 * 
	 * 描述   根据主键busExcList修改异常信息
	 * @author  赵祥江
	 * @date 2016年12月20日 下午6:34:22 
	 * @param  
	 * @throws
	 */
	@Override
	public int updatePubPbopanomalyByBusExcList(PubPbopanomaly pubPbopanomaly)
			throws Exception {
		try {
			if(pubPbopanomaly!=null&&StringUtil.isNotEmpty(pubPbopanomaly.getBusExcList())&&StringUtil.isNotEmpty(pubPbopanomaly.getPriPID())){
				//时间戳
				pubPbopanomaly.setCreateTime(DateUtils.getSysDate());
				Example example = new Example(PubPbopanomaly.class);
				example.createCriteria()
				.andEqualTo("busExcList", pubPbopanomaly.getBusExcList())
				.andEqualTo("priPID", pubPbopanomaly.getPriPID());
			    return pubPbopanomalyMapper.updateByExampleSelective(pubPbopanomaly, example);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception {}", "根据主键busExcList修改异常信息失败!");
            throw new BusinessException("根据主键busExcList修改异常信息失败!");
		} 
		return 0;
	}

	/**
	 * 
	 * 描述   获取已经注销且还在异常列表的个体户
	 * @author  赵祥江
	 * @date 2017年1月16日 上午10:49:38 
	 * @param  
	 * @throws
	 */
	@Override
	public List<PubPbopanomalyDto> selectpubPbOpanomalyDXAutoOutList(Map<String,Object> queryMap) throws Exception {
		 
		return pubPbopanomalyMapper.selectpubPbOpanomalyDXAutoOutList( queryMap);
	} 
	
	

	/**
	 * 
	 * 描述: 查询个转企且存在异常标记的企业记录
	 * @auther gaojinling
	 * @date 2017年1月16日 
	 * @param searchMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubPbopanomalyDto> selectPubPbopanomalyIsIndividualed(
			Map<String, Object> searchMap)throws Exception { 
		return pubPbopanomalyMapper.selectPubPbopanomalyIsIndividualed(searchMap);
	}

	@Override
	public List<PubPbopanomalyDto> selectPubPbopanomalyPriPID(String priPID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("priPID", priPID);
		return pubPbopanomalyMapper.selectPubPbopanomalyPriPID(map);
	}

	/**
	 * 
	 * 描述   未按时年报列入异常
	 * @author  赵祥江
	 * @date 2017年6月29日 上午10:16:03 
	 * @param  
	 * @throws
	 */
	@Override
	public void doPubPbopanomalyNoReport(Map<String, Object> searchMap)
			throws Exception {
		 
		pubPbopanomalyMapper.doPubPbopanomalyNoReport(searchMap);
	}
}
