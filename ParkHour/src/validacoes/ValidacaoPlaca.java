/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import excecoes.PlacaInvalidaException;

/**
 *
 * @author Vini_
 */
public class ValidacaoPlaca implements Validacao{
    @Override
    public void validaCampo(String campo) throws PlacaInvalidaException{
        campo = campo.trim();
        if(campo.matches("([A-Z]{3}\\d{4})|([A-Z]{3}\\d[A-Z]\\d{2})")){
        } else {
            throw new PlacaInvalidaException();
        }
    }
}
