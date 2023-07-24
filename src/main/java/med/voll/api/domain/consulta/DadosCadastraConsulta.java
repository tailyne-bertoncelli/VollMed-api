package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;
import java.util.EmptyStackException;

public record DadosCadastraConsulta(
        @NotNull
        Long paciente,
        Long medico,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade) {
}
