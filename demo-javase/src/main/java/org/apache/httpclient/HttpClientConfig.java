package org.apache.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpclient 长连接工具
 * @author liwei
 * @Date   2016年12月2日 下午3:13:09 
 * @Desc
 */

public class HttpClientConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConfig.class);
	
	//http连接池
	protected Integer httpPoolNum;
	
	//http对每个远程主机最大连接数
	protected Integer maxPerRoute;
	
	//每个主机keeplive时间
	protected Long keepaliveMs;
	//数据传输超时
	protected Integer 	socketTimeout;
	
	
	public HttpClient.HttpClientConfig getConfig(){
		HttpClient.HttpClientConfig config =   new HttpClient.HttpClientConfig();
		config.setHttpPoolNum(httpPoolNum);
		config.setKeepaliveMs(keepaliveMs);
		config.setMaxPerRoute(maxPerRoute);
		config.setSocketTimeout(socketTimeout);
		LOGGER.debug("httpclient config:{}",config);
		return config;
	}

	
	public IHttpClient getHttpClientUtil(){
		return new HttpClient(getConfig());
	}	
}
