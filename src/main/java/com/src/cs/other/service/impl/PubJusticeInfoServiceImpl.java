/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.other.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.utils.BeanUtil;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.ext.mapper.MidBaseInfoMapper;
import com.icinfo.cs.ext.model.MidBaseInfo;
import com.icinfo.cs.other.dto.PubJusticeInfoDto;
import com.icinfo.cs.other.mapper.PubJusticeInfoHisMapper;
import com.icinfo.cs.other.mapper.PubJusticeInfoMapper;
import com.icinfo.cs.other.model.PubJusticeInfo;
import com.icinfo.cs.other.model.PubJusticeInfoHis;
import com.icinfo.cs.other.service.IPubJusticeInfoService;
import com.icinfo.cs.system.model.SysUser;
import com.icinfo.cs.yr.mapper.PubServerHisModMapper;
import com.icinfo.cs.yr.model.PubServerHisMod;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.mapper.entity.Example.Criteria;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述: cs_pub_justiceinfo 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年10月20日
 */
@Service
public class PubJusticeInfoServiceImpl extends MyBatisServiceSupport implements IPubJusticeInfoService {

	@Autowired
	PubJusticeInfoMapper pubJusticeInfoMapper;

	@Autowired
	PubServerHisModMapper pubServerHisModMapper;

	@Autowired
	PubJusticeInfoHisMapper pubJusticeInfoHisMapper;
	@Autowired
	private MidBaseInfoMapper midBaseInfoMapper;

	/**
	 * 描述：获取司法协助信息录入列表数据
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param request
	 * @return List<PubJusticeInfo>
	 * @throws Exception
	 */
	public List<PubJusticeInfo> queryPage(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubJusticeInfoMapper.selectPubJusticeInfoForApply(request.getParams());
	}

	/**
	 * 描述：查询司法协助信息（被执行人选择页面）
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pubJusticeInfo
	 * @param request
	 * @return List<PubJusticeInfo>
	 * @throws Exception
	 */
	public List<PubJusticeInfo> queryPageForInclude(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubJusticeInfoMapper.selectPubJusticeInfoForInclude(request.getParams());
	}

	/**
	 * 描述：获取司法协助信息查询数据
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param request
	 * @return boolean
	 * @throws Exception
	 */
	public List<PubJusticeInfoDto> queryPageForSearch(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		List<PubJusticeInfoDto> pubJusticeInfoDtoList = pubJusticeInfoMapper
				.selectPubJusticeInfoForSearch(request.getParams());
		int totalAll = pubJusticeInfoMapper.selectTotalAllForSearch(request.getParams());
		int AmountAll = pubJusticeInfoMapper.selectTotalAmountForSearch(request.getParams());
		int totalOne = pubJusticeInfoMapper.selectTotalOneForSearch(request.getParams());
		int totalTwo = pubJusticeInfoMapper.selectTotalTwoForSearch(request.getParams());
		if (CollectionUtils.isNotEmpty(pubJusticeInfoDtoList)) {
			for (PubJusticeInfoDto PubJusticeInfoDto : pubJusticeInfoDtoList) {
				PubJusticeInfoDto.setOneCount(totalOne);// 股权冻结信息条数
				PubJusticeInfoDto.setTwoCount(totalTwo);// 股东强制变更信息条数
				PubJusticeInfoDto.setTotal(totalAll);// 公示企业家数
				PubJusticeInfoDto.setAmount(AmountAll);// 公示企业条数
			}
		}
		return pubJusticeInfoDtoList;
	}

	/**
	 * 描述：通过priPID，UID查询司法协助信息
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pripid,uID
	 * @return PubJusticeInfo
	 * @throws Exception
	 */
	public PubJusticeInfo doGetPubJusticeInfo(String pripid, String UID) throws Exception {
		PubJusticeInfo pubJusticeInfo = new PubJusticeInfo();
		pubJusticeInfo.setPriPID(pripid);
		pubJusticeInfo.setUID(UID);
		return pubJusticeInfoMapper.selectOne(pubJusticeInfo);
	}

