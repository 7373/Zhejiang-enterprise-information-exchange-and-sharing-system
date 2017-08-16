/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.dto;

import com.icinfo.cs.sccheck.model.PubScent;

/**
 * 描述:    cs_pub_scent 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
public class PubScentDto extends PubScent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -280500812222632326L;
	private Integer specialNum;
	
	private String agentNames;

	public String getAgentNames() {
		return agentNames;
	}

	public void setAgentNames(String agentNames) {
		this.agentNames = agentNames;
	}

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}
	
	
}