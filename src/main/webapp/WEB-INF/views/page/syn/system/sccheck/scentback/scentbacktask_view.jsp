<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>抽查检查对象名单抽取</title>
<!--     <link rel="stylesheet" href="/css/syn.css"> -->
     <link rel="stylesheet" href="/css/reg.server.css">
	<link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/js/lib/jquerymultiselect/jquery.multiselect.css">
</head>
<body class="pd10">
<div>
    <table class="table-horizontal">
        <tr>
            <td class="bg-gray right" width="15%">任务名称：</td>
            <td width="85%">
                <div class="ipt-box col-12">
                    <input type="text" id="taskName" value="${pubScPlanTask.taskName }" class="ipt-txt required" style="width: 80%" disabled="disabled">
                   <a href="javascript:void(0);" id="taskDetail">详情</a>
               </div>
           </td>
       </tr>
       <tr>
           <td class="bg-gray right"><span style="color:red">*</span>入库对象筛选条件<br/>说明（2000字内）：</td>
            <td>
                <div class="ipt-box">
                    <textarea id="taskCondition" name="taskCondition" class="min-h100 required" placeholder="请对入库对象的筛选条件信息进行必要说明，以方便查看" disabled="disabled">${pubScPlanTask.taskCondition }</textarea>
                </div>
            </td>
        </tr>
    </table>
</div><br/>
<form id="qryForm">
<input type="hidden" id="userType" value=${userType }>
<input type="hidden" id="taskUid" name="taskUid" value=${taskUid }>
<input type="hidden" id="randomType" name="randomType" value="${pubScPlanTask.randomType}">
<div class="form-box mb5">
    <div class="form-list">
        <div class="form-item clearfix">
            <div class="col-4">
                <label class="item-name col-4">统一代码/注册号：</label>
                <div class="col-7">
                    <div class="ipt-box col-12">
                        <input type="text" id="regNO" name="regNO" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-4">企业名称：</label>
                <div class="col-7">
                    <div class="ipt-box col-12">
                        <input type="text" id="entName" name="entName" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="col-7">
                    <div class="btn-box">
		                <input type="button" value="查 询" id="qry" class="btn mr20">
		                <input type="button" value="重 置" id="cancel" class="btn">
		            </div>
                </div>
            </div>
        </div>
        <div class="form-item clearfix">
        </div>
    </div>
</div>
</form>
<div>
    <table border="0" id="scentback-table" cellspacing="0" cellpadding="0" class="table-row perc-100 nowrap">
       <thead>
        <tr>
            <th>序号</th>
            <th>统一社会信用代码/注册号</th>
            <th>企业名称</th>
            <th>成立日期</th>
            <th>主体类别 </th>
            <th>上次抽查日期</th>
            <th>登记机关</th>
            <th>管辖单位</th>
        </tr>
        </thead>
    </table>
</div>
<h5 class="h5-title">
	<span>
	请选择抽取模式：<input type="radio" name="checkType" value="multiple" <c:if test="${pubScPlanTask.randomType == '1'}">checked="checked"</c:if> disabled="disabled"/>按综合主体库统一抽取
	<input type="radio" name="checkType" value="special" <c:if test="${pubScPlanTask.randomType == '2'}">checked="checked"</c:if> disabled="disabled"/>
	按专项检查对象库分类抽取
	</span>
	<span style="color:red;float: right;">提示：抽取数量均按照四舍五入取整数。</span>
</h5>
<div>
    <table border="0"  id="scentcondition-table" cellspacing="0" cellpadding="0" class="table-row perc-100 nowrap">
       <thead>
        <tr>
            <th>序号</th>
            <th>任务涉及的检查事项</th>
            <th>对应的检查对象抽取库</th>
            <th>抽取库主体户数</th>
            <th>抽取目标户数</th>
        </tr>
        </thead>
    </table>
</div>
<div class="form-box">
    <div class="form-list">
        <div class="form-item clearfix mb10">
            <div class="col-4">
                <div class="col-6 item-name">检查对象抽取库总户数：</div>
                <div class="col-6 ipt-box">
                    <div class="col-8"><input type="text" class="ipt-txt" id="totalEnt" value="${pubScPlanTask.entTotal }" readonly="readonly"/></div>
                </div>
            </div>
            <div class="col-4">
                <div class="col-6 item-name">抽取目标总户数：</div>
                <div class="col-6 ipt-box">
                    <div class="col-8"><input type="text" class="ipt-txt" id="totalRandom" value="${pubScPlanTask.randomTotal }" readonly="readonly"/></div>
                </div>
            </div>
            <div class="col-4">
                <div class="col-6 item-name" id="itemcount">总抽取比例：</div>
                <div class="col-6 ipt-box">
                    <div class="col-8"><input type="text" class="ipt-txt" id="randomPercent" value="${pubScPlanTask.randomPercent }" readonly="readonly"/></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="clearfix mt20">
    <div class="btn-box">
        <input type="button" value="关闭" id="cancleEdit" class="btn">
    </div>
</div><br/>

</body>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/syn/system/sccheck/scentback/scentbacktask_view.js"></script>
</html>