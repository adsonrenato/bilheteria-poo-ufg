package br.ufg.inf.poo.bilheteria.persistencia.impl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import br.ufg.inf.poo.bilheteria.utils.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CompraServiceHelper implements ServiceHelper<Compra> {

    private final int ATRIBUTO_CLIENTE = 1;
    private final int ATRIBUTO_DATA = 2;
    private final int ATRIBUTO_FORMA_PAGAMENTO = 3;
    private final int ATRIBUTO_VALOR = 4;
    private final String ARQUIVO = "compras.csv";
    private CSVToFile gerenciadorDeArquivo;

    public CompraServiceHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }

    @Override
    public boolean gravarObjeto(Compra compra) {
        ClienteServiceHelper clienteHelper = new ClienteServiceHelper();
        IngressoServiceHelper ingressoHelper = new IngressoServiceHelper();
        
        if (compra != null && !gerenciadorDeArquivo.contem(compra.getId())
                && compra.getIngressos().size() > 0) {
            clienteHelper.gravarObjeto(compra.getCliente());
            
            List<Ingresso> ingressos = compra.getIngressos();
            for(Ingresso ingresso : ingressos){
                ingresso.setCompra(compra);
                ingressoHelper.remove(ingresso);
                ingressoHelper.gravarObjeto(ingresso);
            }

            return gerenciadorDeArquivo.gravarLinha(toLine(compra));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Compra> ingressos) {
        for (Compra compra : ingressos) {
            this.gravarObjeto(compra);
        }
    }

    @Override
    public Compra getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Compra resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }

    public List<Compra> getComprasPorCliente(long idCliente) {
        List<String> listaObjetos = gerenciadorDeArquivo.getObjetosPorAtributo(idCliente, ATRIBUTO_CLIENTE);
        List<Compra> resultado = new ArrayList<Compra>();
        for (String entrada : listaObjetos) {
            Compra compra = getObject(entrada);
            resultado.add(compra);
        }
        return resultado;
    }

    @Override
    public List<Compra> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Compra> resultado = new ArrayList<Compra>();
        for (String entrada : listaObjetos) {
            Compra compra = getObject(entrada);
            resultado.add(compra);
        }
        return resultado;
    }

    @Override
    public boolean remove(Compra compra) {
        IngressoServiceHelper ingressoHelper = new IngressoServiceHelper();
        
        List<Ingresso> ingressos = ingressoHelper.
                getIngressosPorCompra(compra.getId());
        
        for(Ingresso ingresso : ingressos){
            ingressoHelper.remove(ingresso);
            ingresso.setCompra(null);
            ingressoHelper.gravarObjeto(ingresso);
        }
        
        return gerenciadorDeArquivo.removerLinha(compra.getId());
    }

    private String toLine(Compra compra) {
        StringBuilder sb = new StringBuilder();
        sb.append(compra.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(compra.getCliente().getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(DateUtil.getStringFromCalendar(compra.getData()));
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(compra.getFormaPagamento());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(compra.getValor());
        
        return sb.toString();
    }

    private Compra getObject(String line) {
        ClienteServiceHelper clienteHelper = new ClienteServiceHelper();
        IngressoServiceHelper ingressoHelper = new IngressoServiceHelper();
        
        String[] compra = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));

        long id = Long.parseLong(compra[0]);
        long idCliente = Long.parseLong(compra[ATRIBUTO_CLIENTE]);
        Calendar data = DateUtil.getCalendarFromString(compra[ATRIBUTO_DATA]);
        FormaPagamento formaPagamento = FormaPagamento.fromString(compra[ATRIBUTO_FORMA_PAGAMENTO]);
        float valor = Float.parseFloat(compra[ATRIBUTO_VALOR]);

        Cliente cliente = clienteHelper.getObjetoPorId(idCliente);

        Compra resultado = new Compra(cliente, data, formaPagamento);
        resultado.setId(id);
        resultado.setValor(valor);
        
        return resultado;
    }
}
