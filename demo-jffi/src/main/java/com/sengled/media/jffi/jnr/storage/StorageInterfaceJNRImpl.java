package com.sengled.media.jffi.jnr.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sengled.media.jffi.interfaces.StorageInterface;
import com.sengled.media.jffi.jni.JNIFunction;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.PointerByReference;

public class StorageInterfaceJNRImpl implements StorageInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageInterfaceJNRImpl.class);
    private static final StorageLibrary INSTANCE = StorageLibrary.INSTANCE;
    private static final Runtime RUNTIME = StorageLibrary.RUNTIME;

    private static ConcurrentHashMap<String, Pointer> pointerMap = new ConcurrentHashMap<>();
    private static StorageInterface instance;

    private StorageInterfaceJNRImpl() {
    }

    public synchronized static StorageInterface getInstance() {
        LOGGER.debug("StorageInterface getInstance.");
        if (instance == null) {
            instance = new StorageInterfaceJNRImpl();
        }
        return instance;
    }

    static {
        try {
            LOGGER.info("init...");
            String jnaHome = System.getProperty("jna.library.path");
            LOGGER.info("jna.library.path={}", jnaHome);
            INSTANCE.GInit();
            INSTANCE.SetLogCallback(Pointer.wrap(RUNTIME, JNIFunction.getInstance().getLog4CFunction()));
            LOGGER.info("init finished");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("StorageInterfaceImpl init failed. System exit.");
            System.exit(1);
        }
    }

    @Override
    public synchronized void startStorage(String token, String srcUrl, String dstUrl) {
        LOGGER.debug("[{}],startStorage srcUrl:{},dstUrl:{}", token, srcUrl, dstUrl);
        Pointer pointer = INSTANCE.StartStorage(token, srcUrl, dstUrl);
        if (null == pointer) {
            LOGGER.error("[{}],startStorage opaque is null", token);
            return;
        }
        pointerMap.put(token, pointer);

    }

    @Override
    public synchronized void stopStorage(String token) {
        LOGGER.debug("[{}],stopStorage", token);
        Pointer opaque = pointerMap.get(token);

        if (null == opaque) {
            LOGGER.warn("[{}],stopStorage opaque is null", token);
            return;
        }
        if (0 == opaque.address()) {
            LOGGER.warn("[{}],stopStorage opaque address is 0", token);
            pointerMap.remove(opaque);
            return;
        }
        INSTANCE.StopStorage(new PointerByReference(opaque));
        pointerMap.remove(opaque);
    }

    @Override
    public int tsToMp4(String token, String srcUrl, String destMp4Path) {
        // try {
        // LOGGER.info("tsToMp4 tsData length:{}",tsData.length);
        // File tsFile = new File(destMp4Path+".ts");
        // FileOutputStream out = new FileOutputStream(tsFile);
        // out.write(tsData);
        // out.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        LOGGER.debug("[{}],tsToMp4 srcUrl:{},destMp4Path:{}", token, srcUrl, destMp4Path);
        int n = INSTANCE.TsPackMp42(srcUrl, destMp4Path, token);
        return n;
    }

    @Override
    public int flvPackMp42(String token, String srcUrl, String destMp4Path) {
        LOGGER.debug("[{}],tsToMp4 srcUrl:{},destMp4Path:{}", token, srcUrl, destMp4Path);
        return INSTANCE.FlvPackMp42(srcUrl, destMp4Path, token);
    }
}
