package ru.vsu.kudinov_i_m;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    private List<IFigure> models = new ArrayList<>();
    private Color backgroundColor;

    public Scene(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<IFigure> getModels() {
        return models;
    }

    public void drawWorld(AffineTransformation affineTransformation, IDrawer drawer) {
        List<Vector2> newPoints = new LinkedList<>();
        drawer.clear(backgroundColor);
        for (IFigure model : models) {
            for (Vector2 point : model.getPoints()) {
                newPoints.add(affineTransformation.affineTransform(point));
            }
            drawer.draw(newPoints);
        }
    }
}
