/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import excecoes.ErroDeGravacaoException;
import excecoes.ErroDeLeituraException;



/**
 *
 * @author Vini_
 */
public interface LeituraGravacao {
    public abstract void gravarArquivo(Object objeto, String path) throws ErroDeGravacaoException;
    
    public abstract Object lerArquivo(String path) throws ErroDeLeituraException, ClassNotFoundException;

    
}
