package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsulta agendaConsulta;

    @PostMapping@Transactional
    public ResponseEntity cadastra(@RequestBody @Valid DadosCadastraConsulta dados){
        agendaConsulta.agendar(dados);
        return ResponseEntity.ok(new DadosDetalheConsulta(null, null, null));
    }
}
