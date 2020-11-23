package br.edu.up.adocao.model;

import br.edu.up.adocao.cli.Prompt;
import java.io.IOException;
import java.util.Formattable;
import java.util.Formatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adotante")

public class Adotante implements Formattable {

    @Id
    @GeneratedValue
    private Long id;
    private String cpf;
    private String nomeCompleto;
    private String endereco;
    private String telefone;
    private Boolean maltratosAnimais;

    public Adotante() {

    }

    public Adotante(Long id, String cpf, String nomeCompleto, String endereco, String telefone,
                    Boolean maltratosAnimais) {
        this.id = id;
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.telefone = telefone;
        this.maltratosAnimais = maltratosAnimais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getMaltratosAnimais() {
        return maltratosAnimais;
    }

    public void setMaltratosAnimais(Boolean maltratosAnimais) {
        this.maltratosAnimais = maltratosAnimais;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome Completo: ").append(getNomeCompleto()).append("\n")
            .append("Numero de indentificacao: ").append(getId()).append("\n")
            .append("Telefone: ").append(getTelefone()).append("\n")
            .append("Endereco: ").append(getEndereco()).append("\n")
            .append("Ja maltrato algum animal: ").append(Prompt.asString(getMaltratosAnimais())).append("\n")
            .append("CPF: ").append(getCpf()).append("\n");

        try {
            formatter.out().append(builder.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}



