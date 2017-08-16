/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.sment.dto.EntarchivesDto;
import com.icinfo.cs.sment.dto.SmBaseinfoDto;
import com.icinfo.cs.sment.model.EntarchivesAdjust;
import com.icinfo.cs.sment.model.EntarchivesHelp;
import com.icinfo.cs.sment.model.EntarchivesOperate;
import com.icinfo.cs.sment.service.IEntarchivesAdjustService;
import com.icinfo.cs.sment.service.IEntarchivesHelpService;
import com.icinfo.cs.sment.service.IEntarchivesOperateService;
import com.icinfo.cs.sment.service.IEntarchivesService;
import com.icinfo.cs.sment.service.ISmBaseinfoService;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.cs.yr.model.YrAsset;
import com.icinfo.cs.yr.service.IYrAssetService;
import com.icinfo.framework.common.ajax.AjaxResult;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.framework.core.web.annotation.RepeatSubmit;

/**
 * 描述:   小微企业培育档案库 sm_bus_entarchives 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2017年04月27日
 */
@Controller
@RequestMapping("/sment/entarchives")
public class EntarchivesController extends BaseController {
    
    @Autowired
    private IEntarchivesService entarchivesService;
    @Autowired
    private ISmBaseinfoService smBaseinfoService;
    @Autowired
    private IYrAssetService yrAssetService;
    @Autowired
    private IEntarchivesOperateService entarchivesOperateService;
    @Autowired
    private IEntarchivesHelpService entarchivesHelpService;
    @Autowired
    private IEntarchivesAdjustService entarchivesAdjustService;
    
    
    
    
    /** 
     * 描述: 获取登录用户信息
     * @author 张文男
     * @date 2017年5月8日 
     * @param session
     * @return 
     */
    
