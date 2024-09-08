package com.hrp.user.microservices.config.security.userdetail;

import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.infrastructure.outputadapters.db.JpaUserEntityRepository;
import com.hrp.user.microservices.user.infrastructure.outputadapters.db.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private JpaUserEntityRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(JpaUserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userOpt = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userOpt.getUsername())
                .password(userOpt.getPassword())
                .roles(userOpt.getRole().toString())
                .build();
    }
}
