package med.voll.api.domain.cancelamento;

public record DadosDetalhesCancelamento(Long consulta, String motivo) {

    public DadosDetalhesCancelamento(Cancelamento cancelamento) {
        this(cancelamento.getConsulta().getId(), cancelamento.getMotivo());
    }
}
