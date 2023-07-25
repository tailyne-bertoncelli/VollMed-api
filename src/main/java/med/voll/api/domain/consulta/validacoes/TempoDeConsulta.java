package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCadastraConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class TempoDeConsulta implements ValidadosAgendaConsulta{

    public void validar(DadosCadastraConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(dataConsulta, agora).toMinutes();

        if (diferenca > 30){
            throw new RuntimeException("A consulta deve ser agendada com antecedencia minima de 30 minutos!");
        }
    }
}
