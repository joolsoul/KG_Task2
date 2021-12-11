package ru.vsu.kudinov_i_m.view;

import ru.vsu.kudinov_i_m.view.MainPanel;

import javax.swing.*;

public class FrameMain extends JFrame{

    public FrameMain() {
        setTitle("Affine transform");
        setSize(1500, 1000);
        add(new MainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
