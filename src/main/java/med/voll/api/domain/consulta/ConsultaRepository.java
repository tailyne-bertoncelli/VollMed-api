package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndData(Long medico, LocalDateTime data);
    boolean existsByPacienteIdAndDataBetween(Long paciente, LocalDateTime dataStart, LocalDateTime dataEnd);
}
