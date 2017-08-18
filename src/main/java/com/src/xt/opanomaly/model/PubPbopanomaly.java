/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.cs.opanomaly.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.framework.mybatis.mapper.annotation.Before;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 描述:    cs_pub_pbopanomaly 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年10月08日
 */
@Table(name = "cs_pub_pbopanomaly")
public class PubPbopanomaly implements Serializable {
    /**
     * 自增序号
     */
    @Id
    @Column(name = "id")
   private Integer id;

    /**
     * 主体身份代码
     */
    @Column(name = "PriPID")
    private String priPID;

    /**
     * 名称
     */
    @Column(name = "EntName")
    private String entName;

    /**
     * 注册号
     */
    @Column(name = "RegNO")
    private String regNO;

    /**
     * 统一社会信用代码
     */
    @Column(name = "UniSCID")
    private String uniSCID;

    /**
     * 经营者
     */
    @Column(name = "LeRep")
    private String leRep;

    /**
     * 证件类型
     */
    @Column(name = "CerType")
    private String cerType;

    /**
     * 证件号码
     */
    @Column(name = "CerNO")
    private String cerNO;

    /**
     * 登记机关
     */
    @Column(name = "RegOrg")
    private String regOrg;

    /**
     * 属地监管工商所
     */
    @Column(name = "LocalAdm")
    private String localAdm;

    /**
     * 标记决定机关
     */
    @Column(name = "DecOrg")
    private String decOrg;

    /**
     * 标记决定机关（中文名称）
     */
    @Column(name = "DecorgCN")
    private String decorgCN;

    /**
     * 是否恢复 1-已标记，0-已恢复
     */
    @Column(name = "IsRecovery")
    private String isRecovery;

    /**
     * 恢复正常记载状态原因
     */
    @Column(name = "NorRea")
    private String norRea;

    /**
     * 恢复正常记载状态原因（中文名称）
     */
    @Column(name = "NorReaCN")
    private String norReaCN;

    /**
     * 恢复决定机关（中文名称）
     */
    @Column(name = "NorDecOrgCN")
    private String norDecOrgCN;

    /**
     * 恢复决定机关
     */
    @Column(name = "NorDecOrg")
    private String norDecOrg;

    /**
     * 恢复日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Column(name = "NorDate")
    private Date norDate;

    /**
     * 年度
     */
    @Column(name = "Year")
    private Integer year;

    /**
     * 标记事实和事由
     */
    @Column(name = "SignRea")
    private String signRea;

    /**
     * 设置人
     */
    @Column(name = "SetName")
    private String setName;

    /**
     * 恢复事实和理由
     */
    @Column(name = "RecoverRea")
    private String recoverRea;

    /**
     * 恢复设置人
     */
    @Column(name = "ResetName")
    private String resetName;

    /**
     * 系统录入上报情况 1-已补报，0-未上报
     */
    @Column(name = "PBState")
    private String PBState;

    /**
     * 信息来源 1-工商录入，0-系统录入
     */
    @Column(name = "PBSource")
    private String PBSource;

