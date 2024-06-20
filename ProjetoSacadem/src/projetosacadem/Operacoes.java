package projetosacadem;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Operacoes {

    /*Esse metodo é utilizado para apresentar o 
    menu principal e retornar o valor escolhido 
    para classe principal*/
    public int menuPrincipal() {
        String op = JOptionPane.showInputDialog(null, "1 - Curso\n2 - Disciplina\n3 - Aluno\n4 - Matricular Aluno\n5 - Cadastrar Avaliação\n6 - Historico Escolar\n7 - Sair\nDigite a opção desejada:", "SACADEM", 1);
        return Integer.parseInt(op);
    }

    /*Esse metodo é utilizado para apresentar o 
    menu interno das opcoes curso, aluno e 
    disciplina e retornar o valor escolhido para
    classe principal*/
    public int menuInterno() {
        String op = JOptionPane.showInputDialog(null, "1 - Cadastrar\n2 - Alterar\n3 - Remover\n4 - Voltar\n Digite a opção desejada:", "SACADEM", 1);
        return Integer.parseInt(op);
    }

    /*Esse metodo esta pegando as informacoes para cadastrar
    o curso chamando a funcao que verifica se o codigo do curso 
    já foi cadastrado e assim cadastrando o curso se nenhum 
    curso for encontrado e retorna o arraylist para o menu 
    principal*/
    public ArrayList<Curso> cadastraCurso(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso:", "SACADEM", 1);

        Curso procura;
        if (cursos.isEmpty()) {
            procura = null;
        } else {
            procura = procuraCadastrarCurso(cursos, codigoCurso);
        }
        if (procura == null) {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do curso:", "SACADEM", 1);
            String strQuantidadeAlunos = JOptionPane.showInputDialog(null, "Digite a quantidade maxima de vagas do curso:", "SACADEM", 1);
            int quantidadeAlunos = Integer.parseInt(strQuantidadeAlunos);
            procura = new Curso(codigoCurso, nome, quantidadeAlunos);
            cursos.add(procura);
            JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!", "SACADEM", 1);

        } else {
            JOptionPane.showMessageDialog(null, "Curso já cadastrado!", "SACADEM", 2);
        }
        return cursos;
    }

    /*Esse metodo está verificando se existe algum curso e se 
    caso existir ele chama a funcao que busca esse curso para 
    verificar se o codigo digitado pelo usuario é de um curso 
    cadastrado ou nao*/
    public void alteraCurso(ArrayList<Curso> cursos) {
        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado!", "SACADEM", 2);
        } else {
            String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que deseja alterar:", "SACADEM", 1);
            procuraCurso(cursos, codigoCurso);

        }
    }

    /*Esse metodo está verificando se existe o codigo informado
    pelo usuario no metodo alteraCurso se existir ele pede para
    o usuario digitar novos valores para os campos que existem
    no curso*/
    public void procuraCurso(ArrayList<Curso> cursos, String codigoCurso) {

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();
            if (codigo.equals(codigoCurso)) {
                cursos.get(i);
                String alteraCodigoCurso = JOptionPane.showInputDialog(null, "Digite o novo Codigo do Curso que deseja alterar:", "SACADEM", 1);
                String nome = JOptionPane.showInputDialog(null, "Digite o novo nome do curso que deseja alterar:", "SACADEM", 1);
                String strQuantidadeAluno = JOptionPane.showInputDialog(null, "Digite a nova quantidade de vaga do Curso que deseja alterar:", "SACADEM", 1);
                int quantidadeAluno = Integer.parseInt(strQuantidadeAluno);
                cursos.get(i).setCodigo(alteraCodigoCurso);
                cursos.get(i).setNome(nome);
                cursos.get(i).setQuantidadeAlunos(quantidadeAluno);
                JOptionPane.showMessageDialog(null, "Curso alterado com sucesso!", "SACADEM", 1);
                break;
            }
        }

    }

    /*Esse metodo primeiro verifica se o array 
    esta vazio se não estiver ele pede o codigo 
    do curso para o usuario e depois envia para 
    o metodo dentro da classe Curso, depois verifica
    se a resposta dessa função é true ou false e da 
    a mensagem pro usuario */
    public void removeCurso(ArrayList<Curso> cursos) {
        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado!", "SACADEM", 2);
        } else {
            String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que deseja remover:", "SACADEM", 1);
            boolean procura = procuraRemoverCurso(cursos, codigoCurso);
            if (procura != false) {
                JOptionPane.showMessageDialog(null, "Curso removido com sucesso!", "SACADEM", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Esse metodo percorre todo o array de curso e 
    lista em uma String depois ele retorna essa String*/
    public String imprimeCurso(ArrayList<Curso> cursos) {
        String saida = "";
        for (int i = 0; i < cursos.size(); i++) {
            saida += cursos.get(i).listarCursos();
        }
        return saida;
    }

    /*Esse metodo percorre o array de cursos e 
    compara se o codigo digitado pelo usuario 
    é igual a algum curso dentro do arraylist 
    se ele encontra ele remove o curso do 
    arraylist */
    public boolean procuraRemoverCurso(ArrayList<Curso> cursos, String codigoCurso) {
        boolean resposta = false;
        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();
            if (codigo.equals(codigoCurso)) {
                cursos.remove(i);
                resposta = true;
                break;
            }
        }
        return resposta;
    }

    /*Esse metodo percorre o array de cursos e 
    compara se o codigo digitado pelo usuario 
    é igual a algum curso dentro do arraylist 
    se ele encontra ele remove o curso do 
    arraylist*/
    public Curso procuraCadastrarCurso(ArrayList<Curso> cursos, String codigoCurso) {
        Curso curso = null;

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                curso = cursos.get(i);
                break;
            }
        }
        return curso;
    }

    /*Esse metodo percorre o arraylist de cursos se
    caso achar o curso que foi selecionado ele chama
    a função dentro da classe Curso quando não acha 
    ele retorna a mensagem pro cliente*/
    public void procuraCadastraDisciplina(ArrayList<Curso> cursos, String codigoCurso) {

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                cursos.get(i).cadastraDisciplina(codigoCurso);
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Esse metodo percorre o arraylist de cursos se
    caso achar o curso que foi selecionado ele chama
    a função dentro da classe Curso quando não acha 
    ele retorna a mensagem pro cliente*/
    public void procuraAlteraDisciplina(ArrayList<Curso> cursos, String codigoCurso) {
        Disciplina disciplina;
        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                cursos.get(i).alteraDisciplina();
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Esse metodo percorre o arraylist de cursos se
    caso achar o curso que foi selecionado ele chama
    a função dentro da classe Curso quando não acha 
    ele retorna a mensagem pro cliente*/
    public void procuraRemoverDisciplina(ArrayList<Curso> cursos, String codigoCurso) {

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                cursos.get(i).removerDisciplina();
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Esse metodo percorre o arraylist de cursos se
    caso achar o curso que foi selecionado ele chama
    a função dentro da classe Curso quando não acha 
    ele retorna a mensagem para o usuario*/
    public void procuraCadastraAluno(ArrayList<Curso> cursos, String codigoCurso) {

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                cursos.get(i).cadastraAluno();
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Esse metodo percorre o arraylist de cursos se
    caso achar o curso que foi selecionado ele chama
    a função dentro da classe Curso quando não acha 
    ele retorna a mensagem para o usuario*/
    public void procuraRemoveAluno(ArrayList<Curso> cursos, String codigoCurso) {

        for (int i = 0; i < cursos.size(); i++) {
            String codigo = cursos.get(i).getCodigo();

            if (codigo.equals(codigoCurso)) {
                cursos.get(i).removerAluno();
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado!", "SACADEM", 2);
            }
        }

    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    cadastrar uma disciplina no curso escolhido*/
    public void cadastraDisciplina(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que deseja alterar:", "SACADEM", 1);
        procuraCadastraDisciplina(cursos, codigoCurso);

    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    Alterar uma disciplina no curso escolhido*/
    public void alteraDisciplina(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que deseja alterar:", "SACADEM", 1);
        procuraAlteraDisciplina(cursos, codigoCurso);
    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    remover uma disciplina no curso escolhido*/
    public void removerDisciplina(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que a Disciplina que deseja remover pertence:", "SACADEM", 1);
        procuraRemoverDisciplina(cursos, codigoCurso);
    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    cadastrar um aluno no curso escolhido*/
    public void cadastraAluno(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que o aluno que deseja cadastrar pertence::", "SACADEM", 1);
        procuraCadastraAluno(cursos, codigoCurso);

    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    alterar um aluno no curso escolhido*/
    public void alteraAluno(ArrayList<Curso> cursos) {
        boolean resposta = false;
        String cpfAluno = JOptionPane.showInputDialog(null, "\nDigite o CPF do aluno que deseja alterar:", "SACADEM", 1);
        for (int i = 0; i < cursos.size(); i++) {
            resposta = cursos.get(i).alteraAluno(cpfAluno);
            if (resposta) {
                break;
            }
        }
        if (resposta == false) {
            JOptionPane.showMessageDialog(null, "Aluno não cadastrado!", "SACADEM", 1);
        }

    }

    /*Metodo utilizado para pegar o codigo do curso para poder 
    remover um aluno no curso escolhido*/
    public void removeAluno(ArrayList<Curso> cursos) {
        String codigoCurso = JOptionPane.showInputDialog(null, imprimeCurso(cursos) + "Digite o Codigo do Curso que o aluno que deseja remover pertence:", "SACADEM", 1);
        procuraRemoveAluno(cursos, codigoCurso);
    }

    /*Esse metodo é utilizado para procurar a disciplina e o aluno
    percorrendo a arraylist de curso e chamando a matriculaAluno 
    dentro da classe Curso*/
    public void matriculaAluno(ArrayList<Curso> cursos) {
        boolean resposta;
        String cpfAluno = JOptionPane.showInputDialog(null, "Digite o CPF do aluno que deseja matricular:", "SACADEM", 1);
        String codigoDisciplina = JOptionPane.showInputDialog(null, "Digite o codigo da disciplina que deseja matricular o aluno:", "SACADEM", 1);
        for (int i = 0; i < cursos.size(); i++) {
            resposta = cursos.get(i).matriculaAluno(cpfAluno, codigoDisciplina);
            if (resposta) {
                break;
            }
        }

    }

    /*metodo utilizado para pedir os codigos do aluno e da 
    disciplina para o usuario, com isso ele percorre a arraylist 
    de cursos chamando o metodo cadastrarAvaliação e se a resposta 
    for true ele quebra o laço*/
    public void cadastrarAvaliacao(ArrayList<Curso> cursos) {
        boolean resposta = false;
        String cpfAluno = JOptionPane.showInputDialog(null, "Digite o CPF do aluno que deseja cadastrar a avaliação:", "SACADEM", 1);
        String codigoDisciplina = JOptionPane.showInputDialog(null, "Digite o codigo da disciplina que o aluno está matriculado:", "SACADEM", 1);
        for (int i = 0; i < cursos.size(); i++) {
            resposta = cursos.get(i).cadastraAvaliacao(cpfAluno, codigoDisciplina);
            if (resposta) {
                JOptionPane.showMessageDialog(null, "Avaliação cadastrada com sucesso!", "SACADEM", 1);
                break;
            }
        }
        if (resposta == false) {
            JOptionPane.showMessageDialog(null, "Aluno ou disciplina não cadastrada(o)!", "SACADEM", 2);
        }
    }
    
    /*Este metodo pede para o usuario digitar o cpf do aluno
    que deseja ver o histórico escolar e percorre a arrayliste
    de cursos chamando o metodo buscaAluno para ver se o aluno
    existe se a resposta for true ele quebra o laço, se for
    falsa continua o laço, caso chegar no final da arraylist e
    a resposta ainda for false ele retorna a mensagem que o
    aluno n foi encontrado*/
    public void historicoEscolar(ArrayList<Curso> cursos) {
        boolean resposta = false;
        String cpfAluno = JOptionPane.showInputDialog(null, "Digite o CPF do aluno que deseja ver o histórico:", "SACADEM", 1);
        for (int i = 0; i < cursos.size(); i++) {
            resposta = cursos.get(i).buscaAluno(cpfAluno);
            if (resposta) {
                break;
            }
        }
        if (resposta == false) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado!", "SACADEM", 2);
        }
    }

}
