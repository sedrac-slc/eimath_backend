package com.ei.math.config;

import com.ei.math.entity.UserPeople;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.ei.math.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPeople userPeople = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("User no found"));
        return new User(userPeople.getUsername(), userPeople.getPassword(),true,true,true,true, userPeople.getRoles());
    }    

}
