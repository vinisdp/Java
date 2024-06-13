/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import excecoes.ErroDeLeituraException;


/**
 *
 * @author Vini_
 */
public class Carregador {
    /*Metodos de chamada para leitura de arquivos*/
    public static Object carregarBase(LeituraGravacao arq, String path) throws ErroDeLeituraException,ClassNotFoundException{
        return arq.lerArquivo(path);
    }
   
}
