<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta charset="utf-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title>综合抽查结果统计</title>
<link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="/css/reg.server.css">
<link rel="stylesheet" href="/js/lib/jquerymultiselect/jquery.multiselect.css">
</head>
<body class="pd10">
<!-- <img  src="/img/reg/server/zfryktj.jpg"> -->
	<div class="tabbale" data-component="tab">
		<form id="taskForm" class="form-box mb5">
			<div class="form-list pdr8">
				<div class="form-item clearfix">
					<div class="col-3">
						<label class="item-name col-5">任务名称：</label>
	                      <div class="ipt-box col-7">
<!-- 	                        <div class="ipt-box col-575"> -->
								<select name="taskUid">
									<option></option>
									 <c:forEach var="pubScPlanTask" items="${taskNameList}">
									 	<option value="${pubScPlanTask.uid }">【${pubScPlanTask.taskCode }】${pubScPlanTask.taskName }</option>
									</c:forEach>
								</select>
<!-- 	                            <input type="text" class="ipt-txt" name="taskName" /> -->
<!-- 	                        </div> -->
	                     </div>
					</div>
					<div class="col-3">
						<label class="item-name col-5">统计截止日期：</label>
	                      <div class="ipt-box col-7">
<!-- 	                        <div class="ipt-box col-575"> -->
	                            <input type="text" class="ipt-txt laydate-icon" readonly="readonly"  
	                              name="setTimeEnd" id="setTimeEnd" value="${endCheckPushDate}"/>
<!-- 	                        </div> -->
	                     </div>
					</div>
					<div class="col-3">
						<label class="item-name col-5">统计层级：</label>
						<div class="col-7">
							<div class="ipt-box col-11">
                            <select name="statLevel" id="statLevel">
                                <c:if test="${sysUser.isAdmin == 1 || sysUser.isAdmin == 2 || sysUser.searchRangeLevel == 4 }"><option value="area">按地区</option></c:if>
                                <c:if test="${sysUser.isAdmin == 1 || sysUser.isAdmin == 2 || sysUser.searchRangeLevel != 1 }"><option value="regOrg">按登记机关</option></c:if>
                                <option value="localAdm">按管辖单位</option>
                            </select>
							</div>
						</div>
					</div>
					<div class="col-3">
						<label class="item-name col-5">统计范围：</label>
						<div class="col-7">
							<div class="ipt-box col-9">
                                <select name="statArea" id="statArea" multiple="multiple">
                            </select>
							</div>
						</div>
					</div>
				</div>
				<div class="center mt10">
					<input type="button" id="search" value="查询" class="btn mr20">
					<input type="button" id="cancel"
						onclick="$('#taskForm')[0].reset();" value="重置" class="btn mr20">
				</div>
		</form>
	</div>
	
    <div class="light-info mt10">
         <p class="light">数据说明：
1、“应检查数”指抽查任务中涉及本部门检查事项的检查名单数量；“已检查数”指本部门实施检查的名单中已完成检查结果录入并公示的数量；“完结数”指已检查数中后续处置措施已执行完结的数量。
2、“完成率” = 已检查数 / 应检查数；“完结率” = 完结数 / 已检查数。</p>
    </div>
    <div class="table-out">
        <div>
            <table id="user-table" border="0" cellspacing="0" cellpadding="0" class="table-row mt30" style="width: 100%;white-space: nowrap;">
                <thead>
                <tr>
	                <th rowspan="2">行次</th>
		            <th rowspan="2">地区</th>
		            <th rowspan="2">部门</th>
		            <th rowspan="2">应<br/>检<br/>查<br/>数</th> 
		            <th rowspan="2">已<br/>检<br/>查<br/>数</th>
		            <th rowspan="2">完<br/>成<br/>率<br/>%</th>
		            <th colspan="11">检查结果</th>
		            <th colspan="6">后续处置</th>
		            <th colspan="2">完结情况</th>
                </tr>
                <tr>
		            <th>未<br/>发<br/>现<br/>违<br/>法<br/>行<br/>为</th>
		            <th>违<br/>反<br/>工<br/>商<br/>行<br/>政<br/>管<br/>理<br/>相<br/>关<br/>规<br/>定</th>
		            <th>违<br/>反<br/>食<br/>品<br/>药<br/>品<br/>管<br/>理<br/>相<br/>关<br/>规<br/>定</th>
		            <th>违<br/>反<br/>质<br/>量<br/>技<br/>术<br/>监<br/>督<br/>相<br/>关<br/>规<br/>定</th>
		            <th>查<br/>无<br/>下<br/>落</th>
		            <th>已<br/>关<br/>闭<br/>停<br/>业<br/>或<br/>正<br/>在<br/>清<br/>算</th>
		            <th>不<br/>予<br/>配<br/>合<br/>情<br/>节<br/>严<br/>重<br/></th>
		            <th>注<br/>销<br/></th>
		            <th>被<br/>吊<br/>销<br/></th>
		            <th>被<br/>撤<br/>销<br/></th>
		            <th>迁<br/>出<br/></th>
		            <th>无<br/>需<br/>后<br/>续<br/>处<br/>置</th>
		            <th>停<br/>止<br/>检<br/>查</th>
		            <th>责<br/>令<br/>限<br/>期<br/>整<br/>改</th>
		            <th>发<br/>现<br/>案<br/>件<br/>线<br/>索</th>
		            <th>抄<br/>告<br/>相<br/>关<br/>部<br/>门</th>
		            <th>其<br/>他</th>
		            <th>完<br/>结<br/>数</th>
		            <th>完<br/>结<br/>率<br/>%</th>
                </tr>
                </thead>
                <tfoot>
	        <tr>
	            <th>合计</th>
	            <th>-</th>
	            <th>-</th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	            <th></th>
	        </tr> 
	        </tfoot>
            </table>
        </div>
    </div>
<script>
    window._CONFIG = {
       year:'${year}',
       deptCode:'${deptCode}',
       endCheckPushDate:'${endCheckPushDate}',
       month:'${month}',
       _searchRangeLevel:'${sysUser.searchRangeLevel}',
       _isAdmin:'${sysUser.isAdmin}',
	    chooseUrl:'${sysUser.userType == 2 ? "/syn" : "/reg"}'
    }
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/syn/system/sccheck/scresult/scentresult_count.js"></script>
</body>
</html>
