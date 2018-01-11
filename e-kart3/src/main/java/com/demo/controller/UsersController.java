package com.demo.controller;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.UserModel;
import com.demo.service.UserService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/ekart")
public class UsersController {
	@Autowired
	private UserService userService;

	final static Logger logger = Logger.getLogger(UsersController.class);

	// create user
	@RequestMapping(method = RequestMethod.POST, value = "auth/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNewUser(@RequestBody UserModel userModel) throws JSONException {
		ResponseEntity<String> createUser = userService.createUser(userModel);
		return createUser;
	}

	// get all users
	@RequestMapping(method = RequestMethod.GET, value = "auth/allusers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllUsers() throws JSONException {
		ResponseEntity<String> allUsers = userService.getAllUsers();
		return allUsers;
	}

	// update user by Id
	@RequestMapping(method = RequestMethod.PUT, value = "/auth/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserById(@RequestBody UserModel userModel, @PathVariable int id)
			throws JSONException {
		userModel.setId(id);
		userModel.setActive(1);
		ResponseEntity<String> updateUser = userService.updateUser(userModel);
		return updateUser;
	}

	// Delete user by Id
	@RequestMapping(method = RequestMethod.DELETE, value = "/auth/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUserById(@PathVariable int id) throws JSONException {
		ResponseEntity<String> deleteUser = userService.deleteUser(id);
		return deleteUser;
	}

	// Get user by Id
	@RequestMapping(method = RequestMethod.GET, value = "/auth/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUserById(@PathVariable int id) throws JSONException {
		ResponseEntity<String> specificUser = userService.specificUser(id);
		return specificUser;
	}

	// authorization
	@RequestMapping(method = RequestMethod.POST, value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUserByEmail(@RequestBody String body) throws JSONException {
		JSONObject jsonObject = new JSONObject(body);
		ResponseEntity<String> specificUserByEmail = userService.specificUserByEmail(jsonObject);
		return specificUserByEmail;

	}

	// getProfile
	@RequestMapping(method = RequestMethod.GET, value = "/auth/profile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getByEmail(@RequestParam String email) throws JSONException {
		ResponseEntity<String> profileEmail = userService.profileEmail(email);
		return profileEmail;
		// return null;

	}

	// change password

	@RequestMapping(method = RequestMethod.PUT, value = "/auth/changepassword/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changePassword(@RequestBody UserModel userModel, @PathVariable int id)
			throws JSONException {
		userModel.setId(id);
		userModel.setActive(1);
		ResponseEntity<String> cpassword = userService.cpassword(userModel);
		return cpassword;
	}

}