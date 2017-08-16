require(['component/iframeLayer', 'component/dataTable','common/util', 'common/http', 'handlebars', 'jquery','jquery.serialize','laydate'], function (layer, dataTable, util, http, handlebars) {
	init();
	
    /**
	 * 初始化函数集合
	 */
    function init() { 
    	initDataTable();
    	bind();
    }
    
    var table;

    /**
     * 初始化dataTable
     */
    function initDataTable() {
        table = dataTable.load({
            el: '#agent-table',
            showIndex: true,
            ajax: {
                url:window._CONFIG.chooseUrl+'/pub/server/sccheck/entagent/adjustListJSON',
                data:function(d){
	               	d.params = $("#chooseagentform").serializeObject();
                }
            },
            columns: [
                {data: "_idx", "className": "center"},
                {data: null, "className": "center"}, 
                {data: "agentName", "className": "center"},
                {data: "entName", "className": "center"}, 
                {data: null, "className": "center"}, 
                {data: "taskCode", "className": "center"},
                {data: "taskName", "className": "center"},
                {data: null, "className": "center"}
            ],
            columnDefs: [{
            	targets:1,
    	        render:function(data,type,row,meta){
    	        	return "<input type='checkbox' value='"+data.uid+"'/>";
    	       }
	        },{
	        	targets:4,
	        	render:function(data,type,row,meta){
	        		if(row.uniCode&&row.uniCode != "null"){
						return row.uniCode;
					}else{
						return row.regNO;
					}
	        	}
        	},{
	        	targets:7,
	        	render:function(data,type,row,meta){
	        		if(data.auditState == "1"){
    	        		return "待录入";
    	        	}else{
    	        		return "-";
    	        	}
	        	}
	          }
           ]
        });
    }
    
    $("#search").click(function(){
        table.ajax.reload();
    }); 
    
    function bind() {
        util.bindEvents([{
            el: '#chooseAll',
            event: 'click',
            handler: function () {
            	if($(this).prop("checked")){
					 $("#agent-table input").prop("checked",true);
				 }else{
					 $("#agent-table input").prop("checked",false);
				 }
            }
        },{
            el: '#adjust',
            event: 'click',
            handler: function () {
            	var checkFlag = false;
            	var uidArray = new Array();
            	$("#agent-table input").each(function(){
            		if($(this).prop("checked")){
            			checkFlag = true;
            			uidArray.push($(this).val());
            		}
            	});
            	var scentAgentUids = uidArray.toString();
            	if(checkFlag){
            		layer.dialog({
            			title:"执法人员调整",
            			area:['100%','100%'],
            			content:window._CONFIG.chooseUrl+'/pub/server/sccheck/entagent/adjustPage?scentAgentUids='+scentAgentUids,
            			callback:function(data){
            				if(data.reload){
            					table.ajax.reload();
            				}
            			}
            		});
                }else{
                	layer.msg("请先选择一条需要调整的记录！", {icon: 7,time: 1000});
                	return false;
                }	
            }
        }]);
    }
});
