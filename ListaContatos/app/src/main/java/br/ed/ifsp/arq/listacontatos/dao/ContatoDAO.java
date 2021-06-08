package br.ed.ifsp.arq.listacontatos.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.ed.ifsp.arq.listacontatos.model.Contato;

public class ContatoDAO {

    private static ContatoDAO instance = null;
    private List<Contato> contatoList;

    private ContatoDAO(){
        contatoList = new ArrayList<>(9);
        contatoList.add( new Contato("Andre","de","016 995687142","andre@gmail.com"));
        contatoList.add( new Contato("Aline","line","016 99888569","aline@gmail.com"));
        contatoList.add( new Contato("isabela","isa","016 992625896","isabela@gmail.com"));
        contatoList.add( new Contato("Alan","all","016 995635978","alen@gmail.com"));
        contatoList.add( new Contato("Maria","ria","016 995687142","maria@gmail.com"));
       // Collections.sort();
    }
    public static synchronized ContatoDAO getInstance(){
        if(instance == null){
            instance = new ContatoDAO();
        }
        return instance;
    }
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void addContato(Contato contato){
        if (contato != null){
            //Collections.sort();
            contatoList.add(contato);

        }
    }


    public Contato find(int i){
        //Collections.sort(contatoList);
        return contatoList.get(i);
    }

    public Contato find(String nome) {
        for (Contato c : contatoList) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        return  null;
    }
}

