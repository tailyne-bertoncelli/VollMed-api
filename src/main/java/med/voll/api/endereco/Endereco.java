package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    String logradouro;
    String numero;
    String complemento;
    String bairro;
    String cidade;
    String uf;
    String cep;

    public Endereco(DadosEndereco dados){
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
    }

    public void atualizaEndereco(DadosEndereco dados) {
        if (dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if (dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null){
            this.uf = dados.uf();
        }
        if (dados.numero() != null){
            this.numero = dados.numero();
        }
        if (dados.complemento() != null){
            this.complemento = dados.complemento();
        }
        if (dados.cep() != null){
            this.cep = dados.cep();
        }
    }
}