	/**
	 * 描述：通过pripid 和 justiceType 查询司法信息
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pripid
	 * @param justiceType
	 * @return List<PubJusticeInfo>
	 * @throws Exception
	 */
	public List<PubJusticeInfo> doGetPubJusticeInList(String pripid, String justiceType) throws Exception {
		Example example = new Example(PubJusticeInfo.class);
		example.createCriteria().andEqualTo(Constants.CS_PRIPID, pripid).andEqualTo("justiceType", justiceType);
		return pubJusticeInfoMapper.selectByExample(example);
	}

	/**
	 * 描述：通过priPID，UID删除司法协助信息
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pripid,uID
	 * @return PubJusticeInfo
	 * @throws Exception
	 */
	public boolean deletePubJusticeInfo(String priPID, String UID) throws Exception {
		Example example = new Example(PubJusticeInfo.class);
		example.createCriteria().andEqualTo("priPID", priPID).andEqualTo("UID", UID);
		if (pubJusticeInfoMapper.deleteByExample(example) < 0) {
			return false;
		}
		return true;
	}

	/**
	 * 描述：司法协助提交审核
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pubJusticeInfo
	 * @param sysUser
	 * @return boolean
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean doCommitJusticeInfo(PubJusticeInfo pubJusticeInfo, SysUser sysUser) throws Exception {
		if (pubJusticeInfo != null) {
			pubJusticeInfo.setAuditDate(new Date());
			pubJusticeInfo.setAuditName(sysUser.getRealName());
			Example example = new Example(PubJusticeInfo.class);
			example.createCriteria().andEqualTo(Constants.CS_PRIPID, pubJusticeInfo.getPriPID()).andEqualTo("UID",
					pubJusticeInfo.getUID());
			pubJusticeInfoMapper.updateByExampleSelective(pubJusticeInfo, example);
			PubJusticeInfo pubJusticeInfoTemp = this.doGetPubJusticeInfo(pubJusticeInfo.getPriPID(),
					pubJusticeInfo.getUID());
			if (pubJusticeInfoTemp != null) {
				// 保存修改记录表
				PubServerHisMod pubServerHisMod = new PubServerHisMod();
				pubServerHisMod.setAltDate(pubJusticeInfoTemp.getSetDate());
				pubServerHisMod.setAltName(pubJusticeInfoTemp.getSetName());
				pubServerHisMod.setAltTable("cs_pub_justiceinfo");
				pubServerHisMod.setAuditDate(pubJusticeInfoTemp.getAuditDate());
				pubServerHisMod.setAuditName(pubJusticeInfoTemp.getAuditName());
				pubServerHisMod.setAuditState(pubJusticeInfoTemp.getAuditState());
				pubServerHisMod.setHisNO(StringUtil.getRandomNumber(pubJusticeInfoTemp.getPriPID()));
				pubServerHisMod.setModUID(pubJusticeInfoTemp.getUID());
				pubServerHisMod.setPriPID(pubJusticeInfoTemp.getPriPID());
				pubServerHisModMapper.insert(pubServerHisMod);
				// 保存历史记录
				PubJusticeInfoHis pubJusticeInfoHis = new PubJusticeInfoHis();
				pubJusticeInfoTemp.setId(null);// 防止插入主键重复
				BeanUtil.getBeanUtilsBean().copyProperties(pubJusticeInfoHis, pubJusticeInfoTemp);
				pubJusticeInfoHis.setHisNO(pubServerHisMod.getHisNO());
				pubJusticeInfoHisMapper.insert(pubJusticeInfoHis);
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 描述：新增或修改司法协助信息
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pubJusticeInfo
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doAddAndEditJusticeInfo(PubJusticeInfoDto pubJusticeInfoDto, SysUser sysUser) throws Exception {
		PubJusticeInfo pubJusticeInfo = new PubJusticeInfo();
		BeanUtil.getBeanUtilsBean().copyProperties(pubJusticeInfo, pubJusticeInfoDto);
		// 设置录入时间，录入人，时间戳
		pubJusticeInfo.setSetDate(new Date());
		pubJusticeInfo.setCreateTime(new Date());
		pubJusticeInfo.setSetName(sysUser.getRealName());
		pubJusticeInfo.setDeptCode(sysUser.getDeptCode());
		if(StringUtil.isBlank(pubJusticeInfo.getUID())){
			pubJusticeInfo.setAuditState("0");// 设置待审核
			if(pubJusticeInfo.getExecuteItem().equals("2") || pubJusticeInfo.getExecuteItem().equals("3")){
				pubJusticeInfo.setJusticeConNO(pubJusticeInfoDto.getUpJusticeNO());// 上一条冻结数据的JUSTICE_NO
			}
			if(pubJusticeInfoMapper.insert(pubJusticeInfo) < 0){
				return false;
			}
		}else{
			if(pubJusticeInfo.getAuditState().equals("2")){
				pubJusticeInfo.setAuditState("0");
				pubJusticeInfo.setAuditDate(null);
				pubJusticeInfo.setAuditOpin(null);
				pubJusticeInfo.setAuditName(null);
			}
			if(pubJusticeInfo.getExecuteItem().equals("2") || pubJusticeInfo.getExecuteItem().equals("3")){
				if (StringUtil.isNotBlank(pubJusticeInfoDto.getUpJusticeNO())) {
					pubJusticeInfo.setJusticeConNO(pubJusticeInfoDto.getUpJusticeNO());
				}
			}
			Example example = new Example(PubJusticeInfo.class);
			example.createCriteria().andEqualTo(Constants.CS_PRIPID, pubJusticeInfo.getPriPID()).andEqualTo("UID",
					pubJusticeInfo.getUID());
			if(pubJusticeInfoMapper.updateByExample(pubJusticeInfo, example) < 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * 描述: 获取司法协助信息录入列表数据（公示）
	 * 
	 * @auther ZhouYan
	 * @date 2016年10月26日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubJusticeInfo> queryPageForPub(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		String priPID = request.getParams().get("priPID").toString();
		if (StringUtils.isNotBlank(priPID)) {
			Example example = new Example(PubJusticeInfo.class);
			example.createCriteria().andEqualTo("auditState", "1").andEqualTo(Constants.CS_PRIPID, priPID);
			return pubJusticeInfoMapper.selectByExample(example);
		} else {
			return new ArrayList<PubJusticeInfo>();
		}
	}

	/**
	 * 描述: 司法协助信息录入详情（公示）
	 * 
	 * @auther ZhouYan
	 * @date 2016年10月26日
	 * @param UID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PubJusticeInfo doGetPubJusticeInfoForPub(String UID) throws Exception {
		PubJusticeInfo pubJusticeInfo = new PubJusticeInfo();
		pubJusticeInfo.setUID(UID);
		return pubJusticeInfoMapper.selectOne(pubJusticeInfo);
	}

	/**
	 * 描述：查询司法协助信息（用于公告公示）
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param pubJusticeInfo
	 * @param request
	 * @return List<PubJusticeInfo>
	 * @throws Exception
	 */
	public List<PubJusticeInfo> doGetJusticeInfoForBulletin(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubJusticeInfoMapper.selectJusticeInfoForBulletin(request.getParams());
	}

