package Exemplo;


public class Pessoa implements Comparable<Pessoa> {

    private int id;
    private String nome;
    private String sexo;

    public Pessoa(int id, String nome, String sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        if(nome == null || "".equals(nome)){
            throw new EntradaErradaException("O nome não pode "
                    + "ser vazio ou nulo");
        }else{
            this.nome = nome;
        }
        
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int compareTo(Pessoa comparada) {
        return this.getId() - comparada.getId();
    }

    @Override
    public boolean equals(Object objeto) {
        if(objeto == null || objeto.getClass() != this.getClass()){
            return false;
        }else{
            Pessoa comparada = (Pessoa) objeto;
            return (comparada.getId() == this.getId() &&
                    comparada.getNome().equals(this.getNome())&&
                    comparada.getSexo().equals(this.getSexo()
                    ));
        }
    }
    
    
}
