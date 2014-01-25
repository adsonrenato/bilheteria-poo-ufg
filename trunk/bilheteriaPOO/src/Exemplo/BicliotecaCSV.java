package Exemplo;

import br.ufg.inf.poo.bilheteria.model.entity.Cliente;
import br.ufg.inf.poo.bilheteria.persistencia.impl.EnderecoGravacaoHelper;
import java.util.List;

public class BicliotecaCSV {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa(1,
                "Edmundo", "masculino");
        Pessoa segundaPessoa = new Pessoa(2,
                "Berê", "feminino");
//        Pessoa terceiraPessoa = new Pessoa(3, 
//                "Leandro", "Masculino");
        Pessoa joselito = new Pessoa(2,
                "Zé Ruela", "Masculino");
//        
        PessoaGravacaoHelper gerenciaPessoas =
                new PessoaGravacaoHelper();
//
        gerenciaPessoas.gravarObjeto(segundaPessoa);

        imprimeTudo();
        gerenciaPessoas.remove(joselito);
        System.out.println("---");
        imprimeTudo();

    }

    public static void imprimeTudo() {
        PessoaGravacaoHelper gerenciaPessoas =
                new PessoaGravacaoHelper();
        List<Pessoa> pessoasGuardadas =
                gerenciaPessoas.getTodosObjetos();
        for (Pessoa individuo : pessoasGuardadas) {
            System.out.println(individuo.getNome());
        }
    }
}