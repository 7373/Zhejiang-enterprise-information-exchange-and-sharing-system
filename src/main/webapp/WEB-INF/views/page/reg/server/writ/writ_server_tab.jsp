<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="hx" uri="/WEB-INF/tld/hx.tld"%>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>文书回执详情</title>
    <link rel="stylesheet" href="/css/reg.server.css">
</head>

<body class="pd10">

<div class="clearfix reg-company-basic reg-company-basic-yellowish">
    <div class="clearfix reg-caption">
        <div class="title clearfix">
            <span class="name fl mr5">${midBaseInfo.entName}</span>
            <span class="${hx:yReportStyle(midBaseInfo.regState)}">${hx:publicStatus(midBaseInfo.regState)}</span>
            <span class="icon-rectangle blue fl mr10">${midBaseInfo.isIndivid=='1'?'个转企':''}</span>

            <c:if test="${isOpanomaly=='1'}">
                <span class="icon-rectangle red fl mr10">被列入经营异常名录和严重违法失信（黑名单）</span>
            </c:if>

            <c:if test="${isOpanomaly=='2'}">
                <span class="icon-rectangle red fl mr10">被列入经营异常名录</span>
            </c:if>

            <c:if test="${isOpanomaly=='3'}">
                <span class="icon-rectangle red fl mr10">被列入严重违法失信（黑名单）</span>
            </c:if>

        </div>
        <div class="clearfix">
            <p class="w360">
                <i class="book2-icon"></i>统一社会信用代码/注册号：<span>${!empty midBaseInfo.uniCode?midBaseInfo.uniCode:midBaseInfo.regNO}</span>
            </p>
            <p>
                <i class="local-icon"></i>登记机关：<span>${midBaseInfo.regOrgName}</span>
            </p>
        </div>
        <div class="clearfix">
            <p class="w360">
                <i class="person-icon"></i>法定代表人：<span>${midBaseInfo.leRep}</span>
            </p>
            <p>
                <i class="government-icon"></i>管辖单位：<span>${midBaseInfo.localAdmName}</span>
            </p>
        </div>

        <div class="clearfix">
            <p class="w360">
                <i class="capital-icon"></i>注册资本：
                <span>
                    <c:if test="${!empty midBaseInfo.regCap}">
                        <fmt:formatNumber type="number"  value=" ${midBaseInfo.regCap }"  pattern="0.00" maxFractionDigits="2"/>(万元)
                    </c:if>
                </span>
            </p>
            <c:if test="${midBaseInfo.regState!='D'&&midBaseInfo.regState!='DX'}">
                <p><i class="date-icon"></i>成立日期：<span><fmt:formatDate value="${midBaseInfo.estDate}" dateStyle="long"/></span></p>
            </c:if>
        </div>

        <div class="clearfix">
            <c:if test="${midBaseInfo.regState=='D'}">
                <p class="w360">
                    <i class="date-icon"></i>吊销日期：<span><fmt:formatDate value="${midBaseInfo.altDate}" dateStyle="long"/></span>
                </p>
            </c:if>

            <c:if test="${midBaseInfo.regState=='DX'}">
                <p>
                    <i class="date-icon"></i> 注销日期：<span class="light"><fmt:formatDate value="${midBaseInfo.altDate}" dateStyle="long" /></span>
                </p>
            </c:if>
        </div>

        <div class="clearfix">
            <c:if test="${midBaseInfo.isIndivid=='1'}">
                <p>
                    <i class="date-icon"></i> 转企日期：<span class="light"><fmt:formatDate value="${midBaseInfo.individDate}" dateStyle="long"/></span>
                </p>
            </c:if>
            <p>
                <i></i>&nbsp;<span class="light">&nbsp;</span>
            </p>
        </div>

    </div>
</div>

<div class="light-info mt10">
    <div class="form-list light ">
        <div class="form-item clearfix">
            <div id="uniCode" class="col-4">
                <label class="right col-6">法定代表人（负责人）：</label>
                <div class="col-6">
                    ${midBaseInfo.leRep}  &nbsp;&nbsp;   ${midBaseInfo.tel}
                </div>
            </div>
            <div class="col-4" >
                <label class="right col-6">工商联络员：</label>
                <div class="col-6">
                    ${noticePeople}  &nbsp;&nbsp;  ${tel}
                </div>
            </div>
        </div>
    </div>
</div>


<div>
    其他未回执的文书【<strong class="light">${notReceiveList.size()}</strong>】
    <c:forEach items="${notReceiveList}" var="list">
        <span>${list}&nbsp;&nbsp;</span>
    </c:forEach>
</div>


<div>
    当前文书：
    <select style="width: 260px;" id="writ_list">
        <c:forEach items="${writList}" var="writList">
            <option value="${writList.uuid}" ${writ.uuid==writList.uuid?'selected':''} >${writList.writName}</option>
        </c:forEach>
    </select>
    选择文书名称，查看详情。
</div>

<br>

<div>
    <h2>文书详情</h2>
    <table border="1">
        <tr>
            <td width="100">文号</td>
            <td width="200" id="js-wh">${writ.writNo}</td>
            <td width="100">文书类别</td>
            <td width="200" id="js-wslb">${writ.writType}</td>
        </tr>
        <tr>
            <td width="100">文书名称</td>
            <td width="200" id="js-wsmc">${writ.writName}</td>
            <td width="100">通知对象（工商联络员）</td>
            <td width="200" id="js-tzdx">${writ.noticePeople}</td>
        </tr>
        <tr>
            <td width="100">文书内容</td>
            <td width="200">
                <a href="javascript:void(0)" id="js-view-details" uuid="${writ.uuid}" pripid="${writ.priPID}">查看详情</a>
            </td>
            <td width="100">文书回执状态</td>
            <c:if test="${writ.writReceiveStatue=='1'}">
                <td width="200" class="js-wszt" style="color: green;">已回执</td>
            </c:if>
            <c:if test="${writ.writReceiveStatue=='0'}">
                <td width="200" class="js-wszt" style="color: red;">未回执</td>
            </c:if>
        </tr>
    </table>

</div>



<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/reg/server/writ/writ_server_tab.js"></script>
</body>
</html>
