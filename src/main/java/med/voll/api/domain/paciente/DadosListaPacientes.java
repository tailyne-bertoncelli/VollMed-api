package med.voll.api.domain.paciente;

public record DadosListaPacientes(String nome, String email, String cpf) {
    public DadosListaPacientes(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
