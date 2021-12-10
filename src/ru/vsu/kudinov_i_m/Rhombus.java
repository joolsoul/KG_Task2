package ru.vsu.kudinov_i_m;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Rhombus implements IFigure {

    private List<Vector2> points = new LinkedList<>();

    public Rhombus(Vector2 point1, Vector2 point2, Vector2 point3, Vector2 point4) {
        this.points.add(point1);
        this.points.add(point2);
        this.points.add(point3);
        this.points.add(point4);
    }

    @Override
    public List<Vector2> getPoints() {
        return points;
    }

    @Override
    public void setPoints(List<Vector2> points) {
        this.points = points;
    }

}
