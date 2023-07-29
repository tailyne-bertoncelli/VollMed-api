package med.voll.api.domain.cancelamento;

import jakarta.validation.constraints.NotNull;


public record DadosCancelamento(
        @NotNull
        Long consulta,
        @NotNull
        String motivo
) {
}
