package com.sengled.media.jffi.jna.storage;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
/**
 * JNA Wrapper for library <b>storage</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public interface StorageLibrary extends Library {
	public static final String JNA_LIBRARY_NAME = "rtsp2hls2mp4";
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(StorageLibrary.JNA_LIBRARY_NAME);
	public static final StorageLibrary INSTANCE = (StorageLibrary)Native.loadLibrary(StorageLibrary.JNA_LIBRARY_NAME, StorageLibrary.class);
	/**
	 * <i>native declaration : storage.h</i><br>
	 * enum values
	 */
	public static interface LOG_LEVEL {
		/** <i>native declaration : storage.h:6</i> */
		public static final int LOG_DEBUG = 1;
		/** <i>native declaration : storage.h:7</i> */
		public static final int LOG_INFO = 2;
		/** <i>native declaration : storage.h:8</i> */
		public static final int LOG_WARN = 3;
		/** <i>native declaration : storage.h:9</i> */
		public static final int LOG_ERROR = 4;
		/** <i>native declaration : storage.h:10</i> */
		public static final int LOG_FATAL = 5;
	};
	/**
	 * <i>native declaration : storage.h</i><br>
	 * enum values
	 */
	public static interface PACKSTAT {
		/** <i>native declaration : storage.h:15</i> */
		public static final int PACK_ERROR = -1;
		/** <i>native declaration : storage.h:16</i> */
		public static final int PACK_SUCCESS = 0;
	};
	/**
	 * Original signature : <code>void SetLogCallback(void*)</code><br>
	 * <i>native declaration : storage.h:19</i>
	 */
	void SetLogCallback(Pointer log_callback);
	/**
	 * Original signature : <code>void GInit()</code><br>
	 * <i>native declaration : storage.h:21</i>
	 */
	void GInit();
	/**
	 * Original signature : <code>void* StartStorage(const uint8_t*, const uint8_t*, const uint8_t*)</code><br>
	 * <i>native declaration : storage.h:22</i><br>
	 * @deprecated use the safer methods {@link #StartStorage(byte[], byte[], byte[])} and {@link #StartStorage(com.sun.jna.Pointer, com.sun.jna.Pointer, com.sun.jna.Pointer)} instead
	 */
	@Deprecated 
	Pointer StartStorage(Pointer token, Pointer src_url, Pointer dst_url);
	/**
	 * Original signature : <code>void* StartStorage(const uint8_t*, const uint8_t*, const uint8_t*)</code><br>
	 * <i>native declaration : storage.h:22</i>
	 */
	Pointer StartStorage(String token, String src_url, String dst_url);
	/**
	 * Original signature : <code>void StopStorage(void*)</code><br>
	 * <i>native declaration : storage.h:23</i>
	 */
	void StopStorage(PointerByReference pointerReference);
	/**
	 * pack a ts data to mp4<br>
	 * input<br>
	 * @param buffer is ts data<br>
	 * @param buffer_len is ts data len<br>
	 * @param desturl is for dest mp4 uri<br>
	 * @param token is device id<br>
	 * output<br>
	 * @return >= 0 is success, else is error<br>
	 * Original signature : <code>int TsPackMp4(const uint8_t*, const uint32_t, const uint8_t*, const uint8_t*)</code><br>
	 * <i>native declaration : storage.h:37</i><br>
	 * @deprecated use the safer methods {@link #TsPackMp4(byte[], int, byte[], byte[])} and {@link #TsPackMp4(com.sun.jna.Pointer, int, com.sun.jna.Pointer, com.sun.jna.Pointer)} instead
	 */
	@Deprecated 
	int TsPackMp4(Pointer buffer, int buffer_len, Pointer desturl, Pointer token);
	/**
	 * pack a ts data to mp4<br>
	 * input<br>
	 * @param buffer is ts data<br>
	 * @param buffer_len is ts data len<br>
	 * @param desturl is for dest mp4 uri<br>
	 * @param token is device id<br>
	 * output<br>
	 * @return >= 0 is success, else is error<br>
	 * Original signature : <code>int TsPackMp4(const uint8_t*, const uint32_t, const uint8_t*, const uint8_t*)</code><br>
	 * <i>native declaration : storage.h:37</i>
	 */
	int TsPackMp4(byte buffer[], int buffer_len, String desturl, String token);
	
	int TsPackMp42(String srcUrl,String destUrl,String token);
}