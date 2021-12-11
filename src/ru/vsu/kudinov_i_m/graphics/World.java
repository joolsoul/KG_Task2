package ru.vsu.kudinov_i_m.graphics;

import ru.vsu.kudinov_i_m.figure.IFigure;
import ru.vsu.kudinov_i_m.math.AffineTransformation;
import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    private List<IFigure> models = new ArrayList<>();
    private Color backgroundColor;

    public World(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<IFigure> getModels() {
        return models;
    }

    public void draw(AffineTransformation affineTransformation, IDrawer drawer) {
        Color oldColor = drawer.getGraphics().getColor();
        Stroke oldStroke = drawer.getGraphics().getStroke();
        List<Vector2> newPoints = new LinkedList<>();
        drawer.clear(backgroundColor);
        for (IFigure model : models) {
            drawer.getGraphics().setColor(model.getColor());
            drawer.getGraphics().setStroke(model.getStroke());
            for (Vector2 point : model.getPoints()) {
                newPoints.add(affineTransformation.affineTransform(point));
            }
            drawer.draw(newPoints);
        }
        drawer.getGraphics().setColor(oldColor);
        drawer.getGraphics().setStroke(oldStroke);
    }
}
