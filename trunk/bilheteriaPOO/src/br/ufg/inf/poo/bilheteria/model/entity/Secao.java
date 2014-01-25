/**
 * Classe Secao: Implementa uma entidade Secao (Arquibancadas, Camarotes,
 * Poltronas, Frisas, Tribunas, etc.).
 *
 * [RF 4] O local do evento pode ser dividido em seções. Cada seção tem um preço
 * diferenciado para os ingressos.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Secao extends Entidade {

    private String descricao;
    private float valor;

    public Secao(String descricao, float valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Secao{" + "descricao=" + descricao + ", valor=" + valor + '}';
    }
}
