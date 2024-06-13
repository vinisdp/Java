/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JList;

/**
 *
 * @author Vini_
 */
public class SalvarJListLinked {
    public static LinkedList<Integer> SalvarJListLinked(JList lista){
        int tamanho = lista.getModel().getSize();
        LinkedList<Integer> vagas = new LinkedList<Integer>();
        for(int i = 0; i < tamanho; i++){
            int elemento = Integer.parseInt((String) lista.getModel().getElementAt(i));
            vagas.add(elemento);
        }
        return vagas;
    }
}
