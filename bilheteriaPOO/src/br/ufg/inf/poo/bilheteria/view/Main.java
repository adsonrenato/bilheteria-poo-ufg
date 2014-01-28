/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.poo.bilheteria.view;

import br.ufg.inf.poo.bilheteria.model.entity.Cliente;
import br.ufg.inf.poo.bilheteria.model.entity.Evento;
import br.ufg.inf.poo.bilheteria.model.entity.Secao;
import br.ufg.inf.poo.bilheteria.persistencia.impl.EventoServiceHelper;
import java.util.Scanner;
import br.ufg.inf.poo.bilheteria.persistencia.impl.IngressoServiceHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.SecaoServiceHelper;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author LucasCarvalho
 */
public class Main {

    private static Scanner scan;
    private static String caminho_arquivo_eventos;
    private static String caminho_arquivo_compras;
    private static String caminho_arquivo_clientes;
    private static String caminho_arquivo_enderecos;
    private static String caminho_arquivo_ingressos;
    private static String caminho_arquivo_secoes;

    private static void importarArquivos() {
        System.out.println("Caminho para o arquivo de eventos:");
        caminho_arquivo_eventos = scan.nextLine();

        System.out.println("Caminho para o arquivo de secoes:");
        caminho_arquivo_secoes = scan.nextLine();

        System.out.println("Caminho para o arquivo de ingressos:");
        caminho_arquivo_ingressos = scan.nextLine();

        System.out.println("Caminho para o arquivo de enderecos:");
        caminho_arquivo_enderecos = scan.nextLine();

        System.out.println("Caminho para o arquivo de clientes:");
        caminho_arquivo_clientes = scan.nextLine();

        System.out.println("Caminho para o arquivo de compras:");
        caminho_arquivo_compras = scan.nextLine();
    }

    public static void main(String[] args) {

        scan = new Scanner(System.in);

        //importarArquivos();
        exibeMenuPrincipal();

    }

    private static void exibeMenuPrincipal() {

        int opcaoEscolhida;

        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("0: sair do sistema");
            System.out.println("1: relatório de vendas por seção");
            System.out.println("2: relatório de vendas total");
            System.out.println("3: relatório de clientes por evento");
            System.out.println("4: relatório de receita bruta");
            System.out.println("------------------------------------------------");
            opcaoEscolhida = scan.nextInt();

            switch (opcaoEscolhida) {
                case 0: {
                    //System.out.println("Deseja realmente sair do sistema?");
                    System.exit(0);
                    break;
                }
                case 1: {
                    System.out.println("Ainda não implementado.");
                    exibeRelatorioDeVendasPorSecao();
                    break;
                }

                case 2: {
                    exibeRelatorioDeVendasTotal();
                    break;
                }

                case 3: {
                    exibeRelatorioDeClientesPorEvento();
                    break;
                }

                case 4: {
                    exibeRelatorioDeReceitaBruta();
                    break;
                }

                default: {
                    System.out.println("Opcao Inválida.");
                    break;
                }
            }
        }
    }

    private static void exibeRelatorioDeVendasTotal() {
        IngressoServiceHelper igh = new IngressoServiceHelper();
        EventoServiceHelper egh = new EventoServiceHelper();
        System.out.println("------------RELATORIO DE VENDAS TOTAL---------------");
        for (int i = 0; i < egh.getTodosObjetos().size(); i++) {
            System.out.println("EVENTO: " + egh.getTodosObjetos().get(i).getNome());
            System.out.println("Total de Ingressos: " + igh.getTotalDeIngressos(egh.getTodosObjetos().get(i).getId()));
            System.out.println("Total de Ingressos vendidos: " + igh.getTotalDeIngressosVendidos(egh.getTodosObjetos().get(i).getId()));
            //System.out.println("Total em Reais: R$: ");
            System.out.println("---");
        }
    }

    private static void exibeRelatorioDeVendasPorSecao() {
        IngressoServiceHelper igh = new IngressoServiceHelper();
        SecaoServiceHelper sgh = new SecaoServiceHelper();
        List<Secao> secoes = sgh.getTodosObjetos();

        System.out.println("------------RELATORIO DE VENDAS POR SEÇÃO---------------");
        for (Secao secao : secoes) {
            System.out.println("Evento: "
                    + igh.getIngressosPorSecao(secao.getId()).get(0).getEvento().getNome());
            System.out.println("Seção: " + secao.getDescricao());
            System.out.println("Total de Ingressos: "
                    + igh.getIngressosPorSecao(secao.getId()).size());
            System.out.println("Total de Ingressos vendidos na seção: "
                    + igh.getTotalDeIngressosVendidosPorSecao(secao.getId()));
            //System.out.println("Total em Reais: R$: ");
            System.out.println("---");
        }
    }

    private static void exibeRelatorioDeClientesPorEvento() {
        IngressoServiceHelper igh = new IngressoServiceHelper();

        EventoServiceHelper esh = new EventoServiceHelper();
        List<Evento> eventos = esh.getTodosObjetos();

        for (Evento evento : eventos) {
            System.out.println("------------RELATORIO DE CLIENTES POR EVENTO---------------");
            System.out.println("Evento: " + evento.getNome());
            HashMap<String, Cliente> clientes = igh.getClientesEvento(evento.getId());
            List<Cliente> clientesList = (List) clientes.values();
            for(int i=0; i<clientesList.size(); i++){
                Cliente cliente = clientesList.get(i);
                System.out.println("Cliente: " + cliente.getNome());
            }
            System.out.println("---");
        }
    }

    private static void exibeRelatorioDeReceitaBruta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
