/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import classes.Configuracao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vini_
 */
public class CalculaQuantidadeHoras {
    /*Metodo para calcular a quantidade de horas
    que foram passadas desde a reserva, onde recebe
    a hora de entrada e a de saida junto com as 
    configurações, separa as horas com o sub string 
    para obter minuto e hora, depois tranforma as 
    horas em minutos e subtrai a menor da maior*/
    public static int CalculaQuantidade(String hrEntrada, String hrSaida, Configuracao configuracao){
        int quantidadeHrEntrada;
        int quantidadeMinEntrada;
        int quantidadeHrFinal;
        int quantidadeMinFinal;
        int quantidadeHrSaida;
        int quantidadeMinSaida;
        quantidadeHrEntrada = Integer.parseInt(hrEntrada.substring(0,2));
        quantidadeMinEntrada = Integer.parseInt(hrEntrada.substring(3,5));
        quantidadeHrSaida = Integer.parseInt(hrSaida.substring(0,2));
        quantidadeMinSaida = Integer.parseInt(hrSaida.substring(3,5));
        quantidadeMinEntrada = quantidadeMinEntrada + (quantidadeHrEntrada*60);
        quantidadeMinSaida = quantidadeMinSaida + (quantidadeHrSaida*60);
        if(quantidadeMinEntrada>quantidadeMinSaida){
            quantidadeMinSaida = quantidadeMinSaida +(24*60);
        }
        quantidadeMinFinal = quantidadeMinSaida - quantidadeMinEntrada;
        quantidadeHrFinal = quantidadeMinFinal/60;
        quantidadeMinFinal = quantidadeMinFinal%60;
        if(quantidadeMinFinal > configuracao.getTolerancia()){
            quantidadeHrFinal = quantidadeHrFinal + 1;
        }
        return quantidadeHrFinal;
    }
}
