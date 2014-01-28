package br.ufg.inf.poo.bilheteria.persistencia.impl;

import br.ufg.inf.poo.bilheteria.model.entity.*;
import br.ufg.inf.poo.bilheteria.persistencia.base.CSVToFile;
import br.ufg.inf.poo.bilheteria.utils.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author alunoinf
 */
public class EventoServiceHelper implements ServiceHelper<Evento> {
    private final int ATRIBUTO_NOME = 1;
    private final int ATRIBUTO_DESCRICAO = 2;
    private final int ATRIBUTO_DATA = 3;
    private final int ATRIBUTO_LOCAL = 4;

    private final String ARQUIVO = "eventos.csv";
    private CSVToFile gerenciadorDeArquivo;

    public EventoServiceHelper() {
        gerenciadorDeArquivo = new CSVToFile(ARQUIVO);
    }
    
    public EventoServiceHelper(String filename) {
        gerenciadorDeArquivo = new CSVToFile(filename);
    }

    @Override
    public boolean gravarObjeto(Evento evento) {
        if (!gerenciadorDeArquivo.contem(evento.getId())) {
            return gerenciadorDeArquivo.gravarLinha(toLine(evento));
        } else {
            return false;
        }
    }

    @Override
    public void gravarObjetos(List<Evento> eventos) {
        for (Evento evento : eventos) {
            this.gravarObjeto(evento);
        }
    }

    @Override
    public Evento getObjetoPorId(long id) {
        String linha = gerenciadorDeArquivo.getLinhaPorId(id);
        if (linha != null) {
            Evento resultado = getObject(linha);
            return resultado;
        } else {
            return null;
        }
    }

    @Override
    public List<Evento> getTodosObjetos() {
        List<String> listaObjetos = gerenciadorDeArquivo.getLinhas();
        List<Evento> resultado = new ArrayList<Evento>();
        for (String entrada : listaObjetos) {
            Evento evento = getObject(entrada);
            resultado.add(evento);
        }
        return resultado;
    }

    @Override
    public boolean remove(Evento evento) {
         return gerenciadorDeArquivo.removerLinha(evento.getId());
    }

    private String toLine(Evento e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getId());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(e.getNome());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(e.getDescricao());
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(DateUtil.getStringFromCalendar(e.getData()));
        sb.append(ServiceHelper.SEPARADOR);
        sb.append(e.getLocal());
        return sb.toString();
    }

    private Evento getObject(String line) {
        IngressoServiceHelper ingressoHelper = new IngressoServiceHelper();
        
        String[] evento = line.split(
                String.valueOf(ServiceHelper.SEPARADOR));
        long id = Long.parseLong(evento[0]);
        String nome = evento[ATRIBUTO_NOME];
        String descricao = evento[ATRIBUTO_DESCRICAO];
        Calendar data = DateUtil.getCalendarFromString(evento[ATRIBUTO_DATA]);
        String local = evento[ATRIBUTO_LOCAL];
        
        Evento resultado = new Evento(nome, descricao, data, local);
        resultado.setId(id);
        
        return resultado;
    }


}
 
