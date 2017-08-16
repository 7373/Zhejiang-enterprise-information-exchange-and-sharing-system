/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service;

import java.util.List;

import com.icinfo.cs.sccheck.dto.PubScentDto;
import com.icinfo.cs.sccheck.model.PubScent;
import com.icinfo.cs.sccheck.model.PubScentBack;
import com.icinfo.cs.sccheck.model.PubScentSpecial;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_scent 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
public interface IPubScentService extends BaseService {
	
	/**
	 * 
	 * 描述: 通过任务id查询已抽取的企业
	 * @auther gaojinling
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	public List<PubScent> selectPubSentByDeptTaskUId(String deptTaskUid) throws Exception;
	
	/**
	 * 
	 * 描述: 通过任务id查询已抽取的企业
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	public List<PubScentDto> selectPubSentDtoListBytaskUId(String taskUid) throws Exception;
	

	/**
	 * 
	 * 描述: 通过查询已抽取的企业进行滚动
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param deptTaskUid
	 * @return
	 * @throws Exception
	 */
	public List<PubScent> selectPubSentListBytaskUId(String taskUid) throws Exception;
	
	/**
	 * 
	 * 描述: 通过部门任务id分页查询已抽取的企业
	 * @auther gaojinling
	 * @date 2017年5月20日 
	 * @param request
	 * @return
	 */
	public List<PubScentDto> selectPubScentDtoList(PageRequest request)  throws Exception ;
	
	/**
	 * 
	 * 描述: 保存抽取结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	public void savePubScent(PubScentBack pubScentBack)  throws Exception ;
	
	/**
	 * 
	 * 描述: 删除抽检结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	public void delPubScent(String taskUid)  throws Exception ;

	/**
	 * 
	 * 描述: 企业随机分配
	 * @auther chenxin
	 * @date 2017年5月20日  
	 * @param taskUid
	 * @param deptTaskUid
	 * @param string
	 */
	public void updatePubScent(String taskUid, String deptTaskUid, String specialCode);
	
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
	public void updatePubScent(String taskUid, String deptTaskUid, String specialCode,String regOrg);
	
	/**
	 * 
	 * 描述: 企业随机分配
	 * @auther chenxin
	 * @date 2017年5月20日  
	 * @param taskUid
	 * @param deptTaskUid
	 * @param relateUserUid
	 * @param specialCodes
	 * @param string
	 */
	public void updatePubScentByRelateUid(String taskUid, String deptTaskUid, String relateUserUid);

	/**
	 * 描述: 查询抽检结果中的某个专项库的登记机关数量
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	public List<String> selectRegOrgsInSpecial(String taskUid,String specialCode)throws Exception;
	
	/**
	 * 描述: 查询抽中企业的企业类型
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	public List<String> selectEntTypeCatg(String taskUid)throws Exception;
	
	/**
	 * 描述: 查询抽检结果中的某个专项库的登记机关数量
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	public List<PubScentDto> selectRegOrgNumInSpecial(String taskUid,String specialCode)throws Exception;
	/**
	 * 描述: 查询抽检结果中的某个专项库的监管用户
	 * @auther chenxin
	 * @date 2017年5月20日
	 * @param taskUid
	 * @param specialCode
	 * @return
	 */
	public List<PubScentDto> selectUidsInSpecial(String taskUid,String[] specialCodes)throws Exception;

	/**
	 * 
	 * 描述: 保存抽取结果
	 * @auther chenxin
	 * @date 2017年5月20日 
	 * @param pubScent
	 * @return
	 */
	public void savePubScent(PubScentSpecial pubScentSpecial);

	/**
	 * 描述：只用来处理人工分配抽查任务
	 * @param taskUid
	 * @param entName
	 * @return
	 */
	public PubScent selectEntByEntNameTaskUid(String taskUid, String entName);
}