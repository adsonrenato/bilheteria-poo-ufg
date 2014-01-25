package br.ufg.inf.poo.bilheteria.model.entity;

import java.util.UUID;

abstract class Entidade {

    protected long id;

    public Entidade() {
        this.id = UUID.randomUUID().getLeastSignificantBits();;
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
