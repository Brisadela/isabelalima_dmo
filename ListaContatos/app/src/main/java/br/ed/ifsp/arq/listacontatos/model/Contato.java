package br.ed.ifsp.arq.listacontatos.model;

public class Contato {

    private String nome;
    private String apelido;
    private String telefone;
    private String email;

    private boolean favorite;

    public Contato(String nome, String apelido, String telefone, String email){
        this.setNome(nome);
        this.setApelido(apelido);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.favorite = false;
    }

    public String toString() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setApelido(String apelido) {
        this.apelido = apelido.toUpperCase();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.toUpperCase();
    }
        public void setEmail (String email){
            this.email = email.toLowerCase();
        }

        public boolean isFavorite(){
            return favorite;
        }
        public void setFavorite(boolean favorite){
            this.favorite = favorite;
        }
    }


