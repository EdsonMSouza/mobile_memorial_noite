package com.ems.aula_2804;

import java.io.Serializable;

public class Aluno implements Serializable {
    // criar os atributos da classe
    private int id;
    private String ra;
    private String nome;
    private String curso;

    // aqui vai o método construtor
    public Aluno() {
    }

    public Aluno(String ra, String nome, String curso) {
        this.ra = ra;
        this.nome = nome;
        this.curso = curso;
    }

    // gets e sets

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return nome;
    }

    /**
     * Método para retornar o conteúdo do objeto
     *
     * @return String "ra, nome, curso"
     */
    public String getDados() {
        return "RA: " + ra + "\nNOME: " + nome + "\nCURSO: " + curso;
    }
}
