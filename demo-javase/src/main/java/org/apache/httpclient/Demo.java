package org.apache.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.httpclient.HttpClient.HttpClientConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Demo { 

	
    public static void main(String[] args) throws Exception {
        test();
        
    	
    	//test02();
    	
    }

	private static void test02() throws FileNotFoundException, IOException {
		File info = new File("C:\\Users\\media-liwei\\Desktop\\flv\\DEADE05A-2C1E-4A48-8A87-213156F22C86.inf");
    	File data = new File("C:\\Users\\media-liwei\\Desktop\\flv\\DEADE05A-2C1E-4A48-8A87-213156F22C86.dat");
    	System.out.println(data.length());
    	FileInputStream datain = new FileInputStream(data);
    	FileInputStream in = new FileInputStream(info);
    	byte[] b = new byte[(int) info.length()];
    	in.read(b);
    	
    	System.out.println(new String(b));
    	JSONArray json = JSONArray.parseArray(new String(b));
    	int index = 0;
    	for (Object object : json) {
    		index++;
			JSONObject obj = (JSONObject)object;
			System.out.println(obj.get("position") + "  "+ obj.get("length"));
			int length = (int)obj.get("length");
			int offset = (int)obj.get("position");
			byte[] bb = new byte[length	];
			datain.read(bb, 0, length);
			FileOutputStream out = new FileOutputStream(new File("D:\\test\\a"+index+".flv"));
			out.write(bb);
			out.close();
			System.out.println(datain.available());
		}
	}

	private static void test() throws InterruptedException {
		HttpClientConfig config = new HttpClientConfig();
        config.setHttpPoolNum(1);
        config.setKeepaliveMs(1000l);
        config.setMaxPerRoute(10);
        config.setSocketTimeout(30000);
        
        
        HttpClient client = new HttpClient(config);
        
        
        
        String url1= "http://10.100.102.31:8888/storage";
        String postdata1 = "{\"type\":1,\"storageType\":1,\"token\":\"XXXXXXXXXXXX\",\"flvUrl\":\"http://10.100.102.29:554/3596289F783F87494CE6AF1C273FDCD5.flv\",\"videoClipTime\":300,\"storageTime\":48,\"storageURL\":\"/mnt/videofile/\",\"timezone\":\"Asia/Shanghai\",\"command_type\":1}";
        
        String url2 = "http://54.223.99.190:8888/storage";
        String url3 = "http://54.223.120.87:8888/storage";
        String url4 = "http://54.222.183.119:8888/storage";//ssd
        
        
        String postdata21 = "{\"type\":1,\"storageType\":1,\"token\":\"XXXXXXXXXXXX\",\"flvUrl\":\"http://10.12.18.75:554/3596289F783F87494CE6AF1C273FDCD5.flv\",\"videoClipTime\":300,\"storageTime\":48,\"storageURL\":\"/mnt/videofile/\",\"timezone\":\"Asia/Shanghai\",\"command_type\":1}";
        String postdata22 = "{\"type\":1,\"storageType\":1,\"token\":\"XXXXXXXXXXXX\",\"flvUrl\":\"http://10.12.12.200:554/3596289F783F87494CE6AF1C273FDCD5.flv\",\"videoClipTime\":300,\"storageTime\":48,\"storageURL\":\"/mnt/videofile/\",\"timezone\":\"Asia/Shanghai\",\"command_type\":1}";
        
        
        
        
        
        String  url = url3;
        for(int i=0;i<100;i++){
        	JSONObject obj = null;
        	if( i%2 == 0){
        		obj = JSONObject.parseObject(postdata21);	
        	}else{
        		obj = JSONObject.parseObject(postdata22);
        	}
        	
            obj.put("token", "TOKEN"+RandomUtils.nextInt()+"INDEX"+i);
            HttpResponseResult result = client.post(url, obj.toJSONString());
            System.out.println(result.toString());
            if(i %17 == 0 && i!=0 ){
            	int rand = RandomUtils.nextInt(5000);
            	System.out.println("sleep:"+ 5000);
                Thread.sleep(5000);
            }
        }
	}
}
