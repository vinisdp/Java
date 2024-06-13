/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vini_
 */
public class OperacaoFormataDataHora {
    public static String OperacaoFormataDataHora(String formato){
        DateFormat dateFormat = new SimpleDateFormat(formato);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
