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
import com.icinfo.cs.opanomaly.dto.PubOpaDetailDto;
import com.icinfo.cs.opanomaly.dto.PubPbopanomalyDto;
import com.icinfo.cs.opanomaly.model.PubOpaDetail;
import com.icinfo.cs.opanomaly.model.PubOpanoMaly;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.cs.opanomaly.service.IPubOpaDetailService;
import com.icinfo.cs.opanomaly.service.IPubOpanoMalyService;
import com.icinfo.cs.opanomaly.service.IPubPbopanomalyService;
import com.icinfo.cs.quartz.service.IPubOpanomalyDXAutoOutQuartzJobService;
import com.icinfo.cs.yr.model.DepartMent;
import com.icinfo.cs.yr.service.IDepartMentService;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.tools.utils.DateUtils;

/**
 * 
 * 描述:注销自动移出异常  
 * @author: 赵祥江
 * @date: 2017年1月16日 上午10:10:47
 */
public class PubOpanomalyDXAutoOutQuartzJobServiceImpl extends MyBatisServiceSupport implements IPubOpanomalyDXAutoOutQuartzJobService{
	private static final Logger logger = LoggerFactory.getLogger(PubOpanomalyDXAutoOutQuartzJobServiceImpl.class);
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
	@Autowired
	private IPubOpanoMalyService pubOpanoMalyService;
	@Autowired
	private IDepartMentService departMentService;
	@Autowired
	private IPubOpaDetailService pubOpaDetailService;
	/**
	 * 
	 * 描述   企业个体户注销自动移出异常
	 * @author 赵祥江
	 * @date 2016年12月20日 下午3:34:08 
	 * @param 
	 * @return void
	 * @throws
	 */
	@Override
	public String  pubOpanomalyDXAutoOutQuartzJob() throws Exception {
 		logger.info("企业/农专、个体户注销移出异常定时任务开始");
 		//阻塞任务，防止并发访问数据库
 		SleepUtil.threadSleep();
 		if(csQuartzJobService.checkJobIsExecute(QuartzJobName.PUBOPANOMALYDXAUTOOUT_JOB.getJobName())){
 			return "任务已执行";
 		}
 	 	csQuartzJobService.insert(QuartzJobName.PUBOPANOMALYDXAUTOOUT_JOB.getJobName());
 		Map<String,Object> searchMap=new HashMap<String,Object>(); 
		List<PubPbopanomalyDto> pubPbopanomalyDtoList= pubPbopanomalyService.selectpubPbOpanomalyDXAutoOutList(searchMap);
		if(pubPbopanomalyDtoList!=null&&pubPbopanomalyDtoList.size()>0){
			for(PubPbopanomalyDto pubPbopanomalyDto:pubPbopanomalyDtoList){
				MidBaseInfoDto  midBaseInfoDtoTem=null;
				try {
					String priPID=pubPbopanomalyDto.getPriPID();
					searchMap.clear();
					searchMap.put("priPID", priPID);
					String regNo=pubPbopanomalyDto.getRegNO();
					String busExcList= pubPbopanomalyDto.getBusExcList()==null||"".equals(pubPbopanomalyDto.getBusExcList())?"0000":pubPbopanomalyDto.getBusExcList();
					String regOrg=pubPbopanomalyDto.getRegOrg()==null||"".equals(pubPbopanomalyDto.getRegOrg())?"0000":pubPbopanomalyDto.getRegOrg();
					//企业类型
					String entTypeCatg= pubPbopanomalyDto.getCerType();
					 
					Map<String,Object> qMap=new HashMap<String,Object>();
					qMap.put("regState", "0");
					qMap.put("PriPID",priPID); 
					List<MidBaseInfoDto>  midBaseInfoDtoList=midBaseInfoService.selectMidBaseinfoByRegNoAndName(qMap);
					if(midBaseInfoDtoList!=null&&midBaseInfoDtoList.size()>0){
					    midBaseInfoDtoTem= midBaseInfoDtoList.get(0);
					} 
					//个体户
					if("50".equals(entTypeCatg)){//个体户 
						PubPbopanomaly pubPbopanomaly=new PubPbopanomaly();
						//主键UID
	 					pubPbopanomaly.setBusExcList(busExcList);
						//注册号
						pubPbopanomaly.setRegNO(regNo); 
						//恢复标记 1已标记 0 已恢复
						pubPbopanomaly.setIsRecovery("0");
						//恢复异常原因编码
						pubPbopanomaly.setNorRea("7");
						//恢复正常的原因中文名称
						pubPbopanomaly.setNorReaCN("已注销，自动移出");
						//恢复日期
						//pubPbopanomaly.setNorDate(midBaseInfoDtoTem.getAltDate()); 
						if(midBaseInfoDtoTem.getAltDate()!=null){
							String aDate=DateUtils.formatDate(midBaseInfoDtoTem.getAltDate(), "yyyy-MM-dd")+" "+DateUtils.getTime();
							pubPbopanomaly.setNorDate(DateUtil.stringToDate(aDate,"yyyy-MM-dd HH:mm:ss"));
						} 
						//恢复事实和理由
						pubPbopanomaly.setRecoverRea("已注销");
						//恢复设置人
						pubPbopanomaly.setResetName("系统自动"); 
	 					//决定机关编码
	 					CodeRegorg codeRegorg=codeRegorgService.selectRegOrgByCode(regOrg);
						//恢复决定机关中文名称
						pubPbopanomaly.setNorDecOrgCN(codeRegorg==null?"":codeRegorg.getContentShort());
						//恢复决定机关编码
						pubPbopanomaly.setNorDecOrg(regOrg); 
						pubPbopanomaly.setPriPID(priPID);
						pubPbopanomalyService.updatePubPbopanomalyByBusExcList(pubPbopanomaly);  
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
					}else{//企业、农专
						PubOpaDetail pubOpaDetailTem=pubOpaDetailService.selectPubOpaDetailByBusExcList(busExcList, priPID);
						//删除未审核的撤销和移出记录
						if(pubOpaDetailTem!=null&&!"1".equals(pubOpaDetailTem.getAuditState())){
							pubOpaDetailService.deletePubOpaDetailByBusExcList(busExcList, priPID);
						} 
						PubOpanoMaly pubOpanoMaly=pubOpanoMalyService.selectPubOpanoMalyByBusExcList(busExcList);
						PubOpaDetailDto pubOpaDetail=new PubOpaDetailDto();
						pubOpaDetail.setBusExcList(busExcList); 
 						if(midBaseInfoDtoTem!=null){ 
							pubOpaDetail.setPriPID(midBaseInfoDtoTem.getPriPID());
							pubOpaDetail.setEntName(midBaseInfoDtoTem.getEntName());
							pubOpaDetail.setUniSCID(midBaseInfoDtoTem.getUniCode());
							pubOpaDetail.setRegNO(midBaseInfoDtoTem.getRegNO());
							pubOpaDetail.setLeRep(midBaseInfoDtoTem.getLeRep());
							pubOpaDetail.setCerType(pubOpanoMaly.getCerType());
							pubOpaDetail.setCerNO(pubOpanoMaly.getCerNO());
							pubOpaDetail.setRegOrg(regOrg);
							pubOpaDetail.setLocalAdm(midBaseInfoDtoTem.getLocalAdm());
							pubOpaDetail.setSpeCause(pubOpanoMaly.getSpeCause());
							pubOpaDetail.setSpeCauseCN(pubOpanoMaly.getSpeCauseCN());
							pubOpaDetail.setAbnTime(pubOpanoMaly.getAbnTime());
							pubOpaDetail.setDecOrg(pubOpanoMaly.getDecOrg());
							pubOpaDetail.setDecorgCN(pubOpanoMaly.getDecorgCN());
							pubOpaDetail.setIsMove("1");
							pubOpaDetail.setRemExcpres("7");
							pubOpaDetail.setRemExcpresCN("企业注销，自动移出");
							if(midBaseInfoDtoTem.getAltDate()!=null){
								String aDate=DateUtils.formatDate(midBaseInfoDtoTem.getAltDate(), "yyyy-MM-dd")+" "+DateUtils.getTime();
								pubOpaDetail.setRemDate(DateUtil.stringToDate(aDate,"yyyy-MM-dd HH:mm:ss"));
							}
							//pubOpaDetail.setRemDate(midBaseInfoDtoTem.getAltDate());
							pubOpaDetail.setReDecOrg(regOrg);
							CodeRegorg codeRegorg=codeRegorgService.selectRegOrgByCode(regOrg);
							pubOpaDetail.setReDecOrgCN(codeRegorg==null?"":codeRegorg.getContentShort());
							pubOpaDetail.setRemoveRea("已注销"); 
							pubOpaDetail.setFirstDept(codeRegorg==null?"":codeRegorg.getContentShort());
							pubOpaDetail.setFirstdate(midBaseInfoDtoTem.getAltDate());
							pubOpaDetail.setFirstName("系统自动");
							pubOpaDetail.setAuditDept(codeRegorg==null?"":codeRegorg.getContentShort());
							pubOpaDetail.setAuditName("系统自动");
							pubOpaDetail.setAuditState("1");
							pubOpaDetail.setAuditDate(midBaseInfoDtoTem.getAltDate());
							pubOpaDetail.setSeqYear(Integer.parseInt( DateUtil.getYear()));
 							DepartMent departMent=departMentService.selectDepartMentByDeptCode(regOrg);
							pubOpaDetail.setEntTypeCatg(entTypeCatg);
							pubOpaDetail.setDeptSameGov(departMent==null?"":departMent.getSameGov());
							pubOpaDetail.setDeptSameCourt(departMent==null?"":departMent.getSameCourt());
							pubOpaDetail.setDeptUpName(departMent==null?"":departMent.getDeptUpName());
							pubOpaDetail.setDeptName(departMent==null?"":departMent.getDeptName());
							pubOpaDetail.setCreateTime(DateUtil.getSysDate());
							pubOpaDetail.setYear(pubOpanoMaly.getYear());
							pubOpaDetailService.insertPubOpaDetail(pubOpaDetail); 
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
		 
		logger.info("企业/农专、个体户注销移出异常定时任务结束");
		return ""; 
	} 
}
