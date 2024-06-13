/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import excecoes.ValidacaoException;

/**
 *
 * @author Vini_
 */
public interface Validacao {
    public void validaCampo(String campo)throws ValidacaoException;
}
