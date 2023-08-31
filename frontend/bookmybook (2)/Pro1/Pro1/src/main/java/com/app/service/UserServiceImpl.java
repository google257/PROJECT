package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.SignUpDato;
//import com.app.dto.UserDTO;
import com.app.dto.UserRequestDto;
import com.app.dto.UserResponseDto;
import com.app.entities.User;

import com.app.repository.UserRepository;
import com.app.custom_exceptions.CustomException;
//import com.app.exceptions.CustomException;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRepository userRepo;

	//@Autowired
	//private PasswordEncoder enc;

	@Autowired 
	public ModelMapper mapper;
	
	@Override
	public User getAllUsers(String email, String password) {
		User user =userRepo.findByEmailAndPassword(email, password).orElseThrow(()->new CustomException("User Not Found"));
		String hashedPasswordFromDB = user.getPassword();
		if(BCrypt.checkpw(password, hashedPasswordFromDB)) {
			return user;
		} else {
			throw new CustomException("User Not Found");
		}
		
	}

	@Override
	public boolean addUser(SignUpDato user) {
		boolean status=false;
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		User newUser = new User(user.getUserName(),user.getRole(),user.getEmail(),hashedPassword,user.getMobileNumber());
		User userdata = userRepo.save(newUser);
		if(userdata!=null) {
			status=true;
		}
		return status;
	}

	@Override
	public List<UserResponseDto> findByRole() {
		List<UserResponseDto> dto =new ArrayList<>();
		List<User> users= userRepo.findByRole("admin");
		users.forEach((v)->dto.add(mapper.map(v, UserResponseDto.class)));
		return dto;
	}

	@Override
	public boolean updateUser(UserRequestDto dto) {
		boolean status = false;
		User user = new User(dto.getUserId(),dto.getUserName(), dto.getRole(), dto.getEmail(),dto.getPassword(),dto.getMobileNumber());
		User newUser = userRepo.save(user);
		if(newUser!=null) {status = true;}
		return status;
	}
	
//	@Override
//	public UserRequestDto registerNewUser(@Valid UserRequestDto dto) {
//		// dto --> entity
//		User userEntity = mapper.map(dto, User.class);
//		// encode passdword
//		userEntity.setPassword(enc.encode(userEntity.getPassword()));
//		//save 
//		User persistentEntity = userRepo.save(userEntity);
//		return mapper.map(persistentEntity, UserRequestDto.class);
//	}


}


