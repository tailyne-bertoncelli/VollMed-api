package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.ValidadosAgendaConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadosAgendaConsulta> validacoes;

    public DadosDetalheConsulta agendar(DadosCadastraConsulta dados){
        if (!pacienteRepository.existsById(dados.paciente())){
            throw new RuntimeException("O paciente informado não existe");
        }
        if (dados.medico() != null && !medicoRepository.existsById(dados.medico())){
            throw new RuntimeException("O medico informado não existe");
        }

        validacoes.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.paciente());
        var medico = escolheMedico(dados);
        var consulta = new Consulta(null, paciente, medico, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalheConsulta(consulta);
    }

    private Medico escolheMedico(DadosCadastraConsulta dados) {
        if (dados.medico() != null){
            return medicoRepository.getReferenceById(dados.medico());
        }
        if (dados.especialidade() == null){
            throw new RuntimeException("Especialidade é obrigatório!");
        }
        return medicoRepository.escolheMedico(dados.especialidade(), dados.data());
    }

}
