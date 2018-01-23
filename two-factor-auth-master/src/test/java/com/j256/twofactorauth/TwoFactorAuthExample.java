package com.j256.twofactorauth;

import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Little test program to show how to use the TwoFactorAuthUtil utility class.
 * 
 * See: https://github.com/j256/java-two-factor-auth
 * 
 * @author sekharfly
 */
public class TwoFactorAuthExample {

	// public static void main(String[] args) throws Exception {
		
	 public void doGet(HttpServletRequest request , HttpServletResponse response) throws GeneralSecurityException, InterruptedException{

		 
		 String userotp = request.getParameter("otp");
		// String base32Secret = twoFactorAuthUtil.generateBase32Secret();
		String base32Secret = "NY4A5CPJZ46LXZCP";

		System.out.println("secret = " + base32Secret);

		// this is the name of the key which can be displayed by the
		// authenticator program
		String keyId = "sekharfly@gmail.com";
		// generate the QR code
		System.out.println("Image url = " + TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, base32Secret));
		// we can display this image to the user to let them load it into their
		// auth program

		// we can use the code here and compare it against user input
		String code = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret);

		/*
		 * this loop shows how the number changes over time
		 */
		HttpSession session = request.getSession();
		while (true) {
			long diff = TimeBasedOneTimePasswordUtil.DEFAULT_TIME_STEP_SECONDS
					- ((System.currentTimeMillis() / 1000) % TimeBasedOneTimePasswordUtil.DEFAULT_TIME_STEP_SECONDS);
			code = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret);
			System.out.println("Secret code = " + code + ", change in " + diff + " seconds");
			Thread.sleep(1000);
			session.setAttribute("code", code);
			
			if(code == userotp){
				System.out.println("correct");
			}else{
				System.out.println("incorrect");
			}
		
		}
	}
	
	
}
