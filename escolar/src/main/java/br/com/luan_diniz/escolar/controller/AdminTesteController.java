package br.com.luan_diniz.escolar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminTesteController {

    @GetMapping("/teste")
    public String getTeste() {
        return "Acesso autorizado à rota ADMIN!";
    }
}
