/*
 * Classe Bilheteria: Classe principal da aplicação.
 * 
 */
package br.ufg.inf.poo.bilheteria.view;

import br.ufg.inf.poo.bilheteria.model.entity.Cliente;
import br.ufg.inf.poo.bilheteria.model.entity.Compra;
import br.ufg.inf.poo.bilheteria.model.entity.Endereco;
import br.ufg.inf.poo.bilheteria.model.entity.Evento;
import br.ufg.inf.poo.bilheteria.model.entity.FormaPagamento;
import br.ufg.inf.poo.bilheteria.model.entity.Ingresso;
import br.ufg.inf.poo.bilheteria.model.entity.Secao;
import br.ufg.inf.poo.bilheteria.persistencia.impl.ClienteGravacaoHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.CompraGravacaoHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.EventoGravacaoHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.IngressoGravacaoHelper;
import br.ufg.inf.poo.bilheteria.persistencia.impl.SecaoGravacaoHelper;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Bilheteria {

    static int c1[][] = new int[26][21];
    static int conta_vendas;
    static double caixa;

    public static void main(String args[]) {
        /* Teste -------------------------------------------------------------*/
        testeCliente();
        testeEvento();
        testeSecao();
        testeIngresso();
        testeCompra();

        System.exit(0);
        /* Fim Teste ---------------------------------------------------------*/

        Scanner input = new Scanner(System.in);
        int sair = 0;
        int vendedor = 001;
        int vendedor1;
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------SEJA BEM VINDO POR FAVOR INFORME SEUS DADOS ----------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("INSIRA CÓDIGO DO VENDEDOR");
        vendedor1 = input.nextInt();
        if (vendedor == vendedor1) {
            while (sair != 3) {
                System.out.println("-------------------------------------------------");
                System.out.println("(1) - VENDAS");
                System.out.println("(2) - RELATORIO DE VENDAS");
                System.out.println("(3) - VERIFICA LUGARES DISPONIVEIS");
                System.out.println("(4) - EXIT");
                System.out.println("-------------------------------------------------");
                int menu = input.nextInt();
                switch (menu) {
                    case 1: {
                        iniciavenda();
                        break;
                    }
                    case 2: {
                        relatorio();
                        break;
                    }
                    case 3: {
                        consultar();
                        break;

                    }
                    case 4: {
                        System.exit(0);
                    }
                }
            }

        }
    }

    public static void iniciavenda() {
        Scanner input = new Scanner(System.in);
        int coluna;
        String linha;
        System.out.println("Digite a linha desejada");
        linha = input.next();
        System.out.println("Digite a coluna desejada");
        coluna = input.nextInt();
        vetores(linha.charAt(0), coluna);
    }

    public static void relatorio() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("----------------- RELATÓRIO ANALITICO DE VENDAS ------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Total de Ingressos Vendidos: " + conta_vendas);
        System.out.println("Total em Reais: R$ " + caixa);
        System.out.println("Relação de Poltronas Vendidas:");
        for (int linha = 0; linha < c1.length - 1; linha++) {
            for (int coluna = 0; coluna < 20; coluna++) {
                if (c1[linha][coluna] != 0) {
                    System.out.println("Cadeira " + (char) ('a' + linha) + " " + coluna + " vendida");
                }
            }
        }
        System.out.println("");
        System.out.println("******************************************************************");
    }

    public static void consultar() {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 20; j++) {
                if (c1[i][j] == 0) {
                    System.out.println((char) ('A' + i) + "" + j + " desocupado.");
                }
            }
        }
    }

    public static void vetores(char linha, int col) {


        int lin = (int) linha - 'a';

        switch (c1[lin][col]) {
            case 0:
                if (lin <= 5) {
                    System.out.println(lin);
                    double preco = 10.00;
                    System.out.println("Posicao livre");
                    c1[lin][col] = 1;
                    System.out.println("----------------------------------");
                    System.out.println("= VENDA EFETUADA COM SUCESSO =");
                    System.out.println("----------------------------------");
                    caixa = caixa + preco;
                    conta_vendas++;
                } else {
                    double preco2 = 25.00;
                    System.out.println("Posicao livre");
                    c1[lin][col] = 1;
                    System.out.println("----------------------------------");
                    System.out.println("-> VENDA EFETUADA COM SUCESSO <-");
                    System.out.println("----------------------------------");
                    caixa = caixa + preco2;
                    conta_vendas++;
                }
                break;
            case 1:
                System.out.println("----------------------------------");
                System.out.println("-----> ESPAÇO VENDIDO <------");
                System.out.println("----------------------------------");
                break;
            case 2:
                System.out.println("----------------------------------");
                System.out.println("-----> ESPAÇO RESERVADO <------");
                System.out.println("----------------------------------");
                break;
            default:
        }


    }

    private static void testeCliente() {
        Endereco e = new Endereco("76200-000", "Brasil", "GO", "Iporá", "Centro", "Rua tal");
        Cliente c = new Cliente("000.000.001-91", "Raul", e, "3200-0000");

        // Grava cliente
        ClienteGravacaoHelper clienteHelper = new ClienteGravacaoHelper();
        clienteHelper.gravarObjeto(c);

        // Recupera cliente
        Cliente cliente2 = clienteHelper.getTodosObjetos().get(0);
        System.out.println(cliente2);

        // Remove cliente
        clienteHelper.remove(cliente2);
        
        apagaArquivos();
    }

    private static void testeEvento() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 5);

        Evento evento = new Evento("Carmen", "Ópera em Goiânia!", date, "Teatro Rio Vermelho");
        EventoGravacaoHelper eventoHelper = new EventoGravacaoHelper();

        // Grava evento
        eventoHelper.gravarObjeto(evento);

        // Recupera evento
        Evento evento2 = eventoHelper.getTodosObjetos().get(0);
        System.out.println(evento2);

        // Remove evento
        eventoHelper.remove(evento2);
        
        apagaArquivos();
    }

    private static void testeSecao() {
        Secao secao = new Secao("Arquibancada", 50.0f);
        SecaoGravacaoHelper secaoHelper = new SecaoGravacaoHelper();

        // Grava secao
        secaoHelper.gravarObjeto(secao);

        // Recupera secao
        Secao secao2 = secaoHelper.getTodosObjetos().get(0);
        System.out.println(secao2);

        // Remove secao
        secaoHelper.remove(secao2);
        
        apagaArquivos();
    }

    private static void testeIngresso() {
        // Evento
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 5);
        Evento evento = new Evento("Carmen", "Ópera em Goiânia!", date, "Teatro Rio Vermelho");
        EventoGravacaoHelper eventoHelper = new EventoGravacaoHelper();
        eventoHelper.gravarObjeto(evento);

        // Seção
        Secao secao = new Secao("Arquibancada", 50.0f);
        SecaoGravacaoHelper secaoHelper = new SecaoGravacaoHelper();
        secaoHelper.gravarObjeto(secao);


        Ingresso ingresso = new Ingresso(1, evento, secao);
        IngressoGravacaoHelper ingressoHelper = new IngressoGravacaoHelper();

        // Grava ingresso
        ingressoHelper.gravarObjeto(ingresso);

        // Recupera ingresso
        Ingresso ingresso2 = ingressoHelper.getTodosObjetos().get(0);
        System.out.println(ingresso2);

        // Remove ingresso
        ingressoHelper.remove(ingresso2);
        
        apagaArquivos();
    }

    private static void testeCompra() {
        Endereco endereco = new Endereco("76200-000", "Brasil", "GO", "Iporá", "Centro", "Rua tal");
        Cliente cliente = new Cliente("000.000.001-91", "Raul", endereco, "3200-0000");

        // Cliente
        ClienteGravacaoHelper clienteHelper = new ClienteGravacaoHelper();
        clienteHelper.gravarObjeto(cliente);

        // Evento
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 5);
        Evento evento = new Evento("Carmen", "Ópera em Goiânia!", date, "Teatro Rio Vermelho");
        EventoGravacaoHelper eventoHelper = new EventoGravacaoHelper();
        eventoHelper.gravarObjeto(evento);

        // Seção Arquibancada
        Secao secao = new Secao("Arquibancada", 49.99f);
        SecaoGravacaoHelper secaoHelper = new SecaoGravacaoHelper();
        secaoHelper.gravarObjeto(secao);
        
        // Seção VIP
        Secao secaoVIP = new Secao("Arquibancada", 99.99f);
        secaoHelper.gravarObjeto(secaoVIP);

        // Ingresso
        Ingresso ingresso = new Ingresso(1, evento, secao);
        IngressoGravacaoHelper ingressoHelper = new IngressoGravacaoHelper();
        ingressoHelper.gravarObjeto(ingresso);
        
        // Ingresso na seção vip
        Ingresso ingressoVIP = new Ingresso(2, evento, secaoVIP);
        ingressoHelper.gravarObjeto(ingressoVIP);

        // Recupera ingresso sem compra
        List<Ingresso> ingressosComprados = ingressoHelper.getTodosObjetos();

        // Realiza compra
        Compra compra = new Compra(cliente, date, FormaPagamento.DINHEIRO);
        compra.setIngressos(ingressosComprados);
        compra.finaliza();

        // Grava compra
        CompraGravacaoHelper compraHelper = new CompraGravacaoHelper();
        compraHelper.gravarObjeto(compra);

        // Recupera compra
        Compra compraRecuperada = compraHelper.getTodosObjetos().get(0);
        System.out.println("Compra:");
        System.out.println(compraRecuperada);

        // Ingressos após compra
        System.out.println("\nApós compra:");
        System.out.println(ingressoHelper.getTodosObjetos());

        // Remove compra
        compraHelper.remove(compraRecuperada);

        // Ingressos após remover compra
        System.out.println("\nApós remover compra:");
        System.out.println(ingressoHelper.getTodosObjetos());
        
        apagaArquivos();
    }

    /**
     * Apaga arquivos após teste
     */
    private static void apagaArquivos() {
            File f = new File("compras.csv");
            f.delete();
            
            f = new File("eventos.csv");
            f.delete();
            
            f = new File("ingressos.csv");
            f.delete();
            
            f = new File("clientes.csv");
            f.delete();
            
            f = new File("secoes.csv");
            f.delete();
            
            f = new File("enderecos.csv");
            f.delete();
    }
}
