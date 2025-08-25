package com.desafioviaappia.api.Web;

import com.desafioviaappia.api.Repositores.UserRepository;
import com.desafioviaappia.api.Security.JwtService;
import com.desafioviaappia.api.Web.DTO.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para login e geração de token JWT")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserRepository userRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Operation(
            summary = "Realiza login e retorna o token JWT",
            description = """
                    Esse endpoint autentica o usuário com email e senha.
                    
                    Depois de receber o token JWT, você deve utilizá-lo nos outros endpoints 
                    adicionando no cabeçalho da requisição:
                    
                    `Authorization: Bearer {token}`
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Login",
                                            value = """
                                                    {
                                                      "email": "usuario@email.com",
                                                      "password": "senha123"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso. Retorna o token JWT.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Exemplo de Resposta",
                                                    value = """
                                                            {
                                                              "token": "eyJhbGciOiJIUzI1NiIsInR..."
                                                            }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Falha de autenticação. Credenciais inválidas.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Exemplo de Erro",
                                                    value = """
                                                            {
                                                              "error": "Credenciais inválidas"
                                                            }
                                                            """
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        log.info(">> /auth/login chamado para {}", request.email());
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            log.warn("Falha de autenticação para {}: {}", request.email(), e.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
        }
    }
}
