package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid DadosMedico medico){
        medicoRepository.save(new Medico(medico));
        System.out.println(medico);
    }

    @GetMapping
    public Page<DadosListaMedico> lista(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
    }

    @PutMapping @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaMedico medico){
        var buscaMedico = medicoRepository.getReferenceById(medico.id());
        buscaMedico.atualizaMedico(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }
}



