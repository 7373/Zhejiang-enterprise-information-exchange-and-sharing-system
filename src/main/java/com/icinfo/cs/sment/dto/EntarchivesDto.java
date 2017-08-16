/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.cs.sment.dto;

import java.util.List;

import com.icinfo.cs.sment.model.Entarchives;
import com.icinfo.cs.sment.model.EntarchivesAdjust;
import com.icinfo.cs.sment.model.EntarchivesHelp;
import com.icinfo.cs.sment.model.EntarchivesOperate;

/**
 * 描述:   小微企业培育档案库 sm_bus_entarchives 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年04月27日
 */
public class EntarchivesDto extends Entarchives {

    private String                priPIDs;
    private Integer               operateYear;
    private Integer               helpYear;
    private EntarchivesOperate    entarchivesOperate;
    private List<EntarchivesHelp> entarchivesHelpList;
    private EntarchivesAdjust     entarchivesAdjust;
    private String                tsxzName;
    private String                kjfhyName;
    private String                kjdsyName;
    private String                qtName;
    private String                zckjName;

    public String getPriPIDs(){
        return priPIDs;
    }

    public void setPriPIDs(String priPIDs){
        this.priPIDs = priPIDs;
    }

    public EntarchivesOperate getEntarchivesOperate(){
        return entarchivesOperate;
    }

    public void setEntarchivesOperate(EntarchivesOperate entarchivesOperate){
        this.entarchivesOperate = entarchivesOperate;
    }

    public List<EntarchivesHelp> getEntarchivesHelpList(){
        return entarchivesHelpList;
    }

    public void setEntarchivesHelpList(List<EntarchivesHelp> entarchivesHelpList){
        this.entarchivesHelpList = entarchivesHelpList;
    }

    public EntarchivesAdjust getEntarchivesAdjust(){
        return entarchivesAdjust;
    }

    public void setEntarchivesAdjust(EntarchivesAdjust entarchivesAdjust){
        this.entarchivesAdjust = entarchivesAdjust;
    }

    public Integer getOperateYear(){
        return operateYear;
    }

    public void setOperateYear(Integer operateYear){
        this.operateYear = operateYear;
    }

    public Integer getHelpYear(){
        return helpYear;
    }

    public void setHelpYear(Integer helpYear){
        this.helpYear = helpYear;
    }

    public String getTsxzName(){
        return tsxzName;
    }

    public void setTsxzName(String tsxzName){
        this.tsxzName = tsxzName;
    }

    public String getKjfhyName(){
        return kjfhyName;
    }

    public void setKjfhyName(String kjfhyName){
        this.kjfhyName = kjfhyName;
    }

    public String getKjdsyName(){
        return kjdsyName;
    }

    public void setKjdsyName(String kjdsyName){
        this.kjdsyName = kjdsyName;
    }

    public String getQtName(){
        return qtName;
    }

    public void setQtName(String qtName){
        this.qtName = qtName;
    }

    
    public String getZckjName(){
        return zckjName;
    }

    
    public void setZckjName(String zckjName){
        this.zckjName = zckjName;
    }

}