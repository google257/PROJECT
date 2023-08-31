package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.SignUpDato;
import com.app.dto.UserRequestDto;
import com.app.dto.UserResponseDto;
import com.app.entities.User;

public interface UserService {

	public User getAllUsers(String email,String password);
	public boolean addUser(SignUpDato user);
	public List<UserResponseDto> findByRole();
	public boolean updateUser(UserRequestDto  dto);
	//UserRequestDto registerNewUser(@Valid UserRequestDto dto);
}
