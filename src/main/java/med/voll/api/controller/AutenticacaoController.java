package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosCadastraUsuario;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.exeption.security.DadosToken;
import med.voll.api.infra.exeption.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping@Transactional
    public ResponseEntity login(@RequestBody @Valid DadosCadastraUsuario dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var token = tokenService.geraToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosToken(token));
    }
}
