package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void cadastra(@RequestBody @Valid DadosPaciente paciente){
        pacienteRepository.save(new Paciente(paciente));
        System.out.println(paciente);
    }

    @GetMapping
    public Page<DadosListaPacientes> lista(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListaPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaPaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizaPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }
}