	/**
	 * 描述: 查询司法协助信息（用于公告公示）
	 * 
	 * @auther yujingwei
	 * @date 2016年10月26日
	 * @param justiceType
	 * @return List<PubJusticeInfo>
	 * @throws Exception
	 */
	public List<PubJusticeInfo> doGetInfoByJusticeType(String updateDate) throws Exception {
		return pubJusticeInfoMapper.selectJusticeinfoListForBulletin(updateDate);
	}

	/**
	 * 描述：通过priPID查询司法协助信息
	 * 
	 * @author chenyu
	 * @data 2016-10-31
	 * @param pripid
	 * @return PubJusticeInfo
	 * @throws Exception
	 */
	@Override
	public List<PubJusticeInfo> selectPubJusticeInfoListByPriPID(PageRequest request) throws Exception {
		// TODO Auto-generated method stub
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Example example = new Example(PubJusticeInfo.class);
		example.createCriteria()
	    .andEqualTo("auditState", "1")
		.andEqualTo("priPID", request.getParams().get("priPID").toString());
		return pubJusticeInfoMapper.selectByExample(example);
	}

	/**
	 * 描述：通过cerNO查询司法协助信息
	 * 
	 * @author chenyu
	 * @data 2016-10-31
	 * @param pripid
	 * @return PubJusticeInfo
	 * @throws Exception
	 */
	@Override
	public List<PubJusticeInfoDto> selectSpPledgeListByCerNO(PageRequest request) {
		// TODO Auto-generated method stub
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubJusticeInfoMapper.selectPubJusticeInfoListByCerNO(request.getParams());
	}

