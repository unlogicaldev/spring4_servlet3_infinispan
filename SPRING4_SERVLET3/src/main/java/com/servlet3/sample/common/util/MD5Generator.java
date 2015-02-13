package com.servlet3.sample.common.util;

import java.rmi.dgc.VMID;
import java.security.MessageDigest;

public class MD5Generator {

	    /**
	     * generate Unique Value
	     * @return String
	     * @throws Exception
	     */
	    public static String generateUniqueValue() throws Exception {
	    	return generateMD5Value((new VMID()).toString());
	    }

	    public static String generateMD5Value(String param) throws Exception {
	    	try {
	            MessageDigest algorithm = MessageDigest.getInstance("MD5");
	            algorithm.reset();
	            algorithm.update(param.getBytes());
	            byte[] messageDigest = algorithm.digest();
	            StringBuilder hexString = new StringBuilder();
	            for (int i = 0; i < messageDigest.length; i++) {
	                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	            }

	            return hexString.toString();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Token cannot be generated.", e);
	        }
	    }
}
