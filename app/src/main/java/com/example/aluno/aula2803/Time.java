package com.example.aluno.aula2803;

/**
 * Created by aluno on 28/03/18.
 */

public class Time {
    private long id;
    private String nome;
    private String cidade;
    private int imagem;

    public Time(){

    }

    public Time(long id, String nome, String cidade,
                int imagem) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
