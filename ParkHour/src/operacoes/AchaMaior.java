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
public class AchaMaior {
    public static int AchaMaior(Configuracao configuracao){
        int maior=0;
        for(int i = 0; i < configuracao.getVagas().size();i++){
            if(maior < configuracao.getVagas().get(i)){
                maior = configuracao.getVagas().get(i);
            }
        }
        return maior;
    }
}
