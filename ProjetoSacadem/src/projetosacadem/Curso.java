/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosacadem;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vini_
 */
public class Curso {

    private String codigo;
    private String nome;
    private int quantidadeAlunos;
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    public Curso(String codigo, String nome, int quantidadeAlunos) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
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

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public String listarCursos() {
        String menu = " ";
        menu += this.codigo + "-" + this.nome + "\nNumero de vagas:" + this.quantidadeAlunos + "\n";
        return menu;
    }

    public String listarDisciplina() {
        String saida = " ";
        for (int i = 0; i < disciplinas.size(); i++) {
            saida += this.disciplinas.get(i).getCodigo() + " - " + this.disciplinas.get(i).getNome() + "\nSemestre:" + this.disciplinas.get(i).getSemestre() + "\nAno:" + this.disciplinas.get(i).getAno() + "\nCodigo do curso:" + this.codigo;
        }
        return saida;
    }

    public String mostraAluno() {
        String saida = " ";
        for (int i = 0; i < alunos.size(); i++) {
            saida += "\nCPF:" + this.alunos.get(i).getCpf() + "\nNome: " + this.alunos.get(i).getNome() + "\nEmail: " + this.alunos.get(i).getEmail() + "\nTelefone: " + this.alunos.get(i).getTelefone() + "\nData de nascimento: " + this.alunos.get(i).getDataDeNascimento() + "\nCodigo do curso: " + this.codigo;
        }
        return saida;
    }

    public void cadastraDisciplina(String codigoCurso) {

        String codigoDisciplina = JOptionPane.showInputDialog(null, listarDisciplina() + "Digite o codigo da disciplina:", "SACADEM", 1);
        Disciplina procura;
        if (this.disciplinas.isEmpty()) {
            procura = null;
        } else {
            procura = procuraCadastrarDisciplina(codigoDisciplina);
        }
        if (procura == null) {
            String nomeDisciplina = JOptionPane.showInputDialog(null, "Digite o nome da disciplina:", "SACADEM", 1);
            String strSemestre = JOptionPane.showInputDialog(null, "Digite o semestre que a disciplina ocorrerá:", "SACADEM", 1);
            int semestre = Integer.parseInt(strSemestre);
            String strAno = JOptionPane.showInputDialog(null, "Digite o ano que a disciplina ocorrerá:", "SACADEM", 1);
            int ano = Integer.parseInt(strAno);
            procura = new Disciplina(codigoDisciplina, nomeDisciplina, semestre, ano, codigoCurso);
            this.disciplinas.add(procura);
            JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!", "SACADEM", 1);

        } else {
            JOptionPane.showMessageDialog(null, "Disciplina já cadastrada!", "SACADEM", 2);
        }
    }

    public Disciplina procuraCadastrarDisciplina(String codigoDisciplina) {
        Disciplina disciplina = null;

        for (int i = 0; i < this.disciplinas.size(); i++) {
            String codigoAuxDisciplina = this.disciplinas.get(i).getCodigo();

            if (codigoAuxDisciplina.equals(codigoDisciplina)) {
                disciplina = this.disciplinas.get(i);
                break;

            }
        }
        return disciplina;
    }

    public void procuraAlteraDisciplina(String codigoDisciplina) {
        boolean resposta = false;
        Disciplina disciplina = null;
        for (int i = 0; i < this.disciplinas.size(); i++) {
            String codigoAuxDisciplina = this.disciplinas.get(i).getCodigo();
            if (codigoAuxDisciplina.equals(codigoDisciplina)) {
                String codigoAlteraDisciplina = JOptionPane.showInputDialog(null, "\nDigite o novo codigo da disciplina:", "SACADEM", 1);
                String nomeDisciplina = JOptionPane.showInputDialog(null, "Digite o novo nome da disciplina:", "SACADEM", 1);
                String strSemestre = JOptionPane.showInputDialog(null, "Digite o novo semestre que a disciplina ocorrerá:", "SACADEM", 1);
                int semestre = Integer.parseInt(strSemestre);
                String strAno = JOptionPane.showInputDialog(null, "Digite o novo ano que a disciplina ocorrerá:", "SACADEM", 1);
                int ano = Integer.parseInt(strAno);
                
                this.disciplinas.get(i).setCodigo(codigoAlteraDisciplina);
                this.disciplinas.get(i).setNome(nomeDisciplina);
                this.disciplinas.get(i).setSemestre(semestre);
                this.disciplinas.get(i).setAno(ano);
                JOptionPane.showMessageDialog(null, "Disciplina alterada com sucesso!", "SACADEM", 1);
                resposta = true;
                break;
            }
        }
        if (resposta == false) {
            JOptionPane.showMessageDialog(null, "Disciplina não foi alterada!", "SACADEM", 2);
        }
        

    }

