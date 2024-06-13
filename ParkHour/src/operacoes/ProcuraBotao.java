/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JList;

/**
 *
 * @author Vini_
 */
public class ProcuraBotao {

    public static boolean ProcuraBotao(LinkedList<JButton> vagasReservadas, JButton botao) {
        int tamanho = vagasReservadas.size();
        boolean resposta = false;
        if (!vagasReservadas.isEmpty()) {
            for (int i = 0; i < tamanho; i++) {
                if (vagasReservadas.get(i).equals(botao)) {
                    resposta = true;
                }
            }
        }
        return resposta;
    }
}
