package br.com.luan_diniz.escolar.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginResponse {
    private String token;
    private LocalDateTime expiration;
    private String nome;
    private String email;
    private String role;
}
