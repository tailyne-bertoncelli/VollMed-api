package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastraConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimiteConsultaPaciente implements ValidadosAgendaConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosCadastraConsulta dados){
        var primeroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteTemOutraConsultaNoDia =  consultaRepository.existsByPacienteIdAndDataBetween(dados.paciente(), primeroHorario, ultimoHorario);
        if(pacienteTemOutraConsultaNoDia){
            throw new RuntimeException("O paciente já possui uma consulta agendada nesse dia!");
        }
    }
}
