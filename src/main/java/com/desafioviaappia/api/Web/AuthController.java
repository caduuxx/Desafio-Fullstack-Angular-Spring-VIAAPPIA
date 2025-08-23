package com.desafioviaappia.api.Web;

import com.desafioviaappia.api.Repositores.UserRepository;
import com.desafioviaappia.api.Security.JwtService;
import com.desafioviaappia.api.Web.DTO.LoginRequest;
import com.desafioviaappia.api.Web.DTO.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserRepository userRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserDetails user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateToken(user);
        return TokenResponse.bearer(token);
    }
}
