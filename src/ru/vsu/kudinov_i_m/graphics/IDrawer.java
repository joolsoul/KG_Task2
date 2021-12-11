package ru.vsu.kudinov_i_m.graphics;

import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;
import java.util.List;

public interface IDrawer {

    Graphics2D getGraphics();
    void draw(List<Vector2> points);
    void clear(Color color);
}
