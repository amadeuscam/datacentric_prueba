package com.amadeuscam.datacentric.Auth;

import com.amadeuscam.datacentric.Jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amadeuscam.datacentric.User.Role;
import com.amadeuscam.datacentric.User.User;
import com.amadeuscam.datacentric.User.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {

        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre y usuario no pueden ser vacios");
        }

        try {
            userRepository.findByUsernameAndActive(request.getUsername(), true).orElseThrow(IllegalArgumentException::new);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();


        } catch (BadCredentialsException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad credentials", exception);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Usuario no activo", exception);
        }


    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .active(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}
