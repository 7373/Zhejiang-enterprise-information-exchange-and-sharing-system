require(['component/iframeLayer', 'component/dataTable','common/util', 'common/http', 'handlebars', 
         'jquery.validate','jquery','jquery.serialize','common/validateRules','laydate'], function (layer, dataTable, util, http, handlebars) {
	init();
	var backtable;
	var agenttable;
	var infotable;
//    var chooseUrl = window._CONFIG.chooseUrl;
    /**
	 * 初始化函数集合
	 */
    function init() {
        $("#clientNameCount").rules("add", {number: true});
        bind();
        infotable = dataTable.load({
            // 需要初始化dataTable的dom元素
            el: '#info-table',
            // 是否加索引值
            showIndex: true,
            scrollX: true,
            ajax: {
                url:window._CONFIG.chooseUrl+'/server/sccheck/pubscent/list.json',
                data:function(d){
	               	d.params = $("#paramForm").serializeObject();
                }
            },
            // 需注意如果data没有对应的字段的，请设置为null，不然ie下会出错;
            // 'className'不要写成class
            columns: [
                {data: "_idx","className": "center"},
                {data: "regNO","className": "center"}, 
                {data: "entName", "className": "center"}, 
                {data: "estDate", "className": "center"},
                {data: "entTypeCatg", "className": "center"},
                {data: "lastCheckTime","className": "center"},
                {data: "specialName", "className": "center"},
                {data: "regOrgName","className": "center"},
                {data: "localAdmName", "className": "center"}
                ],
            columnDefs: [{
            	targets:1,
    	        render:function(data,type,row,meta){
    	        	if(row.uniCode != null && row.uniCode != ""){
	   					return row.uniCode;
	   				}else{
	   					return row.regNO;
	   				}
    	       }
	        },
	        {
            	targets:4,
    	        render:function(data,type,row,meta){
    	        	if(data != null && data != ""){
	   					if(data == '50'){//个体户
	   						return "个体户";
	   					}else if(data == '23'){
	   						return "外企代表机构";
	   					}else if(data == '16' || data == '17'){
	   						return "农专社";
	   					}else{
	   						return "企业";
	   					}
	   				}else{
	   					return '';
	   				}
    	       }
	        }
            ]
        });
        initownbackTable();
        
    }
    
    
    /**
     * 初始化dataTable
     */
    function initownbackTable() {
    	backtable = dataTable.load({
            el: "#backTableId",
            showIndex: true,
            scrollX: true,
            ajax: {
                url:window._CONFIG.chooseUrl+"/server/sccheck/pubscentAgentBack/list.json",
                data:function(d){
            		d.params = $.extend({}, $("#paramForm").serializeObject(), {"agentName" : $("#agentName").val()});               		
                }
            },
            columns: [
                {data: "id", width:"40px", "className": "center"},
                {data: "id", width:"50px", "className": "center"},
                {data: "agentName",width:"100px", "className": "center"},
                {data: "agentSex",  width:"100px","className": "center"}, 
                {data: "studyLevel", width:"80px", "className": "center"}, 
                {data: "agentAge", width:"100px","className": "center"},
                {data: "deptCatg",  width:"250px","className": "center"},
                {data: "expertFlag", width:"100px", "className": "center"},
                {data: "agentArea", width:"200px", "className": "center"},
                {data: "agentRange", width:"100px", "className": "center"}
            ],
            columnDefs: [
				{
					targets:1,
				    render:function(data,type,row,meta){
						    return "<input value='" + row.agentUid + "' name = 'checkboxlist' class='chb checkbox' type='checkbox' />";
				   }
				},
				{
					targets:3,
					render:function(data,type,row,meta){
						if( data == '1' ){
				    		return "男";
						}else if(data == '2'){
						    return "女";
						}else{
							return data;
						}
					}
				},
				{
					targets:4,
					render:function(data,type,row,meta){
						if( data == '1' ){
				    		return "大专";
						}else if( data == '2' ){
				    		return "本科";
						}else if( data == '3' ){
				    		return "研究生";
						}else if( data == '4' ){
				    		return "初中";
						}else if( data == '5' ){
				    		return "高中";
						}else if( data == '6' ){
				    		return "硕士";
						}else if( data == '7' ){
				    		return "博士";
						}else if(data == '8'){
						    return "小学";
						}else if(data == '9'){
						    return "博士后";
						}else{
							  return data;
						}
					}
				},
				{
					targets:6,
					render:function(data,type,row,meta){
						if(data!=null&&data!=""){
							var str = data.replace("1","工商行政管理类");
							str = str.replace("2","食品药品管理类");
							str = str.replace("3","质量技术监督类");
							str = str.replace("4","安全生产类");
							str = str.replace("4","物价管理类");
							return str;
						}else{
							return ""; 
						}
					}
				},
				{
					targets:7,
					render:function(data,type,row,meta){
			    		if(data == null || data == "" || data == "undefined"){
			    			return "";
			    		}else if( data == 'N'){
				    		return "<span style='color:red;'>否</span>";
						}else{
					    	return data.replace('Y,','').replace('1','保化组长').replace('2','药品GSP').replace('3','药品GMP').replace('4','医疗器械GSP').replace('5','医疗器械GMP').replace('Y','是');
						}
					}
				},
				{
					targets:8,
					render:function(data,type,row,meta){
						if( data == 'ZJ'){
							return "省本级";
						}else if( data == 'H'){
							return "杭州";
						}else if( data == 'N'){
							return "宁波";
						}else if( data == 'W'){
							return "温州";
						}else if( data == 'Jx'){
							return "嘉兴";
						}else if( data == 'Hu'){
							return "湖州";
						}else if( data == 'S'){
							return "绍兴";
						}else if( data == 'J'){
							return "金华";
						}else if( data == 'Q'){
							return "衢州";
						}else if( data == 'Z'){
							return "舟山";
						}else if( data == 'T'){
							return "台州";
						}else if( data == 'L'){
							return "丽水";
						}else{
							return "";
						}
					}
				},
               {
	        	targets:9,
	        	render:function(data,type,row,meta){
	        		if(row.unitLevel!=null&&row.unitLevel!=""){
	        			 if(row.unitLevel=="1"){
	        				 return "市级";
	        			 } else if(row.unitLevel=="2"){
	        				 return "县级";
	        			 }else{
	        				 return "省级";
	        			 }
	        		}else{
	        			return "";
	        		}
	        		
	        	}
	        }
            ],
		    "fnDrawCallback": function (oSettings) {
		    	var searchMap = $("#paramForm").serializeObject();
		    	http.httpRequest({ 
	                url: window._CONFIG.chooseUrl+"/server/sccheck/pubscentAgentBack/DataCountList",
	                serializable: false,
//	                type:'post',
	                data: {params:searchMap} ,
	                success: function (data) {
	                	var pubScentAgentCount = data.data;
	                    if (data.status == 'success') {  
	                    	//限制对象数量
							$("#clientNameCount").val(pubScentAgentCount);
							//限制原因数量
	                    }else{
							$("#clientNameCount").val(0);
	                    }
	                }
	            });
			}
        });
    }
    
    //表格之外的查询按钮事件
    $("#searchChecker").click(function(){
    	backtable.ajax.reload(); 
    });
    
    
    function bind() {
    	util.bindEvents([
        {
            el: '.view',
            event: 'click',
            handler: function () { 
           	 	var uid=$("#uid").val();
            	layer.dialog({
                    title: '查看详情',
                    area: ['100%', '100%'],
                    content:window._CONFIG.chooseUrl+'/server/sccheck/scplantask/addOrEditView?flag=3&uid='+uid,
                    callback: function(data) {
                    	 
                    }
                })
            }
        },
        {
      	  el: '#checkAll',  //全选
            event: 'click',
            handler: function () {
            	var allChecked= $("#checkAll").prop("checked");   
            	$(".chb").prop("checked", allChecked);
            }
      },
        {
        		el: '#deleteAll',
        		event: 'click',
        		handler: function () {
               	 var deptTaskUid= $("#taskUid").val();
  	           layer.confirm('确定清空本部门下的执法人员吗？', {
	               icon: 2,
	               title: '提示'
	           },  function(){
	        	   http.httpRequest({
       				url: window._CONFIG.chooseUrl+'/server/sccheck/pubscentAgentBack/deleteRtnAll',
       				serializable: false, 
       				data: {deptTaskUid:deptTaskUid},
       				type:"post",
       				success: function (data) {
       					layer.msg(data.msg, {time: 1000}, function () {
       						backtable.ajax.reload();
       					});
       				}
       			});
	           });
  	           }
        	},{
        		el: '#delete',
        		event: 'click',
        		handler: function () {
        	           var ids=new Array();
        	            $(":checkbox[name=checkboxlist]:checked").each(function(k,v){
        	           	ids[k]=this.value; 
        	            });
        	            
        	            if(ids[0]==""||ids[0]==null){
        	                layer.msg("请您至少选择一位执法人员!", {icon: 7,time: 1000});
        	           	 return false;
        	            }
        	          
        	           layer.confirm('确定删除选中的执法人员吗？', {
        	               icon: 2,
        	               title: '提示'
        	           },
        	           function(index) {
        	        	   http.httpRequest({
        	                   url: window._CONFIG.chooseUrl+'/server/sccheck/pubscentAgentBack/deleteRtn',
        	                   data: {
        	                	  agentIds:ids.toString(),
        	                	  deptTaskUid:$("#taskUid").val()
        	                   },
        	                   type: 'post',
        	                   success: function(data) {  
        	                               layer.msg(data.msg, {
        	                                   time: 500
        	                               },
        	                               function() {
        	                            	   backtable.ajax.reload();
        	                               });
        	                       }
        	        	   });
        	           });
        		}
        	},{
                el: '#addAgentId',
                event: 'click',
                handler: function () {
                	 var deptTaskUid= $("#taskUid").val();
                	 var deptCode= $("#deptCode").val();
                	 var scTypeWay= $("#scTypeWay").val();
                     layer.dialog({
                         title: '选择执法人员',
                         area: ['100%', '90%'],
                         content: window._CONFIG.chooseUrl+'/server/drcheck/pubscagent/choosepage?deptTaskUid='+deptTaskUid+'&deptCode='+deptCode+'&scTypeWay='+scTypeWay,
                         callback: function (data) {
                        	//重新加载列表数据
                             if (data.reload) {
                            	 backtable.ajax.reload();
                             }
                         }
                     });
                }
            },{
	            el: '#clean',
	            event: 'click',
	            handler: function () {
	            	http.httpRequest({
	     				url: '/syn/pub/server/drcheck/pubsctaskagent/clean',
	     				serializable: false, 
	     				data: {taskNO:$("#deptTaskUid").val()},
	     				type:"post",
	     				success: function (data) {
	     					layer.msg(data.msg, {time: 1000}, function () {
	     						agenttable.ajax.reload();
	     					});
	     				}
	     			});
	            }
        },{
            el: '#random',
            event: 'click',
            handler: function () {
            	var scTypeWay= $("#scTypeWay").val();
    			var deptTaskUid= $("#taskUid").val();
    			var checkNumber= $("#checkNumber").val();
            	var pubScentAgentCount = $("#clientNameCount").val();
            	var reg = /^\d+$/;
            	if(checkNumber == '' || (! reg.test(checkNumber)) || parseInt(checkNumber) <= 0 ){
	                layer.msg("请输入正确的正整数作为检查人员总数 !", {icon: 7,time: 3000});
	                return false;
            	}
            	if(parseInt(checkNumber) > parseInt(pubScentAgentCount) ){
            		layer.msg("设置的检查人员总数不能大于待选库中检查人员总数 !", {icon: 7,time: 3000});
            		return false;
            	}
            	layer.confirm('系统将随机匹配企业和执法人员，可能需要一段时间，是否继续？', {icon: 2, title: '提示'}, function (index) {
	    			http.httpRequest({
	    				url: '/reg/pub/server/sccheck/entagent/random',
	    				serializable: false, 
	    				data: {deptTaskUid:deptTaskUid,randomType:scTypeWay,groupNum:checkNumber},
	    				type:"post",
	    				success: function (data) {
	    					layer.msg(data.msg, {time: 1000}, function () {
	    						if(data.status == 'success'){
	    							$("#addAgentId").hide();
	    							$("#delete").hide();
	    							$("#deleteAll").hide();
	    							$("#random").hide();
	    							$("#checkNumber").attr("disabled","disabled")
	    							$("#view").show();
	    						}
	    					});
	    				}
	    			});
            	});
            }
        },{
            el: '#view',
            event: 'click',
            handler: function () {
            	var deptTaskUid= $("#taskUid").val();
            	layer.dialog({
                    title: '企业和执法人员随机配对结果',
                    area: ['80%', '80%'],
                    content: window._CONFIG.chooseUrl+'/pub/server/sccheck/entagent/view?deptTaskUid='+deptTaskUid,
                    callback: function (data) {
                    }
                });
            }
        },{
            el: '#close',
            event: 'click',
            handler: function () {
            	layer.close({reload: false});
            }
        }]);
    }
});
