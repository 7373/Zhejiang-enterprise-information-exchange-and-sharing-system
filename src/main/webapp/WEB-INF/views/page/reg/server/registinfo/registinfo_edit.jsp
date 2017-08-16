<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="hx" uri="/WEB-INF/tld/hx.tld"%>
<jsp:useBean id="nowdate" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>主体户口建档页面</title>
    <link rel="stylesheet" href="/css/reg.server.css">
    <link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
</head>
<script type="text/javascript">
    //无证无照查询的初始化条件
    var leRep = '${midBaseInfoDto.leRep}';
    var cerNO = '${midBaseInfoDto.cerNO}';
    var entName = '${midBaseInfoDto.entName}';
    var dom = '${midBaseInfoDto.dom}';
    var _sliceMan = '${registInfoDto.sliceMan}';
    var _sliceTel = '${registInfoDto.sliceTel}';
    var _fromtype='${fromtype}';
</script>
<body class="pd10">
<div  ${fromtype=='batch'?'style="display:none"':''}>
<div class="clearfix reg-company-basic reg-company-basic-yellowish">
    <div class="clearfix reg-caption">
        <div class="title clearfix">
            <span class="name fl mr5">${midBaseInfoDto.entName}</span>
            <span class="${hx:yReportStyle(midBaseInfoDto.regState)}">${hx:publicStatus(midBaseInfoDto.regState)}</span>
            <span class="icon-rectangle blue fl mr10">${midBaseInfoDto.isIndivid=='1'?'个转企':''}</span>

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
                <i class="book2-icon"></i>统一社会信用代码/注册号：<span>${!empty midBaseInfoDto.uniCode?midBaseInfoDto.uniCode:midBaseInfoDto.regNO}</span>
            </p>
            <p>
                <i class="local-icon"></i>登记机关：<span>${midBaseInfoDto.regOrgName}</span>
            </p>
        </div>
        <div class="clearfix">
            <p class="w360">
                <i class="person-icon"></i>法定代表人：<span>${midBaseInfoDto.leRep}</span>
            </p>
            <p>
                <i class="government-icon"></i>管辖单位：<span>${midBaseInfoDto.localAdmName}</span>
            </p>
        </div>

        <div class="clearfix">
            <p class="w360">
                <i class="capital-icon"></i>注册资本：
                <span>
                    <c:if test="${!empty midBaseInfoDto.regCap}">
                        <fmt:formatNumber type="number"  value=" ${midBaseInfoDto.regCap }"  pattern="0.00" maxFractionDigits="2"/>(万元)
                    </c:if>
                </span>
            </p>
            <c:if test="${midBaseInfoDto.regState!='D'&&midBaseInfoDto.regState!='DX'}">
                <p><i class="date-icon"></i>成立日期：<span><fmt:formatDate value="${midBaseInfoDto.estDate}" dateStyle="long"/></span></p>
            </c:if>
        </div>

       <div class="clearfix">
           <c:if test="${midBaseInfoDto.regState=='D'}">
               <p class="w360">
                   <i class="date-icon"></i>吊销日期：<span><fmt:formatDate value="${midBaseInfoDto.altDate}" dateStyle="long"/></span>
               </p>
           </c:if>

           <c:if test="${midBaseInfoDto.regState=='DX'}">
               <p>
                   <i class="date-icon"></i> 注销日期：<span class="light"><fmt:formatDate value="${midBaseInfoDto.altDate}" dateStyle="long" /></span>
               </p>
           </c:if>
       </div>

       <div class="clearfix">
           <c:if test="${midBaseInfoDto.isIndivid=='1'}">
               <p>
                   <i class="date-icon"></i> 转企日期：<span class="light"><fmt:formatDate value="${midBaseInfoDto.individDate}" dateStyle="long"/></span>
               </p>
           </c:if>
           <p>
               <i></i>&nbsp;<span class="light">&nbsp;</span>
           </p>
       </div>
        <div class="btn-box">
            <div class="mb5 clearfix">
