/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.dto;

import java.util.List;

import com.icinfo.cs.sccheck.model.PubScPlanTask;
import com.icinfo.cs.sccheck.model.PubSccheckItem;

/**
 * 描述:    cs_pub_scplan_task 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
@SuppressWarnings("serial")
public class PubScPlanTaskDto extends PubScPlanTask {
	//检查事项
	private List<PubSccheckItem> pubSccheckItemList;
	
	 /**
     * 任务状态
     */
    private String deptState;

    /**
     * 检查人员数
     */
    private Integer checkNum;

    /**
     * 专家人员数
     */
    private Integer specialNum;

    /**
     * 检查对象数量
     */
    private Integer entNum;
    
    
    /**
     * 检查对象数量
     */
    private Integer minNum;
    
    private String taskUid;
    //地区编码
    private String areaCode;
    //部门编码
    private String deptCode;
    //部门名称
    private String deptName;
    //待执行任务数量
    private Integer taskState01;
    //执行中任务数量
    private Integer taskState02;
    //已完成数量
    private Integer taskState03;
    //临时任务数量
    private Integer taskResource1;
    //任务计划数量
    private Integer taskResource2;
    //总任务数量
    private Integer totalTask;
    
    /**  任务计划维度 */
    //待执行计划数量
    private Integer planState01;
    //执行中计划数量
    private Integer planState02;
    //已完成计划数量
    private Integer planState03;
    //总任务数量
    private Integer totalWork;
    
    /** 检查企业维度 */
    //已检查企业数量
    private Integer finishedNum;
    //后续处置已完结企业数量
	private Integer disposeNum;
	//企业总量
	private Integer totalNum;
	
	

	public List<PubSccheckItem> getPubSccheckItemList() {
		return pubSccheckItemList;
	}

	public void setPubSccheckItemList(List<PubSccheckItem> pubSccheckItemList) {
		this.pubSccheckItemList = pubSccheckItemList;
	}

	public String getDeptState() {
		return deptState;
	}

	public void setDeptState(String deptState) {
		this.deptState = deptState;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public Integer getEntNum() {
		return entNum;
	}

	public void setEntNum(Integer entNum) {
		this.entNum = entNum;
	}
	
	public void setTaskUid(String taskUid) {
		this.taskUid = taskUid;
	}
	public String getTaskUid() {
		return taskUid;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getTaskState01() {
		return taskState01;
	}

	public void setTaskState01(Integer taskState01) {
		this.taskState01 = taskState01;
	}

	public Integer getTaskState02() {
		return taskState02;
	}

	public void setTaskState02(Integer taskState02) {
		this.taskState02 = taskState02;
	}

	public Integer getTaskState03() {
		return taskState03;
	}

	public void setTaskState03(Integer taskState03) {
		this.taskState03 = taskState03;
	}

	public Integer getTotalTask() {
		return totalTask;
	}

	public void setTotalTask(Integer totalTask) {
		this.totalTask = totalTask;
	}

	public Integer getPlanState01() {
		return planState01;
	}

	public void setPlanState01(Integer planState01) {
		this.planState01 = planState01;
	}

	public Integer getPlanState02() {
		return planState02;
	}

	public void setPlanState02(Integer planState02) {
		this.planState02 = planState02;
	}

	public Integer getPlanState03() {
		return planState03;
	}

	public void setPlanState03(Integer planState03) {
		this.planState03 = planState03;
	}

	public Integer getTotalWork() {
		return totalWork;
	}

	public void setTotalWork(Integer totalWork) {
		this.totalWork = totalWork;
	}

	public Integer getFinishedNum() {
		return finishedNum;
	}

	public void setFinishedNum(Integer finishedNum) {
		this.finishedNum = finishedNum;
	}

	public Integer getDisposeNum() {
		return disposeNum;
	}

	public void setDisposeNum(Integer disposeNum) {
		this.disposeNum = disposeNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getTaskResource1() {
		return taskResource1;
	}

	public void setTaskResource1(Integer taskResource1) {
		this.taskResource1 = taskResource1;
	}

	public Integer getTaskResource2() {
		return taskResource2;
	}

	public void setTaskResource2(Integer taskResource2) {
		this.taskResource2 = taskResource2;
	}
}