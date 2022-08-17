package com.galib.springBootOauth2.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jivita.rammps.acl.permission.Permission;
//import com.jivita.rammps.acl.role.Role;
//import com.jivita.rammps.acl.user.User;
//import com.jivita.rammps.config.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	UsersService service;
	
	/*user start*/
	@PostMapping("/signup")
	public Users signup(@RequestParam("signupdata") String signupdata) throws JsonMappingException, JsonProcessingException {
		SignupDTO signupDTO = new SignupDTO();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		signupDTO = mapper.readValue(signupdata, SignupDTO.class);
		System.out.println(signupdata);
		return service.saveUser(signupDTO);
	}
	
	@GetMapping("/add")
	public String add() {
		return service.add();
	}
	
	@PostMapping("/add-user")
	public Users addUser(@RequestParam("roles") int[] roles) {
		return service.addUser(roles);
	}

	@GetMapping("/get-user-list")
	List<Users> getUserList(){
		List<Users> users = service.getUserList();
		return users;
	}
}
