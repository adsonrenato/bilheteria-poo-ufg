/**
 * Classe Evento: Implementa a entidade Evento (Show de Rock, Ópera,
 * Teatro,etc..
 *
 * [RF 1] Um evento tem nome, descrição, data e local.
 *
 * [RF 2] Um evento tem várias entradas disponíveis, cada uma relacionada a um
 * ingresso através do numero de identificação da posição.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

import br.ufg.inf.poo.bilheteria.utils.DateUtil;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Evento extends Entidade {

    private String nome;
    private String descricao;
    private Calendar data;
    private String local;

    public Evento(String nome, String descricao,
            Calendar data, String local) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Evento{" + "nome=" + nome 
                + ", descricao=" + descricao 
                + ", data=" + DateUtil.getStringFromCalendar(data) 
                + ", local=" + local + '}';
    }
}
