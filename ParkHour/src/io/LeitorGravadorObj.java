/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import excecoes.ErroDeGravacaoException;
import excecoes.ErroDeLeituraException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 *
 * @author Vini_
 */
public class LeitorGravadorObj implements LeituraGravacao{
    /*Metodo utilizado para gravar os arquivos
    onde ele verificase o diretorio existe,caso 
    ele não existir o mesmo é criado, é criado os 
    3 objetos, apos isso é passado o que tem dentro 
    dos arquivos que são indicados pelo FileOutputStream, 
    apos isso ele grava os dados das List nos respectivos
    arquivos*/

     @Override
     public void gravarArquivo(Object objeto, String path) throws ErroDeGravacaoException{
        File dir = new File("./dados");
        if(!dir.exists()){
            dir.mkdir();
        }
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream("./dados/" + path));
            out.writeObject(objeto);

        } catch (IOException ex) {
            throw new ErroDeGravacaoException();
        }finally{
            try {
                out.close();
            } catch (IOException ex) {
                throw new ErroDeGravacaoException();
            }
        }
        
        
    }
     /*Metodos utilizados para Leitura de arquivo
     onde é aberto o arquivo especificado
     verifica se o arquivo existe, caso não existir
     é retornado null, se existir ele cria um objeto
     e uma arrayList e depois é atribuido o arquivo para
     esse arraylist e a arraylist é retornada para
     classe principal*/
     @Override
    public Object lerArquivo(String path) throws ErroDeLeituraException, ClassNotFoundException{
        File dados = new File("./dados/"+path);
        
        if(!dados.exists()){
            return null;
        }
        
        ObjectInputStream in = null;
        Object objeto = null;
        try {
            in = new ObjectInputStream(new FileInputStream(dados));
            objeto = (Object)in.readObject();
        } catch (IOException ex) {
            throw new ErroDeLeituraException();
        }finally{
            try {
                in.close();
            } catch (IOException ex) {
                throw new ErroDeLeituraException();
            }
        }
            
        
        return objeto;
    }



    
}
