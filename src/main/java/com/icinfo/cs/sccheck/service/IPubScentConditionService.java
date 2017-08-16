/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service;

import java.util.List;

import com.icinfo.cs.sccheck.dto.PubScentConditionDto;
import com.icinfo.cs.sccheck.model.PubScentCondition;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_scent_condition 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
public interface IPubScentConditionService extends BaseService {

	/**
     * 描述：确认本次检查对象抽取库
     * @author chenxin
     * @date 2017-05-17
     * @param taskUid
     * @param sysUser
     * @return
     * @throws Exception
     */
	public boolean doConfirmEntBack(String taskUid,SysUserDto sysUser)throws Exception;
	
	/**
	 * 描述：确认本次检查对象抽取库条件
	 * @author chenxin
	 * @date 2017-05-17
	 * @param pubScentConditionDto
	 * @return
	 * @throws Exception
	 */
	public boolean doCommitCondition(PubScentConditionDto pubScentConditionDto)throws Exception;

	/**
	 * 描述：统计导入结果
	 * @author chenxin
     * @date 2017-05-17
	 * @param request
	 * @return List<PubScentConditionDto>
	 * @throws Exception
	 */
	public List<PubScentConditionDto> queryPubScentConditionDtoPage(PageRequest request)throws Exception;

	/**
	 * 描述：查询待抽取专项库信息
	 * @author chenxin
     * @date 2017-05-17
	 * @param request
	 * @return List<PubScentConditionDto>
	 * @throws Exception
	 */
	public List<PubScentCondition> selectPubScentConditionList(String taskUid,String randomType);
	
	/**
	 * 描述：查询待抽取专项库信息
	 * @author chenxin
     * @date 2017-05-17
	 * @param request
	 * @return PubScentCondition
	 * @throws Exception
	 */
	public List<PubScentCondition> selectPubScentCondition(String taskUid,String specialCode,String randomType);

	/**
	 * 描述：将待抽检企业按照检查事项分类
	 * @author chenxin
	 * @date 2017-05-17
	 * @param taskUid
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	public boolean doClassifyEntBack(String taskUid,SysUserDto sysUser)throws Exception;
}