/**
 * Classe Cliente: Implementa a entidade Cliente.
 *
 * [RF 5] Devemos guardar as seguintes entradas do comprador: nome, número de
 * CPF, endereço completo e telefone de contato.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Cliente extends Entidade {

    private String cpf;
    private String nome;
    private Endereco endereco;
    private String telefone;

    public Cliente(String cpf, String nome, Endereco endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente(long id, String cpf, String nome,
            Endereco endereco, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + '}';
    }
}