<!--                 <a class="btn-attention-box"> -->
<!--                     <i class="attention-icon"></i> -->
<!--                     <span>关注本企业</span> -->
<!--                 </a> -->
                <a class="btn-print-box">
                    <i class="print-icon2"></i>
                    <span>打印</span>
                </a>
            </div>
            <div>
<!--                 <a class="btn-attention-box"> -->
<!--                     <i class="query-icon"></i> -->
<!--                     <span>关系网查询</span> -->
<!--                 </a> -->
                <a class="btn-print-box">
                    <i class="export-icon"></i>
                    <span>导出</span>
                </a>
            </div>
        </div>
    </div>
    <c:if test="${erEscAppinfo.simplecanRea=='4'}">
        <div class="notice"><i class="notice-icon"></i>正在进行简易注销公告：公告期：<fmt:formatDate value="${erEscAppinfo.noticeFrom}" pattern="yyyy年MM月dd日"/>-<fmt:formatDate value="${erEscAppinfo.noticeTo}" pattern="yyyy年MM月dd日"/></div>
    </c:if>
</div>

<div class="supervise-about mt5 mb5">
<!--     <p><label>监管项目：</label><span>食口生产</span><span>保化品</span><span>食品经营</span></p> -->
<!--     <p> -->
<!--         <label>监管提示：</label><span>重点监管对象</span><span>关注服务对象</span><span>列入异常即将届满3年</span><span>许可证已到期</span><span>经营期限已到期</span> -->
<!--     </p> -->
    <div class="more reverse js-fold-pubinfo">展开全部详情<i></i></div>
</div>
<div class="mb5">
    <span class="tag-block-item ft14 mr30"><i class="tag33cccc"></i>分类监管项目（<em class="light">${apprMarkRelDtoSize }</em>）/抽查专项库（<em class="light">${apprMarkRelSccount }</em>）</span>
    <span class="tag-block-item ft14 mr30"><i class="tagff6666"></i>重点监管对象（<em class="light">${csSuperviseObjDtoSize }</em>）</span>
    <span class="tag-block-item ft14 mr30"><i class="tag66cc66"></i>关注服务对象（<em class="light">${csConcernObjDtoSize }</em>）</span>
    <span class="tag-block-item ft14 mr30"><i class="tagff6600"></i>预警提醒提示（<em class="light">${tipsSize }</em>）</span>
</div>
<div class="mb5">
	<c:forEach var="apprMarkRelDto" items="${apprMarkRelDtos }">
	    <span class="tag33cccc tag-item">${apprMarkRelDto.markName }</span>
	</c:forEach>	
	<c:forEach var="csSuperviseObjDto" items="${csSuperviseObjDtoList }">
	    <span class="tagff6666 tag-item">${csSuperviseObjDto.markName }</span>
	</c:forEach>
	<c:forEach var="csConcernObjDto" items="${csConcernObjDtoList }">
	    <span class="tag66cc66 tag-item">${csConcernObjDto.markName }</span>
	</c:forEach>
	<c:forEach var="tip" items="${tips }">
	    <span class="tagff6600 tag-item">${tip }</span>
	</c:forEach>
</div>
<table cellpadding="0" cellspacing="0" border="0" class="table-horizontal  js-table-horizontal js-show" style="display:none;">
    <tbody>
    <tr>
        <td class="bg-gray right" width="15%">类型</td>
        <td>${midBaseInfoDto.entTypeName}</td>
        <td class="bg-gray right">核准日期</td>
        <td><fmt:formatDate value="${midBaseInfoDto.apprDate}" dateStyle="long"/></td>
    </tr>
    <tr>
        <td class="bg-gray right">住所</td>
        <td>${midBaseInfoDto.dom}</td>
        <td class="bg-gray right">营业期限</td>
        <td><fmt:formatDate value="${midBaseInfoDto.opFrom}" dateStyle="long"/>至<fmt:formatDate value="${midBaseInfoDto.opTo}" dateStyle="long"/></td>
    </tr>
    <tr>
        <td class="bg-gray right">经营范围</td>
        <td colspan="3">${midBaseInfoDto.opScope}</td>
    </tr>
    </tbody>
