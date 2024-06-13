/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import com.itextpdf.text.DocumentException;
import io.GerarPDF;
import janelaprincipal.JanelaPrincipal;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vini_
 */
public class RelatorioDia {
    public static void RelatorioDia(JanelaPrincipal janelaPrincipal,String data) throws IOException {
        DecimalFormat deci = new DecimalFormat("0.00");
        String relatorio = "\n";
        relatorio+="=========================================================\n";
        String aux = "relatorioDia"+data.substring(0, 2)+"Mes"+data.substring(3, 5)+"Ano"+data.substring(6, 10)+".pdf";
        double total = 0;
        for(int i = 0; i<janelaPrincipal.getPagamento().size();i++){
            if(data.equals(janelaPrincipal.getPagamento().get(i).getData())){
                relatorio+="| Placa:"+janelaPrincipal.getPagamento().get(i).getPlaca()+" | ";
                relatorio+="Entrada:"+janelaPrincipal.getPagamento().get(i).getHoraEntrada()+" | ";
                relatorio+="Saida:"+janelaPrincipal.getPagamento().get(i).getHoraSaida()+" | ";
                relatorio+="Valor:"+deci.format(janelaPrincipal.getPagamento().get(i).getTotalPago())+" |\n";
                relatorio+="=========================================================\n";
                total += Double.parseDouble(""+janelaPrincipal.getPagamento().get(i).getTotalPago());
            }
        }
        relatorio+= "Total:R$"+deci.format(total)+"\n";
        
        try {
            GerarPDF.GerarPDF(relatorio, aux, data);
        } catch (DocumentException ex) {
            Logger.getLogger(RelatorioMes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
