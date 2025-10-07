package br.com.luan_diniz.escolar.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Token {
    private String token;
    private LocalDateTime expiration;
}