</table>
<div class="light-info mt10 js-show" style="display:none;">
    <div class="form-list light ">
        <div class="form-item clearfix">
            <div id="uniCode" class="col-4">
                <label class="right col-6">法定代表人（负责人）：</label>
                <div class="col-6">
                    ${dto.leRep}     ${dto.tel}
                </div>
            </div>
            <div class="col-4" id="entName">
                <label class="right col-6">工商联络员：</label>
                <div class="col-6">
                   ${liaName}   ${liaTel}
                </div>
            </div>
            <!-- <div class="col-4">
                <label class="right col-6">财务负责人：</label>
                <div class="col-6">
                    李二 13596489562
                </div>
            </div> -->
        </div>
    </div>
   </div>

    <div class="bg-gray2">
       <div class="gold"> 
        <span class="fr line-h30">数据来源：各审批许可部门</span>
        <i id="licenceinfo" class="flex-icon dib2 va-3 <c:if test="${pubOtherlicences.size()==0}">flexed-icon</c:if>"></i>
        <h6 class="add-title dib2">行政许可信息（<span id="pubotherlicTotal" class="light">0</span>）</h6>
       </div>
          <div class="" id="_tab-pubotherlicence" <c:if test="${pubOtherlicences.size()==0}">style='display: none;'</c:if>>
            <table border="0" cellspacing="0" cellpadding="0" class="table-row mt30 nowrap" width="100%">
                <tbody>
                <tr>
                    <th width="7.5%">序号</th>
                    <th width="22.7%">许可类型</th>
                    <th>许可证编号</th>
                    <th>许可证名称</th>
                    <th>许可内容</th>
                    <th>许可机关</th>
                    <th>起始日期</th>
                    <th>到期日期</th>
                    <th>许可状态</th>
                </tr>

                <c:if test="${pubOtherlicences.size()==0}">
                    <tr>
                        <td valign="top" colspan="9" class="dataTables_empty">无</td>
                    </tr>
                </c:if>
                <c:if test="${pubOtherlicences.size()>0}">
                    <c:forEach var="p" items="${pubOtherlicences}" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${p.licType=='1'?'变更':
                                    p.licType=='2'?'注销':
                                            p.licType=='3'?'被吊销':
                                                    p.licType=='4'?'撤销失效':
                                                            ''}</td>
                            <td>${p.licNO}</td>
                            <td>${p.licNameCN}</td>
                            <td>${p.licScope}</td>
                            <td>${p.licAnth}</td>
                            <td><fmt:formatDate value="${p.licValFrom}"/></td>
                            <td><fmt:formatDate value="${p.licValTo}"/></td>
                            <td>${p.licState=='0'?'无效':'有效'}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div> 
