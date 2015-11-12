/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

import java.security.MessageDigest;

/**
 * 加密
 * @author Administrator
 */
public class MD5Util {
    public final static String mD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                    int val = ((int) md[i]) & 0xff;
                    sb.append(Integer.toHexString(val));

            }
            return sb.toString();
        } catch (Exception e) {
                return null;
        }
    }  
    
}
