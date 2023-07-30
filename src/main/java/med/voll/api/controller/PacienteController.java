package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping@Transactional
    public ResponseEntity cadastra(@RequestBody @Valid DadosPaciente paciente, UriComponentsBuilder uriBuider){
        var NovoPaciente = new Paciente(paciente);
        pacienteRepository.save(NovoPaciente);
        var uri = uriBuider.path("/paciente/{id}").buildAndExpand(NovoPaciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesPaciente(NovoPaciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaPacientes>> lista(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var pacientes = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListaPacientes::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesPaciente(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizaPaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizaPaciente(dados);
        return ResponseEntity.ok(new DadosDetalhesPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleta(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }
}