	/**
	 * 描述：司法协助-股权冻结定时失效
	 * 
	 * @author yujingwei
	 * @data 2016-10-17
	 * @param
	 * @throws Exception
	 */
	public void doSetfrozNoEffect() throws Exception {

	}

	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, String> importByExcel(List<List<Object>> list, HttpSession session) throws Exception {
		int result = 0;
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, String> iteMap = new HashMap<String, String>();
		iteMap.put("公示冻结股权、其他投资权益", "1");
		iteMap.put("续行冻结股权、其他投资权益", "2");
		iteMap.put("解除冻结股权、其他投资权益", "3");
		iteMap.put("强制转让被执行人股权，办理有限责任公司股东变更登记", "4");
		Map<String, String> carType = new HashMap<String, String>();
		carType.put("居民身份证", "10");
		carType.put("军官证", "20");
		carType.put("警官证", "30");
		carType.put("外国(地区)护照", "40");
		carType.put("香港身份证", "52");
		carType.put("澳门身份证", "54");
		carType.put("台湾身份证", "56");
		carType.put("其他有效身份证件", "90");
		PubJusticeInfo pubJusticeInfo = new PubJusticeInfo();
		SysUser sysUser = (SysUser) session.getAttribute(Constants.SESSION_SYS_USER);
		pubJusticeInfo.setSetDate(new Date());
		//pubJusticeInfo.setJusticeType("2");
		pubJusticeInfo.setCreateTime(new Date());
		pubJusticeInfo.setDeptCode(sysUser.getDeptCode());
		pubJusticeInfo.setSetName(sysUser.getRealName());
		pubJusticeInfo.setAuditState("0");// 设置待审核
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			List<Object> lo = list.get(i);
			MidBaseInfo bas = new MidBaseInfo();
			if(StringUtils.isEmpty(String.valueOf(lo.get(0)).trim())){//如果注册号为空则不保存
				continue;
			}
			bas.setRegNO(String.valueOf(lo.get(0)));
			MidBaseInfo baseIfo = getBaseInfoByUniIDOrRegNO(lo.get(0).toString());// 查询其主体信息，活动主体id(pirPID)
			if (baseIfo == null || baseIfo.getPriPID().equals("")) {
				buffer.append(lo.get(0).toString());
				continue;
			} else {
				pubJusticeInfo.setPriPID(baseIfo.getPriPID());
			}
			pubJusticeInfo.setUniSCID(String.valueOf(lo.get(0)));
			pubJusticeInfo.setEntName(String.valueOf(lo.get(1)));
			pubJusticeInfo.setExecutionCourt(String.valueOf(lo.get(2)));
		
			if(lo.size()>3){
			pubJusticeInfo.setExecuteItem(iteMap.get(String.valueOf(lo.get(3))));
		    String jusType=iteMap.get(String.valueOf(lo.get(3)));
			if(jusType.equals("1")||jusType.equals("2")||jusType.equals("3")){
				pubJusticeInfo.setJusticeType("1");
			}else if(jusType.equals("4")){
				pubJusticeInfo.setJusticeType("2");
			}
			if(jusType.equals("1")||jusType.equals("2")){
				pubJusticeInfo.setFrozState("1");
			}else if(jusType.equals("3")){
				pubJusticeInfo.setFrozState("2");
			}else if(jusType.equals("4")){
				pubJusticeInfo.setFrozState("");
			}
			}
			pubJusticeInfo.setBotRefNum(String.valueOf(lo.get(4)));
			pubJusticeInfo.setExeRulNum(String.valueOf(lo.get(5)));
			pubJusticeInfo.setExecuteNo(String.valueOf(lo.get(6)));
			pubJusticeInfo.setInv(String.valueOf(lo.get(7)));
			pubJusticeInfo.setCerType(carType.get(String.valueOf(lo.get(8))));
			pubJusticeInfo.setCerNO(String.valueOf(lo.get(9)));
			if (lo.get(10) != null && StringUtils.isNotBlank(String.valueOf(lo.get(10)).trim())) {
				BigDecimal bd = new BigDecimal(String.valueOf(lo.get(10))).setScale(4);
				pubJusticeInfo.setFroAm(bd);
			}
			if (lo.size() > 11) {
				pubJusticeInfo.setFroAuth(String.valueOf(lo.get(11)));
			}
			if (lo.size() > 12) {
				pubJusticeInfo.setFroFrom(DateUtil.stringToDate(String.valueOf(lo.get(12)), "yyyy-MM-dd"));
			}
			if (lo.size() > 13) {
				pubJusticeInfo.setFroTo(DateUtil.stringToDate(String.valueOf(lo.get(13)), "yyyy-MM-dd"));
			}
			if (lo.size() > 14) {
				pubJusticeInfo.setFrozDeadline(String.valueOf(lo.get(14)));
			}
			if (lo.size() > 15) {
				pubJusticeInfo.setPublicDate(DateUtil.stringToDate(String.valueOf(lo.get(15)), "yyyy-MM-dd"));
			}
			if (lo.size() > 16) {
				pubJusticeInfo.setThawDate(DateUtil.stringToDate(String.valueOf(lo.get(16)), "yyyy-MM-dd"));
			}
			if (lo.size() > 17) {
				pubJusticeInfo.setAssInv(String.valueOf(lo.get(17)));
			}
			if (lo.size() > 18) {
				pubJusticeInfo.setAssCerType(carType.get(String.valueOf(lo.get(18))));
			}
			if (lo.size() > 19) {
				pubJusticeInfo.setAssCerNO(String.valueOf(lo.get(19)));
			}
			if (lo.size() > 20) {
				pubJusticeInfo.setExecuteDate(DateUtil.stringToDate(String.valueOf(lo.get(20)), "yyyy-MM-dd"));
			}
			result = pubJusticeInfoMapper.insertSelective(pubJusticeInfo);
		}
		resultMap.put("regNO", buffer.toString());
		resultMap.put("result", String.valueOf(result));
		return resultMap;
	}

	/**
	 * 
	 * @param code
	 * @return
	 * @author ljx
	 */
	@Override
	public MidBaseInfo getBaseInfoByUniIDOrRegNO(String code) {
		Example example = new Example(MidBaseInfo.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("regNO", code);
		Criteria criteria2 = example.createCriteria();
		criteria2.andEqualTo("uniCode", code);
		example.or(criteria2);
		List<MidBaseInfo> list = midBaseInfoMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}