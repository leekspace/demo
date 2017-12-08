package com.leekli.aws.sqs;

public class ConsumerState {
    
    private  boolean stoped = false;
    private  boolean isStopping = false;
    private  boolean inited = false;
    private  boolean isRunning = false;
    
    public void inited(){
        inited = true;
        stoped = false;
        isStopping = false;
        isRunning = false; 
    }
    public void running(){
        isRunning = true;
    }
    public void stopping(){
        isStopping = true;
    }
    
    public void stoped(){
        isRunning = isStopping = false;
        stoped = true;
        inited = false;
    }
    public boolean isStoped() {
        return stoped;
    }
    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }
    public boolean isStopping() {
        return isStopping;
    }
    public void setStopping(boolean isStopping) {
        this.isStopping = isStopping;
    }
    public boolean isInited() {
        return inited;
    }
    public void setInited(boolean inited) {
        this.inited = inited;
    }
    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    
    
}
