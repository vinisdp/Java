/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author Vini_
 */
public class Pagamento implements Serializable{
    private String placa;
    private double totalPago;
    private String data;
    private String horaEntrada;
    private String horaSaida;
    private String vaga;
    private int quantidadeHr;
    public Pagamento(String placa, String data, String horaEntrada, String vaga){
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.data = data;
        this.vaga = vaga;
        this.totalPago = 0;
    }    

    public int getQuantidadeHr() {
        return quantidadeHr;
    }

    public void setQuantidadeHr(int quantidadeHr) {
        this.quantidadeHr = quantidadeHr;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }
    
}
