package com.icinfo.cs.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgs.service.ServiceClient;

@Component("RestFulClient")
public class RestFulClient {
    
	@Autowired
	ServiceClient client;
	
	
	/**
	 * 调用远程服务
	 * @author yujingwei
	 * @param  parameterForSend
	 * @return string
	 */
	public  String callRemoteService(String parameterForSend) {
		return client.callRemoteService("SimpleWriteOff", parameterForSend);
	}
	
}
