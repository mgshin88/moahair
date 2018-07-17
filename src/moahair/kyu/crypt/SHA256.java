package moahair.kyu.crypt;

import java.security.MessageDigest;

/**
@file KISA_SHA256.java
@brief SHA256 암호 알고리즘
@author Copyright (c) 2013 by KISA
@remarks http://seed.kisa.or.kr/
*/

public class SHA256 {
	private static SHA256 s = new SHA256();
	
	private SHA256() {}

	public static SHA256 getInstance() {
		if(s == null) {
			s = new SHA256();
		}
		return s;
	}
	
	public String getSha256(byte[] txt) throws Exception{
	    StringBuffer sbuf = new StringBuffer();
	     
	    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	    mDigest.update(txt);
	     
	    byte[] msgStr = mDigest.digest() ;
	     
	    for(int i=0; i < msgStr.length; i++){
	        byte tmpStrByte = msgStr[i];
	        String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
	         
	        sbuf.append(tmpEncTxt) ;
	    }
	     
	    return sbuf.toString();
	}
	
}
