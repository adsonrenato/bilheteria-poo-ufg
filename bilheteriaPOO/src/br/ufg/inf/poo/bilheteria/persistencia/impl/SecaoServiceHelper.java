package br.ufg.inf.poo.bilheteria.persistencia.impl;

import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alunoinf
 */
public class SecaoServiceHelper implements ServiceHelper<Secao> {

    private final String ARQUIVO = "secoes.csv";
    private CSVToFile gerenciadorDeArquivo;

    public SecaoServiceHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }
    
    public SecaoServiceHelper(String filename) {
        gerenciadorDeArquivo = new CSVToFile(filename);
    }
    
    @Override
    public boolean gravarObjeto(Secao secao) {
        if (!gerenciadorDeArquivo.contem(secao.getId())) {
            return gerenciadorDeArquivo.gravarLinha(toLine(secao));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Secao> secoes) {
        for (Secao secao : secoes) {
            this.gravarObjeto(secao);
        }
    }

    @Override
    public Secao getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Secao resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }

    @Override
    public List<Secao> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Secao> resultado = new ArrayList<Secao>();
        for (String entrada : listaObjetos) {
            Secao secao = getObject(entrada);
            resultado.add(secao);
        }
        return resultado;
    }

    @Override
    public boolean remove(Secao secao) {
        return gerenciadorDeArquivo.removerLinha(secao.getId());
    }
    
    private String toLine(Secao e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(e.getDescricao());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(e.getValor());
        return sb.toString();
    }

    private Secao getObject(String line) {
        String[] secao = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));
        
        long id = Long.parseLong(secao[0]);
        String descricao = secao[1];
        float valor = Float.parseFloat(secao[2]);
        
        Secao resultado = new Secao(descricao, valor);
        resultado.setId(id);
        return resultado;
    }
}
