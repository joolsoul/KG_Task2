package ru.vsu.kudinov_i_m.figure;

import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;
import java.util.List;

public interface IFigure {
    List<Vector2> getPoints();
    void setPoints(List<Vector2> points);
    Color getColor();
    Stroke getStroke();
}
