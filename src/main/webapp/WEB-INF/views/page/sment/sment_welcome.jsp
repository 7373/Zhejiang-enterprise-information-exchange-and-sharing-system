<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" href="/css/sment/sment.server.css">
</head>
<body>
    <div class="home">

        <div class="clearfix">
            <div class="home-item h-notice fl">
                <div class="h-title">
                    <i class="h-icon"></i>
                     <input type="hidden" id="moreFlag" value="0"/>
                </div>
                <div class="h-contant-box">
                    <div class="h-contant ul-list" id="js-ul-list">
                        <ul id="ul-list">
                        </ul>

                    </div>
                </div>

                <a class="check-btn h-sub-name" href="javascript:void(0)">查看全部</a>
            </div>
        </div>
        <p class="type-quantity">培育库企业总数：<span class="mr50" id="span_cultivationTypeCode_count">0户</span>其中：初创型企业：<span id="span_cultivationTypeCode_count_1">0户；</span>成长型企业：<span id="span_cultivationTypeCode_count_2">0户；</span>领军型企业：<span id="span_cultivationTypeCode_count_3">0户。</span></p>
        <div class="mt38">
            <div class="home-item h-statistics">
                <div class="h-contant clearfix">
                    <div class="line100"></div>
                    <div class="vertical-line"></div>
                    <div class="con-left h-con-item fl clearfix">
                    	<div id="main1_1" style="height:350px;"></div>
                    </div>
                    
                    <div class="con-right h-con-item fl clearfix">
                    	<div id="main1_2" style="height:350px;"></div>
                    </div>
                </div>
                
                <div class="h-contant clearfix" style="border-top: none; height: 350px;">
                    <div class="line100"></div>
                	<div id="main2_1" style="height:350px;"></div>
                </div>
                
            </div>
        </div>

        <div class="mt38 mb35 clearfix">
            <div class="home-item h-warning clearfix fl h-agency">
                <div class="h-title">
                    <i class="h-icon waitjob-icon"></i>
                    <span class="h-name">待办</span>
                </div>
                <div class="clear">
                    <div class="fl">
                        <img src="/img/sment/waitjob-pic.png" alt="">
                    </div>
                    <div class="h-con fl">
                    	<!-- 作中转链接  -->
                    	<a style="display: none;" href="/sment/entarchives/toEntarchivesEntlist" class="h-explain J_menuItem" id="fastLink1">添加企业入库</a><em></em>
                    	<a style="display: none;" href="/sment/entarchives/toEntarchivesList" class="h-explain J_menuItem" id="fastLink2">库内企业管理</a><em></em>
                    	<a style="display: none;" href="/sment/entarchives/monitor/list" class="h-explain J_menuItem" id="fastLink3">跟踪监测预警</a><em></em>
                    	  <!-- 作中转链接  -->
                    
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='1'>待添加初创型 [<span id="span_cultivationTypeCode_1">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='1'>待添加成长型 [<span id="span_cultivationTypeCode_2">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='1'>待添加领军型 [<span id="span_cultivationTypeCode_3">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='2'>待更新需求 [<span id="span_help_1">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='2'>待帮扶 [<span id="span_help_2" >0</span>]</a><em></em>
                    </div>

                </div>

            </div>
            <div class="home-item h-warning clearfix fr h-prompt">
                <div class="h-title">
                    <i class="h-icon tips-icon"></i>
                    <span class="h-name">提示</span>
                </div>
                <div class="clear">
                    <div class="fl">
                        <img src="/img/sment/tips-pic.png" alt="">
                    </div>
                    <div class="h-con fl">
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='3'>入库企业注销 [<span id="span_rkqyzx">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='3'>入库企业上规升级  [<span id="span_sgsj">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='3'>入库企业营收下降 [<span id="span_ysxj">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='3'>入库企业经营异常 [<span id="span_jyyc">0</span>]</a><em></em>
                        <a href="javascript:void(0);" class="h-explain fastLink" data-idx='3'>入库企业违法违规 [<span id="span_wfwg">0</span>]</a><em></em>
                    </div>

                </div>
            </div>
        </div>


    </div>
    <script type="text/javascript">
    	var deptCode = "${sessionScope.session_sys_user.departMent.deptCode }".substring(0,6);
    	if(deptCode == "330000"){
    		deptCode = "33";
    	}else{
    		deptCode = deptCode.substring(0,4);
    	}
    </script>
    <script src="/js/lib/echarts/echarts-all.js"></script>
    <script src="/js/lib/require.js"></script>
	<script src="/js/config.js"></script>
    <script src="/js/sment/sment_welcome.js"></script>

</body>
</html>