package br.edu.ifsp.arq.minhastarefas.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.minhastarefas.model.Tarefa;

public class TarefaDAO {

    private final String TAG = this.getClass().getSimpleName();
    private static TarefaDAO instance = null;
    private List<Tarefa> tarefaList;
    private Context context;

    public TarefaDAO(Context context) {
        this.context = context;
        tarefaList = new ArrayList<>();
        selectAll();
    }

    private void selectAll() {
        SharedPreferences sharedPreferences;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String jsonString;
        Tarefa tarefa;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString(Constantes.TABLE_NAME, "");

        if (!jsonString.isEmpty()) {
            try {
                jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    tarefa = new Tarefa(
                            jsonObject.getString(Constantes.ATTR_TITLE),
                            jsonObject.getString(Constantes.ATTR_DATA));
                    tarefa.setPrioridade(jsonObject.getInt(Constantes.ATTR_PRIORIDADE));
                    tarefa.setStatus(jsonObject.getBoolean(Constantes.ATTR_STATUS));
                    tarefaList.add(tarefa);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Erro ao recuperar dados do JSON");
            }
        } else {
            Log.i(TAG, "Sem dados armazenados");
        }
    }

    private void commitAll() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        JSONObject jsonObject;
        JSONArray jsonArray;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        jsonArray = new JSONArray();

        for (Tarefa tarefa : tarefaList) {
            jsonObject = new JSONObject();
            try {
                jsonObject.put(Constantes.ATTR_TITLE, tarefa.getTitulo());
                jsonObject.put(Constantes.ATTR_DATA, tarefa.getData());
                jsonObject.put(Constantes.ATTR_PRIORIDADE, tarefa.getPrioridade());
                jsonObject.put(Constantes.ATTR_STATUS, tarefa.isStatus());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        editor.putString(Constantes.TABLE_NAME, jsonArray.toString());
        editor.commit();
    }

    public static synchronized TarefaDAO getInstance(Context context) {
        if (instance == null) {
            instance = new TarefaDAO(context);
        }
        return instance;
    }

    public List<Tarefa> getTarefas() {
        return tarefaList;
    }

    public void addTarefa(Tarefa tarefa) {
        if (tarefa != null) {
            tarefaList.add(tarefa);
            commitAll();
        }
    }

    public void finalizaAPP() {
        commitAll();
    }

    public void updateTarefa(String oldTitle, String title, String data) {
        Tarefa alterar = find(oldTitle);
        if (alterar != null) {
            alterar.setTitulo(title);
            alterar.setData(data);
            commitAll();
        }
    }

    public Tarefa find(int i) {
        return tarefaList.get(i);
    }

    public Tarefa find(String title) {

        for (Tarefa t : tarefaList) {
            if (t.getTitulo().equals(title)) {
                return t;
            }
        }
        return null;
    }
}



