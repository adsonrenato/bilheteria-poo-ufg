/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.poo.bilheteria.controller.importador;

import br.ufg.inf.poo.bilheteria.model.entity.Cliente;
import br.ufg.inf.poo.bilheteria.model.entity.Compra;
import br.ufg.inf.poo.bilheteria.model.entity.Endereco;
import br.ufg.inf.poo.bilheteria.model.entity.Evento;
import br.ufg.inf.poo.bilheteria.model.entity.Ingresso;
import br.ufg.inf.poo.bilheteria.model.entity.Secao;
import br.ufg.inf.poo.bilheteria.persistencia.impl.ClienteServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.CompraServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.EnderecoServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.EventoServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.IngressoServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.SecaoServiceHelper;
import java.util.List;

/**
 * Os arquivos a importar devem possuir mesma estrutura da base de dados em CSV
 * @author Raul
 */
public class Importador {
    
    public void importarClientes(String filename){
        ClienteServiceHelper clienteHelperToImport = new ClienteServiceHelper(filename);
        List<Cliente> clientes = clienteHelperToImport.getTodosObjetos();
        
        ClienteServiceHelper clienteHelper = new ClienteServiceHelper();
        clienteHelper.gravarObjetos(clientes);
    }
    
    public void importarEnderecos(String filename){
        EnderecoServiceHelper enderecoHelperToImport = new EnderecoServiceHelper(filename);
        List<Endereco> enderecos = enderecoHelperToImport.getTodosObjetos();
        
        EnderecoServiceHelper enderecoHelper = new EnderecoServiceHelper();
        enderecoHelper.gravarObjetos(enderecos);
    }
    
    public void importarEventos(String filename){
        EventoServiceHelper eventoHelperToImport = new EventoServiceHelper(filename);
        List<Evento> eventos = eventoHelperToImport.getTodosObjetos();
        
        EventoServiceHelper eventoHelper = new EventoServiceHelper();
        eventoHelper.gravarObjetos(eventos);
    }
    
    public void importarSecoes(String filename){
        SecaoServiceHelper secaoHelperToImport = new SecaoServiceHelper(filename);
        List<Secao> secoes = secaoHelperToImport.getTodosObjetos();
        
        SecaoServiceHelper secaoHelper = new SecaoServiceHelper();
        secaoHelper.gravarObjetos(secoes);
    }
    
    public void importarIngressos(String filename){
        IngressoServiceHelper ingressoHelperToImport = new IngressoServiceHelper(filename);
        List<Ingresso> ingressos = ingressoHelperToImport.getTodosObjetos();
        
        IngressoServiceHelper ingressoHelper = new IngressoServiceHelper();
        ingressoHelper.gravarObjetos(ingressos);
    }
    
    public void importarCompras(String filename){
        CompraServiceHelper compraHelperToImport = new CompraServiceHelper(filename);
        List<Compra> compras = compraHelperToImport.getTodosObjetos();
        
        CompraServiceHelper compraHelper = new CompraServiceHelper();
        compraHelper.gravarObjetos(compras);
    }
}
