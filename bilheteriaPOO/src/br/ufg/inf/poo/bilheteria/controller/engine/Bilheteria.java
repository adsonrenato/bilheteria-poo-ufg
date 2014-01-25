/*
 * Classe Bilheteria: Classe que implementa os métodos necessários para o 
 * funcionamento da aplicação.
 */
package br.ufg.inf.poo.bilheteria.controller.engine;

import br.ufg.inf.poo.bilheteria.controller.interfaces.IBilheteria;

public class Bilheteria implements IBilheteria {

    /**
     * [RF 04] Método responsável por configurar os valores dos ingressos de
     * acordo com a respectiva seção.
     */
    @Override
    public void configuraValorPorSecao() {
        //codigo aqui
    }

    /**
     * [RF 06 e 07] Método responsável por guardar as formas de pagamento.
     */
    @Override
    public void guardaFormaPagamento() {
        //codigo aqui
    }

    /**
     * [RF 08] Método responsável por calcular os descontos: - desconto de 2% se
     * compra feita no dinheiro maior que 50,00; e - desconto de 1,0 se compra
     * feita no dinheiro menor que 50,00.
     */
    @Override
    public void calculaDesconto() {
        //codigo aqui
    }

    /**
     * [RF 09] Método responsável por verificar a quantidade de ingressos por
     * Seção.
     */
    @Override
    public void verificaQtdeIngressoSecao() {
        //codigo aqui
    }

    /**
     * [RF 09] Método responsável por verificar a quantidade de ingressos na sua
     * totalidade.
     */
    @Override
    public void verificaQtdeIngressoTotal() {
        //codigo aqui
    }

    /**
     * [RF 10] Método responsável por recuperar uma lista de clientes de um
     * determinado evento.
     */
    @Override
    public void recuperaListaClientesPorEvento() {
        //codigo aqui
    }

    /**
     * [RF 11] Método responsável por recuperar uma lista de eventos com suas
     * respectivas receitas brutas (valor total arrecadado).
     */
    @Override
    public void recuperaEventosComReceitaBruta() {
        //codigo aqui
    }

    /**
     * [RF 12] Método responsável por adicionar novo cliente na lista de
     * clientes.
     */
    @Override
    public void adicionaCliente() {
        //codigo aqui
    }

    /**
     * [RF 12 e 19] Método responsável por excluir um cliente da lista de
     * clientes. Se cliente efetuou compra, NÃO pode ser excluído.
     */
    @Override
    public void excluiCliente() {
        //codigo aqui
    }

    /**
     * [RF 14] Método responsável por adicionar nova compra de ingressos.
     */
    @Override
    public void adicionaCompra() {
        //codigo aqui
    }

    /**
     * [RF 14 e 15] Método responsável por excluir uma compra de ingressos.
     * NOTA: A posição do ingresso deve ser liberada para nova compra.
     */
    @Override
    public void excluiCompra() {
        //codigo aqui
    }

    /**
     * [RF 16] Método responsável por importar uma lista de clientes de um
     * arquivo.
     */
    @Override
    public void importaClientesDeArquivo() {
        //codigo aqui
    }

    /**
     * [RF 17] Método responsável por importar uma lista de locais de um
     * arquivo.
     */
    @Override
    public void importaLocaisDeArquivo() {
        //codigo aqui
    }

    /**
     * [RF 18] Método responsável por importar uma lista de compras de um
     * arquivo.
     */
    @Override
    public void importaComprasDeArquivo() {
        //codigo aqui
    }
}
