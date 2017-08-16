package com.icinfo.cs.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.constant.DBAuthorConstants;
import com.icinfo.cs.common.constant.LogOperation;
import com.icinfo.cs.common.logintoken.CSLoginToken;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.system.controller.CSBaseController;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.cs.system.model.SysUser;
import com.icinfo.cs.system.service.ISysLogService;
import com.icinfo.cs.yr.model.DepartMent;
import com.icinfo.framework.security.shiro.UserProfile;
import com.insigma.odin.framework.ucenter.FunctionDTO;
import com.insigma.odin.framework.ucenter.interf.DpInterface;
import com.icinfo.cs.common.utils.SysUnifiedServiceUtil;

/**  
 * Copyright 2003-2013 浙江汇信科技有限公司, All Rights Reserved <br/>
 * 描述: TODO
 * @author: 赵祥江 
 * @date: 2017年6月8日 下午4:30:14
 * @version: V1.0  
 */
public class DpInterfaceUnifiedUserImpl extends CSBaseController  implements DpInterface {
 	private final static Logger logger = LoggerFactory.getLogger(DpInterfaceUnifiedUserImpl.class);

	@Override
	public Object doOther(HttpServletRequest arg0, ServletResponse arg1,
			ServletContext arg2) throws Exception {
		 
		return null;
	}

	@Override
	public List<FunctionDTO> getTreeNode(HttpServletRequest arg0,
			ServletResponse arg1, ServletContext arg2) throws Exception {
		 
		return null;
	}

	/**
	 * 
	 * 描述   登录
	 * @author  赵祥江
	 * @date 2017年6月8日 下午4:35:09 
	 * @param  
	 * @throws
	 */
	@Override
	public void loginApp(String loginname,HttpServletRequest request,
			ServletResponse servletresponse,ServletContext servCtx) throws Exception {
		//String contextConfig=servCtx.getInitParameter("contextConfigLocation").replaceAll("classpath:", "/");  
		SysUnifiedServiceUtil sysUnifiedServiceUtil=new SysUnifiedServiceUtil(); 
		//根据用户名获取用户
		SysUser sysUserTem=sysUnifiedServiceUtil.getSysUserService().selectByLoginName(loginname.trim(), DBAuthorConstants.USER_TYPE_REG); 
		SysUserDto sysUser=null;
		UserProfile userProfile=null;
		HttpSession  session =request.getSession();
		if(sysUserTem!=null){
			 //修改最近登录时间
		     sysUnifiedServiceUtil.getSysUserService().modLastLoginTime(sysUserTem.getId());
        	 try {
                 CSLoginToken loginToken = new CSLoginToken(sysUserTem.getUsername(), "123456");
                 loginToken.setUserType(DBAuthorConstants.USER_TYPE_REG);
                 Subject subject = SecurityUtils.getSubject();
                 subject.login(loginToken);
                 session.setAttribute(Constants.SESSION_SYS_USER_KEY, subject.getPrincipal());
                 userProfile = (UserProfile)subject.getPrincipal();
                 sysUser = sysUnifiedServiceUtil.getSysUserService().selectUserByUId(userProfile.getUserId());
                 request.setAttribute("menus",userProfile==null?"":userProfile.getMenus());
                 request.setAttribute("userProfile", userProfile);
                 DepartMent departMent=(sysUser.getDepartMent()==null)?sysUnifiedServiceUtil.getDepartMentService().selectOne(sysUser.getDeptCode()):sysUnifiedServiceUtil.getDepartMentService().selectOne(sysUser.getDepartMent().getDeptCode());
    			 sysUser.setDepartMent(departMent);
                 sysUser.setDeptCode(departMent.getDeptCode());
                 session.setAttribute(Constants.SESSION_SYS_USER, sysUser); 
                 session.setAttribute(Constants.SESSION_SYS_USER_KEY, userProfile);
             } catch (Exception e) {
                e.printStackTrace();
             }
			 try {
					/*Map<String, Object> forBidmap = new HashMap<String, Object>();
					creatOptDBAuthEnv(forBidmap,"t.CheckDep","t.LocalAdm");
					List<Integer> countList = sysUnifiedServiceUtil.getRegIndexService().getCount(forBidmap);
					sysUnifiedServiceUtil.getTokenManager().setRegCountToken(sysUser.getId(), countList);*/
					saveUnifiedLog(sysUser ,request);
				} catch (Exception e) {
					e.printStackTrace();
		            logger.info("缓存初始化失败！"+e.getMessage());
				} 
		}
	}

	@Override
	public void logoutApp(HttpServletRequest arg0, ServletResponse arg1,
			ServletContext arg2) throws Exception {
		 
		
	}
	
	
	 /**
     * 
     * 描述   保存统一用户登录日志
     * @author 赵祥江
     * @date 2017年6月6日 上午10:02:14 
     * @param 
     * @return void
     * @throws
     */
     private void saveUnifiedLog(SysUserDto sysUser,HttpServletRequest req){
         try{
        	 SysUnifiedServiceUtil sysUnifiedServiceUtil=new SysUnifiedServiceUtil(); 
             Map<String,String> logMap=new HashMap<String,String>();
             String msgContent="警示系统统一用户登录日志:登录用户名【"+sysUser.getUsername()+"】 姓名:【"+sysUser.getRealName()+"】  所属部门：【"+sysUser.getDepartMent().getDeptName()+"】";
             logMap.put(ISysLogService.LOG_MAP_KEY_YEAR, DateUtil.getYear());//年份
             logMap.put(ISysLogService.LOG_MAP_KEY_LOGTYPE,ISysLogService.LOG_TYPE_SERVER);//警示系统
             logMap.put(ISysLogService.LOG_MAP_KEY_LOGMSG, msgContent);//日志内容
             logMap.put(ISysLogService.LOG_MAP_KEY_LOGOPERRATION, LogOperation.unifiedLoginLog.getCode());//操作类型-统一用户登录
             sysUnifiedServiceUtil.getSysLogService().doAddSysLog(logMap,req);
         }catch(Exception e){
             logger.error("记录警示系统登录日志时出现异常:"+e.getMessage());
         }
     } 
}
