/**
 * Classe Endereco: Responsável por disponibilizar o endereço completo do
 * comprador.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Endereco extends Entidade {

    private String cep;
    private String pais;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;

    public Endereco(String cep, String pais, String uf, String cidade, String bairro, String logradouro) {
        this.cep = cep;
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
    }

    public Endereco(long id, String cep, String pais, String uf, String cidade,
            String bairro, String logradouro) {
        this.id = id;
        this.cep = cep;
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public String toString() {
        return "Endereco{" + "cep=" + cep + ", pais=" + pais + ", uf=" + uf + ", cidade=" + cidade + ", bairro=" + bairro + ", logradouro=" + logradouro + '}';
    }
}
