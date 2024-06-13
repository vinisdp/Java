/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import janelaprincipal.JanelaPrincipal;
import java.text.DecimalFormat;

/**
 *
 * @author Vini_
 */
public class CalculaValorTotalDia {
    /*Metodo para calcular o valor faturado no dia */
    public static double CalculaValorDia(JanelaPrincipal janelaPrincipal) {
        double saldo=0;
        
        for (int i = 0; i < janelaPrincipal.getModelTable().getRowCount(); i++) {
            
            String valor = ""+janelaPrincipal.getModelTable().getValueAt(i, 2);
            saldo += Double.parseDouble(valor);
        }
        
        return saldo;
    }
}
