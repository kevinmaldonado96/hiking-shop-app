package org.example.hickingshop.services;

import org.example.hickingshop.configuration.custom.CustomUser;
import org.example.hickingshop.dto.UserDto;
import org.example.hickingshop.entities.PersonalInformation;
import org.example.hickingshop.entities.Role;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.repository.RoleRepository;
import org.example.hickingshop.repository.UserRepository;
import org.example.hickingshop.services.iservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto) {

        PersonalInformation personalInformation = PersonalInformation
                .builder()
                .address(userDto.getAddress())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .lastname(userDto.getLastName())
                .build();

        Optional<Role> roleOpt = roleRepository.getRoleByRole(userDto.getRole().toString());

        User user = User.builder()
                .personalInformation(personalInformation)
                .username(userDto.getUsername())
                .role(roleOpt.orElse(null))
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }
        User user = optUser.orElseThrow();

        return new CustomUser(user.getUsername(), user.getPassword(), user.getId(), user.getRoleAuthorities());
    }
}
