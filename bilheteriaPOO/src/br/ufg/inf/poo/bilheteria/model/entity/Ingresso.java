/**
 * Classe Ingresso: Implementa a entidade Ingresso.
 *
 * [RF 2] Um evento tem várias entradas disponíveis, cada uma relacionada a um
 * ingresso através do numero de identificação da posição.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Ingresso extends Entidade {

    private int posicao;
    private Evento evento;
    private Secao secao;
    private Compra compra;

    public Ingresso(int posicao, Evento evento, Secao secao) {
        this.posicao = posicao;
        this.evento = evento;
        this.secao = secao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public float getValor(){
        return secao.getValor();
    }

    @Override
    public String toString() {
        return "Ingresso{" + "posicao=" + posicao + ", evento=" + evento + ", secao=" + secao + ", compra=" + compra + '}';
    }
}
