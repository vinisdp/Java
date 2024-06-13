/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import janelaprincipal.JanelaPrincipal;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vini_
 */
public class CarregaTableModel {
    public static void CarregaTableModel(JanelaPrincipal janelaPrincipal){
        
        for (int i = 0; i < janelaPrincipal.getPagamento().size(); i++) {
            if(janelaPrincipal.getPagamento().get(i).getData().equals(OperacaoFormataDataHora.OperacaoFormataDataHora("dd/MM/yyyy"))  ){
               
                janelaPrincipal.getModelTable().addRow(new String[]{""+janelaPrincipal.getPagamento().get(i).getPlaca(),""+janelaPrincipal.getPagamento().get(i).getQuantidadeHr(),""+janelaPrincipal.getPagamento().get(i).getTotalPago()});
            }
        }

    }
}
