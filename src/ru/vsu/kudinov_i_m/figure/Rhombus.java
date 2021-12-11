package ru.vsu.kudinov_i_m.figure;

import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Rhombus implements IFigure {

    private Vector2 firstPoint; // левая точка
    private Vector2 secondPoint; // нижняя точка
    private Color color;
    private Stroke stroke;

    public Rhombus(Vector2 point1, Vector2 point2, Color color, Stroke stroke) {
        this.firstPoint = point1;
        this.secondPoint = point2;
        this.color = color;
        this.stroke = stroke;
    }

    @Override
    public List<Vector2> getPoints() {
        List<Vector2> points = new LinkedList<>();
        float height = (firstPoint.getRealY() - secondPoint.getRealY()) * 2;
        float width = (secondPoint.getRealX() - firstPoint.getRealX()) * 2;
        points.add(firstPoint);
        points.add(secondPoint);
        points.add(new Vector2(firstPoint.getRealX() + width, firstPoint.getRealY()));
        points.add(new Vector2(secondPoint.getRealX(), secondPoint.getRealY() + height));
        return points;
    }

    @Override
    public void setPoints(List<Vector2> points) {
        this.firstPoint = points.get(0);
        this.secondPoint = points.get(1);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Stroke getStroke() {
        return stroke;
    }

}
