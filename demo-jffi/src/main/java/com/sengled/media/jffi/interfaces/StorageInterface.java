package com.sengled.media.jffi.interfaces;

public interface StorageInterface {
	
	void  startStorage(String token,String srcUrl,String dstUrl);
	void  stopStorage(String token);
	public int tsToMp4(String token, String srcUrl, String destMp4Path);
	public int flvPackMp42(String token, String srcUrl, String destMp4Path);
}
