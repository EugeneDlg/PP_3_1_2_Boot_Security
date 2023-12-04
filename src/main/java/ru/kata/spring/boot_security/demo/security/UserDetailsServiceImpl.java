package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("The user with this email not found"));
        System.out.println("LLL. username: " + user.getUsername());
        System.out.println("LLL. roles size: " + user.getRoles().size());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
    }
}
