package com.example.jibibackend.Controller;

import com.example.jibibackend.dto.UserDTO;
import com.example.jibibackend.model.Role;
import com.example.jibibackend.model.User;
import com.example.jibibackend.repository.RoleRepository;
import com.example.jibibackend.repository.UserRepository;
import com.example.jibibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final JwtEncoder encoder;
    public AuthController(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    private UserService userService;

    @PostMapping("")
    public String auth(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        User admin = userService.findUserByName(authentication.getName());
        String firstName = admin.getFirstName(); // replace with actual logic to fetch first name
        String lastName = admin.getLastName();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("firstName", firstName)
                .claim("lastName", lastName)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}

