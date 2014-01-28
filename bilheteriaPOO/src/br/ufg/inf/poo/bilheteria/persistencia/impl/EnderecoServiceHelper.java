/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.poo.bilheteria.persistencia.impl;

import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import java.util.ArrayList;
import java.util.List;

public class EnderecoServiceHelper implements ServiceHelper<Endereco> {

    private final String ARQUIVO = "enderecos.csv";
    private CSVToFile gerenciadorDeArquivo;

    public EnderecoServiceHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }

    @Override
    public boolean gravarObjeto(Endereco endereco) {
        if (!gerenciadorDeArquivo.contem(endereco.getId())) {
            return gerenciadorDeArquivo.gravarLinha(toLine(endereco));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Endereco> enderecos) {
        for(Endereco endereco : enderecos){
           this.gravarObjeto(endereco);
       }
    }

    @Override
    public Endereco getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Endereco resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }

    @Override
    public List<Endereco> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Endereco> resultado = new ArrayList<Endereco>();
        for (String entrada : listaObjetos) {
            Endereco endereco = getObject(entrada);
            resultado.add(endereco);
        }
        return resultado;
    }

    @Override
    public boolean remove(Endereco endereco) {
        return gerenciadorDeArquivo.removerLinha(endereco.getId());
    }
    
    private String toLine(Endereco endereco){
        StringBuilder sb = new StringBuilder();
        sb.append(endereco.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getCep());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getPais());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getUf());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getCidade());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getBairro());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(endereco.getLogradouro());
        
        return sb.toString();
    }
    
    private Endereco getObject(String line) {
        String[] endereco = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));
        
        long id = Long.parseLong(endereco[0]);
        String cep = endereco[1];
        String pais = endereco[2];
        String uf = endereco[3];
        String cidade = endereco[4];
        String bairro = endereco[5];
        String logradouro = endereco[6];

        Endereco resultado = new Endereco(cep, pais, uf, cidade, bairro, logradouro);
        resultado.setId(id);
        return resultado;
    }
}
