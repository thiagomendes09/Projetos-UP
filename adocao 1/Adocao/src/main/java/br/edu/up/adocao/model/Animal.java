package br.edu.up.adocao.model;

import br.edu.up.adocao.cli.Prompt;
import java.io.IOException;
import java.util.Formattable;
import java.util.Formatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Table(name = "animal")
@OptimisticLocking(type = OptimisticLockType.NONE)
public class Animal implements Formattable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String vetResponsavel;
    private String nome;
    private String dataNasc;
    private String localResgate;
    private String especie;
    private String raca;
    private String doencas;
    private String cirurgias;
    private Boolean castracao;
    private Boolean vermifugacao;
    private String vacinas;
    private String dataAdocao;
    private Boolean liberadoParaAdocao;
    private String dataRetorno;

    public Animal() {

    }

    public Animal(Long id, String vetResponsavel, String nome, String dataNasc, String localResgate, String especie,
                  String raca, String doencas, String cirurgias, Boolean castracao, Boolean vermifugacao,
                  String vacinas, String dataAdocao, Boolean liberadoParaAdocao, String dataRetorno) {
        this.id = id;
        this.vetResponsavel = vetResponsavel;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.localResgate = localResgate;
        this.especie = especie;
        this.raca = raca;
        this.doencas = doencas;
        this.cirurgias = cirurgias;
        this.castracao = castracao;
        this.vermifugacao = vermifugacao;
        this.vacinas = vacinas;
        this.dataAdocao = dataAdocao;
        this.liberadoParaAdocao = liberadoParaAdocao;
        this.dataRetorno = dataRetorno;
    }

    public String getVacinas() {
        return vacinas;
    }

    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVetResponsavel() {
        return vetResponsavel;
    }

    public void setVetResponsavel(String vetResponsavel) {
        this.vetResponsavel = vetResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getLocalResgate() {
        return localResgate;
    }

    public void setLocalResgate(String localResgate) {
        this.localResgate = localResgate;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }

    public Boolean getCastracao() {
        return castracao;
    }

    public void setCastracao(Boolean castracao) {
        this.castracao = castracao;
    }

    public Boolean getVermifugacao() {
        return vermifugacao;
    }

    public void setVermifugacao(Boolean vermifugacao) {
        this.vermifugacao = vermifugacao;
    }

    public String getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(String dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public Boolean getLiberadoParaAdocao() {
        return liberadoParaAdocao;
    }

    public void setLiberadoParaAdocao(Boolean liberadoParaAdocao) {
        this.liberadoParaAdocao = liberadoParaAdocao;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(getNome()).append("\n")
            .append("Data de nascimento: ").append(getDataNasc()).append("\n")
            .append("Local do resgate: ").append(getLocalResgate()).append("\n")
            .append("Especie: ").append(getEspecie()).append("\n")
            .append("Raca: ").append(getRaca()).append("\n")
            .append("Doenca: ").append(getDoencas()).append("\n")
            .append("Cirurgias: ").append(getCirurgias()).append("\n")
            .append("Castracao: ").append(Prompt.asString(getCastracao())).append("\n")
            .append("Vermifugacao: ").append(Prompt.asString(getVermifugacao())).append("\n")
            .append("Data da adocao: ").append(getDataAdocao()).append("\n")
            .append("Lberacao para adocao: ").append(Prompt.asString(getLiberadoParaAdocao())).append("\n")
            .append("Data para retornar: ").append(getDataRetorno()).append("\n");

        try {
            formatter.out().append(builder.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