    public void alteraDisciplina() {
        Disciplina disciplina = null;
        if (this.disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada!", "SACADEM", 2);
        } else {
            String codigoDisciplina = JOptionPane.showInputDialog(null, listarDisciplina() + "\nDigite o Codigo da disciplina que deseja alterar:", "SACADEM", 1);
            procuraAlteraDisciplina(codigoDisciplina);

        }
    }

    public boolean procuraRemoverDisciplinas(String codigoDisciplina) {
        boolean resposta = false;
        for (int i = 0; i < this.disciplinas.size(); i++) {
            String codigoAuxDisciplina = this.disciplinas.get(i).getCodigo();
            if (codigoAuxDisciplina.equals(codigoDisciplina)) {
                this.disciplinas.remove(i);
                resposta = true;
                break;
            }
        }
        return resposta;
    }

    public void removerDisciplina() {
        if (this.disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrado!", "SACADEM", 2);
        } else {
            String codigoDisciplina = JOptionPane.showInputDialog(null, listarDisciplina() + "\nDigite o Codigo da disciplina que deseja remover:", "SACADEM", 1);
            boolean procura = procuraRemoverDisciplinas(codigoDisciplina);
            if (procura != false) {
                JOptionPane.showMessageDialog(null, "Disciplina removida com sucesso!", "SACADEM", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada!", "SACADEM", 2);
            }
        }

    }

    public boolean procuraAlteraAluno(String cpfAluno) {
        boolean resposta = false;
        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();
            if (cpfAuxAluno.equals(cpfAluno)) {
                String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno:", "SACADEM", 1);
                String emailAluno = JOptionPane.showInputDialog(null, "Digite o email do aluno:", "SACADEM", 1);
                String telefoneAluno = JOptionPane.showInputDialog(null, "Digite o telefone do aluno:", "SACADEM", 1);
                String dataDeNascimentoAluno = JOptionPane.showInputDialog(null, "Digite a data de nascimento do Aluno:", "SACADEM", 1);
                this.alunos.get(i).setNome(nomeAluno);
                this.alunos.get(i).setEmail(emailAluno);
                this.alunos.get(i).setTelefone(telefoneAluno);
                this.alunos.get(i).setDataDeNascimento(dataDeNascimentoAluno);
                JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!", "SACADEM", 1);
                resposta = true;
                break;
            }
        }
        return resposta;

    }

    public boolean alteraAluno(String cpfAluno) {
        boolean resposta;
        resposta = procuraAlteraAluno(cpfAluno);
        return resposta;
    }

