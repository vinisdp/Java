package projetosacadem;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Vini_
 */
public class Sacadem {

    public static void main(String[] args) {
        Curso curso = null;
        Operacoes operacoes = new Operacoes();
        ArrayList<Curso> cursos = new ArrayList<Curso>();

        boolean sair = false;

        while (sair == false) {
            int op = operacoes.menuPrincipal();
            if (op >= 1 && op <= 7) {
                if (op >= 2 && op <= 6 && cursos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum curso foi cadastrado", "SACADEM", 2);
                } else {
                    if (op == 1) {
                        int op2 = operacoes.menuInterno();
                        if (op2 >= 1 && op2 <= 4) {
                            if (cursos.isEmpty() && op2 > 1 && op2 < 4) {
                                JOptionPane.showMessageDialog(null, "Não possui cursos cadastrados!", "SACADEM", 2);
                            } else {
                                if (op2 == 1) {

                                    //cadastrar
                                    cursos = operacoes.cadastraCurso(cursos);

                                } else if (op2 == 2) {
                                    //alterar
                                    operacoes.alteraCurso(cursos);

                                } else if (op2 == 3) {
                                    //remover
                                    operacoes.removeCurso(cursos);

                                } else if (op2 == 4) {
                                    //Voltar
                                    continue;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opção invalida!", "SACADEM", 0);
                        }

                    } else if (op == 2) {
                        //Disciplina

                        int op2 = operacoes.menuInterno();

                        if (op2 >= 1 && op2 <= 4) {
                            if (cursos.isEmpty() && op2 > 1 && op2 < 4) {
                                JOptionPane.showMessageDialog(null, "Não possui cursos cadastrados!", "SACADEM", 2);
                            } else {
                                if (op2 == 1) {
                                    //cadastrar
                                    operacoes.cadastraDisciplina(cursos);
                                } else if (op2 == 2) {
                                    //alterar
                                    operacoes.alteraDisciplina(cursos);
                                } else if (op2 == 3) {
                                    //remover
                                    operacoes.removerDisciplina(cursos);
                                } else if (op2 == 4) {
                                    //Voltar
                                    continue;
                                }
                            }
                        }
                    } else if (op == 3) {
                        //Aluno

                        int op2 = operacoes.menuInterno();
                        if (op2 >= 1 && op2 <= 4) {
                            if (cursos.isEmpty() && op2 > 1 && op2 < 4) {
                                JOptionPane.showMessageDialog(null, "Não possui cursos cadastrados!", "SACADEM", 2);
                            } else {
                                if (op2 == 1) {
                                    //cadastrar
                                    operacoes.cadastraAluno(cursos);
                                } else if (op2 == 2) {
                                    //alterar
                                    operacoes.alteraAluno(cursos);
                                } else if (op2 == 3) {
                                    //remover
                                    operacoes.removeAluno(cursos);
                                } else if (op2 == 4) {
                                    //Voltar
                                    continue;
                                }
                            }
                        }

                    } else if (op == 4) {
                        //Matricular o aluno
                        operacoes.matriculaAluno(cursos);
                    } else if (op == 5) {
                        //Cadastrar avaliacoes
                        operacoes.cadastrarAvaliacao(cursos);
                    } else if (op == 6) {
                        //Historico Escolar
                        operacoes.historicoEscolar(cursos);
                    } else if (op == 7) {
                        //Sair
                        sair = true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opção invalida!", "SACADEM", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
