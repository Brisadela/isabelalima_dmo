package br.edu.ifsp.arq.minhastarefas.model;



public class Tarefa {

    private String titulo;
    private String data;
    private int prioridade;
    private boolean status;
    private boolean selected;

    public Tarefa(String titulo, String data){
        this.setTitulo(titulo);
        this.setData(data);
        this.prioridade = 0;
        this.status = false;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.toUpperCase();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selecao) {
        this.selected = selecao;
    }

}
