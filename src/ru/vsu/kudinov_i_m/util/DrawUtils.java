package ru.vsu.kudinov_i_m.util;

import java.awt.*;

public class DrawUtils {
    public static void drawWithColor(Graphics2D gr2d, Color color, Runnable drawAction) {
        Color oldC = gr2d.getColor();
        gr2d.setColor(color);
        drawAction.run();
        gr2d.setColor(oldC);
    }

    public static void drawWithFont(Graphics2D gr2d, Font font, Runnable drawAction) {
        Font oldF = gr2d.getFont();
        gr2d.setFont(font);
        drawAction.run();
        gr2d.setFont(oldF);
    }
}