package com.leekli.javase.bit;

public class Bit {
    public static void main(String[] args) {
        int i = 16777215;
        int h = i>>24;
        int d = i & 0x00ffffff;
        System.out.println( h);
        System.out.println( d);
        
        System.out.println(  (h<<24) + d);
        
        
        byte[] bb = new byte[3];
        
      
        
        
    }
}

