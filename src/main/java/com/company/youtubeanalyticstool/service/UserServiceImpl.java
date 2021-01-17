package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    public UserDAO save(UserDAO userDAO) {
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUsername(userDAO.getUsername());
        newUserDAO.setPassword(bcryptEncoder.encode(userDAO.getPassword()));
        newUserDAO.setRole(userDAO.getRole());
        return userRepository.save(newUserDAO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;


        UserDAO userDAO = userRepository.findByUsername(username);
        if (userDAO != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(userDAO.getRole()));
            return new User(userDAO.getUsername(), userDAO.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);	}
}
