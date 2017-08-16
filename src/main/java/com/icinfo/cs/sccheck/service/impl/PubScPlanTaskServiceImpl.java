/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.sccheck.dto.PubScPlanTaskDto;
import com.icinfo.cs.sccheck.dto.PubScentDto;
import com.icinfo.cs.sccheck.mapper.PubScPlanTaskMapper;
import com.icinfo.cs.sccheck.mapper.PubSccheckTypeMapper;
import com.icinfo.cs.sccheck.model.PubScPlanTask;
import com.icinfo.cs.sccheck.model.PubSccheckType;
import com.icinfo.cs.sccheck.model.PubScdeptTask;
import com.icinfo.cs.sccheck.model.PubScentCondition;
import com.icinfo.cs.sccheck.service.IPubScPlanTaskService;
import com.icinfo.cs.sccheck.service.IPubScdeptTaskService;
import com.icinfo.cs.sccheck.service.IPubScentConditionService;
import com.icinfo.cs.sccheck.service.IPubScentService;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.cs.system.service.ISysUserService;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_scplan_task 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
@Service
public class PubScPlanTaskServiceImpl extends MyBatisServiceSupport implements IPubScPlanTaskService {

	@Autowired
	private PubScPlanTaskMapper pubScPlanTaskMapper;
	@Autowired
	private PubSccheckTypeMapper pubSccheckTypeMapper;
	@Autowired
	private IPubScentService pubScentService;
	@Autowired
	private IPubScdeptTaskService pubScdeptTaskService;
	@Autowired
	private IPubScentConditionService pubScentConditionService;
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 
	 * 描述   保存
	 * @author  赵祥江
	 * @date 2017年5月17日 上午9:47:49 
	 * @param  
	 * @throws
	 */
	@Override
	public int insertPubScPlanTask(PubScPlanTask pubScPlanTask)
			throws Exception {
		if(pubScPlanTask!=null){
			return pubScPlanTaskMapper.insert(pubScPlanTask);
		}
		return 0;
	}

	/**
	 * 
	 * 描述   根据uid查询
	 * @author  赵祥江
	 * @date 2017年5月17日 上午9:47:59 
	 * @param  
	 * @throws
	 */
	@Override
	public PubScPlanTask selectPubScPlanTaskByUid(String uid) throws Exception {
		if(StringUtil.isNotBlank(uid)){
			PubScPlanTask pubScPlanTask=new PubScPlanTask();
			pubScPlanTask.setUid(uid);
			return pubScPlanTaskMapper.selectOne(pubScPlanTask);
		}
		return null;
	}

	/**
	 * 
	 * 描述   根据uid更新
	 * @author  赵祥江
	 * @date 2017年5月17日 上午9:48:14 
	 * @param  
	 * @throws
	 */
	@Override
	public int updatePubScPlanTaskByUid(PubScPlanTask pubScPlanTask)
			throws Exception {
		if(pubScPlanTask!=null&&StringUtil.isNotBlank(pubScPlanTask.getUid())){
			Example example = new Example(PubScPlanTask.class);
			example.createCriteria()
			.andEqualTo("uid", pubScPlanTask.getUid()); 
			return pubScPlanTaskMapper.updateByExampleSelective(pubScPlanTask, example);
		}
		return 0;
	}
	
