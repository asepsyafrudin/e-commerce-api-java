package co.g2academy.StoreFront.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import co.g2academy.StoreFront.repository.UserRepository;


@Component
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        co.g2academy.StoreFront.entity.User user =  userRepository.findByuserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        } 
        return new User(user.getUserName(), user.getPassword(), Collections.emptyList());
    }

}
