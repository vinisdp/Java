/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import classes.Configuracao;

/**
 *
 * @author Vini_
 */
public class CalculaValorFatura {
    /*Metodo onde recebe quantidade de hrs e a configuração
    e multiplica a quantidade de hr pelo valor da hora*/
    public static double CalculaValorFatura(int hr, Configuracao configuracao){
        double valor = (hr*configuracao.getValor());
        return valor;
    }
}