	/**
	 * 
	 * 描述   锁定任务
	 * @author 陈鑫
	 * @date 2017年5月17日 上午9:40:32
	 * @param 
	 * @return int
	 * @throws
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean lockPubScPlanTaskByUid(SysUserDto sysUser, String taskUid)throws Exception {
		PubScPlanTask pubScPlanTask = new PubScPlanTask();
		pubScPlanTask.setUid(taskUid);
		pubScPlanTask.setTaskState("02");
		List<String> entTypeCatgList = pubScentService.selectEntTypeCatg(taskUid);
		if(CollectionUtils.isNotEmpty(entTypeCatgList)){
			String taskObject = "";
			Set<String> set = new HashSet<String>();
			for(String entTypeCatg : entTypeCatgList){
				String obj = "";
				if("11,13,31,33,12,14,32,34,21,27,24,22,28".indexOf(entTypeCatg) > -1){
					obj = "1";
				}else if("16,17".indexOf(entTypeCatg) > -1){
					obj = "2";
				}else if("50".indexOf(entTypeCatg) > -1){
					obj = "3";
				}else if("23".indexOf(entTypeCatg) > -1){
					obj = "4";
				}
				if(StringUtils.isNotEmpty(obj)){
					if(StringUtils.isEmpty(taskObject)){
						taskObject = obj;
						set.add(obj);
					}else{
						if(!set.contains(obj)){
							taskObject += ","+obj;
							set.add(obj);
						}
					}
				}
			}
			pubScPlanTask.setTaskObject(taskObject);
			if(updatePubScPlanTaskByUid(pubScPlanTask) > 0){
				//4.特别说明：【下发原则：“直销企业专项库”的企业全部分配给省局；综合主体库里的企业按照登记机关下发；其他专项库里的企业，按照建库部门即（管辖机关）下发】
				return sendDeptTask(taskUid,sysUser);
			}
		}
		return false;
	}
	
	/**
	 * 描述：下发任务
	 * @author chenxin
	 * @date 2017-05-17
	 * @param taskUid
	 */
	private boolean sendDeptTask(String taskUid,SysUserDto sysUser)throws Exception {
		//1.直销企业专项库的企业全部分配给省局
		pubScdeptTaskService.delPubScdeptTask(taskUid);
		PubScPlanTask pubScPlanTask = this.selectPubScPlanTaskByUid(taskUid);
		if(StringUtils.isEmpty(pubScPlanTask.getRandomType())){
			return false;
		}
		String randomType = pubScPlanTask.getRandomType();
		if("2".equals(randomType)){
			List<PubScentCondition> pubScentConditionList = pubScentConditionService.selectPubScentCondition(taskUid, "A01",randomType);
			if(CollectionUtils.isNotEmpty(pubScentConditionList)){
				int randomNumTotal = 0;
				for(PubScentCondition pubScentCondition : pubScentConditionList){
					randomNumTotal += pubScentCondition.getRandomNum();
				}
				if(randomNumTotal > 0){
					PubScdeptTask pubScdeptTask = new PubScdeptTask();
					pubScdeptTask.setTaskUid(taskUid);
					pubScdeptTask.setEntNum(randomNumTotal);
					pubScdeptTask.setDeptCode("330000");
					pubScdeptTask.setUnitDeptCode("33000000");
					pubScdeptTask.setDeptState("1");
					pubScdeptTaskService.savePubScdeptTask(pubScdeptTask);
					String deptTaskUid = pubScdeptTask.getUid();
					pubScentService.updatePubScent(taskUid,deptTaskUid,"A01");
				}
			}
		}
		//2.综合主体库里的企业按照登记机关下发:先统计登记机关数量
		List<PubScentCondition> pubScentConditionMList = pubScentConditionService.selectPubScentCondition(taskUid, "multiple",randomType);
		if(CollectionUtils.isNotEmpty(pubScentConditionMList)){
			PubScentCondition pubScentConditionM = pubScentConditionMList.get(0);
			if(pubScentConditionM != null && pubScentConditionM.getRandomNum() > 0){
				List<PubScentDto> PubScentDtoList = pubScentService.selectRegOrgNumInSpecial(taskUid,"multiple");
				if(CollectionUtils.isNotEmpty(PubScentDtoList)){
					for(PubScentDto PubScentDto : PubScentDtoList){
						//2.1根据登记机关先创建任务
						PubScdeptTask pubScdeptTask = new PubScdeptTask();
						pubScdeptTask.setTaskUid(taskUid);
						pubScdeptTask.setEntNum(PubScentDto.getSpecialNum());
						pubScdeptTask.setDeptCode(PubScentDto.getRegOrg());
						pubScdeptTask.setUnitDeptCode(PubScentDto.getRegOrg()+"00");
						pubScdeptTask.setDeptState("1");
						pubScdeptTaskService.savePubScdeptTask(pubScdeptTask);
						//2.2创建完任务更新掉所有的综合主体库的改登记机关的任务taksUid
						String deptTaskUid = pubScdeptTask.getUid();
						pubScentService.updatePubScent(taskUid,deptTaskUid,"multiple",PubScentDto.getRegOrg());
					}
				}
			}
		}
		//3.其他专项库里的企业，查询剩余的需要下发的任务
		if("2".equals(randomType)){
			String[] specialCodes = {"A01","multiple"};
			List<PubScentDto> PubScentDtoList = pubScentService.selectUidsInSpecial(taskUid, specialCodes);
			for(PubScentDto pubScentDto : PubScentDtoList){
				String relateUid = pubScentDto.getRelateUserUid();
				PubScdeptTask pubScdeptTask = new PubScdeptTask();
				pubScdeptTask.setTaskUid(taskUid);
				pubScdeptTask.setEntNum(pubScentDto.getSpecialNum());
				pubScdeptTask.setRelateUserUid(relateUid);
				String deptCode = sysUserService.selectOrgCode(relateUid);
				if(deptCode.length() == 8){
					pubScdeptTask.setUnitDeptCode(deptCode);
					pubScdeptTask.setDeptCode(deptCode.substring(0, 6));
				}else{
					String deptCodeA = combineDeptCode(deptCode);
					pubScdeptTask.setUnitDeptCode(deptCodeA);
					pubScdeptTask.setDeptCode(deptCodeA.substring(0, 6));
				}
				pubScdeptTask.setDeptState("1");
				pubScdeptTaskService.savePubScdeptTask(pubScdeptTask);
				pubScentService.updatePubScentByRelateUid(taskUid, pubScdeptTask.getUid(),relateUid);
			}
		}
		
		return true;
	}
	
