<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>股东及出资信息列表页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/reg.client.css"/>"/>
</head>
<body>
<jsp:include page="../../common/header.jsp"></jsp:include>
<div class="banner-bg">
    <div class="mod banner">
        <h4>欢迎您！${midBaseInfoDto.entName}<font>（登记机关：${midBaseInfoDto.regOrgName}）</font></h4>
        <p><em>统一社会信用代码：3300001006258 </em><em>注册号：3300001006258</em></p>
    </div>
</div>
<div class="mod pdt20">
    <div class="mod-cont">
        <h4>股东及出资信息</h4>
        <div class="">
            <table border="0" cellspacing="0" cellpadding="0" class="table-ordinary">
                <tr>
                    <th width="80" rowspan="2">股东</th>
                    <th width="80" rowspan="2">认缴额<br/>（万元）</th>
                    <th width="80" rowspan="2">实缴额<br/>（万元）</th>
                    <th colspan="4">实缴明细</th>
                    <th colspan="4">认缴明细</th>
                    <th width="60" rowspan="2">操作</th>
                </tr>
                <tr>
                    <th>认缴<br/>出资方式</th>
                    <th>认缴出资额<br/>（万元）</th>
                    <th>认缴<br/>出资日期</th>
                    <th>公示<br/>状态</th>
                    <th>实缴<br/>出资方式</th>
                    <th>实缴出资额<br/>（万元）</th>
                    <th>实缴<br/>出资日期</th>
                    <th>公示<br/>状态</th>
                </tr>
                <tr>
                    <td rowspan="4">李自强<br/>（已公示）</td>
                    <td rowspan="4">2000</td>
                    <td rowspan="4">2000</td>
                    <td>实物</td>
                    <td>500</td>
                    <td>2014年1月10日</td>
                    <td>已公示</td>
                    <td>货币</td>
                    <td>500</td>
                    <td>2014年2月25日</td>
                    <td>已公示</td>
                    <td rowspan="4">
                        <a href="#">修改</a><br/>
                        <a href="#">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>实物</td>
                    <td>500</td>
                    <td>2014年1月10日</td>
                    <td>已公示</td>
                    <td>货币</td>
                    <td>500</td>
                    <td>2014年2月25日</td>
                    <td>已公示</td>
                </tr>
                <tr>
                    <td>实物</td>
                    <td>500</td>
                    <td>2014年1月10日</td>
                    <td>已公示</td>
                    <td>货币</td>
                    <td>500</td>
                    <td>2014年2月25日</td>
                    <td>已公示</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>招商局集团有限公司<br/>（已公示）</td>
                    <td>2000</td>
                    <td>2000</td>
                    <td>实物</td>
                    <td>500</td>
                    <td>2014年1月10日</td>
                    <td>已公示</td>
                    <td>货币</td>
                    <td>500</td>
                    <td>2014年2月25日</td>
                    <td>已公示</td>
                    <td>
                        <a href="#">修改</a><br/>
                        <a href="#">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <p class="btn"><input type="button" value="添加" class="btn-common"><input type="button" value="保存" class="btn-common"><input type="button" value="打印预览" class="btn-common"><input type="button" value="保存并公示" class="btn-common"></p>
    </div>
</div>
<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>