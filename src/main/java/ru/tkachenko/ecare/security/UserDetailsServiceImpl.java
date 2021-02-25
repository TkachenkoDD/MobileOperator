package ru.tkachenko.ecare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tkachenko.ecare.dao.UserRepository;
import ru.tkachenko.ecare.models.Client;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

private final UserRepository userRepository;

@Autowired
public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromAdmin(client);
    }
}
