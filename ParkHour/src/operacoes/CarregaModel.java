/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import janelas.JanelaConfiguracao;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Vini_
 */
public class CarregaModel {

    public static void CarregaModel(LinkedList lista, JanelaConfiguracao janelaConfiguracao) {
        int tamanho = lista.size();
        DefaultListModel vagas = new DefaultListModel();
        for (int i = 0; i < tamanho; i++) {
            String elemento = ""+lista.get(i);
            vagas.addElement(elemento);
        }
        janelaConfiguracao.setModel(vagas);
    }
}
