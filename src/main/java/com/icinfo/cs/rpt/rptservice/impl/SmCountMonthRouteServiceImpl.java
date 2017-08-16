/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.rpt.rptservice.impl;

import com.alibaba.fastjson.JSONObject;
import com.icinfo.cs.rpt.rptmapper.SmCountMonthRouteMapper;
import com.icinfo.cs.rpt.rptmodel.SmCountMonthRoute;
import com.icinfo.cs.rpt.rptservice.ISmCountMonthRouteService;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:    sm_count_month_route 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2017年06月15日
 */
@Service
public class SmCountMonthRouteServiceImpl extends MyBatisServiceSupport implements ISmCountMonthRouteService {

    @Autowired
    private SmCountMonthRouteMapper smCountMonthRouteMapper;

    @Override
    public JSONObject selectAllByZC() throws Exception {
        Example example = new Example(SmCountMonthRoute.class);
        example.createCriteria().andEqualTo("smflag","ZC");
        example.setOrderByClause("smmonth ASC");
        List<SmCountMonthRoute> monthList = smCountMonthRouteMapper.selectByExample(example);

        List<String> strList = new ArrayList<String>();
        List<Integer> dataList = new ArrayList<Integer>();
        JSONObject obj = new JSONObject();
        for(SmCountMonthRoute mon:monthList){
            strList.add(mon.getSmmonth());
            dataList.add(mon.getSmcount());
        }
        obj.put("dataList", JSONObject.toJSON(dataList));
        obj.put("strList", JSONObject.toJSON(strList));

        return obj;
    }

    @Override
    public JSONObject selectAllByXS() throws Exception {
        Example example = new Example(SmCountMonthRoute.class);
        example.createCriteria().andEqualTo("smflag","XS");
        example.setOrderByClause("smmonth ASC");
        List<SmCountMonthRoute> monthList = smCountMonthRouteMapper.selectByExample(example);

        List<String> strList = new ArrayList<String>();
        List<Integer> dataList = new ArrayList<Integer>();
        JSONObject obj = new JSONObject();
        for(SmCountMonthRoute mon:monthList){
            strList.add(mon.getSmmonth());
            dataList.add(mon.getSmcount());
        }
        obj.put("dataList", JSONObject.toJSON(dataList));
        obj.put("strList", JSONObject.toJSON(strList));

        return obj;
    }
    
    @Override
    public JSONObject selectAllByZX() throws Exception {
        Example example = new Example(SmCountMonthRoute.class);
        example.createCriteria().andEqualTo("smflag","ZX");
        example.setOrderByClause("smmonth ASC");
        List<SmCountMonthRoute> monthList = smCountMonthRouteMapper.selectByExample(example);

        List<String> strList = new ArrayList<String>();
        List<Integer> dataList = new ArrayList<Integer>();
        JSONObject obj = new JSONObject();
        for(SmCountMonthRoute mon:monthList){
            strList.add(mon.getSmmonth());
            dataList.add(mon.getSmcount());
        }
        obj.put("dataList", JSONObject.toJSON(dataList));
        obj.put("strList", JSONObject.toJSON(strList));

        return obj;
    }
}