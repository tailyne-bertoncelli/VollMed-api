package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCadastraConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteAtivo implements ValidadosAgendaConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosCadastraConsulta dados){
        var buscaPaciente = dados.paciente();
        var paciente = pacienteRepository.pacienteAtivo(buscaPaciente);
        if (!paciente){
            throw new RuntimeException("O paciente escolhido está desativado!");
        }
    }
}
