package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCadastraConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioFuncionamento implements ValidadosAgendaConsulta{
    public void validar(DadosCadastraConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoExpediente = dataConsulta.getHour() < 7;
        var depoisDoExpediente = dataConsulta.getHour() > 18;
        if (domingo == true){
            throw new RuntimeException("Hoje é domingo! Não é possivel fazer agnedamentos!");
        }
        if (antesDoExpediente == true || depoisDoExpediente == true){
            throw new RuntimeException("Os agendamentos devem ser feitos dentro do horario de atendimento! Segunda a Sábado, das 07:00 às 19:00;");
        }
    }
}
