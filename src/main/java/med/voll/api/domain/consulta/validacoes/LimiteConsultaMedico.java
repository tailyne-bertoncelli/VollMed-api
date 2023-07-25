package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastraConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimiteConsultaMedico implements ValidadosAgendaConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosCadastraConsulta dados){
        var medicoComConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.medico(), dados.data());
        if (medicoComConsultaMesmoHorario){
            throw new RuntimeException("Esse medico já possui uma consulta nesse horario!");
        }
    }
}
