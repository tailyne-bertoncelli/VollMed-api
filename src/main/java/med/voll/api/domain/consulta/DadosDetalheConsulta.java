package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosDetalheConsulta(Long id, Long medico, Long paciente, LocalDateTime data) {

    public DadosDetalheConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

}
