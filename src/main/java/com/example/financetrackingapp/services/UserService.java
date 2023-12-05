package com.example.financetrackingapp.services;

import com.example.financetrackingapp.RoleName;
import com.example.financetrackingapp.repositories.RoleRepository;
import com.example.financetrackingapp.repositories.UserRepository;
import com.example.financetrackingapp.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * todo Document type UserService
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<User> findByUserName(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));

        return new org.springframework.security.core.userdetails.User(
            user.getLogin(),
            user.getPassword(),
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user) {
        user.setRoles(List.of(roleRepository.findByRoleName(RoleName.ROLE_USER.name()).get()));
        userRepository.save(user);
    }
}
