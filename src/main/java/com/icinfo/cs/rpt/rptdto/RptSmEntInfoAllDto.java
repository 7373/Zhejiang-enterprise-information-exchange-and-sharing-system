/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.rpt.rptdto;

import java.math.BigDecimal;



import scala.math.BigInt;

import com.icinfo.cs.rpt.rptmodel.RptSmEntInfoAll;

/**
 * 描述:    rpt_sm_ent_info_all 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年05月09日
 */
public class RptSmEntInfoAllDto extends RptSmEntInfoAll {
	
	//三大产业
	private String industryCodeName;
	//行业/八大产业
	private String industryCoName;
	//小微企业大类（跟企业小类挂钩）
	private String entTypeCatgName;
	//企业细分类型（小类）
	private String entTypeName;
	//登记机关
	private String regorgName;
	//管辖单位
	private String localAdmName;
	
	private String areaTime;
	
    /**
     * 在册企业数（核准库）
     */
    private BigDecimal newEntAll;

    /**
     * 新设企业数（核准库）
     */
    private BigDecimal canEntAll;
    /**
     *新设小微企业注册资本总额
     */
    private BigDecimal newRegCapAll;
    
    //平均年龄个数
    private BigDecimal  avgAgeCount;

	public String getIndustryCodeName() {
		return industryCodeName;
	}

	public void setIndustryCodeName(String industryCodeName) {
		this.industryCodeName = industryCodeName;
	}

	public String getIndustryCoName() {
		return industryCoName;
	}

	public void setIndustryCoName(String industryCoName) {
		this.industryCoName = industryCoName;
	}

	public String getEntTypeCatgName() {
		return entTypeCatgName;
	}

	public void setEntTypeCatgName(String entTypeCatgName) {
		this.entTypeCatgName = entTypeCatgName;
	}

	public String getEntTypeName() {
		return entTypeName;
	}

	public void setEntTypeName(String entTypeName) {
		this.entTypeName = entTypeName;
	}

	public String getRegorgName() {
		return regorgName;
	}

	public void setRegorgName(String regorgName) {
		this.regorgName = regorgName;
	}

	public String getLocalAdmName() {
		return localAdmName;
	}

	public void setLocalAdmName(String localAdmName) {
		this.localAdmName = localAdmName;
	}

	public BigDecimal getNewEntAll() {
		return newEntAll;
	}

	public void setNewEntAll(BigDecimal newEntAll) {
		this.newEntAll = newEntAll;
	}

	public void setCanEntAll(BigDecimal canEntAll) {
		this.canEntAll = canEntAll;
	}
	public BigDecimal getCanEntAll() {
		return canEntAll;
	}
	
	public void setNewRegCapAll(BigDecimal newRegCapAll) {
		this.newRegCapAll = newRegCapAll;
	}
	public BigDecimal getNewRegCapAll() {
		return newRegCapAll;
	}
    public void setAreaTime(String areaTime) {
		this.areaTime = areaTime;
	}
    public String getAreaTime() {
		return areaTime;
	}
    
    public void setAvgAgeCount(BigDecimal avgAgeCount) {
		this.avgAgeCount = avgAgeCount;
	}
    
    public BigDecimal getAvgAgeCount() {
		return avgAgeCount;
	}
}