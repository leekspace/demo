package com.sengled.media.jffi.cmd;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sengled.media.jffi.interfaces.StorageInterface;

public class ProcessBuilderImpl implements StorageInterface{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessBuilderImpl.class);
    
    private static ConcurrentHashMap<String, Process> processMap = new ConcurrentHashMap<>();
 
    private static StorageInterface instance;
    
    private static String ffmpegCmdPath;
    
    private ProcessBuilderImpl (){}  
    
    static{
        LOGGER.info("init...");
        ffmpegCmdPath = System.getProperty("jni.library.path") + "/../bin/ffmpeg";
        LOGGER.info("Executable file  ffmpeg:{} ",ffmpegCmdPath);
        File f = new File(ffmpegCmdPath);
        if( ! f.exists() ){
            LOGGER.error("Executable file ffmpeg:{} isnot exists. ",ffmpegCmdPath);    
            System.exit(1);
        }
    }
    
    public synchronized static  StorageInterface getInstance() {
        LOGGER.debug("StorageInterface getInstance.");
        if (instance == null) {  
             instance = new ProcessBuilderImpl();  
        }
        return instance;
    }
    
    @Override
    public synchronized void startStorage(String token, String srcUrl, String dstUrl) {
//        Process p = processMap.get(token);
//        if( null != p && p.isAlive()){
//            p.destroyForcibly();
//            processMap.remove(token);
//        }
        
        List<String> cmd = Arrays.asList(ffmpegCmdPath
                ,"-re"
                ,"-rtsp_transport"  ,"tcp"
                ,"-i"               ,srcUrl
                ,"-vcodec"          ,"copy"
                ,"-acodec"          ,"aac"
                ,"-strict"          ,"-2"
                ,"-hls_list_size"   ,"1"
                ,"-hls_time"        ,"2"
                ,"-method"          ,"PUT"
                ,dstUrl
                );
        LOGGER.info("[{}],Command:{}",cmd.toString());
        ProcessBuilder pb = new ProcessBuilder(cmd );
        pb.redirectErrorStream(true);
        pb.redirectOutput(new File("/dev/null"));
        try {
            Process process = pb.start();
            processMap.put(token, process);            
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    @Override
    public synchronized void stopStorage(String token) {
        LOGGER.info("[{}],stopStorage",token);
        
        Process process = processMap.get(token);
        if( null == process) {
            LOGGER.warn("[{}],stopStorage process is null",token);
            return;
        }
        
        try {
            process.destroyForcibly();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }finally{
            processMap.remove(token);    
        }
    }

    @Override
    @Deprecated
    public int tsToMp4(String token, String srcUrl, String destMp4Path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Deprecated
    public int flvPackMp42(String token, String srcUrl, String destMp4Path) {
        // TODO Auto-generated method stub
        return 0;
    }
}
