package com.cz.hroauth.services;

import com.cz.hroauth.entities.User;
import com.cz.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        ResponseEntity<User> response = userFeignClient.findByEmail(email);
        User user = response.getBody();

        if (user == null) {
            logger.error("Email not found: {}", email);
            throw new UsernameNotFoundException("Email not found");
        }

        logger.info("Email found: {}", email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
