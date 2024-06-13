/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;


import excecoes.QuantidadeInvalidaException;
import excecoes.ValidacaoException;

/**
 *
 * @author Vini_
 */
public class ValidacaoQuantidade implements Validacao{

    @Override
    public void validaCampo(String campo) throws  QuantidadeInvalidaException{
         campo = campo.trim();
        if(!campo.matches("[0-9]+")){
            throw new QuantidadeInvalidaException();
        }
    }
    
}