</div>

    <form id="registInfoForm">
        <input type="hidden" id="uid" name="uid" value="${registInfoDto.UID}"/>
        <input type="hidden" id="priPID" name="priPID" value="${registInfoDto.priPID}"/>
        <input type="hidden" id="archState" name="archState" value="${registInfoDto.archState}"/>
        <span class="fr line-h30" ${fromtype=='batch'?'style="display:none"':''} >建档日期：<fmt:formatDate value="${registInfoDto.archDate == null ? nowdate : registInfoDto.archDate}"  pattern="yyyy-MM-dd"/>&nbsp;&nbsp;     
		        建档人：${empty registInfoDto.archName ? sysUser.realName : registInfoDto.archName }&nbsp;&nbsp;
		        修改日期：<fmt:formatDate value="${registInfoDto.modDate == null ? nowdate : registInfoDto.modDate}"  pattern="yyyy-MM-dd"/>
		</span>
        <h5 class="tag33cccc h5-tag-item " >户口建档信息</h5>
        <div class="form-box" ${fromtype=='batch'?'style="display:none"':''}>
            <div class="form-list">
                <div class="form-item clearfix ">
                    <div class="col-12">
                        <label class="item-name col-2 " style="font-size: 14px;"><span class="light">*</span>实地勘察：</label>
                        <div class="col-9">
                            <div class="radio-box">
                                <div class="col-2">
                                    <label><input type="radio" value="1"
                                                  name="isInvestigate" ${registInfoDto.isInvestigate  == '1' ? 'checked':''} />是</label>
                                    <label><input type="radio" value="0"
                                                  name="isInvestigate" ${registInfoDto.isInvestigate == '0' ?  'checked':''} />否</label>
                                </div>
                                <div class="col-10 isInvestigateDiv" ${registInfoDto.isInvestigate == '1' ? '':'style="display:none"'}>
                                    <label class="mr20"><span class="light">*</span>勘察时间：</label>
                                    <input type="text" class="ipt-txt inline-txt " readonly="readonly"
                                           onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                                           name="investigateDate"
                                           value="<fmt:formatDate type='both' value="${registInfoDto.investigateDate}"/>">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-item clearfix">
                    <div class="col-12 isInvestigateDiv"  ${registInfoDto.isInvestigate == '1' ? '':'style="display:none"'}>
                        <label class="ft14 item-name col-2" style="font-size: 14px;">经营场所地址与实地是否一致：</label>
                        <div class="col-9">
                            <div class="radio-box col-12">
                                <label class="mr20"><input type="radio" value="1"
                                                           name="isSameAddr" ${registInfoDto.isSameAddr== '1' ? 'checked':''}>
                                    一致 </label>
                                <label class="mr20"><input type="radio" value="0"
                                                           name="isSameAddr" ${registInfoDto.isSameAddr== '0' ? 'checked':''}>不一致</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-item clearfix isInvestigateDiv" ${registInfoDto.isInvestigate == '1' ? '':'style="display:none"'}>
                    <div class="col-8 ">
                        <label class="item-name col-3"></label>
                        <div class="col-9">
                            <div class="radio-box col-9 pd0">
                                     <span class="isSameAddrDiv" ${registInfoDto.isSameAddr == '0' ? '':'style="visibility:hidden"'}>
                                       <label class="mr20 "><input type="radio" value="0"
                                                                   name="diffAddrReason"${registInfoDto.diffAddrReason== '0' ? 'checked':''}> 查无下落 </label>
                                       <label class="mr20 "><input type="radio" value="1"
                                                                   name="diffAddrReason"${registInfoDto.diffAddrReason== '1' ? 'checked':''}>商务秘书公司代理</label>
                                       <label class="mr20 "><input type="radio" value="2"
                                                                   name="diffAddrReason" ${registInfoDto.diffAddrReason== '2' ? 'checked':''}>异址经营</label>
                                      </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bg-gray2">
            <table cellpadding="0" cellspacing="0" border="0" class="table-horizontal">
                <tr class="bg-gray" ${registInfoDto.isChangeArch=='1' && fromtype=='single'?'':'style="display:none"'}>
                    <th class="right ft14"><label class="item-name" style="float: right;font-size: 14px;">建档变更类型：</label></th>
                    <th colspan="5" class="left">
                        <div class="radio-box col-12 pd0">
                            <input type="hidden" name="isChangeArch" value="${registInfoDto.isChangeArch}" >
                            <label class="mr20"><input type="radio"
                                                       value="1" ${registInfoDto.changeArchState=='1'?'checked':''}
                                                       name="changeArchState"> 内外资转型 </label>
                            <label class="mr20"><input type="radio"
                                                       value="2" ${registInfoDto.changeArchState=='2'?'checked':''}
                                                       name="changeArchState">被撤消（吊销）恢复</label>
                            <label class="mr20"><input type="radio"
                                                       value="3" ${(registInfoDto.changeArchState=='3')||(registInfoDto.changeArchState==null&&midBaseInfoDto.isIndivid=='1')?'checked':''}
                                                       name="changeArchState">个转企 </label>
                            <label class="mr40"><input type="radio"
                                                       value="4" ${registInfoDto.changeArchState=='4'?'checked':''}
                                                       name="changeArchState">个转企&新迁入</label>
                            <span id="changeArchState_span" ${registInfoDto.changeArchState=='3'||registInfoDto.changeArchState=='4'||(registInfoDto.changeArchState==null&&midBaseInfoDto.isIndivid=='1')?'':'style="display:none"'}>
                            <label><span class="light">*</span>转企时间：</label>
                            <input type="text" class="ipt-txt" readonly="readonly" onclick="laydate()"
                                   name="individDate"
                                   value="<fmt:formatDate value="${registInfoDto.individDate==null?midBaseInfoDto.individDate:registInfoDto.individDate}"/>">
                            </span>
                        </div>
                    </th>
                </tr>
                <tr>
                    <td class="bg-gray ft14 right" width="11%"><span class="light">*</span>分类监管标签</td>
                    <td colspan="5" style="height:60px;">
                        <input type="hidden" id="iDCodes" name="iDCodes"
                               value="<c:forEach var="p" items="${ownmarkGroups}">${p.markCode}</c:forEach>"/>
                        <input type="hidden" id="iDCodeNames" name="iDCodeNames"
                               value="<c:forEach var="p" items="${ownmarkGroups}">${p.markName}</c:forEach>"/>
                        <input type="hidden" id="iDType" value=""/>
                        <div class="display-table">
                           <span id="iDCodeNamesDiv">
                               <c:forEach var="p" items="${ownmarks}">
                               <span class="tag33cccc tag-item" >${p.markName}</span>
                               </c:forEach>                           
                           </span>
