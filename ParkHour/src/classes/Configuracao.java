/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Vini_
 */
public class Configuracao implements Serializable{
    private double valor;
    private int tolerancia;
    private LinkedList<Integer> vagas = new LinkedList<>(); 
    
    public Configuracao(double valor, int tolerancia, LinkedList<Integer> vagas){
        this.valor = valor;
        this.tolerancia = tolerancia;
        this.vagas = vagas;
    }

    public Configuracao() {
        
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public LinkedList<Integer> getVagas() {
        return vagas;
    }

    public void setVagas(LinkedList<Integer> vagas) {
        this.vagas = vagas;
    }
    
    
}
