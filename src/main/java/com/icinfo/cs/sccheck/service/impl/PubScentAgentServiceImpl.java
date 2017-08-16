/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icinfo.cs.drcheck.service.IPubScagentService;
import com.icinfo.cs.sccheck.dto.PubScentAgentBackDto;
import com.icinfo.cs.sccheck.dto.PubScentAgentDto;
import com.icinfo.cs.sccheck.mapper.PubScentAgentMapper;
import com.icinfo.cs.sccheck.model.PubScent;
import com.icinfo.cs.sccheck.model.PubScentAgent;
import com.icinfo.cs.sccheck.model.PubScentResult;
import com.icinfo.cs.sccheck.service.IPubScdeptTaskService;
import com.icinfo.cs.sccheck.service.IPubScentAgentBackService;
import com.icinfo.cs.sccheck.service.IPubScentAgentService;
import com.icinfo.cs.sccheck.service.IPubScentResultService;
import com.icinfo.cs.sccheck.service.IPubScentService;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述: cs_pub_scent_agent 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2017年05月19日
 */
@Service
public class PubScentAgentServiceImpl extends MyBatisServiceSupport implements IPubScentAgentService {
	@Autowired
	private PubScentAgentMapper pubScentAgentMapper;
	@Autowired
	private IPubScdeptTaskService pubScdeptTaskService;
	@Autowired
	private IPubScentAgentBackService pubScentAgentBackService;
	@Autowired
	private IPubScentService pubScentService;
	@Autowired
	private IPubScentResultService pubScentResultService;
	@Autowired
	private IPubScagentService pubScagentService;

	/**
	 * 描述：企业随机配对执法人员
	 * 
	 * @author chenxin
	 * @date 2017-05-22
	 * @param sysUser
	 * @param deptTaskUid
	 * @param randomType
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean doRandomEntAgent(SysUserDto sysUser, String deptTaskUid, String randomType, int groupNum)
			throws Exception {
		Example example = new Example(PubScentAgent.class);
		example.createCriteria().andEqualTo("taskUid", deptTaskUid); 
		pubScentAgentMapper.deleteByExample(example);
		// 1.查询执法人员信息
		List<PubScentAgentBackDto> pubScentAgentBackList = pubScentAgentBackService.selectPubScentAgentBackList(deptTaskUid);
		// 2.随机分组
		List<PubScent> pubScentList = pubScentService.selectPubSentByDeptTaskUId(deptTaskUid);
		int size = pubScentAgentBackList.size();
		int step = new Random().nextInt(size);//随机抽取第一个
		for (PubScent pubScent : pubScentList) {
			PubScentResult pubScentResult = new PubScentResult();
			pubScentResult.setMainTaskUid(pubScent.getTaskUid());
			pubScentResult.setTaskUid(deptTaskUid);
			pubScentResult.setPriPID(pubScent.getPriPID());
			pubScentResult.setAuditState("1");
			pubScentResultService.savePubScentResult(pubScentResult);
			String agentNames = "";
			String checkDeptNames = "";
			if (size > 0 && size >= groupNum) {
				for (int i = 0; i < groupNum; i++) {
					if (step > size - 1) {
						step = 0;
					}
					PubScentAgentBackDto pubScentAgentBackDto = pubScentAgentBackList.get(step);
					PubScentAgent pubScentAgent = new PubScentAgent();
					pubScentAgent.setTaskUid(deptTaskUid);
					pubScentAgent.setAgentUid(pubScentAgentBackDto.getAgentUid());
					pubScentAgent.setPriPID(pubScent.getPriPID());
					pubScentAgentMapper.insertSelective(pubScentAgent);
					if(StringUtils.isEmpty(agentNames)){
						agentNames += pubScentAgentBackDto.getAgentName();
						checkDeptNames += pubScentAgentBackDto.getDeptCodeDesc();
					}else{
						agentNames += "，"+pubScentAgentBackDto.getAgentName();
						if(checkDeptNames.indexOf(pubScentAgentBackDto.getDeptCodeDesc()) <= -1){
							checkDeptNames += "，"+pubScentAgentBackDto.getDeptCodeDesc();
						}
					}
					step++;
				}
			}
			PubScentResult params = new PubScentResult();
			params.setUid(pubScentResult.getUid());
			params.setCheckDeptPerson(agentNames);
			params.setCheckDeptName(checkDeptNames);
			pubScentResultService.updatePubScentResultByUid(params);
			// 3.随机配对
		}
		pubScdeptTaskService.updatePubScdeptTask(deptTaskUid, "2", sysUser,groupNum);
		return true;
	}

	/**
	 * 描述：查看企业匹配的执法人员
	 * 
	 * @author chenxin
	 * @date 2017-05-22
	 * @param deptTaskUid
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@Override
	public String doSelectEntAgent(String deptTaskUid, String priPID) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskUid", deptTaskUid);
		params.put("priPID", priPID);
		List<String> agentNameList = pubScentAgentMapper.selectEntAgents(params);
		String agentNames = "";
		for (String agentName : agentNameList) {
			if (StringUtils.isEmpty(agentNames)) {
				agentNames = agentName;
			} else {
				agentNames += "，" + agentName;
			}
		}
		return agentNames;
	}

	@Override
	public String doSelectDeptName(String deptTaskUid, String priPID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskUid", deptTaskUid);
		params.put("priPID", priPID);
		List<String> deptNameList = pubScentAgentMapper.selectDeptNames(params);
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		set.addAll(deptNameList);
		newList.addAll(set);
		String deptNames = "";
		for (String deptName : newList) {
			if (StringUtils.isEmpty(deptNames)) {
				deptNames = deptName;
			} else {
				deptNames += "，" + deptName;
			}
		}
		return deptNames;
	}
	
	/**
	 * 描述：查看企业匹配执法人员列表
	 * @author chenxin
	 * @date 2017-6-13
	 * @param request
	 * @return
	 */
	@Override
	public List<PubScentAgentDto> selectPubScentAgentDtoList(PageRequest request)throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubScentAgentMapper.selectPubScentAgentDtoList(request.getParams());
	}
	
	/**
	 * 描述:调整执法人员
	 * @author chenxin
	 * @date 2017-06-14
	 * @param sysUser
	 * @param scentAgentUids
	 * @param agentUid
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean doSignEntAgent(SysUserDto sysUser, String scentAgentUids,String agentUid) throws Exception {
		if(StringUtils.isNotEmpty(scentAgentUids) && StringUtils.isNotEmpty(agentUid)){
			String[] uids = scentAgentUids.split(",");
			for(String uid : uids){
				Example example = new Example(PubScentAgent.class);
				example.createCriteria().andEqualTo("uid", uid);
				PubScentAgent pubScentAgent = new PubScentAgent();
				pubScentAgent.setAgentUid(agentUid);
				pubScentAgent.setAdjustUserUid(sysUser.getId());
				pubScentAgent.setAdjustUserName(sysUser.getRealName());
				pubScentAgent.setAdjustDate(new Date());
				pubScentAgentMapper.updateByExampleSelective(pubScentAgent, example);
			}
			return true;
		}
		return false;
	}
}