<%--                             <c:forEach var="p" items="${ownmarks}"><span class="tag33cccc tag-item iDCodeNamesDiv" >${p.markName}</span></c:forEach> --%>
                            <span class="table-cell right vm"><input id="_markfocusAdd" type="button" class="btn btn-xs"
                                                                     value="添加/编辑"></span>
                        </div>
                    </td>
                </tr>
<!--                  <tr> -->
<!--                  <td class="bg-gray ft14 right" width="11%"><span class="light">*</span>分类标签</td> -->
<!--                  <td valign="top" colspan="5" class="pd0" > -->
<!--                         <input type="hidden" id="superNoNames" name="superNoNames" -->
<%--                                value="<c:forEach var="p" items="${marks}">${p.IDName}</c:forEach>"/> --%>
<!--                         <input type="hidden" id="superNos" name="superNos" -->
<%--                                value="<c:forEach var="p" items="${marks}">${p.IDCode}</c:forEach>"/> --%>
<!--                         <input type="hidden" id="superType" value=""/> -->
<!--                         <div class="display-table"> -->
<%--                             <span class="table-cell vm tag33cccc" id="superNoNamesDiv" ><c:forEach var="p" --%>
<%--                                                                                         items="${ownsuper}">${p.superName}</c:forEach></span> --%>
<!--                             <span class="table-cell right vm"><input id="_markfocusAdd" type="button" class="btn btn-xs" -->
<!--                                                                      value="添加/编辑"></span> -->
<!--                         </div> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr ${fromtype=='batch'?'style="display:none"':''}>
                    <td class="bg-gray ft14 right"><span class="light">*</span>住所（经营场所）</td>
                    <td colspan="5">
                        <input type="hidden" name="street" id="street" value="${registInfoDto.street}">
                        <div class="ipt-box">
                            <div class="item-select">
                                <select name="" class="select-txt" style="width:10%" disabled>
                                    <option value="330000000000">浙江省</option>
                                </select>
                                <select name="cityAddress" style="width:20%" class="select-txt" id="select_city" >
                                    <option value="" >--请选择--</option>
                                </select>
                                <select name="areaAddress" style="width:20%" class="select-txt" id="select_area" >
                                    <option value="" >--请选择--</option>
                                </select>
                                <select name="streeAddress" style="width:20%" class="select-txt" id="select_stree" >
                                    <option value="" >--请选择--</option>
                                </select>
                              <input type="text" style="width:28%" id="desc_address" class="ipt-txt" name="desAddress" value="${registInfoDto.desAddress}" placeholder="">
                            </div>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="info_address" class="ipt-txt" name="opLoc" value="${empty registInfoDto.opLoc ? midBaseInfoDto.dom : registInfoDto.opLoc}" placeholder="" readonly>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="bg-gray ft14 right" width="10%"><span class="light">*</span>片区/商圈</td>
                    <td colspan="5">
                        <input type="hidden" id="sliceNO" name="sliceNO" value="${registInfoDto.sliceNO}">
                        <input type="hidden" id="sliceNOName" name="sliceNOName" value="${registInfoDto.sliceNOName}">
                        <input type="hidden" id="localAdm" value="${registInfoDto.localAdm}">
                        <div class="display-table">
                            <span class="table-cell vm" id="sliceNONameDiv">${registInfoDto.sliceNOName}</span>
