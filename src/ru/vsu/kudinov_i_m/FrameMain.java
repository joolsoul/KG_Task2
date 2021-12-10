package ru.vsu.kudinov_i_m;

import javax.swing.*;

public class FrameMain extends JFrame{

    public FrameMain() {
        setTitle("Function");
        ImageIcon icon = new ImageIcon("src\\ru\\vsu\\fedosova_p_o\\resources\\appIcon.png");
        setIconImage(icon.getImage());
        setSize(1500, 1500);
        add(new MainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
