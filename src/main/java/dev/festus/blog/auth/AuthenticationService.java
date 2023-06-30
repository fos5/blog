package dev.festus.blog.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.festus.blog.appUser.AppUser;
import dev.festus.blog.appUser.AppUserRepository;
import dev.festus.blog.auth.registration.RegistrationRequest;
import dev.festus.blog.auth.token.Token;
import dev.festus.blog.auth.token.TokenRepository;
import dev.festus.blog.auth.token.TokenType;
import dev.festus.blog.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request){
        var appUser = AppUser.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        AppUser savedUser = repository.save(appUser);
        String jwtToken = jwtService.generateToken(appUser);
        var refreshToken = jwtService.generateRefreshToken(appUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AppUser appUser = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(appUser);
        var refreshToken = jwtService.generateRefreshToken(appUser);
        revokeAllUserTokens(appUser);
        saveUserToken(appUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void saveUserToken(AppUser appUser, String jwtToken){
        var token = Token.builder()
                .user(appUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(AppUser appUser){
        List<Token> allValidTokenByUserId = tokenRepository.findAllValidTokenByUserId(appUser.getId());
        if (allValidTokenByUserId.isEmpty()){
            return;
        }
        allValidTokenByUserId.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(allValidTokenByUserId);
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader("Authorisation");
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null){
            var user = this.repository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken,user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(accessToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}
