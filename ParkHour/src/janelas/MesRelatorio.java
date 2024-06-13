/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import excecoes.DataInvalidaException;
import excecoes.ValidacaoException;
import janelaprincipal.JanelaPrincipal;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import operacoes.RelatorioMes;
import validacoes.Validacoes;

/**
 *
 * @author Vini_
 */
public class MesRelatorio extends javax.swing.JFrame {

    /**
     * Creates new form MesRelatorio
     */
    private JanelaPrincipal janelaPrincipal;
    private JanelaRelatorio janelaRelatorio;
    private MaskFormatter mascaraData;
    public MesRelatorio(JanelaPrincipal janelaPrincipal, JanelaRelatorio janelaRelatorio) {
        initComponents();
        this.janelaPrincipal = janelaPrincipal;
        this.setLocationRelativeTo(janelaRelatorio);
        this.janelaRelatorio = janelaRelatorio;
        try {
            this.mascaraData = new MaskFormatter("##/####");
            this.tData.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        } catch (ParseException ex) {
            Logger.getLogger(MesRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        this.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        tData = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButton1.setText("Gerar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDataActionPerformed(evt);
            }
        });

        jLabel1.setText("Digite o mês e o ano:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            Validacoes.DATA.valida(""+tData.getText());
            RelatorioMes.RelatorioMes(janelaPrincipal, this.tData.getText());
            
            this.dispose();
            janelaRelatorio.dispose();
            janelaPrincipal.enable(true);
        } catch (IOException | ValidacaoException ex) {
            JOptionPane.showMessageDialog(this, ex);
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDataActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField tData;
    // End of variables declaration//GEN-END:variables
}