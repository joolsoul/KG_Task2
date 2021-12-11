package ru.vsu.kudinov_i_m.drawingObject;

import ru.vsu.kudinov_i_m.screen.ScreenConverter;

import java.awt.*;

public interface DrawingObject {
    void draw(Graphics2D graphics2D, ScreenConverter screenConverter);
}
