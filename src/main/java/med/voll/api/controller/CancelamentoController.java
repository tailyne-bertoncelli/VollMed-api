package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.cancelamento.CancelamentoService;
import med.voll.api.domain.cancelamento.DadosCancelamento;
import med.voll.api.domain.cancelamento.DadosDetalhesCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancelamento")
public class CancelamentoController {
    @Autowired
    private CancelamentoService cancelamentoService;

    @PostMapping @Transactional
    public ResponseEntity cancelaConsulta(@RequestBody @Valid DadosCancelamento dados){
        cancelamentoService.cancelar(dados);
        System.out.println(dados);
        return ResponseEntity.ok(dados);
    }
}
