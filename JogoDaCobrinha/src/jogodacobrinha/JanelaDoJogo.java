/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodacobrinha;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rafael
 */
public class JanelaDoJogo extends JFrame{
    
    private final int SIZE = 20;
    private JPanel[][] painels;
    private Random random;
    private int posCX;
    private int posCY;
    private int posAX;
    private int posAY;
    private String tecla;
    private ArrayList<Point> pontos;
    private int delay = 500;
    
    public JanelaDoJogo(){
        random = new Random();
        
        initComponents();
        
        initWindow();
        
        pontos = new ArrayList<Point>();
        
        
        posCX = random.nextInt(SIZE);
        posCY = random.nextInt(SIZE);
        pontos.add(new Point(posCX,posCY));
                
        sorteioAlvo();
        
        this.addKeyListener(new HandlerTeclado());
        this.setVisible(true);
        
        tecla = "";
        while(true){
            try {
                movimento();
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(JanelaDoJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void initComponents(){
        apagaTela();
    }
    
    public void apagaTela(){
        this.getContentPane().removeAll();
        painels = new JPanel[SIZE][SIZE];
        for(int i = 0;i<SIZE;i++){
            for(int j = 0;j<SIZE;j++){
                painels[i][j] = new JPanel();
                painels[i][j].setBackground(Color.white);
                this.add(painels[i][j]);
            }
        }
        this.revalidate();
    }
    
    public void initWindow(){
        this.setSize(600, 600);
        this.setTitle("Jogo da Cobrinha - POO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(20,20,1,1));
    }
    
    public void sorteioAlvo(){
        posAX = random.nextInt(SIZE);
        posAY = random.nextInt(SIZE);
        
        while(posAX == posCX && posAY == posCY){
            posAX = random.nextInt(SIZE);
            posAY = random.nextInt(SIZE);
        }
        
        painels[posAY][posAX].setBackground(Color.red);
    }
    
    public void gameOver(){
        JOptionPane.showMessageDialog(null, "GAME OVER!!!!!!");
        System.exit(0);
    }
    
    public void movimento(){
        int xNovo = posCX;
        int yNovo = posCY;
        
        if(tecla.equals("cima")) { // Para cima
            yNovo--;
        } else if(tecla.equals("baixo")) { // Para baixo
            yNovo++;
        } else if(tecla.equals("direita")) { // Para direita
            xNovo++;
        } else if(tecla.equals("esquerda")) { // Para esquerda
            xNovo--;
        } else if(tecla.equals("")){
            painels[posCY][posCX].setBackground(Color.black);
            return;
        }
        
        // Só alterar a cor do quadrado se a posição for válida
        if(xNovo >= 0 && xNovo < 20 && yNovo >= 0 && yNovo < 20) {
            
            posCX = xNovo;
            posCY = yNovo;
            
            Point novoPonto = new Point(posCX,posCY);
            if (pontos.contains(novoPonto)){
                gameOver();
            }
            pontos.add(novoPonto);
            if (posCX == posAX && posCY == posAY) {
                sorteioAlvo();
                if(delay > 50){
                    delay = delay - 50;
                }
            } else {
                pontos.remove(0);
            }
            
            apagaTela();
            painels[posAY][posAX].setBackground(Color.red);
            for (Point pos: pontos) {
                painels[pos.y][pos.x].setBackground(Color.black);
            }
            this.revalidate();
        } else {
            gameOver();
        }
        
    }
    
    private class HandlerTeclado extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            
            if(e.getKeyCode() == 37){
                tecla = "esquerda";
            }else if(e.getKeyCode() == 38){
                tecla = "cima";
            }else if(e.getKeyCode() == 39){
                tecla = "direita";
            }else if(e.getKeyCode() == 40){
                tecla = "baixo";
            }
            
        }
        
    }
    
}
