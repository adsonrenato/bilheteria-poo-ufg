/**
 * Interface IBilheteria: 
 * 
 */
package br.ufg.inf.poo.bilheteria.controller.interfaces;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public interface IBilheteria {

	/**
	 * [RF 04]Interface do método responsável por configurar os valores dos 
	 * ingressos de acordo com a respectiva seção.
	 */
	public void configuraValorPorSecao();
	
	/**
	 * [RF 06 e 07]Interface do método responsável por guardar as formas 
	 * de pagamento.
	 */
	public void guardaFormaPagamento();

	/**
	 * [RF 08]Interface do método responsável por calcular os descontos: 
	 * - desconto de 2% se compra feita no dinheiro maior que 50,00; e 
	 * - desconto de 1,0 se compra feita no dinheiro menor que 50,00.
	 */
	public void calculaDesconto();
	
	/**
	 * [RF 09]Interface do método responsável por verificar a quantidade de 
	 * ingressos por Seção.
	 */
	public void verificaQtdeIngressoSecao();
	
	/**
	 * [RF 09]Interface do método responsável por verificar a quantidade de 
	 * ingressos na sua totalidade.
	 */
	public void verificaQtdeIngressoTotal();
	
	/**
	 * [RF 10]Interface do método responsável por recuperar uma lista de 
	 * clientes de um determinado evento.
	 */
	public void recuperaListaClientesPorEvento();

	/**
	 * [RF 11]Interface do método responsável por recuperar uma lista de 
	 * eventos com suas respectivas receitas brutas (valor total arrecadado).
	 */
	public void recuperaEventosComReceitaBruta();

	/**
	 * [RF 12]Interface do método responsável por adicionar novo cliente na 
	 * lista de clientes.
	 */
	public void adicionaCliente();
	
	/**
	 * [RF 12 e 19]Interface do método responsável por excluir um cliente da 
	 * lista de clientes. Se cliente efetuou compra, NÃO pode ser excluído.
	 */
	public void excluiCliente();

	/**
	 * [RF 14]Interface do método responsável por adicionar nova compra de 
	 * ingressos.
	 */
	public void adicionaCompra();
	
	/**
	 * [RF 14 e 15]Interface do método responsável por excluir uma compra de 
	 * ingressos. A posição do ingresso deve ser liberada para nova compra.
	 */
	public void excluiCompra();
	
	/**
	 * [RF 16]Interface do método responsável por importar uma lista de 
	 * clientes de um arquivo.
	 */
	public void importaClientesDeArquivo();
	
	/**
	 * [RF 17]Interface do método responsável por importar uma lista de 
	 * locais de um arquivo.
	 */
	public void importaLocaisDeArquivo();
	
	/**
	 * [RF 18]Interface do método responsável por importar uma lista de 
	 * compras de um arquivo.
	 */
	public void importaComprasDeArquivo();
	
}
