package com.ssam.service;

import com.ssam.repository.UserRepository;
import com.ssam.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        var u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username :"+username+" not found"));

    }
}