    public Aluno procuraCadastrarAluno(String cpfAluno) {
        Aluno aluno = null;

        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();

            if (cpfAuxAluno.equals(cpfAluno)) {
                aluno = this.alunos.get(i);
                break;

            }
        }
        return aluno;
    }

    public void cadastraAluno() {
        if (this.quantidadeAlunos > alunos.size()) {
            String cpfAluno = JOptionPane.showInputDialog(null, mostraAluno() + "\nDigite o CPF do aluno:", "SACADEM", 1);
            Aluno procura;
            if (this.alunos.isEmpty()) {
                procura = null;
            } else {
                procura = procuraCadastrarAluno(cpfAluno);
            }
            if (procura == null) {
                String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno:", "SACADEM", 1);
                String emailAluno = JOptionPane.showInputDialog(null, "Digite o email do aluno:", "SACADEM", 1);
                String telefoneAluno = JOptionPane.showInputDialog(null, "Digite o telefone do aluno:", "SACADEM", 1);
                String dataDeNascimentoAluno = JOptionPane.showInputDialog(null, "Digite a data de nascimento do Aluno:", "SACADEM", 1);
                procura = new Aluno(cpfAluno, nomeAluno, emailAluno, telefoneAluno, dataDeNascimentoAluno);
                this.alunos.add(procura);
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!", "SACADEM", 1);

            } else {
                JOptionPane.showMessageDialog(null, "Aluno já cadastrado!", "SACADEM", 2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todas as vagas já foram preenchidas!", "SACADEM", 2);
        }
    }

    public boolean procuraRemoverAluno(String cpfAluno) {
        boolean resposta = false;
        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();
            if (cpfAuxAluno.equals(cpfAluno)) {
                this.alunos.remove(i);
                removeMatricula(cpfAluno);
                resposta = true;
                break;
            }
        }
        return resposta;
    }

    public void removeMatricula(String cpfAluno) {
        for (int i = 0; i < this.disciplinas.size(); i++) {
            this.disciplinas.get(i).removeMatricula(cpfAluno);
        }
    }

    public void removerAluno() {
        if (this.alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado!", "SACADEM", 2);
        } else {
            String cpfAuxAluno = JOptionPane.showInputDialog(null, mostraAluno() + "\nDigite o CPF do aluno que deseja remover:", "SACADEM", 1);
            boolean procura = procuraRemoverAluno(cpfAuxAluno);
            if (procura != false) {
                JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!", "SACADEM", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!", "SACADEM", 2);
            }
        }

    }

    public boolean procuraMatriculaAluno(String cpfAluno, String codigoDisciplina) {
        boolean respostaAluno = false;
        boolean respostaDisciplina = false;
        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();

            if (cpfAuxAluno.equals(cpfAluno)) {
                respostaAluno = true;
                break;
            }
        }
        if (respostaAluno) {
            for (int i = 0; i < this.disciplinas.size(); i++) {
                String codigoAuxDisciplina = this.disciplinas.get(i).getCodigo();

                if (codigoAuxDisciplina.equals(codigoDisciplina)) {
                    respostaDisciplina = true;
                    this.disciplinas.get(i).matriculaAluno(cpfAluno, codigoDisciplina);

                    break;
                }
            }
        }
        if ((respostaDisciplina && respostaAluno) == false) {
            JOptionPane.showMessageDialog(null, "Aluno já está matriculado ou não foi encontrado no mesmo curso da disciplina indicada!", "SACADEM", 2);
        }
        return respostaDisciplina;
    }

    public boolean matriculaAluno(String cpfAluno, String codigoDisciplina) {
        boolean resposta = procuraMatriculaAluno(cpfAluno, codigoDisciplina);
        return resposta;
    }

    public boolean cadastraAvaliacao(String cpfAluno, String codigoDisciplina) {
        boolean resposta = false;
        for (int i = 0; i < this.disciplinas.size(); i++) {
            String codigoAuxDisciplina = this.disciplinas.get(i).getCodigo();
            if (codigoAuxDisciplina.equals(codigoDisciplina)) {
                resposta = this.disciplinas.get(i).cadastrarAvaliacao(cpfAluno);
                break;
            }
        }
        return resposta;
    }

    public boolean buscaAluno(String cpfAluno) {
        boolean resposta = false;
        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();
            if (cpfAuxAluno.equals(cpfAluno)) {
                resposta = true;
                historicoEscolar(cpfAluno);
                break;
            }
        }
        return resposta;
    }

    public void historicoEscolar(String cpfAluno) {
        String saida = "";
        for (int i = 0; i < this.alunos.size(); i++) {
            String cpfAuxAluno = this.alunos.get(i).getCpf();
            if (cpfAuxAluno.equals(cpfAuxAluno)) {
                saida += "======================================\nAluno:" + this.alunos.get(i).getNome() + "\n";
                break;
            }
        }
        for (int i = 0; i < this.disciplinas.size(); i++) {
            saida += this.disciplinas.get(i).historicoEscolar(cpfAluno);
        }
        JOptionPane.showMessageDialog(null, saida, "SACADEM", 1);

    }

}
