/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelaprincipal;

import classes.Configuracao;

import classes.HashMapOBJ;
import classes.Pagamento;
import classes.PagamentoObj;

import excecoes.ErroDeGravacaoException;
import excecoes.ErroDeLeituraException;
import io.Carregador;
import io.Gravador;
import io.LeitorGravadorObj;

import janelas.JanelaConfiguracao;
import janelas.JanelaFaturamento;
import janelas.JanelaRelatorio;
import janelas.JanelaReservaVaga;
import java.awt.Color;
import operacoes.OperacaoFormataDataHora;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import operacoes.AchaMaior;
import operacoes.CalculaValorTotalDia;
import operacoes.CarregaTableModel;

/**
 *
 * @author Vini_
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    private JButton[][] botoes;
    private Configuracao configuracao;
    private PagamentoObj pagamento;
    private HashMapOBJ hashMap;
    private DefaultTableModel modelTable;

    public HashMapOBJ getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMapOBJ hashMap) {
        this.hashMap = hashMap;
    }

    public JanelaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.modelTable = (DefaultTableModel) this.tFaturamentoDia.getModel();
        try {
            this.configuracao = (Configuracao) Carregador.carregarBase(new LeitorGravadorObj(), "configuracao.dat");
            this.pagamento = (PagamentoObj) Carregador.carregarBase(new LeitorGravadorObj(), "pagamentos.dat");
            this.hashMap = (HashMapOBJ) Carregador.carregarBase(new LeitorGravadorObj(), "hashModel.dat");
            if (this.configuracao == null) {
                configuracao = new Configuracao();
            }
            if (this.pagamento == null) {
                this.pagamento = new PagamentoObj();
            }
            if (this.hashMap == null) {
                this.hashMap = new HashMapOBJ();
            }
            
            CarregaTableModel.CarregaTableModel(this);
            this.tFaturamentoDia.setModel(this.modelTable);
        } catch (ErroDeLeituraException ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!configuracao.getVagas().isEmpty()) {
            criaBotao();
            pVagas.setLayout(new GridLayout(AchaMaior.AchaMaior(configuracao), configuracao.getVagas().size(), 2, 2));
        }
        if (modelTable.getRowCount() != 0) {
            DecimalFormat deci = new DecimalFormat("0.00");
            this.lTotal.setText("R$" + deci.format(CalculaValorTotalDia.CalculaValorDia(this)));
        }

        Thread threadHora = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    lHorario.setText("Horario: " + OperacaoFormataDataHora.OperacaoFormataDataHora("HH:mm:ss"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        threadHora.start();

    }

    public JTable getjTable1() {
        return tFaturamentoDia;
    }

    public void setjTable1(JTable jTable1) {
        this.tFaturamentoDia = jTable1;
    }

    public DefaultTableModel getModelTable() {
        return this.modelTable;
    }

    public void setModelTable(DefaultTableModel modelTable) {
        this.modelTable = modelTable;
    }

    public class listaAcao implements ActionListener {

        private JanelaPrincipal janelaPrincipal;

        public listaAcao(JanelaPrincipal janelaPrincipal) {
            this.janelaPrincipal = janelaPrincipal;
        }
        /*Esse metodo é utilizada para pegar qual vaga foi selecionada 
        onde eu pegogo todos os componentes do painel e vou comparando 
        com o componente do evento depois eu comparo se já existe no 
        hashMap de vagas reservadas se existir abre a janela de 
        faturamento, se não abre a de reserva de vaga*/
        @Override
        public void actionPerformed(ActionEvent e) {
            Component[] c = pVagas.getComponents();
            JButton botao = (JButton) e.getSource();
            for (int i = 0; i < c.length; i++) {
                if (botao.equals(pVagas.getComponent(i))) {
                    System.out.println();
                    if (!janelaPrincipal.hashMap.getMapVagasReservadas().containsKey(botao.getText())) {
                        new JanelaReservaVaga(janelaPrincipal, botao);
                        janelaPrincipal.enable(false);
                        e.setSource(botao);
                    } else {

                        new JanelaFaturamento(janelaPrincipal, botao);
                        janelaPrincipal.enable(false);
                        e.setSource(botao);
                    }
                }
            }
        }
    }

    public LinkedList<Pagamento> getPagamento() {
        return pagamento.getPagamentos();
    }
    /*Esse Metodo cria os botoes de acordo com 
    a configuração onde eu criado uma matriz 
    de botão com a proporção do tamanho da 
    lista x maior da lista, com isso é 
    criado o contador e uma variavel 
    de controle, essa variavel de 
    controle é utilizada para verificar 
    se a coluna está compativel com o valor 
    estipulado na configuração, se estiver 
    o botão fica visivel, acrescenta um no 
    contador e no controle se o controle for 
    igual ele deixa o botão invisivel, ao final 
    de cada coluna é zerado o controlador*/
    public void criaBotao() {
        botoes = new JButton[configuracao.getVagas().size()][AchaMaior.AchaMaior(configuracao)];
        int cont = 1;
        int controle = 0;
        int elemento;
        for (int i = 0; i < configuracao.getVagas().size(); i++) {
            for (int j = 0; j < AchaMaior.AchaMaior(configuracao); j++) {

                botoes[i][j] = new JButton("Vaga " + cont + ".");

                botoes[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/carro.png")));
                botoes[i][j].setVerticalTextPosition(SwingConstants.BOTTOM);
                botoes[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                botoes[i][j].setMargin(new Insets(5, 5, 5, 5));
                botoes[i][j].setPreferredSize(new Dimension(97, 83));
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 12));
                botoes[i][j].addActionListener(new listaAcao(this));

                elemento = configuracao.getVagas().get(i);
                if (controle < elemento) {
                    cont++;
                    botoes[i][j].setVisible(true);
                    controle++;
                } else {

                    botoes[i][j].setVisible(false);
                }

            }
            controle = 0;
        }
        adicionaBotao();
    }
    /*Esse metodo adiciona o botão no painel verificando 
    se tem vaga reservada, caso tenha ele seta o icon para 
    o carro vermelho e a cor da letra pra vermelho*/
    public void adicionaBotao() {
        for (int j = 0; j < AchaMaior.AchaMaior(configuracao); j++) {
            for (int i = 0; i < configuracao.getVagas().size(); i++) {
                if (hashMap.getMapVagasReservadas().containsKey(botoes[i][j].getText())) {
                    botoes[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/carroVermelho.png")));
                    botoes[i][j].setForeground(Color.red);
                }
                pVagas.add(botoes[i][j]);
            }
        }
    }

    public void setpVagas(JPanel pVagas) {
        this.pVagas = pVagas;
    }

    public JPanel getpVagas() {
        return pVagas;
    }

    public Map<String, Pagamento> getMapVagasReservadas() {
        return hashMap.getMapVagasReservadas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lData = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lHorario = new javax.swing.JLabel();
        spVagas = new javax.swing.JScrollPane();
        pVagas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tFaturamentoDia = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lTotal = new javax.swing.JLabel();
        lTotal1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ParkHour");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/config.png"))); // NOI18N
        jButton1.setText("Configurações");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sair.png"))); // NOI18N
        jButton2.setText("Sair");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        jButton3.setText("Relatórios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton3)
                .addComponent(jButton1)
                .addComponent(jButton2))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lData.setText("Data: " + operacoes.OperacaoFormataDataHora.OperacaoFormataDataHora("dd/MM/yyyy"));

        jLabel2.setText("ParkHour");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lData)
                .addGap(169, 169, 169)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lHorario)
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lData, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addComponent(lHorario))
        );

        spVagas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vagas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pVagas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pVagasLayout = new javax.swing.GroupLayout(pVagas);
        pVagas.setLayout(pVagasLayout);
        pVagasLayout.setHorizontalGroup(
            pVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        pVagasLayout.setVerticalGroup(
            pVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        spVagas.setViewportView(pVagas);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faturamento: " + operacoes.OperacaoFormataDataHora.OperacaoFormataDataHora("dd/MM/yyyy"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11)));

        tFaturamentoDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Qtd. Horas", "Valor Cobrado"
            }
        ));
        tFaturamentoDia.setEnabled(false);
        jScrollPane1.setViewportView(tFaturamentoDia);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lTotal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        lTotal1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lTotal1.setText("Total:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new JanelaConfiguracao(this);
        this.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.enable(false);
        new JanelaRelatorio(this);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            Gravador.gravarBase(new LeitorGravadorObj(), this.configuracao, "configuracao.dat");
            Gravador.gravarBase(new LeitorGravadorObj(), this.pagamento, "pagamentos.dat");
            Gravador.gravarBase(new LeitorGravadorObj(), this.hashMap, "hashModel.dat");
        } catch (ErroDeGravacaoException ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    public JLabel getlTotal() {
        return lTotal;
    }

    public void setlTotal(JLabel lTotal) {
        this.lTotal = lTotal;
    }

    public JButton[][] getBotoes() {
        return botoes;
    }

    public void setBotoes(JButton[][] botoes) {
        this.botoes = botoes;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lData;
    private javax.swing.JLabel lHorario;
    private javax.swing.JLabel lTotal;
    private javax.swing.JLabel lTotal1;
    private javax.swing.JPanel pVagas;
    private javax.swing.JScrollPane spVagas;
    private javax.swing.JTable tFaturamentoDia;
    // End of variables declaration//GEN-END:variables
}
