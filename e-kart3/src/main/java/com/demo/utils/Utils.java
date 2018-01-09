package com.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.controller.UsersController;

public class Utils {
	final static Logger logger = Logger.getLogger(UsersController.class);
	
	public static JSONObject getJSONError(JSONObject jsonError) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "error");
		jsonObject.put("error_message", jsonError);
		return jsonObject;
	}
	public static JSONObject getJSONError(String message) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "error");
		jsonObject.put("message", message);
		return jsonObject;
	}
	public static JSONObject getJSONObject(JSONObject jsonResponse) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "success");
		jsonObject.put("data", jsonResponse);
		return jsonObject;
	}
	public static JSONObject getJSONArray(JSONArray JSONArray) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "success");
		jsonObject.put("data", JSONArray);
		return jsonObject;
	}
	public static JSONObject getJSON(String message) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "success");
		return jsonObject;
	}
	public static JSONObject getSuccessJSON(String message) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "success");
		jsonObject.put("message", message);
		return jsonObject;
	}
	
	static public void runMe(String parameter) {
		
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
	
		logger.warn("This is warn : " + parameter);
	    logger.error("This is error : " + parameter);
	    logger.fatal("This is fatal : " + parameter);
	}
	
	
/*	public static String get_SHA_SecurePassword(String passwordToHash, byte[] salt, String algorithm) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}*/
	public static ResponseEntity<String> exceptionHandling(Exception e) throws JSONException {
		JSONObject objectToJson=null;
		ResponseEntity<String> responseEntity=null;
		String message="";
		if (e instanceof DataIntegrityViolationException) {
			objectToJson = Utils.getJSONError("eMail already exists");
			responseEntity = new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_ACCEPTABLE);
		} else if (e instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e)
					.getConstraintViolations();
			for (ConstraintViolation<?> violation : constraintViolations) {
				message = violation.getMessage();
			}
			objectToJson = Utils.getJSONError(message);
			responseEntity = new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_ACCEPTABLE);
		} else {
			objectToJson = Utils.getJSONError(e.getMessage());
			responseEntity = new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
		
	}
	public static String MDbytesToString(byte[] bytes) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");//SHA-1
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
