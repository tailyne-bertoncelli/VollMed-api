package med.voll.api.paciente;

import med.voll.api.medico.DadosListaMedico;
import med.voll.api.medico.Medico;

public record DadosListaPacientes(String nome, String email, String cpf) {
    public DadosListaPacientes(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
