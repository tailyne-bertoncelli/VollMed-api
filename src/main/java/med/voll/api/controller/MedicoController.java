package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@RequestBody @Valid DadosMedico dados, UriComponentsBuilder uriBuider){
        var medico = new Medico(dados);
        medicoRepository.save(medico);
        var uri = uriBuider.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaMedico>> lista(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var medicos = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizaMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizaMedico(dados);
        return ResponseEntity.ok(new DadosDetalhesMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleta(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}
