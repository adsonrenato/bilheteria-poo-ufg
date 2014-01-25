/**
 * Classe Compra: Implementa a entidade Compra.
 *
 * [RF 3] A compra de dois ingressos com o mesmo número de identificação não é
 * possível.
 *
 * [RF 6] Data e forma de pagamento devem ser guardadas para cada compra de
 * ingressos.
 */
package br.ufg.inf.poo.bilheteria.model.entity;

import br.ufg.inf.poo.bilheteria.utils.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public class Compra extends Entidade {
    /**
     * Desconto em R$ para ingressos abaixo de R$ 50,00
     */
    private final float DESCONTO_MINIMO = 1.00f;
    /**
     * Desconto em % para ingressos acima de 50,00
     */
    private final float PERCENTUAL_DESCONTO_MAXIMO = 0.02f;
    
    private Cliente cliente;
    private Calendar data;
    private FormaPagamento formaPagamento;
    private float valor;
    private List<Ingresso> ingressos;

    public Compra(Cliente cliente, Calendar data, FormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.ingressos = new ArrayList<Ingresso>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
    
    public void finaliza(){
        for(Ingresso ingresso : ingressos){
            float valorIngresso = ingresso.getValor();
            if(valorIngresso > 50){
                valor += valorIngresso * (1-PERCENTUAL_DESCONTO_MAXIMO);
            } else {
                valor += valorIngresso - DESCONTO_MINIMO;
            }
        }
    }

    @Override
    public String toString() {
        return "Compra{" + "cliente=" + cliente + ", data=" + DateUtil.getStringFromCalendar(data) + ", formaPagamento=" + formaPagamento + ", valor=" + valor + '}';
    }

    
    
    
}
