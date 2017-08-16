<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>随机抽取执法人员</title>
    <link rel="stylesheet" href="/css/syn.css">
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
</head>
<body class="pd10">
<form id="paramForm"><input type="hidden" value="${pubScPlanTaskDto.uid }" name="deptTaskUid" id="taskUid"/><!-- 部门任务表uid --></form>
<input type="hidden" value="${sessionDeptCode }" name="deptCode" id="deptCode"/> <!-- 当前用户orgcoding编码 -->
<input type="hidden" value="${pubScPlanTaskDto.taskUid }" name="uid" id="uid"/> <!-- 当前任务uid -->
<h5 class="h5-title center">任务名称：
     <input type="text" class="ipt-txt" readonly="readonly" value="${pubScPlanTaskDto.taskName} "  style="width: 400px; box-sizing: border-box; padding: 1px 3px;line-height: 28px;height: 30px;font: 13.3333px Arial;"/>
     <a href="javascript:void(0);" style="color:blue;" class="view">详情</a>
</h5>
<h5 class="h5-title">抽查对象名单</h5>
 <div id="waitrandom-table">
<table border="0" id="info-table" cellspacing="0" cellpadding="0" class="table-row perc-100 nowrap">
        <thead>
        <tr>
            <th>序号</th>
            <th>统一信用代码/注册号</th>
            <th>企业名称</th>
            <th>成立日期</th>
            <th>主体类别</th>
            <th>上次抽查日期</th>
            <th>所属专项库</th>
            <th>登记机关</th>
            <th>管辖单位</th>
        </tr>
        </thead>
    </table>
</div>

 <div class="clearfix mb5" id="waitrandom">
     <h5 class="fl mr10 h5-title">待选库</h5>
     <c:if test="${pubScPlanTaskDto.deptState != '2'}">
    	<p class="fl"><input type="button" class="btn btn-sm" id="addAgentId" value="添加检查人员">
    	<input type="button" class="btn btn-sm" id="delete" value="删除检查人员">
    	<input type="button" class="btn btn-sm" id="deleteAll" value="清空人员库">
    	</p>
     </c:if>
 </div>
 <div class="clearfix mb5 center">
 <h5>
     <input type="text" class="ipt-txt" id="agentName" placeholder="请输入人员姓名" style="width: 400px; box-sizing: border-box; padding: 1px 3px;line-height: 28px;height: 30px;font: 13.3333px Arial;"/>
     <input type="button" class="btn btn-sm" id="searchChecker" value="查询">
 </h5>
 </div>
 
 <div id="waitrandom-table">
     <input type="hidden" value="" id="clientNameCount"/> <!-- 当前用户orgcoding编码 -->
     <table  border="0" cellspacing="0" cellpadding="0" class="table-row perc-100 mt30 mb10 nowrap" id="backTableId">
         <thead>
          <tr>
              <th width="5%">序号</th>
              <th width="5%">全选<input type="checkbox" id="checkAll"/></th>
              <th width="8%">姓名</th>
              <th width="8%">性别</th>
              <th width="13%">学历</th>
              <th width="8%">年龄</th>
              <th width="11%">岗位大类</th>
              <th>是否专家</th>
              <th width="10%">地区</th>
              <th width="10%">层级</th>
          </tr>
         </thead>
     </table>
 </div>

 <div class="clearfix mb5" id="waitrandom">
     <h5 class="fl mr10 h5-title">检查人员抽取条件设置</h5>
     <c:if test="${pubScPlanTaskDto.deptState != '2'}">
<!--     	<p class="fl"><input type="button" class="btn btn-sm" id="addAgentId" value="确认本次检查人员抽取库"> -->
<!--     	</p> -->
     </c:if>
 </div>
      <div class="center">
<%--                 	<c:if test="${pubDeptSctaskDto.inspectState == '1'}"> --%>
   	<div class="form-box" id="agent-form">
    <div class="form-list">
        <div class="form-item clearfix">
              	<div class="col-5">
              		<div class="col-4 item-name">每户检查对象配置：</div>
               	<div class="col-5 ipt-box">
               		检查人员总数<input type="text" id="checkNumber" class="ipt-txt" style="width: 120px;" value="${pubScPlanTaskDto.minNum }" <c:if test="${pubScPlanTaskDto.deptState != '1'}">disabled="disabled"</c:if>/>人
               	</div>
              	</div>
           </div>
<!-- 					        <div class="form-item clearfix"> -->
<!-- 			                	<div class="col-5"> -->
<!-- 			                		<div class="col-4 item-name">检查人员抽取方式：</div> -->
<!-- 				                	<div class="col-6 ipt-box"> -->
<!-- 			                		<select id="scTypeWay"> -->
<!-- 			                			<option value="2">全局模式(混合随机)</option> -->
<!-- 			                			<option value="2">全局模式(区域定向随机)</option> -->
<!-- 			                			<option value="1">局所联动随机模式</option> -->
<!-- 			                			<option value="3">所所联动随机模式</option> -->
<!-- 			                			<option value="4">所所交叉随机模式</option> -->
<!-- 			                			<option value="3">全所混合随机模式</option> -->
<!-- 			                		</select> -->
<!-- 				                	</div> -->
<!-- 			                	</div> -->
<!-- 				            </div> -->
<!-- 					        <div class="form-item clearfix"> -->
<!-- 			                	<div class="col-5"> -->
<!-- 			                		<div class="col-4 item-name">检查人员配对方式：</div> -->
<!-- 				                	<div class="col-8 ipt-box"> -->
<!-- 				                		<input type="radio" name="checkType" />按小组 -->
<!-- 				                		<input type="radio" name="checkType" />按人员（检查人员均为同一部门内人员情况） -->
<!-- 				                	</div> -->
<!-- 			                	</div> -->
<!-- 				            </div> -->
          </div>
      </div>
<%--                     </c:if> --%>
</div>
<div class="center mb20" style="padding: 20px;">
<c:if test="${pubScPlanTaskDto.deptState == '1'}">
<input type="button" value="开始抽取检查名单" class="btn mr20" id="random"/>
<input type="button" value="查看企业人员配对结果" class="btn mr20" id="view" style="display: none;"/>
</c:if>
<c:if test="${pubScPlanTaskDto.deptState != '1'}">
<input type="button" value="查看企业人员配对结果" class="btn mr20" id="view"/>
</c:if>
<input type="button" value="关闭" class="btn" id="close"/>
</div>
<script>
    window._CONFIG = {
       chooseUrl:'${sysUser.userType == 2 ? "/syn" : "/reg"}', 
    }
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/syn/system/sccheck/scplantask/scrandmagent_main.js"></script>
</body>
</html>