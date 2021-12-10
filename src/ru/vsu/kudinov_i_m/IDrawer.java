package ru.vsu.kudinov_i_m;

import java.awt.*;
import java.util.List;

public interface IDrawer {

    void draw(List<Vector2> points);
    void clear(Color color);
}