    /**
     * 时间戳
     */
    @Column(name = "CreateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 标记日期
     */
    @Column(name = "CogDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date cogDate;

    /**
     * 标记经营异常状态原因
     */
    @Column(name = "ExcpStaRes")
    private String excpStaRes;

    /**
     * 标记经营异常状态原因（中文名称）
     */
    @Column(name = "ExcpStaResCN")
    private String excpStaResCN;

    /**
     * 序号：UUID
     */
    @Column(name = "BusExcList")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String busExcList; 

    private static final long serialVersionUID = 1L;

    /**
     * 获取自增序号
     *
     * @return id - 自增序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增序号
     *
     * @param id 自增序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取主体身份代码
     *
     * @return PriPID - 主体身份代码
     */
    public String getPriPID() {
        return priPID;
    }

    /**
     * 设置主体身份代码
     *
     * @param priPID 主体身份代码
     */
    public void setPriPID(String priPID) {
        this.priPID = priPID;
    }

    /**
     * 获取名称
     *
     * @return EntName - 名称
     */
    public String getEntName() {
        return entName;
    }

    /**
     * 设置名称
     *
     * @param entName 名称
     */
    public void setEntName(String entName) {
        this.entName = entName;
    }

    /**
     * 获取注册号
     *
     * @return RegNO - 注册号
     */
    public String getRegNO() {
        return regNO;
    }

    /**
     * 设置注册号
     *
     * @param regNO 注册号
     */
    public void setRegNO(String regNO) {
        this.regNO = regNO;
    }

     

    public String getUniSCID() {
		return uniSCID;
	}

	public void setUniSCID(String uniSCID) {
		this.uniSCID = uniSCID;
	}

	/**
     * 获取经营者
     *
     * @return LeRep - 经营者
     */
    public String getLeRep() {
        return leRep;
    }

    /**
     * 设置经营者
     *
     * @param leRep 经营者
     */
    public void setLeRep(String leRep) {
        this.leRep = leRep;
    }

    /**
     * 获取证件类型
     *
     * @return CerType - 证件类型
     */
    public String getCerType() {
        return cerType;
    }

    /**
     * 设置证件类型
     *
     * @param cerType 证件类型
     */
    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    /**
     * 获取证件号码
     *
     * @return CerNO - 证件号码
     */
    public String getCerNO() {
        return cerNO;
    }

    /**
     * 设置证件号码
     *
     * @param cerNO 证件号码
     */
    public void setCerNO(String cerNO) {
        this.cerNO = cerNO;
    }

    /**
     * 获取登记机关
     *
     * @return RegOrg - 登记机关
     */
    public String getRegOrg() {
        return regOrg;
    }

    /**
     * 设置登记机关
     *
     * @param regOrg 登记机关
     */
    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    /**
     * 获取属地监管工商所
     *
     * @return LocalAdm - 属地监管工商所
     */
    public String getLocalAdm() {
        return localAdm;
    }

    /**
     * 设置属地监管工商所
     *
     * @param localAdm 属地监管工商所
     */
    public void setLocalAdm(String localAdm) {
        this.localAdm = localAdm;
    }

    /**
     * 获取标记决定机关
     *
     * @return DecOrg - 标记决定机关
     */
    public String getDecOrg() {
        return decOrg;
    }

    /**
     * 设置标记决定机关
     *
     * @param decOrg 标记决定机关
     */
    public void setDecOrg(String decOrg) {
        this.decOrg = decOrg;
    }

    /**
     * 获取标记决定机关（中文名称）
     *
     * @return DecorgCN - 标记决定机关（中文名称）
     */
    public String getDecorgCN() {
        return decorgCN;
    }

    /**
     * 设置标记决定机关（中文名称）
     *
     * @param decorgCN 标记决定机关（中文名称）
     */
    public void setDecorgCN(String decorgCN) {
        this.decorgCN = decorgCN;
    }

    /**
     * 获取是否恢复 1-已标记，0-已恢复
     *
     * @return IsRecovery - 是否恢复 1-已标记，0-已恢复
     */
    public String getIsRecovery() {
        return isRecovery;
    }

    /**
     * 设置是否恢复 1-已标记，0-已恢复
     *
     * @param isRecovery 是否恢复 1-已标记，0-已恢复
     */
    public void setIsRecovery(String isRecovery) {
        this.isRecovery = isRecovery;
    }

    /**
     * 获取恢复正常记载状态原因
     *
     * @return NorRea - 恢复正常记载状态原因
     */
    public String getNorRea() {
        return norRea;
    }

    /**
     * 设置恢复正常记载状态原因
     *
     * @param norRea 恢复正常记载状态原因
     */
    public void setNorRea(String norRea) {
        this.norRea = norRea;
    }

    /**
     * 获取恢复正常记载状态原因（中文名称）
     *
     * @return NorReaCN - 恢复正常记载状态原因（中文名称）
     */
    public String getNorReaCN() {
        return norReaCN;
    }

    /**
     * 设置恢复正常记载状态原因（中文名称）
     *
     * @param norReaCN 恢复正常记载状态原因（中文名称）
     */
    public void setNorReaCN(String norReaCN) {
        this.norReaCN = norReaCN;
    }

    /**
     * 获取恢复决定机关（中文名称）
     *
     * @return NorDecOrgCN - 恢复决定机关（中文名称）
     */
    public String getNorDecOrgCN() {
        return norDecOrgCN;
    }

    /**
     * 设置恢复决定机关（中文名称）
     *
     * @param norDecOrgCN 恢复决定机关（中文名称）
     */
    public void setNorDecOrgCN(String norDecOrgCN) {
        this.norDecOrgCN = norDecOrgCN;
    }

    /**
     * 获取恢复决定机关
     *
     * @return NorDecOrg - 恢复决定机关
     */
    public String getNorDecOrg() {
        return norDecOrg;
    }

    /**
     * 设置恢复决定机关
     *
     * @param norDecOrg 恢复决定机关
     */
    public void setNorDecOrg(String norDecOrg) {
        this.norDecOrg = norDecOrg;
    }

    /**
     * 获取恢复日期
     *
     * @return NorDate - 恢复日期
     */
    public Date getNorDate() {
        return norDate;
    }

    /**
     * 设置恢复日期
     *
     * @param norDate 恢复日期
     */
    public void setNorDate(Date norDate) {
        this.norDate = norDate;
    }

    /**
     * 获取年度
     *
     * @return Year - 年度
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置年度
     *
     * @param year 年度
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 获取标记事实和事由
     *
     * @return SignRea - 标记事实和事由
     */
    public String getSignRea() {
        return signRea;
    }

    /**
     * 设置标记事实和事由
     *
     * @param signRea 标记事实和事由
     */
    public void setSignRea(String signRea) {
        this.signRea = signRea;
    }

    /**
     * 获取设置人
     *
     * @return SetName - 设置人
     */
    public String getSetName() {
        return setName;
    }

    /**
     * 设置设置人
     *
     * @param setName 设置人
     */
    public void setSetName(String setName) {
        this.setName = setName;
    }

    /**
     * 获取恢复事实和理由
     *
     * @return RecoverRea - 恢复事实和理由
     */
    public String getRecoverRea() {
        return recoverRea;
    }

    /**
     * 设置恢复事实和理由
     *
     * @param recoverRea 恢复事实和理由
     */
    public void setRecoverRea(String recoverRea) {
        this.recoverRea = recoverRea;
    }

    /**
     * 获取恢复设置人
     *
     * @return ResetName - 恢复设置人
     */
    public String getResetName() {
        return resetName;
    }

    /**
     * 设置恢复设置人
     *
     * @param resetName 恢复设置人
     */
    public void setResetName(String resetName) {
        this.resetName = resetName;
    }

	public void setPBSource(String pBSource) {
		PBSource = pBSource;
	}
	
	public String getPBSource() {
		return PBSource;
	}
	
	public void setPBState(String pBState) {
		PBState = pBState;
	}
	
	public String getPBState() {
		return PBState;
	}

    /**
     * 获取时间戳
     *
     * @return CreateTime - 时间戳
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间戳
     *
     * @param createTime 时间戳
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取标记日期
     *
     * @return CogDate - 标记日期
     */
    public Date getCogDate() {
        return cogDate;
    }

    /**
     * 设置标记日期
     *
     * @param cogDate 标记日期
     */
    public void setCogDate(Date cogDate) {
        this.cogDate = cogDate;
    }

    /**
     * 获取标记经营异常状态原因
     *
     * @return ExcpStaRes - 标记经营异常状态原因
     */
    public String getExcpStaRes() {
        return excpStaRes;
    }

    /**
     * 设置标记经营异常状态原因
     *
     * @param excpStaRes 标记经营异常状态原因
     */
    public void setExcpStaRes(String excpStaRes) {
        this.excpStaRes = excpStaRes;
    }

    /**
     * 获取标记经营异常状态原因（中文名称）
     *
     * @return ExcpStaResCN - 标记经营异常状态原因（中文名称）
     */
    public String getExcpStaResCN() {
        return excpStaResCN;
    }

    /**
     * 设置标记经营异常状态原因（中文名称）
     *
     * @param excpStaResCN 标记经营异常状态原因（中文名称）
     */
    public void setExcpStaResCN(String excpStaResCN) {
        this.excpStaResCN = excpStaResCN;
    }

    /**
     * 获取序号：UUID
     *
     * @return BusExcList - 序号：UUID
     */
    public String getBusExcList() {
        return busExcList;
    }

    /**
     * 设置序号：UUID
     *
     * @param busExcList 序号：UUID
     */
    public void setBusExcList(String busExcList) {
        this.busExcList = busExcList;
    } 
}