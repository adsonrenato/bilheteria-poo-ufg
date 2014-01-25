/**
 * Enum FormaPagamento: Descreve as formas de pagamento disponíveis para
 * compras.
 *
 * [RF 7] As formas de pagamento disponível são: DINHEIRO e CARTÃO.
 *
 */
package br.ufg.inf.poo.bilheteria.model.entity;

/**
 *
 * @author Emerson
 * @version 1.0
 */
public enum FormaPagamento {

    DINHEIRO("Dinheiro"), 
    CARTAO("Cartão");
    
    private String descricao;
    
    private FormaPagamento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static FormaPagamento fromString(String text) {
    if (text != null) {
      for (FormaPagamento b : FormaPagamento.values()) {
        if (text.equalsIgnoreCase(b.descricao)) {
          return b;
        }
      }
    }
    return null;
  }
}
