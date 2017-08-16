/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinfo.cs.sccheck.dto.PubScentDto;
import com.icinfo.cs.sccheck.mapper.PubScentMapper;
import com.icinfo.cs.sccheck.model.PubScent;
import com.icinfo.cs.sccheck.model.PubScentBack;
import com.icinfo.cs.sccheck.model.PubScentSpecial;
import com.icinfo.cs.sccheck.service.IPubScentService;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_scent 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
@Service
public class PubScentServiceImpl extends MyBatisServiceSupport implements IPubScentService {
	
	@Autowired 
	private PubScentMapper pubScentMapper;
	
	/**
	 * 
	 * 描述: 通过任务id查询已抽取的企业
	 * @auther gaojinling
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	public List<PubScent> selectPubSentByDeptTaskUId(String deptTaskUid) throws Exception{
		Example example = new Example(PubScent.class);
		example.createCriteria().andEqualTo("deptTaskUid", deptTaskUid); 
		return pubScentMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * 描述: 通过部门任务id分页查询已抽取的企业
	 * @auther gaojinling
	 * @date 2017年5月20日 
	 * @param request
	 * @return
	 */
	public List<PubScentDto> selectPubScentDtoList(PageRequest request)  throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		return pubScentMapper.selectPubScentDtoList(request.getParams());
	}
	
	
	/**
	 * 
	 * 描述: 保存抽取结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	@Override
	public void savePubScent(PubScentBack pubScentBack) throws Exception {
		if(pubScentBack != null){
			PubScent pubScent = new PubScent();
			pubScent.setEntName(pubScentBack.getEntName());
			pubScent.setPriPID(pubScentBack.getPriPID());
			pubScent.setRegNO(pubScentBack.getRegNO());
			pubScent.setUniCode(pubScentBack.getUniCode());
			pubScent.setRegState(pubScentBack.getRegState());
			pubScent.setRegOrg(pubScentBack.getRegOrg());
			pubScent.setRegOrgName(pubScentBack.getRegOrgName());
			pubScent.setEntTypeCatg(pubScentBack.getEntTypeCatg());
			pubScent.setLocalAdm(pubScentBack.getLocalAdm());
			pubScent.setLocalAdmName(pubScentBack.getLocalAdmName());
			pubScent.setSpecialCode("multiple");
			pubScent.setSpecialName("主体综合库");
			pubScent.setEstDate(pubScentBack.getEstDate());
			pubScent.setTaskUid(pubScentBack.getTaskUid());
			pubScent.setRelateUserUid(pubScentBack.getRelateUserUid());
			pubScentMapper.insertSelective(pubScent);
		}
	}
	
	/**
	 * 
	 * 描述: 删除抽检结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	@Override
	public void delPubScent(String taskUid) throws Exception {
		Example example = new Example(PubScent.class);
		example.createCriteria().andEqualTo("taskUid", taskUid); 
		pubScentMapper.deleteByExample(example);
	}
	
	/**
	 * 
	 * 描述: 企业分配给具体的部门
	 * @auther chenxin
	 * @date 2017年5月20日  
	 * @param taskUid
	 * @param deptTaskUid
	 * @param string
	 */
	@Override
	public void updatePubScent(String taskUid, String deptTaskUid, String specialCode) {
		if(StringUtils.isNotEmpty(taskUid) && StringUtils.isNotEmpty(deptTaskUid) && StringUtils.isNotEmpty(specialCode)){
			Example example = new Example(PubScent.class);
			example.createCriteria().andEqualTo("taskUid", taskUid).andEqualTo("specialCode", specialCode); 
			PubScent pubScent = new PubScent();
			pubScent.setDeptTaskUid(deptTaskUid);
			pubScentMapper.updateByExampleSelective(pubScent,example);
		}
	}
	
	/**
	 * 
	 * 描述: 企业随机分配
	 * @auther chenxin
	 * @date 2017年5月20日  
	 * @param taskUid
	 * @param deptTaskUid
	 * @param specialCode
	 * @param regOrg
	 * 
	 */
	@Override
	public void updatePubScent(String taskUid, String deptTaskUid,String specialCode, String regOrg) {
		if(StringUtils.isNotEmpty(taskUid) && StringUtils.isNotEmpty(deptTaskUid) && StringUtils.isNotEmpty(specialCode)&& StringUtils.isNotEmpty(regOrg)){
			Example example = new Example(PubScent.class);
			example.createCriteria().andEqualTo("taskUid", taskUid).andEqualTo("specialCode", specialCode).andEqualTo("regOrg", regOrg); 
			PubScent pubScent = new PubScent();
			pubScent.setDeptTaskUid(deptTaskUid);
			pubScentMapper.updateByExampleSelective(pubScent,example);
		}
		
	}
	
	/**
	 * 
	 * 描述: 企业随机分配
	 * @auther chenxin
	 * @date 2017年5月20日  
	 * @param taskUid
	 * @param deptTaskUid
	 * @param string
	 */
	@Override
	public void updatePubScentByRelateUid(String taskUid, String deptTaskUid,String relateUserUid) {
		Example example = new Example(PubScent.class);
		List<String> specialCodes = new ArrayList<String>();
		specialCodes.add("A01");
		specialCodes.add("multiple");
		example.createCriteria().andEqualTo("taskUid", taskUid)
		.andEqualTo("relateUserUid", relateUserUid)
		.andNotIn("specialCode", specialCodes); 
		PubScent pubScent = new PubScent();
		pubScent.setDeptTaskUid(deptTaskUid);
		pubScentMapper.updateByExampleSelective(pubScent,example);
	}
	
	/**
	 * 描述: 查询抽检结果中的某个专项库管辖单位
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	@Override
	public List<String> selectRegOrgsInSpecial(String taskUid,String specialCode)throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("taskUid", taskUid);
		params.put("specialCode", specialCode);
		return pubScentMapper.selectRegOrgsInSpecial(params);
	}
	
	/**
	 * 描述: 查询抽中企业的企业类型
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	@Override
	public List<String> selectEntTypeCatg(String taskUid) throws Exception {
		return pubScentMapper.selectEntTypeCatg(taskUid);
	}
	
	/**
	 * 描述: 查询抽检结果中的某个专项库的登记机关数量
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	@Override
	public List<PubScentDto> selectRegOrgNumInSpecial(String taskUid,String specialCode) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("taskUid", taskUid);
		params.put("specialCode", specialCode);
		return pubScentMapper.selectRegOrgNumInSpecial(params);
	}
	
	/**
	 * 描述: 查询抽检结果中的某个专项库的监管用户
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	@Override
	public List<PubScentDto> selectUidsInSpecial(String taskUid, String[] specialCodes)throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("taskUid", taskUid);
		params.put("specialCodes", specialCodes);
		return pubScentMapper.selectUidsInSpecial(params);
	}
	
	/**
	 * 
	 * 描述: 保存抽取结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	@Override
	public void savePubScent(PubScentSpecial pubScentSpecial) {
		if(pubScentSpecial != null){
			PubScent pubScent = new PubScent();
			pubScent.setEntName(pubScentSpecial.getEntName());
			pubScent.setPriPID(pubScentSpecial.getPriPID());
			pubScent.setRegNO(pubScentSpecial.getRegNO());
			pubScent.setUniCode(pubScentSpecial.getUniCode());
			pubScent.setRegState(pubScentSpecial.getRegState());
			pubScent.setRegOrg(pubScentSpecial.getRegOrg());
			pubScent.setRegOrgName(pubScentSpecial.getRegOrgName());
			pubScent.setEntTypeCatg(pubScentSpecial.getEntTypeCatg());
			pubScent.setLocalAdm(pubScentSpecial.getLocalAdm());
			pubScent.setLocalAdmName(pubScentSpecial.getLocalAdmName());
			pubScent.setSpecialCode(pubScentSpecial.getSpecialCode());
			pubScent.setSpecialName(pubScentSpecial.getSpecialName());
			pubScent.setEstDate(pubScentSpecial.getEstDate());
			pubScent.setTaskUid(pubScentSpecial.getTaskUid());
			pubScent.setRelateUserUid(pubScentSpecial.getRelateUserUid());
			pubScentMapper.insertSelective(pubScent);
		}
	}
	
	/**
	 * 
	 * 描述: 通过任务id查询已抽取的企业各地市数量
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubScentDto> selectPubSentDtoListBytaskUId(String taskUid)throws Exception {
		List<PubScentDto> pubScentDtoList =  pubScentMapper.selectPubSentDtoListBytaskUId(taskUid);
		List<PubScentDto> pubScentDtoOrderList = orderBy(pubScentDtoList);
		return pubScentDtoOrderList;
	}
	
	/**
	 * 描述：生成地图数据
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScentDtoList
	 * @return
	 */
	private List<PubScentDto> orderBy(List<PubScentDto> pubScentDtoList) {
		List<PubScentDto> pubScentDtoOrderList = new ArrayList<PubScentDto>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(PubScentDto pubScentDto : pubScentDtoList){
			if(StringUtils.isNotEmpty(pubScentDto.getRegOrg())){
				map.put(pubScentDto.getRegOrg(), pubScentDto.getSpecialNum());
			}
		}
		PubScentDto pubScentDto = getPubScentDto("3300", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3305", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3301", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3307", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3308", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3325", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3304", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3306", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3309", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3302", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3310", map);
		pubScentDtoOrderList.add(pubScentDto);
		pubScentDto = getPubScentDto("3303", map);
		pubScentDtoOrderList.add(pubScentDto);
		return pubScentDtoOrderList;
	}
	
	/**
	 * 描述:按照页面排序生成数据
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param regOrg
	 * @param map
	 * @return
	 */
	private PubScentDto getPubScentDto(String regOrg,Map<String,Integer> map){
		PubScentDto pubScentDto = new PubScentDto();
		pubScentDto.setRegOrg(regOrg);
		int num = 0;
		if(map.get(regOrg) != null && map.get(regOrg) > 0){
			num = map.get(regOrg);
		}
		pubScentDto.setSpecialNum(num);
		return pubScentDto;
	}

	/**
	 * 
	 * 描述: 通过查询已抽取的企业进行滚动
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PubScent> selectPubSentListBytaskUId(String taskUid)throws Exception {
		return pubScentMapper.selectPubSentListBytaskUId(taskUid);
	}
	
	/**
	 * 描述：只用来处理人工分配抽查任务
	 * @param taskUid
	 * @param entName
	 * @return
	 */
	@Override
	public PubScent selectEntByEntNameTaskUid(String taskUid, String entName) {
		Example example = new Example(PubScent.class);
		example.createCriteria().andEqualTo("taskUid", taskUid).andEqualTo("entName", entName); 
		return pubScentMapper.selectByExample(example).get(0);
	}
}