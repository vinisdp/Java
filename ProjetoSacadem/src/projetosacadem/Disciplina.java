/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosacadem;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Vini_
 */
public class Disciplina {

    private String codigo;
    private String nome;
    private int semestre;
    private int ano;
    private String codigoCurso;
    private ArrayList<Matricula> matriculas = new ArrayList<Matricula>();

    public Disciplina(String codigo, String nome, int semestre, int ano, String codigoCurso) {

        this.codigo = codigo;
        this.nome = nome;
        this.semestre = semestre;
        this.ano = ano;
        this.codigoCurso = codigoCurso;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    
    public boolean procuraMatriculaAluno(String cpfAluno, String codigoDisciplina) {
        boolean resposta = false;
        for (int i = 0; i < this.matriculas.size(); i++) {
            String cpfAuxAluno = this.matriculas.get(i).getCpfAluno();
            if (cpfAluno.equals(cpfAuxAluno)){
                resposta = true;
                break;
            }
        }
        if (resposta == false) {
            this.matriculas.add(new Matricula(cpfAluno, codigoDisciplina));
            JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso!", "SACADEM", 1);
        }
        return resposta;
    }

    public boolean matriculaAluno(String cpfAluno, String codigoDisciplina) {
        boolean resposta = procuraMatriculaAluno(cpfAluno, codigoDisciplina);
        return resposta;
    }
    public void removeMatricula(String cpfAluno){
        for(int i = 0; i < this.matriculas.size(); i++){
            String cpfAuxAluno = this.matriculas.get(i).getCpfAluno();
            if(cpfAuxAluno.equals(cpfAluno)){
                this.matriculas.remove(i);
                break;
            }
        }
        
    }
    public boolean cadastrarAvaliacao(String cpfAluno){
        boolean resposta = false;
        for(int i = 0; i < this.matriculas.size(); i++){
            String cpfAuxAluno = this.matriculas.get(i).getCpfAluno();
            if(cpfAuxAluno.equals(cpfAluno)){
                resposta = this.matriculas.get(i).cadastraAvaliação();
                break;
            }
        }
        return resposta;
    }
    public String historicoEscolar(String cpfAluno){
        String saida="";
        for(int i = 0; i < this.matriculas.size(); i++){
            String cpfAuxAluno = this.matriculas.get(i).getCpfAluno();
            if(cpfAuxAluno.equals(cpfAluno)){
                saida+="\nNome da disciplina:"+this.nome+"\nSemestre da disciplina:"+this.semestre+"\nAno:"+this.ano+this.matriculas.get(i).calculaMedia();
                break;
            }
        }
        return saida;
    }

    

}
