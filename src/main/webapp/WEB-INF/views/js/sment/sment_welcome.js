require(['component/iframeLayer','component/dataTable', 'common/util', 'common/http', 'handlebars', 'jquery','jquery.serialize','laydate','sment-contabs'], function (layer, dataTable, util, http, handlebars) {
	/*
	 * 初始化
	 */
	init();
	function init(){
		loadBulletins();
		txtplay();
		initEcharts();
		showEntarchivesCount();
		bind();
	}
	
	function getCountMap(){
		var map = [];
		map["33-1"]= 50000;// 浙江省-初创
        map["33-2"]= 10000;// 浙江省-成长
        map["33-3"]= 5000;// 浙江省-领军

        map["3301-1"]= 12375;// 杭州市-初创
        map["3301-2"]= 3000;// 杭州市-成长
        map["3301-3"]= 1500;// 杭州市-领军

        map["3302-1"]= 8250;// 宁波-初创
        map["3302-2"]= 2000;// 宁波-成长
        map["3302-3"]= 1000;// 宁波-领军

        map["3303-1"]= 6250;// 温州市-初创
        map["3303-2"]= 1500;// 温州市-成长
        map["3303-3"]= 750;// 温州市-领军

        map["3304-1"]= 3850;// 嘉兴市-初创
        map["3304-2"]= 600;// 嘉兴市-成长
        map["3304-3"]= 300;// 嘉兴市-领军

        map["3305-1"]= 3125;// 湖州市-初创
        map["3305-2"]= 500;// 湖州市-成长
        map["3305-3"]= 250;// 湖州市-领军

        map["3306-1"]= 4000;// 绍兴市-初创
        map["3306-2"]= 600;// 绍兴市-成长
        map["3306-3"]= 300;// 绍兴市-领军

        map["3307-1"]= 4500;// 金华市-初创
        map["3307-2"]= 600;// 金华市-成长
        map["3307-3"]= 300;// 金华市-领军

        map["3308-1"]= 900;// 衢州市-初创
        map["3308-2"]= 200;// 衢州市-成长
        map["3308-3"]= 100;// 衢州市-领军

        map["3309-1"]= 750;// 舟山市-初创
        map["3309-2"]= 150;// 舟山市-成长
        map["3309-3"]= 75;// 舟山市-领军

        map["3310-1"]= 5050;// 台州市-初创
        map["3310-2"]= 700;// 台州市-成长
        map["3310-3"]= 350;// 台州市-领军

        map["3325-1"]= 950;// 丽水市-初创
        map["3325-2"]= 150;// 丽水市-成长
        map["3325-3"]= 75;// 丽水市-领军
        return map;
	}
	
	function showEntarchivesCount(){
		debugger;
		var span_cultivationTypeCode_count=0;
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/count.json',
			data:{cultivationTypeCode:"1"},
			success:function(json){
				var map = getCountMap();
				var count = map[deptCode+"-1"] - json.data;
				span_cultivationTypeCode_count +=json.data;
				$("#span_cultivationTypeCode_count_1").text(json.data+"户；");
				$("#span_cultivationTypeCode_count").text(span_cultivationTypeCode_count+"户");
				if(count >0){
					$("#span_cultivationTypeCode_1").text(count);
				}
			}
		});
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/count.json',
			data:{cultivationTypeCode:"2"},
			success:function(json){
				var map = getCountMap();
				var count = map[deptCode+"-2"] - json.data;
				span_cultivationTypeCode_count +=json.data;
				$("#span_cultivationTypeCode_count_2").text(json.data+"户；");
				$("#span_cultivationTypeCode_count").text(span_cultivationTypeCode_count+"户");
				if(count >0){
					$("#span_cultivationTypeCode_2").text(count);
				}
			}
		});
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/count.json',
			data:{cultivationTypeCode:"3"},
			success:function(json){
				var map = getCountMap();
				var count = map[deptCode+"-3"] - json.data;
				span_cultivationTypeCode_count +=json.data;
				$("#span_cultivationTypeCode_count_3").text(json.data +"户；");
				$("#span_cultivationTypeCode_count").text(span_cultivationTypeCode_count+"户");
				if(count >0){
					$("#span_cultivationTypeCode_3").text(count);
				}
			}
		});
		
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countByHelp.json',
			data:{helpState:"1"},
			success:function(json){
				$("#span_help_1").text(json.data)
			}
		});
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countByHelp.json',
			data:{helpState:"2,3"},
			success:function(json){
				$("#span_help_2").text(json.data)
			}
		});
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countZx.json',
			success:function(json){
				$("#span_rkqyzx").text(json.data)
			}
		});
		
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countWfwgBySetDeptCode.json',
			success:function(json){
				$("#span_wfwg").text(json.data)
			}
		});
		
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countJyycBySetDeptCode.json',
			success:function(json){
				$("#span_jyyc").text(json.data)
			}
		});
		
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countSgsjBySetDeptCode.json',
			success:function(json){
				$("#span_sgsj").text(json.data)
			}
		});
		
		http.httpRequest({
			type:'POST',
			url:'/sment/entarchives/countJyxjBySetDeptCode.json',
			success:function(json){
				$("#span_ysxj").text(json.data)
			}
		});
		
	}
	
	// 初始化echarts图表
	function initEcharts(){
		initEcharts1_1();
		initEcharts1_2();
		initEcharts2_1();
	}

	// 小微企业地区分布
	function initEcharts1_1(){
		var myChart1_1 = echarts.init(document.getElementById('main1_1')); 

		http.httpRequest({
			type:'POST',
			url:'/sment/server/chartscount/mapList.json',
			success:function(json){
				option1_1 = {
			    title : {
			        text: '浙江省小微企业分布图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item'
			    },
			    dataRange: {
			        min: 3000,
			        max: 350000,
			        x: 'left',
			        y: 'bottom',
			        text:['高','低'],  
			        color:['orange','yellow'],
			        calculable : true
			    },
			    series : [
			        {
			            name: '数量',
			            type: 'map',
			            mapType: '浙江',
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            mapLocation: {
			                x: '25%',
			                height:'88%'
			            },
			            data:json.data
			        }
			    ]
			};
			                    
			myChart1_1.setOption(option1_1); 
			}
		
		});
	}

	// 存续数
	function initEcharts1_2(){
		var myChart1_2 = echarts.init(document.getElementById('main1_2'));
		http.httpRequest({
			type:'POST',
			url:'/sment/server/chartscount/zcList.json',
			success:function(json){
	        var option1_2 = {
	    		title : {
			        text: '小微企业在册数',
			        x:'center'
			    },
	            tooltip: {
	                show: true
	            },
	            xAxis : [
	                {
	                	name : '月份',
	                    type : 'category',
			        	axisLabel : {rotate:30},
	                    data : json.data['strList']
	                }
	            ],
	            yAxis : [
	                {
	                	name : '数量',
	                    type : 'value'
	                }
	            ],
	            series : [
	                {
	                    "name":"在册数",
	                    "type":"bar",
	                    "itemStyle" : {
	                        normal: {
	                            color: function(params) {
	                                var colorList = [
	                                  '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
	                                   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
	                                   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
	                                ];
	                                return colorList[params.dataIndex]
	                            },
	                            barBorderRadius:14
	                        },
	                        emphasis: {barBorderRadius: 14}
	                    },
	                    "data":json.data['dataList']
	                }
	            ]
	        };
	        myChart1_2.setOption(option1_2); 
			}
		});
	}
	
	// 当月新设数量
	function initEcharts2_1(){
		var myChart2_1 = echarts.init(document.getElementById('main2_1')); 
		http.httpRequest({
			type:'POST',
			url:'/sment/server/chartscount/xsList.json',
			success:function(json){
				var option2_1 = {
        		title : {
 			        text: '小微企业新设/注销数（月）',
 			        x:'center'
 			    },
 			   legend: {
 				     x: 'right',
 			        data:['新设','注销']
 			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : true,
			    xAxis : [
			        {
			        	name : '月份',
			            type : 'category',
			            boundaryGap : false,
			            data : json.data[0]['strList']
			        }
			    ],
			    yAxis : [
			        {
			        	name : '数量',
			            type : 'value'
			        } 
			    ],
			    series : [
			        {
			            name:'新设',
			            type:'line',
			            smooth:true,
			            symbolSize:'0',
			            itemStyle: {normal: {areaStyle: {type: 'default'},lineStyle:{width:4}}},
			            data: json.data[0]['dataList']
			        },
			        {
			            name:'注销',
			            type:'line',
			            smooth:true,
			            symbolSize:'0',
			            itemStyle: {normal: {areaStyle: {type: 'default'},lineStyle:{width:4}}},
			            data: json.data[1]['dataList']
			        }
			    ]
			};
        	myChart2_1.setOption(option2_1); 
        }});
	}

	
	function loadBulletins(){
		http.httpRequest({
			type:'POST',
			url:'/sment/bulletinsRecord/list.json',
			async:false,
			success:function(data){
				$('#ul-list').html("");
				if (data.status == 'success') {
					var length = data.data.length;
					var bodyHtml = "";
					if(length>0){
						for(var i=0;i<length;i++){
							bodyHtml = bodyHtml
								+ "<li class='clearfix'><a  href='javascript:void(0);' bulletinId='"
								+ data.data[i].bulletinId
								+ "' isRead='"+data.data[i].isRead
								+ "' class='li-red light js-read'><i class='li-disc'></i><span class='h-time'>"
								+ data.data[i].setTime + "</span>" +data.data[i].title+
								"[ " +data.data[i].setDeptname+" ]</a></li>";
						}
					}
					$('#ul-list').append(bodyHtml);
				}
			}
		});
	}
	function bind(){
		util.bindEvents([
		     {
		    	 el:'.js-read',
		    	 event:'click',
		    	 handler:function(event){
		    		 var index=$(this).closest('li').index();
	                    var uid=$(this).attr("bulletinId");
	                    var isRead=$(this).attr("isRead");
	                    var parent =$(this).closest('li');//取到当前元素的父元素
	                    var bulletinUrl="";
	                    if(isRead=='1'){
	                        bulletinUrl='/sment/bulletinsRecord/scan?bulletinId=' + uid;
	                    }else{
	                    	bulletinUrl='/sment/bulletinsRecord/read?bulletinId=' + uid;
	                    }
	                    layer.dialog({
	                        title: '查看公告',
	                        area: ['1024px', '600px'],
	                        content: bulletinUrl,
	                        callback: function (data) { }
	                    });
	                    
		    	 }
		     },{
		    	el:'.h-sub-name',
		    	event:'click',
		    	handler:function(event){
		    		layer.dialog({
                        title: '查看更多',
                        area: ['1024px', '600px'],
                        content: '/sment/bulletins/toMoreList',
                        callback: function (data) {
                        }
                    });
		    		//var url = '/sment/bulletins/toMoreList';
	        		//window.open(url,'_blank','');
		    	}
		     },{
		    	 el:'.fastLink',
		    	 event:'click',
		    	 handler:function(event){
		    		 $('#fastLink'+$(this).attr('data-idx')).click();
		    	 }
		     }
		])
	}

	/**
	 * 文字上下滚动函数
	 */
	function txtplay() {
		var ulList = document.getElementById('js-ul-list');
		ulList.innerHTML+=ulList.innerHTML;
		ulList.style.height = 'auto';
		var len = ulList.offsetHeight/2-6;
		document.title = ulList.offsetHeight;
		ulList.style.height = '20px';
		//向上滚动
		var time;
		ulList.onmouseover=function(){clearInterval(time);}
		ulList.onmouseout= play
		function play(){
			time = setInterval(function(){
				ulList.scrollTop++;
				if(ulList.scrollTop>len){
					ulList.scrollTop = 0;
				}
			},50);
		}
		play();
	}

})
