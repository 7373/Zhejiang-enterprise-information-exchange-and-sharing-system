package com.icinfo.cs.quartz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.icinfo.cs.base.constant.QuartzJobName;
import com.icinfo.cs.base.model.CodeRegorg;
import com.icinfo.cs.base.service.ICodeRegorgService;
import com.icinfo.cs.base.service.ICsQuartzJobService;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.common.utils.SleepUtil;
import com.icinfo.cs.es.service.IEntSearchService;
import com.icinfo.cs.es.service.IPanoramaSearchService;
import com.icinfo.cs.ext.dto.MidBaseInfoDto;
import com.icinfo.cs.ext.service.IMidBaseInfoService;
import com.icinfo.cs.opanomaly.dto.PubPbopanomalyDto;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.cs.opanomaly.service.IPubPbopanomalyService;
import com.icinfo.cs.quartz.service.IPubPbopanomalyQuartzJobService;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.util.StringUtil;

/**  
 * Copyright 2003-2013 浙江汇信科技有限公司, All Rights Reserved <br/>
 * 描述: 个体户经营异常定时
 * @author: 赵祥江 
 * @date: 2016年12月20日 下午3:30:33
 * @version: V1.0  
 */
public class PubPbopanomalyQuartzJobServiceImpl extends MyBatisServiceSupport implements IPubPbopanomalyQuartzJobService{
	private static final Logger logger = LoggerFactory.getLogger(PubPbopanomalyQuartzJobServiceImpl.class);
	@Autowired
	private  IPubPbopanomalyService  pubPbopanomalyService;
	@Autowired
	private ICsQuartzJobService csQuartzJobService;
	@Autowired
	private IPanoramaSearchService panoramaSearchService;
	@Autowired
	private IEntSearchService entSearchService;
	@Autowired
	private ICodeRegorgService codeRegorgService;
	@Autowired
	private IMidBaseInfoService midBaseInfoService;
	/**
	 * 
	 * 描述   个体户 已年报后 定时移出经营异常
	 * @author 赵祥江
	 * @date 2016年12月20日 下午3:34:08 
	 * @param 
	 * @return void
	 * @throws
	 */
	@Override
	public String  pubPbopanomalyOutQuartzJob() throws Exception {
		logger.info("个体户 已年报 定时移出经营异常任务 开始");
		//阻塞任务，防止并发访问数据库
		SleepUtil.threadSleep();
 		if(csQuartzJobService.checkJobIsExecute(QuartzJobName.PBOPANOMALY_JOB.getJobName())){
			return "任务已执行";
		}
		csQuartzJobService.insert(QuartzJobName.PBOPANOMALY_JOB.getJobName());
		Map<String,Object> searchMap=new HashMap<String,Object>();
		//获取已补报且还在经营异常（未按时年报）的个体户
		List<PubPbopanomalyDto> pubPbopanomalyDtoList= pubPbopanomalyService.selectPubPbopanomalyIsReported(searchMap);
		if(pubPbopanomalyDtoList!=null&&pubPbopanomalyDtoList.size()>0){
			for(PubPbopanomalyDto pubPbopanomalyDto:pubPbopanomalyDtoList){ 
				try { 
					//主体身份代码
					String priPID=pubPbopanomalyDto.getPriPID();
					//年报年度
  					Integer year=pubPbopanomalyDto.getYear();
					List<PubPbopanomaly> pubPbopanomalyList=pubPbopanomalyService.selectPubPbopanomalyByYearAndPriPID(year, priPID);
					if(pubPbopanomalyList!=null&&pubPbopanomalyList.size()>0){
						searchMap.clear();
						PubPbopanomaly pubPbopanomaly=new PubPbopanomaly();
						String busExcList=pubPbopanomalyList.get(0).getBusExcList();
						pubPbopanomaly.setPriPID(priPID);
						pubPbopanomaly.setBusExcList(busExcList);
						//注册号
						pubPbopanomaly.setRegNO(pubPbopanomalyList.get(0).getRegNO());
						//系统录入上报情况 1-已补报，0-未上报
						pubPbopanomaly.setPBState("1");
						//恢复标记 1已标记 0 已恢复
						pubPbopanomaly.setIsRecovery("0");
						//恢复异常原因编码
						pubPbopanomaly.setNorRea("3");
						//恢复正常的原因中文名称
						pubPbopanomaly.setNorReaCN("已报送最新年度年报,系统自动恢复正常");
						//恢复日期
						pubPbopanomaly.setNorDate(DateUtil.getSysDate());
						//恢复事实和理由
						pubPbopanomaly.setRecoverRea("已补报");
						//恢复设置人
						pubPbopanomaly.setResetName("系统自动");
 						MidBaseInfoDto midBaseInfoDto=	midBaseInfoService.selectMidBaseInfoByPripid(priPID);
 						//决定机关编码
						String decOrg=midBaseInfoDto==null?"000":midBaseInfoDto.getRegOrg(); 
						CodeRegorg codeRegorg=codeRegorgService.selectRegOrgByCode(decOrg);
						//恢复决定机关中文名称
						pubPbopanomaly.setNorDecOrgCN(codeRegorg==null?"":codeRegorg.getContentShort());
						//恢复决定机关编码
						pubPbopanomaly.setNorDecOrg(codeRegorg==null?"":decOrg); 
						pubPbopanomalyService.updatePubPbopanomalyByBusExcList(pubPbopanomaly);
						searchMap.put("priPID", priPID);
						//检索企业还有没有在列异常 如果没有更新索引打上移出标识
						List<PubPbopanomalyDto>  pubPbomalyList= pubPbopanomalyService.pubPbopanomalySearchRecoverList(searchMap);
						if(pubPbomalyList==null||pubPbomalyList.size()==0){
							if(searchMap.containsKey("isRecovery")){
								searchMap.remove("isRecovery");
							}
							searchMap.put("isOpan", "N"); 
							//搜索列表索引
							panoramaSearchService.updatePanoramaIdx(searchMap);
							searchMap.remove("isOpan");
 							//更新公示索引
							searchMap.put("isOpanomaly", "0");
							entSearchService.updatePubIndex(searchMap);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		logger.info("个体户 已年报 定时移出经营异常任务 结束");
		return ""; 
	} 
}
