package br.ed.ifsp.arq.listacontatos.controller;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ed.ifsp.arq.listacontatos.adapter.ItemContatoAdapter;
import br.ed.ifsp.arq.listacontatos.dao.ContatoDAO;
import br.ed.ifsp.arq.listacontatos.model.Contato;

public class ContatoController{

    public static List<Contato> allContatos(){
        return ContatoDAO.getInstance().getContatoList();
    }

    public  static void addContato(String nome, String apelido, String telefone, String email){
        Contato novo = new Contato(nome, apelido,telefone,email);
        ContatoDAO.getInstance().addContato(novo);
    }

    public  static  void updateContato(String oldnome, String nome, String apelido,String telefone, String email){
        Contato alterar = ContatoDAO.getInstance().find(oldnome);
        if (alterar != null){
            alterar.setNome(nome);
            alterar.setApelido(apelido);
            alterar.setTelefone(telefone);
            alterar.setEmail(email);
        }
    }
}
