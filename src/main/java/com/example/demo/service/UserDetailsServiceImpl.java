package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepo  userRepo;


    @Autowired
    private final PasswordEncoder passwordEncoder;
    /*
    * signup
    * checkifuserexists
    * loadByUsername
    *
    * */


    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not find the exception");
        }
        return new CustomUserDetails(user);
    }
    public User checkIfUserExists(UserDto userDto){
       return userRepo.findByUsername(userDto.getUsername());
    }
    public boolean signupUser (UserDto userDto){
        //insert validation
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(Objects.nonNull(checkIfUserExists(userDto))){
            return false;
        }
        userRepo.save(new User(userDto.getUsername(), userDto.getFullname(), userDto.getPassword(),userDto.getRole()));

        return true;
    }


}