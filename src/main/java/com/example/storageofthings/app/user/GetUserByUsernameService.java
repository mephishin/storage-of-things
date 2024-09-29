package com.example.storageofthings.app.user;

import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByUsernameService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found with name " + username));
    }
//    public User findUserById(Long userId) {
//        Optional<User> userFromDb = userJpaRepo.findById(userId);
//        return userFromDb.orElse(new User());
//    }
//
//    public List<User> allUsers() {
//        return userJpaRepo.findAll();
//    }
//

}
