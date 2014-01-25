package br.ufg.inf.poo.bilheteria.persistencia.impl;

import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import java.util.ArrayList;
import java.util.List;

public class IngressoGravacaoHelper implements ServiceHelper<Ingresso> {
    private final int ATRIBUTO_EVENTO = 1;
    private final int ATRIBUTO_POSICAO = 2;
    private final int ATRIBUTO_SECAO = 3;
    private final int ATRIBUTO_COMPRA = 4;
    
    private final String ARQUIVO = "ingressos.csv";
    private CSVToFile gerenciadorDeArquivo;
    
    public IngressoGravacaoHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }

    @Override
    public boolean gravarObjeto(Ingresso ingresso) {
        EventoGravacaoHelper eventoHelper = new EventoGravacaoHelper();
        SecaoGravacaoHelper secaoHelper = new SecaoGravacaoHelper();
        //CompraGravacaoHelper compraHelper = new CompraGravacaoHelper();
        
        if (!gerenciadorDeArquivo.contem(ingresso.getEvento().getId(), 
                ingresso.getPosicao())) {
            
            eventoHelper.gravarObjeto(ingresso.getEvento());
            secaoHelper.gravarObjeto(ingresso.getSecao());
            //compraHelper.gravarObjeto(ingresso.getCompra());
        
            return gerenciadorDeArquivo.gravarLinha(toLine(ingresso));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Ingresso> ingressos) {
        for (Ingresso ingresso : ingressos) {
            this.gravarObjeto(ingresso);
        }
    }

    @Override
    public Ingresso getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Ingresso resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }
    
    public List<Ingresso> getIngressosPorEvento(long idEvento) {
        List<String> listaObjetos = gerenciadorDeArquivo
                .getObjetosPorAtributo(idEvento, ATRIBUTO_EVENTO);
        List<Ingresso> resultado = new ArrayList<Ingresso>();
        for (String entrada : listaObjetos) {
            Ingresso ingresso = getObject(entrada);
            resultado.add(ingresso);
        }
        return resultado;
    }
    
    public List<Ingresso> getIngressosPorSecao(long idSecao) {
        List<String> listaObjetos = gerenciadorDeArquivo
                .getObjetosPorAtributo(idSecao, ATRIBUTO_SECAO);
        List<Ingresso> resultado = new ArrayList<Ingresso>();
        for (String entrada : listaObjetos) {
            Ingresso ingresso = getObject(entrada);
            resultado.add(ingresso);
        }
        return resultado;
    }
    
    public List<Ingresso> getIngressosPorCompra(long idCompra) {
        List<String> listaObjetos = gerenciadorDeArquivo
                .getObjetosPorAtributo(idCompra, ATRIBUTO_COMPRA);
        List<Ingresso> resultado = new ArrayList<Ingresso>();
        for (String entrada : listaObjetos) {
            Ingresso ingresso = getObject(entrada);
            resultado.add(ingresso);
        }
        return resultado;
    }

    @Override
    public List<Ingresso> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Ingresso> resultado = new ArrayList<Ingresso>();
        for (String entrada : listaObjetos) {
            Ingresso ingresso = getObject(entrada);
            resultado.add(ingresso);
        }
        return resultado;
    }

    @Override
    public boolean remove(Ingresso ingresso) {
        return gerenciadorDeArquivo.removerLinha(ingresso.getId());
    }
    
    private String toLine(Ingresso ingresso) {
        StringBuilder sb = new StringBuilder();
        sb.append(ingresso.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(ingresso.getEvento().getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(ingresso.getPosicao());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(ingresso.getSecao().getId());
        sb.append(ServiceHelper.SEPARADOR);
        if(ingresso.getCompra() != null){
            sb.append(ingresso.getCompra().getId());
        } else{
            sb.append(0L);
        }

        return sb.toString();
    }

    private Ingresso getObject(String line) {
        EventoGravacaoHelper eventoHelper = new EventoGravacaoHelper();
        SecaoGravacaoHelper secaoHelper = new SecaoGravacaoHelper();
        CompraGravacaoHelper compraHelper = new CompraGravacaoHelper();
        
        String[] ingresso = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));
        
        long id = Long.parseLong(ingresso[0]);
        long idEvento = Long.parseLong(ingresso[ATRIBUTO_EVENTO]);
        int posicao = Integer.parseInt(ingresso[ATRIBUTO_POSICAO]);
        long idSecao = Long.parseLong(ingresso[ATRIBUTO_SECAO]);
        long idCompra = Long.parseLong(ingresso[ATRIBUTO_COMPRA]);

        Evento evento = eventoHelper.getObjetoPorId(idEvento);
        Secao secao = secaoHelper.getObjetoPorId(idSecao);
        Compra compra = compraHelper.getObjetoPorId(idCompra);

        Ingresso resultado = new Ingresso(posicao, evento, secao);
        resultado.setId(id);
        resultado.setCompra(compra);
        return resultado;
    }
}
