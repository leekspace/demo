package com.leekli.javase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5ForPhp {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(phpMd5("Shanghai"));
    }
    
    public static String phpMd5(String input) throws NoSuchAlgorithmException {
        String result = input;
        if(input != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while(result.length() < 32) { //40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }
}
