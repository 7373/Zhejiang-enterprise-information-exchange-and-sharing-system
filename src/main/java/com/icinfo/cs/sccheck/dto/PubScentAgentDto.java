/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.dto;

import com.icinfo.cs.sccheck.model.PubScentAgent;

/**
 * 描述:    cs_pub_scent_agent 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年05月19日
 */
public class PubScentAgentDto extends PubScentAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177902780725730668L;
	
	//注册号
	private String regNO;
	
	//统一社会信用代码
	private String uniCode;
	
	//企业名称
	private String entName;
	
	//执法人员姓名
	private String agentNames;
	
	//任务编号
	private String taskCode;
	
	//任务名称
	private String taskName;
	
	//任务状态
	private String taskState;
	
	//执法人员姓名 
	private String agentName;
	
	//实施部门审核状态
	private String auditState;

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getAgentNames() {
		return agentNames;
	}

	public void setAgentNames(String agentNames) {
		this.agentNames = agentNames;
	}

	public String getRegNO() {
		return regNO;
	}

	public void setRegNO(String regNO) {
		this.regNO = regNO;
	}

	public String getUniCode() {
		return uniCode;
	}

	public void setUniCode(String uniCode) {
		this.uniCode = uniCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	
}