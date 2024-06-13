/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vini_
 */
public class PagamentoObj implements Serializable{
    private LinkedList<Pagamento> pagamentos = new LinkedList<>();
    
    public PagamentoObj() {
    }

    public LinkedList<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(LinkedList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
}
