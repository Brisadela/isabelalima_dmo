package br.edu.ifsp.arq.minhastarefas.controller;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.arq.minhastarefas.dao.TarefaDAO;
import br.edu.ifsp.arq.minhastarefas.model.Tarefa;

public class TarefaController {

    public static List<Tarefa> allTarefas(Context context){
        return TarefaDAO.getInstance(context).getTarefas();
    }
    public static void addTarefa(Context context, String title, String data) {
        Tarefa novo = new Tarefa(title, data);
        TarefaDAO.getInstance(context).addTarefa(novo);
    }
    public static void updateTarefa(Context context, String oldTitle, String title, String data) {
//       Tarefa alterar = TarefaDAO.getInstance(context).find(oldTitle);
//
//       if(alterar !=null){
//           alterar.setTitulo(title);
//           alterar.getData();
//       }
        TarefaDAO.getInstance(context).updateTarefa(oldTitle, title, data);
    }
    public static void finalizaAPP(Context context) {
        TarefaDAO.getInstance(context).finalizaAPP();
    }
}