    @RequestMapping(value = "queryLoginUserInfo.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryLoginUserInfo(HttpSession session){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            return AjaxResult.success ("操作成功！", sysUser);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 根据企业id获取对应调整记录
     * @author 张文男
     * @date 2017年5月8日 
     * @param session
     * @param priPID
     * @return 
     */
    
    @RequestMapping(value = "queryEntarchivesAdjustListByPriPID.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryEntarchivesAdjustListByPriPID(HttpSession session,String priPID){
        try {
            List<EntarchivesAdjust> list = entarchivesAdjustService.queryEntarchivesAdjustListBy (priPID);
            return AjaxResult.success ("操作成功！", list);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 根据企业id、年份获取对应数据
     * @author 张文男
     * @date 2017年5月8日 
     * @param session
     * @param priPID
     * @param year
     * @return 
     */
    
    @RequestMapping(value = "queryEntarchivesHelpListByByPriPIDAndYear.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryEntarchivesHelpListByByPriPIDAndYear(HttpSession session,String priPID,int year){
        try {
            List<EntarchivesHelp> list = entarchivesHelpService.queryEntarchivesHelpListBy (priPID, year);
            return AjaxResult.success ("操作成功！", list);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 根据企业id、年份获取对应数据
     * @author 张文男
     * @date 2017年5月8日 
     * @param session
     * @param priPID
     * @param year
     * @return 
     */
    
    @RequestMapping(value = "queryEntarchivesOperateByPriPIDAndYear.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryEntarchivesOperateByPriPIDAndYear(HttpSession session,String priPID,int year){
        try {
            EntarchivesOperate data =  entarchivesOperateService.queryEntarchivesOperateBy (priPID, year);
            return AjaxResult.success ("操作成功！", data);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 根据企业id获取企业基本信息
     * @author 张文男
     * @date 2017年5月5日 
     * @param session
     * @param pripid
     * @return 
     */
    
    @RequestMapping(value = "queryEntInfoByPriPID.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryEntInfoByPriPID(HttpSession session,String pripid){
        try {
            SmBaseinfoDto data = smBaseinfoService.queryBeanByPriPID (pripid);
            return AjaxResult.success ("操作成功！", data);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 根据年份、企业id获取年报信息
     * @author 张文男
     * @date 2017年5月5日 
     * @param session
     * @param pripid
     * @param year
     * @return 
     */
    
    @RequestMapping(value = "queryYrAssetByPriPIDAndYear.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryYrAssetByPriPIDAndYear(HttpSession session,String pripid,Integer year){
        try {
            YrAsset data = yrAssetService.selectYrAssetByYearAndPripid (year, pripid);
            return AjaxResult.success ("操作成功！", data);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 跳转至企业列表页面
     * @author 张文男
     * @date 2017年5月2日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesEntlist")
    public String toEntarchivesEntlist() throws Exception{
        return "sment/entarchives/entarchives_entlist";
    }
    
    /** 
     * 描述: 跳转至培育库列表页面
     * @author 张文男
     * @date 2017年5月4日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesList")
    public String toEntarchivesList() throws Exception{
        return "sment/entarchives/entarchives_list";
    }
    
    
    /** 
     * 描述: 跳转至企业批量新增页面
     * @author 张文男
     * @date 2017年5月2日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesBatchAdd")
    public String toEntarchivesBatchAdd() throws Exception{
        return "sment/entarchives/entarchives_batch_add";
    }
    
    /** 
     * 描述: 跳转至企业编辑页面
     * @author 张文男
     * @date 2017年5月2日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesEdit")
    public String toEntarchivesEdit() throws Exception{
        return "sment/entarchives/entarchives_edit";
    }
    
    
    /** 
     * 描述: 跳转至企业查看页面
     * @author 张文男
     * @date 2017年5月9日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesView")
    public String toEntarchivesView() throws Exception{
        return "sment/entarchives/entarchives_view";
    }
    
    /** 
     * 描述: 跳转至企业跟踪页面
     * @author 张文男
     * @date 2017年5月9日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesFollow")
    public String toEntarchivesFollow() throws Exception{
        return "sment/entarchives/entarchives_follow";
    }
    
    
    /** 
     * 描述: 跳转至企业移除页面
     * @author 张文男
     * @date 2017年5月5日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesRemove")
    public String toEntarchivesRemove() throws Exception{
        return "sment/entarchives/entarchives_remove";
    }
    
    /** 
     * 描述: 跳转至企业帮扶页面
     * @author 张文男
     * @date 2017年5月3日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesHelpEdit")
    public String toEntarchivesHelpEdit() throws Exception{
        return "sment/entarchives/entarchives_help_edit";
    }
    
    /** 
     * 描述: 跳转至企业帮扶页面
     * @author 张文男
     * @date 2017年5月5日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesHelpTypeTree")
    public String toEntarchivesHelpTypeTree() throws Exception{
        return "sment/entarchives/entarchives_helptype_tree";
    }
    
    /** 
     * 描述: 跳转至企业区域树页面
     * @author 张文男
     * @date 2017年5月5日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesAreaTree")
    public String toEntarchivesAreaTree() throws Exception{
        return "sment/entarchives/entarchives_area_tree";
    }
    
    /** 
     * 描述: 跳转至企业区域树-一级页面
     * @author 张文男
     * @date 2017年5月8日 
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("toEntarchivesAreaSingleTree")
    public String toEntarchivesAreaSingleTree() throws Exception{
        return "sment/entarchives/entarchives_area_singletree";
    }
    
    
    /** 
     * 描述: 添加修改培育库
     * @author 张文男
     * @date 2017年5月5日 
     * @param session
     * @param bean
     * @return 
     */
    
    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    @RepeatSubmit
    public AjaxResult saveOrUpdate(HttpSession session,EntarchivesDto bean){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            entarchivesService.saveOrUpdate (bean,sysUser);
            // 警示协同的地址 默认警示
            return AjaxResult.success ("操作成功！");
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 添加修改培育库-帮扶信息
     * @author 张文男
     * @date 2017年5月11日 
     * @param session
     * @param bean
     * @return 
     */
    
    @RequestMapping(value = "saveOrUpdateForHelp",method = RequestMethod.POST)
    @ResponseBody
    @RepeatSubmit
    public AjaxResult saveOrUpdateForHelp(HttpSession session,EntarchivesDto bean){
        try {
            // 警示协同的地址 默认警示
            entarchivesService.saveOrUpdateForHelp (bean, true);
            // 警示协同的地址 默认警示
            return AjaxResult.success ("操作成功！");
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 移除培育库
     * @author 张文男
     * @date 2017年5月5日 
     * @param session
     * @param bean
     * @return 
     */
    
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    @ResponseBody
    @RepeatSubmit
    public AjaxResult remove(HttpSession session,EntarchivesAdjust bean){
        try {
            entarchivesService.remove (bean);
            // 警示协同的地址 默认警示
            return AjaxResult.success ("操作成功！");
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 根据priPID获取信息
     * @author 张文男
     * @date 2017年5月5日 
     * @param session
     * @param uid
     * @return 
     */
    
    @RequestMapping(value = "queryByPriPID.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryByPriPID(HttpSession session,String priPID,String isAdd){
        try {
            EntarchivesDto data = entarchivesService.queryEntarchivesDtoByPriPID (priPID);
            if(StringUtils.isNotBlank(isAdd) && "true".equalsIgnoreCase(isAdd)){ // 新增
                if(null == data){
                    data = new EntarchivesDto ();
                    data.setPriPID (priPID);
                }
            	// 初始化 负责人和联络员等信息
            	data = entarchivesService.initEntarchivesDto(data);
            }
            return AjaxResult.success ("操作成功！",data);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    @RequestMapping(value = "count.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult count(HttpSession session,String cultivationTypeCode){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            int count = entarchivesService.count (sysUser, cultivationTypeCode);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 统计帮扶信息
     * @author 张文男
     * @date 2017年6月20日 
     * @param session
     * @param helpState
     * @return 
     */
    
    @RequestMapping(value = "countByHelp.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult countByHelp(HttpSession session,String helpState){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            int count = entarchivesService.countByHelp (sysUser, helpState);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 统计入库企业违法违规数量
     * @author 张文男
     * @date 2017年6月23日 
     * @param session
     * @param helpState
     * @return 
     */
    
    @RequestMapping(value = "countWfwgBySetDeptCode.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult countWfwgBySetDeptCode(HttpSession session,String helpState){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            Long count = entarchivesService.countWfwgBySetDeptCode (sysUser);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }

    /** 
     * 描述:  统计入库企业经营异常数量
     * @author 张文男
     * @date 2017年6月23日 
     * @param session
     * @param helpState
     * @return 
     */
    
    @RequestMapping(value = "countJyycBySetDeptCode.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult countJyycBySetDeptCode(HttpSession session,String helpState){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            Long count = entarchivesService.countJyycBySetDeptCode (sysUser);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    
    /** 
     * 描述: 统计入库企业上规升级数量
     * @author 张文男
     * @date 2017年6月26日 
     * @param session
     * @param helpState
     * @return 
     */
    
    @RequestMapping(value = "countSgsjBySetDeptCode.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult countSgsjBySetDeptCode(HttpSession session,String helpState){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            Long count = entarchivesService.countSgsjBySetDeptCode (sysUser);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
    
    /** 
     * 描述: 统计入库企业经营下降20%数量
     * @author 张文男
     * @date 2017年6月26日 
     * @param session
     * @param helpState
     * @return 
     */
    
    @RequestMapping(value = "countJyxjBySetDeptCode.json",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult countJyxjBySetDeptCode(HttpSession session,String helpState){
        try {
            SysUserDto sysUser = (SysUserDto) session.getAttribute (Constants.SESSION_SYS_USER);
            Long count = entarchivesService.countJyxjBySetDeptCode (sysUser);
           
            return AjaxResult.success ("操作成功！",count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return AjaxResult.error ("操作失败！");
    }
}