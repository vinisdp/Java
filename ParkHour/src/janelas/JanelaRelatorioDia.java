/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import excecoes.ValidacaoException;
import janelaprincipal.JanelaPrincipal;
import validacoes.Validacoes;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import operacoes.RelatorioDia;

/**
 *
 * @author Vini_
 */
public class JanelaRelatorioDia extends javax.swing.JFrame {

  

    /**
     * Creates new form JanelaRelatorioDia
     */
    private JanelaPrincipal janelaPrincipal;
    private JanelaRelatorio janelaRelatorio;
    private MaskFormatter mascaraData;
    public JanelaRelatorioDia(JanelaPrincipal janelaPrincipal,JanelaRelatorio janelaRelatorio) {
        initComponents();
        this.janelaPrincipal = janelaPrincipal;
        this.setLocationRelativeTo(janelaRelatorio);
        this.janelaRelatorio = janelaRelatorio;
        try {
            this.mascaraData = new MaskFormatter("##/##/####");
            this.tData.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        } catch (ParseException ex) {
            Logger.getLogger(JanelaRelatorioDia.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        tData = new javax.swing.JFormattedTextField(mascaraData);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButton1.setText("Gerar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Digite o data do relatorio:");

        tData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            Validacoes.DATA.valida(this.tData.getText());
            RelatorioDia.RelatorioDia(janelaPrincipal, this.tData.getText());
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