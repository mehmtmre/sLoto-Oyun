/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mem3.sansoyun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mehmetemre
 */
public class sloto extends JFrame implements ActionListener {
    
    private JButton oynabtn;
    private JLabel kznnump;
    private List<JButton> kznbtn;
    private List<JButton> sclbtn;
    private String kznnumm;
    private JTextField txfm =new JTextField("Bahis Miktarını Giriniz");
    private JTextField txf =new JTextField("1 - 49 Arasında 6 Farklı Numara Giriniz");
    private JLabel grlnum = new JLabel("Girilen Numaralar:");
    private JLabel kzn = new JLabel("Kazanç:");
    private JLabel bhsmklbl = new JLabel("Bahis Miktarı : ");
    private int count=0;
    private int[] secsayi1 = new int[6];
    private String secsayi;
    private int[] grlnsayi = new int[6];
    private String grlnsayi1;
    private String grlnsayi2;
    private float bhsmk;
    private float kazanc;
    
    
    public sloto(){
        
        super("SAYISAL LOTO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        kznbtn = new ArrayList<>();
        for(int i=1; i<=49; i++){
            JButton btn = new JButton(String.valueOf(i));
            btn.setEnabled(false);
            kznbtn.add(btn);
        }
        
        oynabtn = new JButton("Oyna");

        txf.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_BACK_SPACE )) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Sadece Sayı Giriniz");}
        }
        });
        
        txfm.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_ENTER || c == KeyEvent.VK_PERIOD)) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Sadece Sayı Giriniz");}
        }
        });
        
        txf.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
        if (txf.getText().isEmpty() || txf.getText().split(" " ).length != 6 || txfm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "1 - 49 Arasında 6 Sayı ve Bahis Miktarını Giriniz");
            }
        }
        });
        
        txfm.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
        JTextField source = (JTextField)e.getSource();
        if (source.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bu alan boş bırakılamaz.");
            source.requestFocus();
        }
        }
        });
        
        oynabtn.addActionListener(this);
        
        kznnump = new JLabel("Kazanan Numaralar:");
        
        JPanel btnpnl = new JPanel(new GridLayout(7,7));
        for(JButton btn : kznbtn){
            btnpnl.add(btn);
        }
        
        JPanel pnlk = new JPanel(new GridLayout(1,2));
        //pnlk.add(txflbl);
        pnlk.add(txf);
        //pnlk.add(txfmlbl);
        pnlk.add(txfm);
        JPanel pnl = new JPanel(new GridLayout(2,1));
        pnl.add(pnlk);
        pnl.add(oynabtn);
        
        JPanel pnl1 = new JPanel(new GridLayout(1,4));
        pnl1.add(kznnump);
        pnl1.add(grlnum);
        pnl1.add(bhsmklbl);
        pnl1.add(kzn);

        
        getContentPane().add(btnpnl,BorderLayout.CENTER);
        getContentPane().add(pnl1,BorderLayout.NORTH);
        getContentPane().add(pnl,BorderLayout.SOUTH);
        
        setMinimumSize(new Dimension(1100, 500));
        setPreferredSize(new Dimension(1100, 500));
        
        setLocationRelativeTo(null);
        
        pack();
        setVisible(true);
        
        getRootPane().setDefaultButton(oynabtn);
        oynabtn.setForeground(Color.WHITE);
    }
    
    public static void main(String[] args){
        new sloto();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (sclbtn != null){
            for(JButton btn : sclbtn){
                btn.setEnabled(false);
                btn.removeActionListener(btn.getActionListeners()[0]);
            }
        }
        if(txf.getText().isEmpty() && txfm.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "1 - 49 Arasında 6 Sayı ve Bahis Miktarını Giriniz");
        }
                else if(txf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "1 - 49 Arasında 6 Sayı Giriniz");
        }
                else if(txfm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bahis Miktarını Giriniz");
        }
        else{
        boolean valid = true;
        String[] sayilar = txf.getText().split("[ ,-]");
        Set<Integer> uniqueSayilar = new HashSet<>();
            for (String sayilar1 : sayilar) {
                int sayi = Integer.parseInt(sayilar1);
                if (sayi < 0 || sayi > 50) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "1 - 49 Arasında Numara Giriniz");
                    break;
                }else if(!uniqueSayilar.add(sayi)){
                    valid = false;
                    JOptionPane.showMessageDialog(null, "6 Farklı Numara Giriniz");
                    break;
                }else if(txf.getText().split("[ ,-]").length != 6){
                    valid = false;
                    JOptionPane.showMessageDialog(null, "6 Numara Giriniz");
                }   }
        if (valid) {
            List<Integer> kznnum=new ArrayList<>();
            
            sclbtn = new ArrayList<>();
            Collections.shuffle(kznbtn);
            Random rnd = new Random();
            
            for(int i=0; i<6; i++){
                int say = rnd.nextInt(49);
                JButton btn = kznbtn.get(say);
                if(!sclbtn.contains(btn)){
                    sclbtn.add(btn);
                    kznnum.add(Integer.parseInt(btn.getText()));
                }else{
                    i--;
                }
            }
            
            for(JButton btn : sclbtn){
                btn.setEnabled(true);
            }
            
            for(JButton btn : sclbtn){
                btn.addActionListener((ActionEvent e1) -> {
                    JOptionPane.showMessageDialog(null, "Kazanan Numara!");
                });
            }
            
            Collections.sort(kznnum);
            List<String> kznnums=new ArrayList<>();
            
            for(int i = 0; i<kznnum.size(); i++){
                kznnums.add(String.valueOf(kznnum.get(i)));
            }
            
            kznnumm = kznnums.toString();
            
            for(int i = 0; i<kznnum.size(); i++){
                secsayi1[i] = kznnum.get(i);
            }

            grlnsayi1 = txf.getText();
            String[] sayi1 = grlnsayi1.split("[ ,-]");
            for(int i=0; i<sayi1.length; i++){
                grlnsayi[i] = Integer.parseInt(sayi1[i]);
            }
            
            List<Integer> srlgrln = new ArrayList<>();
            for(int i = 0; i<grlnsayi.length; i++){
                srlgrln.add((grlnsayi[i]));
            }
            Collections.sort(srlgrln);
            grlnsayi2 = srlgrln.toString();
            
            count=0;
            for(int i=0; i<grlnsayi.length; i++){
                for(int a=0; a<secsayi1.length; a++){
                    if(grlnsayi[i] == secsayi1[a])
                        count = count + 1;
                }
            }
            
            String bhsmks = txfm.getText();
            bhsmk = Float.parseFloat(txfm.getText());
            kazanc=0;
            
            if(count == 0){
                kazanc = 0* bhsmk;
            }else if(count == 1){
                kazanc = (float) (bhsmk * 1);
            }else if(count == 2){
                kazanc = (float) (bhsmk*1.25);
            }else if(count == 3){
                kazanc = (float) (bhsmk * 1.5);
            }else if(count == 4){
                kazanc = (float) (2 * bhsmk);
            }else if(count == 5){
                kazanc = (float) (4 * bhsmk);
            }else if(count == 6){
                kazanc = (float) (bhsmk * 8);
            }
            
            
            kznnump.setText(" Kazanan Numaralar:" + kznnumm);
            grlnum.setText("Girilen Numaralar:" + grlnsayi2);
            bhsmklbl.setText("Bahis Miktarı:" + bhsmk);
            kzn.setText("Kazanç : " + String.valueOf(kazanc));
            
            txf.setText("");
            txfm.setText("");
        }
        }
    }
}
