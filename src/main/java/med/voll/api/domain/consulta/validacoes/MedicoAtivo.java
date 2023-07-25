package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCadastraConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoAtivo implements ValidadosAgendaConsulta{
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DadosCadastraConsulta dados){
        if (dados.medico() == null){
            return;
        }

        var buscaMedico = dados.medico();
        var medico = medicoRepository.findAtivoById(buscaMedico);
        if (!medico){
            throw new RuntimeException("Medico escolhido está desativado!");
        }
    }
}
