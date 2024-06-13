/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import excecoes.DataInvalidaException;
import excecoes.ValidacaoException;


/**
 *
 * @author Vini_
 */
public class ValidaData  implements Validacao{

    @Override
    public void validaCampo(String campo) throws DataInvalidaException {
       campo = campo.trim();
        if(!campo.matches("(\\d{2}\\/\\d{2}\\/\\d{4})|(\\d{2}\\/\\d{4})")){
            throw new DataInvalidaException();
        } 
    }
   
}
