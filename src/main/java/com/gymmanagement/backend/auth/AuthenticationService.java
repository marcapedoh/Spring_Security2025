package com.gymmanagement.backend.auth;


import com.gymmanagement.backend.config.JwtService;
import com.gymmanagement.backend.exception.EntityNotFoundException;
import com.gymmanagement.backend.exception.ErrorCodes;
import com.gymmanagement.backend.exception.InvalidEntityException;
import com.gymmanagement.backend.model.Role;
import com.gymmanagement.backend.model.User;
import com.gymmanagement.backend.model.UserRole;
import com.gymmanagement.backend.repository.RoleRepository;
import com.gymmanagement.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken="";
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
            jwtToken=jwtService.generateToken(user);
        }
        UserDetails user1=userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        if(!StringUtils.hasLength(user1.getUsername())){
            log.warn("le username de ce utilisateur est nul");
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request) {
        Role role=null;
        if(Objects.equals(request.getUserRole(), "USER")){
             role= Role.builder()
                    .roleName("USER")
                    .build();
             roleRepository.save(role);
        }else if(Objects.equals(request.getUserRole(), "ADMIN")){
             role=Role.builder()
                    .roleName("ADMIN")
                    .build();
             roleRepository.save(role);
        }
        if(role==null){
            throw new InvalidEntityException("Le role que vous passez est null", ErrorCodes.ROLE_NOT_VALID);
        }
        var user= User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(role)
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}