	private String combineDeptCode(String deptCode){
		int deptCodeLength = deptCode.length();
		if(deptCodeLength < 8){
			for(int i = deptCodeLength;i<8;i++){
				deptCode = deptCode+"0";
			}
		}
		return deptCode;
	}

	/**
	 * 
	 * 描述   根据uid删除
	 * @author  赵祥江
	 * @date 2017年5月17日 上午9:48:37 
	 * @param  
	 * @throws
	 */
	@Override
	public int deletePubScPlanTaskByUid(String uid) throws Exception {
		if(StringUtil.isNotBlank(uid)){
			PubScPlanTask pubScPlanTask=new PubScPlanTask();
			pubScPlanTask.setUid(uid);
			return pubScPlanTaskMapper.delete(pubScPlanTask);
		}
		return 0;
	}

	/**
	 * 
	 * 描述   分页查询
	 * @author  赵祥江
	 * @date 2017年5月17日 上午9:48:48 
	 * @param  
	 * @throws
	 */
	@Override
	public List<PubScPlanTaskDto> queryPubScPlanTaskListJSON(PageRequest request)
			throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubScPlanTaskMapper.selectPubScPlanTaskList(request.getParams());
	}

	/**
	 * 
	 * 描述   根据任务名称查询
	 * @author  赵祥江
	 * @date 2017年5月18日 上午10:22:56 
	 * @param  
	 * @throws
	 */
	@Override
	public List<PubScPlanTask> selectPubScPlanTaskByTaskName(String taskName)
			throws Exception {
		PubScPlanTask pubScPlanTask=new PubScPlanTask();
		pubScPlanTask.setTaskName(taskName);
		return pubScPlanTaskMapper.select(pubScPlanTask);
	}

	/**
	 * 
	 * 描述   查询所有检查事项
	 * @author  赵祥江
	 * @date 2017年5月18日 下午4:41:04 
	 * @param  
	 * @throws
	 */
	@Override
	public List<PubSccheckType> queryPubSccheckTypeListJSON(PageRequest request)
			throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubSccheckTypeMapper.selectPubSccheckTypeList(request.getParams());
	}
	
	/**
	 * 
	 * 描述:检查人员随机收取任务列表
	 * @auther gaojinling
	 * @date 2017年5月19日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<PubScPlanTaskDto> selectPubScPlanTaskAndDeptList(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubScPlanTaskMapper.selectPubScPlanTaskAndDeptList(request.getParams());
	}
	
	/**
	 * 
	 * 描述:单个任务抽取情况
	 * @auther gaojinling
	 * @date 2017年5月19日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public PubScPlanTaskDto selectPubScPlanTaskAndDeptListNotPage(String taskuid,String deptCode,String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//部门任务表id
		map.put("uid", taskuid);
		//当前部门编码前6位
		map.put("deptCode", deptCode.substring(0,6));
		//当前用户id
		map.put("userId", userId);
		List<PubScPlanTaskDto> pubScPlanTaskDtos = pubScPlanTaskMapper.selectPubScPlanTaskAndDeptList(map);
		if(pubScPlanTaskDtos != null && pubScPlanTaskDtos.size()>0){
			return pubScPlanTaskDtos.get(0);
		}else{
			return new PubScPlanTaskDto();
		}
	}

	/**
	 * 
	 * 描述   获取文号
	 * @author  赵祥江
	 * @date 2017年5月31日 下午5:49:34 
	 * @param  
	 * @throws
	 */
	@Override
	public Integer selectDocNumBydeptShortName(String deptShortName,Integer seqYear) throws Exception {
		Map<String,Object> queryMap=new HashMap<String,Object>();
		queryMap.put("deptShortName", deptShortName);
		queryMap.put("seqYear", seqYear);
		return pubScPlanTaskMapper.selectDocNumBydeptShortName(queryMap);
	}
	
	/**
	 * 
	 * 描述: 抽查任务情况统计
	 * @auther chenxin
	 * @date 2017年6月27日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubScPlanTaskDto> selectScPubScPlanTaskCount(PageRequest request)throws Exception {
		Map<String,Object> parmMap=request.getParams();
		if(parmMap != null){
			StringUtil.paramTrim(parmMap);
		}
		PageHelper.startPage(request.getPageNum(), 10000);
		return pubScPlanTaskMapper.selectScPlanTaskCount(parmMap);
	}
	
	/**
	 * 
	 * 描述: 查询任务
	 * @auther chenxin
	 * @date 2017年6月27日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubScPlanTask> selectTaskNames(String deptCodeLike) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("deptCodeLike", deptCodeLike);
		return pubScPlanTaskMapper.selectTaskNames(parmMap);
	}
}