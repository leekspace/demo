package com.sengled.media.jffi.jna.storage;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sengled.media.jffi.interfaces.StorageInterface;
import com.sengled.media.jffi.jni.JNIFunction;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;


public class StorageInterfaceJNAImpl implements StorageInterface{

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageInterfaceJNAImpl.class);
	
	private static final StorageLibrary INSTANCE = StorageLibrary.INSTANCE;
	
	private static ConcurrentHashMap<String, Pointer> pointerMap = new ConcurrentHashMap<>();
	
	
	private static StorageInterface instance;  
	
	private StorageInterfaceJNAImpl (){}  
	
	public synchronized static  StorageInterface   getInstance() {  
		if (instance == null) {  
			 instance = new StorageInterfaceJNAImpl();  
		}
		return instance;
	}
	
	static{
		try {
			LOGGER.info("init...");
			String jnaHome = System.getProperty("jna.library.path");
			LOGGER.info("jna.library.path={}", jnaHome);
			
			INSTANCE.GInit();
			INSTANCE.SetLogCallback(new Pointer(JNIFunction.getInstance().getLog4CFunction()));
			LOGGER.info("init finished");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("StorageInterfaceImpl init failed. System exit.");
			System.exit(1);
		}
	}
	
	@Override
	public synchronized void startStorage(String token, String srcUrl, String dstUrl) {
		Pointer pointer = INSTANCE.StartStorage(token, srcUrl, dstUrl);
		pointerMap.put(token, pointer);
	}

	@Override
	public synchronized void stopStorage(String token) {
		Pointer opaque = pointerMap.get(token);
		if( null != opaque){
			INSTANCE.StopStorage(new PointerByReference(opaque) );
			pointerMap.remove(opaque);
		}
	}

	@Override
	public int tsToMp4(String token, String srcUrl, String destMp4Path) {
		int n = INSTANCE.TsPackMp42(srcUrl, destMp4Path, token);
		return n;
	}

    @Override
    public int flvPackMp42(String token, String srcUrl, String destMp4Path) {
        // TODO Auto-generated method stub
        return 0;
    }
}
