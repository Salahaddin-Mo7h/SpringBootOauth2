package com.galib.springBootOauth2.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.galib.springBootOauth2.config.AuthUserDetail;
import com.galib.springBootOauth2.permission.Permission;
import com.galib.springBootOauth2.permission.PermissionRepository;
import com.galib.springBootOauth2.role.Role;
import com.galib.springBootOauth2.role.RoleRepository;

//import com.jivita.rammps.acl.*;
//import com.jivita.rammps.acl.permission.Permission;
//import com.jivita.rammps.acl.permission.PermissionRepository;
//import com.jivita.rammps.acl.role.Role;
//import com.jivita.rammps.acl.role.RoleRepository;
//import com.jivita.rammps.acl.user.User;
//import com.jivita.rammps.acl.user.UserDetailServiceImpl;
//import com.jivita.rammps.acl.user.UserRepository;
//import com.jivita.rammps.config.*;

@Service
public class UsersService {
	@Autowired
	PermissionRepository prepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	RoleRepository rrepo;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	/*Permission Start*/
	List<Permission> getPermissionList(){
		List<Permission> permissions = prepo.findAll();
		return permissions;
	}
	
	Permission savePermission(Permission permission) {
		try {
			return prepo.save(permission);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	/*Permission End*/
	
	/*User Start*/
	List<Users> getUserList(){
		List<Users> users = new ArrayList<Users>();
		users = urepo.findAll();
		return users;
	}
	
	public UserDetails findUserByUserName(String name) {

		UserDetails user = null;

        try {
    		  user = userDetailServiceImpl.loadUserByUsername(name);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("exception ::::: "+e.getMessage());
		}
        return user;
	}
	
	//add default user
	public Users addUser(int[] roleArr) {
		Users user = new Users();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmail("mahbub_shohag@gmail.com");
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode("12345"));
		user.setUsername("mahbub_shohag");
		List<Role> roles = new ArrayList<Role>();
		for(int i=0;i<roleArr.length;i++) {
			roles.add(rrepo.findById(roleArr[i]).get());
		}
		user.setRoles(roles);
		return urepo.save(user);
	}
	
	//add default user
	public String add() {
		Users user = new Users();
		Role role = new Role();
		List<Role> roles = new ArrayList<Role>();
		List<Permission> permissions = new ArrayList<Permission>();
		Permission permission = new Permission();
		permission.setName("create_user");
		Permission p = prepo.save(permission);
		permissions.add(p);
		role.setName("ROLE_ADMIN");
		role.setPermissions(permissions);
		Role newRole = rrepo.save(role);
		roles.add(newRole);
		user.setUsername("dmcadmin");
		user.setPassword(passwordEncoder.encode("sAv3mIllI0ns"));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setEmail("shohag.jivita@gmail.com");
		user.setEnabled(true);
		user.setRoles(roles);
		urepo.save(user);
		return "";
	}
	
	public Users saveUser(SignupDTO signupDTO) {
		Role role = new Role();
		role = rrepo.findById(signupDTO.getRoleId()).get();
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		Users user = new Users();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmail(signupDTO.getEmail());
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
		user.setRoles(roles);
		user.setUsername(signupDTO.getUsername());
		Users savedUser = urepo.save(user);
		return savedUser;
	}
	/*User End*/
	
	/*Role Start*/
	public List<Role> getRoleList(){
		List<Role> roles = rrepo.findAll();
		return roles;
	}
	public Role saveRole(String name, int[] permissionids) {
		Role role = new Role();
		List<Permission> permissions = new ArrayList<Permission>();
		for(int i=0;i<permissionids.length;i++) {
			Permission permission = new Permission();
			permission = prepo.findById(permissionids[i]).get();
			permissions.add(permission);
		}
		role.setName(name);
		role.setPermissions(permissions);
		return rrepo.save(role);
	}
	/*Role End*/
}
