package br.com.luan_diniz.escolar.service;

import br.com.luan_diniz.escolar.config.security.TokenService;
import br.com.luan_diniz.escolar.model.Usuario;
import br.com.luan_diniz.escolar.model.dto.LoginRequest;
import br.com.luan_diniz.escolar.model.dto.LoginResponse;
import br.com.luan_diniz.escolar.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponse login(LoginRequest data, HttpServletRequest request, AuthenticationManager authManager) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha());
        authManager.authenticate(authenticationToken);

        Usuario user = (Usuario) usuarioRepository.findByEmail(data.getEmail());
        var token = tokenService.generateToken(user);

        return LoginResponse.builder()
                .token(token.getToken())
                .expiration(token.getExpiration())
                .email(user.getEmail())
                .nome(user.getNome())
                .role(user.getRole())
                .build();
    }

    public Usuario register(Usuario user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        user.setDtCriacao(LocalDateTime.now());
        user.setDtAlteracao(LocalDateTime.now());
        return usuarioRepository.save(user);
    }
}
