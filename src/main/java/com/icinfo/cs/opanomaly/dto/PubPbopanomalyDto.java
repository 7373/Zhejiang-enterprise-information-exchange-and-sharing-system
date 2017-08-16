/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.opanomaly.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;

/**
 * 描述:    cs_pub_pbopanomaly 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2016年10月08日
 */
@SuppressWarnings("serial")
public class PubPbopanomalyDto extends PubPbopanomaly                {
	
	//登记机关名称
	private String regOrgName;
	//管辖单位名称
	private String localAdmName;
	//成立日期
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date estDate; 
	//住所
	private String dom;
    //责任区名称
    private String sliceNOName; 
	
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	 
	public Date getEstDate() {
		return estDate;
	}
	public void setEstDate(Date estDate) {
		this.estDate = estDate;
	}
	public String getRegOrgName() {
		return regOrgName;
	}
	public void setRegOrgName(String regOrgName) {
		this.regOrgName = regOrgName;
	}
	public String getLocalAdmName() {
		return localAdmName;
	}
	public void setLocalAdmName(String localAdmName) {
		this.localAdmName = localAdmName;
	}
	public String getSliceNOName() {
		return sliceNOName;
	}
	public void setSliceNOName(String sliceNOName) {
		this.sliceNOName = sliceNOName;
	} 
}