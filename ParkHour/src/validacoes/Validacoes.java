/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import excecoes.PlacaInvalidaException;
import excecoes.ValidacaoException;

/**
 *
 * @author Vini_
 */
/*Enum para chamada de validações*/
public enum Validacoes {
    PLACA (new ValidacaoPlaca()),
    MONETARIO (new ValidaDinheiro()),
    DATA (new ValidaData()),
    QUANTIDADE (new ValidacaoQuantidade());
    
    private Validacao validacao;
    
    Validacoes(Validacao validacao){
        this.validacao = validacao;
    }
    
    public void valida(String campo) throws ValidacaoException{
        validacao.validaCampo(campo);
    }

   
}
