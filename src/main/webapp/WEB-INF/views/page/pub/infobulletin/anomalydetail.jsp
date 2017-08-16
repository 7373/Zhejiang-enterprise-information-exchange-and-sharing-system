     <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>公示系统公告详情页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pub.css"/>"/>
</head>
<script type="text/javascript">
   function doOptForShow(deptSameGov){
	   if(deptSameGov =="" || deptSameGov == null){
		   return "人民政府";
	   }
	   if(deptSameGov.indexOf("人民政府") != -1){
		   return deptSameGov;
	   }else{
		   return deptSameGov + "人民政府";
	   }
   }
</script>
<body class="enterprise-info-bg">
<jsp:include page="../common/noticeheader.jsp"></jsp:include>
<div class="mb20 mt20">
  <c:if test="${pubType == '1' || pubType == '2'}">
	<div class="mod mod-notice-detail mod-notice-detail2">
		<div class="mod-notice-hd mod-hd">
			<h3>经营异常公告</h3>
		</div>
		<div class="mod-notice-bd">
			<h1>
				<c:if test="${pubType == '1'}">
					${pubOpanoMaly.deptName}
					<p>列入经营异常名录决定书</p>
				</c:if>
				<c:if test="${pubType == '2'}">
					${pubOpaDetail.deptName}
					<p>移出经营异常名录决定书</p>
				</c:if>
			</h1>
			<p class="tr-center mb20">${pubOpanoMaly.penDecNo}${pubOpaDetail.penDecNo}</p>

			${pubOpanoMaly.entName}${pubOpaDetail.entName}：
			<c:if test="${pubType == '1' }">
				<p style="text-indent:2em;">经查，你单位因
					<c:if test="${pubOpanoMaly.speCause =='1'}">未依照《企业信息公示暂行条例》第八条在规定的期限内公示年度报告</c:if>
					<c:if test="${pubOpanoMaly.speCause =='2'}">未在工商行政管理部门依照《企业信息公示暂行条例》第十条规定责令的期限内公示有关企业信息</c:if>
					<c:if test="${pubOpanoMaly.speCause =='3'}">公示企业信息隐瞒真实情况、弄虚作假</c:if>
					<c:if test="${pubOpanoMaly.speCause =='4'}">通过登记的住所或者经营场所无法联系</c:if>
					，违反了《企业信息公示暂行条例》和《企业经营异常名录管理暂行办法》的相关规定。根据《企业信息公示暂行条例》第十七条第一款和《企业经营异常名录管理暂行办法》第四条第一款第（三）项和第八条的规定，现决定将你单位列入经营异常名录。 </p>
					<p style="text-indent:2em;">你单位如不服本决定，可在接到本决定书之日起六十日内向
				    ${empty pubOpanoMaly.deptUpName?"":pubOpanoMaly.deptUpName}${empty pubOpanoMaly.deptUpName?"":"或者"}
				    <script>document.write(doOptForShow("${pubOpanoMaly.deptSameGov}"))</script>
				          申请行政复议；或者六个月内向${pubOpanoMaly.deptSameCourt}人民法院提起行政诉讼。
				    </p>
			</c:if>

			<c:if test="${pubType == '2' }">
				<p style="text-indent:2em;">经查，你单位已经
					<c:if test="${pubOpaDetail.remExcpres =='1'}">补报未报年份的年度报告并且公示</c:if>
					<c:if test="${pubOpaDetail.remExcpres =='2'}">公示了有关企业信息，履行了公示义务</c:if>
					<c:if test="${pubOpaDetail.remExcpres =='3'}">更正了其公示的相关虚假信息</c:if>
					<c:if test="${pubOpaDetail.remExcpres =='4'}">依法办理住所或者经营场所变更登记</c:if>
					。根据《企业信息公示暂行条例》第十七条第二款和《企业经营异常名录管理暂行办法》第十一条的规定，决定将你单位移出经营异常名录。</p>
					<p style="text-indent:2em;">你单位如不服本决定，可在接到本决定书之日起六十日内向
					${empty pubOpaDetail.deptUpName?"":pubOpaDetail.deptUpName}${empty pubOpaDetail.deptUpName?"":'或者'}
					<script>document.write(doOptForShow("${pubOpaDetail.deptSameGov}"))</script>
					申请行政复议；或者六个月内向${pubOpaDetail.deptSameCourt}人民法院提起行政诉讼。
					</p>
			</c:if>
		</div>
		<div class="mod-notice-fd" style="padding-top: 80px">
			<p>${pubOpanoMaly.decorgCN}${pubOpaDetail.reDecOrgCN}</p>
			<p><fmt:formatDate value="${pubOpanoMaly.auditDate}" pattern="yyyy年MM月dd日"/>
				<fmt:formatDate value="${pubOpaDetail.auditDate}" pattern="yyyy年MM月dd日"/></p>
		</div>
	</div>
 </c:if>
 
 <c:if test="${pubType == '3'}">
     <div class="mod mod-notice-detail mod-notice-detail2">
		<div class="mod-notice-hd mod-hd">
			<h3>提醒公告</h3>
		</div>
		<div class="mod-notice-bd">
		  <h1>
			${pubOpanoMaly.deptName}
			<p>经营异常期满三年提醒公告</p>
		  </h1>
		  <p class="tr-center mb20">${pubOpanoMaly.penDecNo}</p>
		  
		  ${pubOpanoMaly.entName}：
		  <p style="text-indent:2em;">经查，你单位因
            <c:if test="${pubOpanoMaly.speCause =='1'}">未依照《企业信息公示暂行条例》第八条在规定的期限内公示年度报告</c:if>
			<c:if test="${pubOpanoMaly.speCause =='2'}">未在工商行政管理部门依照《企业信息公示暂行条例》第十条规定责令的期限内公示有关企业信息</c:if>
			<c:if test="${pubOpanoMaly.speCause =='3'}">公示企业信息隐瞒真实情况、弄虚作假</c:if>
			<c:if test="${pubOpanoMaly.speCause =='4'}">通过登记的住所或者经营场所无法联系</c:if>
			，违反了《企业信息公示暂行条例》和《企业经营异常名录管理暂行办法》的相关规定被列入经营异常名录。 自&nbsp;<u><fmt:formatDate value="${pubOpanoMaly.auditDate}" pattern="yyyy年MM月dd日"/></u>&nbsp;起被列入经营异常名录至今将满三年，
			根据《企业经营异常名录管理暂行办法》相关规定，你企业若届满三年仍未履行相关义务，将被列入严重违法名单。</p>
		<p style="text-indent:2em;">你单位应主动纠正相关违法行为，并向<u>${pubOpanoMaly.decorgCN}</u>申请移出经营异常名录。</p>
		</div>
		<div class="mod-notice-fd" style="padding-top: 80px">
			<p>${pubOpanoMaly.decorgCN}</p>
			<p><fmt:formatDate value="${pubAnnounceMent.auditDate}" pattern="yyyy年MM月dd日"/></p>
		</div>
     </div>
 </c:if>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>

