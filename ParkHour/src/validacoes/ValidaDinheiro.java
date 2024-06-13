/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import excecoes.DinheiroInvalidoException;

/**
 *
 * @author Vini_
 */
public class ValidaDinheiro implements Validacao{
    @Override
    public void validaCampo(String campo) throws DinheiroInvalidoException {
        campo = campo.trim();
        if(!campo.matches("(([0-9]+)*\\.\\d{0})*([0-9]+)")){
            throw new DinheiroInvalidoException();
        }
    }
}