<!--                             <span class="table-cell right vm add-icon" id="sliceNOAdd"><i></i></span> -->
                            
                            <span class="table-cell right vm"><input id="sliceNOAdd" type="button" class="btn btn-xs"
                                                                     value="添加/编辑"></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="bg-gray ft14 right" width="10%"><span class="light">*</span>管辖人员</td>
                    <td colspan="5">
                        <input type="hidden" id="sliceMan" name="sliceMan" value="${registInfoDto.sliceMan}">
                        <input type="hidden" id="sliceTel" name="sliceTel" value="${registInfoDto.sliceTel}">
                        <div class="display-table">
                            <span class="table-cell vm" id="sliceManp"></span>
                            <span class="table-cell right vm"><input id="sliceManAdd" type="button" class="btn btn-xs"
                                                                     value="添加/编辑"></span>
                        </div>
                    </td>
                </tr>
<%--                 <tr ${fromtype=='batch'?'style="display:none"':''}> --%>
<!--                     <td class="bg-gray ft14 right" width="10%">企业联系电话</td> -->
<!--                     <td width="20%" > -->
<!--                             <div class="ipt-box"> -->
<%--                             <input type="text" class="ipt-txt" name="unitTel" value="${registInfoDto.unitTel}"> --%>
<!--                             </div> -->
<!--                     </td> -->
<!--                     <td class="bg-gray ft14 right" width="10%">企业电子邮箱</td> -->
<!--                     <td colspan="3" width="40%" > -->
<!--                             <div class="ipt-box">                     -->
<!--                             <input type="text" class="ipt-txt" id="unitEmail" name="unitEmail" -->
<%--                                    value="${registInfoDto.unitEmail}"> --%>
<!--                             </div> -->
<!--                     </td> -->
<!--                 </tr> -->
<%--                 <tr ${fromtype=='batch'?'style="display:none"':''}> --%>
<!--                     <td class="bg-gray ft14 right" width="10%">企业网站网店</td> -->
<!--                     <td width="20%"> -->
<!--                             <div class="ipt-box"> -->
<%--                             <input type="text" class="ipt-txt" name="webSite" value="${registInfoDto.webSite}" placeholder="名称"> --%>
<!--                             </div> -->
<!--                     </td> -->
<!--                     <td colspan="4"> -->
<!--                             <div class="ipt-box"> -->
<%--                             <input type="text" class="ipt-txt" name="webSite" value="${registInfoDto.webSite}" placeholder="网址"> --%>
<!--                             </div> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr ${fromtype=='batch'?'style="display:none"':''}>
                    <td class="bg-gray ft14 right" width="10%">法定代表人/负责人</td>
                    <td width="20%">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" readonly="true" value="${midBaseInfoDto.leRep }"/>
                            </div>
                    </td>
                    <td colspan="2">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" name ="tel"  value="${empty registInfoDto.tel ? midBaseInfoDto.tel : registInfoDto.tel}"/>
                            </div>
                    </td>
                    <td class="bg-gray ft14 right" width="10%">身份证号码</td>
                    <td width="20%">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" readonly="true" value="${midBaseInfoDto.cerNO}"/>
                            </div>
                    </td>
                </tr>
                <tr ${fromtype=='batch'?'style="display:none"':''}>
                    <td class="bg-gray ft14 right" width="10%">工商联络员</td>
                    <td width="20%">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" readonly="true" value="${liaName}"/>
                            </div>
                    </td>
                    <td colspan="2">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" name ="entEppaPhone" value="${empty registInfoDto.entEppaPhone ? liaTel : registInfoDto.entEppaPhone}"/>
                            </div>
                    </td>
                    <td class="bg-gray ft14 right" width="10%">证件号码</td>
                    <td width="20%">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" readonly="true" value="${liaidnum}"/>
                            </div>
                    </td>
                </tr>
                <tr ${fromtype=='batch'?'style="display:none"':''}>
                    <td class="bg-gray ft14 right" width="10%">户口联系人</td>
                    <td width="20%">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" name="linkman" value="${registInfoDto.linkman}"/>
                            </div> 
                    </td>
                    <td colspan="2">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" name="linkPhone" value="${registInfoDto.linkPhone}" placeholder="手机">
                            </div>
                    </td>
                    <td colspan="2">
                            <div class="ipt-box">
                            <input type="text" class="ipt-txt" name="linktel" value="${registInfoDto.linktel}" placeholder="座机">
                            </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="bg-gray2"  ${fromtype=='batch'?'style="display:none"':''}>
        <div class="toobar mb10">
           <h5 class="tag33cccc h5-tag-item " >无照库对比情况</h5>
           <button class="btn btn-sm glyphicon glyphicon-plus js-logoff" type="button">&nbsp;销户</button>
        </div>
        <table id="logoff-table" width="100%" border="0" cellspacing="0" cellpadding="0" class="table-row mt30 nowrap">
            <thead>
            <tr>
                <th>序号</th>
                <th>单选</th>
                <th>当事人</th>
                <th>证件号码</th>
                <th>经营时所使用的名称</th>
                <th>片区/商圈</th>
                <th>经办人</th>
                <th>经办时间</th>
            </tr>
            </thead>
        </table>
    </div>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
    <p class="center mb10 mt10">
        <input id="save" type="button" value="保 存" class="btn mr20 ">
        <input id="cancel" type="button" value="关 闭" class="btn mr20">
<%--         <input id="print" type="button" value="打 印" class="btn"  ${fromtype=='batch'?'style="display:none"':''}> --%>
    </p>
    
<%--传递至js的参数： start--%>
<input type="hidden" id="params_area_address" value="${registInfoDto.areaAddress}" >
<input type="hidden" id="params_stree_address" value="${registInfoDto.streeAddress}">

<%--传递至js的参数： end--%>

    <script>
    window._CONFIG = {
       dom:'${midBaseInfoDto.dom}',
       fromtype:'${fromtype}'
    }
	</script>
    <script src="/js/lib/require.js"></script>
    <script src="/js/config.js"></script>
    <script src="/js/reg/server/registinfo/registinfo_edit.js"></script>
</body>
</html>