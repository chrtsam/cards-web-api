package com.chrtsam.cards.api.services;

import com.chrtsam.cards.api.auth.AuthUser;
import com.chrtsam.cards.api.model.User;
import com.chrtsam.cards.api.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chris
 */
@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //the username is the user's email
        Optional<User> optional = userRepository.findByEmail(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User : " + username + " not found");
        }
        User user = optional.get();
        return new AuthUser(user.getId(), user.getRole(), user.getEmail(), user.getPassword(), new ArrayList<>());

    }
}
