package com.nbratti;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setTitle("Simulação do escalonamento de processos");

        JLabel text =  new JLabel("");

        this.setVisible(true);
    }
}
