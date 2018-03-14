package com.sengled.media.jffi.jnr.storage;





import com.sengled.media.jffi.jnr.LoadUtil;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.In;
import jnr.ffi.byref.PointerByReference;
import jnr.ffi.types.u_int8_t;

public interface StorageLibrary {
	public static final String JNR_LIBRARY_NAME = "rtsp2hls2mp4";
	public static final StorageLibrary INSTANCE = LoadUtil.loadTestLib(JNR_LIBRARY_NAME,StorageLibrary.class);
	public static final Runtime RUNTIME = Runtime.getRuntime(INSTANCE);
	
	void SetLogCallback(Pointer callback);
	
	void  GInit();
	
	Pointer StartStorage(@In @u_int8_t String token,@In @u_int8_t String src_url,@In @u_int8_t String dst_url);
	
	void  StopStorage(@In PointerByReference opaque);
	
	int TsPackMp42(@In @u_int8_t String srcUrl,@In @u_int8_t String destUrl, @In @u_int8_t String token);
	
	int FlvPackMp42(@In @u_int8_t String srcUrl,@In @u_int8_t String destUrl,@In @u_int8_t String token);
	
}
