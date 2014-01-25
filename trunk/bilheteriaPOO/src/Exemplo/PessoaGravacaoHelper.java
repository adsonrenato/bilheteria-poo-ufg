/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemplo;

import Exemplo.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 * Permitir que gravemos pessoas em algum lugar.
 * @author marceloquinta
 */
public class PessoaGravacaoHelper implements ServiceHelper<Pessoa>{

    private final String ARQUIVO = "pessoas.csv";
    private CSVToFile gerenciadorDeArquivo;
    
    public PessoaGravacaoHelper(){
       gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }
    
    @Override
    public boolean gravarObjeto(Pessoa pessoa) {
        if(!gerenciadorDeArquivo.contem(pessoa.getId())){
            return gerenciadorDeArquivo.gravarLinha(toLine(pessoa));
        }else{
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Pessoa> pessoas) {
       for(Pessoa individuo : pessoas){
           this.gravarObjeto(individuo);
       }
    }
    
    @Override
    public Pessoa getObjetoPorId(int id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if(linha != null){
            Pessoa resultado = getObject(linha);
            return resultado;
        }else{
            return null;
        }
        
    }

    @Override
    public List<Pessoa> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Pessoa> resultado = new ArrayList<Pessoa>();
        for(String entrada : listaObjetos){
            Pessoa pessoa = getObject(entrada);
            resultado.add(pessoa);
        }
        return resultado;
    }
    
    
    
    private String toLine(Pessoa p){
        StringBuilder sb = new StringBuilder();
        sb.append(p.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(p.getNome());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(p.getSexo());
        return sb.toString();
    }
    
    private Pessoa getObject(String line){
        String[] pessoa = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));
        int id = Integer.parseInt(pessoa[0]);
        String nome = pessoa[1];
        String sexo = pessoa[2];
        
        Pessoa resultado = new Pessoa(id,nome, sexo);
        return resultado;
    }

    @Override
    public boolean remove(Pessoa pessoa) {
        return gerenciadorDeArquivo.removerLinha(pessoa.getId());
    }
}
