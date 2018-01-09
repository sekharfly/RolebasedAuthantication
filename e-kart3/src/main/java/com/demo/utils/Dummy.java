package com.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Dummy {
	public static void main(String[] args) {
		String passwordToHash = "1";// c4ca4238a0b923820dcc509a6f75849b
		String generatedPassword = null;
		try {
			generatedPassword = MDbytesToString(passwordToHash.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
	}

	private static String MDbytesToString(byte[] bytes) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] mdbytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
