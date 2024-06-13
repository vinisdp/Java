/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import excecoes.ErroDeGravacaoException;



/**
 *
 * @author Vini_
 */
public class Gravador {
    /*Metodo de chamada para gravar os arquivos*/
    public static void gravarBase(LeituraGravacao arq,Object objeto,String path) throws ErroDeGravacaoException{
        arq.gravarArquivo(objeto, path);
    }
}
