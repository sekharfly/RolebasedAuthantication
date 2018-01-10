package com.demo.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.model.UserModel;
import com.demo.model.UserRepository;
import com.demo.utils.Utils;
import com.google.gson.Gson;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	JSONArray jsonArray = new JSONArray();

	public ResponseEntity<String> createUser(UserModel userModel) throws JSONException {
		JSONObject objectToJson = null;
		ResponseEntity<String> responseEntity = null;
		try {
			String mDbytesToString = Utils.MDbytesToString(userModel.getPassword().getBytes());
			userModel.setPassword(mDbytesToString);
			userModel.setSalt(mDbytesToString);
			userModel.setActive(1);
			UserModel save = userRepository.save(userModel);
			objectToJson = userService.objectToJson(save);
			responseEntity = new ResponseEntity<String>(objectToJson.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = Utils.exceptionHandling(e);
		}
		return responseEntity;

	}

	public ResponseEntity<String> getAllUsers() throws JSONException {
		Iterable<UserModel> findAll = userRepository.findAll();
		JSONObject objectToJsonArray = userService.objectToJsonArray(findAll);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(objectToJsonArray.toString(),
				HttpStatus.ACCEPTED);
		return responseEntity;

	}

	public ResponseEntity<String> updateUser(UserModel userModel) throws JSONException {
		JSONObject objectToJson = null;
		ResponseEntity<String> responseEntity = null;
		try {
			String mDbytesToString = Utils.MDbytesToString(userModel.getPassword().getBytes());
			userModel.setPassword(mDbytesToString);
			userModel.setSalt(mDbytesToString);
			UserModel save = userRepository.save(userModel);
			objectToJson = userService.objectToJson(save);
			responseEntity = new ResponseEntity<String>(objectToJson.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = Utils.exceptionHandling(e);
		}

		// JSONObject json = Utils.getJSON("success");
		return responseEntity;
	}

	public ResponseEntity<String> deleteUser(int id) throws JSONException {
		JSONObject json = null;
		try {
			userRepository.delete(id);
			json = Utils.getJSON("sucess");
		} catch (Exception e) {
			json = Utils.getJSONError("no record found");
		}
		if (json.has("message")) {
			return new ResponseEntity<String>(json.toString(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		}

	}

	public ResponseEntity<String> specificUser(int id) throws JSONException {
		UserModel findOne = userRepository.findOne(id);
		JSONObject objectToJson = null;
		if (findOne == null) {
			objectToJson = Utils.getJSONError("no record found");
			return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_FOUND);
		} else {
			objectToJson = userService.objectToJson(findOne);
			return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.FOUND);
		}
	}

	public ResponseEntity<String> specificUserByEmail(JSONObject body) throws JSONException {
		String email = body.getString("email");
		String password = body.getString("password");
		UserModel findOne = userRepository.findByEmail(email);
		JSONObject objectToJson = null;
		Gson gson = new Gson();
		String json1 = "success fully logged in";
		String json2 = "password incorrect";

		String json4 = gson.toJson(json1);
		String json5 = gson.toJson(json2);

		if (findOne == null) {
			objectToJson = Utils.getJSONError("User doesn't exists");
			return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_FOUND);
		} else {
			String dbPassword = findOne.getPassword();
			String mDbytesToString = Utils.MDbytesToString(password.getBytes());
			if (dbPassword.equals(mDbytesToString)) {
				objectToJson = Utils.getSuccessJSON(json4);
				return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.CREATED);
			} else {
				objectToJson = Utils.getJSONError(json5);
				return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_FOUND);
			}
		}
	}

	public JSONObject objectToJson(UserModel userModel) throws JSONException {
		Gson gson = new Gson();
		String json = gson.toJson(userModel);
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jsonObject2 = Utils.getJSONObject(jsonObject);
		return jsonObject2;
	}

	public JSONObject objectToJsonArray(Iterable<UserModel> allUsers) throws JSONException {
		jsonArray = new JSONArray();
		int i = 0;
		for (UserModel userModel : allUsers) {
			JSONObject objectToJson = userService.toJson(userModel);
			jsonArray.put(i, objectToJson);
			++i;
		}
		JSONObject jsonArray2 = Utils.getJSONArray(jsonArray);
		return jsonArray2;
	}

	public JSONObject toJson(UserModel userModel) throws JSONException {
		Gson gson = new Gson();
		String json = gson.toJson(userModel);
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject;
	}

	// get by mail
	public ResponseEntity<String> profileEmail(String email) throws JSONException {
		// TODO Auto-generated method stub
		UserModel findByEmail = userRepository.findByEmail(email);
		JSONObject objectToJson = null;
		if (findByEmail == null) {
			objectToJson = Utils.getJSONError("no record found");
			return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.NOT_FOUND);
		} else {
			objectToJson = userService.objectToJson(findByEmail);
			return new ResponseEntity<String>(objectToJson.toString(), HttpStatus.OK);
		}
	}

	// change password
	public ResponseEntity<String> cpassword(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

/*
 * public void validation(UserModel userModel) throws UserValidation { if
 * (userModel.getPassword().length() < 7 && userModel.getPassword().length() >
 * 14) { if (userModel.getPassword().length() < 7) { throw new UserValidation(
 * "password too short"); } else { throw new UserValidation(
 * "You have exceed the length"); } }
 * 
 * }
 */
