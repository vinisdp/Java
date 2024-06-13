/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vini_
 */
public class HashMapOBJ implements Serializable{
    private Map<String, Pagamento> mapVagasReservadas = new HashMap<String, Pagamento>();

    public HashMapOBJ() {
        
    }

    public Map<String, Pagamento> getMapVagasReservadas() {
        return mapVagasReservadas;
    }

    public void setMapVagasReservadas() {
        this.mapVagasReservadas = mapVagasReservadas;
    }

   
    
}
