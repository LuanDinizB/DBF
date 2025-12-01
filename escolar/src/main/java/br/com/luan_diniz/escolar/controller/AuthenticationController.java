package br.com.luan_diniz.escolar.controller;

import br.com.luan_diniz.escolar.model.Usuario;
import br.com.luan_diniz.escolar.model.dto.LoginRequest;
import br.com.luan_diniz.escolar.model.dto.LoginResponse;
import br.com.luan_diniz.escolar.service.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest data, HttpServletRequest request) {
        var response = authorizationService.login(data, request, authenticationManager);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario data) {
        var user = authorizationService.register(data);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/encodepwd/{pwd}")
    public ResponseEntity<String> encodePwd(@PathVariable String pwd) {
        return ResponseEntity.ok(passwordEncoder.encode(pwd));
    }
}
