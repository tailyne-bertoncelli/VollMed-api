package med.voll.api.domain.cancelamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CancelamentoService {

    @Autowired
    private CancelamentoRepository cancelamentoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    public DadosDetalhesCancelamento cancelar(DadosCancelamento dados){
        if (!consultaRepository.existsById(dados.consulta())){
            throw new RuntimeException("A consulta informada não existe");
        }

        var agora = LocalDateTime.now();
        var consulta = consultaRepository.getReferenceById(dados.consulta());
        var dataConsulta = consulta.getData();
        var tempoEntreCancelamento = Duration.between(agora, dataConsulta);
        System.out.println("Tempo até a consulta em horas: "+ tempoEntreCancelamento.toHours());

        Long tempoMinino = 24L;

        if (tempoEntreCancelamento.toHours() < tempoMinino && tempoEntreCancelamento.toHours() > 0){
            throw new RuntimeException("O tempo mínimo para cancelamento é de 24 horas!");
        }
        if (tempoEntreCancelamento.isNegative() == true){
            throw new RuntimeException("A consulta informada já foi realizada!");
        }
        var cancelamento = new Cancelamento(null,consulta, dados.motivo());
        cancelamentoRepository.save(cancelamento);

        return new DadosDetalhesCancelamento(cancelamento);
    }
}
