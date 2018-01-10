package com.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
	public UserModel findByEmail(String email);
}
