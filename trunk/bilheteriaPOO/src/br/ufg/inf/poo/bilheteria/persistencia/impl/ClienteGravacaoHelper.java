package br.ufg.inf.poo.bilheteria.persistencia.impl;

import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alunoinf
 */
public class ClienteGravacaoHelper implements ServiceHelper<Cliente> {

    private final String ARQUIVO = "clientes.csv";
    private CSVToFile gerenciadorDeArquivo;

    public ClienteGravacaoHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }

    @Override
    public boolean gravarObjeto(Cliente cliente) {
        EnderecoGravacaoHelper enderecoHelper = new EnderecoGravacaoHelper();

        if (!contemCpf(cliente.getCpf())) {
            enderecoHelper.gravarObjeto(cliente.getEndereco());

            return gerenciadorDeArquivo.gravarLinha(toLine(cliente));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            this.gravarObjeto(cliente);
        }
    }

    @Override
    public Cliente getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Cliente resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }

    @Override
    public List<Cliente> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Cliente> resultado = new ArrayList<Cliente>();
        for (String entrada : listaObjetos) {
            Cliente cliente = getObject(entrada);
            resultado.add(cliente);
        }
        return resultado;
    }

    @Override
    public boolean remove(Cliente cliente) {
        EnderecoGravacaoHelper enderecoHelper = new EnderecoGravacaoHelper();
        if (!contemCompra(cliente)) {
            enderecoHelper.remove(cliente.getEndereco());
            return gerenciadorDeArquivo.removerLinha(cliente.getId());
        }
        return false;
    }

    private String toLine(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append(cliente.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(cliente.getCpf());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(cliente.getNome());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(cliente.getEndereco().getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(cliente.getTelefone());

        return sb.toString();
    }

    private Cliente getObject(String line) {
        EnderecoGravacaoHelper enderecoHelper = new EnderecoGravacaoHelper();
        String[] pessoa = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));

        long id = Long.parseLong(pessoa[0]);
        String cpf = pessoa[1];
        String nome = pessoa[2];
        long idEndereco = Long.parseLong(pessoa[3]);
        String telefone = pessoa[4];

        Endereco endereco = enderecoHelper.getObjetoPorId(idEndereco);

        Cliente resultado = new Cliente(cpf, nome, endereco, telefone);
        resultado.setId(id);
        return resultado;
    }

    private boolean contemCpf(String cpf) {
        int size = gerenciadorDeArquivo.getObjetosPorAtributo(cpf, 1).size();
        return size > 0;
    }

    private boolean contemCompra(Cliente cliente) {
        CompraGravacaoHelper compraHelper = new CompraGravacaoHelper();

        int size = compraHelper.getComprasPorCliente(cliente.getId()).size();
        return size > 0;
    }
}
