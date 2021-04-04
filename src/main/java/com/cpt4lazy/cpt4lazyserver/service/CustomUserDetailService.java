package com.cpt4lazy.cpt4lazyserver.service;
import com.cpt4lazy.cpt4lazyserver.dao.UserRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRoleRepo.save(user.getRole());
		userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		
		if(user != null) {
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			//auths.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
			auths.add(new SimpleGrantedAuthority("USER"));
			return buildUserForAuthentication(user, auths);
		}
		else {
			throw new UsernameNotFoundException("username not found");
		}
		
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
}
